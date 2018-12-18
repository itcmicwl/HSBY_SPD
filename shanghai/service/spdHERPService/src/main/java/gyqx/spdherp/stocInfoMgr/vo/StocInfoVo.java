package gyqx.spdherp.stocInfoMgr.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import common.db.meta.Title;

public class StocInfoVo {

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
	 * 库房名称
	 */
	@Size(max = 200)
	@Column(name = "stoc_name")
	@Title("库房名称")
	private String stocName;
	/**
	 * 负责人
	 */
	@Size(max = 36)
	@Column(name = "stoc_fzr")
	@Title("负责人")
	private String stocFzr;
	/**
	 * 库房电话
	 */
	@Size(max = 100)
	@Column(name = "stoc_phone")
	@Title("库房电话")
	private String stocPhone;
	/**
	 * 直属科室
	 */
	@Size(max = 36)
	@Column(name = "dept_id")
	@Title("直属科室")
	private String deptId;
	/**
	 * 库房级别
	 */
	@Size(max = 36)
	@Column(name = "stoc_level")
	@Title("库房级别")
	private String stocLevel;
	/**
	 * 货位类型
	 */
	@Size(max = 36)
	@Column(name = "shelf_type")
	@Title("货位类型")
	private String shelfType;
	/**
	 * 拼音码
	 */
	@Size(max = 36)
	@Column(name = "short_pinyin")
	@Title("拼音码")
	private String shortPinyin;

	/**
	 * 库房编码
	 */
	@Size(max = 72)
	@Column(name = "code")
	@Title("库房编码")
	private String code;

	/**
	 * 地址
	 */
	@Size(max = 300)
	@Column(name = "address")
	@Title("地址")
	private String address;
	/**
	 * 备注
	 */
	@Size(max = 400)
	@Column(name = "memo")
	@Title("备注")
	private String memo;
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
	// 新加项
	/**
	 * 科室名称
	 */
	private String deptName;

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

	public String getStocName() {
		return stocName;
	}

	public void setStocName(String stocName) {
		this.stocName = stocName;
	}

	public String getStocFzr() {
		return stocFzr;
	}

	public void setStocFzr(String stocFzr) {
		this.stocFzr = stocFzr;
	}

	public String getStocPhone() {
		return stocPhone;
	}

	public void setStocPhone(String stocPhone) {
		this.stocPhone = stocPhone;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getStocLevel() {
		return stocLevel;
	}

	public void setStocLevel(String stocLevel) {
		this.stocLevel = stocLevel;
	}

	public String getShortPinyin() {
		return shortPinyin;
	}

	public void setShortPinyin(String shortPinyin) {
		this.shortPinyin = shortPinyin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getShelfType() {
		return shelfType;
	}

	public void setShelfType(String shelfType) {
		this.shelfType = shelfType;
	}
	
}
