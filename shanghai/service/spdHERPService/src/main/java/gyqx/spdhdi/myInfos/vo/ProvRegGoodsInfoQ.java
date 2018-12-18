
package gyqx.spdhdi.myInfos.vo;

import java.util.*;
import javax.persistence.Column;
import common.db.meta.Title;

public class ProvRegGoodsInfoQ {
	/**
	 * 供应商ID
	 */
	@Column(name = "prov_id")
	@Title("供应商ID")
	private String provId;
	/**
	 * 注册证ID
	 */
	@Column(name = "certificate_id")
	@Title("注册证ID")
	private String certificateId;
	/**
	 * 医院ID
	 */
	@Column(name = "hos_id")
	@Title("医院ID")
	private String hosId;

	/**
	 * 产品ID
	 */
	@Column(name = "goods_id")
	@Title("产品ID")
	private String goodsId;
	/**
	 * fillDate
	 */
	@Column(name = "fill_date")
	@Title("fillDate")
	private Date fillDate;
	/**
	 * 名称
	 */
	@Column(name = "goods_name")
	@Title("名称")
	private String goodsName;
	/**
	 * 注册证产品名称
	 */
	@Column(name = "product_name")
	@Title("注册证产品名称")
	private String productName;
	/**
	 * 证照编码
	 */
	@Column(name = "certificate_code")
	@Title("证照编码")
	private String certificateCode;
	/**
	 * 生产厂家
	 */
	@Column(name = "mfrs_id")
	@Title("生产厂家")
	private String mfrsId;
	/**
	 * 是否历史证件
	 */
	@Column(name = "is_old_info")
	@Title("是否历史证件")
	private String isOldInfo;
	/**
	 * expdtBeginDate
	 */
	@Column(name = "expdt_begin_date")
	private Date expdtBeginDate;

	/**
	 * expdtEndDate
	 */
	@Column(name = "expdt_end_date")
	private Date expdtEndDate;
	/**
	 * 注册证类别
	 */
	@Column(name = "reg_kind")
	@Title("注册证类别")
	private String regKind;
	/**
	 * 规格型号
	 */
	@Column(name = "goods_gg")
	@Title("规格型号")
	private String goodsGg;
	/**
	 * 拼音码
	 */
	@Column(name = "short_pinyin")
	@Title("拼音码")
	private String shortPinyin;
	/**
	 * 产地
	 */
	@Column(name = "made")
	@Title("产地")
	private String made;
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
	 * 统一码
	 */
	@Column(name = "master_code")
	@Title("统一码")
	private String masterCode;
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
	 * 68分类
	 */
	@Column(name = "kind_68code")
	@Title("68分类")
	private String kind68code;
	/**
	 * 编号
	 */
	@Column(name = "code")
	@Title("编号")
	private String code;

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	public String getMfrsId() {
		return mfrsId;
	}

	public void setMfrsId(String mfrsId) {
		this.mfrsId = mfrsId;
	}

	public String getIsOldInfo() {
		return isOldInfo;
	}

	public void setIsOldInfo(String isOldInfo) {
		this.isOldInfo = isOldInfo;
	}

	public Date getExpdtBeginDate() {
		return expdtBeginDate;
	}

	public void setExpdtBeginDate(Date expdtBeginDate) {
		this.expdtBeginDate = expdtBeginDate;
	}

	public Date getExpdtEndDate() {
		return expdtEndDate;
	}

	public void setExpdtEndDate(Date expdtEndDate) {
		this.expdtEndDate = expdtEndDate;
	}

	public String getRegKind() {
		return regKind;
	}

	public void setRegKind(String regKind) {
		this.regKind = regKind;
	}

	public String getGoodsGg() {
		return goodsGg;
	}

	public void setGoodsGg(String goodsGg) {
		this.goodsGg = goodsGg;
	}

	public String getShortPinyin() {
		return shortPinyin;
	}

	public void setShortPinyin(String shortPinyin) {
		this.shortPinyin = shortPinyin;
	}

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
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

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
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

	public String getKind68code() {
		return kind68code;
	}

	public void setKind68code(String kind68code) {
		this.kind68code = kind68code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ProvRegGoodsInfoQ [provId=" + provId + ", certificateId=" + certificateId + ", hosId=" + hosId
				+ ", goodsId=" + goodsId + ", fillDate=" + fillDate + ", goodsName=" + goodsName + ", productName="
				+ productName + ", certificateCode=" + certificateCode + ", mfrsId=" + mfrsId + ", isOldInfo="
				+ isOldInfo + ", expdtBeginDate=" + expdtBeginDate + ", expdtEndDate=" + expdtEndDate + ", regKind="
				+ regKind + ", goodsGg=" + goodsGg + ", shortPinyin=" + shortPinyin + ", made=" + made + ", unit="
				+ unit + ", packeage=" + packeage + ", mfrsCode=" + mfrsCode + ", kindCode=" + kindCode
				+ ", masterCode=" + masterCode + ", erpCode=" + erpCode + ", hitCode=" + hitCode + ", hitPrice="
				+ hitPrice + ", spdGoodsCode=" + spdGoodsCode + ", fieldCode2=" + fieldCode2 + ", fieldCode3="
				+ fieldCode3 + ", fieldCode4=" + fieldCode4 + ", lbsx=" + lbsx + ", flag=" + flag + ", kind68code="
				+ kind68code + ", code=" + code + "]";
	}

}