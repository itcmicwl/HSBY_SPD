
package gyqx.spdherp.deptMgr.vo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import common.db.meta.Title;

/**
 * 科室商品信息Vo
 */
public class DeptGoodsInfoVo {
	
	// 产品数量
	private java.math.BigDecimal goodsNum;
	
	/**
	 * 过滤标志：有值则说明，查询科室产品信息过滤，科室模板产品导入时调用
	 */
	private String filterFlag;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 医疗机构ID
	 */
	private String hosId;
	/**
	 * 医疗机构名称
	 */
	private String hosName;
	/**
	 * 科室ID
	 */
	private String deptId;
	/**
	 * 科室名称
	 */
	private String deptName;
	/**
	 * 产品ID
	 */
	private String goodsId;
	/**
	 * 产品名称
	 */
	private String goodsName;
	/**
	 * 规格
	 */
	private String goodsGg;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 生产厂家ID
	 */
	private String mfrsId;
	/**
	 * 生产厂家名称
	 */
	private String mfrsName;
	/**
	 * 生产国
	 */
	private String made;
	/**
	 * 库存上限
	 */
	private java.math.BigDecimal stocUpper;
	/**
	 * 库存下限
	 */
	private java.math.BigDecimal stocLower;
	/**
	 * 是否定数包管理
	 */
	private String isPacket;
	/**
	 * 是否唯一码管理
	 */
	private String isUnique;
	/**
	 * 包装数量
	 */
	private java.math.BigDecimal packetCode;
	/**
	 * 定数包混批
	 */
	private String isMix;
	/**
	 * 采购模式
	 */
	private String purMode;
	/**
	 * 对应库房
	 */
	private String stocId;
	/**
	 * 库房名称
	 */
	private String stocName;
	/**
	 * 数据版本
	 */
	private int version;
    /**
     * 最小请购数量
     */
    private java.math.BigDecimal reqNum;
    
    /**
     * 品牌
     */
    private String brand;
    
    /**
     * 业务员id
     */
    private String salemanId;
    
    /**
     * 业务员名称
     */
    private String salemanName;
    
    /**
     * 业务员ERP中编码
     */
    private String salemanCode;
    
    /**
     * erp编码
     */
    private String erpCode;
    
    /**
     * 产品分类
     */
    private String lbsx;
    private Integer flag;

	public String getLbsx() {
		return lbsx;
	}

	public void setLbsx(String lbsx) {
		this.lbsx = lbsx;
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

	public String getHosName() {
		return hosName;
	}

	public void setHosName(String hosName) {
		this.hosName = hosName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	public java.math.BigDecimal getStocUpper() {
		return stocUpper;
	}

	public void setStocUpper(java.math.BigDecimal stocUpper) {
		this.stocUpper = stocUpper;
	}

	public java.math.BigDecimal getStocLower() {
		return stocLower;
	}

	public void setStocLower(java.math.BigDecimal stocLower) {
		this.stocLower = stocLower;
	}

	public String getIsPacket() {
		return isPacket;
	}

	public void setIsPacket(String isPacket) {
		this.isPacket = isPacket;
	}

	public String getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(String isUnique) {
		this.isUnique = isUnique;
	}

	public java.math.BigDecimal getPacketCode() {
		return packetCode;
	}

	public void setPacketCode(java.math.BigDecimal packetCode) {
		this.packetCode = packetCode;
	}

	public String getIsMix() {
		return isMix;
	}

	public void setIsMix(String isMix) {
		this.isMix = isMix;
	}

	public String getPurMode() {
		return purMode;
	}

	public void setPurMode(String purMode) {
		this.purMode = purMode;
	}

	public String getStocId() {
		return stocId;
	}

	public void setStocId(String stocId) {
		this.stocId = stocId;
	}

	public String getStocName() {
		return stocName;
	}

	public void setStocName(String stocName) {
		this.stocName = stocName;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getFilterFlag() {
		return filterFlag;
	}

	public void setFilterFlag(String filterFlag) {
		this.filterFlag = filterFlag;
	}

	public java.math.BigDecimal getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(java.math.BigDecimal goodsNum) {
		this.goodsNum = goodsNum;
    }

    public BigDecimal getReqNum() {
        return reqNum;
    }

    public void setReqNum(BigDecimal reqNum) {
        this.reqNum = reqNum;
    } 
    public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSalemanId() {
		return salemanId;
	}

	public void setSalemanId(String salemanId) {
		this.salemanId = salemanId;
	}

	public String getSalemanCode() {
		return salemanCode;
	}

	public void setSalemanCode(String salemanCode) {
		this.salemanCode = salemanCode;
	}

	public String getSalemanName() {
		return salemanName;
	}

	public void setSalemanName(String salemanName) {
		this.salemanName = salemanName;
	}

	public String getErpCode() {
		return erpCode;
	}

	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "DeptGoodsInfoVo [goodsNum=" + goodsNum + ", filterFlag=" + filterFlag + ", id=" + id + ", hosId="
				+ hosId + ", hosName=" + hosName + ", deptId=" + deptId + ", deptName=" + deptName + ", goodsId="
				+ goodsId + ", goodsName=" + goodsName + ", goodsGg=" + goodsGg + ", unit=" + unit + ", mfrsId="
				+ mfrsId + ", mfrsName=" + mfrsName + ", made=" + made + ", stocUpper=" + stocUpper + ", stocLower="
				+ stocLower + ", isPacket=" + isPacket + ", isUnique=" + isUnique + ", packetCode=" + packetCode
				+ ", isMix=" + isMix + ", purMode=" + purMode + ", stocId=" + stocId + ", stocName=" + stocName
				+ ", version=" + version + ", reqNum=" + reqNum + ", brand=" + brand + ", salemanId=" + salemanId
				+ ", salemanName=" + salemanName + ", salemanCode=" + salemanCode + ", erpCode=" + erpCode + "]";
	}

	




}