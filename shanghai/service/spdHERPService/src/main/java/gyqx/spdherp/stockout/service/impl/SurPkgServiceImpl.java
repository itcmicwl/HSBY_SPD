package gyqx.spdherp.stockout.service.impl;

import common.cache.redis.StringObjectRedisTemplate;
import common.db.SimpleDao;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.UtilsContext;
import gyqx.spdherp.po.*;
import gyqx.spdherp.stockout.Constance;
import gyqx.spdherp.stockout.dao.RequestOutDao;
import gyqx.spdherp.stockout.dao.SurPkgDao;
import gyqx.spdherp.stockout.service.SurPkgService;
import gyqx.spdherp.stockout.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @Author liangwu
 * @Date 18-10-8 下午2:16
 */
@Service
public class SurPkgServiceImpl implements SurPkgService {
    @Autowired
    private SurPkgDao dao;

    @Autowired
    private UtilsContext utilsContext;

    @Autowired
    private RequestOutDao requestOutDao;

    @Autowired
    private SimpleDao simpleDao;

    @Autowired
    private StringObjectRedisTemplate redisTemplate;

    @Override
    public QueryResult<SurPkgVo> listSurPkg(String stockId, String deptId, int pageNum, int pageSize) {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        String userId = utilsContext.getUserStateUtils().getCurrent().getUserId();

        Assert.isTrue(checkUserOpStock(hosId, userId, stockId), "非法库房");

        PageUtils.startPage(pageNum, pageSize);
        return PageUtils.endPage(dao.listSurPkg(stockId, deptId, hosId));
    }

    @Override
    public SurPkgVo getSurPkg(String pkgCode) {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        String userId = utilsContext.getUserStateUtils().getCurrent().getUserId();
        SurPkgVo surPkgVo = dao.getSurPkg(pkgCode, hosId);
        Assert.notNull(surPkgVo, "未找到对应手术包");
        if (surPkgVo.getCurStockId() != null) {
            Assert.isTrue(checkUserOpStock(hosId, userId, surPkgVo.getCurStockId()), "此用户无权访问此订单");
        }
        return surPkgVo;
    }

    @Override
    public int saveOutStockBill(List<String> pkgCodeList) throws Exception {
        String corpId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        String userId = utilsContext.getUserStateUtils().getCurrent().getUserId();

        List<SurPkgVo> surPkgVoList = dao.getSurPkgs(pkgCodeList, corpId);

        Assert.isTrue(surPkgVoList != null &&
                surPkgVoList.stream().map(SurPkgVo::getCurStockId).distinct().count() == 1 &&
                surPkgVoList.stream().map(SurPkgVo::getApplyBillId).distinct().count() == 1, "手术包非同一请购或库房");

        String curStockId = surPkgVoList.get(0).getCurStockId();
        String applyBillId = surPkgVoList.get(0).getApplyBillId();
        Assert.isTrue(checkUserOpStock(corpId, userId, curStockId), "此用户无法对当前手术包进行出库");

        EntireDeptBuyVo deptBuyVo = dao.getEntireDeptBuyVo(applyBillId);

        Assert.notNull(deptBuyVo, "系统未找到请购单 " + applyBillId);

        EntireOutStockVo outStockBill = generateOutStockBill(corpId, surPkgVoList, deptBuyVo);

        List<SurPkgListVo> nonUniqueSurPkgList = surPkgVoList.stream()
                .flatMap(item -> item.getSurPkgSubList().stream())
                .filter(item -> item.getUniqueKind() == Constance.UNIQUE_KIND_UNIQUE)
                .collect(toList());
        List<SurPkgListVo> uniqueSurPkgList = surPkgVoList.stream()
                .flatMap(item -> item.getSurPkgSubList().stream())
                .filter(item -> item.getUniqueKind() != Constance.UNIQUE_KIND_UNIQUE)
                .collect(toList());

        List<String> surPkgSurIds = surPkgVoList.stream().map(SurPkgVo::getSurId).collect(toList());

        Map<String, String> deptSubMap = deptBuyVo.getSubList().stream()
                .filter(item -> StringUtils.hasText(item.getSurId()) && surPkgSurIds.contains(item.getSurId()))
                .collect(Collectors.toMap(item -> item.getGoodsId() + item.getSurId(), DeptBuySub::getId));
        Assert.notEmpty(deptSubMap.values(), "未找到请购单子表对应手术包ID");
        outStockBill.getEntireOutStockListVoList().addAll(makeOutStockBillSub(outStockBill, deptSubMap, nonUniqueSurPkgList));
        outStockBill.getEntireOutStockListVoList().addAll(makeOutStockBillSub(outStockBill, deptSubMap, uniqueSurPkgList));

        List<OutStockBatch> outStockBatchList = outStockBill.getEntireOutStockListVoList().stream()
                .flatMap(item -> item.getOutStockBatchList().stream()).collect(toList());
        List<OutStockUniqueCode> outStockUniqueCodeList = outStockBill.getEntireOutStockListVoList().stream()
                .flatMap(item -> item.getOutStockUniqueCodeList().stream()).collect(toList());

        OutStock outStockPo = new OutStock();
        BeanUtils.copyProperties(outStockBill, outStockPo);
        simpleDao.insert(outStockPo);
        Integer insertResult = requestOutDao.insertOutStockBill(outStockBill.getEntireOutStockListVoList(), outStockBatchList, outStockUniqueCodeList, Collections.emptyList());
        Assert.isTrue(insertResult == 0, "写数据库错误");

        int updateTakingResult = dao.updateTakingStock(pkgCodeList, outStockBill.getId());
        Assert.isTrue(updateTakingResult > 0, "写数据库错误");

        int updateSurPkgResult = dao.updateSurPkg(pkgCodeList);
        Assert.isTrue(updateSurPkgResult > 0, "更新手术包状态错误");

        Assert.isTrue(dao.insertSurLog(pkgCodeList, outStockBill.getId(), userId, "一级库出库") > 0, "更新手术包日志错误");
        return 0;
    }

