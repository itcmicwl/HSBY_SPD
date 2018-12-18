package gyqx.spdherp.deptMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.deptMgr.vo.ReceiveAddressVo;
import gyqx.spdherp.po.DeptGoodsAuditRelation;

public interface IDeptGoodsAuditRelationMapper {
	/**
	 * 新增
	 * @param receiveAddressVo
	 */
	void insertDeptGoodsAuditRelation(DeptGoodsAuditRelation deptGoodsAuditRelation); //增加

	void updateDeptGoodsAuditRelation(DeptGoodsAuditRelation deptGoodsAuditRelation);//修改

	List<DeptGoodsAuditRelation> selectByAuditer(String id);//查询
	//根据医院ID 科室ID 审核人ID进行查询
	DeptGoodsAuditRelation selectByDeptGoodsAuditRelation(DeptGoodsAuditRelation deptGoodsAuditRelation);

	void deleteDeptGoodsAuditRelation(String id);
	
	
}
