package gyqx.spdherp.surgeryPkgDept.service.impl;

import common.db.SimpleDao;
import common.exception.ParameterException;
import common.transform.Tx;
import common.utils.UtilsContext;
import gyqx.spdherp.po.*;
import gyqx.spdherp.stockout.dao.RequestOutDao;
import gyqx.spdherp.surgery.constant.Constants;
import gyqx.spdherp.surgeryPkgDept.dao.SurgeryDeptDao;
import gyqx.spdherp.surgeryPkgDept.service.ISurgeryDeptService;
import gyqx.spdherp.surgeryPkgDept.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
@Service
public class SurgeryDeptServiceImpl implements ISurgeryDeptService {
    @Resource
    private UtilsContext utilsContext;
    @Resource
    private SurgeryDeptDao dao;
    @Resource
    private RequestOutDao requestOutDao;
    @Resource
    private SimpleDao simpleDao;

    @Override
    public List<SurgeryPkgVo> getPrintSurInfo(SurgeryPkgVo qBean) throws Exception {
        //打印界面将手术包主表修改为领用登记//改个毛线，明明是查询科室出库的手术包
        //qBean.setStatus(SURGERY_PKG_DEPT_OUT);
        qBean.setStatus(Constants.SUR_PKG_STATUS_KSCK);
        List<SurgeryPkgVo> surgeryBatchList = dao.getSurgeryBatchList(qBean);
        surgeryBatchList.forEach(o->{
            qBean.setSurCode(o.getSurCode());
            List<SurgeryPkgListVo> surgeryUnique = dao.getSurgeryUnique(qBean);
            surgeryUnique.forEach(u->u.setQty(BigDecimal.ONE));
            o.getPkgListDis().addAll(surgeryUnique);
        });
        return surgeryBatchList;
    }

    @Override
    public SurgeryPkgVo getSurgeryInfoByCode(SurgeryPkgVo qBean) throws Exception {
        SurgeryPkgVo surgeryPkgVo = new SurgeryPkgVo();
        SurgeryPkgVo surgeryInfoByCode = dao.getSurgeryInfoByCode(qBean);
        if (surgeryInfoByCode == null) {
            return surgeryPkgVo;
        }
        BeanUtils.copyProperties(surgeryInfoByCode, surgeryPkgVo);
        SurgeryPkgVo surgeryBatch = dao.getSurgeryBatch(qBean);
        List<SurgeryPkgListVo> pkgListDis = dao.getSurgeryUnique(qBean);
        pkgListDis.forEach(o -> o.setQty(BigDecimal.ONE));
        surgeryBatch.getPkgListDis().addAll(pkgListDis);
        surgeryPkgVo.setPkgListDis(surgeryBatch.getPkgListDis());
        return surgeryPkgVo;
    }