    private List<EntireOutStockListVo> makeOutStockBillSub(EntireOutStockVo outStockBill, Map<String, String> deptSubMap, List<SurPkgListVo> surPkgListVoList) {
        List<EntireOutStockListVo> outStockListVoList = new ArrayList<>();
        int billRow = outStockBill.getEntireOutStockListVoList().stream().mapToInt(OutStockList::getOutBillRow).max().orElse(0);
        for (SurPkgListVo surPkgListVo : surPkgListVoList) {
            Map<OutStockListCat, List<SurPkgBatchVo>> surMap = surPkgListVo.getSurPkgBatchList().stream().collect(Collectors.groupingBy(item ->
                    new OutStockListCat(
                            item.getGoodsId(),
                            item.getSurId(),
                            item.getSurCode(),
                            item.getBatchCode(),
                            item.getSterilizationCode(),
                            item.getSterilizationDate(),
                            item.getSterilizationEndDate(),
                            item.getExpdtEndDate())));
            for (Map.Entry<OutStockListCat, List<SurPkgBatchVo>> entry : surMap.entrySet()) {
                OutStockListCat outStockListCat = entry.getKey();

                ++billRow;
                EntireOutStockListVo outStockSub = generateOutStockSub(outStockBill.getOutOrgId(), outStockBill.getBillId(), deptSubMap, surPkgListVo, billRow, outStockListCat);

                if (surPkgListVo.getUniqueKind() == Constance.UNIQUE_KIND_UNIQUE) {
                    outStockSub.getOutStockBatchList().addAll(generateOutStockBatch(outStockBill.getOutOrgId(), entry, outStockSub));
                    BigDecimal qty = outStockSub.getOutStockBatchList().stream().map(OutStockBatch::getQty).reduce(BigDecimal.ZERO, BigDecimal::add);
                    outStockSub.setOutQty(qty);
                } else {
                    outStockSub.getOutStockUniqueCodeList().addAll(generateOutStockUniqueCode(outStockBill.getOutOrgId(), entry, outStockSub));
                    BigDecimal qty = outStockSub.getOutStockUniqueCodeList().stream().map(OutStockUniqueCode::getQty).reduce(BigDecimal.ZERO, BigDecimal::add);
                    outStockSub.setOutQty(qty);
                }

                outStockListVoList.add(outStockSub);
            }
        }
        return outStockListVoList;
    }

