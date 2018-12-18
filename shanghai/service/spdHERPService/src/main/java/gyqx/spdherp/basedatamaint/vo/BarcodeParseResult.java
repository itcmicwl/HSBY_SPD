package gyqx.spdherp.basedatamaint.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther Liangwu
 * @Date 17-7-14 下午3:05
 */
public class BarcodeParseResult {
    // -----------------------------------------
    // 主条码
    private String masterBarcode;
    // 副条码
    private String slaverBarcode;
    // 对应采购目录商品ID
    private String goodsId;
    // 对应采购目录或产品列表商品名称
    private String goodsName;
    // 对应产品列表商品规格
    private String goodsSpec;
    // 数量
    private Integer qty;
    // 价格
    private BigDecimal price;
    // 条码解析的批号
    private String batchNo;
    // 批次
    private String batchId;
    // 采购模式
    private String purMode;
    // 是否唯一码管理
    private String uniqueCodeStrategy;
    // 条码解析的追踪码
    private String uniqueCode;
    // 条码解析的有效期
    private Date expiredDate;
    // 灭菌批号
    private String sterilizationCode;
    // 灭菌效期
    private Date sterilizationEndDate;
    // 灭菌时间
    private Date sterilizationDate;
    // -----------------------------------------

    // 状态
    private Integer status;

    // 条码长
    private Integer barcodeLen;
    // 前导码
    private String barleader;
    // 特征码1
    private String sign1;
    // 特征码2
    private String sign2;
    // 是否主码
    private String mainFlag;
    // 条码解析的耗材码
    private String goodsNo;

    // 前端传入供应商ID
    private String provId;
    // 前端传入医院ID
    private String hosId;

    public String getMasterBarcode() {
        return masterBarcode;
    }

    public void setMasterBarcode(String masterBarcode) {
        this.masterBarcode = masterBarcode;
    }

    public Integer getBarcodeLen() {
        return barcodeLen;
    }

    public void setBarcodeLen(Integer barcodeLen) {
        this.barcodeLen = barcodeLen;
    }

    public String getBarleader() {
        return barleader;
    }

    public void setBarleader(String barleader) {
        this.barleader = barleader;
    }

    public String getSign1() {
        return sign1;
    }

    public void setSign1(String sign1) {
        this.sign1 = sign1;
    }

    public String getSign2() {
        return sign2;
    }

    public void setSign2(String sign2) {
        this.sign2 = sign2;
    }

    public String getMainFlag() {
        return mainFlag;
    }

    public void setMainFlag(String mainFlag) {
        this.mainFlag = mainFlag;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getSterilizationCode() {
        return sterilizationCode;
    }

    public void setSterilizationCode(String sterilizationCode) {
        this.sterilizationCode = sterilizationCode;
    }

    public Date getSterilizationEndDate() {
        return sterilizationEndDate;
    }

    public void setSterilizationEndDate(Date sterilizationEndDate) {
        this.sterilizationEndDate = sterilizationEndDate;
    }

    public Date getSterilizationDate() {
        return sterilizationDate;
    }

    public void setSterilizationDate(Date sterilizationDate) {
        this.sterilizationDate = sterilizationDate;
    }

    public String getUniqueCodeStrategy() {
        return uniqueCodeStrategy;
    }

    public void setUniqueCodeStrategy(String uniqueCodeStrategy) {
        this.uniqueCodeStrategy = uniqueCodeStrategy;
    }

    public String getPurMode() {
        return purMode;
    }

    public void setPurMode(String purMode) {
        this.purMode = purMode;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getSlaverBarcode() {
        return slaverBarcode;
    }

    public void setSlaverBarcode(String slaverBarcode) {
        this.slaverBarcode = slaverBarcode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BarcodeParseResult that = (BarcodeParseResult) o;

        return new EqualsBuilder()
                .append(goodsId, that.goodsId)
                .append(goodsNo, that.goodsNo)
                .append(batchNo, that.batchNo)
                .append(uniqueCode, that.uniqueCode)
                .append(expiredDate, that.expiredDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(goodsId)
                .append(goodsNo)
                .append(batchNo)
                .append(uniqueCode)
                .append(expiredDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "BarcodeParseResult{" +
                "masterBarcode='" + masterBarcode + '\'' +
                ", slaverBarcode='" + slaverBarcode + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsSpec='" + goodsSpec + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", batchNo='" + batchNo + '\'' +
                ", batchId='" + batchId + '\'' +
                ", purMode='" + purMode + '\'' +
                ", uniqueCodeStrategy='" + uniqueCodeStrategy + '\'' +
                ", uniqueCode='" + uniqueCode + '\'' +
                ", expiredDate=" + expiredDate +
                ", sterilizationCode='" + sterilizationCode + '\'' +
                ", sterilizationEndDate=" + sterilizationEndDate +
                ", sterilizationDate=" + sterilizationDate +
                ", barcodeLen=" + barcodeLen +
                ", barleader='" + barleader + '\'' +
                ", sign1='" + sign1 + '\'' +
                ", sign2='" + sign2 + '\'' +
                ", mainFlag='" + mainFlag + '\'' +
                ", goodsNo='" + goodsNo + '\'' +
                ", provId='" + provId + '\'' +
                ", hosId='" + hosId + '\'' +
                '}';
    }
}