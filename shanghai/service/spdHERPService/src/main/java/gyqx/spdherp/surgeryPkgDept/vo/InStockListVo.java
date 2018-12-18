package gyqx.spdherp.surgeryPkgDept.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gyqx.spdherp.po.InStockBatch;
import gyqx.spdherp.po.InStockList;
import gyqx.spdherp.po.InStockUniqueCode;
import gyqx.spdherp.stockMgr.vo.InStockBatchVo;
import gyqx.spdherp.stockMgr.vo.InStockUniqueCodeVo;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InStockListVo extends InStockList {
    private List<InStockUniqueCodeVo> lstUniqueCode;
    private List<InStockBatchVo> lstBatch;
}
