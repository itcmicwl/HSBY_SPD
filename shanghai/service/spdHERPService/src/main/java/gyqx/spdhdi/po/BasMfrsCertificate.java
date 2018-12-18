
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 生产厂商证件信息
*/
@Table(name="bas_mfrs_certificate")
public class BasMfrsCertificate implements IBean
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
	 * 公司/医院ID
	*/
	@Size(max=36)
	@JoinColumn(name="mfrs_id",table="bas_mfrs_info",referencedColumnName="id")
	@Title("公司/医院ID")
	private String mfrsId ;
	/**
	 * 证照类型
	 * 
	 * 证照类型
	*/
	@Size(max=2)
	@Column(name="cert_kind")
	@Title("证照类型")
	private String certKind ;
	/**
	 * 证件编号
	*/
	@Size(max=128)
	@Column(name="cert_code")
	@Title("证件编号")
	private String certCode ;
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
	 * 是否长期有效
	*/
	@Size(max=1)
	@Column(name="is_validing")
	@Title("是否长期有效")
	private String isValiding ;
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
	public String getMfrsId ()
	{
		return mfrsId ;
	}
	
	public void setMfrsId (String mfrsId )
	{
		this.mfrsId = mfrsId;
	}
	public String getCertKind ()
	{
		return certKind ;
	}
	
	public void setCertKind (String certKind )
	{
		this.certKind = certKind;
	}
	public String getCertCode ()
	{
		return certCode ;
	}
	
	public void setCertCode (String certCode )
	{
		this.certCode = certCode;
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
	public String getIsValiding ()
	{
		return isValiding ;
	}
	
	public void setIsValiding (String isValiding )
	{
		this.isValiding = isValiding;
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
		return "BasMfrsCertificate [" +" id=" +  id   +", mfrsId=" +  mfrsId   +", certKind=" +  certKind   +", certCode=" +  certCode   +", expdtBeginDate=" +  expdtBeginDate   +", expdtEndDate=" +  expdtEndDate   +", isValiding=" +  isValiding   +", uxid=" +  uxid   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}