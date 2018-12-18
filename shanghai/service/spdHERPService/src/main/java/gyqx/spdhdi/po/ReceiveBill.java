
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 订单收货
*/
@Table(name="receive_bill")
public class ReceiveBill implements IBean
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
	 * 建档时间
	*/
	@Column(name="fill_date")
	@Title("建档时间")
	private java.util.Date fillDate ;
	/**
	 * 请购机构
	*/
	@Size(max=36)
	@JoinColumn(name="purchase_company_id",table="bas_company_info",referencedColumnName="id")
	@Title("请购机构")
	private String purchaseCompanyId ;
	/**
	 * 请购科室
	*/
	@Size(max=36)
	@JoinColumn(name="apply_org_id",table="sys_org",referencedColumnName="id")
	@Title("请购科室")
	private String applyOrgId ;
	/**
	 * 收货科室
	*/
	@Size(max=36)
	@JoinColumn(name="recieve_org_id",table="sys_org",referencedColumnName="id")
	@Title("收货科室")
	private String recieveOrgId ;
	/**
	 * 配送机构
	*/
	@Size(max=36)
	@JoinColumn(name="prov_id",table="bas_company_info",referencedColumnName="id")
	@Title("配送机构")
	private String provId ;
	/**
	 * 验收人
	*/
	@Size(max=36)
	@Column(name="reviever_id")
	@Title("验收人")
	private String revieverId ;
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
	 * 当前审批级别
	*/
	@Column(name="status")
	@Title("当前审批级别")
	private int status ;
	/**
	 * 笔数
	*/
	@Column(name="sum_row")
	@Title("笔数")
	private int sumRow ;
	/**
	 * 配送单号
	*/
	@Size(max=36)
	@JoinColumn(name="distr_bill_id",table="distr_bill",referencedColumnName="bill_id")
	@Title("配送单号")
	private String distrBillId ;
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
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public String getPurchaseCompanyId ()
	{
		return purchaseCompanyId ;
	}
	
	public void setPurchaseCompanyId (String purchaseCompanyId )
	{
		this.purchaseCompanyId = purchaseCompanyId;
	}
	public String getApplyOrgId ()
	{
		return applyOrgId ;
	}
	
	public void setApplyOrgId (String applyOrgId )
	{
		this.applyOrgId = applyOrgId;
	}
	public String getRecieveOrgId ()
	{
		return recieveOrgId ;
	}
	
	public void setRecieveOrgId (String recieveOrgId )
	{
		this.recieveOrgId = recieveOrgId;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getRevieverId ()
	{
		return revieverId ;
	}
	
	public void setRevieverId (String revieverId )
	{
		this.revieverId = revieverId;
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
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public int getSumRow ()
	{
		return sumRow ;
	}
	
	public void setSumRow (int sumRow )
	{
		this.sumRow = sumRow;
	}
	public String getDistrBillId ()
	{
		return distrBillId ;
	}
	
	public void setDistrBillId (String distrBillId )
	{
		this.distrBillId = distrBillId;
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
		return "ReceiveBill [" +" id=" +  id   +", billId=" +  billId   +", fillDate=" +  fillDate   +", purchaseCompanyId=" +  purchaseCompanyId   +", applyOrgId=" +  applyOrgId   +", recieveOrgId=" +  recieveOrgId   +", provId=" +  provId   +", revieverId=" +  revieverId   +", fillter=" +  fillter   +", memo=" +  memo   +", status=" +  status   +", sumRow=" +  sumRow   +", distrBillId=" +  distrBillId   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}