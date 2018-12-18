package gyqx.spdherp.sickerMgr.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.sickerMgr.vo.SickerOperatorInfoVo;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.sickerMgr.dao.mapper.ISickerOperatorInfoMapper;
import gyqx.spdherp.po.SickerOperatorInfo;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class SickerOperatorInfoDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ISickerOperatorInfoMapper mapper;

	public SickerOperatorInfo get(String id)
	{
		return dao.get(SickerOperatorInfo.class, id);	
	}	

	public SickerOperatorInfo insertAndGet(SickerOperatorInfo sickerOperatorInfo) throws Exception 
	{
		return dao.insertAndGet(sickerOperatorInfo);
	}

	public SickerOperatorInfo updateAndGet(SickerOperatorInfo sickerOperatorInfo) throws Exception {
		return dao.updateAndGet(sickerOperatorInfo);
	}
	public int updateFields(SickerOperatorInfo sickerOperatorInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(sickerOperatorInfo, fieldNames);
	}
	public int updateNotNullFields(SickerOperatorInfo sickerOperatorInfo) throws Exception
	{
		return dao.updateNotNullFields(sickerOperatorInfo);
	}

	public void delete(SickerOperatorInfo sickerOperatorInfo) throws Exception {
		dao.delete(sickerOperatorInfo);
	}
	
	public SickerOperatorInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (SickerOperatorInfo) dao.getByFieldName(SickerOperatorInfo.class, fieldName, fieldValue);
	}
	
	public List<SickerOperatorInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<SickerOperatorInfo>) dao.queryBeanList(SickerOperatorInfo.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(SickerOperatorInfo.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<SickerOperatorInfoVo> list(SickerOperatorInfoVo qbean) throws Exception{
		
		return mapper.list(qbean);
	}
	public List<SickerOperatorInfo> getOperDepts(SickerOperatorInfo sickerOperatorInfo)throws Exception{
		return mapper.getOperDepts(sickerOperatorInfo);
	}
}


