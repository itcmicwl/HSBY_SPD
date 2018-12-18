
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
 * 试剂产品信息
*/
@Table(name="medical_goods_info")
public class MedicalGoodsInfo implements IBean
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
	 * 产品ID
	*/
	@Size(max=36)
	@Column(name="goods_id")
	@Title("产品ID")
	private String goodsId ;
	/**
	 * 温度上限
	*/
	@Column(name="temperature_upper")
	@Title("温度上限")
	private java.math.BigDecimal temperatureUpper ;
	/**
	 * 温度下限
	*/
	@Column(name="temperature_lower")
	@Title("温度下限")
	private java.math.BigDecimal temperatureLower ;
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
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
	}
	public java.math.BigDecimal getTemperatureUpper ()
	{
		return temperatureUpper ;
	}
	
	public void setTemperatureUpper (java.math.BigDecimal temperatureUpper )
	{
		this.temperatureUpper = temperatureUpper;
	}
	public java.math.BigDecimal getTemperatureLower ()
	{
		return temperatureLower ;
	}
	
	public void setTemperatureLower (java.math.BigDecimal temperatureLower )
	{
		this.temperatureLower = temperatureLower;
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
		return "MedicalGoodsInfo [" +" id=" +  id   +", hosId=" +  hosId   +", goodsId=" +  goodsId   +", temperatureUpper=" +  temperatureUpper   +", temperatureLower=" +  temperatureLower   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}