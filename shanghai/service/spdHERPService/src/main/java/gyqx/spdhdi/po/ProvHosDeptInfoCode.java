
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 供应商医院部门内部编码设置
*/
@Table(name="prov_hos_dept_info_code")
public class ProvHosDeptInfoCode implements IBean
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
	 * 医院部门ID
	*/
	@Size(max=36)
	@JoinColumn(name="hos_dept_id",table="sys_org",referencedColumnName="id")
	@Title("医院部门ID")
	private String hosDeptId ;
	/**
	 * 在供应商ERP中编码(供应商)
	*/
	@Size(max=36)
	@Column(name="erp_code")
	@Title("在供应商ERP中编码(供应商)")
	private String erpCode ;
	/**
	 * 业务员ID
	*/
	@Size(max=36)
	@JoinColumn(name="saleman_id",table="sys_user",referencedColumnName="id")
	@Title("业务员ID")
	private String salemanId ;
	/**
	 * 业务员ERP中编码(供应商)
	*/
	@Size(max=36)
	@Column(name="saleman_code")
	@Title("业务员ERP中编码(供应商)")
	private String salemanCode ;
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
	public String getHosDeptId ()
	{
		return hosDeptId ;
	}
	
	public void setHosDeptId (String hosDeptId )
	{
		this.hosDeptId = hosDeptId;
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


	@Override
	public String toString() {
		return "ProvHosDeptInfoCode [" +" id=" +  id   +", provId=" +  provId   +", hosId=" +  hosId   +", hosDeptId=" +  hosDeptId   +", erpCode=" +  erpCode   +", salemanId=" +  salemanId   +", salemanCode=" +  salemanCode   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}