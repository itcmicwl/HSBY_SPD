
package gyqx.spdhdi.myInfos.vo;

/**
 * 过期证件Vo
 */
public class OutdateCertsVo {
	/**
	 * ID
	 */
	private String id;
	/**
	 * 证件类型
	 */
	private String certKind;
	/**
	 * 证件类型代码
	 */
	private String certType;
	/**
	 * 起始日期
	 */
	private String expdtBeginDate;
	/**
	 * 截止日期
	 */
	private String expdtEndDate;
	/**
	 * 是否长期有效
	 */
	private String isValiding;
	/**
	 * 过期天数
	 */
	private String outDate;

	/**
	 * 相关ID
	 */
	private String aboutId;
	/**
	 * 相关名称
	 */
	private String aboutName;
	/**
	 * 相关代码
	 */
	private String aboutCode;
	/**
	 * 医院ID：方便查询承诺书和委托书用
	 */
	private String hosId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCertKind() {
		return certKind;
	}

	public void setCertKind(String certKind) {
		this.certKind = certKind;
	}

	public String getExpdtBeginDate() {
		return expdtBeginDate;
	}

	public void setExpdtBeginDate(String expdtBeginDate) {
		this.expdtBeginDate = expdtBeginDate;
	}

	public String getExpdtEndDate() {
		return expdtEndDate;
	}

	public void setExpdtEndDate(String expdtEndDate) {
		this.expdtEndDate = expdtEndDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getAboutId() {
		return aboutId;
	}

	public void setAboutId(String aboutId) {
		this.aboutId = aboutId;
	}

	public String getAboutName() {
		return aboutName;
	}

	public void setAboutName(String aboutName) {
		this.aboutName = aboutName;
	}

	public String getAboutCode() {
		return aboutCode;
	}

	public void setAboutCode(String aboutCode) {
		this.aboutCode = aboutCode;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getIsValiding() {
		return isValiding;
	}

	public void setIsValiding(String isValiding) {
		this.isValiding = isValiding;
	}

	@Override
	public String toString() {
		return "OutdateCertsVo [id=" + id + ", certKind=" + certKind + ", certType=" + certType + ", expdtBeginDate="
				+ expdtBeginDate + ", expdtEndDate=" + expdtEndDate + ", isValiding=" + isValiding + ", outDate="
				+ outDate + ", aboutId=" + aboutId + ", aboutName=" + aboutName + ", aboutCode=" + aboutCode
				+ ", hosId=" + hosId + "]";
	}

}