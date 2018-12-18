
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 供应商供货目录
*/
@Table(name="prov_goods_hos")
public class ProvGoodsHos implements IBean
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
	 * 供应商产品ID
	*/
	@Size(max=36)
	@JoinColumn(name="prov_goodsid",table="prov_goods_info",referencedColumnName="id")
	@Title("供应商产品ID")
	private String provGoodsid ;
	/**
	 * 对应医院产品编码
	*/
	@Size(max=36)
	@Column(name="his_code")
	@Title("对应医院产品编码")
	private String hisCode ;
	/**
	 * 对应医院产品名称
	*/
	@Size(max=36)
	@Column(name="his_name")
	@Title("对应医院产品名称")
	private String hisName ;
	/**
	 * 对应医院产品单位
	*/
	@Size(max=36)
	@Column(name="his_unit")
	@Title("对应医院产品单位")
	private String hisUnit ;
	/**
	 * 采购价
	*/
	@Column(name="pur_price")
	@Title("采购价")
	private java.math.BigDecimal purPrice ;
	/**
	 * 配送单价
	*/
	@Column(name="sale_price")
	@Title("配送单价")
	private java.math.BigDecimal salePrice ;
	/**
	 * 单位比率
	*/
	@Column(name="unit_rate")
	@Title("单位比率")
	private java.math.BigDecimal unitRate ;
	/**
	 * 数据来源
	*/
	@Size(max=36)
	@Column(name="data_source")
	@Title("数据来源")
	private String dataSource ;
	/**
	 * 税率
	*/
	@Column(name="tax_rate")
	@Title("税率")
	private java.math.BigDecimal taxRate ;
	/**
	 * 标志
	*/
	@Size(max=1)
	@Column(name="flag")
	@Title("标志")
	private String flag ;
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
	public String getProvGoodsid ()
	{
		return provGoodsid ;
	}
	
	public void setProvGoodsid (String provGoodsid )
	{
		this.provGoodsid = provGoodsid;
	}
	public String getHisCode ()
	{
		return hisCode ;
	}
	
	public void setHisCode (String hisCode )
	{
		this.hisCode = hisCode;
	}
	public String getHisName ()
	{
		return hisName ;
	}
	
	public void setHisName (String hisName )
	{
		this.hisName = hisName;
	}
	public String getHisUnit ()
	{
		return hisUnit ;
	}
	
	public void setHisUnit (String hisUnit )
	{
		this.hisUnit = hisUnit;
	}
	public java.math.BigDecimal getPurPrice ()
	{
		return purPrice ;
	}
	
	public void setPurPrice (java.math.BigDecimal purPrice )
	{
		this.purPrice = purPrice;
	}
	public java.math.BigDecimal getSalePrice ()
	{
		return salePrice ;
	}
	
	public void setSalePrice (java.math.BigDecimal salePrice )
	{
		this.salePrice = salePrice;
	}
	public java.math.BigDecimal getUnitRate ()
	{
		return unitRate ;
	}
	
	public void setUnitRate (java.math.BigDecimal unitRate )
	{
		this.unitRate = unitRate;
	}
	public String getDataSource ()
	{
		return dataSource ;
	}
	
	public void setDataSource (String dataSource )
	{
		this.dataSource = dataSource;
	}
	public java.math.BigDecimal getTaxRate ()
	{
		return taxRate ;
	}
	
	public void setTaxRate (java.math.BigDecimal taxRate )
	{
		this.taxRate = taxRate;
	}
	public String getFlag ()
	{
		return flag ;
	}
	
	public void setFlag (String flag )
	{
		this.flag = flag;
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
		return "ProvGoodsHos [" +" id=" +  id   +", provId=" +  provId   +", hosId=" +  hosId   +", provGoodsid=" +  provGoodsid   +", hisCode=" +  hisCode   +", hisName=" +  hisName   +", hisUnit=" +  hisUnit   +", purPrice=" +  purPrice   +", salePrice=" +  salePrice   +", unitRate=" +  unitRate   +", dataSource=" +  dataSource   +", taxRate=" +  taxRate   +", flag=" +  flag   +", uxid=" +  uxid   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}