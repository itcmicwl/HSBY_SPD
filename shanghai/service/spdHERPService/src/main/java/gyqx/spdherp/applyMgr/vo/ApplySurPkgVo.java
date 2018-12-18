package gyqx.spdherp.applyMgr.vo;

import gyqx.spdherp.stockPile.vo.HosStockPileVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgApplyVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ApplySurPkgVo extends DeptBuyBillVo{
    private List<SurgeryPkgApplyVo> lstSurPkgDef;
    private List<SurgeryPkgVo> lstSurPkg;
    private List<HosStockPileVo> lstStocInfo;
    private Set<String> goodsIds;
}
