
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 不良事件上报
*/
@Table(name="adverse_events")
public class AdverseEvents implements IBean
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
	 * 报告来源
	 * 1：个人2：使用单位3：经营企业4：生产企业
	*/
	@Size(max=1)
	@Column(name="report_source")
	@Title("报告来源")
	private String reportSource ;
	/**
	 * 单位名称
	*/
	@Size(max=300)
	@Column(name="org_name")
	@Title("单位名称")
	private String orgName ;
	/**
	 * 单位ID
	*/
	@Size(max=36)
	@Column(name="org_id")
	@Title("单位ID")
	private String orgId ;
	/**
	 * 联系地址
	*/
	@Size(max=300)
	@Column(name="content")
	@Title("联系地址")
	private String content ;
	/**
	 * 编码
	*/
	@Size(max=36)
	@Column(name="code")
	@Title("编码")
	private String code ;
	/**
	 * 邮编
	*/
	@Size(max=10)
	@Column(name="post_code")
	@Title("邮编")
	private String postCode ;
	/**
	 * 联系电话
	*/
	@Size(max=15)
	@Column(name="reporter_tel")
	@Title("联系电话")
	private String reporterTel ;
	/**
	 * 报告日期
	*/
	@Column(name="reprot_time")
	@Title("报告日期")
	private java.util.Date reprotTime ;
	/**
	 * 报告人
	*/
	@Size(max=100)
	@Column(name="reporter")
	@Title("报告人")
	private String reporter ;
	/**
	 * 报告人类别
	 * 1：医师2：技师3：护士4：工程师5：其他
	*/
	@Size(max=1)
	@Column(name="reporter_type")
	@Title("报告人类别")
	private String reporterType ;
	/**
	 * 患者姓名
	*/
	@Size(max=100)
	@Column(name="sick_name")
	@Title("患者姓名")
	private String sickName ;
	/**
	 * 患者年龄
	*/
	@Column(name="sick_age")
	@Title("患者年龄")
	private int sickAge ;
	/**
	 * 患者性别
	*/
	@Size(max=1)
	@Column(name="sick_sex")
	@Title("患者性别")
	private String sickSex ;
	/**
	 * 患者出生日期
	*/
	@Column(name="sick_birthday")
	@Title("患者出生日期")
	private java.util.Date sickBirthday ;
	/**
	 * 患者电话
	*/
	@Size(max=36)
	@Column(name="sick_tel")
	@Title("患者电话")
	private String sickTel ;
	/**
	 * 预期治疗疾病与作用
	*/
	@Size(max=1000)
	@Column(name="anticipation_effects")
	@Title("预期治疗疾病与作用")
	private String anticipationEffects ;
	/**
	 * 事件器械故障
	*/
	@Size(max=1000)
	@Column(name="event_apparatus_fault")
	@Title("事件器械故障")
	private String eventApparatusFault ;
	/**
	 * 事件主要伤害
	*/
	@Size(max=1000)
	@Column(name="event_main_hurt")
	@Title("事件主要伤害")
	private String eventMainHurt ;
	/**
	 * 事件发生日期
	*/
	@Column(name="event_occurrence_time")
	@Title("事件发生日期")
	private java.util.Date eventOccurrenceTime ;
	/**
	 * 事件发现或知悉日期
	*/
	@Column(name="event_informed_time")
	@Title("事件发现或知悉日期")
	private java.util.Date eventInformedTime ;
	/**
	 * 器械使用场所
	 * 1：医疗机构2：家庭3：其它
	*/
	@Size(max=1)
	@Column(name="apparatus_where_used")
	@Title("器械使用场所")
	private String apparatusWhereUsed ;
	/**
	 * 事件后果
	 * 1：死亡2：危及生命3：机构功能结构永久性损伤4：可能 导致机体功能结构永久性损伤5：需要内、外科治疗避免上述永久损伤6：其它
	*/
	@Size(max=1)
	@Column(name="event_result")
	@Title("事件后果")
	private String eventResult ;
	/**
	 * 事件陈述
	*/
	@Size(max=2000)
	@Column(name="event_detail")
	@Title("事件陈述")
	private String eventDetail ;
	/**
	 * 注册证号
	*/
	@Size(max=36)
	@Column(name="register_code")
	@Title("注册证号")
	private String registerCode ;
	/**
	 * 注册证名称
	*/
	@Size(max=36)
	@Column(name="register_name")
	@Title("注册证名称")
	private String registerName ;
	/**
	 * 产品ID
	*/
	@Size(max=36)
	@Column(name="goods_id")
	@Title("产品ID")
	private String goodsId ;
	/**
	 * 产品名称
	*/
	@Size(max=300)
	@Column(name="goods_name")
	@Title("产品名称")
	private String goodsName ;
	/**
	 * 产品型号
	*/
	@Size(max=300)
	@Column(name="goods_gg")
	@Title("产品型号")
	private String goodsGg ;
	/**
	 * 产品编码
	*/
	@Size(max=36)
	@Column(name="godds_code")
	@Title("产品编码")
	private String goddsCode ;
	/**
	 * 产品批号
	*/
	@Size(max=128)
	@Column(name="goods_batch")
	@Title("产品批号")
	private String goodsBatch ;
	/**
	 * 器械操作人
	 * 1：专业人员2：非专业人员3：患者
	*/
	@Size(max=1)
	@Column(name="operator")
	@Title("器械操作人")
	private String operator ;
	/**
	 * 有效期至
	*/
	@Column(name="end_date")
	@Title("有效期至")
	private java.util.Date endDate ;
	/**
	 * 生产日期
	*/
	@Column(name="production_date")
	@Title("生产日期")
	private java.util.Date productionDate ;
	/**
	 * 停用日期
	*/
	@Column(name="disable_date")
	@Title("停用日期")
	private java.util.Date disableDate ;
	/**
	 * 植入日期
	*/
	@Column(name="implan_date")
	@Title("植入日期")
	private java.util.Date implanDate ;
	/**
	 * 事件发生初步原因分析
	*/
	@Size(max=1000)
	@Column(name="event_reson")
	@Title("事件发生初步原因分析")
	private String eventReson ;
	/**
	 * 事件初步处理情况
	*/
	@Size(max=1000)
	@Column(name="event_deal_with")
	@Title("事件初步处理情况")
	private String eventDealWith ;
	/**
	 * 事件报告状态
	 * 1：已通知使用单位2：已通知生产企业3：已通知经营企业4：已通知药监部门
	*/
	@Size(max=1)
	@Column(name="event_report_status")
	@Title("事件报告状态")
	private String eventReportStatus ;
	/**
	 * 关联性评价问题一
	 * 1：是0：否
	*/
	@Size(max=1)
	@Column(name="associate_question1")
	@Title("关联性评价问题一")
	private String associateQuestion1 ;
	/**
	 * 关联性评价问题二
	 * 1：是0：否2：无法确定
	*/
	@Size(max=1)
	@Column(name="associate_question2")
	@Title("关联性评价问题二")
	private String associateQuestion2 ;
	/**
	 * 关联性评价问题三
	 * 1：是2：否3：无法确定
	*/
	@Size(max=1)
	@Column(name="associate_question3")
	@Title("关联性评价问题三")
	private String associateQuestion3 ;
	/**
	 * 关联性评价结果
	 * 1：很有可能2：可能有关3：可能无关4：无法确定
	*/
	@Size(max=1)
	@Column(name="associate_result")
	@Title("关联性评价结果")
	private String associateResult ;
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
	public String getReportSource ()
	{
		return reportSource ;
	}
	
	public void setReportSource (String reportSource )
	{
		this.reportSource = reportSource;
	}
	public String getOrgName ()
	{
		return orgName ;
	}
	
	public void setOrgName (String orgName )
	{
		this.orgName = orgName;
	}
	public String getOrgId ()
	{
		return orgId ;
	}
	
	public void setOrgId (String orgId )
	{
		this.orgId = orgId;
	}
	public String getContent ()
	{
		return content ;
	}
	
	public void setContent (String content )
	{
		this.content = content;
	}
	public String getCode ()
	{
		return code ;
	}
	
	public void setCode (String code )
	{
		this.code = code;
	}
	public String getPostCode ()
	{
		return postCode ;
	}
	
	public void setPostCode (String postCode )
	{
		this.postCode = postCode;
	}
	public String getReporterTel ()
	{
		return reporterTel ;
	}
	
	public void setReporterTel (String reporterTel )
	{
		this.reporterTel = reporterTel;
	}
	public java.util.Date getReprotTime ()
	{
		return reprotTime ;
	}
	
	public void setReprotTime (java.util.Date reprotTime )
	{
		this.reprotTime = reprotTime;
	}
	public String getReporter ()
	{
		return reporter ;
	}
	
	public void setReporter (String reporter )
	{
		this.reporter = reporter;
	}
	public String getReporterType ()
	{
		return reporterType ;
	}
	
	public void setReporterType (String reporterType )
	{
		this.reporterType = reporterType;
	}
	public String getSickName ()
	{
		return sickName ;
	}
	
	public void setSickName (String sickName )
	{
		this.sickName = sickName;
	}
	public int getSickAge ()
	{
		return sickAge ;
	}
	
	public void setSickAge (int sickAge )
	{
		this.sickAge = sickAge;
	}
	public String getSickSex ()
	{
		return sickSex ;
	}
	
	public void setSickSex (String sickSex )
	{
		this.sickSex = sickSex;
	}
	public java.util.Date getSickBirthday ()
	{
		return sickBirthday ;
	}
	
	public void setSickBirthday (java.util.Date sickBirthday )
	{
		this.sickBirthday = sickBirthday;
	}
	public String getSickTel ()
	{
		return sickTel ;
	}
	
	public void setSickTel (String sickTel )
	{
		this.sickTel = sickTel;
	}
	public String getAnticipationEffects ()
	{
		return anticipationEffects ;
	}
	
	public void setAnticipationEffects (String anticipationEffects )
	{
		this.anticipationEffects = anticipationEffects;
	}
	public String getEventApparatusFault ()
	{
		return eventApparatusFault ;
	}
	
	public void setEventApparatusFault (String eventApparatusFault )
	{
		this.eventApparatusFault = eventApparatusFault;
	}
	public String getEventMainHurt ()
	{
		return eventMainHurt ;
	}
	
	public void setEventMainHurt (String eventMainHurt )
	{
		this.eventMainHurt = eventMainHurt;
	}
	public java.util.Date getEventOccurrenceTime ()
	{
		return eventOccurrenceTime ;
	}
	
	public void setEventOccurrenceTime (java.util.Date eventOccurrenceTime )
	{
		this.eventOccurrenceTime = eventOccurrenceTime;
	}
	public java.util.Date getEventInformedTime ()
	{
		return eventInformedTime ;
	}
	
	public void setEventInformedTime (java.util.Date eventInformedTime )
	{
		this.eventInformedTime = eventInformedTime;
	}
	public String getApparatusWhereUsed ()
	{
		return apparatusWhereUsed ;
	}
	
	public void setApparatusWhereUsed (String apparatusWhereUsed )
	{
		this.apparatusWhereUsed = apparatusWhereUsed;
	}
	public String getEventResult ()
	{
		return eventResult ;
	}
	
	public void setEventResult (String eventResult )
	{
		this.eventResult = eventResult;
	}
	public String getEventDetail ()
	{
		return eventDetail ;
	}
	
	public void setEventDetail (String eventDetail )
	{
		this.eventDetail = eventDetail;
	}
	public String getRegisterCode ()
	{
		return registerCode ;
	}
	
	public void setRegisterCode (String registerCode )
	{
		this.registerCode = registerCode;
	}
	public String getRegisterName ()
	{
		return registerName ;
	}
	
	public void setRegisterName (String registerName )
	{
		this.registerName = registerName;
	}
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
	}
	public String getGoodsName ()
	{
		return goodsName ;
	}
	
	public void setGoodsName (String goodsName )
	{
		this.goodsName = goodsName;
	}
	public String getGoodsGg ()
	{
		return goodsGg ;
	}
	
	public void setGoodsGg (String goodsGg )
	{
		this.goodsGg = goodsGg;
	}
	public String getGoddsCode ()
	{
		return goddsCode ;
	}
	
	public void setGoddsCode (String goddsCode )
	{
		this.goddsCode = goddsCode;
	}
	public String getGoodsBatch ()
	{
		return goodsBatch ;
	}
	
	public void setGoodsBatch (String goodsBatch )
	{
		this.goodsBatch = goodsBatch;
	}
	public String getOperator ()
	{
		return operator ;
	}
	
	public void setOperator (String operator )
	{
		this.operator = operator;
	}
	public java.util.Date getEndDate ()
	{
		return endDate ;
	}
	
	public void setEndDate (java.util.Date endDate )
	{
		this.endDate = endDate;
	}
	public java.util.Date getProductionDate ()
	{
		return productionDate ;
	}
	
	public void setProductionDate (java.util.Date productionDate )
	{
		this.productionDate = productionDate;
	}
	public java.util.Date getDisableDate ()
	{
		return disableDate ;
	}
	
	public void setDisableDate (java.util.Date disableDate )
	{
		this.disableDate = disableDate;
	}
	public java.util.Date getImplanDate ()
	{
		return implanDate ;
	}
	
	public void setImplanDate (java.util.Date implanDate )
	{
		this.implanDate = implanDate;
	}
	public String getEventReson ()
	{
		return eventReson ;
	}
	
	public void setEventReson (String eventReson )
	{
		this.eventReson = eventReson;
	}
	public String getEventDealWith ()
	{
		return eventDealWith ;
	}
	
	public void setEventDealWith (String eventDealWith )
	{
		this.eventDealWith = eventDealWith;
	}
	public String getEventReportStatus ()
	{
		return eventReportStatus ;
	}
	
	public void setEventReportStatus (String eventReportStatus )
	{
		this.eventReportStatus = eventReportStatus;
	}
	public String getAssociateQuestion1 ()
	{
		return associateQuestion1 ;
	}
	
	public void setAssociateQuestion1 (String associateQuestion1 )
	{
		this.associateQuestion1 = associateQuestion1;
	}
	public String getAssociateQuestion2 ()
	{
		return associateQuestion2 ;
	}
	
	public void setAssociateQuestion2 (String associateQuestion2 )
	{
		this.associateQuestion2 = associateQuestion2;
	}
	public String getAssociateQuestion3 ()
	{
		return associateQuestion3 ;
	}
	
	public void setAssociateQuestion3 (String associateQuestion3 )
	{
		this.associateQuestion3 = associateQuestion3;
	}
	public String getAssociateResult ()
	{
		return associateResult ;
	}
	
	public void setAssociateResult (String associateResult )
	{
		this.associateResult = associateResult;
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
		return "AdverseEvents [" +" id=" +  id   +", reportSource=" +  reportSource   +", orgName=" +  orgName   +", orgId=" +  orgId   +", content=" +  content   +", code=" +  code   +", postCode=" +  postCode   +", reporterTel=" +  reporterTel   +", reprotTime=" +  reprotTime   +", reporter=" +  reporter   +", reporterType=" +  reporterType   +", sickName=" +  sickName   +", sickAge=" +  sickAge   +", sickSex=" +  sickSex   +", sickBirthday=" +  sickBirthday   +", sickTel=" +  sickTel   +", anticipationEffects=" +  anticipationEffects   +", eventApparatusFault=" +  eventApparatusFault   +", eventMainHurt=" +  eventMainHurt   +", eventOccurrenceTime=" +  eventOccurrenceTime   +", eventInformedTime=" +  eventInformedTime   +", apparatusWhereUsed=" +  apparatusWhereUsed   +", eventResult=" +  eventResult   +", eventDetail=" +  eventDetail   +", registerCode=" +  registerCode   +", registerName=" +  registerName   +", goodsId=" +  goodsId   +", goodsName=" +  goodsName   +", goodsGg=" +  goodsGg   +", goddsCode=" +  goddsCode   +", goodsBatch=" +  goodsBatch   +", operator=" +  operator   +", endDate=" +  endDate   +", productionDate=" +  productionDate   +", disableDate=" +  disableDate   +", implanDate=" +  implanDate   +", eventReson=" +  eventReson   +", eventDealWith=" +  eventDealWith   +", eventReportStatus=" +  eventReportStatus   +", associateQuestion1=" +  associateQuestion1   +", associateQuestion2=" +  associateQuestion2   +", associateQuestion3=" +  associateQuestion3   +", associateResult=" +  associateResult   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}