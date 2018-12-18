package gyqx.spdherp.surgery.vo;

import gyqx.spdherp.stockPile.vo.HosStockPileVo;
import lombok.Data;

import java.util.List;
import java.util.Set;
@Data
public class SurBill4Pack {
    private SurgeryPkgVo surBill;
    private List<HosStockPileVo> lstStocInfo;
    private Set<String> goodsIds;
}
