
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 定数包信息主表
*/
@Table(name="hos_package_info")
public class HosPackageInfo implements IBean
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
	 * 包号
	*/
	@Size(max=50)
	@Column(name="package_id")
	@Title("包号")
	private String packageId ;
	/**
	 * 行号
	*/
	@Column(name="row_id")
	@Title("行号")
	private int rowId ;
	/**
	 * 配送机构
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("配送机构")
	private String provId ;
	/**
	 * 配送机构名称
	*/
	@Size(max=36)
	@Column(name="prov_name")
	@Title("配送机构名称")
	private String provName ;
	/**
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 医院名称
	*/
	@Size(max=200)
	@Column(name="hos_name")
	@Title("医院名称")
	private String hosName ;
	/**
	 * 产品ID
	*/
	@Size(max=36)
	@Column(name="goods_id")
	@Title("产品ID")
	private String goodsId ;
	/**
	 * 产品名称
	*/
	@Size(max=36)
	@Column(name="goods_name")
	@Title("产品名称")
	private String goodsName ;
	/**
	 * 规格
	*/
	@Size(max=36)
	@Column(name="goods_gg")
	@Title("规格")
	private String goodsGg ;
	/**
	 * 生产厂商ID
	*/
	@Size(max=36)
	@Column(name="mfrs_id")
	@Title("生产厂商ID")
	private String mfrsId ;
	/**
	 * 生产厂商名称
	*/
	@Size(max=200)
	@Column(name="mfrs_name")
	@Title("生产厂商名称")
	private String mfrsName ;
	/**
	 * 产地
	*/
	@Size(max=36)
	@Column(name="made")
	@Title("产地")
	private String made ;
	/**
	 * 包装数量
	*/
	@Column(name="packet_code")
	@Title("包装数量")
	private java.math.BigDecimal packetCode ;
	/**
	 * 批号
	*/
	@Size(max=36)
	@Column(name="batch_code")
	@Title("批号")
	private String batchCode ;
	/**
	 * 灭菌日期
	*/
	@Column(name="sterilization_date")
	@Title("灭菌日期")
	private java.util.Date sterilizationDate ;
	/**
	 * 灭菌批号
	*/
	@Size(max=36)
	@Column(name="sterilization_code")
	@Title("灭菌批号")
	private String sterilizationCode ;
	/**
	 * 灭菌效期
	*/
	@Column(name="sterilization_end_date")
	@Title("灭菌效期")
	private java.util.Date sterilizationEndDate ;
	/**
	 * 有效期至
	*/
	@Column(name="expdt_end_date")
	@Title("有效期至")
	private java.util.Date expdtEndDate ;
	/**
	 * 单位
	*/
	@Size(max=36)
	@Column(name="unit")
	@Title("单位")
	private String unit ;
	/**
	 * 数量
	*/
	@Column(name="qty")
	@Title("数量")
	private java.math.BigDecimal qty ;
	/**
	 * 整包数量数
	*/
	@Column(name="packet_qty")
	@Title("整包数量数")
	private java.math.BigDecimal packetQty ;
	/**
	 * 单号
	*/
	@Size(max=36)
	@Column(name="bill_id")
	@Title("单号")
	private String billId ;

	/**
	 * 状态
	 */
	@Column(name="status")
	@Title("状态")
	private Integer status ;

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
	public String getPackageId ()
	{
		return packageId ;
	}
	
	public void setPackageId (String packageId )
	{
		this.packageId = packageId;
	}
	public int getRowId ()
	{
		return rowId ;
	}
	
	public void setRowId (int rowId )
	{
		this.rowId = rowId;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getProvName ()
	{
		return provName ;
	}
	
	public void setProvName (String provName )
	{
		this.provName = provName;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getHosName ()
	{
		return hosName ;
	}
	
	public void setHosName (String hosName )
	{
		this.hosName = hosName;
	}
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
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
	public String getMade ()
	{
		return made ;
	}
	
	public void setMade (String made )
	{
		this.made = made;
	}
	public java.math.BigDecimal getPacketCode ()
	{
		return packetCode ;
	}
	
	public void setPacketCode (java.math.BigDecimal packetCode )
	{
		this.packetCode = packetCode;
	}
	public String getBatchCode ()
	{
		return batchCode ;
	}
	
	public void setBatchCode (String batchCode )
	{
		this.batchCode = batchCode;
	}
	public java.util.Date getSterilizationDate ()
	{
		return sterilizationDate ;
	}
	
	public void setSterilizationDate (java.util.Date sterilizationDate )
	{
		this.sterilizationDate = sterilizationDate;
	}
	public String getSterilizationCode ()
	{
		return sterilizationCode ;
	}
	
	public void setSterilizationCode (String sterilizationCode )
	{
		this.sterilizationCode = sterilizationCode;
	}
	public java.util.Date getSterilizationEndDate ()
	{
		return sterilizationEndDate ;
	}
	
	public void setSterilizationEndDate (java.util.Date sterilizationEndDate )
	{
		this.sterilizationEndDate = sterilizationEndDate;
	}
	public java.util.Date getExpdtEndDate ()
	{
		return expdtEndDate ;
	}
	
	public void setExpdtEndDate (java.util.Date expdtEndDate )
	{
		this.expdtEndDate = expdtEndDate;
	}
	public String getUnit ()
	{
		return unit ;
	}
	
	public void setUnit (String unit )
	{
		this.unit = unit;
	}
	public java.math.BigDecimal getQty ()
	{
		return qty ;
	}
	
	public void setQty (java.math.BigDecimal qty )
	{
		this.qty = qty;
	}
	public java.math.BigDecimal getPacketQty ()
	{
		return packetQty ;
	}
	
	public void setPacketQty (java.math.BigDecimal packetQty )
	{
		this.packetQty = packetQty;
	}
	public String getBillId ()
	{
		return billId ;
	}
	
	public void setBillId (String billId )
	{
		this.billId = billId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "HosPackageInfo [" +" id=" +  id   +", packageId=" +  packageId   +", rowId=" +  rowId   +", provId=" +  provId   +", provName=" +  provName   +", hosId=" +  hosId   +", hosName=" +  hosName   +", goodsId=" +  goodsId   +", goodsName=" +  goodsName   +", goodsGg=" +  goodsGg   +", mfrsId=" +  mfrsId   +", mfrsName=" +  mfrsName   +", made=" +  made   +", packetCode=" +  packetCode   +", batchCode=" +  batchCode   +", sterilizationDate=" +  sterilizationDate   +", sterilizationCode=" +  sterilizationCode   +", sterilizationEndDate=" +  sterilizationEndDate   +", expdtEndDate=" +  expdtEndDate   +", unit=" +  unit   +", qty=" +  qty   +", packetQty=" +  packetQty   +", billId=" +  billId   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}