
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
 * 病人消耗记录
*/
@Table(name="sicker_use")
public class SickerUse implements IBean
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
	@Size(max=512)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 病人标识号
	*/
	@Size(max=36)
	@Column(name="patient_id")
	@Title("病人标识号")
	private String patientId ;
	/**
	 * 病人本次住院标识
	*/
	@Size(max=50)
	@Column(name="patient_in_hos_id")
	@Title("病人本次住院标识")
	private String patientInHosId ;
	/**
	 * 使用日期
	*/
	@Column(name="use_date")
	@Title("使用日期")
	private Date useDate ;
	/**
	 * 执行医生
	*/
	@Size(max=36)
	@Column(name="exec_doctor")
	@Title("执行医生")
	private String execDoctor ;
	/**
	 * 执行护士
	*/
	@Size(max=36)
	@Column(name="exec_nurse")
	@Title("执行护士")
	private String execNurse ;
	/**
	 * 执行科室
	*/
	@Size(max=36)
	@Column(name="exec_dept_id")
	@Title("执行科室")
	private String execDeptId ;
	/**
	 * 手术室
	*/
	@Size(max=36)
	@Column(name="operation_room")
	@Title("手术室")
	private String operationRoom ;
	/**
	 * 状态
	*/
	@Column(name="status")
	@Title("状态")
	private int status ;
	/**
	 * 登记人
	*/
	@Size(max=36)
	@Column(name="filler")
	@Title("登记人")
	private String filler ;
	/**
	 * 登记时间
	*/
	@Column(name="fill_date")
	@Title("登记时间")
	private Date fillDate ;
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
	public String getPatientId ()
	{
		return patientId ;
	}
	
	public void setPatientId (String patientId )
	{
		this.patientId = patientId;
	}
	public String getPatientInHosId ()
	{
		return patientInHosId ;
	}
	
	public void setPatientInHosId (String patientInHosId )
	{
		this.patientInHosId = patientInHosId;
	}
	public Date getUseDate ()
	{
		return useDate ;
	}
	
	public void setUseDate (Date useDate )
	{
		this.useDate = useDate;
	}
	public String getExecDoctor ()
	{
		return execDoctor ;
	}
	
	public void setExecDoctor (String execDoctor )
	{
		this.execDoctor = execDoctor;
	}
	public String getExecNurse ()
	{
		return execNurse ;
	}
	
	public void setExecNurse (String execNurse )
	{
		this.execNurse = execNurse;
	}
	public String getExecDeptId ()
	{
		return execDeptId ;
	}
	
	public void setExecDeptId (String execDeptId )
	{
		this.execDeptId = execDeptId;
	}
	public String getOperationRoom ()
	{
		return operationRoom ;
	}
	
	public void setOperationRoom (String operationRoom )
	{
		this.operationRoom = operationRoom;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public String getFiller ()
	{
		return filler ;
	}
	
	public void setFiller (String filler )
	{
		this.filler = filler;
	}
	public Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (Date fillDate )
	{
		this.fillDate = fillDate;
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


	@Override
	public String toString() {
		return "SickerUse [" +" id=" +  id   +", hosId=" +  hosId   +", patientId=" +  patientId   +", patientInHosId=" +  patientInHosId   +", useDate=" +  useDate   +", execDoctor=" +  execDoctor   +", execNurse=" +  execNurse   +", execDeptId=" +  execDeptId   +", operationRoom=" +  operationRoom   +", status=" +  status   +", filler=" +  filler   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}