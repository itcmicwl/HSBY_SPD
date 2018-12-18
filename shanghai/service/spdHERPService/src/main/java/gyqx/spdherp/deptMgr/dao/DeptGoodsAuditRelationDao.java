package gyqx.spdherp.deptMgr.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdherp.deptMgr.dao.mapper.IDeptGoodsAuditRelationMapper;
import gyqx.spdherp.deptMgr.dao.mapper.ReceiveAddressMapper;
import gyqx.spdherp.deptMgr.vo.ReceiveAddressVo;
import gyqx.spdherp.po.DeptGoodsAuditRelation;

@Repository
public class DeptGoodsAuditRelationDao {
	@Resource
	private IDeptGoodsAuditRelationMapper mapper;
	/**
	 * 新增
	 * @param receiveAddressVo
	 */
	public void insertDeptGoodsAuditRelation(DeptGoodsAuditRelation deptGoodsAuditRelation) {
		mapper.insertDeptGoodsAuditRelation(deptGoodsAuditRelation);
	}
	
	public void updateDeptGoodsAuditRelation(DeptGoodsAuditRelation deptGoodsAuditRelation) {
		mapper.updateDeptGoodsAuditRelation(deptGoodsAuditRelation);
	}

	public List<DeptGoodsAuditRelation> selectByAuditer(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByAuditer(id);
	}
	//根据医院ID 科室ID 审核员ID进行查询
	public DeptGoodsAuditRelation selectByDeptGoodsAuditRelation(DeptGoodsAuditRelation deptGoodsAuditRelation) {
		// TODO Auto-generated method stub
		return mapper.selectByDeptGoodsAuditRelation(deptGoodsAuditRelation);
	}
	//根据主键进行删除
	public void deleteDeptGoodsAuditRelation(String id) {
		// TODO Auto-generated method stub
		 mapper.deleteDeptGoodsAuditRelation(id);
	}
	
}
