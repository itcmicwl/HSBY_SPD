
package gyqx.spdherp.po;

import java.util.*;
import common.db.IBean;
import common.db.meta.Title;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.*;
/**
 * 商品配送价主表接口待同步
*/
@Table(name="pass_pro_goods_price_wait")
public class PassProGoodsPriceWait implements IBean
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
	 * SPD系统标识
	 * SPD系统标识
	*/
	@Size(max=50)
	@Column(name="spd_code")
	@Title("SPD系统标识")
	private String spdCode ;
	/**
	 * 配送机构系统标识
	 * ERP系统标识
	*/
	@Size(max=50)
	@Column(name="prov_sys_code")
	@Title("配送机构系统标识")
	private String provSysCode ;
	/**
	 * 配送机构ERP编号
	*/
	@Size(max=50)
	@Column(name="prov_erp_code")
	@Title("配送机构ERP编号")
	private String provErpCode ;
	/**
	 * 配送机构名称
	*/
	@Size(max=100)
	@Column(name="prov_name")
	@Title("配送机构名称")
	private String provName ;
	/**
	 * ERP商品编号
	*/
	@Size(max=50)
	@Column(name="prov_goods_erp_code")
	@Title("ERP商品编号")
	private String provGoodsErpCode ;
	/**
	 * ERP商品名称
	*/
	@Size(max=100)
	@Column(name="prov_goods_erp_name")
	@Title("ERP商品名称")
	private String provGoodsErpName ;
	/**
	 * 配送价
	*/
	@Column(name="distr_price")
	@Title("配送价")
	private java.math.BigDecimal distrPrice ;
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
	public String getSpdCode ()
	{
		return spdCode ;
	}
	
	public void setSpdCode (String spdCode )
	{
		this.spdCode = spdCode;
	}
	public String getProvSysCode ()
	{
		return provSysCode ;
	}
	
	public void setProvSysCode (String provSysCode )
	{
		this.provSysCode = provSysCode;
	}
	public String getProvErpCode ()
	{
		return provErpCode ;
	}
	
	public void setProvErpCode (String provErpCode )
	{
		this.provErpCode = provErpCode;
	}
	public String getProvName ()
	{
		return provName ;
	}
	
	public void setProvName (String provName )
	{
		this.provName = provName;
	}
	public String getProvGoodsErpCode ()
	{
		return provGoodsErpCode ;
	}
	
	public void setProvGoodsErpCode (String provGoodsErpCode )
	{
		this.provGoodsErpCode = provGoodsErpCode;
	}
	public String getProvGoodsErpName ()
	{
		return provGoodsErpName ;
	}
	
	public void setProvGoodsErpName (String provGoodsErpName )
	{
		this.provGoodsErpName = provGoodsErpName;
	}
	public java.math.BigDecimal getDistrPrice ()
	{
		return distrPrice ;
	}
	
	public void setDistrPrice (java.math.BigDecimal distrPrice )
	{
		this.distrPrice = distrPrice;
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
		return "PassProGoodsPriceWait [" +" id=" +  id   +", spdCode=" +  spdCode   +", provSysCode=" +  provSysCode   +", provErpCode=" +  provErpCode   +", provName=" +  provName   +", provGoodsErpCode=" +  provGoodsErpCode   +", provGoodsErpName=" +  provGoodsErpName   +", distrPrice=" +  distrPrice   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}