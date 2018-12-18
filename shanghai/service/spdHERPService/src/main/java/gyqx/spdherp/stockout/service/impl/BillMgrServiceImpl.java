package gyqx.spdherp.stockout.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import common.db.SimpleDao;
import common.exception.ParameterException;
import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import gyqx.platform.po.SysOrg;
import gyqx.spdherp.po.*;
import gyqx.spdherp.stockMgr.vo.InStockListVo;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.Constance;
import gyqx.spdherp.stockout.dao.BillMgrDao;
import gyqx.spdherp.stockout.dao.SurPkgDao;
import gyqx.spdherp.stockout.po.ReturnProvBill;
import gyqx.spdherp.stockout.po.ReturnProvBillBatch;
import gyqx.spdherp.stockout.po.ReturnProvBillSub;
import gyqx.spdherp.stockout.po.ReturnProvBillUniqueCode;
import gyqx.spdherp.stockout.service.BillMgrService;
import gyqx.spdherp.stockout.service.RequestOutService;
import gyqx.spdherp.stockout.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * @Auther Liangwu
 * @Date 17-8-29 上午11:16
 */
@Service
public class BillMgrServiceImpl implements BillMgrService {
    @Resource
    private UtilsContext utilsContext;

    @Resource
    private BillMgrDao billMgrDao;

    @Resource
    private SimpleDao simpleDao;

    @Resource
    private SurPkgDao surPkgDao;

    @Resource
    private RequestOutService requestOutService;

//    @Resource
//    private IDeptBuySubService deptBuySubService;

    @Override
    public List<SysOrgVo> listSysOrg(String stockId, Boolean submitted) {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        return billMgrDao.listSysOrgByCorpId(user.getCorpId(), stockId, user.getOrgId(), user.getUserId(), submitted);
    }

    @Override
    public List<OutStockBillVo> listBill(String deptId, String stockId, Date beginDate, Date endDate, String status) {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        endDate = endDate == null ? new Date() : endDate;
        beginDate = beginDate == null ? DateUtils.addDays(endDate, -7) : beginDate;
        List<String> stockIdList;
        if (StringUtils.isNotBlank(stockId)) {
            stockIdList = Lists.newArrayList(stockId);
        } else {
            stockIdList = requestOutService.listOwnStock().stream().map(IdNameVo::getId).collect(toList());
        }
        Assert.isTrue(stockIdList.size() > 0, "用户库房错误");
        return billMgrDao.listBill(user.getCorpId(), deptId, stockIdList, beginDate, endDate, status, user.getUserId());
    }

    @Override
    public OutStockBillVo getBillDetail(String id) {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        return billMgrDao.getBillDetail(id, user.getUserId());
    }

    @Override
    @Transactional
    public Integer updateOutStock(OutStock outStock) throws Exception {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();

        switch (outStock.getStatus()) {
            case 10: // 提交
                outStock.setFiller(user.getUserId());
                outStock.setFillDate(new Date());
//                writeHosTakingStock(outStock.getBsId());
                break;
            case 20: // 审核
                outStock.setAuditor(user.getUserId());
                outStock.setAuditDate(new Date());
                break;
            case 30: // 记帐
                outStock.setAccounter(user.getUserId());
                outStock.setAccountDate(new Date());
                if (Objects.equals(outStock.getOutStockKind(), gyqx.spdherp.stockout.Constance.OUTSTOCK_KIND_REQUEST.toString())) { // 请购出库回写状态
                    writeDeptBuySub(outStock.getId(), false);
                }
                // 商品正式出库
                // 调用存储过程
                if (!billMgrDao.stockpilebatchOutstock(outStock)) {
                    throw new ParameterException("记帐失败！", -1);
                }
                removeTakingInfo(outStock.getId());
                break;
            case 40: // 作废
                // 恢复库存
            case 50: // 驳回
                // 恢复库存
                OutStock outStockDb = simpleDao.get(OutStock.class, outStock.getId());
                if (StringUtils.isNotEmpty(outStockDb.getRemark()) && outStockDb.getRemark().startsWith("手术包")) {
                    // 手术包
                    String pkgCodeString = StringUtils.substringAfter(outStockDb.getRemark(), "手术包,");
                    List<String> pkgCodeList = Arrays.asList(StringUtils.split(pkgCodeString, ","));
                    Assert.isTrue(billMgrDao.updateSurPkg(pkgCodeList) > 0, "更新手术包状态错误");
                    Assert.isTrue(billMgrDao.updateTakingInfo(outStock.getId(), pkgCodeList) > 0, "更新占用库存错误");
                    Assert.isTrue(surPkgDao.insertSurLog(pkgCodeList, outStock.getId(), user.getUserId(), "设备科驳回") > 0, "更新包日志错误");
                } else {
                    // 非手术包
                    removeTakingInfo(outStock.getId());
                }
                removeOutStockListQty(outStock.getId());
                break;
            default:
                break;
        }

        outStock.setLastUpdateDatetime(new Date());
        if (simpleDao.updateNotNullFields(outStock) > 0) {
            return outStock.getVersion() + 1;
        } else {
            throw new ParameterException("更新状态失败");
        }
    }

