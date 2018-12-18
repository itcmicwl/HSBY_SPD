
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
 * 科室产品模板对应产品
*/
@Table(name="goods_pur_template_goods")
public class GoodsPurTemplateGoods implements IBean
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
	 * 模板ID
	*/
	@Size(max=36)
	@JoinColumn(name="temp_id",table="goods_pur_template",referencedColumnName="id")
	@Title("模板ID")
	private String tempId ;
	/**
	 * 产品ID
	 * 只有在模板末级才能添加产品
	*/
	@Size(max=36)
	@Column(name="goods_id")
	@Title("产品ID")
	private String goodsId ;
	/**
	 * 产品数量
	*/
	@Column(name="goods_num")
	@Title("产品数量")
	private java.math.BigDecimal goodsNum ;
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
	public String getTempId ()
	{
		return tempId ;
	}
	
	public void setTempId (String tempId )
	{
		this.tempId = tempId;
	}
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
	}
	public java.math.BigDecimal getGoodsNum ()
	{
		return goodsNum ;
	}
	
	public void setGoodsNum (java.math.BigDecimal goodsNum )
	{
		this.goodsNum = goodsNum;
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
		return "GoodsPurTemplateGoods [" +" id=" +  id   +", tempId=" +  tempId   +", goodsId=" +  goodsId   +", goodsNum=" +  goodsNum   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}