    @Override
    public SurgeryVo convertOutBill(SurgeryVo surgeryVo) throws Exception {
        OutStock outStock = new OutStock();
        outStock = transformOutStock(surgeryVo, outStock);
        BeanUtils.copyProperties(outStock, surgeryVo);
        List<OutStockListVo> outStockLists = new ArrayList<>();
        List<OutStockBatch> outStockBatches = new ArrayList<>();
        List<OutStockUniqueCode> outStockUniqueCodeList = new ArrayList<>();
        List<SurgeryPkgVo> surgeryPkgVoList = surgeryVo.getSurgeryPkgVoList();
        List<SurgeryInfo> surgeryInfoList = new ArrayList<>();
        transforSurgeryInfo(surgeryPkgVoList, surgeryInfoList);
        List<SurgeryInfo> uniqueList = surgeryInfoList.stream().filter(o -> o.getUniqueKind() == 1).collect(Collectors.toList());
        List<SurgeryInfo> batchList = surgeryInfoList.stream().filter(o -> o.getUniqueKind() != 1).collect(Collectors.toList());
//      按高低值分组
        Map<Integer, List<SurgeryInfo>> isUniqueListMap = surgeryInfoList.stream().collect(Collectors.groupingBy(SurgeryInfo::getUniqueKind));
        int rowNum = 0;
        for (Map.Entry<Integer, List<SurgeryInfo>> ent : isUniqueListMap.entrySet()
                ) {
            if (ent.getKey() != 3) {
//              按产品id、批号、手术包号分组
                Map<String, List<SurgeryInfo>> listMap =
                        uniqueList.stream().collect(Collectors.groupingBy(spi -> spi.getGoodsId() + spi.getBatchCode() + spi.getSurCode()));
                for (Map.Entry<String, List<SurgeryInfo>> entry : listMap.entrySet()
                        ) {
                    int j = rowNum + 1;
                    List<SurgeryInfo> outlist = entry.getValue();
                    OutStockListVo outStockList = getOutStockListVo(j, outlist);
                    //确认手术包出库的isUnique
                    outStockList.setIsUnique("1");
                    outStockList.setOutQty(outlist.stream().map(SurgeryInfo::getQty).reduce(BigDecimal.ZERO, BigDecimal::add));
                    outStockList.setSum(outlist.stream().map(SurgeryInfo::getQty).reduce(BigDecimal.ZERO, BigDecimal::add));
                    outStockList.setSumPrice(outlist.stream().map(SurgeryInfo::getInPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
                    for (SurgeryInfo sgi : outlist) {
                        OutStockUniqueCode outStockUniqueCode = getOutStockUniqueCode(j, sgi);
                        outStockUniqueCodeList.add(outStockUniqueCode);
                    }
                    outStockList.setOutStockUniqueCodeList(outStockUniqueCodeList);
                    outStockLists.add(outStockList);
                    rowNum++;
                }
            } else {
                Map<String, List<SurgeryInfo>> listMap =
                        batchList.stream().collect(Collectors.groupingBy(spi -> spi.getGoodsId() + spi.getBatchCode() + spi.getSurCode()));
                for (Map.Entry<String, List<SurgeryInfo>> entry : listMap.entrySet()
                        ) {
                    int j = rowNum + 1;
                    List<SurgeryInfo> outlist = entry.getValue();
                    OutStockListVo outStockList = getOutStockListVo(j, outlist);
                    outStockList.setIsUnique("3");
                    outStockList.setOutQty(outlist.stream().map(SurgeryInfo::getQty).reduce(BigDecimal.ZERO, BigDecimal::add));
                    outStockList.setSum(outlist.stream().map(SurgeryInfo::getQty).reduce(BigDecimal.ZERO, BigDecimal::add));
                    outStockList.setSumPrice(outlist.stream().map(o -> o.getQty().multiply(o.getInPrice())).reduce(BigDecimal.ZERO, BigDecimal::add));
                    ArrayList<SurgeryInfo> infos = outlist.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(SurgeryInfo::getBatchId))), ArrayList::new));
                    for (SurgeryInfo sgi : infos
                            ) {
                        OutStockBatch outStockBatch = getOutStockBatch(j, outlist, sgi);
                        outStockBatches.add(outStockBatch);
                    }
                    outStockList.setOutStockBatchList(outStockBatches);
                    outStockLists.add(outStockList);
                    rowNum++;
                }
            }
        }
        surgeryVo.setTotalSum(outStockLists.stream().map(OutStockListVo::getSum).reduce(BigDecimal.ZERO, BigDecimal::add));
        surgeryVo.setTotalPrice(outStockLists.stream().map(OutStockListVo::getSumPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        surgeryVo.setOutStockLists(outStockLists);
        surgeryVo.setOutStockBatches(outStockBatches);
        surgeryVo.setOutStockUniqueCodeList(outStockUniqueCodeList);
        return surgeryVo;
    }


    @Override
    public String submitOutBill(SurgeryVo surgeryVo) throws Exception {
        OutStock outStock = new OutStock();
        Tx.transform(surgeryVo).to(outStock);
        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_id");
        outStock.setId(id);
        outStock.setBillId(id);
        simpleDao.insert(outStock);
        List<OutStockList> outStockLists = new ArrayList<>();
        List<OutStockBatch> outStockBatches = surgeryVo.getOutStockBatches();
        List<OutStockUniqueCode> outStockUniqueCodes = surgeryVo.getOutStockUniqueCodeList();
        surgeryVo.getOutStockLists().forEach(o -> {
            OutStockList outStockList = new OutStockList();
            BeanUtils.copyProperties(o, outStockList);
            outStockLists.add(outStockList);
            String pid = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                    utilsContext.getSysAtomUtil().newValue("out_stock_list_id");
            outStockList.setId(pid);
            outStockList.setPid(outStock.getBillId());
            outStockList.setBillId(outStock.getBillId());
            if (outStockBatches != null && outStockBatches.size() > 0) {
                outStockBatches.forEach(op -> {
                    if (op.getpRowId() == o.getOutBillRow()) {
                        String bid = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                                utilsContext.getSysAtomUtil().newValue("out_stock_batch_id");
                        op.setId(bid);
                        op.setPid(pid);
                        op.setBillId(outStock.getBillId());
                    }
                });
            }
            if (outStockUniqueCodes != null && outStockUniqueCodes.size() > 0) {
                outStockUniqueCodes.forEach(ou -> {
                    if (ou.getPRowId() == o.getOutBillRow()) {
                        String idStockUniqueCode = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                                utilsContext.getSysAtomUtil().newValue("out_stock_unique_code_id");
                        ou.setId(ou.getUniqueCode());
                        ou.setPid(pid);
                        ou.setBillId(outStock.getBillId());
                    }
                });
            }
        });
        List<HosTakingStock> hosTakingStocks = new ArrayList<>();
        if (requestOutDao.insertOutStockBill(outStockLists, outStockBatches, outStockUniqueCodes, hosTakingStocks) < 0) {
            throw new ParameterException("写数据库错误。");
        }
        /*回写打包表主表状态*/
        for (SurgeryPkgVo o : surgeryVo.getSurgeryPkgVoList()) {
            int i = dao.updateStatus(o);
            if (i != 1) {
                throw new ParameterException("回写打包表主表状态失败!");
            }
            PkgLog pkgLog = new PkgLog();
            pkgLog.setBillId(outStock.getBillId());
            pkgLog.setId(UUID.randomUUID().toString().replace("-", ""));
            pkgLog.setCode(o.getSurCode());
            //手术包类型 0:手术包 1:定数包
            pkgLog.setPackageKind(0);
            pkgLog.setDescription("手术包登记领用");
            pkgLog.setFiller(utilsContext.getUserStateUtils().getCurrent().getEname());
            pkgLog.setFillDate(new Date());
            pkgLog.setLastUpdateDatetime(new Date());
            pkgLog.setVersion(0);
            simpleDao.insert(pkgLog);
        }
        return "ok";
    }

    public OutStock transformOutStock(SurgeryVo surgeryVo, OutStock outStock) throws Exception {
        Tx.transform(surgeryVo).to(outStock);
        outStock.setVersion(0);
        outStock.setStatus(10);
        outStock.setFiller(utilsContext.getUserStateUtils().getCurrent().getEname());
        outStock.setFillDate(new Date());
        return outStock;
    }

    /**
     * 遍历surgeryVo得到所有的产品信息
     *
     * @param surgeryPkgVoList
     * @param surgeryInfoList
     * @throws Exception
     */
    public void transforSurgeryInfo(List<SurgeryPkgVo> surgeryPkgVoList, List<SurgeryInfo> surgeryInfoList) throws Exception {
        for (SurgeryPkgVo spv : surgeryPkgVoList
                ) {
            for (SurgeryPkgListVo splv : spv.getPkgList()
                    ) {
                SurgeryInfo surgeryInfo = new SurgeryInfo();
                Tx.transform(spv).to(surgeryInfo);
                surgeryInfo.setGoodsId(splv.getGoodsId());
                surgeryInfo.setUniqueKind(splv.getUniqueKind());
                surgeryInfo.setSubStatus(splv.getStatus());
                surgeryInfo.setSubQty(splv.getQty());
                surgeryInfo.setSubUnit(splv.getUnit());
                surgeryInfo.setShouldSterilize(splv.getShouldSterilize());
                surgeryInfo.setFirstSterilizeDate(splv.getFirstSterilizeDate());
                surgeryInfo.setFirstSterilizer(splv.getFirstSterilizer());
                surgeryInfo.setSterilizeExpdate(splv.getSterilizeExpdate());
                surgeryInfo.setMade(splv.getMade());
                surgeryInfo.setMfrsId(splv.getMfrsId());
                surgeryInfo.setMfrsName(splv.getMfrsName());
                surgeryInfo.setProvName(splv.getProvName());
                surgeryInfo.setProvId(splv.getProvId());
                surgeryInfo.setProvName(splv.getProvName());
                surgeryInfo.setGoodsGg(splv.getGoodsGg());
                surgeryInfo.setGoodsName(splv.getGoodsName());
                surgeryInfo.setCertificateCode(splv.getCertificateCode());
                for (SurgeryPkgBatchVo spbv : splv.getPkgBatchList()
                        ) {
                    SurgeryInfo surgeryInfoBatch = new SurgeryInfo();
                    BeanUtils.copyProperties(surgeryInfo, surgeryInfoBatch);
                    surgeryInfoBatch.setGoodsId(spbv.getGoodsId());
                    surgeryInfoBatch.setBatchId(spbv.getBatchId());
                    surgeryInfoBatch.setBatchCode(spbv.getBatchCode());
                    surgeryInfoBatch.setBigBatchCode(spbv.getBigBatchCode());
                    surgeryInfoBatch.setUniqueCode(spbv.getUniqueCode());
                    surgeryInfoBatch.setQty(spbv.getQty());
                    surgeryInfoBatch.setExpdtEndDate(spbv.getExpdtEndDate());
                    surgeryInfoBatch.setSterilizationEndDate(spbv.getSterilizationEndDate());
                    surgeryInfoBatch.setSterilizationDate(spbv.getSterilizationDate());
                    surgeryInfoBatch.setSterilizationCode(spbv.getSterilizationCode());
                    surgeryInfoBatch.setInPrice(spbv.getInPrice());
                    surgeryInfoList.add(surgeryInfoBatch);
                }
            }
        }
    }

    public OutStockBatch getOutStockBatch(int j, List<SurgeryInfo> outlist, SurgeryInfo sgi) {
        OutStockBatch outStockBatch = new OutStockBatch();
        outStockBatch.setPRowId(j);
        outStockBatch.setProvId(sgi.getProvId());
        outStockBatch.setGoodsId(sgi.getGoodsId());
        outStockBatch.setGoodsBatchId(sgi.getBatchId());
        outStockBatch.setInPrice(sgi.getInPrice());
        outStockBatch.setQty(outlist.stream().filter(o -> o.getBatchId().equals(sgi.getBatchId())).map(SurgeryInfo::getQty).reduce(BigDecimal.ZERO, BigDecimal::add));
        outStockBatch.setBigBatchCode(sgi.getBigBatchCode());
        outStockBatch.setVersion(0);
        return outStockBatch;
    }

    public OutStockUniqueCode getOutStockUniqueCode(int j, SurgeryInfo sgi) {
        OutStockUniqueCode outStockUniqueCode = new OutStockUniqueCode();
        outStockUniqueCode.setPRowId(j);
        outStockUniqueCode.setProvId(sgi.getProvId());
        outStockUniqueCode.setGoodsId(sgi.getGoodsId());
        outStockUniqueCode.setGoodsBatchId(sgi.getBatchId());
        outStockUniqueCode.setInPrice(sgi.getInPrice());
        outStockUniqueCode.setQty(BigDecimal.valueOf(1));
        outStockUniqueCode.setUniqueCode(sgi.getUniqueCode());
        outStockUniqueCode.setVersion(0);
        return outStockUniqueCode;
    }

    public OutStockListVo getOutStockListVo(int j, List<SurgeryInfo> outlist) {
        OutStockListVo outStockList = new OutStockListVo();
        outStockList.setSourceBillId(outlist.get(0).getSurCode());
        outStockList.setOutBillRow(j);
        outStockList.setProvId(outlist.get(0).getProvId());
        outStockList.setProvCode(outlist.get(0).getProvId());
        outStockList.setProvName(outlist.get(0).getProvName());
        outStockList.setGoodsId(outlist.get(0).getGoodsId());
        outStockList.setGoodsName(outlist.get(0).getGoodsName());
        outStockList.setGoodsGg(outlist.get(0).getGoodsGg());
        outStockList.setMfrsId(outlist.get(0).getMfrsId());
        outStockList.setMfrsName(outlist.get(0).getMfrsName());
        outStockList.setMade(outlist.get(0).getMade());
        outStockList.setBatchCode(outlist.get(0).getBatchCode());
        outStockList.setUnit(outlist.get(0).getSubUnit());
        outStockList.setSterilizationDate(outlist.get(0).getSterilizationDate());
        outStockList.setSterilizationCode(outlist.get(0).getSterilizationCode());
        outStockList.setSterilizationEndDate(outlist.get(0).getSterilizationEndDate());
        outStockList.setExpdtEndDate(outlist.get(0).getExpdtEndDate());
        outStockList.setStatus(0);
        outStockList.setVersion(0);
        outStockList.setCertificateCode(outlist.get(0).getCertificateCode());
        return outStockList;
    }
}
