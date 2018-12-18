
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 供应商医院信息
*/
@Table(name="prov_hos_info")
public class ProvHosInfo implements IBean
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
	 * 状态
	 * 
	 * 供应商医院对应状态
	*/
	@Size(max=36)
	@Column(name="status")
	@Title("状态")
	private String status ;
	/**
	 * 医院联系人
	*/
	@Size(max=36)
	@Column(name="hos_lxr")
	@Title("医院联系人")
	private String hosLxr ;
	/**
	 * 医院联系人电话
	*/
	@Size(max=36)
	@Column(name="hos_lxr_telphonenum")
	@Title("医院联系人电话")
	private String hosLxrTelphonenum ;
	/**
	 * 配送商联系人
	*/
	@Size(max=36)
	@Column(name="prov_lxr")
	@Title("配送商联系人")
	private String provLxr ;
	/**
	 * 配送商联系人电话
	*/
	@Size(max=36)
	@Column(name="prov_lxr_telphonenum")
	@Title("配送商联系人电话")
	private String provLxrTelphonenum ;
	/**
	 * 备注信息
	*/
	@Size(max=128)
	@Column(name="remark")
	@Title("备注信息")
	private String remark ;
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
	public String getStatus ()
	{
		return status ;
	}
	
	public void setStatus (String status )
	{
		this.status = status;
	}
	public String getHosLxr ()
	{
		return hosLxr ;
	}
	
	public void setHosLxr (String hosLxr )
	{
		this.hosLxr = hosLxr;
	}
	public String getHosLxrTelphonenum ()
	{
		return hosLxrTelphonenum ;
	}
	
	public void setHosLxrTelphonenum (String hosLxrTelphonenum )
	{
		this.hosLxrTelphonenum = hosLxrTelphonenum;
	}
	public String getProvLxr ()
	{
		return provLxr ;
	}
	
	public void setProvLxr (String provLxr )
	{
		this.provLxr = provLxr;
	}
	public String getProvLxrTelphonenum ()
	{
		return provLxrTelphonenum ;
	}
	
	public void setProvLxrTelphonenum (String provLxrTelphonenum )
	{
		this.provLxrTelphonenum = provLxrTelphonenum;
	}
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
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
		return "ProvHosInfo [" +" id=" +  id   +", provId=" +  provId   +", hosId=" +  hosId   +", status=" +  status   +", hosLxr=" +  hosLxr   +", hosLxrTelphonenum=" +  hosLxrTelphonenum   +", provLxr=" +  provLxr   +", provLxrTelphonenum=" +  provLxrTelphonenum   +", remark=" +  remark   +", uxid=" +  uxid   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}