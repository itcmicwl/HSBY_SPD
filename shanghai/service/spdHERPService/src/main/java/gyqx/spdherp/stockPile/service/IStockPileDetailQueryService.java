package gyqx.spdherp.stockPile.service;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.stockPile.vo.StockPileDetailQueryVo;

public interface IStockPileDetailQueryService 
{
	QueryResult<StockPileDetailQueryVo> listByPage(QueryInfo<StockPileDetailQueryVo> queryInfo) throws Exception;
}


