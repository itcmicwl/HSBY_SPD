package gyqx.spdherp.sickerMgr.service.impl;

import javax.annotation.Resource;

import gyqx.spdherp.sickerMgr.vo.SickerOperatorInfoVo;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SickerOperatorInfo;
import gyqx.spdherp.sickerMgr.dao.SickerOperatorInfoDao;
import gyqx.spdherp.sickerMgr.service.ISickerOperatorInfoService;

@Service
public class SickerOperatorInfoService implements ISickerOperatorInfoService {
	
	@Resource
	private SickerOperatorInfoDao  dao;
	
	public SickerOperatorInfo get(String id)
	{
		return dao.get( id);	
	}	

	public SickerOperatorInfo add(SickerOperatorInfo sickerOperatorInfo) throws Exception 
	{
		return dao.insertAndGet(sickerOperatorInfo);
	}
		
	public SickerOperatorInfo update(SickerOperatorInfo sickerOperatorInfo) throws Exception {
		return dao.updateAndGet(sickerOperatorInfo);
	}
	public int updateFields(SickerOperatorInfo sickerOperatorInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(sickerOperatorInfo, fieldNames);
	}

	public void delete(SickerOperatorInfo sickerOperatorInfo) throws Exception {
		dao.delete(sickerOperatorInfo);
	}
	
	public SickerOperatorInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (SickerOperatorInfo) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<SickerOperatorInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<SickerOperatorInfo>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<SickerOperatorInfoVo> list(SickerOperatorInfoVo bean) throws Exception
	{
		return (List<SickerOperatorInfoVo>) dao.list(bean);
	}	
	
	public QueryResult<SickerOperatorInfoVo> listByPage(QueryInfo<SickerOperatorInfoVo> queryInfo) throws Exception{
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	@Override
	public List<SickerOperatorInfo> getOperDepts(SickerOperatorInfo sickerOperatorInfo) throws Exception {
		return dao.getOperDepts(sickerOperatorInfo);
	}

}


