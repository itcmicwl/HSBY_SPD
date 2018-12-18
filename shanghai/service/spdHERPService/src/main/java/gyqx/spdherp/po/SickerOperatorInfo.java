
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
 * 手术预约患者信息表
*/
@Table(name="sicker_operator_info")
public class SickerOperatorInfo implements IBean
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
	 * 病案号
	*/
	@Size(max=50)
	@Column(name="patient_code")
	@Title("病案号")
	private String patientCode ;
	/**
	 * 手术安排标识
	*/
	@Size(max=50)
	@Column(name="patient_operation_id")
	@Title("手术安排标识")
	private String patientOperationId ;
	/**
	 * 姓名
	*/
	@Size(max=36)
	@Column(name="patient_name")
	@Title("姓名")
	private String patientName ;
	/**
	 * 出生日期
	*/
	@Column(name="patient_birthday")
	@Title("出生日期")
	private Date patientBirthday ;
	/**
	 * 姓名拼音
	*/
	@Size(max=100)
	@Column(name="patient_short_pinyin")
	@Title("姓名拼音")
	private String patientShortPinyin ;
	/**
	 * 性别
	*/
	@Size(max=36)
	@Column(name="patient_sex")
	@Title("性别")
	private String patientSex ;
	/**
	 * 病人所在科室SPD码
	 * SPD代码
	*/
	@Size(max=36)
	@Column(name="dept_spd_code")
	@Title("病人所在科室SPD码")
	private String deptSpdCode ;
	/**
	 * 病人所在科室代码
	 * HIS代码
	*/
	@Size(max=36)
	@Column(name="dept_code")
	@Title("病人所在科室代码")
	private String deptCode ;
	/**
	 * 病人所在科室名称
	*/
	@Size(max=100)
	@Column(name="dept_name")
	@Title("病人所在科室名称")
	private String deptName ;
	/**
	 * 床号
	*/
	@Size(max=36)
	@Column(name="bed_num")
	@Title("床号")
	private String bedNum ;
	/**
	 * 手术日期及时间
	*/
	@Column(name="opration_date")
	@Title("手术日期及时间")
	private Date oprationDate ;
	/**
	 * 申请日期及时间
	*/
	@Column(name="fill_date")
	@Title("申请日期及时间")
	private Date fillDate ;
	/**
	 * 手术室SPD编码
	 * SPD代码
	*/
	@Size(max=36)
	@Column(name="opration_room_spd_code")
	@Title("手术室SPD编码")
	private String oprationRoomSpdCode ;
	/**
	 * 手术室
	 * HIS代码
	*/
	@Size(max=36)
	@Column(name="opration_room_code")
	@Title("手术室")
	private String oprationRoomCode ;
	/**
	 * 手术室名称
	*/
	@Size(max=100)
	@Column(name="opration_room_name")
	@Title("手术室名称")
	private String oprationRoomName ;
	/**
	 * 手术间
	*/
	@Size(max=100)
	@Column(name="opration_house")
	@Title("手术间")
	private String oprationHouse ;
	/**
	 * 手术者
	*/
	@Size(max=100)
	@Column(name="oprationer")
	@Title("手术者")
	private String oprationer ;
	/**
	 * 麻醉医师
	*/
	@Size(max=100)
	@Column(name="anaesthesiaer")
	@Title("麻醉医师")
	private String anaesthesiaer ;
	/**
	 * 麻醉类型
	*/
	@Size(max=100)
	@Column(name="anaesthesia_kind")
	@Title("麻醉类型")
	private String anaesthesiaKind ;
	/**
	 * 手术安排标识
	*/
	@Size(max=100)
	@Column(name="operator_id")
	@Title("手术安排标识")
	private String operatorId ;
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
	public String getPatientCode ()
	{
		return patientCode ;
	}
	
	public void setPatientCode (String patientCode )
	{
		this.patientCode = patientCode;
	}
	public String getPatientOperationId ()
	{
		return patientOperationId ;
	}
	
	public void setPatientOperationId (String patientOperationId )
	{
		this.patientOperationId = patientOperationId;
	}
	public String getPatientName ()
	{
		return patientName ;
	}
	
	public void setPatientName (String patientName )
	{
		this.patientName = patientName;
	}
	public Date getPatientBirthday ()
	{
		return patientBirthday ;
	}
	
	public void setPatientBirthday (Date patientBirthday )
	{
		this.patientBirthday = patientBirthday;
	}
	public String getPatientShortPinyin ()
	{
		return patientShortPinyin ;
	}
	
	public void setPatientShortPinyin (String patientShortPinyin )
	{
		this.patientShortPinyin = patientShortPinyin;
	}
	public String getPatientSex ()
	{
		return patientSex ;
	}
	
	public void setPatientSex (String patientSex )
	{
		this.patientSex = patientSex;
	}
	public String getDeptSpdCode ()
	{
		return deptSpdCode ;
	}
	
	public void setDeptSpdCode (String deptSpdCode )
	{
		this.deptSpdCode = deptSpdCode;
	}
	public String getDeptCode ()
	{
		return deptCode ;
	}
	
	public void setDeptCode (String deptCode )
	{
		this.deptCode = deptCode;
	}
	public String getDeptName ()
	{
		return deptName ;
	}
	
	public void setDeptName (String deptName )
	{
		this.deptName = deptName;
	}
	public String getBedNum ()
	{
		return bedNum ;
	}
	
	public void setBedNum (String bedNum )
	{
		this.bedNum = bedNum;
	}
	public Date getOprationDate ()
	{
		return oprationDate ;
	}
	
	public void setOprationDate (Date oprationDate )
	{
		this.oprationDate = oprationDate;
	}
	public Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public String getOprationRoomSpdCode ()
	{
		return oprationRoomSpdCode ;
	}
	
	public void setOprationRoomSpdCode (String oprationRoomSpdCode )
	{
		this.oprationRoomSpdCode = oprationRoomSpdCode;
	}
	public String getOprationRoomCode ()
	{
		return oprationRoomCode ;
	}
	
	public void setOprationRoomCode (String oprationRoomCode )
	{
		this.oprationRoomCode = oprationRoomCode;
	}
	public String getOprationRoomName ()
	{
		return oprationRoomName ;
	}
	
	public void setOprationRoomName (String oprationRoomName )
	{
		this.oprationRoomName = oprationRoomName;
	}
	public String getOprationHouse ()
	{
		return oprationHouse ;
	}
	
	public void setOprationHouse (String oprationHouse )
	{
		this.oprationHouse = oprationHouse;
	}
	public String getOprationer ()
	{
		return oprationer ;
	}
	
	public void setOprationer (String oprationer )
	{
		this.oprationer = oprationer;
	}
	public String getAnaesthesiaer ()
	{
		return anaesthesiaer ;
	}
	
	public void setAnaesthesiaer (String anaesthesiaer )
	{
		this.anaesthesiaer = anaesthesiaer;
	}
	public String getAnaesthesiaKind ()
	{
		return anaesthesiaKind ;
	}
	
	public void setAnaesthesiaKind (String anaesthesiaKind )
	{
		this.anaesthesiaKind = anaesthesiaKind;
	}
	public String getOperatorId ()
	{
		return operatorId ;
	}
	
	public void setOperatorId (String operatorId )
	{
		this.operatorId = operatorId;
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
		return "SickerOperatorInfo [" +" id=" +  id   +", hosId=" +  hosId   +", patientId=" +  patientId   +", patientInHosId=" +  patientInHosId   +", patientCode=" +  patientCode   +", patientOperationId=" +  patientOperationId   +", patientName=" +  patientName   +", patientBirthday=" +  patientBirthday   +", patientShortPinyin=" +  patientShortPinyin   +", patientSex=" +  patientSex   +", deptSpdCode=" +  deptSpdCode   +", deptCode=" +  deptCode   +", deptName=" +  deptName   +", bedNum=" +  bedNum   +", oprationDate=" +  oprationDate   +", fillDate=" +  fillDate   +", oprationRoomSpdCode=" +  oprationRoomSpdCode   +", oprationRoomCode=" +  oprationRoomCode   +", oprationRoomName=" +  oprationRoomName   +", oprationHouse=" +  oprationHouse   +", oprationer=" +  oprationer   +", anaesthesiaer=" +  anaesthesiaer   +", anaesthesiaKind=" +  anaesthesiaKind   +", operatorId=" +  operatorId   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}