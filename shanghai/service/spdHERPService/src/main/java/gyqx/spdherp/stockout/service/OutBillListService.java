package gyqx.spdherp.stockout.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.stockout.vo.EnameValVo;
import gyqx.spdherp.stockout.vo.OutBillListVo;

import java.util.List;

public interface OutBillListService {
    List<EnameValVo> getOutStatus() throws Exception;
    QueryResult<OutBillListVo> listByPage(QueryInfo<OutBillListVo> queryInfo) throws Exception;
    OutBillListVo getByBillId(OutBillListVo outBillListVo) throws Exception;

}
