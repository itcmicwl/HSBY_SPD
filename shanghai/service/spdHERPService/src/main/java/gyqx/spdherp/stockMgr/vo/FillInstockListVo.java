package gyqx.spdherp.stockMgr.vo;

import gyqx.spdherp.po.InStockList;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class FillInstockListVo extends InStockList {
   private List<FillInstockBatchVo> lstBatch = new ArrayList<>();
}
