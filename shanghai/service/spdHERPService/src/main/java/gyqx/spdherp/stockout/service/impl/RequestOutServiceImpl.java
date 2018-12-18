package gyqx.spdherp.stockout.service.impl;

import common.db.SimpleDao;
import common.exception.ParameterException;
import common.transform.Tx;
import common.utils.DictionaryUtils;
import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import gyqx.spdherp.po.*;
import gyqx.spdherp.stockout.dao.RequestOutDao;
import gyqx.spdherp.stockout.service.RequestOutService;
import gyqx.spdherp.stockout.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static gyqx.spdherp.basedatamaint.controller.BarcodeParseController.PACKAGE_PREFIX;
import static gyqx.spdherp.stockout.Constance.OUTSTOCK_KIND_CONSUME;

/**
 * @Auther Liangwu
 * @Date 17-8-16 上午10:31
 */
@Service
public class RequestOutServiceImpl implements RequestOutService {
    @Resource
    private RequestOutDao requestOutDao;

    @Resource
    private SimpleDao simpleDao;

    @Resource
    private UtilsContext utilsContext;

    @Override
    public List<SysOrgVo> listSysOrg(String stockId) {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        return requestOutDao.listSysOrgByCorpId(user.getCorpId(), stockId, user.getOrgId(), user.getUserId());
    }

    @Override
    public List<IdNameVo> listOwnStock() {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        String uid = user.getUserId();
        String corpId = user.getCorpId();
        return requestOutDao.listOwnStock(corpId, uid);
    }

    @Override
    public List<DeptBuyMainVo> listDeptBuyRequest(Date beginDate, Date endDate, String billId, String deptId, String stockId) {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        if (StringUtils.hasText(billId)) {
            billId = billId.replaceAll("%", "\\\\%");
        }
        return requestOutDao.listDeptBuyRequest(beginDate, endDate, billId, deptId, stockId, user.getCorpId(), user.getUserId());
    }

    @Override
    public List<DictionaryUtils.DictionaryValue> listDictValue(String dict) {
        return utilsContext.getDictUtils().getDictItems(dict);
    }

    @Override
    @Transactional
    public String saveOutStockBill(OutStockBillVo outStockBillVo) throws Exception {
        OutStock outStockBill = new OutStock();

        Tx.transform(outStockBillVo).to(outStockBill);

        if (outStockBill.getOutStockKind().equals(OUTSTOCK_KIND_CONSUME.toString())) {
            // 科室出库直接将出库类型改为实采
            outStockBill.setOutStockType(10);
        }
        simpleDao.insert(updateOutStockBillInfo(outStockBill));

        List<OutStockList> outStockLists = new ArrayList<>();
        List<OutStockBatch> outStockBatches = new ArrayList<>();
        List<OutStockUniqueCode> outStockUniqueCodeList = new ArrayList<>();
        List<HosTakingStock> hosTakingStocks = new ArrayList<>();
        // 以商品ID，批号，效期，灭菌效期，注册证号，货位进行分组
        Map<RequestStockGoodsVo, List<RequestStockGoodsVo>> goodsMap = outStockBillVo.getGoodsList().stream().collect(Collectors.groupingBy(o -> o));
        int outBillRow = 0;
        for (Map.Entry<RequestStockGoodsVo, List<RequestStockGoodsVo>> entrySet : goodsMap.entrySet()) {
            RequestStockGoodsVo key = entrySet.getKey();
            List<HosStockpileVo> hosStockpileVos;
            if (key.getStockTableId().toLowerCase().startsWith(PACKAGE_PREFIX)) {
                hosStockpileVos = requestOutDao.listHosStockpileWithDs(key, outStockBill);
            } else {
                hosStockpileVos = requestOutDao.listHosStockpile(key.getStockTableId(), outStockBill.getOutOrgId(), outStockBill.getOutStocId(), key.getGoodsId(), key.getBatchNo(), key.getBatchId(), key.getUniqueCode(), outStockBillVo.getOutStockType(), !key.getIsUnique().equals("3"));
            }
            if (hosStockpileVos.size() == 0) {
                throw new ValidationException("库存未找到相关批号/批次/唯一码商品");
            }

            HosStockpileVo prevHosStockpile = null;
            OutStockList prevOutStockBillSub = null;
            Double subGoodsSum = 0.0;
            for (Iterator<RequestStockGoodsVo> it = entrySet.getValue().iterator(); it.hasNext(); ) {
                RequestStockGoodsVo requestStockGoodsVo = it.next();
                OutStockList outStockBillSub = new OutStockList();
                Tx.transform(requestStockGoodsVo).to(outStockBillSub);

                Optional<HosStockpileVo> hosStockpileVo = hosStockpileVos.stream().filter(o -> o.getId().equals(requestStockGoodsVo.getStockTableId())).findAny();
                if (!hosStockpileVo.isPresent()) {
                    throw new ValidationException("库存未找到相关批号/批次/唯一码商品");
                }

                if (prevHosStockpile == null) {
                    outStockBillSub = updateOutStockBillSubInfo(outStockBillSub, ++outBillRow, outStockBill, requestStockGoodsVo, hosStockpileVo.get());
                    prevOutStockBillSub = outStockBillSub;
                    prevHosStockpile = hosStockpileVo.get();
                } else if (!prevHosStockpile.equals(hosStockpileVo.get())) {
                    prevOutStockBillSub.setOutQty(new BigDecimal(subGoodsSum));
//                    simpleDao.insert(prevOutStockBillSub);
                    outStockLists.add(prevOutStockBillSub);
//                    simpleDao.insert(updateHosTakingStock(outStockBill, prevOutStockBillSub, prevHosStockpile.getId()));
//                    hosTakingStocks.add(updateHosTakingStock(outStockBill, prevOutStockBillSub, prevHosStockpile.getId()));
                    subGoodsSum = 0.0;
                    outStockBillSub = updateOutStockBillSubInfo(outStockBillSub, ++outBillRow, outStockBill, requestStockGoodsVo, hosStockpileVo.get());
                    prevOutStockBillSub = outStockBillSub;
                    prevHosStockpile = hosStockpileVo.get();
                } else {
                    outStockBillSub = prevOutStockBillSub;
                }

                if (requestStockGoodsVo.getIsUnique().equalsIgnoreCase("3")) { // 非唯一码管理
//                    simpleDao.insert(updateOutStockBatch(outStockBillSub, requestStockGoodsVo, hosStockpileVo.get().getBigBatchCode()));
                    outStockBatches.add(updateOutStockBatch(outStockBillSub, requestStockGoodsVo, hosStockpileVo.get().getBigBatchCode()));
                    subGoodsSum += requestStockGoodsVo.getQty();
                } else { // 唯一码管理
//                    simpleDao.insert(updateOutStockUniqueCode(outStockBillSub, requestStockGoodsVo));
                    outStockUniqueCodeList.add(updateOutStockUniqueCode(outStockBillSub, requestStockGoodsVo));
                    subGoodsSum += requestStockGoodsVo.getQty();
                }
                hosTakingStocks.add(updateHosTakingStock(outStockBill, outStockBillSub, requestStockGoodsVo));

                if (!it.hasNext()) {
                    prevOutStockBillSub.setOutQty(new BigDecimal(subGoodsSum));
//                    simpleDao.insert(prevOutStockBillSub);
                    outStockLists.add(prevOutStockBillSub);
//                    simpleDao.insert(updateHosTakingStock(outStockBill, prevOutStockBillSub, prevHosStockpile.getId()));
//                    hosTakingStocks.add(updateHosTakingStock(outStockBill, prevOutStockBillSub, prevHosStockpile.getId()));
                }
            }
        }
        if (requestOutDao.insertOutStockBill(outStockLists, outStockBatches, outStockUniqueCodeList, hosTakingStocks) < 0) {
            throw new ParameterException("写数据库错误。");
        }
        return "ok";
    }

