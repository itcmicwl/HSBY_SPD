package gyqx.spdherp.stockPile.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.HosTakingStock;

public interface IHosTakingStockService 
{
	HosTakingStock get(String id) throws Exception ;
	HosTakingStock add(HosTakingStock hosTakingStock) throws Exception ;
	HosTakingStock update(HosTakingStock hosTakingStock) throws Exception;
	
	List<HosTakingStock> query(String predicate, String orderBy, Object... fieldValue) throws Exception;
	
	List<HosTakingStock> list(HosTakingStock bean) throws Exception;
	
	QueryResult<HosTakingStock> listByPage(QueryInfo<HosTakingStock> queryInfo) throws Exception;	

}


