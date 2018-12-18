package gyqx.spdherp.applyMgr.vo;

import gyqx.spdherp.po.SurgeryPkgDef;
import gyqx.spdherp.po.SurgeryPkgDefList;

import java.util.List;

public class DeptCarSurGoodsStVo {

    private String hosId;
    private String surId;
    private String surName;
    private String needSurPkgQty;
    private List<SurGoods4carVo> surDefGoodsLst;

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getSurId() {
        return surId;
    }

    public void setSurId(String surId) {
        this.surId = surId;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getNeedSurPkgQty() {
        return needSurPkgQty;
    }

    public void setNeedSurPkgQty(String needSurPkgQty) {
        this.needSurPkgQty = needSurPkgQty;
    }

    public List<SurGoods4carVo> getSurDefGoodsLst() {
        return surDefGoodsLst;
    }

    public void setSurDefGoodsLst(List<SurGoods4carVo> surDefGoodsLst) {
        this.surDefGoodsLst = surDefGoodsLst;
    }
}
