
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 科室请退单
*/
@Table(name="return_bill")
public class ReturnBill implements IBean
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
	 * 结算单位ID
	*/
	@Size(max=36)
	@Column(name="balance_org_id")
	@Title("结算单位ID")
	private String balanceOrgId ;
	/**
	 * 建档时间
	*/
	@Column(name="fill_date")
	@Title("建档时间")
	private java.util.Date fillDate ;
	/**
	 * 请退机构
	*/
	@Size(max=36)
	@JoinColumn(name="return_company_id",table="bas_company_info",referencedColumnName="id")
	@Title("请退机构")
	private String returnCompanyId ;
	/**
	 * 请退科室
	*/
	@Size(max=36)
	@JoinColumn(name="return_org_id",table="sys_org",referencedColumnName="id")
	@Title("请退科室")
	private String returnOrgId ;
	/**
	 * 出库科室
	*/
	@Size(max=36)
	@JoinColumn(name="out_org_id",table="sys_org",referencedColumnName="id")
	@Title("出库科室")
	private String outOrgId ;
	/**
	 * 配送机构
	*/
	@Size(max=36)
	@JoinColumn(name="prov_id",table="bas_company_info",referencedColumnName="id")
	@Title("配送机构")
	private String provId ;
	/**
	 * 配送关系类型
	 * 先空着
	*/
	@Size(max=36)
	@Column(name="distr_relation")
	@Title("配送关系类型")
	private String distrRelation ;
	/**
	 * 制单人
	*/
	@Size(max=36)
	@Column(name="fillter")
	@Title("制单人")
	private String fillter ;
	/**
	 * 说明
	*/
	@Size(max=300)
	@Column(name="memo")
	@Title("说明")
	private String memo ;
	/**
	 * 启用采购发票管理
	*/
	@Size(max=1)
	@Column(name="is_start_invoice")
	@Title("启用采购发票管理")
	private String isStartInvoice ;
	/**
	 * 笔数
	*/
	@Column(name="sum_row")
	@Title("笔数")
	private int sumRow ;
	/**
	 * 当前审批级别
	 * 保存：10，提交：20
	*/
	@Column(name="status")
	@Title("当前审批级别")
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
	public String getBalanceOrgId ()
	{
		return balanceOrgId ;
	}
	
	public void setBalanceOrgId (String balanceOrgId )
	{
		this.balanceOrgId = balanceOrgId;
	}
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public String getReturnCompanyId ()
	{
		return returnCompanyId ;
	}
	
	public void setReturnCompanyId (String returnCompanyId )
	{
		this.returnCompanyId = returnCompanyId;
	}
	public String getReturnOrgId ()
	{
		return returnOrgId ;
	}
	
	public void setReturnOrgId (String returnOrgId )
	{
		this.returnOrgId = returnOrgId;
	}
	public String getOutOrgId ()
	{
		return outOrgId ;
	}
	
	public void setOutOrgId (String outOrgId )
	{
		this.outOrgId = outOrgId;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getDistrRelation ()
	{
		return distrRelation ;
	}
	
	public void setDistrRelation (String distrRelation )
	{
		this.distrRelation = distrRelation;
	}
	public String getFillter ()
	{
		return fillter ;
	}
	
	public void setFillter (String fillter )
	{
		this.fillter = fillter;
	}
	public String getMemo ()
	{
		return memo ;
	}
	
	public void setMemo (String memo )
	{
		this.memo = memo;
	}
	public String getIsStartInvoice ()
	{
		return isStartInvoice ;
	}
	
	public void setIsStartInvoice (String isStartInvoice )
	{
		this.isStartInvoice = isStartInvoice;
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
		return "ReturnBill [" +" id=" +  id   +", billId=" +  billId   +", balanceOrgId=" +  balanceOrgId   +", fillDate=" +  fillDate   +", returnCompanyId=" +  returnCompanyId   +", returnOrgId=" +  returnOrgId   +", outOrgId=" +  outOrgId   +", provId=" +  provId   +", distrRelation=" +  distrRelation   +", fillter=" +  fillter   +", memo=" +  memo   +", isStartInvoice=" +  isStartInvoice   +", sumRow=" +  sumRow   +", status=" +  status   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}