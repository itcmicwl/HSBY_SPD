package gyqx.spdherp.deptMgr.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyVo;
import gyqx.spdherp.deptMgr.vo.SysUserOrgVo;
import gyqx.spdherp.reportAnalysis.vo.SysConfigVo;

public interface IDeptGoodsAuditMapper {
	
	/**
	 * 查询已提交的科室产品信息
	 * @param queryObject
	 * @return
	 */
	List<DeptGoodsApplyVo> getDeptGoodsAuditList(DeptGoodsApplyVo queryObject);
	
	/**
	 * 科室产品审批通过
	 * @param vo
	 */
	void deptGoodsAuditList(DeptGoodsApplyVo vo);
	
	
	/**
	 * 科室产品审批驳回
	 * @param vo
	 */
	void deptGoodsReject(DeptGoodsApplyVo vo);
	
	/**
	 * 根据科室产品申请表ID，更改申请状态
	 * @param param
	 */
	void updateDeptApplyGoodsStatus(@Param("ids") List<String> deptGoodsApplyIds,@Param("userCode")String userCode);
	
	List<SysConfigVo> getConfig();
	
	public List<SysUserOrgVo> getSalemanCode(SysUserOrgVo orgVo);
	
	/**
	 * 查询待插入科室产品信息
	 * @param goodsIds
	 * @return
	 */
	public List<DeptGoodsApplyVo> getApplyGoodsList(List<String> deptApplyGoodsIds);

	/**
	 * 科室产品审核通过，批量插入科室产品表
	 * @param deptGoodsList
	 */
	void importDeptGoodsInfo(List<DeptGoodsApplyVo> deptGoodsList);
}
