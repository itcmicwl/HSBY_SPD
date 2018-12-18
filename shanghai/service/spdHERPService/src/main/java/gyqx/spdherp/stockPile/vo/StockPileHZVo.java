package gyqx.spdherp.stockPile.vo;

import java.math.BigDecimal;

/**
 * Created by zouliang on 2017-8-22.
 */
public class StockPileHZVo {
    private String hosId;
    private String stocId;
    private String hosGoodsId;
    private String goodsName;
    private String code;
    private String goodsGg;
    private String unit;
    private java.math.BigDecimal qty;
    private java.math.BigDecimal price;
    private java.math.BigDecimal je;
    private java.math.BigDecimal lockqty;

    public BigDecimal getLockqty() {
        return lockqty;
    }

    public void setLockqty(BigDecimal lockqty) {
        this.lockqty = lockqty;
    }

    private String mfrsId;
    private String mfrsName;
    private String provId;
    private String provName;
    private String lbsx;

    public String getLbsx() {
        return lbsx;
    }

    public void setLbsx(String lbsx) {
        this.lbsx = lbsx;
    }

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getStocId() {
        return stocId;
    }

    public void setStocId(String stocId) {
        this.stocId = stocId;
    }

    public String getHosGoodsId() {
        return hosGoodsId;
    }

    public void setHosGoodsId(String hosGoodsId) {
        this.hosGoodsId = hosGoodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGoodsGg() {
        return goodsGg;
    }

    public void setGoodsGg(String goodsGg) {
        this.goodsGg = goodsGg;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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
        this.mfrsId = mfrsId;
    }

    public String getMfrsName() {
        return mfrsName;
    }

    public void setMfrsName(String mfrsName) {
        this.mfrsName = mfrsName;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }
}
