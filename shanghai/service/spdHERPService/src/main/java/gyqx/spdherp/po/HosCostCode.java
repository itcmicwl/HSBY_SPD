
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 医院收费代码对照
*/
@Table(name="hos_cost_code")
public class HosCostCode implements IBean
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
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 医院产品信息ID
	*/
	@Size(max=36)
	@Column(name="hos_goods_id")
	@Title("医院产品信息ID")
	private String hosGoodsId ;
	/**
	 * 收费代码
	*/
	@Size(max=36)
	@Column(name="cost_code")
	@Title("收费代码")
	private String costCode ;
	/**
	 * 收费名称
	*/
	@Size(max=100)
	@Column(name="cost_name")
	@Title("收费名称")
	private String costName ;
	/**
	 * 收费价格
	 * 10--区位，20--货位
	*/
	@Column(name="cost_price")
	@Title("收费价格")
	private java.math.BigDecimal costPrice ;
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
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getHosGoodsId ()
	{
		return hosGoodsId ;
	}
	
	public void setHosGoodsId (String hosGoodsId )
	{
		this.hosGoodsId = hosGoodsId;
	}
	public String getCostCode ()
	{
		return costCode ;
	}
	
	public void setCostCode (String costCode )
	{
		this.costCode = costCode;
	}
	public String getCostName ()
	{
		return costName ;
	}
	
	public void setCostName (String costName )
	{
		this.costName = costName;
	}
	public java.math.BigDecimal getCostPrice ()
	{
		return costPrice ;
	}
	
	public void setCostPrice (java.math.BigDecimal costPrice )
	{
		this.costPrice = costPrice;
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
		return "HosCostCode [" +" id=" +  id   +", hosId=" +  hosId   +", hosGoodsId=" +  hosGoodsId   +", costCode=" +  costCode   +", costName=" +  costName   +", costPrice=" +  costPrice   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}