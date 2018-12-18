
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
 * SPD订单返回明细接口表历史数据
*/
@Table(name="pass_distr_bill_list_sheet")
public class PassDistrBillListSheet implements IBean
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
	@Column(name="p_id")
	@Title("PID")
	private String pId ;
	/**
	 * 采购订单行号
	*/
	@Column(name="pur_row_num")
	@Title("采购订单行号")
	private int purRowNum ;
	/**
	 * 采购单号
	 * 医疗机构申请单号
	*/
	@Size(max=36)
	@Column(name="pur_bill_id")
	@Title("采购单号")
	private String purBillId ;
	/**
	 * 请购单号
	 * 科室申请单号
	*/
	@Size(max=36)
	@Column(name="apply_bill_id")
	@Title("请购单号")
	private String applyBillId ;
	/**
	 * 销售单号
	 * 配送机构对应的销售单号
	*/
	@Size(max=50)
	@Column(name="sale_bill_id")
	@Title("销售单号")
	private String saleBillId ;
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
	 * 采购订单日期
	*/
	@Column(name="pur_date")
	@Title("采购订单日期")
	private java.util.Date purDate ;
	/**
	 * 医药医材ERP编号
	 * 医药医材在ERP中的编号
	*/
	@Size(max=50)
	@Column(name="goods_erp_code")
	@Title("医药医材ERP编号")
	private String goodsErpCode ;
	/**
	 * 医药医材名称
	*/
	@Size(max=100)
	@Column(name="goods_name")
	@Title("医药医材名称")
	private String goodsName ;
	/**
	 * 规格
	*/
	@Size(max=50)
	@Column(name="goods_gg")
	@Title("规格")
	private String goodsGg ;
	/**
	 * 生产厂商
	*/
	@Size(max=100)
	@Column(name="goods_mfrs_name")
	@Title("生产厂商")
	private String goodsMfrsName ;
	/**
	 * 产地
	*/
	@Size(max=100)
	@Column(name="made")
	@Title("产地")
	private String made ;
	/**
	 * 单位
	*/
	@Size(max=50)
	@Column(name="unit")
	@Title("单位")
	private String unit ;
	/**
	 * 批号
	*/
	@Size(max=50)
	@Column(name="batch_no")
	@Title("批号")
	private String batchNo ;
	/**
	 * 灭菌日期
	*/
	@Column(name="sterilization_date")
	@Title("灭菌日期")
	private java.util.Date sterilizationDate ;
	/**
	 * 灭菌批号
	*/
	@Size(max=50)
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
	 * 生产日期
	*/
	@Column(name="produce_date")
	@Title("生产日期")
	private java.util.Date produceDate ;
	/**
	 * 包装
	*/
	@Column(name="packet_num")
	@Title("包装")
	private java.math.BigDecimal packetNum ;
	/**
	 * 数量
	*/
	@Column(name="qty")
	@Title("数量")
	private java.math.BigDecimal qty ;
	/**
	 * 单价
	 * 请购单价
	*/
	@Column(name="price")
	@Title("单价")
	private java.math.BigDecimal price ;
	/**
	 * 出库库区
	*/
	@Size(max=50)
	@Column(name="out_storearea")
	@Title("出库库区")
	private String outStorearea ;
	/**
	 * 发货日期
	*/
	@Column(name="send_date")
	@Title("发货日期")
	private java.util.Date sendDate ;
	/**
	 * 说明
	*/
	@Size(max=200)
	@Column(name="remark")
	@Title("说明")
	private String remark ;
	/**
	 * 条码
	*/
	@Size(max=50)
	@Column(name="barcode")
	@Title("条码")
	private String barcode ;
	/**
	 * 定量包装
	*/
	@Size(max=50)
	@Column(name="packet_code")
	@Title("定量包装")
	private String packetCode ;
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
	public String getPId ()
	{
		return pId ;
	}
	
	public void setPId (String pId )
	{
		this.pId = pId;
	}
	public int getPurRowNum ()
	{
		return purRowNum ;
	}
	
	public void setPurRowNum (int purRowNum )
	{
		this.purRowNum = purRowNum;
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
	public String getSaleBillId ()
	{
		return saleBillId ;
	}
	
	public void setSaleBillId (String saleBillId )
	{
		this.saleBillId = saleBillId;
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
	public java.util.Date getPurDate ()
	{
		return purDate ;
	}
	
	public void setPurDate (java.util.Date purDate )
	{
		this.purDate = purDate;
	}
	public String getGoodsErpCode ()
	{
		return goodsErpCode ;
	}
	
	public void setGoodsErpCode (String goodsErpCode )
	{
		this.goodsErpCode = goodsErpCode;
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
	public String getGoodsMfrsName ()
	{
		return goodsMfrsName ;
	}
	
	public void setGoodsMfrsName (String goodsMfrsName )
	{
		this.goodsMfrsName = goodsMfrsName;
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
	public String getBatchNo ()
	{
		return batchNo ;
	}
	
	public void setBatchNo (String batchNo )
	{
		this.batchNo = batchNo;
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
	public java.util.Date getProduceDate ()
	{
		return produceDate ;
	}
	
	public void setProduceDate (java.util.Date produceDate )
	{
		this.produceDate = produceDate;
	}
	public java.math.BigDecimal getPacketNum ()
	{
		return packetNum ;
	}
	
	public void setPacketNum (java.math.BigDecimal packetNum )
	{
		this.packetNum = packetNum;
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
	public String getOutStorearea ()
	{
		return outStorearea ;
	}
	
	public void setOutStorearea (String outStorearea )
	{
		this.outStorearea = outStorearea;
	}
	public java.util.Date getSendDate ()
	{
		return sendDate ;
	}
	
	public void setSendDate (java.util.Date sendDate )
	{
		this.sendDate = sendDate;
	}
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
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


	@Override
	public String toString() {
		return "PassDistrBillListSheet [" +" id=" +  id   +", pId=" +  pId   +", purRowNum=" +  purRowNum   +", purBillId=" +  purBillId   +", applyBillId=" +  applyBillId   +", saleBillId=" +  saleBillId   +", checkCode=" +  checkCode   +", transBillId=" +  transBillId   +", purDate=" +  purDate   +", goodsErpCode=" +  goodsErpCode   +", goodsName=" +  goodsName   +", goodsGg=" +  goodsGg   +", goodsMfrsName=" +  goodsMfrsName   +", made=" +  made   +", unit=" +  unit   +", batchNo=" +  batchNo   +", sterilizationDate=" +  sterilizationDate   +", sterilizationCode=" +  sterilizationCode   +", sterilizationEndDate=" +  sterilizationEndDate   +", expdtEndDate=" +  expdtEndDate   +", produceDate=" +  produceDate   +", packetNum=" +  packetNum   +", qty=" +  qty   +", price=" +  price   +", outStorearea=" +  outStorearea   +", sendDate=" +  sendDate   +", remark=" +  remark   +", barcode=" +  barcode   +", packetCode=" +  packetCode   +", invoicesCode=" +  invoicesCode   +", invoicesDate=" +  invoicesDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}