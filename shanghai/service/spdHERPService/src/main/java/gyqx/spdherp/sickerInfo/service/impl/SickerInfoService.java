package gyqx.spdherp.sickerInfo.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SickerInfo;
import gyqx.spdherp.sickerInfo.dao.SickerInfoDao;
import gyqx.spdherp.sickerInfo.service.ISickerInfoService;

@Service
public class SickerInfoService implements ISickerInfoService {
	
	@Resource
	private SickerInfoDao  dao;
	
	public SickerInfo get(String id)
	{
		return dao.get( id);	
	}	

	public SickerInfo add(SickerInfo sickerInfo) throws Exception 
	{
		return dao.insertAndGet(sickerInfo);
	}
		
	public SickerInfo update(SickerInfo sickerInfo) throws Exception {
		return dao.updateAndGet(sickerInfo);
	}
	public int updateFields(SickerInfo sickerInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(sickerInfo, fieldNames);
	}

	public void delete(SickerInfo sickerInfo) throws Exception {
		dao.delete(sickerInfo);
	}
	
	public SickerInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (SickerInfo) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<SickerInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<SickerInfo>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<SickerInfo> list(SickerInfo bean) throws Exception
	{
		return (List<SickerInfo>) dao.list(bean);
	}	
	
	public QueryResult<SickerInfo> listByPage(QueryInfo<SickerInfo> queryInfo) throws Exception{		
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


