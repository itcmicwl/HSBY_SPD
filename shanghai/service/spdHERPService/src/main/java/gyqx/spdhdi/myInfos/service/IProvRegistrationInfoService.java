package gyqx.spdhdi.myInfos.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.po.ProvRegistrationInfo;

public interface IProvRegistrationInfoService 
{
	ProvRegistrationInfo get(String id) throws Exception ;
	ProvRegistrationInfo add(ProvRegistrationInfo provRegistrationInfo) throws Exception ;
	ProvRegistrationInfo update(ProvRegistrationInfo provRegistrationInfo) throws Exception;
	
	List<ProvRegistrationInfo> query(String predicate, String orderBy, Object... fieldValue) throws Exception;
	
	List<ProvRegistrationInfo> list(ProvRegistrationInfo bean) throws Exception;
	
	QueryResult<ProvRegistrationInfo> listByPage(QueryInfo<ProvRegistrationInfo> queryInfo) throws Exception;	

}