    @Override
    public List<OutStockUniqueCode> fillOutStockBill(List<String> uniqueCodeList) throws ParameterException {
        return fillOutStockBill(uniqueCodeList, 1, null);
    }

    @Override
    public List<OutStockUniqueCode> fillOutStockBill(List<String> uniqueCodeList, Integer fillType, String execDeptId) throws ParameterException {
        if (fillType != Constance.FILL_OUT_FAKE && fillType != Constance.FILL_OUT_REAL) {
            throw new ParameterException("补出库单类型fillType错误。");
        }
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        String hosId;
        if (null != user) {
            hosId = user.getCorpId();
        } else {
            hosId = billMgrDao.getHosIdByUniqueCode(uniqueCodeList.get(0));
        }
        List<EntireOutStockVo> outStockVos = billMgrDao.getWholeOutStockByUniqueCode(uniqueCodeList, hosId, fillType);
        List<EntireOutStockListVo> outStockListVos = outStockVos.stream().map(EntireOutStockVo::getEntireOutStockListVoList).flatMap(Collection::stream).collect(toList());
        List<OutStockUniqueCode> outStockUniqueCodeList = outStockListVos.stream().map(EntireOutStockListVo::getOutStockUniqueCodeList).flatMap(Collection::stream).collect(toList());
//        if (uniqueCodeList.size() != outStockUniqueCodeList.size()) {
//            throw new ParameterException("唯一码数据不一致");
//        }
        for (EntireOutStockVo main : outStockVos) {
            String mId = hosId +
                    utilsContext.getSysAtomUtil().newValue("out_stock_id");
            main.setId(mId);
            main.setBillId(mId);
            main.setOutStockType(Constance.OUTSTOCKBILL_TYPE_REAL); // 实库
            main.setLastUpdateDatetime(new Date());
            main.setVersion(main.getVersion() + 1);
            if (main.getOutStockKind().equals(Constance.OUTSTOCK_KIND_REQUEST.toString())
                    && execDeptId != null) {
                main.setInDeptId(execDeptId);
                SysOrg sysOrg = simpleDao.get(SysOrg.class, execDeptId);
                main.setInDeptName(sysOrg.getEname());
            }
            int rowNum = 1;
            for (EntireOutStockListVo sub : main.getEntireOutStockListVoList()) {
                String sId = hosId + utilsContext.getSysAtomUtil().newValue("out_stock_list_id");
                sub.setId(sId);
                sub.setPid(mId);
                sub.setBillId(mId);
                sub.setOutBillRow(rowNum++);
                BigDecimal subOutQtySum = BigDecimal.ZERO;
                sub.setLastUpdateDatetime(new Date());
                sub.setVersion(sub.getVersion() + 1);
                for (OutStockUniqueCode unique : sub.getOutStockUniqueCodeList()) {
                    String uId = hosId + utilsContext.getSysAtomUtil().newValue("out_stock_unique_code_id");
                    unique.setId(uId);
                    unique.setPid(sId);
                    unique.setBillId(mId);
                    unique.setQty(new BigDecimal(fillType));
                    subOutQtySum = subOutQtySum.add(unique.getQty());
                    unique.setPRowId(sub.getOutBillRow());
                    unique.setLastUpdateDatetime(new Date());
                    unique.setVersion(unique.getVersion() + 1);
                }
                sub.setOutQty(subOutQtySum);
            }
        }
        if (outStockVos.size() > 0 && outStockListVos.size() > 0 && outStockUniqueCodeList.size() > 0)
            billMgrDao.insertFillBills(outStockVos, outStockListVos, outStockUniqueCodeList);
        return outStockUniqueCodeList;
    }

