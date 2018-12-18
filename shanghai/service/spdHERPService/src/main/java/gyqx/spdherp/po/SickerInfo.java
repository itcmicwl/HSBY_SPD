
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
 * 病人基本信息
*/
@Table(name="sicker_info")
public class SickerInfo implements IBean
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
	 * 住院号
	*/
	@Size(max=36)
	@Column(name="patient_in_hos_id")
	@Title("住院号")
	private String patientInHosId ;
	/**
	 * 姓名
	*/
	@Size(max=36)
	@Column(name="patient_name")
	@Title("姓名")
	private String patientName ;
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
	 * 出生日期
	*/
	@Column(name="patient_birthday")
	@Title("出生日期")
	private java.util.Date patientBirthday ;
	/**
	 * 出生地
	*/
	@Size(max=100)
	@Column(name="patient_born_address")
	@Title("出生地")
	private String patientBornAddress ;
	/**
	 * 国籍
	*/
	@Size(max=50)
	@Column(name="patient_country")
	@Title("国籍")
	private String patientCountry ;
	/**
	 * 民族
	*/
	@Size(max=36)
	@Column(name="patient_nation")
	@Title("民族")
	private String patientNation ;
	/**
	 * 身份证号
	*/
	@Size(max=36)
	@Column(name="patient_card")
	@Title("身份证号")
	private String patientCard ;
	/**
	 * 身份
	*/
	@Size(max=100)
	@Column(name="patient_type")
	@Title("身份")
	private String patientType ;
	/**
	 * 费别
	*/
	@Size(max=36)
	@Column(name="cost_kind")
	@Title("费别")
	private String costKind ;
	/**
	 * 合同单位
	*/
	@Size(max=100)
	@Column(name="contract_unit")
	@Title("合同单位")
	private String contractUnit ;
	/**
	 * 通信地址
	*/
	@Size(max=100)
	@Column(name="address")
	@Title("通信地址")
	private String address ;
	/**
	 * 邮政编码
	*/
	@Size(max=36)
	@Column(name="post_code")
	@Title("邮政编码")
	private String postCode ;
	/**
	 * 家庭电话号码
	*/
	@Size(max=36)
	@Column(name="family_phone_num")
	@Title("家庭电话号码")
	private String familyPhoneNum ;
	/**
	 * 单位电话号码
	*/
	@Size(max=100)
	@Column(name="unit_phone_num")
	@Title("单位电话号码")
	private String unitPhoneNum ;
	/**
	 * 联系人姓名
	*/
	@Size(max=100)
	@Column(name="link_name")
	@Title("联系人姓名")
	private String linkName ;
	/**
	 * 与联系人关系
	*/
	@Size(max=100)
	@Column(name="link_relation")
	@Title("与联系人关系")
	private String linkRelation ;
	/**
	 * 联系人地址
	*/
	@Size(max=100)
	@Column(name="link_address")
	@Title("联系人地址")
	private String linkAddress ;
	/**
	 * 联系人邮政编码
	*/
	@Size(max=36)
	@Column(name="link_post_code")
	@Title("联系人邮政编码")
	private String linkPostCode ;
	/**
	 * 联系人电话号码
	*/
	@Size(max=36)
	@Column(name="link_phone_num")
	@Title("联系人电话号码")
	private String linkPhoneNum ;
	/**
	 * 上次就诊日期
	*/
	@Column(name="last_look_doctor_date")
	@Title("上次就诊日期")
	private java.util.Date lastLookDoctorDate ;
	/**
	 * 重要人物标志
	*/
	@Size(max=36)
	@Column(name="is_importment")
	@Title("重要人物标志")
	private String isImportment ;
	/**
	 * 建卡日期
	*/
	@Column(name="fill_date")
	@Title("建卡日期")
	private java.util.Date fillDate ;
	/**
	 * 操作员
	*/
	@Size(max=36)
	@Column(name="filler")
	@Title("操作员")
	private String filler ;
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
	public String getPatientName ()
	{
		return patientName ;
	}
	
	public void setPatientName (String patientName )
	{
		this.patientName = patientName;
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
	public java.util.Date getPatientBirthday ()
	{
		return patientBirthday ;
	}
	
	public void setPatientBirthday (java.util.Date patientBirthday )
	{
		this.patientBirthday = patientBirthday;
	}
	public String getPatientBornAddress ()
	{
		return patientBornAddress ;
	}
	
	public void setPatientBornAddress (String patientBornAddress )
	{
		this.patientBornAddress = patientBornAddress;
	}
	public String getPatientCountry ()
	{
		return patientCountry ;
	}
	
	public void setPatientCountry (String patientCountry )
	{
		this.patientCountry = patientCountry;
	}
	public String getPatientNation ()
	{
		return patientNation ;
	}
	
	public void setPatientNation (String patientNation )
	{
		this.patientNation = patientNation;
	}
	public String getPatientCard ()
	{
		return patientCard ;
	}
	
	public void setPatientCard (String patientCard )
	{
		this.patientCard = patientCard;
	}
	public String getPatientType ()
	{
		return patientType ;
	}
	
	public void setPatientType (String patientType )
	{
		this.patientType = patientType;
	}
	public String getCostKind ()
	{
		return costKind ;
	}
	
	public void setCostKind (String costKind )
	{
		this.costKind = costKind;
	}
	public String getContractUnit ()
	{
		return contractUnit ;
	}
	
	public void setContractUnit (String contractUnit )
	{
		this.contractUnit = contractUnit;
	}
	public String getAddress ()
	{
		return address ;
	}
	
	public void setAddress (String address )
	{
		this.address = address;
	}
	public String getPostCode ()
	{
		return postCode ;
	}
	
	public void setPostCode (String postCode )
	{
		this.postCode = postCode;
	}
	public String getFamilyPhoneNum ()
	{
		return familyPhoneNum ;
	}
	
	public void setFamilyPhoneNum (String familyPhoneNum )
	{
		this.familyPhoneNum = familyPhoneNum;
	}
	public String getUnitPhoneNum ()
	{
		return unitPhoneNum ;
	}
	
	public void setUnitPhoneNum (String unitPhoneNum )
	{
		this.unitPhoneNum = unitPhoneNum;
	}
	public String getLinkName ()
	{
		return linkName ;
	}
	
	public void setLinkName (String linkName )
	{
		this.linkName = linkName;
	}
	public String getLinkRelation ()
	{
		return linkRelation ;
	}
	
	public void setLinkRelation (String linkRelation )
	{
		this.linkRelation = linkRelation;
	}
	public String getLinkAddress ()
	{
		return linkAddress ;
	}
	
	public void setLinkAddress (String linkAddress )
	{
		this.linkAddress = linkAddress;
	}
	public String getLinkPostCode ()
	{
		return linkPostCode ;
	}
	
	public void setLinkPostCode (String linkPostCode )
	{
		this.linkPostCode = linkPostCode;
	}
	public String getLinkPhoneNum ()
	{
		return linkPhoneNum ;
	}
	
	public void setLinkPhoneNum (String linkPhoneNum )
	{
		this.linkPhoneNum = linkPhoneNum;
	}
	public java.util.Date getLastLookDoctorDate ()
	{
		return lastLookDoctorDate ;
	}
	
	public void setLastLookDoctorDate (java.util.Date lastLookDoctorDate )
	{
		this.lastLookDoctorDate = lastLookDoctorDate;
	}
	public String getIsImportment ()
	{
		return isImportment ;
	}
	
	public void setIsImportment (String isImportment )
	{
		this.isImportment = isImportment;
	}
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
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
		return "SickerInfo [" +" id=" +  id   +", hosId=" +  hosId   +", patientId=" +  patientId   +", patientInHosId=" +  patientInHosId   +", patientName=" +  patientName   +", patientShortPinyin=" +  patientShortPinyin   +", patientSex=" +  patientSex   +", patientBirthday=" +  patientBirthday   +", patientBornAddress=" +  patientBornAddress   +", patientCountry=" +  patientCountry   +", patientNation=" +  patientNation   +", patientCard=" +  patientCard   +", patientType=" +  patientType   +", costKind=" +  costKind   +", contractUnit=" +  contractUnit   +", address=" +  address   +", postCode=" +  postCode   +", familyPhoneNum=" +  familyPhoneNum   +", unitPhoneNum=" +  unitPhoneNum   +", linkName=" +  linkName   +", linkRelation=" +  linkRelation   +", linkAddress=" +  linkAddress   +", linkPostCode=" +  linkPostCode   +", linkPhoneNum=" +  linkPhoneNum   +", lastLookDoctorDate=" +  lastLookDoctorDate   +", isImportment=" +  isImportment   +", fillDate=" +  fillDate   +", filler=" +  filler   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}