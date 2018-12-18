
package gyqx.spdhdi.myInfos.vo;

import java.util.List;

/**
 * 公司/医院信息表Vo
 */
public class CompanyInfoVo {
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
	 * 
	 * 机构类型
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
	/**
	 * 生产厂商证件信息
	 */
	private List<CompanyCertificateVo> companyCertificateVos;

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

	public List<CompanyCertificateVo> getCompanyCertificateVos() {
		return companyCertificateVos;
	}

	public void setCompanyCertificateVos(List<CompanyCertificateVo> companyCertificateVos) {
		this.companyCertificateVos = companyCertificateVos;
	}

	@Override
	public String toString() {
		return "BasCompanyInfoVo [id=" + id + ", code=" + code + ", cname=" + cname + ", anotherName=" + anotherName
				+ ", shortPinyin=" + shortPinyin + ", regCode=" + regCode + ", jyxkCode=" + jyxkCode + ", nsrsbCode="
				+ nsrsbCode + ", kind=" + kind + ", legal=" + legal + ", address=" + address + ", linkman=" + linkman
				+ ", contactWay=" + contactWay + ", contactFax=" + contactFax + ", logo=" + logo + ", postCode="
				+ postCode + ", email=" + email + ", remark=" + remark + ", isThreeInOne=" + isThreeInOne + ", flag="
				+ flag + ", uid=" + uid + ", fillDate=" + fillDate + ", lastUpdateDatetime=" + lastUpdateDatetime
				+ ", version=" + version + ", companyCertificateVos=" + companyCertificateVos + "]";
	}

}