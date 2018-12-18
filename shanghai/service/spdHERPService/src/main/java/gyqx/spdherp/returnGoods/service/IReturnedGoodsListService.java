package gyqx.spdherp.returnGoods.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.ReturnedGoodsList;

public interface IReturnedGoodsListService 
{
	ReturnedGoodsList get(String id) throws Exception ;
	ReturnedGoodsList add(ReturnedGoodsList returnedGoodsList) throws Exception ;
	ReturnedGoodsList update(ReturnedGoodsList returnedGoodsList) throws Exception;
	
	List<ReturnedGoodsList> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<ReturnedGoodsList> list(ReturnedGoodsList bean) throws Exception;
	
	QueryResult<ReturnedGoodsList> listByPage(QueryInfo<ReturnedGoodsList> queryInfo) throws Exception;	

}