    @Override
    public List<EntireOutStockListVo> fillOutStockBillByBigBatch(List<BigBatch4FillVo> bigBatchInfos, Integer fillType, String execDeptId) throws ParameterException {
        if (fillType != Constance.FILL_OUT_FAKE && fillType != Constance.FILL_OUT_REAL) {
            throw new ParameterException("补出库单类型fillType错误。");
        }
        List<EntireOutStockVo> outStockVos = billMgrDao.getWholeOutStockByBigBatch(bigBatchInfos, fillType);
        if (CollectionUtils.isEmpty(outStockVos)) {
            throw new ParameterException("未找到相应出库单。");
        }
        if (outStockVos.stream().map(EntireOutStockVo::getOutOrgId).distinct().count() != 1) {
            throw new ParameterException("非同一医院出库单。");
        }

        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        String hosId;
        if (null != user) {
            hosId = user.getCorpId();
        } else {
            hosId = outStockVos.get(0).getOutOrgId();
        }

        Map<String, BigDecimal> bigBatchMap = bigBatchInfos.stream().collect(groupingBy(item -> item.getSurCode() + item.getBigBatchCode(), reducing(BigDecimal.ZERO, BigBatch4FillVo::getQty, BigDecimal::add)));
        for (EntireOutStockVo main : outStockVos) {
            String mId = hosId +
                    utilsContext.getSysAtomUtil().newValue("out_stock_id");
            main.setId(mId);
            main.setBillId(mId);
            main.setOutStockType(Constance.OUTSTOCKBILL_TYPE_REAL); // 实库
            main.setLastUpdateDatetime(new Date());
            main.setVersion(main.getVersion() + 1);
            main.setRemark(fillType == Constance.FILL_OUT_FAKE ? "消耗补单据" : "冲红补单据");
            if (main.getOutStockKind().equals(Constance.OUTSTOCK_KIND_REQUEST.toString())
                    && execDeptId != null) {
                main.setInDeptId(execDeptId);
                SysOrg sysOrg = simpleDao.get(SysOrg.class, execDeptId);
                main.setInDeptName(sysOrg.getEname());
            }
            int rowNum = 1;
            for (EntireOutStockListVo sub : main.getEntireOutStockListVoList()) {
                String sId = hosId + utilsContext.getSysAtomUtil().newValue("out_stock_list_id");
                sub.setId(sId);
                sub.setPid(mId);
                sub.setBillId(mId);
                sub.setOutBillRow(rowNum++);
                BigDecimal subOutQtySum = BigDecimal.ZERO;
                sub.setLastUpdateDatetime(new Date());
                sub.setVersion(sub.getVersion() + 1);
                for (OutStockBatch batch : sub.getOutStockBatchList()) {
                    String bId = hosId + utilsContext.getSysAtomUtil().newValue("out_stock_batch_id");
                    batch.setId(bId);
                    batch.setPid(sId);
                    batch.setBillId(mId);
                    // 出库数量乘以fillType，-1为冲红，出库数量为负数
                    batch.setQty(bigBatchMap.getOrDefault(sub.getSurCode() + batch.getBigBatchCode(), BigDecimal.ZERO).multiply(BigDecimal.valueOf(fillType)));
                    subOutQtySum = subOutQtySum.add(batch.getQty());
                    batch.setPRowId(sub.getOutBillRow());
                    batch.setLastUpdateDatetime(new Date());
                    batch.setVersion(batch.getVersion() + 1);
                }
                sub.setOutQty(subOutQtySum);
            }
        }

        List<EntireOutStockListVo> outStockListVos = outStockVos.stream().map(EntireOutStockVo::getEntireOutStockListVoList).flatMap(Collection::stream).collect(toList());
        List<OutStockBatch> outStockBatchList = outStockListVos.stream().map(EntireOutStockListVo::getOutStockBatchList).flatMap(Collection::stream).collect(toList());
        if (!billMgrDao.insertFillBills(outStockVos, outStockListVos, null, outStockBatchList)) {
            throw new ParameterException("写数据库错误");
        }
        return outStockListVos;
    }

