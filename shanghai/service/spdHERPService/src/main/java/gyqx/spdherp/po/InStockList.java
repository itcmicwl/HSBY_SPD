
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 入库单明细
*/
@Table(name="in_stock_list")
public class InStockList implements IBean
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
	 * PID
	*/
	@Size(max=36)
	@Column(name="pid")
	@Title("PID")
	private String pid ;
	/**
	 * 单号
	*/
	@Size(max=36)
	@Column(name="bill_id")
	@Title("单号")
	private String billId ;

	@Size(max=36)
	@Column(name="out_bill_id")
	@Title("来源单号")
	private String outBillId;
	/**
	 * 出库单行号
	*/
	@Column(name="out_bill_row")
	@Title("出库单行号")
	private int outBillRow ;
	/**
	 * 入库单行号
	*/
	@Column(name="in_bill_row")
	@Title("入库单行号")
	private int inBillRow ;
	/**
	 * 配送机构
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("配送机构")
	private String provId ;
	/**
	 * 配送机构编号
	*/
	@Size(max=36)
	@Column(name="prov_code")
	@Title("配送机构编号")
	private String provCode ;
	/**
	 * 配送机构名称
	*/
	@Size(max=36)
	@Column(name="prov_name")
	@Title("配送机构名称")
	private String provName ;
	/**
	 * 手术包号
	 */
	@Size(max=36)
	@Column(name="sur_code")
	@Title("手术包码")
	private String surCode ;
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
	@Size(max=36)
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
	 * 是否定数包管理
	*/
	@Size(max=1)
	@Column(name="is_packet")
	@Title("是否定数包管理")
	private String isPacket ;
	/**
	 * 唯一码管理
	*/
	@Column(name="unique_kind")
	@Title("唯一码管理")
	private int uniqueKind ;
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
	 * 出库数量
	*/
	@Column(name="out_qty")
	@Title("出库数量")
	private java.math.BigDecimal outQty ;
	/**
	 * 出库包数
	*/
	@Column(name="out_packet_qty")
	@Title("出库包数")
	private java.math.BigDecimal outPacketQty ;
	/**
	 * 入库数量
	*/
	@Column(name="in_qty")
	@Title("入库数量")
	private java.math.BigDecimal inQty ;
	/**
	 * 入库包数
	*/
	@Column(name="int_packet_qty")
	@Title("入库包数")
	private java.math.BigDecimal intPacketQty ;
	/**
	 * 主码
	*/
	@Size(max=128)
	@Column(name="master_code")
	@Title("主码")
	private String masterCode ;
	/**
	 * 副码
	*/
	@Size(max=128)
	@Column(name="sec_code")
	@Title("副码")
	private String secCode ;
	/**
	 * HIBC
	*/
	@Size(max=128)
	@Column(name="hibc_code")
	@Title("HIBC")
	private String hibcCode ;
	/**
	 * 注册证
	*/
	@Size(max=256)
	@Column(name="certificate_code")
	@Title("certificateCode")
	private String certificateCode ;
	/**
	 * 货位ID
	*/
	@Size(max=36)
	@Column(name="shelf_id")
	@Title("货位ID")
	private String shelfId ;
	/**
	 * 状态
	 */
	@Size(max=4)
	@Column(name="state")
	@Title("状态")
	private int state ;
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
	public String getPid ()
	{
		return pid ;
	}
	
	public void setPid (String pid )
	{
		this.pid = pid;
	}
	public String getBillId ()
	{
		return billId ;
	}
	
	public void setBillId (String billId )
	{
		this.billId = billId;
	}
	public int getOutBillRow ()
	{
		return outBillRow ;
	}
	
	public void setOutBillRow (int outBillRow )
	{
		this.outBillRow = outBillRow;
	}
	public int getInBillRow ()
	{
		return inBillRow ;
	}
	
	public void setInBillRow (int inBillRow )
	{
		this.inBillRow = inBillRow;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getProvCode ()
	{
		return provCode ;
	}
	
	public void setProvCode (String provCode )
	{
		this.provCode = provCode;
	}
	public String getProvName ()
	{
		return provName ;
	}
	
	public void setProvName (String provName )
	{
		this.provName = provName;
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
	public String getIsPacket ()
	{
		return isPacket ;
	}
	
	public void setIsPacket (String isPacket )
	{
		this.isPacket = isPacket;
	}
	public int getUniqueKind ()
	{
		return uniqueKind ;
	}
	
	public void setUniqueKind (int uniqueKind )
	{
		this.uniqueKind = uniqueKind;
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
	public java.math.BigDecimal getOutQty ()
	{
		return outQty ;
	}
	
	public void setOutQty (java.math.BigDecimal outQty )
	{
		this.outQty = outQty;
	}
	public java.math.BigDecimal getOutPacketQty ()
	{
		return outPacketQty ;
	}
	
	public void setOutPacketQty (java.math.BigDecimal outPacketQty )
	{
		this.outPacketQty = outPacketQty;
	}
	public java.math.BigDecimal getInQty ()
	{
		return inQty ;
	}
	
	public void setInQty (java.math.BigDecimal inQty )
	{
		this.inQty = inQty;
	}
	public java.math.BigDecimal getIntPacketQty ()
	{
		return intPacketQty ;
	}
	
	public void setIntPacketQty (java.math.BigDecimal intPacketQty )
	{
		this.intPacketQty = intPacketQty;
	}
	public String getMasterCode ()
	{
		return masterCode ;
	}
	
	public void setMasterCode (String masterCode )
	{
		this.masterCode = masterCode;
	}
	public String getSecCode ()
	{
		return secCode ;
	}
	
	public void setSecCode (String secCode )
	{
		this.secCode = secCode;
	}
	public String getHibcCode ()
	{
		return hibcCode ;
	}
	
	public void setHibcCode (String hibcCode )
	{
		this.hibcCode = hibcCode;
	}
	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}
	public String getShelfId ()
	{
		return shelfId ;
	}
	
	public void setShelfId (String shelfId )
	{
		this.shelfId = shelfId;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getOutBillId() {
		return outBillId;
	}

	public void setOutBillId(String outBillId) {
		this.outBillId = outBillId;
	}

	public String getSurCode() {
		return surCode;
	}

	public void setSurCode(String surCode) {
		this.surCode = surCode;
	}

	@Override
	public String toString() {
		return "InStockList [" +" id=" +  id   +", pid=" +  pid   +", billId=" +  billId   +", outBillRow=" +  outBillRow   +", inBillRow=" +  inBillRow   +", provId=" +  provId   +", provCode=" +  provCode   +", provName=" +  provName   +", goodsId=" +  goodsId   +", goodsName=" +  goodsName   +", goodsGg=" +  goodsGg   +", mfrsId=" +  mfrsId   +", mfrsName=" +  mfrsName   +", made=" +  made   +", packetCode=" +  packetCode   +", isPacket=" +  isPacket   +", uniqueKind=" +  uniqueKind   +", batchCode=" +  batchCode   +", sterilizationDate=" +  sterilizationDate   +", sterilizationCode=" +  sterilizationCode   +", sterilizationEndDate=" +  sterilizationEndDate   +", expdtEndDate=" +  expdtEndDate   +", unit=" +  unit   +", outQty=" +  outQty   +", outPacketQty=" +  outPacketQty   +", inQty=" +  inQty   +", intPacketQty=" +  intPacketQty   +", masterCode=" +  masterCode   +", secCode=" +  secCode   +", hibcCode=" +  hibcCode +", certificateCode=" +  certificateCode   +", shelfId=" +  shelfId   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}