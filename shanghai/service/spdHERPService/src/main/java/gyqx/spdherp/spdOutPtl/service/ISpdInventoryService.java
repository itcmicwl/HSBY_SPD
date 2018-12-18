package gyqx.spdherp.spdOutPtl.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SpdInventory;
import gyqx.spdherp.spdOutPtl.vo.SpdInventoryVo;

public interface ISpdInventoryService 
{
	SpdInventory get(String id) throws Exception ;
	SpdInventoryVo add(SpdInventoryVo spdInventory) throws Exception ;
	String update(SpdInventory spdInventory) throws Exception;
	
	List<SpdInventory> query(String predicate, String orderBy, Object... fieldValue) throws Exception;
	
	List<SpdInventoryVo> list(SpdInventoryVo bean) throws Exception;
	
	QueryResult<SpdInventoryVo> listByPage(QueryInfo<SpdInventoryVo> queryInfo) throws Exception;

	String closeBill(String hosId) throws Exception;

}


