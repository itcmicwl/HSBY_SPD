
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 集配商配送商商品信息
*/
@Table(name="prov_prov_goods")
public class ProvProvGoods implements IBean
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
	 * 集配商ID
	*/
	@Size(max=36)
	@Column(name="collector_id")
	@Title("集配商ID")
	private String collectorId ;
	/**
	 * 合作商ID
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("合作商ID")
	private String provId ;
	/**
	 * 供应商商品ID
	*/
	@Size(max=36)
	@Column(name="prov_goods_id")
	@Title("供应商商品ID")
	private String provGoodsId ;
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
	public String getCollectorId ()
	{
		return collectorId ;
	}
	
	public void setCollectorId (String collectorId )
	{
		this.collectorId = collectorId;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getProvGoodsId ()
	{
		return provGoodsId ;
	}
	
	public void setProvGoodsId (String provGoodsId )
	{
		this.provGoodsId = provGoodsId;
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
		return "ProvProvGoods [" +" id=" +  id   +", hosId=" +  hosId   +", collectorId=" +  collectorId   +", provId=" +  provId   +", provGoodsId=" +  provGoodsId   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}