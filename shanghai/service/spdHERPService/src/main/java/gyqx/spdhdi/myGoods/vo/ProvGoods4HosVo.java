package gyqx.spdhdi.myGoods.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Size;

import common.db.meta.Title;

public class ProvGoods4HosVo implements Serializable{	
	
	/**
	 * ID
	*/
	@Id
	@Size(max=36)
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 供应商ID
	*/
	@Size(max=36)
	@JoinColumn(name="prov_id",table="bas_company_info",referencedColumnName="id")
	@Title("供应商ID")
	private String provId ;
	/**
	 * 医院ID
	*/
	@Size(max=36)
	@JoinColumn(name="hos_id",table="bas_company_info",referencedColumnName="id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 供应商产品ID
	*/
	@Size(max=36)
	@JoinColumn(name="prov_goodsid",table="prov_goods_info",referencedColumnName="id")
	@Title("供应商产品ID")
	private String provGoodsid ;
	/**
	 * 对应医院产品编码
	*/
	@Size(max=36)
	@Column(name="his_code")
	@Title("对应医院产品编码")
	private String hisCode ;
	/**
	 * 对应医院产品名称
	*/
	@Size(max=36)
	@Column(name="his_name")
	@Title("对应医院产品名称")
	private String hisName ;
	/**
	 * 对应医院产品单位
	*/
	@Size(max=36)
	@Column(name="his_unit")
	@Title("对应医院产品单位")
	private String hisUnit ;
	/**
	 * 采购价
	*/
	@Column(name="pur_price")
	@Title("采购价")
	private java.math.BigDecimal purPrice ;
	/**
	 * 配送单价
	*/
	@Column(name="sale_price")
	@Title("配送单价")
	private java.math.BigDecimal salePrice ;
	/**
	 * 单位比率
	*/
	@Column(name="unit_rate")
	@Title("单位比率")
	private java.math.BigDecimal unitRate ;
	/**
	 * 数据来源   //手动新增，批量导入，Excel导入 等
	*/
	@Size(max=36)
	@Column(name="data_source")   
	@Title("数据来源")
	private String dataSource ;
	/**
	 * 税率
	*/
	@Column(name="tax_rate")
	@Title("税率")
	private java.math.BigDecimal taxRate ;
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
     * 品牌
     */
    @Column(name = "brand")
    @Title("品牌")
    private String brand;
	
	//添加供应商产品信息内容  ProvGoodsInfo
	private String provGoodsCode;
	private String provGoodsName;
	private String provGoodsGg;
	private String provGoodsMade;
	private String provGoodsMfrsId;
	private String provGoodsUnit;
	private java.math.BigDecimal provGoodsPackeage;
	private String provGoodsMasterCode;
	private String provGoodsMfrsCode;
	private String provGoodsKindCode;
	private String provGoodsKind68code;
	private String provGoodsErpCode;
	private String provGoodsHitCode;
	private java.math.BigDecimal provGoodsHitPrice;
	private String provGoodsSpdGoodsCode;
	private String provGoodsLbsx;
	private String provGoodsFlag;
	private String provGoodsShortPinyin;
	private String provName;//经销商名
	
	private String isAll; //1查询所有供货目录，0 查询未添加过的供货目录
	
	public String getIsAll() {
		return isAll;
	}

	public void setIsAll(String isAll) {
		this.isAll = isAll;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	//添加厂家名称 
	private String provGoodsMfrsName;

	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getProvGoodsid ()
	{
		return provGoodsid ;
	}
	
	public void setProvGoodsid (String provGoodsid )
	{
		this.provGoodsid = provGoodsid;
	}
	public String getHisCode ()
	{
		return hisCode ;
	}
	
	public void setHisCode (String hisCode )
	{
		this.hisCode = hisCode;
	}
	public String getHisName ()
	{
		return hisName ;
	}
	
	public void setHisName (String hisName )
	{
		this.hisName = hisName;
	}
	public String getHisUnit ()
	{
		return hisUnit ;
	}
	
	public void setHisUnit (String hisUnit )
	{
		this.hisUnit = hisUnit;
	}
	public java.math.BigDecimal getPurPrice ()
	{
		return purPrice ;
	}
	
	public void setPurPrice (java.math.BigDecimal purPrice )
	{
		this.purPrice = purPrice;
	}
	public java.math.BigDecimal getSalePrice ()
	{
		return salePrice ;
	}
	
	public void setSalePrice (java.math.BigDecimal salePrice )
	{
		this.salePrice = salePrice;
	}
	public java.math.BigDecimal getUnitRate ()
	{
		return unitRate ;
	}
	
	public void setUnitRate (java.math.BigDecimal unitRate )
	{
		this.unitRate = unitRate;
	}
	public String getDataSource ()
	{
		return dataSource ;
	}
	
	public void setDataSource (String dataSource )
	{
		this.dataSource = dataSource;
	}
	public java.math.BigDecimal getTaxRate ()
	{
		return taxRate ;
	}
	
	public void setTaxRate (java.math.BigDecimal taxRate )
	{
		this.taxRate = taxRate;
	}
	public String getFlag ()
	{
		return flag ;
	}
	
	public void setFlag (String flag )
	{
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

	public String getProvGoodsCode() {
		return provGoodsCode;
	}

	public void setProvGoodsCode(String provGoodsCode) {
		this.provGoodsCode = provGoodsCode;
	}

	public String getProvGoodsName() {
		return provGoodsName;
	}

	public void setProvGoodsName(String provGoodsName) {
		this.provGoodsName = provGoodsName;
	}

	public String getProvGoodsGg() {
		return provGoodsGg;
	}

	public void setProvGoodsGg(String provGoodsGg) {
		this.provGoodsGg = provGoodsGg;
	}

	public String getProvGoodsMade() {
		return provGoodsMade;
	}

	public void setProvGoodsMade(String provGoodsMade) {
		this.provGoodsMade = provGoodsMade;
	}

	public String getProvGoodsMfrsId() {
		return provGoodsMfrsId;
	}

	public void setProvGoodsMfrsId(String provGoodsMfrsId) {
		this.provGoodsMfrsId = provGoodsMfrsId;
	}

	public String getProvGoodsUnit() {
		return provGoodsUnit;
	}

	public void setProvGoodsUnit(String provGoodsUnit) {
		this.provGoodsUnit = provGoodsUnit;
	}

	public java.math.BigDecimal getProvGoodsPackeage() {
		return provGoodsPackeage;
	}

	public void setProvGoodsPackeage(java.math.BigDecimal provGoodsPackeage) {
		this.provGoodsPackeage = provGoodsPackeage;
	}

	public String getProvGoodsMasterCode() {
		return provGoodsMasterCode;
	}

	public void setProvGoodsMasterCode(String provGoodsMasterCode) {
		this.provGoodsMasterCode = provGoodsMasterCode;
	}

	public String getProvGoodsMfrsCode() {
		return provGoodsMfrsCode;
	}

	public void setProvGoodsMfrsCode(String provGoodsMfrsCode) {
		this.provGoodsMfrsCode = provGoodsMfrsCode;
	}

	public String getProvGoodsKindCode() {
		return provGoodsKindCode;
	}

	public void setProvGoodsKindCode(String provGoodsKindCode) {
		this.provGoodsKindCode = provGoodsKindCode;
	}

	public String getProvGoodsKind68code() {
		return provGoodsKind68code;
	}

	public void setProvGoodsKind68code(String provGoodsKind68code) {
		this.provGoodsKind68code = provGoodsKind68code;
	}

	public String getProvGoodsErpCode() {
		return provGoodsErpCode;
	}

	public void setProvGoodsErpCode(String provGoodsErpCode) {
		this.provGoodsErpCode = provGoodsErpCode;
	}

	public String getProvGoodsHitCode() {
		return provGoodsHitCode;
	}

	public void setProvGoodsHitCode(String provGoodsHitCode) {
		this.provGoodsHitCode = provGoodsHitCode;
	}

	public java.math.BigDecimal getProvGoodsHitPrice() {
		return provGoodsHitPrice;
	}

	public void setProvGoodsHitPrice(java.math.BigDecimal provGoodsHitPrice) {
		this.provGoodsHitPrice = provGoodsHitPrice;
	}

	public String getProvGoodsSpdGoodsCode() {
		return provGoodsSpdGoodsCode;
	}

	public void setProvGoodsSpdGoodsCode(String provGoodsSpdGoodsCode) {
		this.provGoodsSpdGoodsCode = provGoodsSpdGoodsCode;
	}

	public String getProvGoodsLbsx() {
		return provGoodsLbsx;
	}

	public void setProvGoodsLbsx(String provGoodsLbsx) {
		this.provGoodsLbsx = provGoodsLbsx;
	}

	public String getProvGoodsFlag() {
		return provGoodsFlag;
	}

	public void setProvGoodsFlag(String provGoodsFlag) {
		this.provGoodsFlag = provGoodsFlag;
	}

	public String getProvGoodsMfrsName() {
		return provGoodsMfrsName;
	}

	public void setProvGoodsMfrsName(String provGoodsMfrsName) {
		this.provGoodsMfrsName = provGoodsMfrsName;
	}

	public String getProvGoodsShortPinyin() {
		return provGoodsShortPinyin;
	}

	public void setProvGoodsShortPinyin(String provGoodsShortPinyin) {
		this.provGoodsShortPinyin = provGoodsShortPinyin;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
}
