package gyqx.spdherp.deptMgr.dao;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.deptMgr.dao.mapper.IApplyAuditRelMapper;
import gyqx.spdherp.po.ApplyAuditRel;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class ApplyAuditRelDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IApplyAuditRelMapper mapper;

	public ApplyAuditRel get(String id)
	{
		return dao.get(ApplyAuditRel.class, id);	
	}	

	public ApplyAuditRel insertAndGet(ApplyAuditRel applyAuditRel) throws Exception 
	{
		return dao.insertAndGet(applyAuditRel);
	}

	public ApplyAuditRel updateAndGet(ApplyAuditRel applyAuditRel) throws Exception {
		return dao.updateAndGet(applyAuditRel);
	}
	public int updateFields(ApplyAuditRel applyAuditRel,String fieldNames) throws Exception
	{
		return dao.updateFields(applyAuditRel, fieldNames);
	}
	public int updateNotNullFields(ApplyAuditRel applyAuditRel) throws Exception
	{
		return dao.updateNotNullFields(applyAuditRel);
	}

	public void delete(String  userId) throws Exception {
		mapper.delete(userId);
	}
	
	public ApplyAuditRel getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ApplyAuditRel) dao.getByFieldName(ApplyAuditRel.class, fieldName, fieldValue);
	}
	
	public List<ApplyAuditRel> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ApplyAuditRel>) dao.queryBeanList(ApplyAuditRel.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(ApplyAuditRel.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<ApplyAuditRel> list(ApplyAuditRel qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


