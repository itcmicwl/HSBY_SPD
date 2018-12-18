package gyqx.spdherp.sickerInfo.dao;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.sickerInfo.dao.mapper.ISickerInfoMapper;
import gyqx.spdherp.po.SickerInfo;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class SickerInfoDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ISickerInfoMapper mapper;

	public SickerInfo get(String id)
	{
		return dao.get(SickerInfo.class, id);	
	}	

	public SickerInfo insertAndGet(SickerInfo sickerInfo) throws Exception 
	{
		return dao.insertAndGet(sickerInfo);
	}

	public SickerInfo updateAndGet(SickerInfo sickerInfo) throws Exception {
		return dao.updateAndGet(sickerInfo);
	}
	public int updateFields(SickerInfo sickerInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(sickerInfo, fieldNames);
	}
	public int updateNotNullFields(SickerInfo sickerInfo) throws Exception
	{
		return dao.updateNotNullFields(sickerInfo);
	}

	public void delete(SickerInfo sickerInfo) throws Exception {
		dao.delete(sickerInfo);
	}
	
	public SickerInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (SickerInfo) dao.getByFieldName(SickerInfo.class, fieldName, fieldValue);
	}
	
	public List<SickerInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<SickerInfo>) dao.queryBeanList(SickerInfo.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(SickerInfo.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<SickerInfo> list(SickerInfo qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


