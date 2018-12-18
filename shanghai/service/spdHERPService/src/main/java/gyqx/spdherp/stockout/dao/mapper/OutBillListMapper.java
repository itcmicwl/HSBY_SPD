package gyqx.spdherp.stockout.dao.mapper;

import gyqx.spdherp.stockout.vo.*;
import java.util.List;

public interface OutBillListMapper {
    List<EnameValVo> getOutStatus();
    List<OutBillListVo> getOutBillList(OutBillListVo outBillListVo);
}
