package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.po.SurgeryPkgList;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SurgeryPkg4UseListVo extends SurgeryPkgList {


    private String surId;
    private String batchCode;
    private String goodsName;
    private BigDecimal useQty;

    private String billId;
    private Integer outBillRow;
    private String goodsId;
    private String goodsGg;
    private String unit;
    private String sterilizationCode;
    private Date expdtEndDate;
    private Date sterilizationDate;
    private Date sterilizationEndDate;
    private BigDecimal qty;
    
    private String outOrgId;
    private String outDeptId;
    private String outStocId;
    private String filler;
    private String masterCode;
    private String secCode;
    private String hibcCode;
    private String epcCode;             //暂无
    private String selfCode;            //暂无
    private String provId;
    private String outStockType;
    private int purMode;
    
    private BigDecimal consumedQty; //已经消耗数
 
    public String getSurId() {
        return surId;
    }

    public void setSurId(String surId) {
        this.surId = surId;
    }
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getOutBillRow() {
        return outBillRow;
    }

    public void setOutBillRow(Integer outBillRow) {
        this.outBillRow = outBillRow;
    }

    @Override
    public String getGoodsId() {
        return goodsId;
    }

    @Override
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSterilizationCode() {
        return sterilizationCode;
    }

    public void setSterilizationCode(String sterilizationCode) {
        this.sterilizationCode = sterilizationCode;
    }

    public Date getExpdtEndDate() {
        return expdtEndDate;
    }

    public void setExpdtEndDate(Date expdtEndDate) {
        this.expdtEndDate = expdtEndDate;
    }

    public Date getSterilizationDate() {
        return sterilizationDate;
    }

    public void setSterilizationDate(Date sterilizationDate) {
        this.sterilizationDate = sterilizationDate;
    }

    public Date getSterilizationEndDate() {
        return sterilizationEndDate;
    }

    public void setSterilizationEndDate(Date sterilizationEndDate) {
        this.sterilizationEndDate = sterilizationEndDate;
    }

    @Override
    public BigDecimal getQty() {
        return qty;
    }

    @Override
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }



    public String getOutOrgId() {
        return outOrgId;
    }

    public void setOutOrgId(String outOrgId) {
        this.outOrgId = outOrgId;
    }

    public String getOutDeptId() {
        return outDeptId;
    }

    public void setOutDeptId(String outDeptId) {
        this.outDeptId = outDeptId;
    }

    public String getOutStocId() {
        return outStocId;
    }

    public void setOutStocId(String outStocId) {
        this.outStocId = outStocId;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getHibcCode() {
        return hibcCode;
    }

    public void setHibcCode(String hibcCode) {
        this.hibcCode = hibcCode;
    }

    public String getEpcCode() {
        return epcCode;
    }

    public void setEpcCode(String epcCode) {
        this.epcCode = epcCode;
    }

    public String getSelfCode() {
        return selfCode;
    }

    public void setSelfCode(String selfCode) {
        this.selfCode = selfCode;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getOutStockType() {
        return outStockType;
    }

    public void setOutStockType(String outStockType) {
        this.outStockType = outStockType;
    }

    public int getPurMode() {
        return purMode;
    }

    public void setPurMode(int purMode) {
        this.purMode = purMode;
    }

    public BigDecimal getUseQty() {
        return useQty;
    }

    public void setUseQty(BigDecimal useQty) {
        this.useQty = useQty;
    }

    public String getGoodsGg() {
        return goodsGg;
    }

    public void setGoodsGg(String goodsGg) {
        this.goodsGg = goodsGg;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    private List<SurgeryPkg4UseBatchVo> surgeryPkg4UseBatchVo;


    public List<SurgeryPkg4UseBatchVo> getSurgeryPkg4UseBatchVo() {
        return surgeryPkg4UseBatchVo;
    }

    public void setSurgeryPkg4UseBatchVo(List<SurgeryPkg4UseBatchVo> surgeryPkg4UseBatchVo) {
        this.surgeryPkg4UseBatchVo = surgeryPkg4UseBatchVo;
    }
    
    public BigDecimal getConsumedQty() {
        return consumedQty;
    }
    
    public void setConsumedQty(BigDecimal consumedQty) {
        this.consumedQty = consumedQty;
    }
}
