
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
 * 货位产品信息
*/
@Table(name="stoc_shelf_goods_info")
public class StocShelfGoodsInfo implements IBean
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
	 * 仓库ID
	*/
	@Size(max=36)
	@Column(name="stoc_id")
	@Title("仓库ID")
	private String stocId ;
	/**
	 * 货位编码
	*/
	@Size(max=36)
	@Column(name="shelf_code")
	@Title("货位编码")
	private String shelfCode ;
	/**
	 * 区位编码
	*/
	@Size(max=36)
	@Column(name="area_code")
	@Title("区位编码")
	private String areaCode ;
	/**
	 * 产品ID
	*/
	@Size(max=36)
	@Column(name="goods_id")
	@Title("产品ID")
	private String goodsId ;
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
	public String getStocId ()
	{
		return stocId ;
	}
	
	public void setStocId (String stocId )
	{
		this.stocId = stocId;
	}
	public String getShelfCode ()
	{
		return shelfCode ;
	}
	
	public void setShelfCode (String shelfCode )
	{
		this.shelfCode = shelfCode;
	}
	public String getAreaCode ()
	{
		return areaCode ;
	}
	
	public void setAreaCode (String areaCode )
	{
		this.areaCode = areaCode;
	}
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
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
		return "StocShelfGoodsInfo [" +" id=" +  id   +", hosId=" +  hosId   +", stocId=" +  stocId   +", shelfCode=" +  shelfCode   +", areaCode=" +  areaCode   +", goodsId=" +  goodsId   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}