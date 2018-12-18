package gyqx.spdherp.stockout.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class StockpileHzVo {
    private String hosId;

    private String stocId;

    private String goodsId;

    private String goodsName;

    private String code;

    private String goodsGg;

    private String unit;

    private BigDecimal avlQty;

    private BigDecimal lockqty;

    private BigDecimal price;

    private BigDecimal je;

    private String mfrsId;

    private String mfrsName;

    private String provId;

    private String provName;

    private String isUnique;

    private String made;

    private String packetCode;

    private String isPacket;

    private String provCode;

    private BigDecimal qty;

    @JsonIgnore
    public String getHosId() {
        return hosId;
    }

    @JsonProperty
    public void setHosId(String hosId) {
        this.hosId = hosId == null ? null : hosId.trim();
    }

    @JsonIgnore
    public String getStocId() {
        return stocId;
    }

    @JsonProperty
    public void setStocId(String stocId) {
        this.stocId = stocId == null ? null : stocId.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    @JsonIgnore
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getGoodsGg() {
        return goodsGg;
    }

    public void setGoodsGg(String goodsGg) {
        this.goodsGg = goodsGg == null ? null : goodsGg.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getAvlQty() {
        return avlQty;
    }

    public void setAvlQty(BigDecimal avlQty) {
        this.avlQty = avlQty;
    }

    @JsonIgnore
    public BigDecimal getLockqty() {
        return lockqty;
    }

    public void setLockqty(BigDecimal lockqty) {
        this.lockqty = lockqty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @JsonIgnore
    public BigDecimal getJe() {
        return je;
    }

    public void setJe(BigDecimal je) {
        this.je = je;
    }

    public String getMfrsId() {
        return mfrsId;
    }

    public void setMfrsId(String mfrsId) {
        this.mfrsId = mfrsId == null ? null : mfrsId.trim();
    }

    public String getMfrsName() {
        return mfrsName;
    }

    public void setMfrsName(String mfrsName) {
        this.mfrsName = mfrsName == null ? null : mfrsName.trim();
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId == null ? null : provId.trim();
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName == null ? null : provName.trim();
    }

    public String getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(String isUnique) {
        this.isUnique = isUnique;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public String getPacketCode() {
        return packetCode;
    }

    public void setPacketCode(String packetCode) {
        this.packetCode = packetCode;
    }

    public String getIsPacket() {
        return isPacket;
    }

    public void setIsPacket(String isPacket) {
        this.isPacket = isPacket;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "StockpileHzVo{" +
                "hosId='" + hosId + '\'' +
                ", stocId='" + stocId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", code='" + code + '\'' +
                ", goodsGg='" + goodsGg + '\'' +
                ", unit='" + unit + '\'' +
                ", avlQty=" + avlQty +
                ", lockqty=" + lockqty +
                ", price=" + price +
                ", je=" + je +
                ", mfrsId='" + mfrsId + '\'' +
                ", mfrsName='" + mfrsName + '\'' +
                ", provId='" + provId + '\'' +
                ", provName='" + provName + '\'' +
                ", isUnique='" + isUnique + '\'' +
                ", made='" + made + '\'' +
                ", packetCode='" + packetCode + '\'' +
                ", isPacket='" + isPacket + '\'' +
                ", provCode='" + provCode + '\'' +
                ", qty=" + qty +
                '}';
    }
}