    private EntireOutStockListVo generateOutStockSub(String corpId, String billId, Map<String, String> deptSubMap, SurPkgListVo surPkgListVo, int billRow, OutStockListCat outStockListCat) {
        EntireOutStockListVo outStockSub = new EntireOutStockListVo();
        String subId = corpId + utilsContext.getSysAtomUtil().newValue("out_stock_list_id");
        outStockSub.setId(subId);
        outStockSub.setPid(billId);
        outStockSub.setBillId(billId);
        outStockSub.setSourceBillId(deptSubMap.get(outStockListCat.getGoodsId() + outStockListCat.getSurId()));
        outStockSub.setOutBillRow(billRow);
        outStockSub.setProvId(surPkgListVo.getProvId());
        outStockSub.setProvCode(surPkgListVo.getProvCode());
        outStockSub.setProvName(surPkgListVo.getProvName());
        outStockSub.setGoodsId(surPkgListVo.getGoodsId());
        outStockSub.setGoodsName(surPkgListVo.getGoodsName());
        outStockSub.setGoodsGg(surPkgListVo.getGoodsSpec());
        outStockSub.setMfrsId(surPkgListVo.getMfrsId());
        outStockSub.setMfrsName(surPkgListVo.getMfrsName());
        outStockSub.setMade(surPkgListVo.getMade());
        outStockSub.setPacketCode(surPkgListVo.getPacketCode());
        outStockSub.setIsPacket("0");
        outStockSub.setIsUnique(String.valueOf(surPkgListVo.getUniqueKind()));
        outStockSub.setBatchCode(outStockListCat.getBatchNo());
        outStockSub.setSterilizationCode(outStockListCat.getSterilizationCode());
        outStockSub.setSterilizationDate(outStockListCat.getSterilizationDate());
        outStockSub.setSterilizationEndDate(outStockListCat.getSterilizationEndDate());
        outStockSub.setExpdtEndDate(outStockListCat.getExpdtEndDate());
        outStockSub.setUnit(surPkgListVo.getUnit());
        outStockSub.setStatus(0);
        outStockSub.setVersion(0);
        outStockSub.setCertificateCode(surPkgListVo.getCertCode());
        outStockSub.setSurCode(outStockListCat.getSurCode());
        return outStockSub;
    }

    private List<OutStockBatch> generateOutStockBatch(String corpId, Map.Entry<OutStockListCat, List<SurPkgBatchVo>> entry, EntireOutStockListVo outStockSub) {
        List<OutStockBatch> outStockBatchList = new ArrayList<>();
        for (SurPkgBatchVo surPkgBatchVo : entry.getValue()) {
            OutStockBatch outStockBatch = new OutStockBatch();
            String batchId = corpId + utilsContext.getSysAtomUtil().newValue("out_stock_batch_id");
            outStockBatch.setId(batchId);
            outStockBatch.setPid(outStockSub.getId());
            outStockBatch.setBillId(outStockSub.getBillId());
            outStockBatch.setPRowId(outStockSub.getOutBillRow());
            outStockBatch.setProvId(outStockSub.getProvId());
            outStockBatch.setGoodsId(outStockSub.getGoodsId());
            outStockBatch.setGoodsBatchId(surPkgBatchVo.getBatchId());
            outStockBatch.setInPrice(surPkgBatchVo.getInPrice());
            // outStockBatch.setInTime(); 手术包信息未获取，需要从库存中获取
            outStockBatch.setQty(surPkgBatchVo.getQty());
            outStockBatch.setBigBatchCode(surPkgBatchVo.getBigBatchCode());
            outStockBatch.setVersion(0);
            outStockBatchList.add(outStockBatch);
        }
        return outStockBatchList;
    }

