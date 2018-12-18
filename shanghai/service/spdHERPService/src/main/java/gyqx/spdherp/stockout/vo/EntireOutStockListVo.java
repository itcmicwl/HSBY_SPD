
package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockList;
import gyqx.spdherp.po.OutStockUniqueCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntireOutStockListVo extends OutStockList {
    private List<OutStockUniqueCode> outStockUniqueCodeList = new ArrayList<>();
    private List<OutStockBatch> outStockBatchList = new ArrayList<>();
}
