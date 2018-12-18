package gyqx.spdherp.stockout.service.impl;

import common.db.SimpleDao;
import common.db.query.QueryResult;
import common.exception.ParameterException;
import common.transform.Tx;
import common.utils.PageUtils;
import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import gyqx.spdherp.basedatamaint.controller.BarcodeParseController;
import gyqx.spdherp.basedatamaint.service.BarcodeParseService;
import gyqx.spdherp.basedatamaint.vo.BarcodeParseResult;
import gyqx.spdherp.po.*;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.dao.BillMgrDao;
import gyqx.spdherp.stockout.dao.PackageOutDao;
import gyqx.spdherp.stockout.dao.RequestOutDao;
import gyqx.spdherp.stockout.dao.ReturnOutDao;
import gyqx.spdherp.stockout.service.PackageOutService;
import gyqx.spdherp.stockout.service.ReturnOutService;
import gyqx.spdherp.stockout.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static gyqx.spdherp.basedatamaint.controller.BarcodeParseController.PACKAGE_PREFIX;
import static gyqx.spdherp.basedatamaint.controller.BarcodeParseController.UNIQUE_PREFIX;
import static gyqx.spdherp.stockout.Constance.OUTSTOCKBILL_STATUS_CHECKED;
import static gyqx.spdherp.stockout.Constance.OUTSTOCKBILL_STATUS_SUBMIT;
import static gyqx.spdherp.stockout.Constance.OUTSTOCK_KIND_PCKAGE;

@Service
public class PackageOutServiceImpl implements PackageOutService {
    private static Logger logger = LoggerFactory.getLogger(PackageOutServiceImpl.class);
    @Resource
    private PackageOutDao dao;

    @Resource
    private UtilsContext utilsContext;

    @Resource
    private RequestOutDao requestOutDao;

    @Resource
    private BarcodeParseService barcodeParseService;

    @Resource
    private SimpleDao simpleDao;

    @Resource
    private BillMgrDao billMgrDao;

    @Override
    public QueryResult<StockpileHzVo> listStockGoods(String stockId, Integer stocKind, String filter, int pageNum, int pageSize) {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        PageUtils.startPage(pageNum, pageSize);
        return PageUtils.endPage(dao.listStockGoods(hosId, stockId, stocKind, filter));
    }