    @Override
    public Boolean updateOutStockStatus(String outStockId) throws ParameterException {
        int result = 0;
        OutStockQtySum outStockQtySum = billMgrDao.getOutStockQtySum(outStockId);
        if (outStockQtySum == null) {
            throw new ParameterException("未找到出库单信息");
        }
        Integer inQtySum = (outStockQtySum.getBatchInQty() == null ? 0 : outStockQtySum.getBatchInQty()) +
                (outStockQtySum.getUniqueInQty() == null ? 0 : outStockQtySum.getUniqueInQty());
        String sql = "UPDATE out_stock SET status = ?, version = version + 1, last_update_datetime = NOW() WHERE id = ? AND version = ?";
        if (outStockQtySum.getOutQty() > inQtySum) {
            result = simpleDao.executeSql(sql, 69, outStockQtySum.getId(), outStockQtySum.getVersion());
        } else if (outStockQtySum.getOutQty().equals(inQtySum)) {
            result = simpleDao.executeSql(sql, 60, outStockQtySum.getId(), outStockQtySum.getVersion());
        }
        return result > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean checkSurPkg(String id) {
        OutStock outStock = simpleDao.get(OutStock.class, id);
        Assert.notNull(outStock, "未找到对应出库单");
        return StringUtils.isNotEmpty(outStock.getRemark()) && outStock.getRemark().startsWith("手术包");
    }

    @Override
    public boolean updateOutStockByReturn(List<InStockListVo> removeRowList) throws Exception {
        List<String> billIds = removeRowList.stream().map(InStockListVo::getOutBillId).distinct().collect(toList());
        if (billIds.size() != 1 && StringUtils.isNotEmpty(billIds.get(0))) {
            throw new ParameterException("错误，非同一出库单");
        }
        EntireOutStockVo outStockVo = billMgrDao.getEntireOutStockById(billIds.get(0));
        String remark = StringUtils.isNotEmpty(outStockVo.getRemark()) ? outStockVo.getRemark() + ";" : "" + "入库拒收商品";
        Map<IdVersion, Boolean> subIdMap = Maps.newHashMap();
        List<EntireOutStockListVo> subList = Lists.newArrayList();
        for (InStockListVo row : removeRowList) {
            List<EntireOutStockListVo> outStockSubList = outStockVo.getEntireOutStockListVoList().stream().filter(item -> item.getBillId().equals(row.getOutBillId())
                    && item.getOutBillRow() == row.getOutBillRow()).collect(toList());
            Assert.isTrue(outStockSubList.size() == 1, "出库单行号错误");
            EntireOutStockListVo outStockSub = outStockSubList.get(0);
            subList.add(outStockSub);
            subIdMap.put(new IdVersion(outStockSub.getId(), outStockSub.getVersion()), outStockSub.getIsUnique().equals(Constance.UNIQUE_NONE) ? Boolean.FALSE : Boolean.TRUE);
        }
        Assert.isTrue(billMgrDao.updateEntireOutStock(outStockVo.getId(), remark, outStockVo.getVersion(), subIdMap), "更新出库单失败");
        return insertReturnProvBill(outStockVo, subList);
    }

    @Override
    public boolean genReturnProvBillByInStock(InStockVo inStock) {
        Assert.isTrue(StringUtils.isNotEmpty(inStock.getOutBillId()), "出库单号不能为空");
        EntireOutStockVo outStockVo = billMgrDao.getEntireOutStockById(inStock.getOutBillId());
        return insertReturnProvBill(outStockVo, outStockVo.getEntireOutStockListVoList());
    }

    private boolean insertReturnProvBill(EntireOutStockVo outStockVo, List<EntireOutStockListVo> subList) {
        List<String> goodsIds = subList.stream().filter(item -> StringUtils.isNotEmpty(item.getGoodsId()))
                .map(EntireOutStockListVo::getGoodsId).collect(toList());
        List<ProvGoods> provGoodsList = billMgrDao.listProvGoods(goodsIds);
        Assert.isTrue(provGoodsList.stream().map(ProvGoods::getGoodsId).distinct().count()
                        == provGoodsList.stream().map(ProvGoods::getProvGoodsId).distinct().count(),
                "商品与供应商关系不唯一");
        List<ReturnProvBill> returnProvBillList = Lists.newArrayList();
        List<ReturnProvBillSub> returnProvBillSubList = Lists.newArrayList();
        List<ReturnProvBillBatch> returnProvBillBatchList = Lists.newArrayList();
        List<ReturnProvBillUniqueCode> returnProvBillUniqueCodeList = Lists.newArrayList();
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        Date now = new Date();
        provGoodsList.stream().collect(groupingBy(ProvGoods::getProvId)).forEach((provId, value) -> {
            ReturnProvBill main = ReturnProvBill.builder()
                    .id(utilsContext.getSysAtomUtil().newValue("return_prov_bill_id"))
                    .sourceBillId(subList.get(0).getBillId())
                    .returnKind(Constance.RETURN_KIND_STOCK)
                    .hosId(user.getCorpId())
                    .hosName(user.getCorpName())
                    .deptId(outStockVo.getOutDeptId())
                    .deptName(outStockVo.getOutDeptName())
                    .stocId(outStockVo.getOutStocId())
                    .provCode(value.get(0).getProvErpCode())
                    .provId(provId)
                    .provName(value.get(0).getProvName())
                    .status(Constance.RETURN_STATUS_VERIFIED)
                    .filler(user.getUserId())
                    .fillerName(user.getCname())
                    .fillDate(now)
                    .auditor(user.getUserId())
                    .remark("出库单" + subList.get(0).getBillId() + "拒收退货")
                    .lastUpdateDatetime(now)
                    .version(0)
                    .build();
            returnProvBillList.add(main);
            int rowNum = 1;
            for (ProvGoods goods : value) {
                List<EntireOutStockListVo> outStockSubList = subList.stream().filter(item -> item.getGoodsId().equals(goods.getGoodsId())).collect(toList());
                for (EntireOutStockListVo outStockListVo : outStockSubList) {
                    ReturnProvBillSub sub = ReturnProvBillSub.builder()
                            .id(utilsContext.getSysAtomUtil().newValue("return_prov_bill_sub_id"))
                            .billId(main.getId())
                            .rowNum(rowNum++)
                            .sourceBillId(outStockListVo.getBillId())
                            .sourceBillRow(outStockListVo.getOutBillRow())
                            .provGoodsId(goods.getProvGoodsId())
                            .hosGoodsId(goods.getGoodsId())
                            .hosGoodsName(outStockListVo.getGoodsName())
                            .erpGoodsCode(goods.getProvGoodsErp())
                            .goodsGg(outStockListVo.getGoodsGg())
                            .mrfsId(outStockListVo.getMfrsId())
                            .mfrsName(outStockListVo.getMfrsName())
                            .made(outStockListVo.getMade())
                            .qty(outStockListVo.getOutQty())
                            .unit(outStockListVo.getUnit())
                            .uniqueKind(Integer.valueOf(outStockListVo.getIsUnique()))
                            .batchCode(outStockListVo.getBatchCode())
                            .sterilizationEndDate(outStockListVo.getSterilizationEndDate())
                            .sterilizationCode(outStockListVo.getSterilizationCode())
                            .sterilizationDate(outStockListVo.getSterilizationDate())
                            .expdtEndDate(outStockListVo.getExpdtEndDate())
                            .certificateCode(outStockListVo.getCertificateCode())
                            .lastUpdateDatetime(now)
                            .version(0)
                            .build();
                    returnProvBillSubList.add(sub);
                    for (OutStockBatch outStockBatch : outStockListVo.getOutStockBatchList()) {
                        ReturnProvBillBatch returnProvBillBatch = ReturnProvBillBatch.builder()
                                .id(utilsContext.getSysAtomUtil().newValue("return_prov_bill_batch_id"))
                                .pid(sub.getId())
                                .billId(main.getId())
                                .pRowId(sub.getRowNum())
                                .provId(provId)
                                .goodsId(outStockBatch.getGoodsId())
                                .goodsBatchId(outStockBatch.getGoodsBatchId())
                                .qty(outStockBatch.getQty())
                                .bigBatchCode(outStockBatch.getBigBatchCode())
                                .lastUpdateDatetime(now)
                                .version(0)
                                .build();
                        returnProvBillBatchList.add(returnProvBillBatch);
                    }
                    for (OutStockUniqueCode outStockUniqueCode : outStockListVo.getOutStockUniqueCodeList()) {
                        ReturnProvBillUniqueCode returnProvBillUniqueCode = ReturnProvBillUniqueCode.builder()
                                .id(utilsContext.getSysAtomUtil().newValue("return_prov_bill_unique_id"))
                                .pid(sub.getId())
                                .billId(main.getId())
                                .pRowId(sub.getRowNum())
                                .provId(provId)
                                .goodsId(outStockUniqueCode.getGoodsId())
                                .goodsBatchId(outStockUniqueCode.getGoodsBatchId())
                                .uniqueCode(outStockUniqueCode.getUniqueCode())
                                .lastUpdateDatetime(now)
                                .version(0)
                                .build();
                        returnProvBillUniqueCodeList.add(returnProvBillUniqueCode);
                    }
                }
            }
        });
        return billMgrDao.insertWholeReturnProvBillBatch(returnProvBillList, returnProvBillSubList, returnProvBillBatchList, returnProvBillUniqueCodeList);
    }

    private void removeOutStockListQty(String billId) {
        simpleDao.executeSql("update out_stock_list set out_qty = 0, last_update_datetime = NOW(), version = version +1 where pid = ?", billId);
    }

    private void removeTakingInfo(String billId) {
        simpleDao.executeSql("delete from hos_taking_stock where bill_id=?", billId);
    }

/*
    private void writeHosTakingStock(String id) throws Exception {
        OutStock outStock = simpleDao.get(OutStock.class, id);
        List<OutStockList> outStockLists = simpleDao.queryByFieldName(OutStockList.class, "pid", id);

        for (OutStockList outStockSub : outStockLists) {
            HosTakingStock hosTakingStock = new HosTakingStock();
            hosTakingStock.setId(utilsContext.getSysAtomUtil().newValue("hos_taking_stock_id"));
            hosTakingStock.setHosId(outStock.getOutOrgId());
            hosTakingStock.setStocId(outStock.getOutStocId());
            hosTakingStock.setHosGoodsId(outStockSub.getGoodsId());
            hosTakingStock.setKind(outStock.getOutStockKind());
            hosTakingStock.setBillId(outStock.getBillId());
            hosTakingStock.setQty(outStockSub.getOutQty());
            hosTakingStock.setBillRownum(outStockSub.getOutBillRow());
            hosTakingStock.setVersion(0);
            simpleDao.insert(hosTakingStock);
        }
    }
*/

    /**
     * 回写请购单状态
     *
     * @param id       出库单主表ID
     * @param isRemove true，出库单撤销或其它原因，请购单子表已发送数量减少；false，出库后请购单子表的已发送数量增加
     * @throws Exception simpleDao Exception
     */
    // TODO: 未考虑出库目标科室是否为请购科室
    private void writeDeptBuySub(String id, boolean isRemove) throws Exception {
        List<OutStockList> outStockLists = simpleDao.queryByFieldName(OutStockList.class, "pid", id);

        for (OutStockList outStockSub : outStockLists) {
            DeptBuySub deptBuySub = simpleDao.get(DeptBuySub.class, outStockSub.getSourceBillId());
            BigDecimal sum = deptBuySub.getSendQty() == null ? new BigDecimal(0.0) : deptBuySub.getSendQty();
            sum = isRemove ? sum.subtract(outStockSub.getOutQty()) : sum.add(outStockSub.getOutQty());
            if (sum.compareTo(deptBuySub.getQty()) > 0) {
                throw new ParameterException("出库数量不能大于请购数量！", -1);
            } else if (sum.doubleValue() == 0) {
                deptBuySub.setSubState(gyqx.spdherp.applyMgr.Constance.DEPTAPPLAY_SUB_STATUS_PENDING); // TODO: 回写10是否正确，未考虑30不采购状态
            } else if (sum.compareTo(deptBuySub.getQty()) == 0) {
                deptBuySub.setSubState(gyqx.spdherp.applyMgr.Constance.DEPTAPPLAY_SUB_STATUS_ALLDISTRED); // 全部配送
            } else {
                deptBuySub.setSubState(gyqx.spdherp.applyMgr.Constance.DEPTAPPLAY_SUB_STATUS_PARTDISTRED); // 部分配送
            }
            deptBuySub.setSendQty(sum);
            if (simpleDao.updateNotNullFields(deptBuySub) <= 0) {
                throw new ParameterException("更新状态失败");
            }
//            deptBuySub.setVersion(deptBuySub.getVersion() + 1);
//            deptBuySubService.updateBill4OutStock(deptBuySub);
        }
    }

    @Data
    @AllArgsConstructor
    public class IdVersion {
        private String id;
        private Integer version;
    }
}
