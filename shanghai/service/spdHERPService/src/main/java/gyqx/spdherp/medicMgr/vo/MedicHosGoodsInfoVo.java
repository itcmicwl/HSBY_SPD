package gyqx.spdherp.medicMgr.vo;

import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Size;

public class MedicHosGoodsInfoVo {
	/**
	 * ID
	 */
	@Id
	@Size(max = 36)
	@Column(name = "id")
	@Title("ID")
	private String id;
	/**
	 * 医院ID
	 */
	@Size(max = 36)
	@JoinColumn(name = "hos_id", table = "bas_company_info", referencedColumnName = "id")
	@Title("医院ID")
	private String hosId;
	/**
	 * 编号
	 */
	@Size(max = 36)
	@Column(name = "code")
	@Title("编号")
	private String code;
	/**
	 * 名称
	 */
	@Size(max = 300)
	@Column(name = "goods_name")
	@Title("名称")
	private String goodsName;
	/**
	 * 拼音码
	 */
	@Size(max = 72)
	@Column(name = "short_pinyin")
	@Title("拼音码")
	private String shortPinyin;
	/**
	 * 规格型号
	 */
	@Size(max = 300)
	@Column(name = "goods_gg")
	@Title("规格型号")
	private String goodsGg;
	/**
	 * 产地
	 */
	@Size(max = 36)
	@Column(name = "made")
	@Title("产地")
	private String made;
	/**
	 * 生产厂商
	 */
	@Size(max = 36)
	@JoinColumn(name = "mfrs_id", table = "bas_mfrs_info", referencedColumnName = "id")
	@Title("生产厂商")
	private String mfrsId;
	/**
	 * 院内厂商名称
	 */
	@Size(max = 128)
	@Column(name = "hos_mfrs_name")
	@Title("院内厂商名称")
	private String hosMfrsName;
	/**
	 * 单位
	 */
	@Size(max = 36)
	@Column(name = "unit")
	@Title("单位")
	private String unit;
	/**
	 * 包装
	 */
	@Column(name = "packeage")
	@Title("包装")
	private java.math.BigDecimal packeage;
	/**
	 * 单价
	 */
	@Column(name = "price")
	@Title("单价")
	private java.math.BigDecimal price;
	/**
	 * 唯一码管理策略
	 */
	@Column(name = "unique_code_strategy")
	@Title("唯一码管理策略")
	private String uniqueCodeStrategy;
	/**
	 * 税率
	 */
	@Column(name = "tax_rate")
	@Title("税率")
	private java.math.BigDecimal taxRate;
	/**
	 * 统一码
	 */
	@Size(max = 128)
	@Column(name = "master_code")
	@Title("统一码")
	private String masterCode;
	/**
	 * 厂家出厂编码
	 */
	@Size(max = 128)
	@Column(name = "mfrs_code")
	@Title("厂家出厂编码")
	private String mfrsCode;
	/**
	 * 类别
	 */
	@Size(max = 36)
	@Column(name = "kind_code")
	@Title("类别")
	private String kindCode;
	/**
	 * 68分类
	 * 
	 * 68分类
	 */
	@Size(max = 36)
	@Column(name = "kind_68code")
	@Title("68分类")
	private String kind68code;
	/**
	 * erp编码
	 */
	@Size(max = 36)
	@Column(name = "erp_code")
	@Title("erp编码")
	private String erpCode;
	/**
	 * 对应平台编码
	 */
	@Size(max = 128)
	@Column(name = "spd_goods_code")
	@Title("对应平台编码")
	private String spdGoodsCode;
	/**
	 * 编码2
	 */
	@Size(max = 128)
	@Column(name = "field_code2")
	@Title("编码2")
	private String fieldCode2;
	/**
	 * 编码3
	 */
	@Size(max = 128)
	@Column(name = "field_code3")
	@Title("编码3")
	private String fieldCode3;
	/**
	 * 编码4
	 */
	@Size(max = 128)
	@Column(name = "field_code4")
	@Title("编码4")
	private String fieldCode4;
	/**
	 * 阳光采购编码
	 */
	@Size(max = 36)
	@Column(name = "hit_code")
	@Title("阳光采购编码")
	private String hitCode;
	/**
	 * 阳光采购价
	 */
	@Column(name = "hit_price")
	@Title("阳光采购价")
	private java.math.BigDecimal hitPrice;
	/**
	 * HIS销售价
	 */
	@Column(name = "his_price")
	@Title("HIS销售价")
	private java.math.BigDecimal hisPrice;
	/**
	 * 类别属性
	 * 
	 * 类别属性
	 */
	@Size(max = 36)
	@Column(name = "lbsx")
	@Title("类别属性")
	private String lbsx;
	/**
	 * 备注信息
	 */
	@Size(max = 128)
	@Column(name = "remark")
	@Title("备注信息")
	private String remark;
	/**
	 * 标志
	 */
	@Size(max = 1)
	@Column(name = "flag")
	@Title("标志")
	private String flag;
	/**
	 * 操作员ID 新增或修改的操作员ID
	 */
	@Size(max = 36)
    @Column(name = "uxid")
    @Title("操作员ID")
	private String uid;
	/**
	 * 建档时间
	 */
	@Column(name = "fill_date")
	@Title("建档时间")
	private java.util.Date fillDate;
	/**
	 * 最后更新时间
	 */
	@Column(name = "last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime;
	/**
	 * 数据版本
	 */
	@Column(name = "version")
	@Title("数据版本")
	private int version;
	/**
	 * 供应商ID
	 */
	@Column(name = "provId")
	@Title("供应商ID")
	private String provId;
	/**
	 * Vo 新增bas_mfrs_info 表中 mfrs_name
	 */
	@Column(name = "mfrsName")
	@Title("厂商名称")
	private String mfrsName;

	/**
	 * Vo 新增bas_company_info 表中 cname
	 */
	@Column(name = "provName")
	@Title("供应商名称")
	private String provName;

	/**
	 * Vo 新增hos_kindcode 表中 kind_name（lbsx 存该表的id）
	 */
	@Column(name = "hosKindName")
	@Title("医院分类名称")
	private String hosKindName;

	/**
	 * 货位产品信息ID add by xuliqiang 2017-09-05
	 */
	private String ssgiId;
	
	
	/**
	 * 试剂产品信息主键盘
	*/
	@Size(max=36)
	@Column(name="id")
	@Title("ID")
	private String medicHosGoodsId ;
	
	/**
	 * 产品ID
	*/
	@Size(max=36)
	@Column(name="goods_id")
	@Title("产品ID")
	private String goodsId ;
	/**
	 * 温度上限
	*/
	@Column(name="temperature_upper")
	@Title("温度上限")
	private java.math.BigDecimal temperatureUpper ;
	/**
	 * 温度下限
	*/
	@Column(name="temperature_lower")
	@Title("温度下限")
	private java.math.BigDecimal temperatureLower ;
	/**
	 * 数据版本
	*/
	@Column(name="version")
	@Title("试剂产品数据版本")
	private int medicDataVersion ;

	public String getMedicHosGoodsId ()
	{
		return medicHosGoodsId ;
	}
	
	public void setMedicHosGoodsId (String medicHosGoodsId )
	{
		this.medicHosGoodsId = medicHosGoodsId;
	}
	
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
	}
	public java.math.BigDecimal getTemperatureUpper ()
	{
		return temperatureUpper ;
	}
	
