package gyqx.spdhdi.baseData.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import common.db.meta.Title;

public class BasGoodsInfoVo {
	/**
	 * ID
	*/
	@Id
	@Size(max=36)
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 编号
	*/
	@Size(max=36)
	@Column(name="code")
	@Title("编号")
	private String code ;
	/**
	 * 名称
	*/
	@Size(max=128)
	@Column(name="goods_name")
	@Title("名称")
	private String goodsName ;
	/**
	 * 拼音码
	*/
	@Size(max=72)
	@Column(name="short_pinyin")
	@Title("拼音码")
	private String shortPinyin ;
	/**
	 * 规格型号
	*/
	@Size(max=36)
	@Column(name="goods_gg")
	@Title("规格型号")
	private String goodsGg ;
	/**
	 * 产地
	*/
	@Size(max=36)
	@Column(name="made")
	@Title("产地")
	private String made ;
	/**
	 * 生产厂商
	*/
	@Size(max=36)
	@JoinColumn(name="mfrs_id",table="bas_mfrs_info",referencedColumnName="id")
	@Title("生产厂商")
	private String mfrsId ;
	/**
	 * 单位
	*/
	@Size(max=36)
	@Column(name="unit")
	@Title("单位")
	private String unit ;
	/**
	 * 包装
	*/
	@Column(name="packeage")
	@Title("包装")
	@DecimalMin("0")
	private java.math.BigDecimal packeage ;
	/**
	 * 统一码
	*/
	@Size(max=128)
	@Column(name="master_code")
	@Title("统一码")
	private String masterCode ;
	/**
	 * 厂家出厂编码
	*/
	@Size(max=128)
	@Column(name="mfrs_code")
	@Title("厂家出厂编码")
	private String mfrsCode ;
	/**
	 * 类别
	*/
	@Size(max=36)
	@Column(name="kind_code")
	@Title("类别")
	private String kindCode ;
	/**
	 * 68分类
	 * 
	 * 68分类
	*/
	@Size(max=36)
	@Column(name="kind_68code")
	@Title("68分类")
	private String kind68code ;
	/**
	 * 编码1
	*/
	@Size(max=128)
	@Column(name="field_code1")
	@Title("编码1")
	private String fieldCode1 ;
	/**
	 * 编码2
	*/
	@Size(max=128)
	@Column(name="field_code2")
	@Title("编码2")
	private String fieldCode2 ;
	/**
	 * 编码3
	*/
	@Size(max=128)
	@Column(name="field_code3")
	@Title("编码3")
	private String fieldCode3 ;
	/**
	 * 编码4
	*/
	@Size(max=128)
	@Column(name="field_code4")
	@Title("编码4")
	private String fieldCode4 ;
	/**
	 * 注册证号
	*/
	@Size(max=36)
	@Column(name="certificate_code")
	@Title("注册证号")
	private String certificateCode ;
	/**
	 * 类别属性
	 * 
	 * 类别属性
	*/
	@Size(max=36)
	@Column(name="lbsx")
	@Title("类别属性")
	private String lbsx ;
	/**
	 * 标志
	*/
	@Size(max=1)
	@Column(name="flag")
	@Title("标志")
	private String flag ;
	/**
	 * 备注信息
	*/
	@Size(max=128)
	@Column(name="remark")
	@Title("备注信息")
	private String remark ;
	/**
	 * 操作员ID
	 * 新增或修改的操作员ID
	*/
	@Size(max=36)
	@Column(name="uxid")
	@Title("操作员ID")
	private String uid ;
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
	 * Vo 新增bas_mfrs_info 表中 mfrs_name  
	 */
	@Column(name="mfrsName")
	@Title("厂商名称")
	private String mfrsName ;

	/**
	 * 品牌
	 */
	@Column(name="brand")
	@Title("品牌")
	private String brand ;
	
	
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public String getCode ()
	{
		return code ;
	}
	
	public void setCode (String code )
	{
		this.code = code;
	}
	public String getGoodsName ()
	{
		return goodsName ;
	}
	