    @Override
    public String addPkg(PackageWhole packageWhole) throws Exception {
        OutStock outStock = new OutStock();
        Tx.transform(packageWhole).to(outStock);
        outStock = updateOutStockInfo(outStock);
        simpleDao.insert(outStock);
        List<OutStockList> outStockLists = new ArrayList<>();
        List<OutStockBatch> outStockBatches = new ArrayList<>();
        List<OutStockUniqueCode> outStockUniqueCodeList = new ArrayList<>();
        List<HosTakingStock> hosTakingStocks = new ArrayList<>();
        List<HosPackageInfoVo> packageInfos = packageWhole.getPackageInfos();
        List<PackageOutBillVo> packageOutBillVos = new ArrayList<>();
        for (HosPackageInfoVo pg : packageInfos) {
            for (HosPackageBatchDetailVo res : pg.getHosPackageBatchDetailLst()) {
                PackageOutBillVo packageOutBillVo = new PackageOutBillVo();
                Tx.transform(pg).to(packageOutBillVo);
                packageOutBillVo.setProwId(res.getRowId());
                packageOutBillVo.setGoodsBatchId(res.getGoodsBatchId());
                packageOutBillVo.setInPrice(res.getInPrice());
                packageOutBillVo.setInTime(res.getInTime());
                packageOutBillVo.setDetailQty(res.getQty());
                packageOutBillVo.setBigBatchCode(res.getBigBatchCode());
                packageOutBillVos.add(packageOutBillVo);
            }
        }
        Map<String, Map<String, List<PackageOutBillVo>>> groupBy =
                packageOutBillVos.stream().collect(Collectors.groupingBy(PackageOutBillVo::getGoodsId, Collectors.groupingBy(PackageOutBillVo::getBatchCode)));
        int j = 0;
        for (Map.Entry<String, Map<String, List<PackageOutBillVo>>> entry : groupBy.entrySet()) {
            String key = entry.getKey();
            Map<String, List<PackageOutBillVo>> value = entry.getValue();
//            按批号进行分组
            for (Map.Entry<String, List<PackageOutBillVo>> entry1 : value.entrySet()) {
                int i = j + 1;
                OutStockList outStockList = new OutStockList();
                String key1 = entry1.getKey();
//              每一个批号对应的对象
                List<PackageOutBillVo> value1 = entry1.getValue();
                double sumQty = 0;
                for (PackageOutBillVo packageVo : value1) {
                    sumQty += packageVo.getQty().doubleValue();
                }
                String id = getOutStockList1(outStock, outStockLists, i, outStockList, value1, sumQty);
                List<OutStockBatch> outStockBatches1 = new ArrayList<>();

                for (PackageOutBillVo packageVo : value1) {
                    if (outStockBatches1.size() > 0) {
                        List<OutStockBatch> temp = new ArrayList<>();
                        for (OutStockBatch osb : outStockBatches1) {
                            if (packageVo.getGoodsBatchId().equals(osb.getGoodsBatchId())) {
                                temp.add(osb);
                            }
                        }
                        if (temp.size() > 0) {
                            for (OutStockBatch osb : outStockBatches1) {
                                if (packageVo.getGoodsBatchId().equals(osb.getGoodsBatchId())) {
                                    osb.setQty(osb.getQty().add(packageVo.getDetailQty()));
                                }
                            }
                        } else {
                            for (OutStockBatch osb : outStockBatches1) {
                                if (osb.getGoodsBatchId().equals(packageVo.getGoodsBatchId())) {
                                    osb.setQty(osb.getQty().add(packageVo.getDetailQty()));
                                    break;
                                } else {
                                    getOutStockBatch(outStock, i, id, outStockBatches1, packageVo);
                                    break;
                                }
                            }
                        }
                    } else {
                        getOutStockBatch(outStock, i, id, outStockBatches1, packageVo);
                    }
                }
                outStockBatches.addAll(outStockBatches1);
                outStockBatches1.clear();
                j++;
            }
        }
        if (requestOutDao.insertOutStockBill(outStockLists, outStockBatches, outStockUniqueCodeList, hosTakingStocks) < 0) {
            throw new ParameterException("写数据库错误。");
        }
        packageWhole.getPackageInfos().forEach(res -> {
            dao.writeStatusPkg(res.getPackageId());
        });
        //提交直接记账
        if (outStock.getStatus().equals(OUTSTOCKBILL_STATUS_CHECKED)
                && outStock.getOutStockKind().equals(OUTSTOCK_KIND_PCKAGE.toString())
                && !billMgrDao.stockpilebatchOutstock(outStock)) {
            throw new RuntimeException("记帐失败！");
        }
        return "ok";
    }

    public void getOutStockBatch(OutStock outStock, int i, String id, List<OutStockBatch> outStockBatches1, PackageOutBillVo packageVo) {
        OutStockBatch outStockBatch = new OutStockBatch();
        String bid = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_batch_id");
        outStockBatch.setId(bid);
        outStockBatch.setPid(id);
        outStockBatch.setBillId(outStock.getBillId());
        outStockBatch.setPRowId(i);
        outStockBatch.setProvId(packageVo.getProvId());
        outStockBatch.setGoodsId(packageVo.getGoodsId());
        outStockBatch.setGoodsBatchId(packageVo.getGoodsBatchId());
        outStockBatch.setInPrice(packageVo.getInPrice());
        outStockBatch.setQty(packageVo.getDetailQty());
        outStockBatch.setBigBatchCode(packageVo.getBigBatchCode());
        outStockBatch.setVersion(0);
        outStockBatches1.add(outStockBatch);
    }

