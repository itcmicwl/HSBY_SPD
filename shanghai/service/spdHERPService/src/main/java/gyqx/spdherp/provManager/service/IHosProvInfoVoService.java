package gyqx.spdherp.provManager.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myInfos.vo.HosInfoVo;
import gyqx.spdhdi.po.ProvHosInfo;
import gyqx.spdherp.provManager.vo.HosProvVo;
import gyqx.spdherp.provManager.vo.ProvHosInfoVo;

public interface IHosProvInfoVoService {

	QueryResult<ProvHosInfoVo> getHosProvInfoVoList(QueryInfo<ProvHosInfoVo> queryInfo) throws Exception;
	
	QueryResult<HosInfoVo> getHosInfoVoList(QueryInfo<HosInfoVo> queryInfo) throws Exception;

	void insert(ProvHosInfo bean) throws Exception;

	int setStatus(ProvHosInfoVo provHosInfoVo) throws Exception;

	int setProvHosInfo(ProvHosInfoVo provHosInfoVo) throws Exception;

	int deleteByPrimaryKey(String id) throws Exception;
	
	int updateLogo(String logo) throws Exception;

	boolean existProvHosInfo(ProvHosInfoVo provHosInfoVo) throws Exception;

	ProvHosInfoVo getProvHosInfoByid(String id) throws Exception;

	List<HosProvVo> getHosProvVoList(HosProvVo hosProvVo) throws Exception;

}