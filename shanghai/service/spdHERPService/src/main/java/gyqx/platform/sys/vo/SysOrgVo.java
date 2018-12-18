package gyqx.platform.sys.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import common.db.meta.Title;

public class SysOrgVo implements  Serializable{
	/**
	 * ID
	*/   
	@Id
	@Size(max=36)
	@Column(name="id")
	private String id ;
	/**
	 * 上级ID
	*/
	@Size(max=36)
	@Column(name="pid")
	private String pid ;
	/**
	 * 机构编码
	*/
	@Size(max=36)
	@Column(name="code")
	private String code ;
	/**
	 * 自编码
	 * 企业机构的自用编码
	*/
	@Size(max=36)
	@Column(name="self_code")
	private String selfCode ;
	/**
	 * ERP编号
	*/
	@Size(max=36)
	@Column(name="erp_code")
	@Title("ERP编号")
	private String erpCode ;
	/**
	 * 机构类型
	 * 
	 * [0 企业内部机构,1 上游厂商,2 下游客户]
	*/
	@Size(max=2)
	@Column(name="kind")
	private String kind ;
	/**
	 * 机构名称
	*/
	@NotEmpty(message="机构名称不能为空")
	@Size(max=36)
	@Column(name="ename")
	private String ename ;
	/**
	 * 机构别名
	*/
	@NotEmpty
	@Size(max=36)
	@Column(name="cname")
	private String cname ;
	/**
	 * 序号
	 * 机构在上级机构中的序号
	*/
	@Column(name="ordinal")
	private int ordinal ;
	/**
	 * 拼音码
	*/
	@Size(max=36)
	@Column(name="short_pinyin")
	private String shortPinyin ;
	/**
	 * 地址
	*/
	@Size(max=36)
	@Column(name="address")
	private String address ;

	
	/**
	 * 联系方式
	*/
	@Size(max=36)
	@Pattern(regexp ="1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}",message="{tel.format.error}")
	private String contactWay ;
	/**
	 * 备注信息
	*/
	@Size(max=128)
	@Column(name="remark")
	private String remark ;
	/**
	 * 结点类型
	 * 
	 * [0 非叶结点,1 叶结点]
	*/
	@Size(max=1)
	@Column(name="node_kind")
	private String nodeKind ;
	/**
	 * 标志
	*/
	@Size(max=1)
	@Column(name="flag")
	private String flag ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 数据版本
	*/
	@Column(name="version")
	private int version ;
	
	/**
	 * Vo添加属性 pName pCname  当前机构的上级机构名称
	 */
	
	private String pName;

	private String pCname;
	
	/**
	 * Vo添加属性 corpId corpKind	/**
	 * 所属单位ID
	*/
	@NotEmpty
	@Size(max=36)
	@Column(name="corp_id")
	private String corpId ;
	/**
	 * 单位类型
	 * 
	 * 机构类型
	*/
	@NotEmpty
	@Size(max=36)
	@Column(name="corp_kind")
	private String corpKind ;
	
	/**
	 * 所属单位名 所属单位类型名（医疗机构、配送机构）
	 */
	private String corpName ;
	private String corpKindName ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSelfCode() {
		return selfCode;
	}
	public void setSelfCode(String selfCode) {
		this.selfCode = selfCode;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getOrdinal() {
		return ordinal;
	}
	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
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
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNodeKind() {
		return nodeKind;
	}
	public void setNodeKind(String nodeKind) {
		this.nodeKind = nodeKind;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpCname() {
		return pCname;
	}
	public void setpCname(String pCname) {
		this.pCname = pCname;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getCorpKind() {
		return corpKind;
	}
	public void setCorpKind(String corpKind) {
		this.corpKind = corpKind;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCorpKindName() {
		return corpKindName;
	}
	public void setCorpKindName(String corpKindName) {
		this.corpKindName = corpKindName;
	}
	public String getErpCode() {
		return erpCode;
	}
	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}
}
