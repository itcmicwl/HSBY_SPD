package gyqx.spdherp.surgery.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.SurgeryPkgDefList;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SurgeryPkgDefListVo extends SurgeryPkgDefList implements Serializable {

    // 手术包名称
    private String surName;
    private List<String> selSurIds;
    private String goodsName;
    private HosGoodsInfoVo hosGoods;
    private boolean hasUse;
    private String deptId;
    private boolean canQGByDept;
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public HosGoodsInfoVo getHosGoods() {
        return hosGoods;
    }

    public void setHosGoods(HosGoodsInfoVo hosGoods) {
        this.hosGoods = hosGoods;
    }

    public List<String> getSelSurIds() {
        return selSurIds;
    }

    public void setSelSurIds(List<String> selSurIds) {
        this.selSurIds = selSurIds;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public boolean isHasUse() {
        return hasUse;
    }

    public void setHasUse(boolean hasUse) {
        this.hasUse = hasUse;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public boolean isCanQGByDept() {
        return canQGByDept;
    }

    public void setCanQGByDept(boolean canQGByDept) {
        this.canQGByDept = canQGByDept;
    }
}
