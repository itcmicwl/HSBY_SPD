
package gyqx.spdhdi.myInfos.vo;

import java.util.List;

import javax.persistence.Column;

import common.db.meta.Title;
import gyqx.spdhdi.po.ProvRegistrationImage;

/**
 * 供应商注册证推送信息Vo
 */
public class ProvRegistInfoPushVo {
	
	/**
	 * 注册证推送ID
	 */
	private String pushId;
	/**
	 * 销售代表
	 */
	private String salesName;
	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	/**
	 * 供应商注册证产品推送列表
	 */
	private List<ProvRegistGoodsPushQR> goodsPushQRs;
	/**
	 * 供应商注册证图片信息列表
	 */
	private List<ProvRegistrationImage> registrationImages;
	/**
	 * 相关名字
	 */
	private String aboutName;
	/**
	 * 证件类型
	 */
	private String certKind;
	/**
	 * 过期天数
	 */
	private String outDate;
	/**
	 * 还差多少天过期
	 */
	private String toOutDate;
	/**
	 * 是否长期有效
	 */
	private String isValiding;
	/**
	 * 供应商名称
	 */
	private String cname;
	/**
	 * 委托人
	 */
	private String ename;

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	/**
	 * ID
	 */
	private String id;
	/**
	 * 供应商注册证ID
	 */
	private String provRegistId;
	/**
	 * 供应商ID
	 */
	private String provId;
	/**
	 * 医院ID
	 */
	private String hosId;
	/**
	 * 注册证类别
	 */
	private String regKind;
	/**
	 * 注册证产品名称
	 */
	private String productName;
	/**
	 * 证照编码
	 */
	private String certificateCode;
	/**
	 * 生产厂家ID
	 */
	private String mfrsId;
	/**
	 * 生产厂家名称
	 */
	private String mfrsName;
	/**
	 * 生产厂家类型
	 */
	private String mfrsKind;
	/**
	 * 有效开始日期
	 */
	private java.util.Date expdtBeginDate;
	/**
	 * 有效结束日期
	 */
	private java.util.Date expdtEndDate;
	/**
	 * 推送状态
	 */
	private String pushStatus;
	/**
	 * 推送操作员ID 新增或修改的操作员ID
	 */
	private String pushUid;
	/**
	 * 审核人ID 操作员ID
	 */
	private String auditorUid;
	/**
	 * 推送时间
	 */
	private java.util.Date fillDate;
	/**
	 * 审核时间
	 */
	private java.util.Date auditDate;
	/**
	 * 数据版本
	 */
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
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvRegistId() {
		return provRegistId;
	}

	public void setProvRegistId(String provRegistId) {
		this.provRegistId = provRegistId;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getRegKind() {
		return regKind;
	}

	public void setRegKind(String regKind) {
		this.regKind = regKind;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	public String getMfrsId() {
		return mfrsId;
	}

	public void setMfrsId(String mfrsId) {
		this.mfrsId = mfrsId;
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

	public String getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	public String getPushUid() {
		return pushUid;
	}

	public void setPushUid(String pushUid) {
		this.pushUid = pushUid;
	}

	public String getAuditorUid() {
		return auditorUid;
	}

	public void setAuditorUid(String auditorUid) {
		this.auditorUid = auditorUid;
	}

	public java.util.Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(java.util.Date fillDate) {
		this.fillDate = fillDate;
	}

	public java.util.Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(java.util.Date auditDate) {
		this.auditDate = auditDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<ProvRegistGoodsPushQR> getGoodsPushQRs() {
		return goodsPushQRs;
	}

	public void setGoodsPushQRs(List<ProvRegistGoodsPushQR> goodsPushQRs) {
		this.goodsPushQRs = goodsPushQRs;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public List<ProvRegistrationImage> getRegistrationImages() {
		return registrationImages;
	}

	public void setRegistrationImages(List<ProvRegistrationImage> registrationImages) {
		this.registrationImages = registrationImages;
	}

	public String getCertKind() {
		return certKind;
	}

	public void setCertKind(String certKind) {
		this.certKind = certKind;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getToOutDate() {
		return toOutDate;
	}

	public void setToOutDate(String toOutDate) {
		this.toOutDate = toOutDate;
	}

	public String getAboutName() {
		return aboutName;
	}

	public void setAboutName(String aboutName) {
		this.aboutName = aboutName;
	}

	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}

	public String getIsValiding() {
		return isValiding;
	}

	public void setIsValiding(String isValiding) {
		this.isValiding = isValiding;
	}

	public String getMfrsKind() {
		return mfrsKind;
	}

	public void setMfrsKind(String mfrsKind) {
		this.mfrsKind = mfrsKind;
	}

	@Override
	public String toString() {
		return "ProvRegistInfoPushVo [pushId=" + pushId + ", goodsPushQRs=" + goodsPushQRs + ", registrationImages="
				+ registrationImages + ", aboutName=" + aboutName + ", certKind=" + certKind + ", outDate=" + outDate
				+ ", toOutDate=" + toOutDate + ", isValiding=" + isValiding + ", cname=" + cname + ", ename=" + ename
				+ ", id=" + id + ", provRegistId=" + provRegistId + ", provId=" + provId + ", hosId=" + hosId
				+ ", regKind=" + regKind + ", productName=" + productName + ", certificateCode=" + certificateCode
				+ ", mfrsId=" + mfrsId + ", mfrsName=" + mfrsName + ", mfrsKind=" + mfrsKind + ", expdtBeginDate="
				+ expdtBeginDate + ", expdtEndDate=" + expdtEndDate + ", pushStatus=" + pushStatus + ", pushUid="
				+ pushUid + ", auditorUid=" + auditorUid + ", fillDate=" + fillDate + ", auditDate=" + auditDate
				+ ", version=" + version+", lastUpdateDatetime=" +  lastUpdateDatetime + "]";
	}

}