
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 采购单
*/
@Table(name="purchase")
public class Purchase implements IBean
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
	 * 采购类型
	*/
	@Column(name="pur_kind")
	@Title("采购类型")
	private int purKind ;
	/**
	 * 采购方式
	 * 0--实物采购，1--虚拟采购
	*/
	@Column(name="pur_type")
	@Title("采购方式")
	private int purType ;
	/**
	 * 单号
	*/
	@Size(max=36)
	@Column(name="bill_id")
	@Title("单号")
	private String billId ;
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
	@JoinColumn(name="purchase_dept_id",table="sys_org",referencedColumnName="id")
	@Title("请购科室")
	private String purchaseDeptId ;
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
	 * 供应商
	*/
	@Size(max=36)
	@JoinColumn(name="sub_prov_id",table="bas_company_info",referencedColumnName="id")
	@Title("供应商")
	private String subProvId ;
	/**
	 * 配送关系类型
	*/
	@Size(max=36)
	@Column(name="delivery_relation")
	@Title("配送关系类型")
	private String deliveryRelation ;
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
	@Size(max=500)
	@Column(name="remark")
	@Title("备注信息")
	private String remark ;
	/**
	 * 当前审批级别
	*/
	@Column(name="status")
	@Title("当前审批级别")
	private int status ;
	/**
	 * 当前审批人
	*/
	@Size(max=36)
	@Column(name="autitor_id")
	@Title("当前审批人")
	private String autitorId ;
	/**
	 * 最终审批通过笔数
	*/
	@Column(name="autitor_sumrow")
	@Title("最终审批通过笔数")
	private int autitorSumrow ;
	/**
	 * 笔数
	*/
	@Column(name="sum_row")
	@Title("笔数")
	private int sumRow ;
	/**
	 * 采购总金额
	*/
	@Column(name="sum_amount")
	@Title("采购总金额")
	private java.math.BigDecimal sumAmount ;
	/**
	 * 采购不含税总金额
	*/
	@Column(name="sum_tax_amount")
	@Title("采购不含税总金额")
	private java.math.BigDecimal sumTaxAmount ;
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
	 * 标志
	*/
	@Column(name="flag")
	@Title("标志")
	private int flag ;
	/**
	 * 关闭理由
	*/
	@Size(max=1024)
	@Column(name="closing_reason")
	@Title("关闭理由")
	private String closingReason ;
	/**
	 * 建档时间
	*/
	@Column(name="fill_date")
	@Title("建档时间")
	private java.util.Date fillDate ;
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
	 * 数据版本
	*/
	@Column(name="last_arrival_date")
	@Title("最后收货时间")
	private java.util.Date lastArrivalDate;

	public java.util.Date getLastArrivalDate() {
		return lastArrivalDate;
	}

	public void setLastArrivalDate(java.util.Date lastArrivalDate) {
		this.lastArrivalDate = lastArrivalDate;
	}
	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public int getPurKind ()
	{
		return purKind ;
	}
	
	public void setPurKind (int purKind )
	{
		this.purKind = purKind;
	}
	public int getPurType ()
	{
		return purType ;
	}
	
	public void setPurType (int purType )
	{
		this.purType = purType;
	}
	public String getBillId ()
	{
		return billId ;
	}
	
	public void setBillId (String billId )
	{
		this.billId = billId;
	}
	public String getPurchaseCompanyId ()
	{
		return purchaseCompanyId ;
	}
	
	public void setPurchaseCompanyId (String purchaseCompanyId )
	{
		this.purchaseCompanyId = purchaseCompanyId;
	}
	public String getPurchaseDeptId ()
	{
		return purchaseDeptId ;
	}
	
	public void setPurchaseDeptId (String purchaseDeptId )
	{
		this.purchaseDeptId = purchaseDeptId;
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
	public String getSubProvId ()
	{
		return subProvId ;
	}
	
	public void setSubProvId (String subProvId )
	{
		this.subProvId = subProvId;
	}
	public String getDeliveryRelation ()
	{
		return deliveryRelation ;
	}
	
	public void setDeliveryRelation (String deliveryRelation )
	{
		this.deliveryRelation = deliveryRelation;
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
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public String getAutitorId ()
	{
		return autitorId ;
	}
	
	public void setAutitorId (String autitorId )
	{
		this.autitorId = autitorId;
	}
	public int getAutitorSumrow ()
	{
		return autitorSumrow ;
	}
	
	public void setAutitorSumrow (int autitorSumrow )
	{
		this.autitorSumrow = autitorSumrow;
	}
	public int getSumRow ()
	{
		return sumRow ;
	}
	
	public void setSumRow (int sumRow )
	{
		this.sumRow = sumRow;
	}
	public java.math.BigDecimal getSumAmount ()
	{
		return sumAmount ;
	}
	
	public void setSumAmount (java.math.BigDecimal sumAmount )
	{
		this.sumAmount = sumAmount;
	}
	public java.math.BigDecimal getSumTaxAmount ()
	{
		return sumTaxAmount ;
	}
	
	public void setSumTaxAmount (java.math.BigDecimal sumTaxAmount )
	{
		this.sumTaxAmount = sumTaxAmount;
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
	public int getFlag ()
	{
		return flag ;
	}
	
	public void setFlag (int flag )
	{
		this.flag = flag;
	}
	public String getClosingReason ()
	{
		return closingReason ;
	}
	
	public void setClosingReason (String closingReason )
	{
		this.closingReason = closingReason;
	}
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
	{
		this.fillDate = fillDate;
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
		return "Purchase [" +" id=" +  id   +", purKind=" +  purKind   +", purType=" +  purType   +", billId=" +  billId   +", purchaseCompanyId=" +  purchaseCompanyId   +", purchaseDeptId=" +  purchaseDeptId   +", recieveOrgId=" +  recieveOrgId   +", provId=" +  provId   +", subProvId=" +  subProvId   +", deliveryRelation=" +  deliveryRelation   +", fillter=" +  fillter   +", remark=" +  remark   +", status=" +  status   +", autitorId=" +  autitorId   +", autitorSumrow=" +  autitorSumrow   +", sumRow=" +  sumRow   +", sumAmount=" +  sumAmount   +", sumTaxAmount=" +  sumTaxAmount   +", recAddressId=" +  recAddressId   +", recLinkman=" +  recLinkman   +", recLinkmanPhone=" +  recLinkmanPhone   +", recAddress=" +  recAddress   +", flag=" +  flag   +", closingReason=" +  closingReason   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version+", lastArrivalDate=" +  lastArrivalDate   
			 + "]";
	}

}