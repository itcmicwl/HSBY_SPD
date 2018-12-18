
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 注册证基本信息
*/
@Table(name="bas_registration_info")
public class BasRegistrationInfo implements IBean
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
	 * 注册证类别
	 * 
	 * 注册证类型
	*/
	@Size(max=36)
	@Column(name="reg_kind")
	@Title("注册证类别")
	private String regKind ;
	/**
	 * 注册证产品名称
	*/
	@Size(max=36)
	@Column(name="product_name")
	@Title("注册证产品名称")
	private String productName ;
	/**
	 * 证照编码
	*/
	@Size(max=36)
	@Column(name="certificate_code")
	@Title("证照编码")
	private String certificateCode ;
	/**
	 * 生产厂家
	*/
	@Size(max=36)
	@JoinColumn(name="mfrs_id",table="bas_mfrs_info",referencedColumnName="id")
	@Title("生产厂家")
	private String mfrsId ;
	/**
	 * 有效开始日期
	*/
	@Column(name="expdt_begin_date")
	@Title("有效开始日期")
	private java.util.Date expdtBeginDate ;
	/**
	 * 有效结束日期
	*/
	@Column(name="expdt_end_date")
	@Title("有效结束日期")
	private java.util.Date expdtEndDate ;
	/**
	 * 是否历史证件
	*/
	@Size(max=1)
	@Column(name="is_old_info")
	@Title("是否历史证件")
	private String isOldInfo ;
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
	public String getRegKind ()
	{
		return regKind ;
	}
	
	public void setRegKind (String regKind )
	{
		this.regKind = regKind;
	}
	public String getProductName ()
	{
		return productName ;
	}
	
	public void setProductName (String productName )
	{
		this.productName = productName;
	}
	public String getCertificateCode ()
	{
		return certificateCode ;
	}
	
	public void setCertificateCode (String certificateCode )
	{
		this.certificateCode = certificateCode;
	}
	public String getMfrsId ()
	{
		return mfrsId ;
	}
	
	public void setMfrsId (String mfrsId )
	{
		this.mfrsId = mfrsId;
	}
	public java.util.Date getExpdtBeginDate ()
	{
		return expdtBeginDate ;
	}
	
	public void setExpdtBeginDate (java.util.Date expdtBeginDate )
	{
		this.expdtBeginDate = expdtBeginDate;
	}
	public java.util.Date getExpdtEndDate ()
	{
		return expdtEndDate ;
	}
	
	public void setExpdtEndDate (java.util.Date expdtEndDate )
	{
		this.expdtEndDate = expdtEndDate;
	}
	public String getIsOldInfo ()
	{
		return isOldInfo ;
	}
	
	public void setIsOldInfo (String isOldInfo )
	{
		this.isOldInfo = isOldInfo;
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
		return "BasRegistrationInfo [" +" id=" +  id   +", regKind=" +  regKind   +", productName=" +  productName   +", certificateCode=" +  certificateCode   +", mfrsId=" +  mfrsId   +", expdtBeginDate=" +  expdtBeginDate   +", expdtEndDate=" +  expdtEndDate   +", isOldInfo=" +  isOldInfo   +", uxid=" +  uxid   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}