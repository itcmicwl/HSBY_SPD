package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.*;

/**
 * Created by moonb on 2017/11/22.
 */
@XmlType(propOrder = { "rowNum", "purType", "goodsCode", "goodsDetailCode", "goodsHosId", "goodsGG", "deliverRemark"
        , "purQty", "purPrice", "provCode", "isFast", "deliverRequire", "isDeliverMany", "shelfInfo", "remark"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY111_REQ_DETAIL {
    @XmlElement(name = "SXH")
    private Integer rowNum;
    @XmlElement(name = "CGLX")
    private Integer purType;
    @XmlElement(name = "HCTBDM")
    private String goodsCode;
    @XmlElement(name = "HCXFDM")
    private String goodsDetailCode;
    @XmlElement(name = "YYBDDM")
    private String goodsHosId;
    @XmlElement(name = "CGGGXH")
    private String goodsGG;
    @XmlElement(name = "PSSM")
    private String deliverRemark;
    @XmlElement(name = "CGSL")
    private String purQty;
    @XmlElement(name = "CGDJ")
    private String purPrice;
    @XmlElement(name = "QYBM")
    private String provCode;
    @XmlElement(name = "SFJJ")
    private String isFast;
    @XmlElement(name = "PSYQ")
    private Integer deliverRequire;
    @XmlElement(name = "DCPSBS")
    private String isDeliverMany;
    @XmlElement(name = "CWXX")
    private String shelfInfo;
    @XmlElement(name = "BZSM")
    private String remark;

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getPurType() {
        return purType;
    }

    public void setPurType(Integer purType) {
        this.purType = purType;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsDetailCode() {
        return goodsDetailCode;
    }

    public void setGoodsDetailCode(String goodsDetailCode) {
        this.goodsDetailCode = goodsDetailCode;
    }

    public String getGoodsHosId() {
        return goodsHosId;
    }

    public void setGoodsHosId(String goodsHosId) {
        this.goodsHosId = goodsHosId;
    }

    public String getGoodsGG() {
        return goodsGG;
    }

    public void setGoodsGG(String goodsGG) {
        this.goodsGG = goodsGG;
    }

    public String getDeliverRemark() {
        return deliverRemark;
    }

    public void setDeliverRemark(String deliverRemark) {
        this.deliverRemark = deliverRemark;
    }

    public String getPurQty() {
        return purQty;
    }

    public void setPurQty(String purQty) {
        this.purQty = purQty;
    }

    public String getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(String purPrice) {
        this.purPrice = purPrice;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }

    public String getIsFast() {
        return isFast;
    }

    public void setIsFast(String isFast) {
        this.isFast = isFast;
    }

    public Integer getDeliverRequire() {
        return deliverRequire;
    }

    public void setDeliverRequire(Integer deliverRequire) {
        this.deliverRequire = deliverRequire;
    }

    public String getIsDeliverMany() {
        return isDeliverMany;
    }

    public void setIsDeliverMany(String isDeliverMany) {
        this.isDeliverMany = isDeliverMany;
    }

    public String getShelfInfo() {
        return shelfInfo;
    }

    public void setShelfInfo(String shelfInfo) {
        this.shelfInfo = shelfInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
