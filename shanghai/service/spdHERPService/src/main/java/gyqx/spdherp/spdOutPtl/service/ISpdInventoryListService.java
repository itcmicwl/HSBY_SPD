package gyqx.spdherp.spdOutPtl.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SpdInventoryList;

public interface ISpdInventoryListService 
{
	SpdInventoryList get(String id) throws Exception ;
	SpdInventoryList add(SpdInventoryList spdInventoryList) throws Exception ;
	SpdInventoryList update(SpdInventoryList spdInventoryList) throws Exception;
	
	List<SpdInventoryList> query(String predicate, String orderBy, Object... fieldValue) throws Exception;
	
	List<SpdInventoryList> list(SpdInventoryList bean) throws Exception;
	
	QueryResult<SpdInventoryList> listByPage(QueryInfo<SpdInventoryList> queryInfo) throws Exception;	

}


