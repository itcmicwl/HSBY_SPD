
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
 * 医院出库结算单明细
*/
@Table(name="hos_out_balance_detail")
public class HosOutBalanceDetail implements IBean
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
	 * 行号
	*/
	@Column(name="row_num")
	@Title("行号")
	private int rowNum ;
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
	@Size(max=200)
	@Column(name="prov_name")
	@Title("配送机构名称")
	private String provName ;
	/**
	 * 配送机构ERP编号
	*/
	@Size(max=36)
	@Column(name="prov_erp_code")
	@Title("配送机构ERP编号")
	private String provErpCode ;
	/**
	 * 出库科室
	*/
	@Size(max=36)
	@Column(name="out_dept_id")
	@Title("出库科室")
	private String outDeptId ;
	/**
	 * 出库科室名称
	*/
	@Size(max=200)
	@Column(name="out_dept_name")
	@Title("出库科室名称")
	private String outDeptName ;
	/**
	 * 出库科室ERP编号
	*/
	@Size(max=36)
	@Column(name="out_dept_erp_code")
	@Title("出库科室ERP编号")
	private String outDeptErpCode ;
	/**
	 * 出库单号
	*/
	@Size(max=36)
	@Column(name="out_billid")
	@Title("出库单号")
	private String outBillid ;
	/**
	 * 出库单行号
	*/
	@Column(name="out_row_num")
	@Title("出库单行号")
	private int outRowNum ;
	/**
	 * 出库日期
	*/
	@Column(name="out_date")
	@Title("出库日期")
	private java.util.Date outDate ;
	/**
	 * 医院产品ID
	*/
	@Size(max=36)
	@Column(name="hos_goods_id")
	@Title("医院产品ID")
	private String hosGoodsId ;
	/**
	 * 医院产品名称
	*/
	@Size(max=36)
	@Column(name="hos_goods_name")
	@Title("医院产品名称")
	private String hosGoodsName ;
	/**
	 * 产品规格
	*/
	@Size(max=200)
	@Column(name="goods_gg")
	@Title("产品规格")
	private String goodsGg ;
	/**
	 * 生产厂商
	*/
	@Size(max=36)
	@Column(name="mfrs_id")
	@Title("生产厂商")
	private String mfrsId ;
	/**
	 * 产地
	*/
	@Size(max=36)
	@Column(name="made")
	@Title("产地")
	private String made ;
	/**
	 * 包装
	*/
	@Size(max=36)
	@Column(name="packet_code")
	@Title("包装")
	private String packetCode ;
	/**
	 * 供应商产品ID
	*/
	@Size(max=36)
	@Column(name="prov_goods_id")
	@Title("供应商产品ID")
	private String provGoodsId ;
	/**
	 * 供应商产品名称
	*/
	@Size(max=36)
	@Column(name="prov_goods_name")
	@Title("供应商产品名称")
	private String provGoodsName ;
	/**
	 * 医药医材批次
	*/
	@Size(max=36)
	@Column(name="batch_id")
	@Title("医药医材批次")
	private String batchId ;
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
	@Size(max=3)
	@Column(name="sterilization_date")
	@Title("灭菌日期")
	private java.util.Date sterilizationDate ;
	/**
	 * 灭菌效期
	*/
	@Size(max=3)
	@Column(name="sterilization_end_date")
	@Title("灭菌效期")
	private java.util.Date sterilizationEndDate ;
	/**
	 * 灭菌批号
	*/
	@Size(max=36)
	@Column(name="sterilization_code")
	@Title("灭菌批号")
	private String sterilizationCode ;
	/**
	 * 有效期
	*/
	@Column(name="expdt_end_date")
	@Title("有效期")
	private java.util.Date expdtEndDate ;
	/**
	 * 生产日期
	*/
	@Column(name="produce_datetime")
	@Title("生产日期")
	private java.util.Date produceDatetime ;
	/**
	 * 医院单位
	*/
	@Size(max=36)
	@Column(name="hos_unit")
	@Title("医院单位")
	private String hosUnit ;
	/**
	 * 医院单位数量
	*/
	@Column(name="hos_unit_qty")
	@Title("医院单位数量")
	private java.math.BigDecimal hosUnitQty ;
	/**
	 * 供应商单位
	*/
	@Size(max=36)
	@Column(name="prov_unit")
	@Title("供应商单位")
	private String provUnit ;
	/**
	 * 单位转换比率
	*/
	@Column(name="hos_scale")
	@Title("单位转换比率")
	private java.math.BigDecimal hosScale ;
	/**
	 * 供应商单位数量
	*/
	@Column(name="prov_unit_qty")
	@Title("供应商单位数量")
	private java.math.BigDecimal provUnitQty ;
	/**
	 * 状态
	*/
	@Column(name="status")
	@Title("状态")
	private int status ;
	/**
	 * 出库类型
	*/
	@Column(name="out_kind")
	@Title("出库类型")
	private int outKind ;
	/**
	 * 发票号
	*/
	@Size(max=128)
	@Column(name="invoice_number")
	@Title("发票号")
	private String invoiceNumber ;
	/**
	 * 发票编号
	*/
	@Size(max=128)
	@Column(name="invoice_code")
	@Title("发票编号")
	private String invoiceCode ;
	/**
	 * 业务员
	*/
	@Size(max=36)
	@Column(name="sale_man")
	@Title("业务员")
	private String saleMan ;
	/**
	 * 单价
	*/
	@Column(name="price")
	@Title("单价")
	private java.math.BigDecimal price ;
	/**
	 * 结算金额
	*/
	@Column(name="settle_amount")
	@Title("结算金额")
	private java.math.BigDecimal settleAmount ;
	/**
	 * 发票金额
	*/
	@Column(name="invoice_amount")
	@Title("发票金额")
	private java.math.BigDecimal invoiceAmount ;
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
	public int getRowNum ()
	{
		return rowNum ;
	}
	
	public void setRowNum (int rowNum )
	{
		this.rowNum = rowNum;
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
	public String getProvErpCode ()
	{
		return provErpCode ;
	}
	
	public void setProvErpCode (String provErpCode )
	{
		this.provErpCode = provErpCode;
	}
	public String getOutDeptId ()
	{
		return outDeptId ;
	}
	
	public void setOutDeptId (String outDeptId )
	{
		this.outDeptId = outDeptId;
	}
	public String getOutDeptName ()
	{
		return outDeptName ;
	}
	
	public void setOutDeptName (String outDeptName )
	{
		this.outDeptName = outDeptName;
	}
	public String getOutDeptErpCode ()
	{
		return outDeptErpCode ;
	}
	
	public void setOutDeptErpCode (String outDeptErpCode )
	{
		this.outDeptErpCode = outDeptErpCode;
	}
	public String getOutBillid ()
	{
		return outBillid ;
	}
	
	public void setOutBillid (String outBillid )
	{
		this.outBillid = outBillid;
	}
	public int getOutRowNum ()
	{
		return outRowNum ;
	}
	
	public void setOutRowNum (int outRowNum )
	{
		this.outRowNum = outRowNum;
	}
	public java.util.Date getOutDate ()
	{
		return outDate ;
	}
	
	public void setOutDate (java.util.Date outDate )
	{
		this.outDate = outDate;
	}
	public String getHosGoodsId ()
	{
		return hosGoodsId ;
	}
	
	public void setHosGoodsId (String hosGoodsId )
	{
		this.hosGoodsId = hosGoodsId;
	}
	public String getHosGoodsName ()
	{
		return hosGoodsName ;
	}
	
	public void setHosGoodsName (String hosGoodsName )
	{
		this.hosGoodsName = hosGoodsName;
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
	public String getPacketCode ()
	{
		return packetCode ;
	}
	
	public void setPacketCode (String packetCode )
	{
		this.packetCode = packetCode;
	}
	public String getProvGoodsId ()
	{
		return provGoodsId ;
	}
	
	public void setProvGoodsId (String provGoodsId )
	{
		this.provGoodsId = provGoodsId;
	}
	public String getProvGoodsName ()
	{
		return provGoodsName ;
	}
	
	public void setProvGoodsName (String provGoodsName )
	{
		this.provGoodsName = provGoodsName;
	}
	public String getBatchId ()
	{
		return batchId ;
	}
	
	public void setBatchId (String batchId )
	{
		this.batchId = batchId;
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
	public java.util.Date getSterilizationEndDate ()
	{
		return sterilizationEndDate ;
	}
	
	public void setSterilizationEndDate (java.util.Date sterilizationEndDate )
	{
		this.sterilizationEndDate = sterilizationEndDate;
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
	public java.util.Date getProduceDatetime ()
	{
		return produceDatetime ;
	}
	
	public void setProduceDatetime (java.util.Date produceDatetime )
	{
		this.produceDatetime = produceDatetime;
	}
	public String getHosUnit ()
	{
		return hosUnit ;
	}
	
	public void setHosUnit (String hosUnit )
	{
		this.hosUnit = hosUnit;
	}
	public java.math.BigDecimal getHosUnitQty ()
	{
		return hosUnitQty ;
	}
	
	public void setHosUnitQty (java.math.BigDecimal hosUnitQty )
	{
		this.hosUnitQty = hosUnitQty;
	}
	public String getProvUnit ()
	{
		return provUnit ;
	}
	
	public void setProvUnit (String provUnit )
	{
		this.provUnit = provUnit;
	}
	public java.math.BigDecimal getHosScale ()
	{
		return hosScale ;
	}
	
	public void setHosScale (java.math.BigDecimal hosScale )
	{
		this.hosScale = hosScale;
	}
	public java.math.BigDecimal getProvUnitQty ()
	{
		return provUnitQty ;
	}
	
	public void setProvUnitQty (java.math.BigDecimal provUnitQty )
	{
		this.provUnitQty = provUnitQty;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public int getOutKind ()
	{
		return outKind ;
	}
	
	public void setOutKind (int outKind )
	{
		this.outKind = outKind;
	}
	public String getInvoiceNumber ()
	{
		return invoiceNumber ;
	}
	
	public void setInvoiceNumber (String invoiceNumber )
	{
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceCode ()
	{
		return invoiceCode ;
	}
	
	public void setInvoiceCode (String invoiceCode )
	{
		this.invoiceCode = invoiceCode;
	}
	public String getSaleMan ()
	{
		return saleMan ;
	}
	
	public void setSaleMan (String saleMan )
	{
		this.saleMan = saleMan;
	}
	public java.math.BigDecimal getPrice ()
	{
		return price ;
	}
	
	public void setPrice (java.math.BigDecimal price )
	{
		this.price = price;
	}
	public java.math.BigDecimal getSettleAmount ()
	{
		return settleAmount ;
	}
	
	public void setSettleAmount (java.math.BigDecimal settleAmount )
	{
		this.settleAmount = settleAmount;
	}
	public java.math.BigDecimal getInvoiceAmount ()
	{
		return invoiceAmount ;
	}
	
	public void setInvoiceAmount (java.math.BigDecimal invoiceAmount )
	{
		this.invoiceAmount = invoiceAmount;
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
		return "HosOutBalanceDetail [" +" id=" +  id   +", pid=" +  pid   +", billId=" +  billId   +", rowNum=" +  rowNum   +", provId=" +  provId   +", provName=" +  provName   +", provErpCode=" +  provErpCode   +", outDeptId=" +  outDeptId   +", outDeptName=" +  outDeptName   +", outDeptErpCode=" +  outDeptErpCode   +", outBillid=" +  outBillid   +", outRowNum=" +  outRowNum   +", outDate=" +  outDate   +", hosGoodsId=" +  hosGoodsId   +", hosGoodsName=" +  hosGoodsName   +", goodsGg=" +  goodsGg   +", mfrsId=" +  mfrsId   +", made=" +  made   +", packetCode=" +  packetCode   +", provGoodsId=" +  provGoodsId   +", provGoodsName=" +  provGoodsName   +", batchId=" +  batchId   +", batchCode=" +  batchCode   +", sterilizationDate=" +  sterilizationDate   +", sterilizationEndDate=" +  sterilizationEndDate   +", sterilizationCode=" +  sterilizationCode   +", expdtEndDate=" +  expdtEndDate   +", produceDatetime=" +  produceDatetime   +", hosUnit=" +  hosUnit   +", hosUnitQty=" +  hosUnitQty   +", provUnit=" +  provUnit   +", hosScale=" +  hosScale   +", provUnitQty=" +  provUnitQty   +", status=" +  status   +", outKind=" +  outKind   +", invoiceNumber=" +  invoiceNumber   +", invoiceCode=" +  invoiceCode   +", saleMan=" +  saleMan   +", price=" +  price   +", settleAmount=" +  settleAmount   +", invoiceAmount=" +  invoiceAmount   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}