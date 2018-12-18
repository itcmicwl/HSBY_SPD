
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 库存占用信息
*/
@Table(name="hos_taking_stock")
public class HosTakingStock implements IBean
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
	 * 库存表占用ID
	 */
	@Id
	@Size(max=36)
	@Column(name="stockpile_id")
	@Title("库存表占用ID")
	private String stockpileId ;
	/**
	 * 库房ID
	*/
	@Size(max=36)
	@Column(name="stoc_id")
	@Title("库房ID")
	private String stocId ;
	/**
	 * 占用类型
	*/
	@Size(max=36)
	@Column(name="kind")
	@Title("占用类型")
	private String kind ;
	/**
	 * 占用单号
	*/
	@Size(max=100)
	@Column(name="bill_id")
	@Title("占用单号")
	private String billId ;
	/**
	 * 占用数量
	 * 10--区位，20--货位
	*/
	@Column(name="qty")
	@Title("占用数量")
	private java.math.BigDecimal qty ;
	/**
	 * 单据行号
	*/
	@Column(name="bill_rownum")
	@Title("单据行号")
	private int billRownum ;
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
	/**
	 * 手术包ID
	 */
	@Column(name="sur_pkg_id")
	@Title("手术包ID")
	private String surPkgId ;


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
	public String getStocId ()
	{
		return stocId ;
	}
	
	public void setStocId (String stocId )
	{
		this.stocId = stocId;
	}
	public String getKind ()
	{
		return kind ;
	}
	
	public void setKind (String kind )
	{
		this.kind = kind;
	}
	public String getBillId ()
	{
		return billId ;
	}
	
	public void setBillId (String billId )
	{
		this.billId = billId;
	}
	public java.math.BigDecimal getQty ()
	{
		return qty ;
	}
	
	public void setQty (java.math.BigDecimal qty )
	{
		this.qty = qty;
	}
	public int getBillRownum ()
	{
		return billRownum ;
	}
	
	public void setBillRownum (int billRownum )
	{
		this.billRownum = billRownum;
	}
	public java.util.Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (java.util.Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}

    public String getStockpileId() {
        return stockpileId;
    }

    public void setStockpileId(String stockpileId) {
        this.stockpileId = stockpileId;
    }

    public int getVersion ()
	{
		return version ;
	}
	
	public void setVersion (int version )
	{
		this.version = version;
	}

	public String getSurPkgId() {
		return surPkgId;
	}

	public void setSurPkgId(String surPkgId) {
		this.surPkgId = surPkgId;
	}

	@Override
	public String toString() {
		return "HosTakingStock{" +
				"id='" + id + '\'' +
				", hosId='" + hosId + '\'' +
				", hosGoodsId='" + hosGoodsId + '\'' +
				", stockpileId='" + stockpileId + '\'' +
				", stocId='" + stocId + '\'' +
				", kind='" + kind + '\'' +
				", billId='" + billId + '\'' +
				", qty=" + qty +
				", billRownum=" + billRownum +
				", lastUpdateDatetime=" + lastUpdateDatetime +
				", version=" + version +
				", surPkgId='" + surPkgId + '\'' +
				'}';
	}

}