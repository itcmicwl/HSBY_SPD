
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 医院出库结算单
*/
@Table(name="hos_out_balance")
public class HosOutBalance implements IBean
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
	 * 单号
	*/
	@Size(max=36)
	@Column(name="bill_id")
	@Title("单号")
	private String billId ;
	/**
	 * SPD系统标识
	*/
	@Size(max=36)
	@Column(name="spd_code")
	@Title("SPD系统标识")
	private String spdCode ;
	/**
	 * 付款单位
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("付款单位")
	private String hosId ;
	/**
	 * 付款单位名称
	*/
	@Size(max=200)
	@Column(name="hos_name")
	@Title("付款单位名称")
	private String hosName ;
	/**
	 * 付款单位ERP编号
	*/
	@Size(max=36)
	@Column(name="hos_erp_code")
	@Title("付款单位ERP编号")
	private String hosErpCode ;
	/**
	 * 结算单位
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("结算单位")
	private String provId ;
	/**
	 * 结算单位名称
	*/
	@Size(max=200)
	@Column(name="prov_name")
	@Title("结算单位名称")
	private String provName ;
	/**
	 * 结算单位ERP编号
	*/
	@Size(max=36)
	@Column(name="prov_erp_code")
	@Title("结算单位ERP编号")
	private String provErpCode ;
	/**
	 * 结算单位ERP部门编号
	*/
	@Size(max=36)
	@Column(name="prov_dept_erp_code")
	@Title("结算单位ERP部门编号")
	private String provDeptErpCode ;
	/**
	 * 制单人
	*/
	@Size(max=36)
	@Column(name="filler")
	@Title("制单人")
	private String filler ;
	/**
	 * 制单日期
	*/
	@Column(name="fill_date")
	@Title("制单日期")
	private java.util.Date fillDate ;
	/**
	 * 备注
	*/
	@Size(max=500)
	@Column(name="remark")
	@Title("备注")
	private String remark ;
	/**
	 * 笔数
	*/
	@Column(name="sum_row")
	@Title("笔数")
	private int sumRow ;
	/**
	 * 状态
	*/
	@Column(name="status")
	@Title("状态")
	private int status ;
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
	public String getBillId ()
	{
		return billId ;
	}
	
	public void setBillId (String billId )
	{
		this.billId = billId;
	}
	public String getSpdCode ()
	{
		return spdCode ;
	}
	
	public void setSpdCode (String spdCode )
	{
		this.spdCode = spdCode;
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
	public String getHosErpCode ()
	{
		return hosErpCode ;
	}
	
	public void setHosErpCode (String hosErpCode )
	{
		this.hosErpCode = hosErpCode;
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
	public String getProvDeptErpCode ()
	{
		return provDeptErpCode ;
	}
	
	public void setProvDeptErpCode (String provDeptErpCode )
	{
		this.provDeptErpCode = provDeptErpCode;
	}
	public String getFiller ()
	{
		return filler ;
	}
	
	public void setFiller (String filler )
	{
		this.filler = filler;
	}
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
	}
	public int getSumRow ()
	{
		return sumRow ;
	}
	
	public void setSumRow (int sumRow )
	{
		this.sumRow = sumRow;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
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
		return "HosOutBalance [" +" id=" +  id   +", billId=" +  billId   +", spdCode=" +  spdCode   +", hosId=" +  hosId   +", hosName=" +  hosName   +", hosErpCode=" +  hosErpCode   +", provId=" +  provId   +", provName=" +  provName   +", provErpCode=" +  provErpCode   +", provDeptErpCode=" +  provDeptErpCode   +", filler=" +  filler   +", fillDate=" +  fillDate   +", remark=" +  remark   +", sumRow=" +  sumRow   +", status=" +  status   +", invoiceNumber=" +  invoiceNumber   +", invoiceCode=" +  invoiceCode   +", saleMan=" +  saleMan   +", settleAmount=" +  settleAmount   +", invoiceAmount=" +  invoiceAmount   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}