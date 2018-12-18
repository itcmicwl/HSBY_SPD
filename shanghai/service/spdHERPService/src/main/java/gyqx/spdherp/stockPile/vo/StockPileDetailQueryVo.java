
package gyqx.spdherp.stockPile.vo;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.Column;
import common.db.meta.Title;

public class StockPileDetailQueryVo 
{
	/**
	 * 医院ID
	 */
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 仓库ID
	 */
	@Column(name="stoc_id")
	@Title("仓库ID")
	private String stocId ;
	/**
	 * 货位编码
	 */
	@Column(name="shelf_code")
	@Title("货位编码")
	private String shelfCode ;
	/**
	 * 医院产品ID
	 * 10--区位，20--货位
	 */
	@Column(name="hos_goods_id")
	@Title("医院产品ID")
	private String hosGoodsId ;
	/**
	 * 数量
	 */
	@Column(name="qty")
	@Title("数量")
	private java.math.BigDecimal qty ;
	/**
	 * 单价
	 */
	@Column(name="price")
	@Title("单价")
	private java.math.BigDecimal price ;

	public BigDecimal getJe() {
		return je;
	}

	public void setJe(BigDecimal je) {
		this.je = je;
	}

	private java.math.BigDecimal je ;
	/**
	 * 批号
	 */
	@Column(name="batch_no")
	@Title("批号")
	private String batchNo ;
	/**
	 * 批次
	 */
	@Column(name="batch_id")
	@Title("批次")
	private String batchId ;
	/**
	 * 效期
	 */
	@Column(name="expdt_end_date")
	@Title("效期")
	private Date expdtEndDate ;
	/**
	 * 灭菌批号
	 */
	@Column(name="sterilization_code")
	@Title("灭菌批号")
	private String sterilizationCode ;
	/**
	 * 灭菌效期
	 */
	@Column(name="sterilization_end_date")
	@Title("灭菌效期")
	private Date sterilizationEndDate ;
	/**
	 * 灭菌日期
	 */
	@Column(name="sterilization_date")
	@Title("灭菌日期")
	private Date sterilizationDate ;
	/**
	 * 入库时间
	 */
	@Column(name="in_time")
	@Title("入库时间")
	private Date inTime ;
	/**
	 * 条码
	 */
	@Column(name="barcode")
	@Title("条码")
	private String barcode ;
	/**
	 * ID
	 */
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 数据版本
	 */
	@Column(name="version")
	@Title("数据版本")
	private Integer version ;
	/**
	 * 名称
	 */
	@Column(name="goods_name")
	@Title("名称")
	private String goodsName ;
	/**
	 * 编号
	 */
	@Column(name="code")
	@Title("编号")
	private String code ;
	/**
	 * 规格型号
	 */
	@Column(name="goods_gg")
	@Title("规格型号")
	private String goodsGg ;
	/**
	 * 产地
	 */
	@Column(name="made")
	@Title("产地")
	private String made ;
	/**
	 * 单位
	 */
	@Column(name="unit")
	@Title("单位")
	private String unit ;
	/**
	 * 包装
	 */
	@Column(name="packeage")
	@Title("包装")
	private java.math.BigDecimal packeage ;
	/**
	 * 类别属性
	 */
	@Column(name="lbsx")
	@Title("类别属性")
	private String lbsx ;
	/**
	 * 厂家出厂编码
	 */
	@Column(name="mfrs_code")
	@Title("厂家出厂编码")
	private String mfrsCode ;
	/**
	 * 统一码
	 */
	@Column(name="master_code")
	@Title("统一码")
	private String masterCode ;
	/**
	 * 供应商ID
	 */
	@Column(name="prov_id")
	@Title("供应商ID")
	private String provId ;
	/**
	 * 生产厂商
	 */
	@Column(name="mfrs_id")
	@Title("生产厂商")
	private String mfrsId ;
	/**
	 * 公司/医院名称
	 */
	@Column(name="cname")
	@Title("公司/医院名称")
	private String ProvName ;
	/**
	 * 厂家名称
	 */
	@Column(name="mfrs_name")
	@Title("厂家名称")
	private String mfrsName ;

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
	public String getHosGoodsId ()
	{
		return hosGoodsId ;
	}

	public void setHosGoodsId (String hosGoodsId )
	{
		this.hosGoodsId = hosGoodsId;
	}
	public java.math.BigDecimal getQty ()
	{
		return qty ;
	}

	public void setQty (java.math.BigDecimal qty )
	{
		this.qty = qty;
	}
	public java.math.BigDecimal getPrice ()
	{
		return price ;
	}

