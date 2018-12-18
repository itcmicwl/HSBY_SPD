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
 * 出库单主表
*/
@Table(name="out_stock")
public class OutStock implements IBean
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
	 * 出库类型
	*/
	@Size(max=10)
	@Column(name="out_stock_kind")
	@Title("出库类型")
	private String outStockKind ;
	/**
	 * 出库方式
	 * 10--实采，20--虚采
	*/
	@Column(name="out_stock_type")
	@Title("出库方式")
	private Integer outStockType ;
	/**
	 * 源单号
	*/
	@Size(max=36)
	@Column(name="source_bill_id")
	@Title("源单号")
	private String sourceBillId ;
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
	 * 患者姓名
	*/
	@Size(max=50)
	@Column(name="patient_name")
	@Title("患者姓名")
	private String patientName ;
	/**
	 * 费用类别
	*/
	@Column(name="cost_kind")
	@Title("费用类别")
	private Integer costKind ;
	/**
	 * 制单日期
	*/
	@Column(name="fill_date")
	@Title("制单日期")
	private Date fillDate ;
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
	private Date auditDate ;
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
	private Date accountDate ;
	/**
	 * 收货地址ID
	*/
	@Size(max=36)
	@JoinColumn(name="rec_address_id",table="receive_address",referencedColumnName="id")
	@Title("收货地址ID")
	private String recAddressId ;
	/**
	 * 联系人
	*/
	@Size(max=80)
	@JoinColumn(name="rec_linkman",table="receive_address",referencedColumnName="lxr")
	@Title("联系人")
	private String recLinkman ;
	/**
	 * 联系电话
	*/
	@Size(max=100)
	@JoinColumn(name="rec_linkman_phone",table="receive_address",referencedColumnName="lxr_phone")
	@Title("联系电话")
	private String recLinkmanPhone ;
	/**
	 * 收货地址
	*/
	@Size(max=500)
	@JoinColumn(name="rec_address",table="receive_address",referencedColumnName="address")
	@Title("收货地址")
	private String recAddress ;
	/**
	 * 状态
	*/
	@Column(name="status")
	@Title("状态")
	private Integer status ;
	/**
	 * 传输标志
	 * [0 不需要；1 需传输 ; 2已传输]
	*/
	@Column(name="trans_flag")
	@Title("传输标志")
	private Integer transFlag ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private Date lastUpdateDatetime ;
	/**
	 * 数据版本
	*/
	@Column(name="version")
	@Title("数据版本")
	private int version ;

	@Size(max=128)
	@Column(name="remark")
	@Title("备注")
	private String remark;

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
	public String getOutStockKind ()
	{
		return outStockKind ;
	}
	
	public void setOutStockKind (String outStockKind )
	{
		this.outStockKind = outStockKind;
	}
	public Integer getOutStockType ()
	{
		return outStockType ;
	}
	
	public void setOutStockType (Integer outStockType )
	{
		this.outStockType = outStockType;
	}
	public String getSourceBillId ()
	{
		return sourceBillId ;
	}
	
	public void setSourceBillId (String sourceBillId )
	{
		this.sourceBillId = sourceBillId;
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
	public String getPatientName ()
	{
		return patientName ;
	}
	
	public void setPatientName (String patientName )
	{
		this.patientName = patientName;
	}
	public Integer getCostKind ()
	{
		return costKind ;
	}
	
	public void setCostKind (Integer costKind )
	{
		this.costKind = costKind;
	}
	public Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (Date fillDate )
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
	public Date getAuditDate ()
	{
		return auditDate ;
	}
	
	public void setAuditDate (Date auditDate )
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
	public Date getAccountDate ()
	{
		return accountDate ;
	}
	
	public void setAccountDate (Date accountDate )
	{
		this.accountDate = accountDate;
	}
	public String getRecAddressId ()
	{
		return recAddressId ;
	}
	
	public void setRecAddressId (String recAddressId )
	{
		this.recAddressId = recAddressId;
	}
	public String getRecLinkman ()
	{
		return recLinkman ;
	}
	
	public void setRecLinkman (String recLinkman )
	{
		this.recLinkman = recLinkman;
	}
	public String getRecLinkmanPhone ()
	{
		return recLinkmanPhone ;
	}
	
	public void setRecLinkmanPhone (String recLinkmanPhone )
	{
		this.recLinkmanPhone = recLinkmanPhone;
	}
	public String getRecAddress ()
	{
		return recAddress ;
	}
	
	public void setRecAddress (String recAddress )
	{
		this.recAddress = recAddress;
	}
	public Integer getStatus ()
	{
		return status ;
	}
	
	public void setStatus (Integer status )
	{
		this.status = status;
	}
	public Integer getTransFlag ()
	{
		return transFlag ;
	}
	
	public void setTransFlag (Integer transFlag )
	{
		this.transFlag = transFlag;
	}
	public Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (Date lastUpdateDatetime )
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
		return "OutStock [" +" id=" +  id   +", billId=" +  billId   +", outStockKind=" +  outStockKind   +", outStockType=" +  outStockType   +", sourceBillId=" +  sourceBillId   +", outOrgId=" +  outOrgId   +", outOrgName=" +  outOrgName   +", outDeptId=" +  outDeptId   +", outDeptName=" +  outDeptName   +", outStocId=" +  outStocId   +", inOrgId=" +  inOrgId   +", inOrgName=" +  inOrgName   +", inDeptId=" +  inDeptId   +", inDeptName=" +  inDeptName   +", inStocId=" +  inStocId   +", patientName=" +  patientName   +", costKind=" +  costKind   +", fillDate=" +  fillDate   +", filler=" +  filler   +", auditor=" +  auditor   +", auditDate=" +  auditDate   +", accounter=" +  accounter   +", accountDate=" +  accountDate   +", recAddressId=" +  recAddressId   +", recLinkman=" +  recLinkman   +", recLinkmanPhone=" +  recLinkmanPhone   +", recAddress=" +  recAddress   +", status=" +  status   +", transFlag=" +  transFlag   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}
}