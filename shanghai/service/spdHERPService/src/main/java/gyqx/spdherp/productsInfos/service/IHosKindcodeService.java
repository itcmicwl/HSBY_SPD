package gyqx.spdherp.productsInfos.service;


import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.po.HosKindcode;

public interface IHosKindcodeService 
{
	HosKindcode get(String id) throws Exception ;
	HosKindcode add(HosKindcode hosKindcode) throws Exception ;
	HosKindcode update(HosKindcode hosKindcode) throws Exception;
	
	List<HosKindcode> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<HosKindcode> list(HosKindcode bean) throws Exception;
    void delete(HosKindcode bean) throws Exception;
	
	QueryResult<HosKindcode> listByPage(QueryInfo<HosKindcode> queryInfo) throws Exception;	

}


