
package gyqx.spdhdi.myInfos.vo;

import java.util.List;

import gyqx.spdhdi.po.BasMfrsCertificateImage;

/**
 * 生产厂商证件信息
 */
public class BasMfrsCertificateVo{
	/**
	 * ID
	 */
	private String id;
	/**
	 * 生产厂商证件图片信息
	 */
	private List<BasMfrsCertificateImage> basMfrsCertificateImages;
	/**
	 * 公司/医院ID
	 */
	private String mfrsId;
	/**
	 * 证照类型
	 */
	private String certKind;
	/**
	 * 证件编号
	 */
	private String certCode;
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

	public List<BasMfrsCertificateImage> getBasMfrsCertificateImages() {
		return basMfrsCertificateImages;
	}

	public void setBasMfrsCertificateImages(List<BasMfrsCertificateImage> basMfrsCertificateImages) {
		this.basMfrsCertificateImages = basMfrsCertificateImages;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMfrsId() {
		return mfrsId;
	}

	public void setMfrsId(String mfrsId) {
		this.mfrsId = mfrsId;
	}

	public String getCertKind() {
		return certKind;
	}

	public void setCertKind(String certKind) {
		this.certKind = certKind;
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
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

	@Override
	public String toString() {
		return "BasMfrsCertificateVo [id=" + id + ", basMfrsCertificateImages=" + basMfrsCertificateImages + ", mfrsId="
				+ mfrsId + ", certKind=" + certKind + ", certCode=" + certCode + ", expdtBeginDate=" + expdtBeginDate
				+ ", expdtEndDate=" + expdtEndDate + ", isValiding=" + isValiding + ", uid=" + uid + ", fillDate="
				+ fillDate + ", lastUpdateDatetime=" + lastUpdateDatetime + ", version=" + version + "]";
	}

}