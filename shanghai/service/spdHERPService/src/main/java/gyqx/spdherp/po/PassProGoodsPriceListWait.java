
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
 * 商品配送价明细接口待同步
*/
@Table(name="pass_pro_goods_price_list_wait")
public class PassProGoodsPriceListWait implements IBean
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
	 * PID
	*/
	@Size(max=36)
	@JoinColumn(name="p_id",table="pro_goods_price_wait",referencedColumnName="id")
	@Title("PID")
	private String pId ;
	/**
	 * 医疗机构ERP编号
	*/
	@Size(max=50)
	@Column(name="hos_erp_code")
	@Title("医疗机构ERP编号")
	private String hosErpCode ;
	/**
	 * 配送机构ERP名称
	*/
	@Size(max=100)
	@Column(name="hos_name")
	@Title("配送机构ERP名称")
	private String hosName ;
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
	public String getPId ()
	{
		return pId ;
	}
	
	public void setPId (String pId )
	{
		this.pId = pId;
	}
	public String getHosErpCode ()
	{
		return hosErpCode ;
	}
	
	public void setHosErpCode (String hosErpCode )
	{
		this.hosErpCode = hosErpCode;
	}
	public String getHosName ()
	{
		return hosName ;
	}
	
	public void setHosName (String hosName )
	{
		this.hosName = hosName;
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
		return "PassProGoodsPriceListWait [" +" id=" +  id   +", pId=" +  pId   +", hosErpCode=" +  hosErpCode   +", hosName=" +  hosName   +", distrPrice=" +  distrPrice   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}