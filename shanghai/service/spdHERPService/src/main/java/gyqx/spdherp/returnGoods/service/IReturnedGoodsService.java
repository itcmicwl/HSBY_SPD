package gyqx.spdherp.returnGoods.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.ReturnedGoods;

public interface IReturnedGoodsService 
{
	ReturnedGoods get(String id) throws Exception ;
	ReturnedGoods add(ReturnedGoods returnedGoods) throws Exception ;
	ReturnedGoods update(ReturnedGoods returnedGoods) throws Exception;
	
	List<ReturnedGoods> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<ReturnedGoods> list(ReturnedGoods bean) throws Exception;
	
	QueryResult<ReturnedGoods> listByPage(QueryInfo<ReturnedGoods> queryInfo) throws Exception;	

}


