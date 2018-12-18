package gyqx.spdherp.deptMgr.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;

import java.util.Date;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.ApplyAuditRel;
import gyqx.spdherp.deptMgr.dao.ApplyAuditRelDao;
import gyqx.spdherp.deptMgr.service.IApplyAuditRelService;

@Service
public class ApplyAuditRelService implements IApplyAuditRelService {
	
	@Resource
	private ApplyAuditRelDao  dao;
	@Resource
	private SysAtomUtil atomUtil;
	public ApplyAuditRel get(String id)
	{
		return dao.get( id);	
	}	

	public ApplyAuditRel add(ApplyAuditRel applyAuditRel) throws Exception 
	{
		return dao.insertAndGet(applyAuditRel);
	}
	public int addList(List<ApplyAuditRel> rels) throws Exception {
		dao.delete(rels.get(0).getUserId());
		for(ApplyAuditRel r :rels){
			//删除用户当前数据
			r.setId(atomUtil.newValue("apply-audit-rel-id"));
			r.setLastUpdateDatetime(new Date());
			r.setVersion(1);
			this.add(r);   
		}
		return rels.size();
	}

		
	public ApplyAuditRel update(ApplyAuditRel applyAuditRel) throws Exception {
		return dao.updateAndGet(applyAuditRel);
	}
	public int updateFields(ApplyAuditRel applyAuditRel,String fieldNames) throws Exception
	{
		return dao.updateFields(applyAuditRel, fieldNames);
	}

	public void delete(String userId) throws Exception {
		dao.delete(userId);
	}
	
	public ApplyAuditRel getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ApplyAuditRel) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<ApplyAuditRel> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ApplyAuditRel>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<ApplyAuditRel> list(ApplyAuditRel bean) throws Exception
	{
		return (List<ApplyAuditRel>) dao.list(bean);
	}	
	
	public QueryResult<ApplyAuditRel> listByPage(QueryInfo<ApplyAuditRel> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


