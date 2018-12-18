
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
 * SPD订单返回接口表
*/
@Table(name="pass_distr_bill_wait")
public class PassDistrBillWait implements IBean
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
	 * SPD系统标识
	 * SPD系统标识
	*/
	@Size(max=50)
	@Column(name="spd_code")
	@Title("SPD系统标识")
	private String spdCode ;
	/**
	 * 配送单位ID
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("配送单位ID")
	private String provId ;
	/**
	 * 配送单位编码
	 * ERP系统标识
	*/
	@Size(max=50)
	@Column(name="prov_code")
	@Title("配送单位编码")
	private String provCode ;
	/**
	 * 配送单位名称
	*/
	@Size(max=100)
	@Column(name="prov_name")
	@Title("配送单位名称")
	private String provName ;
	/**
	 * 要货医院ERP编号
	 * 采购订单生成单位,即要货的医院在配送机构中的编码
	*/
	@Size(max=50)
	@Column(name="hos_erp_code")
	@Title("要货医院ERP编号")
	private String hosErpCode ;
	/**
	 * 要货医院名称
	*/
	@Size(max=100)
	@Column(name="hos_name")
	@Title("要货医院名称")
	private String hosName ;
	/**
	 * 要货科室ERP编号
	 * 医院科室在配送机构ERP系统中的编号
	*/
	@Size(max=50)
	@Column(name="hos_apply_dept_erp_code")
	@Title("要货科室ERP编号")
	private String hosApplyDeptErpCode ;
	/**
	 * 要货科室名称
	*/
	@Size(max=100)
	@Column(name="hos_apply_dept_name")
	@Title("要货科室名称")
	private String hosApplyDeptName ;
	/**
	 * 收货科室ERP编号
	 * 医院收货科室在配送机构ERP系统中的编号
	*/
	@Size(max=50)
	@Column(name="hos_receive_dept_erp_code")
	@Title("收货科室ERP编号")
	private String hosReceiveDeptErpCode ;
	/**
	 * 收货科室名称
	*/
	@Size(max=100)
	@Column(name="hos_receive_dept_name")
	@Title("收货科室名称")
	private String hosReceiveDeptName ;
	/**
	 * 采购订单号
	 * 采购订单号
	*/
	@Size(max=50)
	@Column(name="put_bill")
	@Title("采购订单号")
	private String putBill ;
	/**
	 * 请购类型
	 * 请购或者借货
	*/
	@Size(max=50)
	@Column(name="pur_kind")
	@Title("请购类型")
	private String purKind ;
	/**
	 * 任务总行数
	 * 用于校验订单完整性
	*/
	@Column(name="row_count")
	@Title("任务总行数")
	private int rowCount ;
	/**
	 * 发送时间
	*/
	@Column(name="send_date")
	@Title("发送时间")
	private java.util.Date sendDate ;
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
	public String getSpdCode ()
	{
		return spdCode ;
	}
	
	public void setSpdCode (String spdCode )
	{
		this.spdCode = spdCode;
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
	public String getHosErpCode ()
	{
		return hosErpCode ;
	}
	
	public void setHosErpCode (String hosErpCode )
	{
		this.hosErpCode = hosErpCode;
	}
	public String getHosName ()
	{
		return hosName ;
	}
	
	public void setHosName (String hosName )
	{
		this.hosName = hosName;
	}
	public String getHosApplyDeptErpCode ()
	{
		return hosApplyDeptErpCode ;
	}
	
	public void setHosApplyDeptErpCode (String hosApplyDeptErpCode )
	{
		this.hosApplyDeptErpCode = hosApplyDeptErpCode;
	}
	public String getHosApplyDeptName ()
	{
		return hosApplyDeptName ;
	}
	
	public void setHosApplyDeptName (String hosApplyDeptName )
	{
		this.hosApplyDeptName = hosApplyDeptName;
	}
	public String getHosReceiveDeptErpCode ()
	{
		return hosReceiveDeptErpCode ;
	}
	
	public void setHosReceiveDeptErpCode (String hosReceiveDeptErpCode )
	{
		this.hosReceiveDeptErpCode = hosReceiveDeptErpCode;
	}
	public String getHosReceiveDeptName ()
	{
		return hosReceiveDeptName ;
	}
	
	public void setHosReceiveDeptName (String hosReceiveDeptName )
	{
		this.hosReceiveDeptName = hosReceiveDeptName;
	}
	public String getPutBill ()
	{
		return putBill ;
	}
	
	public void setPutBill (String putBill )
	{
		this.putBill = putBill;
	}
	public String getPurKind ()
	{
		return purKind ;
	}
	
	public void setPurKind (String purKind )
	{
		this.purKind = purKind;
	}
	public int getRowCount ()
	{
		return rowCount ;
	}
	
	public void setRowCount (int rowCount )
	{
		this.rowCount = rowCount;
	}
	public java.util.Date getSendDate ()
	{
		return sendDate ;
	}
	
	public void setSendDate (java.util.Date sendDate )
	{
		this.sendDate = sendDate;
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
		return "PassDistrBillWait [" +" id=" +  id   +", spdCode=" +  spdCode   +", provId=" +  provId   +", provCode=" +  provCode   +", provName=" +  provName   +", hosErpCode=" +  hosErpCode   +", hosName=" +  hosName   +", hosApplyDeptErpCode=" +  hosApplyDeptErpCode   +", hosApplyDeptName=" +  hosApplyDeptName   +", hosReceiveDeptErpCode=" +  hosReceiveDeptErpCode   +", hosReceiveDeptName=" +  hosReceiveDeptName   +", putBill=" +  putBill   +", purKind=" +  purKind   +", rowCount=" +  rowCount   +", sendDate=" +  sendDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}