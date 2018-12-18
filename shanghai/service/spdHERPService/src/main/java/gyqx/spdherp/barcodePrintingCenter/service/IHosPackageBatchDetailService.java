package gyqx.spdherp.barcodePrintingCenter.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.HosPackageBatchDetail;

public interface IHosPackageBatchDetailService 
{
	HosPackageBatchDetail get(String id) throws Exception ;
	HosPackageBatchDetail add(HosPackageBatchDetail hosPackageBatchDetail) throws Exception ;
	HosPackageBatchDetail update(HosPackageBatchDetail hosPackageBatchDetail) throws Exception;
	
	List<HosPackageBatchDetail> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<HosPackageBatchDetail> list(HosPackageBatchDetail bean) throws Exception;
	
	QueryResult<HosPackageBatchDetail> listByPage(QueryInfo<HosPackageBatchDetail> queryInfo) throws Exception;	

}


