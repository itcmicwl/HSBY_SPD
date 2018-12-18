package gyqx.spdherp.applyMgr.vo;

import gyqx.spdherp.po.SurgeryPkgDef;
import gyqx.spdherp.po.SurgeryPkgDefList;

import java.io.Serializable;
import java.util.List;

public class SurDef4applyBillVo implements Serializable
{
    private SurgeryPkgDef surDef;
    private Integer qty;
    private List<DeptBuySubVo> surDefSubBillList;
    private List<SurgeryPkgDefList> surDefGoodsList;

    public SurgeryPkgDef getSurDef() {
        return surDef;
    }

    public void setSurDef(SurgeryPkgDef surDef) {
        this.surDef = surDef;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public List<DeptBuySubVo> getSurDefSubBillList() {
        return surDefSubBillList;
    }

    public void setSurDefSubBillList(List<DeptBuySubVo> surDefSubBillList) {
        this.surDefSubBillList = surDefSubBillList;
    }

    public List<SurgeryPkgDefList> getSurDefGoodsList() {
        return surDefGoodsList;
    }

    public void setSurDefGoodsList(List<SurgeryPkgDefList> surDefGoodsList) {
        this.surDefGoodsList = surDefGoodsList;
    }

    @Override
    public String toString() {
        return "SurDef4applyBillVo{" +
                "surDef=" + surDef +
                ", qty=" + qty +
                ", surDefSubBillList=" + surDefSubBillList +
                ", surDefGoodsList=" + surDefGoodsList +
                '}';
    }
}
