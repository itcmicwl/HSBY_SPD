
package gyqx.spdhdi.myInfos.vo;

/**
 * 公司/医院信息Vo
 */
public class CompanyInfoVo1 {
	///////////////////////////////////////////////////////////
	/////////////////////// 公司/医院基本信息  /////////////////////
	/**
	 * ID
	 */
	private String id;
	/**
	 * 公司/医院编号
	 */
	private String code;
	/**
	 * 公司/医院名称
	 */
	private String cname;
	/**
	 * 公司/医院别名
	 */
	private String anotherName;
	/**
	 * 拼音码
	 */
	private String shortPinyin;
	/**
	 * 营业执照注册号
	 */
	private String regCode;
	/**
	 * 经营许可证编号
	 */
	private String jyxkCode;
	/**
	 * 纳税人识别号
	 */
	private String nsrsbCode;
	/**
	 * 公司/医院类型
	 */
	private String kind;
	/**
	 * 法人代表
	 */
	private String legal;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 联系人
	 */
	private String linkman;
	/**
	 * 联系方式
	 */
	private String contactWay;
	/**
	 * 传真
	 */
	private String contactFax;
	/**
	 * 企业logo
	 */
	private String logo;
	/**
	 * 邮政编码
	 */
	private String postCode;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 是否三证合一
	 */
	private String isThreeInOne;
	/**
	 * 标志
	 */
	private String flag;
	/**
	 * 操作员ID 新增或修改的操作员ID
	 */
	private String basUid;
	/**
	 * 建档时间
	 */
	private java.util.Date basFillDate;
	/**
	 * 最后更新时间
	 */
	private java.util.Date basLastUpdateDatetime;
	/**
	 * 数据版本
	 */
	private int basVersion;
	
	///////////////////////////////////////////////////////
	/////////////////////// 公司证件信息   ////////////////////
	/**
	 * 公司证件信息ID
	 */
	private String certId;
	/**
	 * 公司/医院ID
	 */
	private String companyId;
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
	private String certUid;
	/**
	 * 建档时间
	 */
	private java.util.Date certFillDate;
	/**
	 * 最后更新时间
	 */
	private java.util.Date certLastUpdateDatetime;
	/**
	 * 数据版本
	 */
	private int certVersion;
	
	////////////////////////////////////////////////////////
	/////////////////////// 公司证照图片信息  ///////////////////
	/**
	 * 公司证照图片信息ID
	 */
	private String imgId;
	/**
	 * 证照ID
	 */
	private String imgCertId;
	/**
	 * 附件路径
	 */
	private String filePath;
	/**
	 * 建档时间
	 */
	private java.util.Date fillDate;
	/**
	 * 操作员ID 新增或修改的操作员ID
	 */
	private String imgUid;
	/**
	 * 最后更新时间
	 */
	private java.util.Date imgLastUpdateDatetime;
	/**
	 * 数据版本
	 */
	private int imgVersion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getAnotherName() {
		return anotherName;
	}

	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}

	public String getShortPinyin() {
		return shortPinyin;
	}

	public void setShortPinyin(String shortPinyin) {
		this.shortPinyin = shortPinyin;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getJyxkCode() {
		return jyxkCode;
	}

	public void setJyxkCode(String jyxkCode) {
		this.jyxkCode = jyxkCode;
	}

	public String getNsrsbCode() {
		return nsrsbCode;
	}

	public void setNsrsbCode(String nsrsbCode) {
		this.nsrsbCode = nsrsbCode;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getContactFax() {
		return contactFax;
	}

	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsThreeInOne() {
		return isThreeInOne;
	}

	public void setIsThreeInOne(String isThreeInOne) {
		this.isThreeInOne = isThreeInOne;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBasUid() {
		return basUid;
	}

	public void setBasUid(String basUid) {
		this.basUid = basUid;
	}

	public java.util.Date getBasFillDate() {
		return basFillDate;
	}

	public void setBasFillDate(java.util.Date basFillDate) {
		this.basFillDate = basFillDate;
	}

	public java.util.Date getBasLastUpdateDatetime() {
		return basLastUpdateDatetime;
	}

	public void setBasLastUpdateDatetime(java.util.Date basLastUpdateDatetime) {
		this.basLastUpdateDatetime = basLastUpdateDatetime;
	}

	public int getBasVersion() {
		return basVersion;
	}

	public void setBasVersion(int basVersion) {
		this.basVersion = basVersion;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public String getCertUid() {
		return certUid;
	}

	public void setCertUid(String certUid) {
		this.certUid = certUid;
	}

	public java.util.Date getCertFillDate() {
		return certFillDate;
	}

	public void setCertFillDate(java.util.Date certFillDate) {
		this.certFillDate = certFillDate;
	}

	public java.util.Date getCertLastUpdateDatetime() {
		return certLastUpdateDatetime;
	}

	public void setCertLastUpdateDatetime(java.util.Date certLastUpdateDatetime) {
		this.certLastUpdateDatetime = certLastUpdateDatetime;
	}

	public int getCertVersion() {
		return certVersion;
	}

	public void setCertVersion(int certVersion) {
		this.certVersion = certVersion;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getImgCertId() {
		return imgCertId;
	}

	public void setImgCertId(String imgCertId) {
		this.imgCertId = imgCertId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public java.util.Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(java.util.Date fillDate) {
		this.fillDate = fillDate;
	}

	public String getImgUid() {
		return imgUid;
	}

	public void setImgUid(String imgUid) {
		this.imgUid = imgUid;
	}

	public java.util.Date getImgLastUpdateDatetime() {
		return imgLastUpdateDatetime;
	}

	public void setImgLastUpdateDatetime(java.util.Date imgLastUpdateDatetime) {
		this.imgLastUpdateDatetime = imgLastUpdateDatetime;
	}

	public int getImgVersion() {
		return imgVersion;
	}

	public void setImgVersion(int imgVersion) {
		this.imgVersion = imgVersion;
	}

	@Override
	public String toString() {
		return "CompanyInfoVo [id=" + id + ", code=" + code + ", cname=" + cname + ", anotherName=" + anotherName
				+ ", shortPinyin=" + shortPinyin + ", regCode=" + regCode + ", jyxkCode=" + jyxkCode + ", nsrsbCode="
				+ nsrsbCode + ", kind=" + kind + ", legal=" + legal + ", address=" + address + ", linkman=" + linkman
				+ ", contactWay=" + contactWay + ", contactFax=" + contactFax + ", logo=" + logo + ", postCode="
				+ postCode + ", email=" + email + ", remark=" + remark + ", isThreeInOne=" + isThreeInOne + ", flag="
				+ flag + ", basUid=" + basUid + ", basFillDate=" + basFillDate + ", basLastUpdateDatetime="
				+ basLastUpdateDatetime + ", basVersion=" + basVersion + ", certId=" + certId + ", companyId="
				+ companyId + ", certKind=" + certKind + ", certCode=" + certCode + ", expdtBeginDate=" + expdtBeginDate
				+ ", expdtEndDate=" + expdtEndDate + ", isValiding=" + isValiding + ", certUid=" + certUid
				+ ", certFillDate=" + certFillDate + ", certLastUpdateDatetime=" + certLastUpdateDatetime
				+ ", certVersion=" + certVersion + ", imgId=" + imgId + ", imgCertId=" + imgCertId + ", filePath="
				+ filePath + ", fillDate=" + fillDate + ", imgUid=" + imgUid + ", imgLastUpdateDatetime="
				+ imgLastUpdateDatetime + ", imgVersion=" + imgVersion + "]";
	}

}