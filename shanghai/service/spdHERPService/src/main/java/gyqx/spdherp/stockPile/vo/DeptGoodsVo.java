package gyqx.spdherp.stockPile.vo;

import gyqx.spdherp.po.DeptGoodsInfo;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class DeptGoodsVo extends DeptGoodsInfo {
    private BigDecimal stocQty;
    private BigDecimal purQty;
    private BigDecimal needQty;
    private String provId;
    private String subProvId;
    private BigDecimal purPrice;
    private BigDecimal purTax;
    private float hosScale;
    private String goodsProvId;
}
