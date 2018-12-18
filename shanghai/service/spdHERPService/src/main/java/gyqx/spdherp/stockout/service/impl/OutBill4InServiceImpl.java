package gyqx.spdherp.stockout.service.impl;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import gyqx.spdherp.barcodePrintingCenter.service.IHosPackageInfoService;
import gyqx.spdherp.barcodePrintingCenter.vo.DsGoodsVo;
import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.stockMgr.Constance;
import gyqx.spdherp.stockMgr.vo.InStockBatchVo;
import gyqx.spdherp.stockMgr.vo.InStockListVo;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.dao.OutBill4InDao;
import gyqx.spdherp.stockout.service.BillMgrService;
import gyqx.spdherp.stockout.service.OutBill4InService;
import gyqx.spdherp.stockout.vo.Out4UseVo;
import gyqx.spdherp.stockout.vo.OutStockBatchVo;
import gyqx.spdherp.stockout.vo.vo4In.Out4Settle;
import gyqx.spdherp.stockout.vo.vo4In.OutBill4InVo;
import gyqx.spdherp.stockout.vo.vo4In.OutSub4InVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutBill4InServiceImpl implements OutBill4InService {
    @Resource
    private SimpleDao simpleDao;
    @Resource
    private OutBill4InDao o4iDao;
    @Resource
    private BillMgrService billMgrService;

    @Resource
    private IHosPackageInfoService iHosPackageInfoService;
    public OutBill4InVo getOutBillDetails(String id, String isPacket) { //id 为billId
        // o4iDao.getOutBill(billId);
        OutBill4InVo main = o4iDao.getOutBill(id);
        List<OutSub4InVo> subList = o4iDao.getSubs(id, isPacket);
        for (OutSub4InVo sub : subList) {
/*		  //查询批次
		  sub.setLstOutBatch(o4iDao.getBatchsByPid(id, sub.getId()));
		  //查询唯一码
		  sub.setLstOutUniqueCode(o4iDao.getUniqueCodesByPid(id, sub.getId()));
*/
            if (sub.getIsUnique().equals("3")) {
                sub.setLstOutBatch(o4iDao.getBatchsByPid(id, sub.getId()));
                sub.setLstOutUniqueCode(Collections.EMPTY_LIST);

                BigDecimal useQty = sub.getLstOutBatch().stream().map(OutStockBatch::getUseQty).reduce(BigDecimal.ZERO,BigDecimal::add);
                sub.setUsedQty(useQty);

            } else {
                sub.setLstOutUniqueCode(o4iDao.getUniqueCodesByPid(id, sub.getId(),sub.getOutBillRow()));
                long count = sub.getLstOutUniqueCode().stream().filter(o->o.getIsUsed() != null && o.getIsUsed().equals(1)).count();
                sub.setUsedQty(new BigDecimal(count));
                List<OutStockBatch> lstOutBatch = new ArrayList<>();
                sub.getLstOutUniqueCode()
                        .stream()
                        .collect(Collectors.groupingBy(OutStockUniqueCode::getGoodsBatchId, Collectors.counting()))
                        .forEach((key, value) -> sub.getLstOutUniqueCode()
                                .stream()
                                .filter(item -> item.getGoodsBatchId().equals(key))
                                .findFirst()
                                .ifPresent(outStockUniqueCode -> lstOutBatch.add(transUniqueToBatch(value, outStockUniqueCode))));
                sub.setLstOutBatch(lstOutBatch);
                sub.setOutQty(new BigDecimal(sub.getLstOutUniqueCode().size()));
            }

        }
        main.setLstOutsubVo(subList);
        return main;
    }

    private OutStockBatch transUniqueToBatch(Long qty, OutStockUniqueCode outStockUniqueCode) {
        OutStockBatch outStockBatch = new OutStockBatch();
        outStockBatch.setId(outStockUniqueCode.getId() + "fake");
        outStockBatch.setPid(outStockUniqueCode.getPid());
        outStockBatch.setBillId(outStockUniqueCode.getBillId());
        outStockBatch.setPRowId(outStockUniqueCode.getPRowId());
        outStockBatch.setProvId(outStockUniqueCode.getProvId());
        outStockBatch.setGoodsId(outStockUniqueCode.getGoodsId());
        outStockBatch.setGoodsBatchId(outStockUniqueCode.getGoodsBatchId());
        outStockBatch.setInPrice(outStockUniqueCode.getInPrice());
        outStockBatch.setInTime(outStockUniqueCode.getInTime());
        outStockBatch.setQty(new BigDecimal(qty));
        outStockBatch.setVersion(outStockUniqueCode.getVersion());
        outStockBatch.setLastUpdateDatetime(outStockUniqueCode.getLastUpdateDatetime());
        return outStockBatch;
    }

    public QueryResult<OutBill4InVo> getBillList4Instock(QueryInfo<OutBill4InVo> queryInfo) {
        PageUtils.startPage(queryInfo);
        return PageUtils.endPage(o4iDao.list4Instock(queryInfo.getQueryObject()));
    }

    public int updateOutBillStatus(String billId, int status) {
        return simpleDao.executeSql("update  out_stock set status=? where bill_id=? ", status, billId);
    }

    private void updateBatchUseQty(InStockVo instock){
        for (InStockListVo inStockListVo: instock.getLstDetail()) {
            if(inStockListVo.getLstBatch() != null && inStockListVo.getLstBatch().size() > 0){
                for (InStockBatchVo inStockBatchVo: inStockListVo.getLstBatch()) {
                    o4iDao.updateBatchUseQty(inStockBatchVo.getQty(), instock.getOutBillId(), inStockListVo.getGoodsId(), inStockListVo.getBatchCode(), inStockBatchVo.getGoodsBatchId());
                }
            }
        }
    }

    public void updateHosPackageInStockStatus(List<String> barcodeList) {
        if(barcodeList == null || barcodeList.size() == 0){
            return;
        }
        for (String barcode:barcodeList) {
            simpleDao.executeSql("update  hos_package_info set status= 1 where package_id=? and (status = 0 or status is null)", barcode);
        }
    }

    /**
     * 获取请购出库单主表状态
     * @param instock
     * @return
     */
    private Integer getOutMainStatus(InStockVo instock){
        // 默认状态全部入库
        Integer inStatus = gyqx.spdherp.stockout.Constance.OUTSTOCKBILL_STATUS_INSTOCK;

        // 判断本次所选商品是不是全部入库
        boolean isPartIn = instock.getLstDetail().stream().anyMatch(o -> o.getOutQty().subtract(o.getUsedQty()== null ? new BigDecimal(0) : o.getUsedQty()).compareTo(o.getInQty() == null ? new BigDecimal(0) : o.getInQty()) > 0);
        if (isPartIn) {
            return gyqx.spdherp.stockout.Constance.OUTSTOCKBILL_STATUS_PART_INSTOCK;
        }

        // 判断未选择商品是否全部入库
        List<OutSub4InVo> subList = o4iDao.getSubs(instock.getOutBillId(), null);
        for (OutSub4InVo outSub4InVo: subList) {
            // 是否为本次入库的商品
            boolean hasExists = false;
            for (InStockListVo inStockListVo : instock.getLstDetail()){
                if(outSub4InVo.getGoodsId().equals(inStockListVo.getGoodsId()) && outSub4InVo.getBatchCode().equals(inStockListVo.getBatchCode())){
                    hasExists = true;
                    break;
                }
            }

            if(hasExists){
                continue;
            } else {
                // 获取非本次入库非唯一码商品的消耗数量
                if (outSub4InVo.getIsUnique().equals("3")) {
                    outSub4InVo.setLstOutBatch(o4iDao.getBatchsByPid(instock.getOutBillId(), outSub4InVo.getId()));

                    BigDecimal useQty = outSub4InVo.getLstOutBatch().stream().map(OutStockBatch::getUseQty).reduce(BigDecimal.ZERO, BigDecimal::add);
                    outSub4InVo.setUsedQty(useQty);
                }

                if(outSub4InVo.getUsedQty() == null){
                    outSub4InVo.setUsedQty(new BigDecimal(0));
                }
                if(outSub4InVo.getOutQty().compareTo(outSub4InVo.getUsedQty()) > 0){
                    return gyqx.spdherp.stockout.Constance.OUTSTOCKBILL_STATUS_PART_INSTOCK;
                }
            }
        }

        return inStatus;
    }

    /**
     * 实购请购入库回写状态
     * @param instock
     */
    private void updateOutBillStatusByTrueInstock(InStockVo instock){



        // 回写请购出库批次表消耗数量
        updateBatchUseQty(instock);

        // 获取请购出库单主表状态
        Integer inStatus = getOutMainStatus(instock);

        // 请购出库单状态回写
        updateOutBillStatus(instock.getOutBillId(),inStatus);
    }

    @Override
    public int updateOutBillStatusByInstock(InStockVo instock) throws Exception {
        //update by wjm 2018/10/24 不论是否实物还是虚拟，都要更新出库单唯一码表，批次表，定数包表。以防后面实物有高值，虚拟有低值情况
        int res = 0;
        List<OutStockUniqueCode> lstU4Update = new ArrayList<>();
        List<OutStockUniqueCode> lst4OutCode = o4iDao.getUniqueCodes(instock.getOutBillId());
        List<String> lstUniqueCodes = new ArrayList<>();
        instock.getLstDetail().stream().map(o -> lstUniqueCodes
                .addAll(o.getLstUniqueCode().stream().map(code -> code.getUniqueCode()).collect(Collectors.toList())))
                .collect(Collectors.toList());
        for (OutStockUniqueCode outStockUniqueCode : lst4OutCode) {
            if (lstUniqueCodes.contains(outStockUniqueCode.getUniqueCode())) {
                outStockUniqueCode.setIsUsed(1);
                lstU4Update.add(outStockUniqueCode);
            }
        }
        if(lstU4Update.size()>0){   //add by wjm 2018/10/23 手术包低值虚拟入库无唯一码
            res = o4iDao.updateUniqueCodesUsed(lstU4Update);
        }
        this.setPackageStatus(instock);
        this.setOutBatchQty(instock);
        billMgrService.updateOutStockStatus(instock.getOutBillId());
        return res;
    }
    private void setPackageStatus(InStockVo instock){
        // 是否有定数包管理商品
        boolean hasPacket = instock.getLstDetail().stream().anyMatch(o -> o.getIsPacket().equals("1"));
        if(hasPacket) {
            // 判断整单验收还是扫码验收
            List<String> packageCodeList = instock.getBarcodeList();
            // 整单验收
            if(packageCodeList == null || packageCodeList.size() ==0){
                packageCodeList = iHosPackageInfoService.listPackageCodeByBillId(instock.getOutBillId());
            }
            // 回写定数包状态
            updateHosPackageInStockStatus(packageCodeList);
        }
    }
    private void setOutBatchQty(InStockVo instock){
        List<OutStockBatchVo> lst4Update = new ArrayList<>();
        instock.getLstDetail().forEach(item->{
            item.getLstBatch().forEach(o->{
                OutStockBatchVo osbv = new OutStockBatchVo();
                osbv.setBigBatchCode(o.getBigBatchCode());
                osbv.setBillId(item.getOutBillId());
                osbv.setpRowId(item.getOutBillRow());
                osbv.setUseQty(o.getQty());
                lst4Update.add(osbv) ;
            });
        });
        o4iDao.setOutBatchQty(lst4Update);
    }
    @Override
    public QueryResult<Out4Settle> outBill4settle(QueryInfo<Out4Settle> queryInfo) throws Exception {
        PageUtils.startPage(queryInfo);
        return PageUtils.endPage(o4iDao.outBill4settle(queryInfo.getQueryObject()));
    }

    @Override
    public Out4UseVo outBill4Use(QueryInfo<Out4UseVo> queryInfo) {
        return o4iDao.outBill4Use(queryInfo.getQueryObject());
    }
    @Override
    public QueryResult<DsGoodsVo>  getDSGoodsByOutBill(QueryInfo<DsGoodsVo> queryInfo) throws Exception{
        PageUtils.startPage(queryInfo);
        return PageUtils.endPage(o4iDao.getDSGoodsByOutBill(queryInfo.getQueryObject()));
    }

}
