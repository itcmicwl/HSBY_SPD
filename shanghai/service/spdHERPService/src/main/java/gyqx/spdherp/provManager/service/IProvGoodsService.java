package gyqx.spdherp.provManager.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.provManager.vo.ProvGoodsVo;

public interface IProvGoodsService {

	QueryResult<ProvGoodsVo> queryProvGoodsList(QueryInfo<ProvGoodsVo> queryInfo) throws Exception;

}
