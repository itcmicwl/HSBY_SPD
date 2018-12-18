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
 * 手术包订单主表
*/
@Table(name="surgery_pkg")
public class SurgeryPkg implements IBean
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
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 科室ID
	*/
	@Size(max=36)
	@Column(name="dept_id")
	@Title("科室ID")
	private String deptId ;
	/**
	 * 手术包配置表ID
	*/
	@Size(max=36)
	@Column(name="sur_id")
	@Title("手术包配置表ID")
	private String surId ;
	/**
	 * 手术包码
	*/
	@Column(name="sur_code")
	@Title("手术包码")
	private String surCode ;
	/**
	 * 手术包名
	*/
	@Size(max=256)
	@Column(name="sur_name")
	@Title("手术包名")
	private String surName ;
	@Size(max=4)
	@Column(name="should_sterilize")
	@Title("是否需要消毒")
	private Integer shouldSterilize;
	/**
	 * 患者姓名
	*/
	@Size(max=36)
	@Column(name="sicker_name")
	@Title("患者姓名")
	private String sickerName ;
	/**
	 * 入院号
	*/
	@Size(max=36)
	@Column(name="in_hos_code")
	@Title("入院号")
	private String inHosCode ;
	/**
	 * 手术编号
	*/
	@Size(max=36)
	@Column(name="sur_schedule_no")
	@Title("手术编号")
	private String surScheduleNo ;
	/**
	 * 请购单号
	*/
	@Size(max=36)
	@Column(name="apply_bill_id")
	@Title("请购单号")
	private String applyBillId ;
	/**
	 * 当前库房
	*/
	@Size(max=36)
	@Column(name="cur_stock_id")
	@Title("当前库房")
	private String curStockId ;
	/**
	 * 状态
	 * 0:打包，10：请购出库，20：请购入库，30：部分消毒，31：无需消耗，32：全部消毒40：科室出库 41：消耗，50：部分消毒，51：无需消毒，52：全部消毒，60：退库，70：完成
	*/
	@Column(name="status")
	@Title("状态")
	private int status ;
	/**
	 * 打包人
	*/
	@Size(max=36)
	@Column(name="packer")
	@Title("打包人")
	private String packer ;
	/**
	 * 打包日期
	*/
	@Column(name="package_date")
	@Title("打包日期")
	private Date packageDate ;
	/**
	 * 备注
	*/
	@Size(max=256)
	@Column(name="remark")
	@Title("备注")
	private String remark ;
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

	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getDeptId ()
	{
		return deptId ;
	}
	
	public void setDeptId (String deptId )
	{
		this.deptId = deptId;
	}
	public String getSurId ()
	{
		return surId ;
	}
	
	public void setSurId (String surId )
	{
		this.surId = surId;
	}
	public String getSurCode ()
	{
		return surCode ;
	}
	
	public void setSurCode (String surCode )
	{
		this.surCode = surCode;
	}
	public String getSurName ()
	{
		return surName ;
	}
	
	public void setSurName (String surName )
	{
		this.surName = surName;
	}
	public String getSickerName ()
	{
		return sickerName ;
	}
	
	public void setSickerName (String sickerName )
	{
		this.sickerName = sickerName;
	}
	public String getInHosCode ()
	{
		return inHosCode ;
	}
	
	public void setInHosCode (String inHosCode )
	{
		this.inHosCode = inHosCode;
	}
	public String getSurScheduleNo ()
	{
		return surScheduleNo ;
	}
	
	public void setSurScheduleNo (String surScheduleNo )
	{
		this.surScheduleNo = surScheduleNo;
	}
	public String getApplyBillId ()
	{
		return applyBillId ;
	}
	
	public void setApplyBillId (String applyBillId )
	{
		this.applyBillId = applyBillId;
	}
	public String getCurStockId ()
	{
		return curStockId ;
	}
	
	public void setCurStockId (String curStockId )
	{
		this.curStockId = curStockId;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public String getPacker ()
	{
		return packer ;
	}
	
	public void setPacker (String packer )
	{
		this.packer = packer;
	}
	public Date getPackageDate ()
	{
		return packageDate ;
	}
	
	public void setPackageDate (Date packageDate )
	{
		this.packageDate = packageDate;
	}
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
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

	public Integer getShouldSterilize() {
		return shouldSterilize;
	}

	public void setShouldSterilize(Integer shouldSterilize) {
		this.shouldSterilize = shouldSterilize;
	}

	@Override
	public String toString() {
		return "SurgeryPkg [" +" id=" +  id   +", hosId=" +  hosId   +", deptId=" +  deptId   +", surId=" +  surId   +", surCode=" +  surCode   +", surName=" +  surName   +", sickerName=" +  sickerName   +", inHosCode=" +  inHosCode   +", surScheduleNo=" +  surScheduleNo   +", applyBillId=" +  applyBillId   +", curStockId=" +  curStockId   +", status=" +  status   +", packer=" +  packer   +", packageDate=" +  packageDate   +", remark=" +  remark   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}