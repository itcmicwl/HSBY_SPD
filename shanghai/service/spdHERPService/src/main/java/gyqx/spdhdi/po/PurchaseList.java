
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 采购单明细
*/
@Table(name="purchase_list")
public class PurchaseList implements IBean
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
	 * 业务员ID
	*/
	@Size(max=36)
	@Column(name="sale_man")
	@Title("业务员ID")
	private String saleMan ;
	/**
	 * 医院产品ID
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_id",table="hos_goods_info",referencedColumnName="id")
	@Title("医院产品ID")
	private String hosGoodsId ;
	/**
	 * 医院产品编号
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_code",table="hos_goods_info",referencedColumnName="code")
	@Title("医院产品编号")
	private String hosGoodsCode ;
	/**
	 * 医院内部名称
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_name",table="hos_goods_info",referencedColumnName="goods_name")
	@Title("医院内部名称")
	private String hosGoodsName ;
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
	 * 院内单位数量（确认）
	*/
	@Column(name="unit_qty")
	@Title("院内单位数量（确认）")
	private java.math.BigDecimal unitQty ;
	/**
	 * 数量（确认）
	*/
	@Column(name="qty")
	@Title("数量（确认）")
	private java.math.BigDecimal qty ;
	/**
	 * 税率
	*/
	@Column(name="pur_tax")
	@Title("税率")
	private java.math.BigDecimal purTax ;
	/**
	 * 采购不含税单价
	*/
	@Column(name="pur_tax_price")
	@Title("采购不含税单价")
	private java.math.BigDecimal purTaxPrice ;
	/**
	 * 采购不含税金额
	*/
	@Column(name="pur_tax_amount")
	@Title("采购不含税金额")
	private java.math.BigDecimal purTaxAmount ;
	/**
	 * 采购含税金额
	*/
	@Column(name="pur_amount")
	@Title("采购含税金额")
	private java.math.BigDecimal purAmount ;
	/**
	 * 税额
	*/
	@Column(name="pur_tax_money")
	@Title("税额")
	private java.math.BigDecimal purTaxMoney ;
	/**
	 * 合同编号
	*/
	@Size(max=36)
	@Column(name="contract_code")
	@Title("合同编号")
	private String contractCode ;
	/**
	 * 当前审批级别
	*/
	@Column(name="status")
	@Title("当前审批级别")
	private int status ;
	/**
	 * 供应商
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("供应商")
	private String provId ;
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
	 * 备注
	*/
	@Size(max=500)
	@Column(name="remark")
	@Title("备注")
	private String remark ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 到货日期
	*/
	@Column(name="receive_date")
	@Title("到货日期")
	private java.util.Date receiveDate ;
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
	public String getSaleMan ()
	{
		return saleMan ;
	}
	
	public void setSaleMan (String saleMan )
	{
		this.saleMan = saleMan;
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
	public java.math.BigDecimal getPurTax ()
	{
		return purTax ;
	}
	
	public void setPurTax (java.math.BigDecimal purTax )
	{
		this.purTax = purTax;
	}
	public java.math.BigDecimal getPurTaxPrice ()
	{
		return purTaxPrice ;
	}
	
	public void setPurTaxPrice (java.math.BigDecimal purTaxPrice )
	{
		this.purTaxPrice = purTaxPrice;
	}
	public java.math.BigDecimal getPurTaxAmount ()
	{
		return purTaxAmount ;
	}
	
	public void setPurTaxAmount (java.math.BigDecimal purTaxAmount )
	{
		this.purTaxAmount = purTaxAmount;
	}
	public java.math.BigDecimal getPurAmount ()
	{
		return purAmount ;
	}
	
	public void setPurAmount (java.math.BigDecimal purAmount )
	{
		this.purAmount = purAmount;
	}
	public java.math.BigDecimal getPurTaxMoney ()
	{
		return purTaxMoney ;
	}
	
	public void setPurTaxMoney (java.math.BigDecimal purTaxMoney )
	{
		this.purTaxMoney = purTaxMoney;
	}
	public String getContractCode ()
	{
		return contractCode ;
	}
	
	public void setContractCode (String contractCode )
	{
		this.contractCode = contractCode;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
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
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
	}
	public java.util.Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (java.util.Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public java.util.Date getReceiveDate ()
	{
		return receiveDate ;
	}
	
	public void setReceiveDate (java.util.Date receiveDate )
	{
		this.receiveDate = receiveDate;
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
		return "PurchaseList [" +" id=" +  id   +", pid=" +  pid   +", billId=" +  billId   +", rowNumber=" +  rowNumber   +", applyDeptId=" +  applyDeptId   +", applyBillId=" +  applyBillId   +", applyRowNumber=" +  applyRowNumber   +", saleMan=" +  saleMan   +", hosGoodsId=" +  hosGoodsId   +", hosGoodsCode=" +  hosGoodsCode   +", hosGoodsName=" +  hosGoodsName   +", hosPrice=" +  hosPrice   +", hosUnit=" +  hosUnit   +", hosScale=" +  hosScale   +", packetCode=" +  packetCode   +", hosApplyQty=" +  hosApplyQty   +", applyQty=" +  applyQty   +", hosPurQty=" +  hosPurQty   +", purQty=" +  purQty   +", unitQty=" +  unitQty   +", qty=" +  qty   +", purTax=" +  purTax   +", purTaxPrice=" +  purTaxPrice   +", purTaxAmount=" +  purTaxAmount   +", purAmount=" +  purAmount   +", purTaxMoney=" +  purTaxMoney   +", contractCode=" +  contractCode   +", status=" +  status   +", provId=" +  provId   +", applyDate=" +  applyDate   +", esArrivalDate=" +  esArrivalDate   +", remark=" +  remark   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", receiveDate=" +  receiveDate   +", uniqueKind=" +  uniqueKind   +", version=" +  version   
			 + "]";
	}

}