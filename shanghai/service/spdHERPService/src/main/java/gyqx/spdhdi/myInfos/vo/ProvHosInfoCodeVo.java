
package gyqx.spdhdi.myInfos.vo;

import java.util.*;
import javax.persistence.Column;
import common.db.meta.Title;

public class ProvHosInfoCodeVo 
{
	/**
	 * 用户ID
	*/
	@Column(name="userId")
	@Title("用户ID")
	private String userId ;
	/**
	 * 机构ID
	*/
	@Column(name="orgId")
	@Title("机构ID")
	private String orgId ;
	/**
	 * 机构编码
	*/
	@Column(name="orgCode")
	@Title("机构编码")
	private String orgCode ;
	/**
	 * 用户名称
	 * 在机构中显示的名称
	*/
	@Column(name="userName")
	@Title("用户名称")
	private String userName ;
	/**
	 * 用户联系电话
	*/
	@Column(name="tel")
	@Title("用户联系电话")
	private String tel ;
	/**
	 * 邮箱
	*/
	@Column(name="email")
	@Title("邮箱")
	private String email ;
	
	/**
	 * 业务员ErpCode
	*/
	@Column(name="userErpCode")
	@Title("业务员ErpCode")
	private String userErpCode ;
	public String getUserErpCode() {
		return userErpCode;
	}

	public void setUserErpCode(String userErpCode) {
		this.userErpCode = userErpCode;
	}
	
	/**
	 * ID
	*/
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 供应商ID
	*/
	@Column(name="prov_id")
	@Title("供应商ID")
	private String provId ;
	/**
	 * 医院ID
	*/
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 在供应商ERP中编码(供应商)
	*/
	@Column(name="erp_code")
	@Title("在供应商ERP中编码(供应商)")
	private String erpCode ;
	/**
	 * 业务员ID
	*/
	@Column(name="saleman_id")
	@Title("业务员ID")
	private String salemanId ;
	/**
	 * 业务员ERP中编码(供应商)
	*/
	@Column(name="saleman_code")
	@Title("业务员ERP中编码(供应商)")
	private String salemanCode ;
	/**
	 * 建档时间
	*/
	@Column(name="fill_date")
	@Title("建档时间")
	private Date fillDate ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private Date lastUpdateDatetime ;
	/**
	 * 数据版本
	*/
	@Column(name="version")
	@Title("数据版本")
	private Integer version ;

	private String hosDeptId;
	
	public String getHosDeptId() {
		return hosDeptId;
	}

	public void setHosDeptId(String hosDeptId) {
		this.hosDeptId = hosDeptId;
	}

	public String getUserId ()
	{
		return userId ;
	}
	
	public void setUserId (String userId )
	{
		this.userId = userId;
	}
	public String getOrgId ()
	{
		return orgId ;
	}
	
	public void setOrgId (String orgId )
	{
		this.orgId = orgId;
	}
	public String getOrgCode ()
	{
		return orgCode ;
	}
	
	public void setOrgCode (String orgCode )
	{
		this.orgCode = orgCode;
	}
	public String getUserName ()
	{
		return userName ;
	}
	
	public void setUserName (String userName )
	{
		this.userName = userName;
	}
	public String getTel ()
	{
		return tel ;
	}
	
	public void setTel (String tel )
	{
		this.tel = tel;
	}
	public String getEmail ()
	{
		return email ;
	}
	
	public void setEmail (String email )
	{
		this.email = email;
	}
	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getErpCode ()
	{
		return erpCode ;
	}
	
	public void setErpCode (String erpCode )
	{
		this.erpCode = erpCode;
	}
	public String getSalemanId ()
	{
		return salemanId ;
	}
	
	public void setSalemanId (String salemanId )
	{
		this.salemanId = salemanId;
	}
	public String getSalemanCode ()
	{
		return salemanCode ;
	}
	
	public void setSalemanCode (String salemanCode )
	{
		this.salemanCode = salemanCode;
	}
	public Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public Integer getVersion ()
	{
		return version ;
	}
	
	public void setVersion (Integer version )
	{
		this.version = version;
	}
	@Override
	public String toString() {
		return "ProvHosInfoCodeVo ["  +" userId=" +  userId    +", orgId=" +  orgId    +", orgCode=" +  orgCode    +", userName=" +  userName    +", tel=" +  tel    +", email=" +  email    +", id=" +  id    +", provId=" +  provId    +", hosId=" +  hosId    +", erpCode=" +  erpCode    +", salemanId=" +  salemanId    +", salemanCode=" +  salemanCode    +", fillDate=" +  fillDate    +", lastUpdateDatetime=" +  lastUpdateDatetime    +", version=" +  version   
			 + "]";
	}

}