    @Override
    public String saveOutStockBill(PackageWhole packageWhole) throws Exception {
        OutStock outStock = new OutStock();
        Tx.transform(packageWhole).to(outStock);
        outStock = updateOutStockInfo(outStock);
        //simpleDao.insert(updateOutStockInfo(outStock));
        List<OutStockList> outStockLists = new ArrayList<>();
        List<OutStockBatch> outStockBatches = new ArrayList<>();
        List<OutStockUniqueCode> outStockUniqueCodeList = new ArrayList<>();
        List<HosTakingStock> hosTakingStocks = new ArrayList<>();
        List<HosPackageInfoVo> packageInfos = packageWhole.getPackageInfos();
        Map<String, Map<String, List<HosPackageInfoVo>>> groupBy =
                packageInfos.stream().collect(Collectors.groupingBy(HosPackageInfoVo::getGoodsId, Collectors.groupingBy(HosPackageInfoVo::getBatchCode)));
        int j = 0;
        for (Map.Entry<String, Map<String, List<HosPackageInfoVo>>> entry : groupBy.entrySet()) {
            String key = entry.getKey();
            Map<String, List<HosPackageInfoVo>> value = entry.getValue();
//            按批号进行分组
            for (Map.Entry<String, List<HosPackageInfoVo>> entry1 : value.entrySet()) {
                int i = j + 1;
                OutStockList outStockList = new OutStockList();
                String key1 = entry1.getKey();
//              每一个批号对应的对象
                List<HosPackageInfoVo> value1 = entry1.getValue();
                double sumQty = 0;
                for (HosPackageInfoVo packageVo : value1) {
                    sumQty += packageVo.getQty().doubleValue();
                }
                String id = getOutStockList(outStock, outStockLists, i, outStockList, value1, sumQty);
                for (HosPackageInfoVo packageVo : value1) {
                    for (OutStockList osl : outStockLists) {
                        if (osl.getBatchCode().equals(packageVo.getBatchCode())) {
                            for (HosPackageBatchDetailVo pkgDetail : packageVo.getHosPackageBatchDetailLst()) {
                                if (outStockBatches.size() > 0) {
                                    for (OutStockBatch osb : outStockBatches) {
                                        if (!osb.getGoodsBatchId().equals(pkgDetail.getGoodsBatchId())) {
                                            getOutBatch(outStock, outStockBatches, i, id, packageVo, pkgDetail);
                                            break;
                                        } else {
                                            osb.setQty(osb.getQty().add(pkgDetail.getQty()));
                                            break;
                                        }
                                    }
                                } else {
                                    getOutBatch(outStock, outStockBatches, i, id, packageVo, pkgDetail);
                                }
                            }
                            break;
                        } else {
                            for (HosPackageBatchDetailVo pkgDetail : packageVo.getHosPackageBatchDetailLst()) {
                                getOutBatch(outStock, outStockBatches, i, id, packageVo, pkgDetail);
                            }
                            break;
                        }
                    }
                }
                j++;
            }
        }
       /* if (requestOutDao.insertOutStockBill(outStockLists, outStockBatches, outStockUniqueCodeList, hosTakingStocks) < 0) {
            throw new ParameterException("写数据库错误。");
        }
        packageWhole.getPackageInfos().forEach(res -> {
            dao.writeStatusPkg(res.getPackageId());
        });
        //提交直接记账
        if (outStock.getStatus().equals(OUTSTOCKBILL_STATUS_CHECKED)
                && outStock.getOutStockKind().equals(OUTSTOCK_KIND_PCKAGE.toString())
                && !billMgrDao.stockpilebatchOutstock(outStock)) {
            throw new ParameterException("记帐失败！", -1);
        }*/
        return "ok";
    }

    public String getOutStockList(OutStock outStock, List<OutStockList> outStockLists, int i, OutStockList outStockList, List<HosPackageInfoVo> value1, double sumQty) {
        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_list_id");
        //String id = UUID.randomUUID().toString().replace("-","");
        outStockList.setId(id);
        outStockList.setSourceBillId(value1.get(0).getBillId());
        outStockList.setPid(outStock.getId());
        outStockList.setBillId(outStock.getBillId());
        outStockList.setOutBillRow(i);
        outStockList.setProvId(value1.get(0).getProvId());
        outStockList.setProvName(value1.get(0).getProvName());
        outStockList.setGoodsId(value1.get(0).getGoodsId());
        outStockList.setGoodsName(value1.get(0).getGoodsName());
        outStockList.setGoodsGg(value1.get(0).getGoodsGg());
        outStockList.setMfrsId(value1.get(0).getMfrsId());
        outStockList.setMfrsName(value1.get(0).getMfrsName());
        outStockList.setMade(value1.get(0).getMade());
        outStockList.setPacketCode(value1.get(0).getPacketCode());
        outStockList.setIsPacket("1");
        outStockList.setIsUnique("3");
        outStockList.setBatchCode(value1.get(0).getBatchCode());
        outStockList.setSterilizationDate(value1.get(0).getSterilizationDate());
        outStockList.setSterilizationCode(value1.get(0).getSterilizationCode());
        outStockList.setSterilizationEndDate(value1.get(0).getSterilizationEndDate());
        outStockList.setExpdtEndDate(value1.get(0).getExpdtEndDate());
        outStockList.setUnit(value1.get(0).getUnit());
        outStockList.setOutQty(BigDecimal.valueOf(sumQty));
        outStockList.setMasterCode(value1.get(0).getPackageId());
        outStockList.setStatus(0);
        outStockList.setVersion(0);
        //outStockList.setCertificateCode(value1.get(0).getCertificateCode());
        outStockLists.add(outStockList);
        return id;
    }

