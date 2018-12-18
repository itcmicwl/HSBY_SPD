
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 入库单主表
*/
@Table(name="in_stock")
public class InStock implements IBean
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
	 * 入库类型
	*/
	@Size(max=10)
	@Column(name="in_stock_kind")
	@Title("入库类型")
	private String inStockKind ;
	/**
	 * 入库方式
	 * 10--实采，20--虚采
	*/
	@Column(name="in_stock_type")
	@Title("入库方式")
	private int inStockType ;
	/**
	 * 出库单号
	*/
	@Size(max=36)
	@Column(name="out_bill_id")
	@Title("出库单号")
	private String outBillId ;
	/**
	 * 出库机构ID
	*/
	@Size(max=36)
	@Column(name="out_org_id")
	@Title("出库机构ID")
	private String outOrgId ;
	/**
	 * 出库机构名称
	*/
	@Size(max=200)
	@Column(name="out_org_name")
	@Title("出库机构名称")
	private String outOrgName ;
	/**
	 * 出库科室ID
	*/
	@Size(max=36)
	@Column(name="out_dept_id")
	@Title("出库科室ID")
	private String outDeptId ;
	/**
	 * 出库科室名称
	*/
	@Size(max=200)
	@Column(name="out_dept_name")
	@Title("出库科室名称")
	private String outDeptName ;
	/**
	 * 出库库房
	*/
	@Size(max=36)
	@Column(name="out_stoc_id")
	@Title("出库库房")
	private String outStocId ;
	/**
	 * 入库机构ID
	*/
	@Size(max=36)
	@Column(name="in_org_id")
	@Title("入库机构ID")
	private String inOrgId ;
	/**
	 * 入库机构名称
	*/
	@Size(max=200)
	@Column(name="in_org_name")
	@Title("入库机构名称")
	private String inOrgName ;
	/**
	 * 入库科室ID
	*/
	@Size(max=36)
	@Column(name="in_dept_id")
	@Title("入库科室ID")
	private String inDeptId ;
	/**
	 * 入库科室名称
	*/
	@Size(max=200)
	@Column(name="in_dept_name")
	@Title("入库科室名称")
	private String inDeptName ;
	/**
	 * 入库库房
	*/
	@Size(max=36)
	@Column(name="in_stoc_id")
	@Title("入库库房")
	private String inStocId ;
	/**
	 * 制单日期
	*/
	@Column(name="fill_date")
	@Title("制单日期")
	private java.util.Date fillDate ;
	/**
	 * 制单人
	*/
	@Size(max=36)
	@Column(name="filler")
	@Title("制单人")
	private String filler ;
	/**
	 * 审核人
	*/
	@Size(max=36)
	@Column(name="auditor")
	@Title("审核人")
	private String auditor ;
	/**
	 * 审核时间
	*/
	@Column(name="audit_date")
	@Title("审核时间")
	private java.util.Date auditDate ;
	/**
	 * 记账人
	*/
	@Size(max=36)
	@Column(name="accounter")
	@Title("记账人")
	private String accounter ;
	/**
	 * 记账时间
	*/
	@Column(name="account_date")
	@Title("记账时间")
	private java.util.Date accountDate ;
	/**
	 * 配送单号
	*/
	@Size(max=36)
	@Column(name="send_billid")
	@Title("配送单号")
	private String sendBillid ;
	/**
	 * 状态
	*/
	@Column(name="status")
	@Title("状态")
	private int status ;
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
	
	/**
	 * 发票号
	*/
	@Size(max=36)
	@Column(name="invoice_number")
	@Title("发票号")
	private String invoiceNumber ;
	
	/**
	 * 发票代码
	*/
	@Size(max=36)
	@Column(name="invoice_code")
	@Title("发票代码")
	private String invoiceCode ;

	@Size(max = 128)
	@Column(name="remark")
	@Title("")
	private String remark;
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

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
	public String getInStockKind ()
	{
		return inStockKind ;
	}
	
	public void setInStockKind (String inStockKind )
	{
		this.inStockKind = inStockKind;
	}
	public int getInStockType ()
	{
		return inStockType ;
	}
	
	public void setInStockType (int inStockType )
	{
		this.inStockType = inStockType;
	}
	public String getOutBillId ()
	{
		return outBillId ;
	}
	
	public void setOutBillId (String outBillId )
	{
		this.outBillId = outBillId;
	}
	public String getOutOrgId ()
	{
		return outOrgId ;
	}
	
	public void setOutOrgId (String outOrgId )
	{
		this.outOrgId = outOrgId;
	}
	public String getOutOrgName ()
	{
		return outOrgName ;
	}
	
	public void setOutOrgName (String outOrgName )
	{
		this.outOrgName = outOrgName;
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
	public String getOutStocId ()
	{
		return outStocId ;
	}
	
	public void setOutStocId (String outStocId )
	{
		this.outStocId = outStocId;
	}
	public String getInOrgId ()
	{
		return inOrgId ;
	}
	
	public void setInOrgId (String inOrgId )
	{
		this.inOrgId = inOrgId;
	}
	public String getInOrgName ()
	{
		return inOrgName ;
	}
	
	public void setInOrgName (String inOrgName )
	{
		this.inOrgName = inOrgName;
	}
	public String getInDeptId ()
	{
		return inDeptId ;
	}
	
	public void setInDeptId (String inDeptId )
	{
		this.inDeptId = inDeptId;
	}
	public String getInDeptName ()
	{
		return inDeptName ;
	}
	
	public void setInDeptName (String inDeptName )
	{
		this.inDeptName = inDeptName;
	}
	public String getInStocId ()
	{
		return inStocId ;
	}
	
	public void setInStocId (String inStocId )
	{
		this.inStocId = inStocId;
	}
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public String getFiller ()
	{
		return filler ;
	}
	
	public void setFiller (String filler )
	{
		this.filler = filler;
	}
	public String getAuditor ()
	{
		return auditor ;
	}
	
	public void setAuditor (String auditor )
	{
		this.auditor = auditor;
	}
	public java.util.Date getAuditDate ()
	{
		return auditDate ;
	}
	
	public void setAuditDate (java.util.Date auditDate )
	{
		this.auditDate = auditDate;
	}
	public String getAccounter ()
	{
		return accounter ;
	}
	
	public void setAccounter (String accounter )
	{
		this.accounter = accounter;
	}
	public java.util.Date getAccountDate ()
	{
		return accountDate ;
	}
	
	public void setAccountDate (java.util.Date accountDate )
	{
		this.accountDate = accountDate;
	}
	public String getSendBillid ()
	{
		return sendBillid ;
	}
	
	public void setSendBillid (String sendBillid )
	{
		this.sendBillid = sendBillid;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "InStock [" +" id=" +  id   +", billId=" +  billId   +", inStockKind=" +  inStockKind   +", inStockType=" +  inStockType   +", outBillId=" +  outBillId   +", outOrgId=" +  outOrgId   +", outOrgName=" +  outOrgName   +", outDeptId=" +  outDeptId   +", outDeptName=" +  outDeptName   +", outStocId=" +  outStocId   +", inOrgId=" +  inOrgId   +", inOrgName=" +  inOrgName   +", inDeptId=" +  inDeptId   +", inDeptName=" +  inDeptName   +", inStocId=" +  inStocId   +", fillDate=" +  fillDate   +", filler=" +  filler   +", auditor=" +  auditor   +", auditDate=" +  auditDate   +", accounter=" +  accounter   +", accountDate=" +  accountDate   +", sendBillid=" +  sendBillid   +", status=" +  status   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + ", invoiceNumber=" +  invoiceNumber + ", invoiceCode=" +  invoiceCode  +"]";
	}

}