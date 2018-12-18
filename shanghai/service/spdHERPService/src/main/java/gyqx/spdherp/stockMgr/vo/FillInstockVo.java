package gyqx.spdherp.stockMgr.vo;

import gyqx.spdherp.po.InStock;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class FillInstockVo extends InStock {
    List<FillInstockListVo> lstDetail = new ArrayList<>();
}
