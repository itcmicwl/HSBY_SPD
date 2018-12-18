package gyqx.spdherp.deptMgr.service;

import java.util.List;

import gyqx.spdherp.po.DeptGoodsAuditRelation;


public interface IDeptGoodsAuditRelationService {
	void insertDeptGoodsAuditRelation(List<DeptGoodsAuditRelation> deptGoodsAuditRelations); //新增或修改
	
	List<DeptGoodsAuditRelation> selectByAuditer(String id);//查询

}