    private OutStockUniqueCode updateOutStockUniqueCode(OutStockList outStockSub, RequestStockGoodsVo requestStockGoodsVo) {
        OutStockUniqueCode outStockUniqueCode = new OutStockUniqueCode();

        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_unique_code_id");
        outStockUniqueCode.setId(id);
        outStockUniqueCode.setPid(outStockSub.getId());
        outStockUniqueCode.setBillId(outStockSub.getBillId());
        outStockUniqueCode.setPRowId(outStockSub.getOutBillRow());
        outStockUniqueCode.setProvId(outStockSub.getProvId());
        outStockUniqueCode.setGoodsId(outStockSub.getGoodsId());
        outStockUniqueCode.setGoodsBatchId(requestStockGoodsVo.getBatchId());
        outStockUniqueCode.setInPrice(new BigDecimal(requestStockGoodsVo.getPrice()));
        outStockUniqueCode.setInTime(new Date());
        outStockUniqueCode.setUniqueCode(requestStockGoodsVo.getUniqueCode());
        outStockUniqueCode.setQty(BigDecimal.ONE);
        outStockUniqueCode.setVersion(0);

//        outStockUniqueCode.setLastUpdateDatetime(new Date());

        return outStockUniqueCode;
    }

    private OutStockBatch updateOutStockBatch(OutStockList outStockSub, RequestStockGoodsVo requestStockGoodsVo, String bigBatchCode) {
        OutStockBatch outStockBatch = new OutStockBatch();

        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_batch_id");
        outStockBatch.setId(id);
        outStockBatch.setPid(outStockSub.getId());
        outStockBatch.setBillId(outStockSub.getBillId());
        outStockBatch.setPRowId(outStockSub.getOutBillRow());
        outStockBatch.setProvId(outStockSub.getProvId());
        outStockBatch.setGoodsId(outStockSub.getGoodsId());
        outStockBatch.setGoodsBatchId(requestStockGoodsVo.getBatchId());
        outStockBatch.setBigBatchCode(bigBatchCode);
        outStockBatch.setInPrice(new BigDecimal(requestStockGoodsVo.getPrice() == null ? 0 : requestStockGoodsVo.getPrice()));
        outStockBatch.setInTime(new Date());
        outStockBatch.setQty(new BigDecimal(requestStockGoodsVo.getQty()));
        outStockBatch.setVersion(0);

//        outStockBatch.setLastUpdateDatetime(new Date());

        return outStockBatch;
    }

