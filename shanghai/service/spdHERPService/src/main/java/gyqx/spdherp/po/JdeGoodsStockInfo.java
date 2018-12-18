
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
 * JDE商品货位信息
*/
@Table(name="jde_goods_stock_info")
public class JdeGoodsStockInfo implements IBean
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
	 * (分布场所)仓库编码
	*/
	@Size(max=36)
	@Column(name="stock_code")
	@Title("(分布场所)仓库编码")
	private String stockCode ;
	/**
	 * 仓库名称
	*/
	@Size(max=100)
	@Column(name="stock_name")
	@Title("仓库名称")
	private String stockName ;
	/**
	 * 货位
	*/
	@Size(max=50)
	@Column(name="goods_stock_location")
	@Title("货位")
	private String goodsStockLocation ;
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
	 * 批次号序列
	*/
	@Size(max=36)
	@Column(name="prov_batch_id")
	@Title("批次号序列")
	private String provBatchId ;
	/**
	 * 生产批号
	*/
	@Size(max=36)
	@Column(name="batch_code")
	@Title("生产批号")
	private String batchCode ;
	/**
	 * 商品编码
	*/
	@Size(max=36)
	@Column(name="erp_code")
	@Title("商品编码")
	private String erpCode ;
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
	 * 入库日期
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
	 * 产品线
	*/
	@Size(max=100)
	@Column(name="product_line")
	@Title("产品线")
	private String productLine ;
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
	 * 供应商erp编码
	*/
	@Size(max=36)
	@Column(name="company_erp_code")
	@Title("供应商erp编码")
	private String companyErpCode ;
	/**
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
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
	public String getStockCode ()
	{
		return stockCode ;
	}
	
	public void setStockCode (String stockCode )
	{
		this.stockCode = stockCode;
	}
	public String getStockName ()
	{
		return stockName ;
	}
	
	public void setStockName (String stockName )
	{
		this.stockName = stockName;
	}
	public String getGoodsStockLocation ()
	{
		return goodsStockLocation ;
	}
	
	public void setGoodsStockLocation (String goodsStockLocation )
	{
		this.goodsStockLocation = goodsStockLocation;
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
	public String getProvBatchId ()
	{
		return provBatchId ;
	}
	
	public void setProvBatchId (String provBatchId )
	{
		this.provBatchId = provBatchId;
	}
	public String getBatchCode ()
	{
		return batchCode ;
	}
	
	public void setBatchCode (String batchCode )
	{
		this.batchCode = batchCode;
	}
	public String getErpCode ()
	{
		return erpCode ;
	}
	
	public void setErpCode (String erpCode )
	{
		this.erpCode = erpCode;
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
	public String getProductLine ()
	{
		return productLine ;
	}
	
	public void setProductLine (String productLine )
	{
		this.productLine = productLine;
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
	public String getCompanyErpCode ()
	{
		return companyErpCode ;
	}
	
	public void setCompanyErpCode (String companyErpCode )
	{
		this.companyErpCode = companyErpCode;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
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
		return "JdeGoodsStockInfo [" +" id=" +  id   +", stockCode=" +  stockCode   +", stockName=" +  stockName   +", goodsStockLocation=" +  goodsStockLocation   +", stockQty=" +  stockQty   +", stockSupplyQty=" +  stockSupplyQty   +", provBatchId=" +  provBatchId   +", batchCode=" +  batchCode   +", erpCode=" +  erpCode   +", goodsName=" +  goodsName   +", goodsGg=" +  goodsGg   +", instockDate=" +  instockDate   +", unitCost=" +  unitCost   +", totalCost=" +  totalCost   +", productLine=" +  productLine   +", effectiveDate=" +  effectiveDate   +", expirationDate=" +  expirationDate   +", sterilizationCode=" +  sterilizationCode   +", companyErpCode=" +  companyErpCode   +", hosId=" +  hosId   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}