    private List<OutStockUniqueCode> generateOutStockUniqueCode(String corpId, Map.Entry<OutStockListCat, List<SurPkgBatchVo>> entry, EntireOutStockListVo outStockSub) {
        List<OutStockUniqueCode> outStockUniqueCodeList = new ArrayList<>();
        for (SurPkgBatchVo surPkgBatchVo : entry.getValue()) {
            OutStockUniqueCode outStockUniqueCode = new OutStockUniqueCode();
            String uniqueId = corpId + utilsContext.getSysAtomUtil().newValue("out_stock_unique_code_id");
            outStockUniqueCode.setId(uniqueId);
            outStockUniqueCode.setPid(outStockSub.getId());
            outStockUniqueCode.setBillId(outStockSub.getBillId());
            outStockUniqueCode.setPRowId(outStockSub.getOutBillRow());
            outStockUniqueCode.setProvId(outStockSub.getProvId());
            outStockUniqueCode.setGoodsId(outStockSub.getGoodsId());
            outStockUniqueCode.setGoodsBatchId(surPkgBatchVo.getBatchId());
            outStockUniqueCode.setInPrice(surPkgBatchVo.getInPrice());
            // outStockUniqueCode.setInTime();
            outStockUniqueCode.setUniqueCode(surPkgBatchVo.getUniqueCode());
            outStockUniqueCode.setEpc(surPkgBatchVo.getUniqueCode());
            outStockUniqueCode.setQty(surPkgBatchVo.getQty());
            outStockUniqueCode.setVersion(0);
            outStockUniqueCodeList.add(outStockUniqueCode);
        }
        return outStockUniqueCodeList;
    }

    private EntireOutStockVo generateOutStockBill(String corpId, List<SurPkgVo> surPkgVoList, EntireDeptBuyVo deptBuyVo) {

        String corpName = utilsContext.getUserStateUtils().getCurrent().getCorpName();
        String deptId = utilsContext.getUserStateUtils().getCurrent().getOrgId();
        String deptName = utilsContext.getUserStateUtils().getCurrent().getOrgName();
        String userId = utilsContext.getUserStateUtils().getCurrent().getUserId();

        EntireOutStockVo outStockBill = new EntireOutStockVo();
        String billId = corpId + utilsContext.getSysAtomUtil().newValue("out_stock_id");
        outStockBill.setId(billId);
        outStockBill.setBillId(billId);
        outStockBill.setOutStockKind(Constance.OUTSTOCK_KIND_REQUEST.toString());
        outStockBill.setOutStockType(deptBuyVo.getBuyKind());
        outStockBill.setSourceBillId(deptBuyVo.getId());
        outStockBill.setOutOrgId(corpId);
        outStockBill.setOutOrgName(corpName);
        outStockBill.setOutDeptId(deptId);
        outStockBill.setOutDeptName(deptName);
        outStockBill.setOutStocId(surPkgVoList.get(0).getCurStockId());
        outStockBill.setInOrgId(corpId);
        outStockBill.setInOrgName(corpName);
        outStockBill.setInDeptId(surPkgVoList.get(0).getDeptId());
        outStockBill.setInDeptName(surPkgVoList.get(0).getDeptName());
        outStockBill.setFiller(userId);
        outStockBill.setRecAddressId(deptBuyVo.getRecAddressId());
        outStockBill.setRecLinkman(deptBuyVo.getRecLinkman());
        outStockBill.setRecLinkmanPhone(deptBuyVo.getRecLinkmanPhone());
        outStockBill.setRecAddress(deptBuyVo.getSickerName());
        String pkgCodeList = org.apache.commons.lang3.StringUtils.join(surPkgVoList.stream().map(SurPkgVo::getId).collect(toList()), ",");
        outStockBill.setRemark("手术包," + pkgCodeList);
        outStockBill.setStatus(Constance.OUTSTOCKBILL_STATUS_SUBMIT);
        outStockBill.setVersion(0);
        Date now = new Date();
        outStockBill.setLastUpdateDatetime(now);
        outStockBill.setFillDate(now);
        return outStockBill;
    }

    @Data
    @AllArgsConstructor
    // 用于手术包到出库单子表分类
    private class OutStockListCat {
        private String goodsId;
        private String surId;
        private String surCode;
        private String batchNo;
        private String sterilizationCode;
        private Date sterilizationDate;
        private Date sterilizationEndDate;
        private Date expdtEndDate;
    }

    private boolean checkUserOpStock(String hosId, String userId, String stockId) {
        String key = "checkUserOpStock@" + hosId + "/" + userId + "/" + stockId;
        if (redisTemplate.hasKey(key)) {
            return redisTemplate.boundValueOps(key).get().equals(1);
        } else {
            Integer ret = dao.checkUserOpStock(hosId, userId, stockId);
            redisTemplate.opsForValue().set(key, ret, 1, TimeUnit.HOURS);
            return ret > 0;
        }
    }
}
