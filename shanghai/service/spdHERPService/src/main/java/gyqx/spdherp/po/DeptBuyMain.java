
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 请购单
*/
@Table(name="dept_buy_main")
public class DeptBuyMain implements IBean
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
	 * 请购单ID
	*/
	@Size(max=36)
	@Column(name="bill_id")
	@Title("请购单ID")
	private String billId ;
	/**
	 * 请购类型
	 * 10--实物请购20--虚拟请购
	*/
	@Column(name="buy_kind")
	@Title("请购类型")
	private int buyKind ;
	/**
	 * 采购模式
	 * 10--实物请购20--虚拟请购 30--办公
	*/
	@Column(name="pur_mode")
	@Title("采购模式")
	private int purMode ;

	/**      
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 请购科室ID
	*/
	@Size(max=36)
	@Column(name="buy_dept_id")
	@Title("请购科室ID")
	private String buyDeptId ;
	/**
	 * 货源仓库Id（科室）
	*/
	@Size(max=36)
	@Column(name="source_warehouse_id")
	@Title("货源仓库Id（科室）")
	private String sourceWarehouseId ;
	/**
	 * 备注
	*/
	@Size(max=128)
	@Column(name="remark")
	@Title("备注")
	private String remark ;
	/**
	 * 请购单状态
	 * 10 未处理 20处理中 30 已处理
	*/
	@Column(name="state")
	@Title("请购单状态")
	private int state ;
	/**
	 * 科室内部状态
	 * 10 暂存 20已提交 30已审核
	*/
	@Column(name="dept_state")
	@Title("科室内部状态")
	private int deptState ;
	/**
	 * 科内审核意见
	*/
	@Size(max=128)
	@Column(name="dept_view")
	@Title("科内审核意见")
	private String deptView ;
	/**
	 * 请购笔数
	*/
	@Column(name="sum_row")
	@Title("请购笔数")
	private int sumRow ;
	/**
	 * 货源仓库批准笔数
	*/
	@Column(name="wh_sumrow")
	@Title("货源仓库批准笔数")
	private int whSumrow ;
	/**
	 * 科内审核时间
	*/
	@Column(name="dept_deal_time")
	@Title("科内审核时间")
	private java.util.Date deptDealTime ;
	/**
	 * 科内审核人
	*/
	@Size(max=36)
	@Column(name="dept_deal_man")
	@Title("科内审核人")
	private String deptDealMan ;
	/**
	 * 制单人
	*/
	@Size(max=36)
	@Column(name="fillter")
	@Title("制单人")
	private String fillter ;
	/**
	 * 制单日期
	*/
	@Column(name="fill_date")
	@Title("制单日期")
	private java.util.Date fillDate ;
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
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 版本控制
	*/
	@Column(name="version")
	@Title("版本控制")
	private int version ;

	@Size(max=36)
	@Column(name="sicker_name")
	@Title("患者姓名")
	private String sickerName;
	
	/**
	 * 是否关闭
	 * 0--未关闭--1已关闭
	*/
	@Column(name="if_closed")
	@Title("是否关闭")
	private int ifClosed ;

	public int getIfClosed() {
		return ifClosed;
	}

	public void setIfClosed(int ifClosed) {
		this.ifClosed = ifClosed;
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
	public int getBuyKind ()
	{
		return buyKind ;
	}
	
	public void setBuyKind (int buyKind )
	{
		this.buyKind = buyKind;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getBuyDeptId ()
	{
		return buyDeptId ;
	}
	
	public void setBuyDeptId (String buyDeptId )
	{
		this.buyDeptId = buyDeptId;
	}
	public String getSourceWarehouseId ()
	{
		return sourceWarehouseId ;
	}
	
	public void setSourceWarehouseId (String sourceWarehouseId )
	{
		this.sourceWarehouseId = sourceWarehouseId;
	}
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
	}
	public int getState ()
	{
		return state ;
	}
	
	public void setState (int state )
	{
		this.state = state;
	}
	public int getDeptState ()
	{
		return deptState ;
	}
	
	public void setDeptState (int deptState )
	{
		this.deptState = deptState;
	}
	public String getDeptView ()
	{
		return deptView ;
	}
	
	public void setDeptView (String deptView )
	{
		this.deptView = deptView;
	}
	public int getSumRow ()
	{
		return sumRow ;
	}
	
	public void setSumRow (int sumRow )
	{
		this.sumRow = sumRow;
	}
	public int getWhSumrow ()
	{
		return whSumrow ;
	}
	
	public void setWhSumrow (int whSumrow )
	{
		this.whSumrow = whSumrow;
	}
	public java.util.Date getDeptDealTime ()
	{
		return deptDealTime ;
	}
	
	public void setDeptDealTime (java.util.Date deptDealTime )
	{
		this.deptDealTime = deptDealTime;
	}
	public String getDeptDealMan ()
	{
		return deptDealMan ;
	}
	
	public void setDeptDealMan (String deptDealMan )
	{
		this.deptDealMan = deptDealMan;
	}
	public String getFillter ()
	{
		return fillter ;
	}
	
	public void setFillter (String fillter )
	{
		this.fillter = fillter;
	}
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
	{
		this.fillDate = fillDate;
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
	public java.util.Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (java.util.Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public int getPurMode() {
		return purMode;
	}

	public void setPurMode(int purMode) {
		this.purMode = purMode;
	}

	public int getVersion ()
	{
		return version ;
	}


	public String getSickerName() {
		return sickerName;
	}

	public void setSickerName(String sickerName) {
		this.sickerName = sickerName;
	}

	public void setVersion (int version )
	{
		this.version = version;
	}

	@Override
	public String toString() {
		return "DeptBuyMain [" +" id=" +  id   +", billId=" +  billId   +", buyKind=" +  buyKind   +", hosId=" +  hosId   +", buyDeptId=" +  buyDeptId   +", sourceWarehouseId=" +  sourceWarehouseId   +", remark=" +  remark   +", state=" +  state   +", deptState=" +  deptState   +", deptView=" +  deptView   +", sumRow=" +  sumRow   +", whSumrow=" +  whSumrow   +", deptDealTime=" +  deptDealTime   +", deptDealMan=" +  deptDealMan   +", fillter=" +  fillter   +", fillDate=" +  fillDate   +", auditor=" +  auditor   +", auditDate=" +  auditDate   +", recAddressId=" +  recAddressId   +", recLinkman=" +  recLinkman   +", recLinkmanPhone=" +  recLinkmanPhone   +", recAddress=" +  recAddress   +", lastUpdateDatetime=" +  lastUpdateDatetime  +", ifClosed=" +  ifClosed +", purMode=" +  purMode +", version=" +  version   
			 + "]";
	}

}