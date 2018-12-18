
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 采购确认单明细
*/
@Table(name="purconfirm_list")
public class PurconfirmList implements IBean
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
	 * 请购科室
	*/
	@Size(max=36)
	@Column(name="apply_dept_id")
	@Title("请购科室")
	private String applyDeptId ;
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
	@Column(name="pur_bill_rownum")
	@Title("采购订单行号")
	private int purBillRownum ;
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
	 * 科室请购单行号
	*/
	@Column(name="apply_row_number")
	@Title("科室请购单行号")
	private int applyRowNumber ;
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
	 * 配送单价
	*/
	@Column(name="prov_goods_erp_price")
	@Title("配送单价")
	private java.math.BigDecimal provGoodsErpPrice ;
	/**
	 * 供应商单位
	*/
	@Size(max=36)
	@Column(name="unit")
	@Title("供应商单位")
	private String unit ;
	/**
	 * 请购单价
	*/
	@JoinColumn(name="hos_price",table="hos_goods_info",referencedColumnName="price")
	@Title("请购单价")
	private java.math.BigDecimal hosPrice ;
	/**
	 * 院内单位
	*/
	@Size(max=36)
	@JoinColumn(name="hos_unit",table="hos_goods_info",referencedColumnName="unit")
	@Title("院内单位")
	private String hosUnit ;
	/**
	 * 转换比率
	*/
	@Column(name="hos_scale")
	@Title("转换比率")
	private java.math.BigDecimal hosScale ;
	/**
	 * 包装数量
	*/
	@Column(name="packet_code")
	@Title("包装数量")
	private java.math.BigDecimal packetCode ;
	/**
	 * 院内单位请购数量
	*/
	@Column(name="hos_apply_qty")
	@Title("院内单位请购数量")
	private java.math.BigDecimal hosApplyQty ;
	/**
	 * 请购数量
	*/
	@Column(name="apply_qty")
	@Title("请购数量")
	private java.math.BigDecimal applyQty ;
	/**
	 * 院内单位采购数量
	*/
	@Column(name="hos_pur_qty")
	@Title("院内单位采购数量")
	private java.math.BigDecimal hosPurQty ;
	/**
	 * 采购数量
	*/
	@Column(name="pur_qty")
	@Title("采购数量")
	private java.math.BigDecimal purQty ;
	/**
	 * 院内单位数量
	*/
	@Column(name="unit_qty")
	@Title("院内单位数量")
	private java.math.BigDecimal unitQty ;
	/**
	 * 数量
	*/
	@Column(name="qty")
	@Title("数量")
	private java.math.BigDecimal qty ;
	/**
	 * 税率
	*/
	@Column(name="qr_tax")
	@Title("税率")
	private java.math.BigDecimal qrTax ;
	/**
	 * 采购不含税单价
	*/
	@Column(name="qr_tax_price")
	@Title("采购不含税单价")
	private java.math.BigDecimal qrTaxPrice ;
	/**
	 * 采购不含税金额
	*/
	@Column(name="qr_tax_amount")
	@Title("采购不含税金额")
	private java.math.BigDecimal qrTaxAmount ;
	/**
	 * 采购含税金额
	*/
	@Column(name="qr_amount")
	@Title("采购含税金额")
	private java.math.BigDecimal qrAmount ;
	/**
	 * 税额
	*/
	@Column(name="qr_tax_money")
	@Title("税额")
	private java.math.BigDecimal qrTaxMoney ;
	/**
	 * 配送数量
	*/
	@Column(name="send_qty")
	@Title("配送数量")
	private java.math.BigDecimal sendQty ;
	/**
	 * 当前审批级别
	*/
	@Column(name="status")
	@Title("当前审批级别")
	private int status ;
	/**
	 * 科室请购日期
	*/
	@Column(name="apply_date")
	@Title("科室请购日期")
	private java.util.Date applyDate ;
	/**
	 * 预计到货日期
	*/
	@Column(name="es_arrival_date")
	@Title("预计到货日期")
	private java.util.Date esArrivalDate ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 唯一码管理方式
	*/
	@Column(name="unique_kind")
	@Title("唯一码管理方式")
	private int uniqueKind ;
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
	public String getApplyDeptId ()
	{
		return applyDeptId ;
	}
	
	public void setApplyDeptId (String applyDeptId )
	{
		this.applyDeptId = applyDeptId;
	}
	public String getPurBillId ()
	{
		return purBillId ;
	}
	
	public void setPurBillId (String purBillId )
	{
		this.purBillId = purBillId;
	}
	public int getPurBillRownum ()
	{
		return purBillRownum ;
	}
	
	public void setPurBillRownum (int purBillRownum )
	{
		this.purBillRownum = purBillRownum;
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
	public int getApplyRowNumber ()
	{
		return applyRowNumber ;
	}
	
	public void setApplyRowNumber (int applyRowNumber )
	{
		this.applyRowNumber = applyRowNumber;
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
	public java.math.BigDecimal getProvGoodsErpPrice ()
	{
		return provGoodsErpPrice ;
	}
	
	public void setProvGoodsErpPrice (java.math.BigDecimal provGoodsErpPrice )
	{
		this.provGoodsErpPrice = provGoodsErpPrice;
	}
	public String getUnit ()
	{
		return unit ;
	}
	
	public void setUnit (String unit )
	{
		this.unit = unit;
	}
	public java.math.BigDecimal getHosPrice ()
	{
		return hosPrice ;
	}
	
	public void setHosPrice (java.math.BigDecimal hosPrice )
	{
		this.hosPrice = hosPrice;
	}
	public String getHosUnit ()
	{
		return hosUnit ;
	}
	
	public void setHosUnit (String hosUnit )
	{
		this.hosUnit = hosUnit;
	}
	public java.math.BigDecimal getHosScale ()
	{
		return hosScale ;
	}
	
	public void setHosScale (java.math.BigDecimal hosScale )
	{
		this.hosScale = hosScale;
	}
	public java.math.BigDecimal getPacketCode ()
	{
		return packetCode ;
	}
	
	public void setPacketCode (java.math.BigDecimal packetCode )
	{
		this.packetCode = packetCode;
	}
	public java.math.BigDecimal getHosApplyQty ()
	{
		return hosApplyQty ;
	}
	
	public void setHosApplyQty (java.math.BigDecimal hosApplyQty )
	{
		this.hosApplyQty = hosApplyQty;
	}
	public java.math.BigDecimal getApplyQty ()
	{
		return applyQty ;
	}
	
	public void setApplyQty (java.math.BigDecimal applyQty )
	{
		this.applyQty = applyQty;
	}
	public java.math.BigDecimal getHosPurQty ()
	{
		return hosPurQty ;
	}
	
	public void setHosPurQty (java.math.BigDecimal hosPurQty )
	{
		this.hosPurQty = hosPurQty;
	}
	public java.math.BigDecimal getPurQty ()
	{
		return purQty ;
	}
	
	public void setPurQty (java.math.BigDecimal purQty )
	{
		this.purQty = purQty;
	}
	public java.math.BigDecimal getUnitQty ()
	{
		return unitQty ;
	}
	
	public void setUnitQty (java.math.BigDecimal unitQty )
	{
		this.unitQty = unitQty;
	}
	public java.math.BigDecimal getQty ()
	{
		return qty ;
	}
	
	public void setQty (java.math.BigDecimal qty )
	{
		this.qty = qty;
	}
	public java.math.BigDecimal getQrTax ()
	{
		return qrTax ;
	}
	
	public void setQrTax (java.math.BigDecimal qrTax )
	{
		this.qrTax = qrTax;
	}
	public java.math.BigDecimal getQrTaxPrice ()
	{
		return qrTaxPrice ;
	}
	
	public void setQrTaxPrice (java.math.BigDecimal qrTaxPrice )
	{
		this.qrTaxPrice = qrTaxPrice;
	}
	public java.math.BigDecimal getQrTaxAmount ()
	{
		return qrTaxAmount ;
	}
	
	public void setQrTaxAmount (java.math.BigDecimal qrTaxAmount )
	{
		this.qrTaxAmount = qrTaxAmount;
	}
	public java.math.BigDecimal getQrAmount ()
	{
		return qrAmount ;
	}
	
	public void setQrAmount (java.math.BigDecimal qrAmount )
	{
		this.qrAmount = qrAmount;
	}
	public java.math.BigDecimal getQrTaxMoney ()
	{
		return qrTaxMoney ;
	}
	
	public void setQrTaxMoney (java.math.BigDecimal qrTaxMoney )
	{
		this.qrTaxMoney = qrTaxMoney;
	}
	public java.math.BigDecimal getSendQty ()
	{
		return sendQty ;
	}
	
	public void setSendQty (java.math.BigDecimal sendQty )
	{
		this.sendQty = sendQty;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public java.util.Date getApplyDate ()
	{
		return applyDate ;
	}
	
	public void setApplyDate (java.util.Date applyDate )
	{
		this.applyDate = applyDate;
	}
	public java.util.Date getEsArrivalDate ()
	{
		return esArrivalDate ;
	}
	
	public void setEsArrivalDate (java.util.Date esArrivalDate )
	{
		this.esArrivalDate = esArrivalDate;
	}
	public java.util.Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (java.util.Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public int getUniqueKind ()
	{
		return uniqueKind ;
	}
	
	public void setUniqueKind (int uniqueKind )
	{
		this.uniqueKind = uniqueKind;
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
		return "PurconfirmList [" +" id=" +  id   +", pid=" +  pid   +", billId=" +  billId   +", rowNumber=" +  rowNumber   +", applyDeptId=" +  applyDeptId   +", purBillId=" +  purBillId   +", purBillRownum=" +  purBillRownum   +", purBillDate=" +  purBillDate   +", applyBillId=" +  applyBillId   +", applyRowNumber=" +  applyRowNumber   +", provGoodsId=" +  provGoodsId   +", goodsGg=" +  goodsGg   +", goodsMrfsId=" +  goodsMrfsId   +", made=" +  made   +", hosGoodsId=" +  hosGoodsId   +", hosGoodsCode=" +  hosGoodsCode   +", hosGoodsName=" +  hosGoodsName   +", provGoodsErpCode=" +  provGoodsErpCode   +", provGoodsErpName=" +  provGoodsErpName   +", provGoodsErpPrice=" +  provGoodsErpPrice   +", unit=" +  unit   +", hosPrice=" +  hosPrice   +", hosUnit=" +  hosUnit   +", hosScale=" +  hosScale   +", packetCode=" +  packetCode   +", hosApplyQty=" +  hosApplyQty   +", applyQty=" +  applyQty   +", hosPurQty=" +  hosPurQty   +", purQty=" +  purQty   +", unitQty=" +  unitQty   +", qty=" +  qty   +", qrTax=" +  qrTax   +", qrTaxPrice=" +  qrTaxPrice   +", qrTaxAmount=" +  qrTaxAmount   +", qrAmount=" +  qrAmount   +", qrTaxMoney=" +  qrTaxMoney   +", sendQty=" +  sendQty   +", status=" +  status   +", applyDate=" +  applyDate   +", esArrivalDate=" +  esArrivalDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", uniqueKind=" +  uniqueKind   +", version=" +  version   
			 + "]";
	}

}