
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 退货单主表
*/
@Table(name="returned_goods")
public class ReturnedGoods implements IBean
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
	 * 源单号
	*/
	@Size(max=36)
	@Column(name="source_bill_id")
	@Title("源单号")
	private String sourceBillId ;
	/**
	 * 退货机构ID
	*/
	@Size(max=36)
	@Column(name="exc_org_id")
	@Title("退货机构ID")
	private String excOrgId ;
	/**
	 * 退货机构名称
	*/
	@Size(max=200)
	@Column(name="exc_org_name")
	@Title("退货机构名称")
	private String excOrgName ;
	/**
	 * 退货科室ID
	*/
	@Size(max=36)
	@Column(name="exc_dept_id")
	@Title("退货科室ID")
	private String excDeptId ;
	/**
	 * 退货科室名称
	*/
	@Size(max=200)
	@Column(name="exc_dept_name")
	@Title("退货科室名称")
	private String excDeptName ;
	/**
	 * 退货库房ID
	*/
	@Size(max=36)
	@Column(name="exc_stoc_id")
	@Title("退货库房ID")
	private String excStocId ;
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
	@Column(name="accounnter")
	@Title("记账人")
	private String accounnter ;
	/**
	 * 记账时间
	*/
	@Column(name="account_date")
	@Title("记账时间")
	private java.util.Date accountDate ;
	/**
	 * 状态
	*/
	@Column(name="status")
	@Title("状态")
	private int status ;
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
	public String getExcOrgId ()
	{
		return excOrgId ;
	}
	
	public void setExcOrgId (String excOrgId )
	{
		this.excOrgId = excOrgId;
	}
	public String getExcOrgName ()
	{
		return excOrgName ;
	}
	
	public void setExcOrgName (String excOrgName )
	{
		this.excOrgName = excOrgName;
	}
	public String getExcDeptId ()
	{
		return excDeptId ;
	}
	
	public void setExcDeptId (String excDeptId )
	{
		this.excDeptId = excDeptId;
	}
	public String getExcDeptName ()
	{
		return excDeptName ;
	}
	
	public void setExcDeptName (String excDeptName )
	{
		this.excDeptName = excDeptName;
	}
	public String getExcStocId ()
	{
		return excStocId ;
	}
	
	public void setExcStocId (String excStocId )
	{
		this.excStocId = excStocId;
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
	public String getAccounnter ()
	{
		return accounnter ;
	}
	
	public void setAccounnter (String accounnter )
	{
		this.accounnter = accounnter;
	}
	public java.util.Date getAccountDate ()
	{
		return accountDate ;
	}
	
	public void setAccountDate (java.util.Date accountDate )
	{
		this.accountDate = accountDate;
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


	@Override
	public String toString() {
		return "ReturnedGoods [" +" id=" +  id   +", billId=" +  billId   +", sourceBillId=" +  sourceBillId   +", excOrgId=" +  excOrgId   +", excOrgName=" +  excOrgName   +", excDeptId=" +  excDeptId   +", excDeptName=" +  excDeptName   +", excStocId=" +  excStocId   +", fillDate=" +  fillDate   +", filler=" +  filler   +", auditor=" +  auditor   +", auditDate=" +  auditDate   +", accounnter=" +  accounnter   +", accountDate=" +  accountDate   +", status=" +  status   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}