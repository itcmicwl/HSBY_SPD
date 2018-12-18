package gyqx.spdherp.spdOutPtl.vo;

import gyqx.spdherp.po.DeptBuySub;

import java.io.Serializable;

/**
 * Created by cjzyw on 2018/6/26.
 */
public class DeptBuyForSpdPtl extends DeptBuySub implements Serializable{

    private String erpCode;

    private String goodsGg;

    private String goodsName;

    private String mfrsId;

    private String mfrsName;

    private String unit;

    public String getErpCode() {
        return erpCode;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    public String getGoodsGg() {
        return goodsGg;
    }

    public void setGoodsGg(String goodsGg) {
        this.goodsGg = goodsGg;
    }

    public String getMfrsName() {
        return mfrsName;
    }

    public void setMfrsName(String mfrsName) {
        this.mfrsName = mfrsName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMfrsId() {
        return mfrsId;
    }

    public void setMfrsId(String mfrsId) {
        this.mfrsId = mfrsId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
