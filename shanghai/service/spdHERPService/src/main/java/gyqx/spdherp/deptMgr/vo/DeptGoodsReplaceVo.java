
package gyqx.spdherp.deptMgr.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 科室商品信息修改Vo
 */
public class DeptGoodsReplaceVo implements Serializable {

	private static final long serialVersionUID = -4498578128713736689L;

	/**
	 * ID
	 */
	private String id;

	/**
	 * 供应商Id
	 */
	private String provId;
	/**
	 * 供应商名称
	 */
	private String provName;

	/**
	 * 医院Id
	 */
	private String hosId;
	/**
	 * 医院名称
	 */
	private String hosName;

	/**
	 * 配送商产品ID
	 */
	private String provGoodsId;
	/**
	 * 产品His编码
	 */
	private String hisCode;
	/**
	 * 产品His名称
	 */
	private String hisName;
	/**
	 * 产品His单位
	 */
	private String hisUnit;
	/**
	 *
	 */
	private Integer unitRate;
	/**
	 * 产品规格
	 */
	private String goodsGg;
	/**
	 * 产地
	 */
	private String made;
	/**
	 * 生产厂商
	 */
	private String mfrsId;
	/**
	 * 生产厂商名称
	 */
	private String mfrsName;
	/**
	 * 生产商产品编码
	 */
	private String mfrsGoodsCode;
	/**
	 * erpCode
	 */
	private String erpCode;
	/**
	 * 阳光采购编码
	 */
	private String hitCode;
	/**
	 * 阳光采购价
	 */
	private java.math.BigDecimal hitPrice;
	/**
	 * 包装
	 */
	private java.math.BigDecimal packeage;
	/**
	 * 产品主码
	 */
	private String masterCode;
	/**
	 * 68分类
	 */
	private String kind68Code;

	private String shortPinYin;
	/**
	 * 产品名称
	 */
	private String goodsName;

	private String code;

	private String unit;
	/**
	 * 储运条件
	 */
	private String stCondition;
	/**
	 * 产品单价
	 */
	private java.math.BigDecimal price;
	/**
	 * 税率
	 */
	private java.math.BigDecimal taxRate;
	/**
	 * 售价
	 */
	private java.math.BigDecimal salePrice;
	/**
	 * 供应商erpCode
	 */
	private String provErpCode;
	/**
	 * 医院erpCode
	 */
	private String hosErpCode;

	private java.math.BigDecimal purPrice;
	/**
	 * 唯一码管理策略
	 */
	private String uniqueCodeStrategy;
	/**
	 * 医保编码
	 */
	private String miCode;
	/**
	 * 采购模式
 	 */
	private String purMode;
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 证照编码
	 */
	private String certificateCode ;

	private String flag;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getHosName() {
		return hosName;
	}

	public void setHosName(String hosName) {
		this.hosName = hosName;
	}

	public String getProvGoodsId() {
		return provGoodsId;
	}

	public void setProvGoodsId(String provGoodsId) {
		this.provGoodsId = provGoodsId;
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

	public Integer getUnitRate() {
		return unitRate;
	}

	public void setUnitRate(Integer unitRate) {
		this.unitRate = unitRate;
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

	public BigDecimal getHitPrice() {
		return hitPrice;
	}

	public void setHitPrice(BigDecimal hitPrice) {
		this.hitPrice = hitPrice;
	}

	public BigDecimal getPackeage() {
		return packeage;
	}

	public void setPackeage(BigDecimal packeage) {
		this.packeage = packeage;
	}

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public String getKind68Code() {
		return kind68Code;
	}

	public void setKind68Code(String kind68Code) {
		this.kind68Code = kind68Code;
	}

	public String getShortPinYin() {
		return shortPinYin;
	}

	public void setShortPinYin(String shortPinYin) {
		this.shortPinYin = shortPinYin;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getStCondition() {
		return stCondition;
	}

	public void setStCondition(String stCondition) {
		this.stCondition = stCondition;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getProvErpCode() {
		return provErpCode;
	}

	public void setProvErpCode(String provErpCode) {
		this.provErpCode = provErpCode;
	}

	public String getHosErpCode() {
		return hosErpCode;
	}

	public void setHosErpCode(String hosErpCode) {
		this.hosErpCode = hosErpCode;
	}

	public BigDecimal getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(BigDecimal purPrice) {
		this.purPrice = purPrice;
	}

	public String getUniqueCodeStrategy() {
		return uniqueCodeStrategy;
	}

	public void setUniqueCodeStrategy(String uniqueCodeStrategy) {
		this.uniqueCodeStrategy = uniqueCodeStrategy;
	}

	public String getMiCode() {
		return miCode;
	}

	public void setMiCode(String miCode) {
		this.miCode = miCode;
	}

	public String getPurMode() {
		return purMode;
	}

	public void setPurMode(String purMode) {
		this.purMode = purMode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}

	public String getMfrsGoodsCode() {
		return mfrsGoodsCode;
	}

	public void setMfrsGoodsCode(String mfrsGoodsCode) {
		this.mfrsGoodsCode = mfrsGoodsCode;
	}

	@Override
	public String toString() {
		return "DeptGoodsReplaceVo{" +
				"id='" + id + '\'' +
				", provId='" + provId + '\'' +
				", provName='" + provName + '\'' +
				", hosId='" + hosId + '\'' +
				", hosName='" + hosName + '\'' +
				", provGoodsId='" + provGoodsId + '\'' +
				", hisCode='" + hisCode + '\'' +
				", hisName='" + hisName + '\'' +
				", hisUnit='" + hisUnit + '\'' +
				", unitRate=" + unitRate +
				", goodsGg='" + goodsGg + '\'' +
				", made='" + made + '\'' +
				", mfrsId='" + mfrsId + '\'' +
				", mfrsName='" + mfrsName + '\'' +
				", mfrsGoodsCode='" + mfrsGoodsCode + '\'' +
				", erpCode='" + erpCode + '\'' +
				", hitCode='" + hitCode + '\'' +
				", hitPrice=" + hitPrice +
				", packeage=" + packeage +
				", masterCode='" + masterCode + '\'' +
				", kind68Code='" + kind68Code + '\'' +
				", shortPinYin='" + shortPinYin + '\'' +
				", goodsName='" + goodsName + '\'' +
				", code='" + code + '\'' +
				", unit='" + unit + '\'' +
				", stCondition='" + stCondition + '\'' +
				", price=" + price +
				", taxRate=" + taxRate +
				", salePrice=" + salePrice +
				", provErpCode='" + provErpCode + '\'' +
				", hosErpCode='" + hosErpCode + '\'' +
				", purPrice=" + purPrice +
				", uniqueCodeStrategy='" + uniqueCodeStrategy + '\'' +
				", miCode='" + miCode + '\'' +
				", purMode='" + purMode + '\'' +
				", brand='" + brand + '\'' +
				", certificateCode='" + certificateCode + '\'' +
				'}';
	}
}