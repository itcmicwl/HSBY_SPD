package gyqx.spdherp.adverseEvents.dao;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.adverseEvents.dao.mapper.IAdverseEventsFileMapper;
import gyqx.spdherp.po.AdverseEvents;
import gyqx.spdherp.po.AdverseEventsFile;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class AdverseEventsFileDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IAdverseEventsFileMapper mapper;

	public AdverseEventsFile get(String id)
	{
		return dao.get(AdverseEventsFile.class, id);	
	}	

	public AdverseEventsFile insertAndGet(AdverseEventsFile adverseEventsFile) throws Exception 
	{
		return dao.insertAndGet(adverseEventsFile);
	}

	public AdverseEventsFile updateAndGet(AdverseEventsFile adverseEventsFile) throws Exception {
		return dao.updateAndGet(adverseEventsFile);
	}
	public int updateFields(AdverseEventsFile adverseEventsFile,String fieldNames) throws Exception
	{
		return dao.updateFields(adverseEventsFile, fieldNames);
	}
	public int updateNotNullFields(AdverseEventsFile adverseEventsFile) throws Exception
	{
		return dao.updateNotNullFields(adverseEventsFile);
	}

	public void delete(AdverseEventsFile adverseEventsFile) throws Exception {
		dao.delete(adverseEventsFile);
	}
	
	public AdverseEventsFile getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (AdverseEventsFile) dao.getByFieldName(AdverseEventsFile.class, fieldName, fieldValue);
	}
	
	public List<AdverseEventsFile> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<AdverseEventsFile>) dao.queryBeanList(AdverseEventsFile.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(AdverseEventsFile.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<AdverseEventsFile> list(AdverseEvents adverseEvents) throws Exception{		
		return mapper.list(adverseEvents);
	}
}


