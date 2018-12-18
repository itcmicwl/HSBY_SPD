package gyqx.spdherp.adverseEvents.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import gyqx.spdherp.adverseEvents.dao.AdverseEventsDao;
import gyqx.spdherp.adverseEvents.service.IAdverseEventsService;
import gyqx.spdherp.adverseEvents.vo.AdverseEventsVo;
import gyqx.spdherp.po.AdverseEvents;

@Service
public class AdverseEventsService implements IAdverseEventsService {
	
	@Resource
	private AdverseEventsDao  dao;
	
	public AdverseEvents get(String id)
	{
		return dao.get( id);	
	}	

	public AdverseEventsVo add(AdverseEventsVo adverseEvents) throws Exception 
	{
		return dao.insertAndGet(adverseEvents);
	}
		
	public AdverseEvents update(AdverseEvents adverseEvents) throws Exception {
		return dao.updateAndGet(adverseEvents);
	}
	public int updateFields(AdverseEvents adverseEvents,String fieldNames) throws Exception
	{
		return dao.updateFields(adverseEvents, fieldNames);
	}

	public void delete(AdverseEvents adverseEvents) throws Exception {
		dao.delete(adverseEvents);
	}
	
	public AdverseEvents getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (AdverseEvents) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<AdverseEvents> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<AdverseEvents>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<AdverseEvents> list(AdverseEvents bean) throws Exception
	{
		return (List<AdverseEvents>) dao.list(bean);
	}	
	
	public QueryResult<AdverseEvents> listByPage(QueryInfo<AdverseEvents> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


