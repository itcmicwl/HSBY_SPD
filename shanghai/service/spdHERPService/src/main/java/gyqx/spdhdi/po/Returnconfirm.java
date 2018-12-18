
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 退货确认单
*/
@Table(name="returnconfirm")
public class Returnconfirm implements IBean
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
	 * 退货单号
	*/
	@Size(max=36)
	@Column(name="return_bill_id")
	@Title("退货单号")
	private String returnBillId ;
	/**
	 * 状态
	 * 10、已保存 20 已提交
	*/
	@Column(name="status")
	@Title("状态")
	private int status ;
	/**
	 * 建档时间
	*/
	@Column(name="fill_date")
	@Title("建档时间")
	private java.util.Date fillDate ;
	/**
	 * SPD系统标识
	*/
	@Size(max=36)
	@Column(name="spd_code")
	@Title("SPD系统标识")
	private String spdCode ;
	/**
	 * 请退机构
	*/
	@Size(max=36)
	@JoinColumn(name="return_company_id",table="bas_company_info",referencedColumnName="id")
	@Title("请退机构")
	private String returnCompanyId ;
	/**
	 * 请退机构编号
	*/
	@Size(max=36)
	@JoinColumn(name="return_company_code",table="bas_company_info",referencedColumnName="code")
	@Title("请退机构编号")
	private String returnCompanyCode ;
	/**
	 * 请退机构名称
	*/
	@Size(max=36)
	@JoinColumn(name="return_company_name",table="bas_company_info",referencedColumnName="name")
	@Title("请退机构名称")
	private String returnCompanyName ;
	/**
	 * 请退机构ERP编号
	*/
	@Size(max=36)
	@JoinColumn(name="return_company_erp_code",table="bas_company_info",referencedColumnName="erp_code")
	@Title("请退机构ERP编号")
	private String returnCompanyErpCode ;
	/**
	 * 请退科室
	*/
	@Size(max=36)
	@JoinColumn(name="return_org_id",table="sys_org",referencedColumnName="id")
	@Title("请退科室")
	private String returnOrgId ;
	/**
	 * 请退科室编号
	*/
	@Size(max=36)
	@JoinColumn(name="return_org_code",table="sys_org",referencedColumnName="code")
	@Title("请退科室编号")
	private String returnOrgCode ;
	/**
	 * 请退科室名称
	*/
	@Size(max=36)
	@JoinColumn(name="return_org_name",table="sys_org",referencedColumnName="name")
	@Title("请退科室名称")
	private String returnOrgName ;
	/**
	 * 请退科室SCM编号
	*/
	@Size(max=36)
	@JoinColumn(name="return_org_scm_code",table="sys_org",referencedColumnName="scm_code")
	@Title("请退科室SCM编号")
	private String returnOrgScmCode ;
	/**
	 * 请退科室ERP编号
	*/
	@Size(max=36)
	@JoinColumn(name="return_org_erp_code",table="sys_org",referencedColumnName="erp_code")
	@Title("请退科室ERP编号")
	private String returnOrgErpCode ;
	/**
	 * 出库科室
	*/
	@Size(max=36)
	@JoinColumn(name="out_org_id",table="sys_org",referencedColumnName="id")
	@Title("出库科室")
	private String outOrgId ;
	/**
	 * 出库科室编号
	*/
	@Size(max=36)
	@JoinColumn(name="out_org_code",table="sys_org",referencedColumnName="code")
	@Title("出库科室编号")
	private String outOrgCode ;
	/**
	 * 出库科室名称
	*/
	@Size(max=36)
	@JoinColumn(name="out_org_name",table="sys_org",referencedColumnName="name")
	@Title("出库科室名称")
	private String outOrgName ;
	/**
	 * 出库科室SCM编号
	*/
	@Size(max=36)
	@JoinColumn(name="out_org_scm_code",table="sys_org",referencedColumnName="scm_code")
	@Title("出库科室SCM编号")
	private String outOrgScmCode ;
	/**
	 * 出库科室ERP编号
	*/
	@Size(max=36)
	@JoinColumn(name="out_org_erp_code",table="sys_org",referencedColumnName="erp_code")
	@Title("出库科室ERP编号")
	private String outOrgErpCode ;
	/**
	 * 配送机构
	*/
	@Size(max=36)
	@JoinColumn(name="prov_id",table="bas_company_info",referencedColumnName="id")
	@Title("配送机构")
	private String provId ;
	/**
	 * 配送机构编号
	*/
	@Size(max=36)
	@JoinColumn(name="prov_code",table="bas_company_info",referencedColumnName="code")
	@Title("配送机构编号")
	private String provCode ;
	/**
	 * 配送机构名称
	*/
	@Size(max=36)
	@JoinColumn(name="prov_name",table="bas_company_info",referencedColumnName="name")
	@Title("配送机构名称")
	private String provName ;
	/**
	 * 配送机构ERP编号
	*/
	@Size(max=36)
	@JoinColumn(name="prov_erp_code",table="bas_company_info",referencedColumnName="erp_code")
	@Title("配送机构ERP编号")
	private String provErpCode ;
	/**
	 * 配送机构ERP部门编号
	*/
	@Size(max=36)
	@JoinColumn(name="prov_org_code",table="sys_org",referencedColumnName="erp_code")
	@Title("配送机构ERP部门编号")
	private String provOrgCode ;
	/**
	 * 配送机构系统标识
	*/
	@Size(max=36)
	@Column(name="prov_system_id")
	@Title("配送机构系统标识")
	private String provSystemId ;
	/**
	 * 配送关系类型
	*/
	@Size(max=36)
	@Column(name="delivery_relation")
	@Title("配送关系类型")
	private String deliveryRelation ;
	/**
	 * 结算配送机构
	*/
	@Size(max=36)
	@Column(name="balance_org_id")
	@Title("结算配送机构")
	private String balanceOrgId ;
	/**
	 * 制单人
	*/
	@Size(max=36)
	@Column(name="fillter")
	@Title("制单人")
	private String fillter ;
	/**
	 * 备注信息
	*/
	@Size(max=300)
	@Column(name="remark")
	@Title("备注信息")
	private String remark ;
	/**
	 * 笔数
	*/
	@Column(name="sum_row")
	@Title("笔数")
	private int sumRow ;
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
	public String getReturnBillId ()
	{
		return returnBillId ;
	}
	
	public void setReturnBillId (String returnBillId )
	{
		this.returnBillId = returnBillId;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public String getSpdCode ()
	{
		return spdCode ;
	}
	
	public void setSpdCode (String spdCode )
	{
		this.spdCode = spdCode;
	}
	public String getReturnCompanyId ()
	{
		return returnCompanyId ;
	}
	
	public void setReturnCompanyId (String returnCompanyId )
	{
		this.returnCompanyId = returnCompanyId;
	}
	public String getReturnCompanyCode ()
	{
		return returnCompanyCode ;
	}
	
	public void setReturnCompanyCode (String returnCompanyCode )
	{
		this.returnCompanyCode = returnCompanyCode;
	}
	public String getReturnCompanyName ()
	{
		return returnCompanyName ;
	}
	
	public void setReturnCompanyName (String returnCompanyName )
	{
		this.returnCompanyName = returnCompanyName;
	}
	public String getReturnCompanyErpCode ()
	{
		return returnCompanyErpCode ;
	}
	
	public void setReturnCompanyErpCode (String returnCompanyErpCode )
	{
		this.returnCompanyErpCode = returnCompanyErpCode;
	}
	public String getReturnOrgId ()
	{
		return returnOrgId ;
	}
	
	public void setReturnOrgId (String returnOrgId )
	{
		this.returnOrgId = returnOrgId;
	}
	public String getReturnOrgCode ()
	{
		return returnOrgCode ;
	}
	
	public void setReturnOrgCode (String returnOrgCode )
	{
		this.returnOrgCode = returnOrgCode;
	}
	public String getReturnOrgName ()
	{
		return returnOrgName ;
	}
	
	public void setReturnOrgName (String returnOrgName )
	{
		this.returnOrgName = returnOrgName;
	}
	public String getReturnOrgScmCode ()
	{
		return returnOrgScmCode ;
	}
	
	public void setReturnOrgScmCode (String returnOrgScmCode )
	{
		this.returnOrgScmCode = returnOrgScmCode;
	}
	public String getReturnOrgErpCode ()
	{
		return returnOrgErpCode ;
	}
	
	public void setReturnOrgErpCode (String returnOrgErpCode )
	{
		this.returnOrgErpCode = returnOrgErpCode;
	}
	public String getOutOrgId ()
	{
		return outOrgId ;
	}
	
	public void setOutOrgId (String outOrgId )
	{
		this.outOrgId = outOrgId;
	}
	public String getOutOrgCode ()
	{
		return outOrgCode ;
	}
	
	public void setOutOrgCode (String outOrgCode )
	{
		this.outOrgCode = outOrgCode;
	}
	public String getOutOrgName ()
	{
		return outOrgName ;
	}
	
	public void setOutOrgName (String outOrgName )
	{
		this.outOrgName = outOrgName;
	}
	public String getOutOrgScmCode ()
	{
		return outOrgScmCode ;
	}
	
	public void setOutOrgScmCode (String outOrgScmCode )
	{
		this.outOrgScmCode = outOrgScmCode;
	}
	public String getOutOrgErpCode ()
	{
		return outOrgErpCode ;
	}
	
	public void setOutOrgErpCode (String outOrgErpCode )
	{
		this.outOrgErpCode = outOrgErpCode;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getProvCode ()
	{
		return provCode ;
	}
	
	public void setProvCode (String provCode )
	{
		this.provCode = provCode;
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
	public String getProvOrgCode ()
	{
		return provOrgCode ;
	}
	
	public void setProvOrgCode (String provOrgCode )
	{
		this.provOrgCode = provOrgCode;
	}
	public String getProvSystemId ()
	{
		return provSystemId ;
	}
	
	public void setProvSystemId (String provSystemId )
	{
		this.provSystemId = provSystemId;
	}
	public String getDeliveryRelation ()
	{
		return deliveryRelation ;
	}
	
	public void setDeliveryRelation (String deliveryRelation )
	{
		this.deliveryRelation = deliveryRelation;
	}
	public String getBalanceOrgId ()
	{
		return balanceOrgId ;
	}
	
	public void setBalanceOrgId (String balanceOrgId )
	{
		this.balanceOrgId = balanceOrgId;
	}
	public String getFillter ()
	{
		return fillter ;
	}
	
	public void setFillter (String fillter )
	{
		this.fillter = fillter;
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
		return "Returnconfirm [" +" id=" +  id   +", billId=" +  billId   +", returnBillId=" +  returnBillId   +", status=" +  status   +", fillDate=" +  fillDate   +", spdCode=" +  spdCode   +", returnCompanyId=" +  returnCompanyId   +", returnCompanyCode=" +  returnCompanyCode   +", returnCompanyName=" +  returnCompanyName   +", returnCompanyErpCode=" +  returnCompanyErpCode   +", returnOrgId=" +  returnOrgId   +", returnOrgCode=" +  returnOrgCode   +", returnOrgName=" +  returnOrgName   +", returnOrgScmCode=" +  returnOrgScmCode   +", returnOrgErpCode=" +  returnOrgErpCode   +", outOrgId=" +  outOrgId   +", outOrgCode=" +  outOrgCode   +", outOrgName=" +  outOrgName   +", outOrgScmCode=" +  outOrgScmCode   +", outOrgErpCode=" +  outOrgErpCode   +", provId=" +  provId   +", provCode=" +  provCode   +", provName=" +  provName   +", provErpCode=" +  provErpCode   +", provOrgCode=" +  provOrgCode   +", provSystemId=" +  provSystemId   +", deliveryRelation=" +  deliveryRelation   +", balanceOrgId=" +  balanceOrgId   +", fillter=" +  fillter   +", remark=" +  remark   +", sumRow=" +  sumRow   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}