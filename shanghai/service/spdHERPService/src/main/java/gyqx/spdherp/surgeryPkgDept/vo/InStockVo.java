package gyqx.spdherp.surgeryPkgDept.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gyqx.spdherp.po.InStock;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InStockVo extends InStock{
    private String sourceBillId;
    private List<String> barcodeList;
    private List<InStockListVo> lstDetail;
}
