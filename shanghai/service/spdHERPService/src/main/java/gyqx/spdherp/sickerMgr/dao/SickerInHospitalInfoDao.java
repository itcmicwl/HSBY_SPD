package gyqx.spdherp.sickerMgr.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.po.HisKs;
import gyqx.spdherp.sickerMgr.vo.SickerInHospitalInfoVo;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.sickerMgr.dao.mapper.ISickerInHospitalInfoMapper;
import gyqx.spdherp.po.SickerInHospitalInfo;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class SickerInHospitalInfoDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ISickerInHospitalInfoMapper mapper;

	public SickerInHospitalInfo get(String id)
	{
		return dao.get(SickerInHospitalInfo.class, id);	
	}	

	public SickerInHospitalInfo insertAndGet(SickerInHospitalInfo sickerInHospitalInfo) throws Exception 
	{
		return dao.insertAndGet(sickerInHospitalInfo);
	}

	public SickerInHospitalInfo updateAndGet(SickerInHospitalInfo sickerInHospitalInfo) throws Exception {
		return dao.updateAndGet(sickerInHospitalInfo);
	}
	public int updateFields(SickerInHospitalInfo sickerInHospitalInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(sickerInHospitalInfo, fieldNames);
	}
	public int updateNotNullFields(SickerInHospitalInfo sickerInHospitalInfo) throws Exception
	{
		return dao.updateNotNullFields(sickerInHospitalInfo);
	}

	public void delete(SickerInHospitalInfo sickerInHospitalInfo) throws Exception {
		dao.delete(sickerInHospitalInfo);
	}
	
	public SickerInHospitalInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (SickerInHospitalInfo) dao.getByFieldName(SickerInHospitalInfo.class, fieldName, fieldValue);
	}
	
	public List<SickerInHospitalInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<SickerInHospitalInfo>) dao.queryBeanList(SickerInHospitalInfo.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(SickerInHospitalInfo.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<SickerInHospitalInfoVo> list(SickerInHospitalInfoVo qbean) throws Exception{
		
		return mapper.list(qbean);
	}

	public List<HisKs> getHisDepts(HisKs hisKs) throws Exception{
		return mapper.getHisDepts(hisKs);
	}
}


