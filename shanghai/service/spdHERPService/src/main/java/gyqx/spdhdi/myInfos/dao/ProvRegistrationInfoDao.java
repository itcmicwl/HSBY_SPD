package gyqx.spdhdi.myInfos.dao;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdhdi.myInfos.dao.mapper.IProvRegistrationInfoMapper;
import gyqx.spdhdi.po.ProvRegistrationInfo;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class ProvRegistrationInfoDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IProvRegistrationInfoMapper mapper;

	public ProvRegistrationInfo get(String id)
	{
		return dao.get(ProvRegistrationInfo.class, id);	
	}	

	public ProvRegistrationInfo insertAndGet(ProvRegistrationInfo provRegistrationInfo) throws Exception 
	{
		return dao.insertAndGet(provRegistrationInfo);
	}

	public ProvRegistrationInfo updateAndGet(ProvRegistrationInfo provRegistrationInfo) throws Exception {
		return dao.updateAndGet(provRegistrationInfo);
	}
	public int updateFields(ProvRegistrationInfo provRegistrationInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(provRegistrationInfo, fieldNames);
	}
	public int updateNotNullFields(ProvRegistrationInfo provRegistrationInfo) throws Exception
	{
		return dao.updateNotNullFields(provRegistrationInfo);
	}

	public void delete(ProvRegistrationInfo provRegistrationInfo) throws Exception {
		dao.delete(provRegistrationInfo);
	}
	
	public ProvRegistrationInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ProvRegistrationInfo) dao.getByFieldName(ProvRegistrationInfo.class, fieldName, fieldValue);
	}
	
	public List<ProvRegistrationInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ProvRegistrationInfo>) dao.queryBeanList(ProvRegistrationInfo.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(ProvRegistrationInfo.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<ProvRegistrationInfo> list(ProvRegistrationInfo qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


