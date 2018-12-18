
package gyqx.spdhdi.myInfos.vo;

import common.db.meta.Title;

import javax.persistence.Column;
import java.util.Date;

public class ProvRegistGoodsPushQ {
	/**
	 * 注册证推送ID
	 */
	private String pushId;
	/**
	 * 注册证ID
	 */
	@Column(name = "certificate_id")
	@Title("注册证ID")
	private String certificateId;
	/**
	 * 供应商ID
	 */
	@Column(name = "prov_id")
	@Title("供应商ID")
	private String provId;
	/**
	 * 医院ID
	 */
	@Column(name = "hos_id")
	@Title("医院ID")
	private String hosId;
	/**
	 * ID
	 */
	@Column(name = "id")
	@Title("ID")
	private String id;
	/**
	 * 供应商ID
	 */
	@Column(name = "prov_id1")
	@Title("供应商ID")
	private String provId1;
	/**
	 * 编号
	 */
	@Column(name = "code")
	@Title("编号")
	private String code;
	/**
	 * 名称
	 */
	@Column(name = "goods_name")
	@Title("名称")
	private String goodsName;
	/**
	 * 拼音码
	 */
	@Column(name = "short_pinyin")
	@Title("拼音码")
	private String shortPinyin;
	/**
	 * 规格型号
	 */
	@Column(name = "goods_gg")
	@Title("规格型号")
	private String goodsGg;
	/**
	 * 产地
	 */
	@Column(name = "made")
	@Title("产地")
	private String made;
	/**
	 * 生产厂商
	 */
	@Column(name = "mfrs_id")
	@Title("生产厂商")
	private String mfrsId;
	/**
	 * 单位
	 */
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
	 * 统一码
	 */
	@Column(name = "master_code")
	@Title("统一码")
	private String masterCode;
	/**
	 * 厂家出厂编码
	 */
	@Column(name = "mfrs_code")
	@Title("厂家出厂编码")
	private String mfrsCode;
	/**
	 * 类别
	 */
	@Column(name = "kind_code")
	@Title("类别")
	private String kindCode;
	/**
	 * 68分类
	 */
	@Column(name = "kind_68code")
	@Title("68分类")
	private String kind68code;
	/**
	 * erp编码
	 */
	@Column(name = "erp_code")
	@Title("erp编码")
	private String erpCode;
	/**
	 * 阳光采购平台编码
	 */
	@Column(name = "hit_code")
	@Title("阳光采购平台编码")
	private String hitCode;
	/**
	 * 中标价格
	 */
	@Column(name = "hit_price")
	@Title("中标价格")
	private java.math.BigDecimal hitPrice;
	/**
	 * 平台商品编码
	 */
	@Column(name = "spd_goods_code")
	@Title("平台商品编码")
	private String spdGoodsCode;
	/**
	 * 编码2
	 */
	@Column(name = "field_code2")
	@Title("编码2")
	private String fieldCode2;
	/**
	 * 编码3
	 */
	@Column(name = "field_code3")
	@Title("编码3")
	private String fieldCode3;
	/**
	 * 编码4
	 */
	@Column(name = "field_code4")
	@Title("编码4")
	private String fieldCode4;
	/**
	 * 类别属性
	 */
	@Column(name = "lbsx")
	@Title("类别属性")
	private String lbsx;
	/**
	 * 标志
	 */
	@Column(name = "flag")
	@Title("标志")
	private String flag;
	/**
	 * 备注信息
	 */
	@Column(name = "remark")
	@Title("备注信息")
	private String remark;
	/**
	 * 操作员ID 新增或修改的操作员ID
	 */
    @Column(name = "uxid")
    @Title("操作员ID")
	private String uid;
	/**
	 * fillDate
	 */
	@Column(name = "fill_date")
	@Title("fillDate$begin")
	private Date fillDate$begin;
	/**
	 * fillDate
	 */
	@Column(name = "fill_date")
	@Title("fillDate$end")
	private Date fillDate$end;
	/**
	 * lastUpdateDatetime
	 */
	@Column(name = "last_update_datetime")
	@Title("lastUpdateDatetime$begin")
	private Date lastUpdateDatetime$begin;
	/**
	 * lastUpdateDatetime
	 */
	@Column(name = "last_update_datetime")
	@Title("lastUpdateDatetime$end")
	private Date lastUpdateDatetime$end;
	/**
	 * 数据版本
	 */
	@Column(name = "version")
	@Title("数据版本")
	private Integer version;

	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvId1() {
		return provId1;
	}

	public void setProvId1(String provId1) {
		this.provId1 = provId1;
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

	public String getLbsx() {
		return lbsx;
	}

	public void setLbsx(String lbsx) {
		this.lbsx = lbsx;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getFillDate$begin() {
		return fillDate$begin;
	}

	public void setFillDate$begin(Date fillDate$begin) {
		this.fillDate$begin = fillDate$begin;
	}

	public Date getFillDate$end() {
		return fillDate$end;
	}

	public void setFillDate$end(Date fillDate$end) {
		this.fillDate$end = fillDate$end;
	}

	public Date getLastUpdateDatetime$begin() {
		return lastUpdateDatetime$begin;
	}

	public void setLastUpdateDatetime$begin(Date lastUpdateDatetime$begin) {
		this.lastUpdateDatetime$begin = lastUpdateDatetime$begin;
	}

	public Date getLastUpdateDatetime$end() {
		return lastUpdateDatetime$end;
	}

	public void setLastUpdateDatetime$end(Date lastUpdateDatetime$end) {
		this.lastUpdateDatetime$end = lastUpdateDatetime$end;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	@Override
	public String toString() {
		return "ProvRegistGoodsPushQ [pushId=" + pushId + ", certificateId=" + certificateId + ", provId=" + provId
				+ ", hosId=" + hosId + ", id=" + id + ", provId1=" + provId1 + ", code=" + code + ", goodsName="
				+ goodsName + ", shortPinyin=" + shortPinyin + ", goodsGg=" + goodsGg + ", made=" + made + ", mfrsId="
				+ mfrsId + ", unit=" + unit + ", packeage=" + packeage + ", masterCode=" + masterCode + ", mfrsCode="
				+ mfrsCode + ", kindCode=" + kindCode + ", kind68code=" + kind68code + ", erpCode=" + erpCode
				+ ", hitCode=" + hitCode + ", hitPrice=" + hitPrice + ", spdGoodsCode=" + spdGoodsCode + ", fieldCode2="
				+ fieldCode2 + ", fieldCode3=" + fieldCode3 + ", fieldCode4=" + fieldCode4 + ", lbsx=" + lbsx
				+ ", flag=" + flag + ", remark=" + remark + ", uid=" + uid + ", fillDate$begin=" + fillDate$begin
				+ ", fillDate$end=" + fillDate$end + ", lastUpdateDatetime$begin=" + lastUpdateDatetime$begin
				+ ", lastUpdateDatetime$end=" + lastUpdateDatetime$end + ", version=" + version + "]";
	}

}