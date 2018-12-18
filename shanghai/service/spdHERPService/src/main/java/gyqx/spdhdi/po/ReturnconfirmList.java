
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 退货确认单明细
*/
@Table(name="returnconfirm_list")
public class ReturnconfirmList implements IBean
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
	 * pid
	*/
	@Size(max=36)
	@JoinColumn(name="pid",table="purchase",referencedColumnName="id")
	@Title("pid")
	private String pid ;
	/**
	 * 单号
	*/
	@Size(max=36)
	@JoinColumn(name="bill_id",table="purchase",referencedColumnName="bill_id")
	@Title("单号")
	private String billId ;
	/**
	 * 行号
	*/
	@Column(name="row_number")
	@Title("行号")
	private int rowNumber ;
	/**
	 * 退货单号
	*/
	@Size(max=36)
	@Column(name="return_bill_id")
	@Title("退货单号")
	private String returnBillId ;
	/**
	 * 退货单行号
	*/
	@Column(name="return_bill_rownum")
	@Title("退货单行号")
	private int returnBillRownum ;
	/**
	 * 退货单日期
	*/
	@Column(name="return_bill_date")
	@Title("退货单日期")
	private java.util.Date returnBillDate ;
	/**
	 * 原医院请购单号
	*/
	@Size(max=36)
	@JoinColumn(name="pur_bill_id",table="return_bill_list",referencedColumnName="pur_bill_id")
	@Title("原医院请购单号")
	private String purBillId ;
	/**
	 * 原科室请购单号
	*/
	@Size(max=36)
	@JoinColumn(name="apply_bill_id",table="return_bill_list",referencedColumnName="apply_bill_id")
	@Title("原科室请购单号")
	private String applyBillId ;
	/**
	 * 配送商产品ID
	*/
	@Size(max=36)
	@JoinColumn(name="prov_goods_id",table="hos_goods_info",referencedColumnName="id")
	@Title("配送商产品ID")
	private String provGoodsId ;
	/**
	 * 规格
	*/
	@Size(max=36)
	@Column(name="goods_gg")
	@Title("规格")
	private String goodsGg ;
	/**
	 * 生产厂商
	*/
	@Size(max=36)
	@Column(name="goods_mrfs_id")
	@Title("生产厂商")
	private String goodsMrfsId ;
	/**
	 * 产地
	*/
	@Size(max=36)
	@Column(name="made")
	@Title("产地")
	private String made ;
	/**
	 * 医院产品ID
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_id",table="hos_goods_info",referencedColumnName="id")
	@Title("医院产品ID")
	private String hosGoodsId ;
	/**
	 * 医院内部产品编号
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_code",table="hos_goods_info",referencedColumnName="code")
	@Title("医院内部产品编号")
	private String hosGoodsCode ;
	/**
	 * 医院内部产品名称
	*/
	@Size(max=128)
	@JoinColumn(name="hos_goods_name",table="hos_goods_info",referencedColumnName="goods_name")
	@Title("医院内部产品名称")
	private String hosGoodsName ;
	/**
	 * 配送机构内部编号
	*/
	@Size(max=36)
	@Column(name="prov_goods_erp_code")
	@Title("配送机构内部编号")
	private String provGoodsErpCode ;
	/**
	 * 配送机构内部名称
	*/
	@Size(max=128)
	@Column(name="prov_goods_erp_name")
	@Title("配送机构内部名称")
	private String provGoodsErpName ;
	/**
	 * 产品批次
	*/
	@Size(max=128)
	@JoinColumn(name="batch_num",table="return_bill_list",referencedColumnName="batch_num")
	@Title("产品批次")
	private String batchNum ;
	/**
	 * 批号
	*/
	@Size(max=128)
	@JoinColumn(name="batch_code",table="return_bill_list",referencedColumnName="batch_code")
	@Title("批号")
	private String batchCode ;
	/**
	 * 灭菌批号
	*/
	@Size(max=128)
	@Column(name="sterilization_code")
	@Title("灭菌批号")
	private String sterilizationCode ;
	/**
	 * 有效日期
	*/
	@Column(name="expdt_end_date")
	@Title("有效日期")
	private java.util.Date expdtEndDate ;
	/**
	 * 生产日期
	*/
	@Column(name="product_date")
	@Title("生产日期")
	private java.util.Date productDate ;
	/**
	 * 请退单位
	*/
	@Size(max=36)
	@Column(name="return_unit")
	@Title("请退单位")
	private String returnUnit ;
	/**
	 * 转换比率
	*/
	@Column(name="return_scale")
	@Title("转换比率")
	private java.math.BigDecimal returnScale ;
	/**
	 * 请退单位单价
	*/
	@Column(name="return_price")
	@Title("请退单位单价")
	private java.math.BigDecimal returnPrice ;
	/**
	 * 请退单位数量
	*/
	@Column(name="return_qty")
	@Title("请退单位数量")
	private java.math.BigDecimal returnQty ;
	/**
	 * 配送单位
	*/
	@Size(max=36)
	@JoinColumn(name="send_unit",table="hos_goods_info",referencedColumnName="unit")
	@Title("配送单位")
	private String sendUnit ;
	/**
	 * 配送单位数量
	*/
	@Column(name="send_qty")
	@Title("配送单位数量")
	private java.math.BigDecimal sendQty ;
	/**
	 * 配送单位单价
	*/
	@Column(name="send_price")
	@Title("配送单位单价")
	private java.math.BigDecimal sendPrice ;
	/**
	 * 入库类型
	 * 
	 * 入库类型
	*/
	@Column(name="out_kind")
	@Title("入库类型")
	private int outKind ;
	/**
	 * 退货单总行数
	*/
	@Column(name="sum_rows")
	@Title("退货单总行数")
	private int sumRows ;
	/**
	 * 数据来源
	 * 手工录入：0，导入：1
	*/
	@Column(name="source_data")
	@Title("数据来源")
	private int sourceData ;
	/**
	 * 退货原因
	*/
	@Size(max=128)
	@Column(name="return_reason")
	@Title("退货原因")
	private String returnReason ;
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
	public int getRowNumber ()
	{
		return rowNumber ;
	}
	
	public void setRowNumber (int rowNumber )
	{
		this.rowNumber = rowNumber;
	}
	public String getReturnBillId ()
	{
		return returnBillId ;
	}
	
	public void setReturnBillId (String returnBillId )
	{
		this.returnBillId = returnBillId;
	}
	public int getReturnBillRownum ()
	{
		return returnBillRownum ;
	}
	
	public void setReturnBillRownum (int returnBillRownum )
	{
		this.returnBillRownum = returnBillRownum;
	}
	public java.util.Date getReturnBillDate ()
	{
		return returnBillDate ;
	}
	
	public void setReturnBillDate (java.util.Date returnBillDate )
	{
		this.returnBillDate = returnBillDate;
	}
	public String getPurBillId ()
	{
		return purBillId ;
	}
	
	public void setPurBillId (String purBillId )
	{
		this.purBillId = purBillId;
	}
	public String getApplyBillId ()
	{
		return applyBillId ;
	}
	
	public void setApplyBillId (String applyBillId )
	{
		this.applyBillId = applyBillId;
	}
	public String getProvGoodsId ()
	{
		return provGoodsId ;
	}
	
	public void setProvGoodsId (String provGoodsId )
	{
		this.provGoodsId = provGoodsId;
	}
	public String getGoodsGg ()
	{
		return goodsGg ;
	}
	
	public void setGoodsGg (String goodsGg )
	{
		this.goodsGg = goodsGg;
	}
	public String getGoodsMrfsId ()
	{
		return goodsMrfsId ;
	}
	
	public void setGoodsMrfsId (String goodsMrfsId )
	{
		this.goodsMrfsId = goodsMrfsId;
	}
	public String getMade ()
	{
		return made ;
	}
	
	public void setMade (String made )
	{
		this.made = made;
	}
	public String getHosGoodsId ()
	{
		return hosGoodsId ;
	}
	
	public void setHosGoodsId (String hosGoodsId )
	{
		this.hosGoodsId = hosGoodsId;
	}
	public String getHosGoodsCode ()
	{
		return hosGoodsCode ;
	}
	
	public void setHosGoodsCode (String hosGoodsCode )
	{
		this.hosGoodsCode = hosGoodsCode;
	}
	public String getHosGoodsName ()
	{
		return hosGoodsName ;
	}
	
	public void setHosGoodsName (String hosGoodsName )
	{
		this.hosGoodsName = hosGoodsName;
	}
	public String getProvGoodsErpCode ()
	{
		return provGoodsErpCode ;
	}
	
	public void setProvGoodsErpCode (String provGoodsErpCode )
	{
		this.provGoodsErpCode = provGoodsErpCode;
	}
	public String getProvGoodsErpName ()
	{
		return provGoodsErpName ;
	}
	
	public void setProvGoodsErpName (String provGoodsErpName )
	{
		this.provGoodsErpName = provGoodsErpName;
	}
	public String getBatchNum ()
	{
		return batchNum ;
	}
	
	public void setBatchNum (String batchNum )
	{
		this.batchNum = batchNum;
	}
	public String getBatchCode ()
	{
		return batchCode ;
	}
	
	public void setBatchCode (String batchCode )
	{
		this.batchCode = batchCode;
	}
	public String getSterilizationCode ()
	{
		return sterilizationCode ;
	}
	
	public void setSterilizationCode (String sterilizationCode )
	{
		this.sterilizationCode = sterilizationCode;
	}
	public java.util.Date getExpdtEndDate ()
	{
		return expdtEndDate ;
	}
	
	public void setExpdtEndDate (java.util.Date expdtEndDate )
	{
		this.expdtEndDate = expdtEndDate;
	}
	public java.util.Date getProductDate ()
	{
		return productDate ;
	}
	
	public void setProductDate (java.util.Date productDate )
	{
		this.productDate = productDate;
	}
	public String getReturnUnit ()
	{
		return returnUnit ;
	}
	
	public void setReturnUnit (String returnUnit )
	{
		this.returnUnit = returnUnit;
	}
	public java.math.BigDecimal getReturnScale ()
	{
		return returnScale ;
	}
	
	public void setReturnScale (java.math.BigDecimal returnScale )
	{
		this.returnScale = returnScale;
	}
	public java.math.BigDecimal getReturnPrice ()
	{
		return returnPrice ;
	}
	
	public void setReturnPrice (java.math.BigDecimal returnPrice )
	{
		this.returnPrice = returnPrice;
	}
	public java.math.BigDecimal getReturnQty ()
	{
		return returnQty ;
	}
	
	public void setReturnQty (java.math.BigDecimal returnQty )
	{
		this.returnQty = returnQty;
	}
	public String getSendUnit ()
	{
		return sendUnit ;
	}
	
	public void setSendUnit (String sendUnit )
	{
		this.sendUnit = sendUnit;
	}
	public java.math.BigDecimal getSendQty ()
	{
		return sendQty ;
	}
	
	public void setSendQty (java.math.BigDecimal sendQty )
	{
		this.sendQty = sendQty;
	}
	public java.math.BigDecimal getSendPrice ()
	{
		return sendPrice ;
	}
	
	public void setSendPrice (java.math.BigDecimal sendPrice )
	{
		this.sendPrice = sendPrice;
	}
	public int getOutKind ()
	{
		return outKind ;
	}
	
	public void setOutKind (int outKind )
	{
		this.outKind = outKind;
	}
	public int getSumRows ()
	{
		return sumRows ;
	}
	
	public void setSumRows (int sumRows )
	{
		this.sumRows = sumRows;
	}
	public int getSourceData ()
	{
		return sourceData ;
	}
	
	public void setSourceData (int sourceData )
	{
		this.sourceData = sourceData;
	}
	public String getReturnReason ()
	{
		return returnReason ;
	}
	
	public void setReturnReason (String returnReason )
	{
		this.returnReason = returnReason;
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
		return "ReturnconfirmList [" +" id=" +  id   +", pid=" +  pid   +", billId=" +  billId   +", rowNumber=" +  rowNumber   +", returnBillId=" +  returnBillId   +", returnBillRownum=" +  returnBillRownum   +", returnBillDate=" +  returnBillDate   +", purBillId=" +  purBillId   +", applyBillId=" +  applyBillId   +", provGoodsId=" +  provGoodsId   +", goodsGg=" +  goodsGg   +", goodsMrfsId=" +  goodsMrfsId   +", made=" +  made   +", hosGoodsId=" +  hosGoodsId   +", hosGoodsCode=" +  hosGoodsCode   +", hosGoodsName=" +  hosGoodsName   +", provGoodsErpCode=" +  provGoodsErpCode   +", provGoodsErpName=" +  provGoodsErpName   +", batchNum=" +  batchNum   +", batchCode=" +  batchCode   +", sterilizationCode=" +  sterilizationCode   +", expdtEndDate=" +  expdtEndDate   +", productDate=" +  productDate   +", returnUnit=" +  returnUnit   +", returnScale=" +  returnScale   +", returnPrice=" +  returnPrice   +", returnQty=" +  returnQty   +", sendUnit=" +  sendUnit   +", sendQty=" +  sendQty   +", sendPrice=" +  sendPrice   +", outKind=" +  outKind   +", sumRows=" +  sumRows   +", sourceData=" +  sourceData   +", returnReason=" +  returnReason   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}