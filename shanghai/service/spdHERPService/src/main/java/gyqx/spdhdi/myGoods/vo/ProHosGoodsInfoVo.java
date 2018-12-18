package gyqx.spdhdi.myGoods.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProHosGoodsInfoVo implements Serializable{	
	private static final long serialVersionUID = 740581847373071985L;
	private String id;
	private String provId;
	private String hosId;
	private String provGoodsId;
	private String hisCode;
	private String hisName;
	private String hisUnit;
	private BigDecimal salePrice;
	private BigDecimal unitRate;
	private BigDecimal purPrice;
	private float taxRate;
	private String goodsGg;
	private String made;
	private String mfrsId;
	private String erpCode;
	private String hitCode;
	private String hitPrice;
	private String packeage;
	private String masterCode;
	private String kind68Code;
	private String shortPinyin;
	private String goodsName;
	private String code;
	private String unit;
	private String provName;
	private String hosName;
	private String mfrsName;
	private String isHisCodeNotNull;
	private String deptId;						//医院部门ID
	private Integer purMode;					//采购方式
	private Integer uniqueCodeStrategy;
	private String saleManId;
	private String[] provIds;
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
	public String getHosId() {
		return hosId;
	}
	public void setHosId(String hosId) {
		this.hosId = hosId;
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
	public BigDecimal getPurPrice() {
		return purPrice;
	}
	public void setPurPrice(BigDecimal purPrice) {
		this.purPrice = purPrice;
	}
	public void setHisUnit(String hisUnit) {
		this.hisUnit = hisUnit;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public BigDecimal getUnitRate() {
		return unitRate;
	}
	public void setUnitRate(BigDecimal unitRate) {
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
	public String getMfrsId() {
		return mfrsId;
	}
	public void setMfrsId(String mfrsId) {
		this.mfrsId = mfrsId;
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
	public String getHitPrice() {
		return hitPrice;
	}
	public void setHitPrice(String hitPrice) {
		this.hitPrice = hitPrice;
	}
	public String getPackeage() {
		return packeage;
	}
	public void setPackeage(String packeage) {
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
	public String getShortPinyin() {
		return shortPinyin;
	}
	public void setShortPinyin(String shortPinyin) {
		this.shortPinyin = shortPinyin;
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
	public String getProvName() {
		return provName;
	}
	public void setProvName(String provName) {
		this.provName = provName;
	}
	public String getHosName() {
		return hosName;
	}
	public void setHosName(String hosName) {
		this.hosName = hosName;
	}
	public String getMfrsName() {
		return mfrsName;
	}
	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIsHisCodeNotNull() {
		return isHisCodeNotNull;
	}
	public void setIsHisCodeNull(String isHisCodeNotNull) {
		this.isHisCodeNotNull = isHisCodeNotNull;
	}
	public float getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Integer getPurMode() {
		return purMode;
	}

	public void setPurMode(Integer purMode) {
		this.purMode = purMode;
	}
	public Integer getUniqueCodeStrategy() {
		return uniqueCodeStrategy;
	}
	public void setUniqueCodeStrategy(Integer uniqueCodeStrategy) {
		this.uniqueCodeStrategy = uniqueCodeStrategy;
	}

	public String getSaleManId() {
		return saleManId;
	}

	public void setSaleManId(String saleManId) {
		this.saleManId = saleManId;
	}

    public String[] getProvIds() {
        return provIds;
    }

    public void setProvIds(String[] provIds) {
        this.provIds = provIds;
    }
}
