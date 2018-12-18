package gyqx.spdherp.returnGoods.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.ExchangeGoods;

public interface IExchangeGoodsService 
{
	ExchangeGoods get(String id) throws Exception ;
	ExchangeGoods add(ExchangeGoods exchangeGoods) throws Exception ;
	ExchangeGoods update(ExchangeGoods exchangeGoods) throws Exception;
	
	List<ExchangeGoods> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<ExchangeGoods> list(ExchangeGoods bean) throws Exception;
	
	QueryResult<ExchangeGoods> listByPage(QueryInfo<ExchangeGoods> queryInfo) throws Exception;	

}


