package gyqx.spdherp.deptMgr.vo;

import java.util.List;

import gyqx.spdhdi.po.HosGoodsInfo;

public class DeptGoodsInfoImportVo {

	private String hosId;
	private String hosName;
	private String deptId;
	private String deptName;
	private List<HosGoodsInfo> hosGoodsInfos;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getHosName() {
		return hosName;
	}

	public void setHosName(String hosName) {
		this.hosName = hosName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<HosGoodsInfo> getHosGoodsInfos() {
		return hosGoodsInfos;
	}

	public void setHosGoodsInfos(List<HosGoodsInfo> hosGoodsInfos) {
		this.hosGoodsInfos = hosGoodsInfos;
	}

	@Override
	public String toString() {
		return "DeptGoodsInfoImportVo [hosId=" + hosId + ", hosName=" + hosName + ", deptId=" + deptId + ", deptName="
				+ deptName + ", hosGoodsInfos=" + hosGoodsInfos + "]";
	}

}
