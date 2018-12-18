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
 * 手术包订单明细表
*/
@Table(name="surgery_pkg_list")
public class SurgeryPkgList implements IBean
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
	@Column(name="sur_code")
	@Title("医院ID")
	private String surCode ;
	/**
	 * 产品ID
	*/
	@Size(max=36)
	@Column(name="goods_id")
	@Title("产品ID")
	private String goodsId ;
	/**
	 * 唯一码类型
	*/
	@Column(name="unique_kind")
	@Title("唯一码类型")
	private int uniqueKind ;
	/**
	 * 状态
	*/
	@Column(name="status")
	@Title("状态")
	private int status ;
	/**
	 * 商品数量
	*/
	@Column(name="qty")
	@Title("商品数量")
	private java.math.BigDecimal qty ;
	/**
	 * 单位
	*/
	@Size(max=36)
	@Column(name="unit")
	@Title("单位")
	private String unit ;
	/**
	 * 是否需要消毒
	*/
	@Column(name="should_sterilize")
	@Title("是否需要消毒")
	private int shouldSterilize ;
	/**
	 * 消毒人
	*/
	@Size(max=36)
	@Column(name="first_sterilizer")
	@Title("消毒人")
	private String firstSterilizer ;
	/**
	 * 消毒时间
	*/
	@Column(name="first_sterilize_date")
	@Title("消毒时间")
	private Date firstSterilizeDate ;
	/**
	 * 消毒效期
	*/
	@Column(name="sterilize_expdate")
	@Title("消毒效期")
	private Date sterilizeExpdate ;
	/**
	 * 退货消毒人
	*/
	@Size(max=36)
	@Column(name="sec_sterilizer")
	@Title("退货消毒人")
	private String secSterilizer ;
	/**
	 * 退货消毒时间
	*/
	@Column(name="sec_sterilize_date")
	@Title("退货消毒时间")
	private Date secSterilizeDate ;
	/**
	 * 备注
	*/
	@Size(max=256)
	@Column(name="remark")
	@Title("备注")
	private String remark ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private Date lastUpdateDatetime ;
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
	public String getSurCode ()
	{
		return surCode ;
	}
	
	public void setSurCode (String surCode )
	{
		this.surCode = surCode;
	}
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
	}
	public int getUniqueKind ()
	{
		return uniqueKind ;
	}
	
	public void setUniqueKind (int uniqueKind )
	{
		this.uniqueKind = uniqueKind;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public java.math.BigDecimal getQty ()
	{
		return qty ;
	}
	
	public void setQty (java.math.BigDecimal qty )
	{
		this.qty = qty;
	}
	public String getUnit ()
	{
		return unit ;
	}
	
	public void setUnit (String unit )
	{
		this.unit = unit;
	}
	public int getShouldSterilize ()
	{
		return shouldSterilize ;
	}
	
	public void setShouldSterilize (int shouldSterilize )
	{
		this.shouldSterilize = shouldSterilize;
	}
	public String getFirstSterilizer ()
	{
		return firstSterilizer ;
	}
	
	public void setFirstSterilizer (String firstSterilizer )
	{
		this.firstSterilizer = firstSterilizer;
	}
	public Date getFirstSterilizeDate ()
	{
		return firstSterilizeDate ;
	}
	
	public void setFirstSterilizeDate (Date firstSterilizeDate )
	{
		this.firstSterilizeDate = firstSterilizeDate;
	}
	public Date getSterilizeExpdate ()
	{
		return sterilizeExpdate ;
	}
	
	public void setSterilizeExpdate (Date sterilizeExpdate )
	{
		this.sterilizeExpdate = sterilizeExpdate;
	}
	public String getSecSterilizer ()
	{
		return secSterilizer ;
	}
	
	public void setSecSterilizer (String secSterilizer )
	{
		this.secSterilizer = secSterilizer;
	}
	public Date getSecSterilizeDate ()
	{
		return secSterilizeDate ;
	}
	
	public void setSecSterilizeDate (Date secSterilizeDate )
	{
		this.secSterilizeDate = secSterilizeDate;
	}
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
	}
	public Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (Date lastUpdateDatetime )
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
		return "SurgeryPkgList [" +" id=" +  id   +", surCode=" +  surCode   +", goodsId=" +  goodsId   +", uniqueKind=" +  uniqueKind   +", status=" +  status   +", qty=" +  qty   +", unit=" +  unit   +", shouldSterilize=" +  shouldSterilize   +", firstSterilizer=" +  firstSterilizer   +", firstSterilizeDate=" +  firstSterilizeDate   +", sterilizeExpdate=" +  sterilizeExpdate   +", secSterilizer=" +  secSterilizer   +", secSterilizeDate=" +  secSterilizeDate   +", remark=" +  remark   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}