package gyqx.spdherp.deptMgr.vo;

import java.util.List;

import gyqx.spdhdi.po.HosGoodsInfo;

/**
 * @author Administrator
 * 科室产品信息批量新增vo
 */
public class DeptGoodsApplyBatchAddVo {

	/**
	 * 医院产品集合信息
	 */
	private List<HosGoodsInfo> hosGoodsInfos;

	public List<HosGoodsInfo> getHosGoodsInfos() {
		return hosGoodsInfos;
	}

	public void setHosGoodsInfos(List<HosGoodsInfo> hosGoodsInfos) {
		this.hosGoodsInfos = hosGoodsInfos;
	}
	
}