	public void setTemperatureUpper (java.math.BigDecimal temperatureUpper )
	{
		this.temperatureUpper = temperatureUpper;
	}
	public java.math.BigDecimal getTemperatureLower ()
	{
		return temperatureLower ;
	}
	
	public void setTemperatureLower (java.math.BigDecimal temperatureLower )
	{
		this.temperatureLower = temperatureLower;
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
		return "MedicalGoodsInfo [" +" id=" +  id   +", hosId=" +  hosId   +", goodsId=" +  goodsId   +", temperatureUpper=" +  temperatureUpper   +", temperatureLower=" +  temperatureLower   +", medicDataVersion=" +  medicDataVersion   
			 + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getShortPinyin() {
		return shortPinyin;
	}

	public void setShortPinyin(String shortPinyin) {
		this.shortPinyin = shortPinyin;
	}

	public String getGoodsGg() {
		return goodsGg;
	}

	public void setGoodsGg(String goodsGg) {
		this.goodsGg = goodsGg;
	}

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	public String getMfrsId() {
		return mfrsId;
	}

	public void setMfrsId(String mfrsId) {
		this.mfrsId = mfrsId;
	}

	public String getHosMfrsName() {
		return hosMfrsName;
	}

	public void setHosMfrsName(String hosMfrsName) {
		this.hosMfrsName = hosMfrsName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public java.math.BigDecimal getPackeage() {
		return packeage;
	}

	public void setPackeage(java.math.BigDecimal packeage) {
		this.packeage = packeage;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	public String getUniqueCodeStrategy() {
		return uniqueCodeStrategy;
	}

	public void setUniqueCodeStrategy(String uniqueCodeStrategy) {
		this.uniqueCodeStrategy = uniqueCodeStrategy;
	}

	public java.math.BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(java.math.BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public String getMfrsCode() {
		return mfrsCode;
	}

	public void setMfrsCode(String mfrsCode) {
		this.mfrsCode = mfrsCode;
	}

	public String getKindCode() {
		return kindCode;
	}

	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}

	public String getKind68code() {
		return kind68code;
	}

	public void setKind68code(String kind68code) {
		this.kind68code = kind68code;
	}

	public String getErpCode() {
		return erpCode;
	}

	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}

	public String getSpdGoodsCode() {
		return spdGoodsCode;
	}

	public void setSpdGoodsCode(String spdGoodsCode) {
		this.spdGoodsCode = spdGoodsCode;
	}

	public String getFieldCode2() {
		return fieldCode2;
	}

	public void setFieldCode2(String fieldCode2) {
		this.fieldCode2 = fieldCode2;
	}

	public String getFieldCode3() {
		return fieldCode3;
	}

	public void setFieldCode3(String fieldCode3) {
		this.fieldCode3 = fieldCode3;
	}

	public String getFieldCode4() {
		return fieldCode4;
	}

	public void setFieldCode4(String fieldCode4) {
		this.fieldCode4 = fieldCode4;
	}

	public String getHitCode() {
		return hitCode;
	}

	public void setHitCode(String hitCode) {
		this.hitCode = hitCode;
	}

	public java.math.BigDecimal getHitPrice() {
		return hitPrice;
	}

	public void setHitPrice(java.math.BigDecimal hitPrice) {
		this.hitPrice = hitPrice;
	}

	public java.math.BigDecimal getHisPrice() {
		return hisPrice;
	}

	public void setHisPrice(java.math.BigDecimal hisPrice) {
		this.hisPrice = hisPrice;
	}

	public String getLbsx() {
		return lbsx;
	}

	public void setLbsx(String lbsx) {
		this.lbsx = lbsx;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public java.util.Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(java.util.Date fillDate) {
		this.fillDate = fillDate;
	}

	public java.util.Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(java.util.Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}

	public int getMedicDataVersion() {
		return medicDataVersion;
	}

	public void setMedicDataVersion(int medicDataVersion) {
		this.medicDataVersion = medicDataVersion;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public String getHosKindName() {
		return hosKindName;
	}

	public void setHosKindName(String hosKindName) {
		this.hosKindName = hosKindName;
	}

	public String getSsgiId() {
		return ssgiId;
	}

	public void setSsgiId(String ssgiId) {
		this.ssgiId = ssgiId;
	}

}
