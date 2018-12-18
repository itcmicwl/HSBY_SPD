
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 经销授权书关联注册证
*/
@Table(name="prov_jxsqs_regist")
public class ProvJxsqsRegist implements IBean
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
	 * 授权书ID
	*/
	@Size(max=36)
	@JoinColumn(name="sqs_id",table="prov_jxsqs_regist",referencedColumnName="id")
	@Title("授权书ID")
	private String sqsId ;
	/**
	 * 供应商ID
	*/
	@Size(max=36)
	@JoinColumn(name="prov_id",table="bas_company_info",referencedColumnName="id")
	@Title("供应商ID")
	private String provId ;
	/**
	 * 生产厂家
	*/
	@Size(max=36)
	@JoinColumn(name="mfrs_id",table="bas_mfrs_info",referencedColumnName="id")
	@Title("生产厂家")
	private String mfrsId ;
	/**
	 * 证照编号
	*/
	@Size(max=128)
	@JoinColumn(name="certificate_id",table="prov_registration_info",referencedColumnName="id")
	@Title("证照编号")
	private String certificateId ;
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
	public String getSqsId ()
	{
		return sqsId ;
	}
	
	public void setSqsId (String sqsId )
	{
		this.sqsId = sqsId;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getMfrsId ()
	{
		return mfrsId ;
	}
	
	public void setMfrsId (String mfrsId )
	{
		this.mfrsId = mfrsId;
	}
	public String getCertificateId ()
	{
		return certificateId ;
	}
	
	public void setCertificateId (String certificateId )
	{
		this.certificateId = certificateId;
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
		return "ProvJxsqsRegist [" +" id=" +  id   +", sqsId=" +  sqsId   +", provId=" +  provId   +", mfrsId=" +  mfrsId   +", certificateId=" +  certificateId   +", uxid=" +  uxid   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}