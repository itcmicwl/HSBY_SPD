package gyqx.spdherp.surgeryPkgDept.vo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockList;
import gyqx.spdherp.po.OutStockUniqueCode;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurgeryVo extends OutStock{
    private java.math.BigDecimal totalSum;
    private java.math.BigDecimal totalPrice ;
    private List<SurgeryPkgVo> surgeryPkgVoList;
    private List<OutStockListVo> outStockLists;
    private List<OutStockBatch> outStockBatches;
    private List<OutStockUniqueCode> outStockUniqueCodeList;
}
