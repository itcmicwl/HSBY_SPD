
package gyqx.spdhdi.myInfos.vo;

import common.db.IBean;

/**
 * 供应商经销授权书Vo
 */
public class ProvJxsqsVo implements IBean {
	/**
	 * ID
	 */
	private String id;
	/**
	 * 供应商ID
	 */
	private String provId;
	/**
	 * 生产厂家
	 */
	private String mfrsId;
	/**
	 * 是否厂家
	 */
	private String isMfrs;
	/**
	 * 上级单位
	 */
	private String chiefId;
	/**
	 * 上级单位名称
	 */
	private String mfrsName;
	/**
	 * 授权区域
	 */
	private String saleArea;
	/**
	 * 有效开始日期
	 */
	private java.util.Date expdtBeginDate;
	/**
	 * 有效结束日期
	 */
	private java.util.Date expdtEndDate;
	/**
	 * 是否长期有效
	 */
	private String isValiding;
	/**
	 * 附件路径
	 */
	private String filePath;
	/**
	 * 操作员ID 新增或修改的操作员ID
	 */
	private String uid;
	/**
	 * 建档时间
	 */
	private java.util.Date fillDate;
	/**
	 * 最后更新时间
	 */
	private java.util.Date lastUpdateDatetime;
	/**
	 * 数据版本
	 */
	private int version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getMfrsId() {
		return mfrsId;
	}

	public void setMfrsId(String mfrsId) {
		this.mfrsId = mfrsId;
	}

	public String getIsMfrs() {
		return isMfrs;
	}

	public void setIsMfrs(String isMfrs) {
		this.isMfrs = isMfrs;
	}

	public String getChiefId() {
		return chiefId;
	}

	public void setChiefId(String chiefId) {
		this.chiefId = chiefId;
	}

	public String getSaleArea() {
		return saleArea;
	}

	public void setSaleArea(String saleArea) {
		this.saleArea = saleArea;
	}

	public java.util.Date getExpdtBeginDate() {
		return expdtBeginDate;
	}

	public void setExpdtBeginDate(java.util.Date expdtBeginDate) {
		this.expdtBeginDate = expdtBeginDate;
	}

	public java.util.Date getExpdtEndDate() {
		return expdtEndDate;
	}

	public void setExpdtEndDate(java.util.Date expdtEndDate) {
		this.expdtEndDate = expdtEndDate;
	}

	public String getIsValiding() {
		return isValiding;
	}

	public void setIsValiding(String isValiding) {
		this.isValiding = isValiding;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public java.util.Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(java.util.Date fillDate) {
		this.fillDate = fillDate;
	}

	public java.util.Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(java.util.Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}

	@Override
	public String toString() {
		return "ProvJxsqsVo [id=" + id + ", provId=" + provId + ", mfrsId=" + mfrsId + ", isMfrs=" + isMfrs
				+ ", chiefId=" + chiefId + ", mfrsName=" + mfrsName + ", saleArea=" + saleArea + ", expdtBeginDate="
				+ expdtBeginDate + ", expdtEndDate=" + expdtEndDate + ", isValiding=" + isValiding + ", filePath="
				+ filePath + ", uid=" + uid + ", fillDate=" + fillDate + ", lastUpdateDatetime=" + lastUpdateDatetime
				+ ", version=" + version + "]";
	}

}