
package gyqx.spdherp.provManager.vo;

/**
 * 供应商供货目录
 */
public class ProvGoodsVo {
	/**
	 * 供应商ID
	 */
	private String provId;
	/**
	 * 医院ID
	 */
	private String hosId;
	/**
	 * 对应医院产品编码
	 */
	private String hisCode;
	/**
	 * 对应医院产品名称
	 */
	private String hisName;
	/**
	 * 对应医院产品单位
	 */
	private String hisUnit;
	/**
	 * 采购价
	 */
	private java.math.BigDecimal purPrice;
	/**
	 * 配送单价
	 */
	private java.math.BigDecimal salePrice;
	/**
	 * 单位比率
	 */
	private java.math.BigDecimal unitRate;
	/**
	 * 税率
	 */
	private java.math.BigDecimal taxRate;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 名称
	 */
	private String goodsName;
	/**
	 * 规格型号
	 */
	private String goodsGg;
	/**
	 * 产地
	 */
	private String made;
	/**
	 * 生产厂商ID
	 */
	private String mfrsId;
	/**
	 * 生产厂商
	 */
	private String mfrsName;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 包装
	 */
	private java.math.BigDecimal packeage;

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

	public String getHisCode() {
		return hisCode;
	}

	public void setHisCode(String hisCode) {
		this.hisCode = hisCode;
	}

	public String getHisName() {
		return hisName;
	}

	public void setHisName(String hisName) {
		this.hisName = hisName;
	}

	public String getHisUnit() {
		return hisUnit;
	}

	public void setHisUnit(String hisUnit) {
		this.hisUnit = hisUnit;
	}

	public java.math.BigDecimal getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(java.math.BigDecimal purPrice) {
		this.purPrice = purPrice;
	}

	public java.math.BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(java.math.BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public java.math.BigDecimal getUnitRate() {
		return unitRate;
	}

	public void setUnitRate(java.math.BigDecimal unitRate) {
		this.unitRate = unitRate;
	}

	public java.math.BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(java.math.BigDecimal taxRate) {
		this.taxRate = taxRate;
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

	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
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

}