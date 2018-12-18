
package gyqx.spdherp.deptMgr.vo;

import java.util.List;

/**
 * 模板产品查询参数对象
 */
public class TemplateGoodsParam {
	/**
	 * 医疗机构ID
	 */
	private String hosId;
	/**
	 * 科室ID
	 */
	private String deptId;
	/**
	 * 产品模板ID
	 */
	private List<String> tempIds;
	/**
	 * 产品名称：作条件查询用
	 */
	private String goodsName;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public List<String> getTempIds() {
		return tempIds;
	}

	public void setTempIds(List<String> tempIds) {
		this.tempIds = tempIds;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

}