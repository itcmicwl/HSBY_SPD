package gyqx.spdherp.stockPile.vo;

import gyqx.spdherp.po.HosStockpile;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class HosStockPileVo extends HosStockpile {
    private BigDecimal takeQty;
    private Set<String> goodsIds;
}