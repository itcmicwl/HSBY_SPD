
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 请购配送单明细
*/
@Table(name="distr_bill_list")
public class DistrBillList implements IBean
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
	 * 确认单号
	*/
	@Size(max=36)
	@Column(name="purconfirm_bill_id")
	@Title("确认单号")
	private String purconfirmBillId ;
	/**
	 * 确认单行号
	*/
	@Column(name="purconfirm_row_number")
	@Title("确认单行号")
	private int purconfirmRowNumber ;
	/**
	 * 采购订单号
	*/
	@Size(max=36)
	@Column(name="pur_bill_id")
	@Title("采购订单号")
	private String purBillId ;
	/**
	 * 采购订单行号
	*/
	@Column(name="pur_row_number")
	@Title("采购订单行号")
	private int purRowNumber ;
	/**
	 * 采购订单日期
	*/
	@Column(name="pur_bill_date")
	@Title("采购订单日期")
	private java.util.Date purBillDate ;
	/**
	 * 科室请购单号
	*/
	@Size(max=36)
	@Column(name="apply_bill_id")
	@Title("科室请购单号")
	private String applyBillId ;
	/**
	 * 销售单号
	*/
	@Size(max=36)
	@Column(name="sale_bill_id")
	@Title("销售单号")
	private String saleBillId ;
	/**
	 * 发货日期
	*/
	@Column(name="delivery_date")
	@Title("发货日期")
	private java.util.Date deliveryDate ;
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
	@Column(name="batch_num")
	@Title("产品批次")
	private String batchNum ;
	/**
	 * 启用唯一码管理
	*/
	@Size(max=1)
	@Column(name="is_unique_code")
	@Title("启用唯一码管理")
	private String isUniqueCode ;
	/**
	 * 批号
	*/
	@Size(max=128)
	@Column(name="batch_code")
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
	 * 灭菌有效日期
	*/
	@Column(name="sterilization_end_date")
	@Title("灭菌有效日期")
	private java.util.Date sterilizationEndDate ;
	/**
	 * 灭菌日期
	*/
	@Column(name="sterilization_date")
	@Title("灭菌日期")
	private java.util.Date sterilizationDate ;
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
	 * 配送单位
	*/
	@Size(max=36)
	@JoinColumn(name="send_unit",table="hos_goods_info",referencedColumnName="unit")
	@Title("配送单位")
	private String sendUnit ;
	/**
	 * 配送单位转换比率
	*/
	@Column(name="send_scale")
	@Title("配送单位转换比率")
	private java.math.BigDecimal sendScale ;
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
	 * 请购单位
	*/
	@Size(max=36)
	@Column(name="hos_unit")
	@Title("请购单位")
	private String hosUnit ;
	/**
	 * 请购单位数量
	*/
	@Column(name="hos_qty")
	@Title("请购单位数量")
	private java.math.BigDecimal hosQty ;
	/**
	 * 请购单位单价
	*/
	@Column(name="hos_price")
	@Title("请购单位单价")
	private java.math.BigDecimal hosPrice ;
	/**
	 * 出库库区
	*/
	@Size(max=36)
	@Column(name="out_storearea")
	@Title("出库库区")
	private String outStorearea ;
	/**
	 * 出库类型
	*/
	@Column(name="out_kind")
	@Title("出库类型")
	private int outKind ;
	/**
	 * 总行数
	*/
	@Column(name="sum_row")
	@Title("总行数")
	private int sumRow ;
	/**
	 * 说明
	*/
	@Size(max=300)
	@Column(name="memo")
	@Title("说明")
	private String memo ;
	/**
	 * 条码
	*/
	@Size(max=300)
	@Column(name="barcode")
	@Title("条码")
	private String barcode ;
	/**
	 * 注册证
	*/
	@Size(max=256)
	@Column(name="certificate_code")
	@Title("certificateCode")
	private String certificateCode ;
	/**
	 * 定量包装
	*/
	@Size(max=300)
	@Column(name="packet_code")
	@Title("定量包装")
	private String packetCode ;
	/**
	 * 发货时温度
	*/
	@Column(name="send_temprature")
	@Title("发货时温度")
	private java.math.BigDecimal sendTemprature ;
	/**
	 * 发货时湿度
	*/
	@Column(name="send_humidity")
	@Title("发货时湿度")
	private java.math.BigDecimal sendHumidity ;
	/**
	 * 收货时温度
	*/
	@Column(name="receive_temprature")
	@Title("收货时温度")
	private java.math.BigDecimal receiveTemprature ;
	/**
	 * 收货时湿度
	*/
	@Column(name="receive_humidity")
	@Title("收货时湿度")
	private java.math.BigDecimal receiveHumidity ;
	/**
	 * 数据来源
	*/
	@Column(name="source_data")
	@Title("数据来源")
	private int sourceData ;
	/**
	 * 配送单位收货数量
	*/
	@Column(name="receive_qty")
	@Title("配送单位收货数量")
	private java.math.BigDecimal receiveQty ;
	/**
	 * 当前审批级别
	*/
	@Column(name="status")
	@Title("当前审批级别")
	private int status ;
	/**
	 * 换货标识
	*/
	@Size(max=1)
	@Column(name="is_exchange")
	@Title("换货标识")
	private String isExchange ;
	/**
	 * 医院拒收数量（医院单位）
	*/
	@Column(name="rejected_qty_unit")
	@Title("医院拒收数量（医院单位）")
	private java.math.BigDecimal rejectedQtyUnit ;
	/**
	 * 医院拒收数量(供应商单位)
	*/
	@Column(name="rejected_qty")
	@Title("医院拒收数量(供应商单位)")
	private java.math.BigDecimal rejectedQty ;
	/**
	 * 条码类型
	*/
	@Column(name="unique_kind")
	@Title("条码类型")
	private int uniqueKind ;
	/**
	 * 复核打包号
	 * 配送机构对应的出库复核打包号
	*/
	@Size(max=50)
	@Column(name="check_code")
	@Title("复核打包号")
	private String checkCode ;
	/**
	 * 运单号
	 * 配送机构对应的运单号
	*/
	@Size(max=50)
	@Column(name="trans_bill_id")
	@Title("运单号")
	private String transBillId ;
	/**
	 * 包装
	*/
	@Column(name="packet_num")
	@Title("包装")
	private java.math.BigDecimal packetNum ;
	/**
	 * 发票号
	*/
	@Size(max=200)
	@Column(name="invoices_code")
	@Title("发票号")
	private String invoicesCode ;
	/**
	 * 发票日期
	*/
	@Column(name="invoices_date")
	@Title("发票日期")
	private java.util.Date invoicesDate ;
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
	public String getPurconfirmBillId ()
	{
		return purconfirmBillId ;
	}
	
	public void setPurconfirmBillId (String purconfirmBillId )
	{
		this.purconfirmBillId = purconfirmBillId;
	}
	public int getPurconfirmRowNumber ()
	{
		return purconfirmRowNumber ;
	}
	
	public void setPurconfirmRowNumber (int purconfirmRowNumber )
	{
		this.purconfirmRowNumber = purconfirmRowNumber;
	}
	public String getPurBillId ()
	{
		return purBillId ;
	}
	
	public void setPurBillId (String purBillId )
	{
		this.purBillId = purBillId;
	}
	public int getPurRowNumber ()
	{
		return purRowNumber ;
	}
	
	public void setPurRowNumber (int purRowNumber )
	{
		this.purRowNumber = purRowNumber;
	}
	public java.util.Date getPurBillDate ()
	{
		return purBillDate ;
	}
	
	public void setPurBillDate (java.util.Date purBillDate )
	{
		this.purBillDate = purBillDate;
	}
	public String getApplyBillId ()
	{
		return applyBillId ;
	}
	
	public void setApplyBillId (String applyBillId )
	{
		this.applyBillId = applyBillId;
	}
	public String getSaleBillId ()
	{
		return saleBillId ;
	}
	
	public void setSaleBillId (String saleBillId )
	{
		this.saleBillId = saleBillId;
	}
	public java.util.Date getDeliveryDate ()
	{
		return deliveryDate ;
	}
	
	public void setDeliveryDate (java.util.Date deliveryDate )
	{
		this.deliveryDate = deliveryDate;
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
	public String getIsUniqueCode ()
	{
		return isUniqueCode ;
	}
	
	public void setIsUniqueCode (String isUniqueCode )
	{
		this.isUniqueCode = isUniqueCode;
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
	public java.util.Date getSterilizationEndDate ()
	{
		return sterilizationEndDate ;
	}
	
	public void setSterilizationEndDate (java.util.Date sterilizationEndDate )
	{
		this.sterilizationEndDate = sterilizationEndDate;
	}
	public java.util.Date getSterilizationDate ()
	{
		return sterilizationDate ;
	}
	
	public void setSterilizationDate (java.util.Date sterilizationDate )
	{
		this.sterilizationDate = sterilizationDate;
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
	public String getSendUnit ()
	{
		return sendUnit ;
	}
	
	public void setSendUnit (String sendUnit )
	{
		this.sendUnit = sendUnit;
	}
	public java.math.BigDecimal getSendScale ()
	{
		return sendScale ;
	}
	
	public void setSendScale (java.math.BigDecimal sendScale )
	{
		this.sendScale = sendScale;
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
	public String getHosUnit ()
	{
		return hosUnit ;
	}
	
	public void setHosUnit (String hosUnit )
	{
		this.hosUnit = hosUnit;
	}
	public java.math.BigDecimal getHosQty ()
	{
		return hosQty ;
	}
	
	public void setHosQty (java.math.BigDecimal hosQty )
	{
		this.hosQty = hosQty;
	}
	public java.math.BigDecimal getHosPrice ()
	{
		return hosPrice ;
	}
	
	public void setHosPrice (java.math.BigDecimal hosPrice )
	{
		this.hosPrice = hosPrice;
	}
	public String getOutStorearea ()
	{
		return outStorearea ;
	}
	
	public void setOutStorearea (String outStorearea )
	{
		this.outStorearea = outStorearea;
	}
	public int getOutKind ()
	{
		return outKind ;
	}
	
	public void setOutKind (int outKind )
	{
		this.outKind = outKind;
	}
	public int getSumRow ()
	{
		return sumRow ;
	}
	
	public void setSumRow (int sumRow )
	{
		this.sumRow = sumRow;
	}
	public String getMemo ()
	{
		return memo ;
	}
	
	public void setMemo (String memo )
	{
		this.memo = memo;
	}
	public String getBarcode ()
	{
		return barcode ;
	}
	
	public void setBarcode (String barcode )
	{
		this.barcode = barcode;
	}
	public String getPacketCode ()
	{
		return packetCode ;
	}
	
	public void setPacketCode (String packetCode )
	{
		this.packetCode = packetCode;
	}
	public java.math.BigDecimal getSendTemprature ()
	{
		return sendTemprature ;
	}
	
	public void setSendTemprature (java.math.BigDecimal sendTemprature )
	{
		this.sendTemprature = sendTemprature;
	}
	public java.math.BigDecimal getSendHumidity ()
	{
		return sendHumidity ;
	}
	
	public void setSendHumidity (java.math.BigDecimal sendHumidity )
	{
		this.sendHumidity = sendHumidity;
	}
	public java.math.BigDecimal getReceiveTemprature ()
	{
		return receiveTemprature ;
	}
	
	public void setReceiveTemprature (java.math.BigDecimal receiveTemprature )
	{
		this.receiveTemprature = receiveTemprature;
	}
	public java.math.BigDecimal getReceiveHumidity ()
	{
		return receiveHumidity ;
	}
	
	public void setReceiveHumidity (java.math.BigDecimal receiveHumidity )
	{
		this.receiveHumidity = receiveHumidity;
	}
	public int getSourceData ()
	{
		return sourceData ;
	}
	
	public void setSourceData (int sourceData )
	{
		this.sourceData = sourceData;
	}
	public java.math.BigDecimal getReceiveQty ()
	{
		return receiveQty ;
	}
	
	public void setReceiveQty (java.math.BigDecimal receiveQty )
	{
		this.receiveQty = receiveQty;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public String getIsExchange ()
	{
		return isExchange ;
	}
	
	public void setIsExchange (String isExchange )
	{
		this.isExchange = isExchange;
	}
	public java.math.BigDecimal getRejectedQtyUnit ()
	{
		return rejectedQtyUnit ;
	}
	
	public void setRejectedQtyUnit (java.math.BigDecimal rejectedQtyUnit )
	{
		this.rejectedQtyUnit = rejectedQtyUnit;
	}
	public java.math.BigDecimal getRejectedQty ()
	{
		return rejectedQty ;
	}
	
	public void setRejectedQty (java.math.BigDecimal rejectedQty )
	{
		this.rejectedQty = rejectedQty;
	}
	public int getUniqueKind ()
	{
		return uniqueKind ;
	}
	
	public void setUniqueKind (int uniqueKind )
	{
		this.uniqueKind = uniqueKind;
	}
	public String getCheckCode ()
	{
		return checkCode ;
	}
	
	public void setCheckCode (String checkCode )
	{
		this.checkCode = checkCode;
	}
	public String getTransBillId ()
	{
		return transBillId ;
	}
	
	public void setTransBillId (String transBillId )
	{
		this.transBillId = transBillId;
	}
	public java.math.BigDecimal getPacketNum ()
	{
		return packetNum ;
	}
	
	public void setPacketNum (java.math.BigDecimal packetNum )
	{
		this.packetNum = packetNum;
	}
	public String getInvoicesCode ()
	{
		return invoicesCode ;
	}
	
	public void setInvoicesCode (String invoicesCode )
	{
		this.invoicesCode = invoicesCode;
	}
	public java.util.Date getInvoicesDate ()
	{
		return invoicesDate ;
	}
	
	public void setInvoicesDate (java.util.Date invoicesDate )
	{
		this.invoicesDate = invoicesDate;
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


	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	@Override
	public String toString() {
		return "DistrBillList [" +" id=" +  id   +", pid=" +  pid   +", billId=" +  billId   +", rowNumber=" +  rowNumber   +", purconfirmBillId=" +  purconfirmBillId   +", purconfirmRowNumber=" +  purconfirmRowNumber   +", purBillId=" +  purBillId   +", purRowNumber=" +  purRowNumber   +", purBillDate=" +  purBillDate   +", applyBillId=" +  applyBillId   +", saleBillId=" +  saleBillId   +", deliveryDate=" +  deliveryDate   +", provGoodsId=" +  provGoodsId   +", goodsGg=" +  goodsGg   +", goodsMrfsId=" +  goodsMrfsId   +", made=" +  made   +", hosGoodsId=" +  hosGoodsId   +", hosGoodsCode=" +  hosGoodsCode   +", hosGoodsName=" +  hosGoodsName   +", provGoodsErpCode=" +  provGoodsErpCode   +", provGoodsErpName=" +  provGoodsErpName   +", batchNum=" +  batchNum   +", isUniqueCode=" +  isUniqueCode   +", batchCode=" +  batchCode   +", sterilizationCode=" +  sterilizationCode   +", sterilizationEndDate=" +  sterilizationEndDate   +", sterilizationDate=" +  sterilizationDate   +", expdtEndDate=" +  expdtEndDate   +", productDate=" +  productDate   +", sendUnit=" +  sendUnit   +", sendScale=" +  sendScale   +", sendQty=" +  sendQty   +", sendPrice=" +  sendPrice   +", hosUnit=" +  hosUnit   +", hosQty=" +  hosQty   +", hosPrice=" +  hosPrice   +", outStorearea=" +  outStorearea   +", outKind=" +  outKind   +", sumRow=" +  sumRow   +", memo=" +  memo   +", barcode=" +  barcode   +", packetCode=" +  packetCode   +", sendTemprature=" +  sendTemprature   +", sendHumidity=" +  sendHumidity   +", receiveTemprature=" +  receiveTemprature   +", receiveHumidity=" +  receiveHumidity   +", sourceData=" +  sourceData   +", receiveQty=" +  receiveQty   +", status=" +  status   +", isExchange=" +  isExchange   +", rejectedQtyUnit=" +  rejectedQtyUnit   +", rejectedQty=" +  rejectedQty   +", uniqueKind=" +  uniqueKind   +", checkCode=" +  checkCode   +", transBillId=" +  transBillId   +", packetNum=" +  packetNum   +", invoicesCode=" +  invoicesCode   +", invoicesDate=" +  invoicesDate   +", lastUpdateDatetime=" +  lastUpdateDatetime  +", certificateCode=" +  certificateCode  +", version=" +  version   
			 + "]";
	}

}