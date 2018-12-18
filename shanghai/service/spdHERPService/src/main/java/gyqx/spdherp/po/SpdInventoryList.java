
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
 * 盘点单子表
*/
@Table(name="spd_inventory_list")
public class SpdInventoryList implements IBean
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
	 * 盘点单号
	*/
	@Size(max=36)
	@Column(name="pid")
	@Title("盘点单号")
	private String pid ;
	/**
	 * 商品名称
	*/
	@Size(max=500)
	@Column(name="goods_name")
	@Title("商品名称")
	private String goodsName ;
	/**
	 * 商品规格
	*/
	@Size(max=500)
	@Column(name="goods_gg")
	@Title("商品规格")
	private String goodsGg ;
	/**
	 * 商品编码
	*/
	@Size(max=36)
	@Column(name="erp_code")
	@Title("商品编码")
	private String erpCode ;
	/**
	 * 行号
	*/
	@Column(name="row_num")
	@Title("行号")
	private Integer rowNum ;
	/**
	 * 入库日期
	 * JDE入库日期
	*/
	@Column(name="instock_date")
	@Title("入库日期")
	private Date instockDate ;
	/**
	 * 单位成本
	*/
	@Column(name="unit_cost")
	@Title("单位成本")
	private java.math.BigDecimal unitCost ;
	/**
	 * 总成本
	*/
	@Column(name="total_cost")
	@Title("总成本")
	private java.math.BigDecimal totalCost ;
	/**
	 * 现有数量
	*/
	@Column(name="stock_qty")
	@Title("现有数量")
	private java.math.BigDecimal stockQty ;
	/**
	 * 库存可供量
	*/
	@Column(name="stock_supply_qty")
	@Title("库存可供量")
	private java.math.BigDecimal stockSupplyQty ;
	/**
	 * 生效日期
	*/
	@Column(name="effective_date")
	@Title("生效日期")
	private Date effectiveDate ;
	/**
	 * 失效日期
	*/
	@Column(name="expiration_date")
	@Title("失效日期")
	private Date expirationDate ;
	/**
	 * 灭菌批号
	*/
	@Size(max=128)
	@Column(name="sterilization_code")
	@Title("灭菌批号")
	private String sterilizationCode ;
	/**
	 * 生产批号
	*/
	@Size(max=36)
	@Column(name="batch_code")
	@Title("生产批号")
	private String batchCode ;
	/**
	 * 厂家ID
	*/
	@Size(max=36)
	@Column(name="mfrs_id")
	@Title("厂家ID")
	private String mfrsId ;
	/**
	 * 厂家名称
	*/
	@Size(max=500)
	@Column(name="mfrs_name")
	@Title("厂家名称")
	private String mfrsName ;
	/**
	 * 包装单位
	*/
	@Size(max=50)
	@Column(name="unit")
	@Title("包装单位")
	private String unit ;
	/**
	 * 货位
	*/
	@Size(max=50)
	@Column(name="goods_stock_location")
	@Title("货位")
	private String goodsStockLocation ;
	/**
	 * 盘点数量
	*/
	@Column(name="inventory_qty")
	@Title("盘点数量")
	private java.math.BigDecimal inventoryQty ;
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
	public String getPid ()
	{
		return pid ;
	}
	
	public void setPid (String pid )
	{
		this.pid = pid;
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
	public String getErpCode ()
	{
		return erpCode ;
	}
	
	public void setErpCode (String erpCode )
	{
		this.erpCode = erpCode;
	}
	public Integer getRowNum ()
	{
		return rowNum ;
	}
	
	public void setRowNum (Integer rowNum )
	{
		this.rowNum = rowNum;
	}
	public Date getInstockDate ()
	{
		return instockDate ;
	}
	
	public void setInstockDate (Date instockDate )
	{
		this.instockDate = instockDate;
	}
	public java.math.BigDecimal getUnitCost ()
	{
		return unitCost ;
	}
	
	public void setUnitCost (java.math.BigDecimal unitCost )
	{
		this.unitCost = unitCost;
	}
	public java.math.BigDecimal getTotalCost ()
	{
		return totalCost ;
	}
	
	public void setTotalCost (java.math.BigDecimal totalCost )
	{
		this.totalCost = totalCost;
	}
	public java.math.BigDecimal getStockQty ()
	{
		return stockQty ;
	}
	
	public void setStockQty (java.math.BigDecimal stockQty )
	{
		this.stockQty = stockQty;
	}
	public java.math.BigDecimal getStockSupplyQty ()
	{
		return stockSupplyQty ;
	}
	
	public void setStockSupplyQty (java.math.BigDecimal stockSupplyQty )
	{
		this.stockSupplyQty = stockSupplyQty;
	}
	public Date getEffectiveDate ()
	{
		return effectiveDate ;
	}
	
	public void setEffectiveDate (Date effectiveDate )
	{
		this.effectiveDate = effectiveDate;
	}
	public Date getExpirationDate ()
	{
		return expirationDate ;
	}
	
	public void setExpirationDate (Date expirationDate )
	{
		this.expirationDate = expirationDate;
	}
	public String getSterilizationCode ()
	{
		return sterilizationCode ;
	}
	
	public void setSterilizationCode (String sterilizationCode )
	{
		this.sterilizationCode = sterilizationCode;
	}
	public String getBatchCode ()
	{
		return batchCode ;
	}
	
	public void setBatchCode (String batchCode )
	{
		this.batchCode = batchCode;
	}
	public String getMfrsId ()
	{
		return mfrsId ;
	}
	
	public void setMfrsId (String mfrsId )
	{
		this.mfrsId = mfrsId;
	}
	public String getMfrsName ()
	{
		return mfrsName ;
	}
	
	public void setMfrsName (String mfrsName )
	{
		this.mfrsName = mfrsName;
	}
	public String getUnit ()
	{
		return unit ;
	}
	
	public void setUnit (String unit )
	{
		this.unit = unit;
	}
	public String getGoodsStockLocation ()
	{
		return goodsStockLocation ;
	}
	
	public void setGoodsStockLocation (String goodsStockLocation )
	{
		this.goodsStockLocation = goodsStockLocation;
	}
	public java.math.BigDecimal getInventoryQty ()
	{
		return inventoryQty ;
	}
	
	public void setInventoryQty (java.math.BigDecimal inventoryQty )
	{
		this.inventoryQty = inventoryQty;
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
		return "SpdInventoryList [" +" id=" +  id   +", pid=" +  pid   +", goodsName=" +  goodsName   +", goodsGg=" +  goodsGg   +", erpCode=" +  erpCode   +", rowNum=" +  rowNum   +", instockDate=" +  instockDate   +", unitCost=" +  unitCost   +", totalCost=" +  totalCost   +", stockQty=" +  stockQty   +", stockSupplyQty=" +  stockSupplyQty   +", effectiveDate=" +  effectiveDate   +", expirationDate=" +  expirationDate   +", sterilizationCode=" +  sterilizationCode   +", batchCode=" +  batchCode   +", mfrsId=" +  mfrsId   +", mfrsName=" +  mfrsName   +", unit=" +  unit   +", goodsStockLocation=" +  goodsStockLocation   +", inventoryQty=" +  inventoryQty   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}