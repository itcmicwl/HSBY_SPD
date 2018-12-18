
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 公司/医院信息表
*/
@Table(name="bas_company_info")
public class BasCompanyInfo implements IBean
{
	/**
	 * ID
	*/
	@Id
	@Size(max=36)
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 公司/医院编号
	*/
	@Size(max=36)
	@Column(name="code")
	@Title("公司/医院编号")
	private String code ;
	/**
	 * 公司/医院名称
	*/
	@Size(max=300)
	@Column(name="cname")
	@Title("公司/医院名称")
	private String cname ;
	/**
	 * 公司/医院别名
	*/
	@Size(max=300)
	@Column(name="another_name")
	@Title("公司/医院别名")
	private String anotherName ;
	/**
	 * 拼音码
	*/
	@Size(max=36)
	@Column(name="short_pinyin")
	@Title("拼音码")
	private String shortPinyin ;
	/**
	 * 营业执照注册号
	*/
	@Size(max=36)
	@Column(name="Reg_code")
	@Title("营业执照注册号")
	private String regCode ;
	/**
	 * 经营许可证编号
	*/
	@Size(max=36)
	@Column(name="jyxk_code")
	@Title("经营许可证编号")
	private String jyxkCode ;
	/**
	 * 纳税人识别号
	*/
	@Size(max=36)
	@Column(name="nsrsb_code")
	@Title("纳税人识别号")
	private String nsrsbCode ;
	/**
	 * 公司/医院类型
	 * 
	 * 机构类型
	*/
	@Size(max=2)
	@Column(name="kind")
	@Title("公司/医院类型")
	private String kind ;
	/**
	 * 法人代表
	*/
	@Size(max=36)
	@Column(name="legal")
	@Title("法人代表")
	private String legal ;
	/**
	 * 地址
	*/
	@Size(max=300)
	@Column(name="address")
	@Title("地址")
	private String address ;
	/**
	 * 联系人
	*/
	@Size(max=36)
	@Column(name="linkman")
	@Title("联系人")
	private String linkman ;
	/**
	 * 联系方式
	*/
	@Size(max=36)
	@Column(name="contact_way")
	@Title("联系方式")
	private String contactWay ;
	/**
	 * 传真
	*/
	@Size(max=36)
	@Column(name="contact_fax")
	@Title("传真")
	private String contactFax ;
	/**
	 * 企业logo
	*/
	@Size(max=255)
	@Column(name="logo")
	@Title("企业logo")
	private String logo ;
	/**
	 * 邮政编码
	*/
	@Size(max=10)
	@Column(name="post_code")
	@Title("邮政编码")
	private String postCode ;
	/**
	 * 电子邮箱
	*/
	@Size(max=100)
	@Column(name="email")
	@Title("电子邮箱")
	private String email ;
	/**
	 * 备注信息
	*/
	@Size(max=128)
	@Column(name="remark")
	@Title("备注信息")
	private String remark ;
	/**
	 * 内部ERP编码
	*/
	@Size(max=36)
	@Column(name="erp_code")
	@Title("内部ERP编码")
	private String erpCode ;
	/**
	 * 是否供货单位
	*/
	@Size(max=1)
	@Column(name="is_supply")
	@Title("是否供货单位")
	private String isSupply ;
	/**
	 * 是否三证合一
	*/
	@Size(max=1)
	@Column(name="is_three_in_one")
	@Title("是否三证合一")
	private String isThreeInOne ;
	/**
	 * 统一码
	*/
	@Size(max=128)
	@Column(name="master_code")
	@Title("统一码")
	private String masterCode ;
	/**
	 * 标志
	*/
	@Size(max=1)
	@Column(name="flag")
	@Title("标志")
	private String flag ;
	/**
	 * 省
	 * 手工输入
	*/
	@Size(max=36)
	@JoinColumn(name="province",table="bas_area",referencedColumnName="cname")
	@Title("省")
	private String province ;
	/**
	 * 市
	*/
	@Size(max=36)
	@JoinColumn(name="city",table="bas_area",referencedColumnName="cname")
	@Title("市")
	private String city ;
	/**
	 * 区县
	*/
	@Size(max=36)
	@JoinColumn(name="area",table="bas_area",referencedColumnName="cname")
	@Title("区县")
	private String area ;
	/**
	 * 区域代码
	*/
	@Size(max=36)
	@JoinColumn(name="area_code",table="bas_area",referencedColumnName="code")
	@Title("区域代码")
	private String areaCode ;
	/**
	 * 操作员ID
	 * 新增或修改的操作员ID
	*/
	@Size(max=36)
	@Column(name="uxid")
	@Title("操作员ID")
	private String uxid ;
	/**
	 * 建档时间
	*/
	@Column(name="fill_date")
	@Title("建档时间")
	private java.util.Date fillDate ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 数据版本
	*/
	@Column(name="version")
	@Title("数据版本")
	private int version ;

	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public String getCode ()
	{
		return code ;
	}
	
	public void setCode (String code )
	{
		this.code = code;
	}
	public String getCname ()
	{
		return cname ;
	}
	
