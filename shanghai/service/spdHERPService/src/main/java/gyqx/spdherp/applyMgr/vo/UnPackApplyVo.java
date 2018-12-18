package gyqx.spdherp.applyMgr.vo;

import gyqx.spdherp.po.ApplySurgeryPkg;
import gyqx.spdherp.stockPile.vo.HosStockPileVo;

import java.util.List;

public class UnPackApplyVo {
    private DeptBuyBillVo main;
    private List<HosStockPileVo> lstGoodsStore;
    private List<ApplySurgeryPkgVo> lstPkg;

    public DeptBuyBillVo getMain() {
        return main;
    }

    public void setMain(DeptBuyBillVo main) {
        this.main = main;
    }

    public List<HosStockPileVo> getLstDetail() {
        return lstGoodsStore;
    }

    public void setLstDetail(List<HosStockPileVo> lstDetail) {
        this.lstGoodsStore = lstDetail;
    }

    public List<ApplySurgeryPkgVo> getLstPkg() {
        return lstPkg;
    }

    public void setLstPkg(List<ApplySurgeryPkgVo> lstPkg) {
        this.lstPkg = lstPkg;
    }
}
