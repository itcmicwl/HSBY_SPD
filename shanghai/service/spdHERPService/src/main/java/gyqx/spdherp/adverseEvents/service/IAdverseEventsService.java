package gyqx.spdherp.adverseEvents.service;


import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.adverseEvents.vo.AdverseEventsVo;
import gyqx.spdherp.po.AdverseEvents;

public interface IAdverseEventsService 
{
	AdverseEvents get(String id) throws Exception ;
	AdverseEventsVo add(AdverseEventsVo adverseEvents) throws Exception ;
	AdverseEvents update(AdverseEvents adverseEvents) throws Exception;
	
	List<AdverseEvents> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<AdverseEvents> list(AdverseEvents bean) throws Exception;
	
	QueryResult<AdverseEvents> listByPage(QueryInfo<AdverseEvents> queryInfo) throws Exception;	

}


