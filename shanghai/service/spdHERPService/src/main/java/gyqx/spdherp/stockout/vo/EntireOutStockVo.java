package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.po.OutStock;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class EntireOutStockVo extends OutStock {
    private List<EntireOutStockListVo> entireOutStockListVoList = new ArrayList<>();
}
