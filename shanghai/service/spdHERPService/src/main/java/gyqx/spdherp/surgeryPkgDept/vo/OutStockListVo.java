package gyqx.spdherp.surgeryPkgDept.vo;

import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockList;
import gyqx.spdherp.po.OutStockUniqueCode;
import lombok.Data;

import java.util.List;
@Data
public class OutStockListVo extends OutStockList{
    private java.math.BigDecimal sum;
    private java.math.BigDecimal sumPrice ;
    private List<OutStockBatch> outStockBatchList;
    private List<OutStockUniqueCode> outStockUniqueCodeList;
}