	public void setCname (String cname )
	{
		this.cname = cname;
	}
	public String getAnotherName ()
	{
		return anotherName ;
	}
	
	public void setAnotherName (String anotherName )
	{
		this.anotherName = anotherName;
	}
	public String getShortPinyin ()
	{
		return shortPinyin ;
	}
	
	public void setShortPinyin (String shortPinyin )
	{
		this.shortPinyin = shortPinyin;
	}
	public String getRegCode ()
	{
		return regCode ;
	}
	
	public void setRegCode (String regCode )
	{
		this.regCode = regCode;
	}
	public String getJyxkCode ()
	{
		return jyxkCode ;
	}
	
	public void setJyxkCode (String jyxkCode )
	{
		this.jyxkCode = jyxkCode;
	}
	public String getNsrsbCode ()
	{
		return nsrsbCode ;
	}
	
	public void setNsrsbCode (String nsrsbCode )
	{
		this.nsrsbCode = nsrsbCode;
	}
	public String getKind ()
	{
		return kind ;
	}
	
	public void setKind (String kind )
	{
		this.kind = kind;
	}
	public String getLegal ()
	{
		return legal ;
	}
	
	public void setLegal (String legal )
	{
		this.legal = legal;
	}
	public String getAddress ()
	{
		return address ;
	}
	
	public void setAddress (String address )
	{
		this.address = address;
	}
	public String getLinkman ()
	{
		return linkman ;
	}
	
	public void setLinkman (String linkman )
	{
		this.linkman = linkman;
	}
	public String getContactWay ()
	{
		return contactWay ;
	}
	
	public void setContactWay (String contactWay )
	{
		this.contactWay = contactWay;
	}
	public String getContactFax ()
	{
		return contactFax ;
	}
	
	public void setContactFax (String contactFax )
	{
		this.contactFax = contactFax;
	}
	public String getLogo ()
	{
		return logo ;
	}
	
	public void setLogo (String logo )
	{
		this.logo = logo;
	}
	public String getPostCode ()
	{
		return postCode ;
	}
	
	public void setPostCode (String postCode )
	{
		this.postCode = postCode;
	}
	public String getEmail ()
	{
		return email ;
	}
	
	public void setEmail (String email )
	{
		this.email = email;
	}
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
	}
	public String getErpCode ()
	{
		return erpCode ;
	}
	
	public void setErpCode (String erpCode )
	{
		this.erpCode = erpCode;
	}
	public String getIsSupply ()
	{
		return isSupply ;
	}
	
	public void setIsSupply (String isSupply )
	{
		this.isSupply = isSupply;
	}
	public String getIsThreeInOne ()
	{
		return isThreeInOne ;
	}
	
	public void setIsThreeInOne (String isThreeInOne )
	{
		this.isThreeInOne = isThreeInOne;
	}
	public String getMasterCode ()
	{
		return masterCode ;
	}
	
	public void setMasterCode (String masterCode )
	{
		this.masterCode = masterCode;
	}
	public String getFlag ()
	{
		return flag ;
	}
	
	public void setFlag (String flag )
	{
		this.flag = flag;
	}
	public String getProvince ()
	{
		return province ;
	}
	
	public void setProvince (String province )
	{
		this.province = province;
	}
	public String getCity ()
	{
		return city ;
	}
	
	public void setCity (String city )
	{
		this.city = city;
	}
	public String getArea ()
	{
		return area ;
	}
	
	public void setArea (String area )
	{
		this.area = area;
	}
	public String getAreaCode ()
	{
		return areaCode ;
	}
	
	public void setAreaCode (String areaCode )
	{
		this.areaCode = areaCode;
	}
	public String getUxid ()
	{
		return uxid ;
	}
	
	public void setUxid (String uxid )
	{
		this.uxid = uxid;
	}
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public java.util.Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (java.util.Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public int getVersion ()
	{
		return version ;
	}
	
	public void setVersion (int version )
	{
		this.version = version;
	}

	public String getUid ()
	{
		return uxid ;
	}
	
	public void setUid (String uxid )
	{
		this.uxid = uxid;
	}

	@Override
	public String toString() {
		return "BasCompanyInfo [" +" id=" +  id   +", code=" +  code   +", cname=" +  cname   +", anotherName=" +  anotherName   +", shortPinyin=" +  shortPinyin   +", regCode=" +  regCode   +", jyxkCode=" +  jyxkCode   +", nsrsbCode=" +  nsrsbCode   +", kind=" +  kind   +", legal=" +  legal   +", address=" +  address   +", linkman=" +  linkman   +", contactWay=" +  contactWay   +", contactFax=" +  contactFax   +", logo=" +  logo   +", postCode=" +  postCode   +", email=" +  email   +", remark=" +  remark   +", erpCode=" +  erpCode   +", isSupply=" +  isSupply   +", isThreeInOne=" +  isThreeInOne   +", masterCode=" +  masterCode   +", flag=" +  flag   +", province=" +  province   +", city=" +  city   +", area=" +  area   +", areaCode=" +  areaCode   +", uxid=" +  uxid   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}