    public String getOutStockList1(OutStock outStock, List<OutStockList> outStockLists, int i, OutStockList outStockList, List<PackageOutBillVo> value1, double sumQty) throws Exception {
        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_list_id");
        outStockList.setId(id);
        outStockList.setSourceBillId(value1.get(0).getBillId());
        outStockList.setPid(outStock.getId());
        outStockList.setBillId(outStock.getBillId());
        outStockList.setOutBillRow(i);
        outStockList.setProvId(value1.get(0).getProvId());
        outStockList.setProvName(value1.get(0).getProvName());
        outStockList.setGoodsId(value1.get(0).getGoodsId());
        outStockList.setGoodsName(value1.get(0).getGoodsName());
        outStockList.setGoodsGg(value1.get(0).getGoodsGg());
        outStockList.setMfrsId(value1.get(0).getMfrsId());
        outStockList.setMfrsName(value1.get(0).getMfrsName());
        outStockList.setMade(value1.get(0).getMade());
        outStockList.setPacketCode(value1.get(0).getPacketCode());
        outStockList.setIsPacket("1");
        outStockList.setIsUnique("3");
        outStockList.setBatchCode(value1.get(0).getBatchCode());
        outStockList.setSterilizationDate(value1.get(0).getSterilizationDate());
        outStockList.setSterilizationCode(value1.get(0).getSterilizationCode());
        outStockList.setSterilizationEndDate(value1.get(0).getSterilizationEndDate());
        outStockList.setExpdtEndDate(value1.get(0).getExpdtEndDate());
        outStockList.setUnit(value1.get(0).getUnit());
        outStockList.setOutQty(BigDecimal.valueOf(sumQty));
        //outStockList.setMasterCode(value1.get(0).getPackageId());
        outStockList.setStatus(0);
        outStockList.setVersion(0);
        outStockList.setCertificateCode(value1.get(0).getCertificateCode());
        outStockLists.add(outStockList);
        return id;
    }

    public void getOutBatch(OutStock outStock, List<OutStockBatch> outStockBatches, int i, String id, HosPackageInfoVo packageVo, HosPackageBatchDetailVo pkgDetail) {
        OutStockBatch outStockBatch = new OutStockBatch();
        String bid = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_batch_id");
        //String bid = UUID.randomUUID().toString().replace("-","");
        outStockBatch.setId(bid);
        outStockBatch.setPid(id);
        outStockBatch.setBillId(outStock.getBillId());
        outStockBatch.setPRowId(i);
        outStockBatch.setProvId(packageVo.getProvId());
        outStockBatch.setGoodsId(packageVo.getGoodsId());
        outStockBatch.setGoodsBatchId(pkgDetail.getGoodsBatchId());
        outStockBatch.setInPrice(pkgDetail.getInPrice());
        outStockBatch.setQty(pkgDetail.getQty());
        outStockBatch.setBigBatchCode(pkgDetail.getBigBatchCode());
        outStockBatch.setVersion(0);
        outStockBatches.add(outStockBatch);
    }

    @Override
    public List<IdNameVo> listOrgs() {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        return dao.listOrgsByHosId(user.getCorpId(), user.getOrgId());
    }

    @Override
    public PackageWhole parseHosPackageInfo(PackageWhole packageWhole) throws Exception {
        return dao.getPkgResult(packageWhole);
    }

    private OutStock updateOutStockInfo(OutStock outStockBill) {
        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_id");
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        outStockBill.setId(id);
        outStockBill.setBillId(id);
        outStockBill.setFiller(user.getUserId());
        outStockBill.setFillDate(new Date());
        outStockBill.setOutOrgId(user.getCorpId());
        outStockBill.setOutOrgName(user.getCorpName());
        IdNameVo deptInfo = requestOutDao.getDeptInfoByStockId(user.getCorpId(), outStockBill.getOutStocId());
        outStockBill.setOutDeptId(deptInfo.getId());
        outStockBill.setOutDeptName(deptInfo.getName());
        outStockBill.setAccounter(utilsContext.getUserStateUtils().getCurrent().getUserId());
        outStockBill.setAccountDate(new Date());
        outStockBill.setStatus(20);
        outStockBill.setVersion(1);
        return outStockBill;
    }

}