	public void setGoodsName (String goodsName )
	{
		this.goodsName = goodsName;
	}
	public String getShortPinyin ()
	{
		return shortPinyin ;
	}
	
	public void setShortPinyin (String shortPinyin )
	{
		this.shortPinyin = shortPinyin;
	}
	public String getGoodsGg ()
	{
		return goodsGg ;
	}
	
	public void setGoodsGg (String goodsGg )
	{
		this.goodsGg = goodsGg;
	}
	public String getMade ()
	{
		return made ;
	}
	
	public void setMade (String made )
	{
		this.made = made;
	}
	public String getMfrsId ()
	{
		return mfrsId ;
	}
	
	public void setMfrsId (String mfrsId )
	{
		this.mfrsId = mfrsId;
	}
	public String getUnit ()
	{
		return unit ;
	}
	
	public void setUnit (String unit )
	{
		this.unit = unit;
	}
	public java.math.BigDecimal getPackeage ()
	{
		return packeage ;
	}
	
	public void setPackeage (java.math.BigDecimal packeage )
	{
		this.packeage = packeage;
	}
	public String getMasterCode ()
	{
		return masterCode ;
	}
	
	public void setMasterCode (String masterCode )
	{
		this.masterCode = masterCode;
	}
	public String getMfrsCode ()
	{
		return mfrsCode ;
	}
	
	public void setMfrsCode (String mfrsCode )
	{
		this.mfrsCode = mfrsCode;
	}
	public String getKindCode ()
	{
		return kindCode ;
	}
	
	public void setKindCode (String kindCode )
	{
		this.kindCode = kindCode;
	}
	public String getKind68code ()
	{
		return kind68code ;
	}
	
	public void setKind68code (String kind68code )
	{
		this.kind68code = kind68code;
	}
	public String getFieldCode1 ()
	{
		return fieldCode1 ;
	}
	
	public void setFieldCode1 (String fieldCode1 )
	{
		this.fieldCode1 = fieldCode1;
	}
	public String getFieldCode2 ()
	{
		return fieldCode2 ;
	}
	
	public void setFieldCode2 (String fieldCode2 )
	{
		this.fieldCode2 = fieldCode2;
	}
	public String getFieldCode3 ()
	{
		return fieldCode3 ;
	}
	
	public void setFieldCode3 (String fieldCode3 )
	{
		this.fieldCode3 = fieldCode3;
	}
	public String getFieldCode4 ()
	{
		return fieldCode4 ;
	}
	
	public void setFieldCode4 (String fieldCode4 )
	{
		this.fieldCode4 = fieldCode4;
	}
	public String getCertificateCode ()
	{
		return certificateCode ;
	}
	
	public void setCertificateCode (String certificateCode )
	{
		this.certificateCode = certificateCode;
	}
	public String getLbsx ()
	{
		return lbsx ;
	}
	
	public void setLbsx (String lbsx )
	{
		this.lbsx = lbsx;
	}
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
	}
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUid ()
	{
		return uid ;
	}
	
	public void setUid (String uid )
	{
		this.uid = uid;
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
	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}

	@Override
	public String toString() {
		return "BasGoodsInfoVo [id=" + id + ", code=" + code + ", goodsName=" + goodsName + ", shortPinyin="
				+ shortPinyin + ", goodsGg=" + goodsGg + ", made=" + made + ", mfrsId=" + mfrsId + ", unit=" + unit
				+ ", packeage=" + packeage + ", masterCode=" + masterCode + ", mfrsCode=" + mfrsCode + ", kindCode="
				+ kindCode + ", kind68code=" + kind68code + ", fieldCode1=" + fieldCode1 + ", fieldCode2=" + fieldCode2
				+ ", fieldCode3=" + fieldCode3 + ", fieldCode4=" + fieldCode4 + ", certificateCode=" + certificateCode
				+ ", lbsx=" + lbsx + ", flag=" + flag + ", remark=" + remark + ", uid=" + uid + ", fillDate=" + fillDate
				+ ", lastUpdateDatetime=" + lastUpdateDatetime + ", version=" + version + ", mfrsName=" + mfrsName
				+ ", brand=" + brand + "]";
	}

	
}
