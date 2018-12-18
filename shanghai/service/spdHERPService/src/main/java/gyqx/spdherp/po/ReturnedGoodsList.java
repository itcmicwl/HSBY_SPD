
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
 * 退货明细表
*/
@Table(name="returned_goods_list")
public class ReturnedGoodsList implements IBean
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
	/**
	 * 源单号
	*/
	@Size(max=36)
	@Column(name="source_bill_id")
	@Title("源单号")
	private String sourceBillId ;
	/**
	 * 配送机构ID
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("配送机构ID")
	private String provId ;
	/**
	 * 配送机构名称
	*/
	@Size(max=200)
	@Column(name="prov_name")
	@Title("配送机构名称")
	private String provName ;
	/**
	 * 配送机构编码
	*/
	@Size(max=36)
	@Column(name="prov_code")
	@Title("配送机构编码")
	private String provCode ;
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
	@Size(max=200)
	@Column(name="goods_name")
	@Title("产品名称")
	private String goodsName ;
	/**
	 * 规格
	*/
	@Size(max=200)
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
	 * 产地
	*/
	@Size(max=200)
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
	 * 退货数量
	*/
	@Column(name="exc_qty")
	@Title("退货数量")
	private java.math.BigDecimal excQty ;
	/**
	 * 退货包数
	*/
	@Column(name="exc_packet_qty")
	@Title("退货包数")
	private java.math.BigDecimal excPacketQty ;
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
	 * 唯一码管理策略
	*/
	@Column(name="unique_kind")
	@Title("唯一码管理策略")
	private int uniqueKind ;
	/**
	 * 最后修改时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后修改时间")
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
	public String getSourceBillId ()
	{
		return sourceBillId ;
	}
	
	public void setSourceBillId (String sourceBillId )
	{
		this.sourceBillId = sourceBillId;
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
	public String getProvCode ()
	{
		return provCode ;
	}
	
	public void setProvCode (String provCode )
	{
		this.provCode = provCode;
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
	public java.math.BigDecimal getExcQty ()
	{
		return excQty ;
	}
	
	public void setExcQty (java.math.BigDecimal excQty )
	{
		this.excQty = excQty;
	}
	public java.math.BigDecimal getExcPacketQty ()
	{
		return excPacketQty ;
	}
	
	public void setExcPacketQty (java.math.BigDecimal excPacketQty )
	{
		this.excPacketQty = excPacketQty;
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
	public int getUniqueKind ()
	{
		return uniqueKind ;
	}
	
	public void setUniqueKind (int uniqueKind )
	{
		this.uniqueKind = uniqueKind;
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
		return "ReturnedGoodsList [" +" id=" +  id   +", pid=" +  pid   +", billId=" +  billId   +", sourceBillId=" +  sourceBillId   +", provId=" +  provId   +", provName=" +  provName   +", provCode=" +  provCode   +", goodsId=" +  goodsId   +", goodsName=" +  goodsName   +", goodsGg=" +  goodsGg   +", mfrsId=" +  mfrsId   +", made=" +  made   +", packetCode=" +  packetCode   +", batchCode=" +  batchCode   +", sterilizationDate=" +  sterilizationDate   +", sterilizationCode=" +  sterilizationCode   +", sterilizationEndDate=" +  sterilizationEndDate   +", expdtEndDate=" +  expdtEndDate   +", unit=" +  unit   +", excQty=" +  excQty   +", excPacketQty=" +  excPacketQty   +", masterCode=" +  masterCode   +", secCode=" +  secCode   +", uniqueKind=" +  uniqueKind   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}