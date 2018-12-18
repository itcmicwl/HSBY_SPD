package gyqx.spdherp.deptMgr.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gyqx.spdherp.deptMgr.dao.mapper.IDeptGoodsAuditMapper;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyVo;
import gyqx.spdherp.deptMgr.vo.SysUserOrgVo;
import gyqx.spdherp.reportAnalysis.vo.SysConfigVo;

@Repository
public class DeptGoodsAuditDao {

	@Autowired
	private IDeptGoodsAuditMapper auditMapper;
	
	public List<DeptGoodsApplyVo> getApplyGoodsList(List<String> goodsIds){
		return auditMapper.getApplyGoodsList(goodsIds);
	}
	
	public List<DeptGoodsApplyVo> getDeptGoodsAuditList(DeptGoodsApplyVo queryObject) {
		return auditMapper.getDeptGoodsAuditList(queryObject);
	}
	
	public void importDeptGoodsInfo(List<DeptGoodsApplyVo> deptGoodsList) throws Exception {
		auditMapper.importDeptGoodsInfo(deptGoodsList);
	}
	
	public void updateDeptApplyGoodsStatus(List<String> deptGoodsApplyIds,String userCode)throws Exception {
		auditMapper.updateDeptApplyGoodsStatus(deptGoodsApplyIds,userCode);
	}
	
	public void deptGoodsAuditList(DeptGoodsApplyVo vo){
		auditMapper.deptGoodsAuditList(vo);
	}
	
	public void deptGoodsReject(DeptGoodsApplyVo vo){
		auditMapper.deptGoodsReject(vo);
	}
	
	public List<SysConfigVo> getConfig(){
		return auditMapper.getConfig();
	}
	
	public List<SysUserOrgVo> getSalemanCode(SysUserOrgVo orgVo){
		return auditMapper.getSalemanCode(orgVo);
	}
	
}