	public void setPrice (java.math.BigDecimal price )
	{
		this.price = price;
	}
	public String getBatchNo ()
	{
		return batchNo ;
	}

	public void setBatchNo (String batchNo )
	{
		this.batchNo = batchNo;
	}
	public String getBatchId ()
	{
		return batchId ;
	}

	public void setBatchId (String batchId )
	{
		this.batchId = batchId;
	}
	public Date getExpdtEndDate ()
	{
		return expdtEndDate ;
	}

	public void setExpdtEndDate (Date expdtEndDate )
	{
		this.expdtEndDate = expdtEndDate;
	}
	public String getSterilizationCode ()
	{
		return sterilizationCode ;
	}

	public void setSterilizationCode (String sterilizationCode )
	{
		this.sterilizationCode = sterilizationCode;
	}
	public Date getSterilizationEndDate ()
	{
		return sterilizationEndDate ;
	}

	public void setSterilizationEndDate (Date sterilizationEndDate )
	{
		this.sterilizationEndDate = sterilizationEndDate;
	}
	public Date getSterilizationDate ()
	{
		return sterilizationDate ;
	}

	public void setSterilizationDate (Date sterilizationDate )
	{
		this.sterilizationDate = sterilizationDate;
	}
	public Date getInTime ()
	{
		return inTime ;
	}

	public void setInTime (Date inTime )
	{
		this.inTime = inTime;
	}
	public String getBarcode ()
	{
		return barcode ;
	}

	public void setBarcode (String barcode )
	{
		this.barcode = barcode;
	}
	public String getId ()
	{
		return id ;
	}

	public void setId (String id )
	{
		this.id = id;
	}
	public Integer getVersion ()
	{
		return version ;
	}

	public void setVersion (Integer version )
	{
		this.version = version;
	}
	public String getGoodsName ()
	{
		return goodsName ;
	}

	public void setGoodsName (String goodsName )
	{
		this.goodsName = goodsName;
	}
	public String getCode ()
	{
		return code ;
	}

	public void setCode (String code )
	{
		this.code = code;
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
	public String getUnit ()
	{
		return unit ;
	}

	public void setUnit (String unit )
	{
		this.unit = unit;
	}
	public java.math.BigDecimal getPackeage ()
	{
		return packeage ;
	}

	public void setPackeage (java.math.BigDecimal packeage )
	{
		this.packeage = packeage;
	}
	public String getLbsx ()
	{
		return lbsx ;
	}

	public void setLbsx (String lbsx )
	{
		this.lbsx = lbsx;
	}
	public String getMfrsCode ()
	{
		return mfrsCode ;
	}

	public void setMfrsCode (String mfrsCode )
	{
		this.mfrsCode = mfrsCode;
	}
	public String getMasterCode ()
	{
		return masterCode ;
	}

	public void setMasterCode (String masterCode )
	{
		this.masterCode = masterCode;
	}
	public String getProvId ()
	{
		return provId ;
	}

	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getMfrsId ()
	{
		return mfrsId ;
	}

	public void setMfrsId (String mfrsId )
	{
		this.mfrsId = mfrsId;
	}
	public String getProvName ()
	{
		return ProvName ;
	}

	public void setProvName (String cname )
	{
		this.ProvName = cname;
	}
	public String getMfrsName ()
	{
		return mfrsName ;
	}

	public void setMfrsName (String mfrsName )
	{
		this.mfrsName = mfrsName;
	}
	@Override
	public String toString() {
		return "StockPileDetailQuery ["  +" hosId=" +  hosId    +", stocId=" +  stocId    +", shelfCode=" +  shelfCode    +", hosGoodsId=" +  hosGoodsId    +", qty=" +  qty    +", price=" +  price    +", batchNo=" +  batchNo    +", batchId=" +  batchId    +", expdtEndDate=" +  expdtEndDate    +", sterilizationCode=" +  sterilizationCode    +", sterilizationEndDate=" +  sterilizationEndDate    +", sterilizationDate=" +  sterilizationDate    +", inTime=" +  inTime    +", barcode=" +  barcode    +", id=" +  id    +", version=" +  version    +", goodsName=" +  goodsName    +", code=" +  code    +", goodsGg=" +  goodsGg    +", made=" +  made    +", unit=" +  unit    +", packeage=" +  packeage    +", lbsx=" +  lbsx    +", mfrsCode=" +  mfrsCode    +", masterCode=" +  masterCode    +", provId=" +  provId    +", mfrsId=" +  mfrsId    +", cname=" +  ProvName    +", mfrsName=" +  mfrsName
				+ "]";
	}

}