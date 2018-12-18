
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
 * 在院病人记录
*/
@Table(name="sicker_in_hospital_info")
public class SickerInHospitalInfo implements IBean
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
	 * 所在病房代码
	*/
	@Size(max=50)
	@Column(name="sickroom_code")
	@Title("所在病房代码")
	private String sickroomCode ;
	/**
	 * 所在科室代码
	*/
	@Size(max=50)
	@Column(name="dept_code")
	@Title("所在科室代码")
	private String deptCode ;
	/**
	 * 床号
	*/
	@Size(max=36)
	@Column(name="bed_num")
	@Title("床号")
	private String bedNum ;
	/**
	 * 入院日期及时间
	*/
	@Column(name="in_hospital_date")
	@Title("入院日期及时间")
	private Date inHospitalDate ;
	/**
	 * 入科日期及时间
	*/
	@Column(name="in_dept_date")
	@Title("入科日期及时间")
	private Date inDeptDate ;
	/**
	 * 主要诊断
	*/
	@Size(max=1000)
	@Column(name="diagnose")
	@Title("主要诊断")
	private String diagnose ;
	/**
	 * 病情状态
	*/
	@Size(max=36)
	@Column(name="illness_status")
	@Title("病情状态")
	private String illnessStatus ;
	/**
	 * 护理等级
	*/
	@Size(max=36)
	@Column(name="lookfor_level")
	@Title("护理等级")
	private String lookforLevel ;
	/**
	 * 经治医生
	*/
	@Size(max=100)
	@Column(name="doctor")
	@Title("经治医生")
	private String doctor ;
	/**
	 * 手术日期
	*/
	@Column(name="opration_date")
	@Title("手术日期")
	private Date oprationDate ;
	/**
	 * 计价截止日期及时间
	*/
	@Column(name="write_price_date")
	@Title("计价截止日期及时间")
	private Date writePriceDate ;
	/**
	 * 预交金余额
	*/
	@Column(name="ye")
	@Title("预交金余额")
	private java.math.BigDecimal ye ;
	/**
	 * 累计未结费用
	*/
	@Column(name="sum_miss_money")
	@Title("累计未结费用")
	private java.math.BigDecimal sumMissMoney ;
	/**
	 * 优惠后未结费用
	*/
	@Column(name="yhwjfy")
	@Title("优惠后未结费用")
	private java.math.BigDecimal yhwjfy ;
	/**
	 * 经济担保人
	*/
	@Size(max=100)
	@Column(name="jjdbr")
	@Title("经济担保人")
	private String jjdbr ;
	/**
	 * 经济担保人所在单位
	*/
	@Size(max=100)
	@Column(name="jjdbr_phone_unit")
	@Title("经济担保人所在单位")
	private String jjdbrPhoneUnit ;
	/**
	 * 经济担保人联系电话
	*/
	@Size(max=100)
	@Column(name="jjdbr_phone")
	@Title("经济担保人联系电话")
	private String jjdbrPhone ;
	/**
	 * 上次划价检查日期
	*/
	@Column(name="last_check_date")
	@Title("上次划价检查日期")
	private Date lastCheckDate ;
	/**
	 * 出院结算标记
	*/
	@Column(name="is_out_hospital")
	@Title("出院结算标记")
	private int isOutHospital ;
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
	public String getSickroomCode ()
	{
		return sickroomCode ;
	}
	
	public void setSickroomCode (String sickroomCode )
	{
		this.sickroomCode = sickroomCode;
	}
	public String getDeptCode ()
	{
		return deptCode ;
	}
	
	public void setDeptCode (String deptCode )
	{
		this.deptCode = deptCode;
	}
	public String getBedNum ()
	{
		return bedNum ;
	}
	
	public void setBedNum (String bedNum )
	{
		this.bedNum = bedNum;
	}
	public Date getInHospitalDate ()
	{
		return inHospitalDate ;
	}
	
	public void setInHospitalDate (Date inHospitalDate )
	{
		this.inHospitalDate = inHospitalDate;
	}
	public Date getInDeptDate ()
	{
		return inDeptDate ;
	}
	
	public void setInDeptDate (Date inDeptDate )
	{
		this.inDeptDate = inDeptDate;
	}
	public String getDiagnose ()
	{
		return diagnose ;
	}
	
	public void setDiagnose (String diagnose )
	{
		this.diagnose = diagnose;
	}
	public String getIllnessStatus ()
	{
		return illnessStatus ;
	}
	
	public void setIllnessStatus (String illnessStatus )
	{
		this.illnessStatus = illnessStatus;
	}
	public String getLookforLevel ()
	{
		return lookforLevel ;
	}
	
	public void setLookforLevel (String lookforLevel )
	{
		this.lookforLevel = lookforLevel;
	}
	public String getDoctor ()
	{
		return doctor ;
	}
	
	public void setDoctor (String doctor )
	{
		this.doctor = doctor;
	}
	public Date getOprationDate ()
	{
		return oprationDate ;
	}
	
	public void setOprationDate (Date oprationDate )
	{
		this.oprationDate = oprationDate;
	}
	public Date getWritePriceDate ()
	{
		return writePriceDate ;
	}
	
	public void setWritePriceDate (Date writePriceDate )
	{
		this.writePriceDate = writePriceDate;
	}
	public java.math.BigDecimal getYe ()
	{
		return ye ;
	}
	
	public void setYe (java.math.BigDecimal ye )
	{
		this.ye = ye;
	}
	public java.math.BigDecimal getSumMissMoney ()
	{
		return sumMissMoney ;
	}
	
	public void setSumMissMoney (java.math.BigDecimal sumMissMoney )
	{
		this.sumMissMoney = sumMissMoney;
	}
	public java.math.BigDecimal getYhwjfy ()
	{
		return yhwjfy ;
	}
	
	public void setYhwjfy (java.math.BigDecimal yhwjfy )
	{
		this.yhwjfy = yhwjfy;
	}
	public String getJjdbr ()
	{
		return jjdbr ;
	}
	
	public void setJjdbr (String jjdbr )
	{
		this.jjdbr = jjdbr;
	}
	public String getJjdbrPhoneUnit ()
	{
		return jjdbrPhoneUnit ;
	}
	
	public void setJjdbrPhoneUnit (String jjdbrPhoneUnit )
	{
		this.jjdbrPhoneUnit = jjdbrPhoneUnit;
	}
	public String getJjdbrPhone ()
	{
		return jjdbrPhone ;
	}
	
	public void setJjdbrPhone (String jjdbrPhone )
	{
		this.jjdbrPhone = jjdbrPhone;
	}
	public Date getLastCheckDate ()
	{
		return lastCheckDate ;
	}
	
	public void setLastCheckDate (Date lastCheckDate )
	{
		this.lastCheckDate = lastCheckDate;
	}
	public int getIsOutHospital ()
	{
		return isOutHospital ;
	}
	
	public void setIsOutHospital (int isOutHospital )
	{
		this.isOutHospital = isOutHospital;
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
		return "SickerInHospitalInfo [" +" id=" +  id   +", hosId=" +  hosId   +", patientId=" +  patientId   +", patientInHosId=" +  patientInHosId   +", sickroomCode=" +  sickroomCode   +", deptCode=" +  deptCode   +", bedNum=" +  bedNum   +", inHospitalDate=" +  inHospitalDate   +", inDeptDate=" +  inDeptDate   +", diagnose=" +  diagnose   +", illnessStatus=" +  illnessStatus   +", lookforLevel=" +  lookforLevel   +", doctor=" +  doctor   +", oprationDate=" +  oprationDate   +", writePriceDate=" +  writePriceDate   +", ye=" +  ye   +", sumMissMoney=" +  sumMissMoney   +", yhwjfy=" +  yhwjfy   +", jjdbr=" +  jjdbr   +", jjdbrPhoneUnit=" +  jjdbrPhoneUnit   +", jjdbrPhone=" +  jjdbrPhone   +", lastCheckDate=" +  lastCheckDate   +", isOutHospital=" +  isOutHospital   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}