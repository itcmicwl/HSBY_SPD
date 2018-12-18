package gyqx.spdhdi.myInfos.service;


import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myInfos.vo.ProvHosInfoCodeVo;
import gyqx.spdhdi.po.ProvHosInfoCode;

public interface IProvHosInfoCodeService 
{
	ProvHosInfoCode get(String id) throws Exception ;
	ProvHosInfoCode add(ProvHosInfoCode provHosInfoCode) throws Exception ;
	ProvHosInfoCode update(ProvHosInfoCode provHosInfoCode) throws Exception;
	
	List<ProvHosInfoCode> query(String predicate, String orderBy, Object... fieldValue) throws Exception;
	
	List<ProvHosInfoCode> list(ProvHosInfoCode bean) throws Exception;
	
	QueryResult<ProvHosInfoCode> listByPage(QueryInfo<ProvHosInfoCode> queryInfo) throws Exception;	
	QueryResult<ProvHosInfoCodeVo> list_PH_code_voByPage(QueryInfo<ProvHosInfoCodeVo> queryInfo) throws Exception;	

}


