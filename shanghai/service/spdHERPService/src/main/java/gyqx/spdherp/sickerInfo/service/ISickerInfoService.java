package gyqx.spdherp.sickerInfo.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SickerInfo;

public interface ISickerInfoService 
{
	SickerInfo get(String id) throws Exception ;
	SickerInfo add(SickerInfo sickerInfo) throws Exception ;
	SickerInfo update(SickerInfo sickerInfo) throws Exception;
	
	List<SickerInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<SickerInfo> list(SickerInfo bean) throws Exception;
	
	QueryResult<SickerInfo> listByPage(QueryInfo<SickerInfo> queryInfo) throws Exception;	

}


