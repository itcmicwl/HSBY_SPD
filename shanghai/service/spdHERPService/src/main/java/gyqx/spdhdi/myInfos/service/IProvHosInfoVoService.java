package gyqx.spdhdi.myInfos.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myInfos.vo.HosInfoVo;
import gyqx.spdhdi.myInfos.vo.HosProvVo;
import gyqx.spdhdi.myInfos.vo.ProvHosInfoVo;

public interface IProvHosInfoVoService {

	QueryResult<ProvHosInfoVo> getProvHosInfoVoList(QueryInfo<ProvHosInfoVo> queryInfo) throws Exception;
	
	QueryResult<HosInfoVo> getHosInfoVoList(QueryInfo<HosInfoVo> queryInfo) throws Exception;

	int insertProvHosInfo(ProvHosInfoVo provHosInfoVo) throws Exception;

	int setStatus(ProvHosInfoVo provHosInfoVo) throws Exception;

	int setProvHosInfo(ProvHosInfoVo provHosInfoVo) throws Exception;

	int deleteByPrimaryKey(String id) throws Exception;

	boolean existProvHosInfo(ProvHosInfoVo provHosInfoVo) throws Exception;

	ProvHosInfoVo getProvHosInfoByid(String id) throws Exception;

	QueryResult<HosProvVo> getHosProvVoList(QueryInfo<HosProvVo> queryInfo) throws Exception;

}