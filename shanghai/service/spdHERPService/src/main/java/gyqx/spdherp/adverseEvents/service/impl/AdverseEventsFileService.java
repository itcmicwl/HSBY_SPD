package gyqx.spdherp.adverseEvents.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.AdverseEvents;
import gyqx.spdherp.po.AdverseEventsFile;
import gyqx.spdherp.adverseEvents.dao.AdverseEventsFileDao;
import gyqx.spdherp.adverseEvents.service.IAdverseEventsFileService;

@Service
public class AdverseEventsFileService implements IAdverseEventsFileService {
	
	@Resource
	private AdverseEventsFileDao  dao;
	
	public AdverseEventsFile get(String id)
	{
		return dao.get( id);	
	}	

	public AdverseEventsFile add(AdverseEventsFile adverseEventsFile) throws Exception 
	{
		return dao.insertAndGet(adverseEventsFile);
	}
		
	public AdverseEventsFile update(AdverseEventsFile adverseEventsFile) throws Exception {
		return dao.updateAndGet(adverseEventsFile);
	}
	public int updateFields(AdverseEventsFile adverseEventsFile,String fieldNames) throws Exception
	{
		return dao.updateFields(adverseEventsFile, fieldNames);
	}

	public void delete(AdverseEventsFile adverseEventsFile) throws Exception {
		dao.delete(adverseEventsFile);
	}
	
	public AdverseEventsFile getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (AdverseEventsFile) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<AdverseEventsFile> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<AdverseEventsFile>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<AdverseEventsFile> list(AdverseEvents adverseEvents) throws Exception
	{
		return (List<AdverseEventsFile>) dao.list(adverseEvents);
	}	
	
}


