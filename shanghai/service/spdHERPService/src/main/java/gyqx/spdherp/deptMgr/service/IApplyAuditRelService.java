package gyqx.spdherp.deptMgr.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.ApplyAuditRel;
import gyqx.spdherp.po.DeptBuyCar;

public interface IApplyAuditRelService 
{
	ApplyAuditRel get(String id) throws Exception ;
	ApplyAuditRel add(ApplyAuditRel applyAuditRel) throws Exception ;
	ApplyAuditRel update(ApplyAuditRel applyAuditRel) throws Exception;
	
	List<ApplyAuditRel> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<ApplyAuditRel> list(ApplyAuditRel bean) throws Exception;
	
	QueryResult<ApplyAuditRel> listByPage(QueryInfo<ApplyAuditRel> queryInfo) throws Exception;	
	int addList(List<ApplyAuditRel> rels) throws Exception ;
}


