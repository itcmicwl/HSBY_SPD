package gyqx.spdherp.spdOutPtl.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.JdeGoodsStockInfo;

public interface IJdeGoodsStockInfoService 
{
	JdeGoodsStockInfo get(String id) throws Exception ;
	JdeGoodsStockInfo add(JdeGoodsStockInfo jdeGoodsStockInfo) throws Exception ;
	JdeGoodsStockInfo update(JdeGoodsStockInfo jdeGoodsStockInfo) throws Exception;
	
	List<JdeGoodsStockInfo> query(String predicate, String orderBy, Object... fieldValue) throws Exception;
	
	List<JdeGoodsStockInfo> list(JdeGoodsStockInfo bean) throws Exception;
	
	QueryResult<JdeGoodsStockInfo> listByPage(QueryInfo<JdeGoodsStockInfo> queryInfo) throws Exception;	

}


