
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 科室请购单
*/
@Table(name="sup_apply")
public class SupApply implements IBean
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
	 * 请购机构
	*/
	@Size(max=36)
	@JoinColumn(name="apply_company_id",table="bas_company_info",referencedColumnName="id")
	@Title("请购机构")
	private String applyCompanyId ;
	/**
	 * 请购科室
	*/
	@Size(max=36)
	@JoinColumn(name="apply_org_id",table="sys_org",referencedColumnName="id")
	@Title("请购科室")
	private String applyOrgId ;
	/**
	 * 到货日期
	*/
	@Column(name="arrval_date")
	@Title("到货日期")
	private java.util.Date arrvalDate ;
	/**
	 * 备注信息
	*/
	@Size(max=128)
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
	 * 标志
	*/
	@Size(max=1)
	@Column(name="flag")
	@Title("标志")
	private String flag ;
	/**
	 * 操作员ID
	 * 新增或修改的操作员ID
	*/
	@Size(max=36)
	@Column(name="uxid")
	@Title("操作员ID")
	private String uxid ;
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
	public String getApplyCompanyId ()
	{
		return applyCompanyId ;
	}
	
	public void setApplyCompanyId (String applyCompanyId )
	{
		this.applyCompanyId = applyCompanyId;
	}
	public String getApplyOrgId ()
	{
		return applyOrgId ;
	}
	
	public void setApplyOrgId (String applyOrgId )
	{
		this.applyOrgId = applyOrgId;
	}
	public java.util.Date getArrvalDate ()
	{
		return arrvalDate ;
	}
	
	public void setArrvalDate (java.util.Date arrvalDate )
	{
		this.arrvalDate = arrvalDate;
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
	public String getFlag ()
	{
		return flag ;
	}
	
	public void setFlag (String flag )
	{
		this.flag = flag;
	}
	public String getUxid ()
	{
		return uxid ;
	}
	
	public void setUxid (String uxid )
	{
		this.uxid = uxid;
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

	public String getUid ()
	{
		return uxid ;
	}
	
	public void setUid (String uxid )
	{
		this.uxid = uxid;
	}

	@Override
	public String toString() {
		return "SupApply [" +" id=" +  id   +", billId=" +  billId   +", applyCompanyId=" +  applyCompanyId   +", applyOrgId=" +  applyOrgId   +", arrvalDate=" +  arrvalDate   +", remark=" +  remark   +", status=" +  status   +", autitorId=" +  autitorId   +", autitorSumrow=" +  autitorSumrow   +", sumRow=" +  sumRow   +", flag=" +  flag   +", uxid=" +  uxid   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}