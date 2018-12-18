
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 供应商经销授权书
*/
@Table(name="prov_jxsqs")
public class ProvJxsqs implements IBean
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
	 * 生产厂家
	*/
	@Size(max=36)
	@JoinColumn(name="mfrs_id",table="bas_mfrs_info",referencedColumnName="id")
	@Title("生产厂家")
	private String mfrsId ;
	/**
	 * 是否厂家
	*/
	@Size(max=1)
	@Column(name="is_mfrs")
	@Title("是否厂家")
	private String isMfrs ;
	/**
	 * 上级单位
	*/
	@Size(max=36)
	@Column(name="chief_id")
	@Title("上级单位")
	private String chiefId ;
	/**
	 * 授权区域
	*/
	@Size(max=300)
	@Column(name="sale_area")
	@Title("授权区域")
	private String saleArea ;
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
	 * 附件路径
	*/
	@Size(max=128)
	@Column(name="file_path")
	@Title("附件路径")
	private String filePath ;
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
	public String getMfrsId ()
	{
		return mfrsId ;
	}
	
	public void setMfrsId (String mfrsId )
	{
		this.mfrsId = mfrsId;
	}
	public String getIsMfrs ()
	{
		return isMfrs ;
	}
	
	public void setIsMfrs (String isMfrs )
	{
		this.isMfrs = isMfrs;
	}
	public String getChiefId ()
	{
		return chiefId ;
	}
	
	public void setChiefId (String chiefId )
	{
		this.chiefId = chiefId;
	}
	public String getSaleArea ()
	{
		return saleArea ;
	}
	
	public void setSaleArea (String saleArea )
	{
		this.saleArea = saleArea;
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
	public String getFilePath ()
	{
		return filePath ;
	}
	
	public void setFilePath (String filePath )
	{
		this.filePath = filePath;
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
		return "ProvJxsqs [" +" id=" +  id   +", provId=" +  provId   +", mfrsId=" +  mfrsId   +", isMfrs=" +  isMfrs   +", chiefId=" +  chiefId   +", saleArea=" +  saleArea   +", expdtBeginDate=" +  expdtBeginDate   +", expdtEndDate=" +  expdtEndDate   +", isValiding=" +  isValiding   +", filePath=" +  filePath   +", uxid=" +  uxid   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}