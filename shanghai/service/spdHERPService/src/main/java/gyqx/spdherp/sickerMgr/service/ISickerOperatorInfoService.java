package gyqx.spdherp.sickerMgr.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SickerOperatorInfo;
import gyqx.spdherp.sickerMgr.vo.SickerOperatorInfoVo;

public interface ISickerOperatorInfoService 
{
	SickerOperatorInfo get(String id) throws Exception ;
	SickerOperatorInfo add(SickerOperatorInfo sickerOperatorInfo) throws Exception ;
	SickerOperatorInfo update(SickerOperatorInfo sickerOperatorInfo) throws Exception;
	
	List<SickerOperatorInfo> query(String predicate, String orderBy, Object... fieldValue) throws Exception;
	
	List<SickerOperatorInfoVo> list(SickerOperatorInfoVo bean) throws Exception;
	
	QueryResult<SickerOperatorInfoVo> listByPage(QueryInfo<SickerOperatorInfoVo> queryInfo) throws Exception;

	List<SickerOperatorInfo> getOperDepts(SickerOperatorInfo sickerOperatorInfo) throws Exception;
}


