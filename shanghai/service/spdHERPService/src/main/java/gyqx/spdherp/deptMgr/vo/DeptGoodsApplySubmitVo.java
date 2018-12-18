package gyqx.spdherp.deptMgr.vo;

import java.util.List;

import gyqx.spdhdi.po.HosGoodsInfo;

/**
 * @author Administrator
 * 科室产品信息批量提交vo
 */
public class DeptGoodsApplySubmitVo {

	/**
	 * 科室产品申请集合信息
	 */
	private List<DeptGoodsApplyVo> deptGoodsApplys;

	public List<DeptGoodsApplyVo> getDeptGoodsApplys() {
		return deptGoodsApplys;
	}

	public void setDeptGoodsApplys(List<DeptGoodsApplyVo> deptGoodsApplys) {
		this.deptGoodsApplys = deptGoodsApplys;
	}



}
