
package gyqx.spdherp.productsInfos.vo;

import java.util.*;
import javax.persistence.Column;
import common.db.meta.Title;

public class HosCostCodeVo 
{
	/**
	 * 名称
	*/
	@Column(name="goodsName")
	@Title("名称")
	private String goodsName ;
	/**
	 * 规格型号
	*/
	@Column(name="goodsGg")
	@Title("规格型号")
	private String goodsGg ;
	/**
	 * 产地
	*/
	@Column(name="made")
	@Title("产地")
	private String made ;
	/**
	 * 生产厂商
	*/
	@Column(name="mfrsId")
	@Title("生产厂商")
	private String mfrsId ;
	/**
	 * 类别属性
	*/
	@Column(name="lbsx")
	@Title("类别属性")
	private String lbsx ;
	/**
	 * 单位
	*/
	@Column(name="unit")
	@Title("单位")
	private String unit ;
	/**
	 * 厂家名称
	*/
	@Column(name="mfrsName")
	@Title("厂家名称")
	private String mfrsName ;
	/**
	 * ID
	*/
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 医院ID
	*/
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 医院产品信息ID
	*/
	@Column(name="hos_goods_id")
	@Title("医院产品信息ID")
	private String hosGoodsId ;
	/**
	 * 收费代码
	*/
	@Column(name="cost_code")
	@Title("收费代码")
	private String costCode ;
	/**
	 * 收费名称
	*/
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
	 * 数据版本
	*/
	@Column(name="version")
	@Title("数据版本")
	private Integer version ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	public java.util.Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(java.util.Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public String getGoodsName ()
	{
		return goodsName ;
	}
	
	public void setGoodsName (String goodsName )
	{
		this.goodsName = goodsName;
	}
	public String getGoodsGg ()
	{
		return goodsGg ;
	}
	
	public void setGoodsGg (String goodsGg )
	{
		this.goodsGg = goodsGg;
	}
	public String getMade ()
	{
		return made ;
	}
	
	public void setMade (String made )
	{
		this.made = made;
	}
	public String getMfrsId ()
	{
		return mfrsId ;
	}
	
	public void setMfrsId (String mfrsId )
	{
		this.mfrsId = mfrsId;
	}
	public String getLbsx ()
	{
		return lbsx ;
	}
	
	public void setLbsx (String lbsx )
	{
		this.lbsx = lbsx;
	}
	public String getMfrsName ()
	{
		return mfrsName ;
	}
	
	public void setMfrsName (String mfrsName )
	{
		this.mfrsName = mfrsName;
	}
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
	public Integer getVersion ()
	{
		return version ;
	}
	
	public void setVersion (Integer version )
	{
		this.version = version;
	}
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "HosCostCodeVo ["  +" goodsName=" +  goodsName    +", goodsGg=" +  goodsGg    +", made=" +  made    +", mfrsId=" +  mfrsId    +", lbsx=" +  lbsx    +", mfrsName=" +  mfrsName    +", unit=" +  unit    +", id=" +  id    +", hosId=" +  hosId    +", hosGoodsId=" +  hosGoodsId    +", costCode=" +  costCode    +", costName=" +  costName    +", costPrice=" +  costPrice    +", version=" +  version +", lastUpdateDatetime=" +  lastUpdateDatetime  
			 + "]";
	}

}