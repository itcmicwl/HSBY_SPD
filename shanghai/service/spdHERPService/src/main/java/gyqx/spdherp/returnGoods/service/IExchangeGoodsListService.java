package gyqx.spdherp.returnGoods.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.ExchangeGoodsList;

public interface IExchangeGoodsListService 
{
	ExchangeGoodsList get(String id) throws Exception ;
	ExchangeGoodsList add(ExchangeGoodsList exchangeGoodsList) throws Exception ;
	ExchangeGoodsList update(ExchangeGoodsList exchangeGoodsList) throws Exception;
	
	List<ExchangeGoodsList> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<ExchangeGoodsList> list(ExchangeGoodsList bean) throws Exception;
	
	QueryResult<ExchangeGoodsList> listByPage(QueryInfo<ExchangeGoodsList> queryInfo) throws Exception;	

}


