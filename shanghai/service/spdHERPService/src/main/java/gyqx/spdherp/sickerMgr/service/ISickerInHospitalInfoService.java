package gyqx.spdherp.sickerMgr.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.HisKs;
import gyqx.spdherp.po.SickerInHospitalInfo;
import gyqx.spdherp.sickerMgr.vo.SickerInHospitalInfoVo;

public interface ISickerInHospitalInfoService 
{
	SickerInHospitalInfo get(String id) throws Exception ;
	SickerInHospitalInfo add(SickerInHospitalInfo sickerInHospitalInfo) throws Exception ;
	SickerInHospitalInfo update(SickerInHospitalInfo sickerInHospitalInfo) throws Exception;
	
	List<SickerInHospitalInfo> query(String predicate, String orderBy, Object... fieldValue) throws Exception;
	
	List<SickerInHospitalInfoVo> list(SickerInHospitalInfoVo bean) throws Exception;
	
	QueryResult<SickerInHospitalInfoVo> listByPage(QueryInfo<SickerInHospitalInfoVo> queryInfo) throws Exception;

	List<HisKs> getHisDepts(HisKs hisKs) throws Exception;

}


