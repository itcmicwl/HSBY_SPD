package gyqx.spdherp.stocInfoMgr.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import common.db.meta.Title;

/**
 * 科室请领关系
 * 科室向科室请领
 * @date 2017年8月21日 上午11:53:45
 */
public class HosDeptPurStockVo {
	/**
	 * ID
	 */
	@Id
	@Size(max = 36)
	@Column(name = "id")
	@Title("ID")
	private String id;
	/**
	 * 医院ID
	 */
	@Size(max = 36)
	@Column(name = "hos_id")
	@Title("医院ID")
	private String hosId;
	/**
	 * 科室ID
	 */
	@Size(max = 36)
	@Column(name = "dept_id")
	@Title("科室ID")
	private String deptId;
	/**
	 * 库房ID
	 */
	@Size(max = 36)
	@Column(name = "stock_id")
	@Title("库房ID")
	private String stockId;
	
	// 设置默认
	@Size(max = 1)
	@Column(name = "is_default") // the correct is default
	@Title("是否默认")
	private String isDefault;
	/**
	 * 数据版本
	 */
	@Column(name = "version")
	@Title("数据版本")
	private int version;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	public java.util.Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(java.util.Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	// 存储相关科室
	private List<String> stockIds; // 实际数据为科室数据
	private String deptName; // 科室名称
	private String hosName; // 医院名称
	private String stocName; // 实质上也是科室
	private String defaultDeptId; // 默认请购科室

	public String getStocName() {
		return stocName;
	}

	public void setStocName(String stocName) {
		this.stocName = stocName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getHosName() {
		return hosName;
	}

	public void setHosName(String hosName) {
		this.hosName = hosName;
	}

	public List<String> getStockIds() {
		return stockIds;
	}

	public void setStockIds(List<String> stockIds) {
		this.stockIds = stockIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getDefaultDeptId() {
		return defaultDeptId;
	}

	public void setDefaultDeptId(String defaultDeptId) {
		this.defaultDeptId = defaultDeptId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}