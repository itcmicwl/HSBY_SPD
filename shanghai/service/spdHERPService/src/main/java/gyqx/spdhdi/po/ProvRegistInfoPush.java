
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 供应商注册证推送信息
*/
@Table(name="prov_regist_info_push")
public class ProvRegistInfoPush implements IBean
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
	 * 供应商注册证ID
	*/
	@Size(max=36)
	@JoinColumn(name="prov_regist_ID",table="prov_registration_info",referencedColumnName="id")
	@Title("供应商注册证ID")
	private String provRegistId ;
	/**
	 * 供应商ID
	*/
	@Size(max=36)
	@JoinColumn(name="prov_id",table="bas_company_info",referencedColumnName="id")
	@Title("供应商ID")
	private String provId ;
	/**
	 * 医院ID
	*/
	@Size(max=36)
	@JoinColumn(name="hos_id",table="bas_company_info",referencedColumnName="id")
	@Title("医院ID")
	private String hosId ;
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
	@Size(max=300)
	@Column(name="product_name")
	@Title("注册证产品名称")
	private String productName ;
	/**
	 * 证照编码
	*/
	@Size(max=128)
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
	 * 推送状态
	*/
	@Size(max=4)
	@Column(name="push_status")
	@Title("推送状态")
	private String pushStatus ;
	/**
	 * 推送操作员ID
	 * 新增或修改的操作员ID
	*/
	@Size(max=36)
	@Column(name="push_uid")
	@Title("推送操作员ID")
	private String pushUid ;
	/**
	 * 审核人ID
	 * 操作员ID
	*/
	@Size(max=36)
	@Column(name="auditor_uid")
	@Title("审核人ID")
	private String auditorUid ;
	/**
	 * 推送时间
	*/
	@Column(name="fill_date")
	@Title("推送时间")
	private java.util.Date fillDate ;
	/**
	 * 审核时间
	*/
	@Column(name="audit_date")
	@Title("审核时间")
	private java.util.Date auditDate ;
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
	public String getProvRegistId ()
	{
		return provRegistId ;
	}
	
	public void setProvRegistId (String provRegistId )
	{
		this.provRegistId = provRegistId;
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
	public String getPushStatus ()
	{
		return pushStatus ;
	}
	
	public void setPushStatus (String pushStatus )
	{
		this.pushStatus = pushStatus;
	}
	public String getPushUid ()
	{
		return pushUid ;
	}
	
	public void setPushUid (String pushUid )
	{
		this.pushUid = pushUid;
	}
	public String getAuditorUid ()
	{
		return auditorUid ;
	}
	
	public void setAuditorUid (String auditorUid )
	{
		this.auditorUid = auditorUid;
	}
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public java.util.Date getAuditDate ()
	{
		return auditDate ;
	}
	
	public void setAuditDate (java.util.Date auditDate )
	{
		this.auditDate = auditDate;
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


	@Override
	public String toString() {
		return "ProvRegistInfoPush [" +" id=" +  id   +", provRegistId=" +  provRegistId   +", provId=" +  provId   +", hosId=" +  hosId   +", regKind=" +  regKind   +", productName=" +  productName   +", certificateCode=" +  certificateCode   +", mfrsId=" +  mfrsId   +", expdtBeginDate=" +  expdtBeginDate   +", expdtEndDate=" +  expdtEndDate   +", pushStatus=" +  pushStatus   +", pushUid=" +  pushUid   +", auditorUid=" +  auditorUid   +", fillDate=" +  fillDate   +", auditDate=" +  auditDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}