    @Override
    public List<GoodsNoIdUniqueVo> listUniqueCode(String stockId, String buyKind, List<String> goodsIdList) {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        return requestOutDao.listUniqueCode(hosId, stockId, buyKind, goodsIdList);
    }

    @Override
    public List<DeptBuySubVo> listDeptBuyRequestDetail(String billId, String deptId, String stockId) {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        return requestOutDao.listDeptBuyRequestDetail(hosId, billId, deptId, stockId);
    }

    private HosTakingStock updateHosTakingStock(OutStock outStockBill, OutStockList outStockSub, RequestStockGoodsVo requestStockGoodsVo) {
        HosTakingStock hosTakingStock = new HosTakingStock();
        hosTakingStock.setId(utilsContext.getSysAtomUtil().newValue("hos_taking_stock_id"));
        hosTakingStock.setHosId(outStockBill.getOutOrgId());
        hosTakingStock.setStocId(outStockBill.getOutStocId());
        hosTakingStock.setHosGoodsId(outStockSub.getGoodsId());
        hosTakingStock.setStockpileId(requestStockGoodsVo.getStockTableId());
        hosTakingStock.setKind(outStockBill.getOutStockKind());
        hosTakingStock.setBillId(outStockBill.getBillId());
        hosTakingStock.setQty(BigDecimal.valueOf(requestStockGoodsVo.getQty()));
        hosTakingStock.setBillRownum(outStockSub.getOutBillRow());
        hosTakingStock.setVersion(0);
//        hosTakingStock.setLastUpdateDatetime(new Date());
        return hosTakingStock;
    }

    private OutStockList updateOutStockBillSubInfo(OutStockList outStockSub, int outBillRow, OutStock outStockBill, RequestStockGoodsVo requestStockGoodsVo, HosStockpileVo hosStockpile) {
        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_list_id");
        outStockSub.setId(id);
        outStockSub.setSourceBillId(requestStockGoodsVo.getBsId());
        outStockSub.setPid(outStockBill.getId());
        outStockSub.setBillId(outStockBill.getBillId());
        outStockSub.setOutBillRow(outBillRow);
        outStockSub.setOutQty(new BigDecimal(requestStockGoodsVo.getQty()));
        outStockSub.setShelfId(hosStockpile.getShelfCode());

        if (StringUtils.hasText(requestStockGoodsVo.getBatchNo()))
            outStockSub.setBatchCode(requestStockGoodsVo.getBatchNo());
        if (StringUtils.hasText(requestStockGoodsVo.getCertificateCode()))
            outStockSub.setCertificateCode(requestStockGoodsVo.getCertificateCode());
        if (hosStockpile.getSterilizationDate() != null)
            outStockSub.setSterilizationDate(hosStockpile.getSterilizationDate());
        if (StringUtils.hasText(hosStockpile.getSterilizationCode()))
            outStockSub.setSterilizationCode(hosStockpile.getSterilizationCode());
        if (hosStockpile.getSterilizationEndDate() != null)
            outStockSub.setSterilizationEndDate(hosStockpile.getSterilizationEndDate());
        if (hosStockpile.getExpdtEndDate() != null)
            outStockSub.setExpdtEndDate(hosStockpile.getExpdtEndDate());
        if (StringUtils.hasText(hosStockpile.getBarcode()))
            outStockSub.setMasterCode(hosStockpile.getBarcode());
        if (StringUtils.hasText(hosStockpile.getSecCode()))
            outStockSub.setSecCode(hosStockpile.getSecCode());
        if (StringUtils.hasText(hosStockpile.getHibcCode()))
            outStockSub.setHibcCode(hosStockpile.getHibcCode());

        outStockSub.setStatus(0);
        outStockSub.setVersion(0);

//        outStockSub.setLastUpdateDatetime(new Date());

        return outStockSub;
    }

    private OutStock updateOutStockBillInfo(OutStock outStockBill) {
        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_id");
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        outStockBill.setId(id);
        outStockBill.setSourceBillId(outStockBill.getBillId());
        outStockBill.setBillId(id);
        outStockBill.setFiller(user.getUserId());
        outStockBill.setFillDate(new Date());
        outStockBill.setOutOrgId(user.getCorpId());
        outStockBill.setOutOrgName(user.getCorpName());
        IdNameVo deptInfo = requestOutDao.getDeptInfoByStockId(user.getCorpId(), outStockBill.getOutStocId());
        outStockBill.setOutDeptId(deptInfo.getId());
        outStockBill.setOutDeptName(deptInfo.getName());
        outStockBill.setInOrgId(user.getCorpId());
        outStockBill.setInOrgName(user.getCorpName());
        outStockBill.setVersion(0);
        outStockBill.setLastUpdateDatetime(new Date());
        return outStockBill;
    }
}
