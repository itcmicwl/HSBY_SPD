
package gyqx.spdherp.deptMgr.vo;

import java.math.BigDecimal;

/**
 * 科室商品申请vo
 */
public class DeptGoodsApplyVo {

	/**
	 * ID
	 */
	private String id;

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
	 * 产地
	 */
	private String made;

	/**
	 * 生产厂商
	 */
	private String mfrsName;

	private String mfrsId;

	private String brand;

	/**
	 * 包装数量
	 */
	private BigDecimal packetCode;
	/**
	 * 数据版本
	 */
	private int version;

	/**
	 * 申请原因
	 */
	private String applyReason;

	/**
	 * 审核意见
	 */
	private String auditView;

	/**
	 * 医院id
	 */
	private String hosId;

	private String hosName;

	/**
	 * 科室id
	 */
	private String deptId;

	/**
	 * 审核状态
	 */
	private Integer status;

	/**
	 * 采购模式
	 */
	private String purMode;

	private String miCode;
	/**
	 * 审核人 查询用字段
	 */
	private String auditer;
	private String erpCode;
	private String deptName;
	private String isUnique;
	private String salemanId;

	private String stocUpper;
	private String stocLower;
	private String isPacket;
	private String isMix;
	private String stocId;
	private String reqNum;
	private String salemanCode;

	public String getErpCode() {
		return erpCode;
	}

	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(String isUnique) {
		this.isUnique = isUnique;
	}

	public String getSalemanId() {
		return salemanId;
	}

	public void setSalemanId(String salemanId) {
		this.salemanId = salemanId;
	}

	public String getHosName() {
		return hosName;
	}

	public void setHosName(String hosName) {
		this.hosName = hosName;
	}

	public String getMiCode() {
		return miCode;
	}

	public void setMiCode(String miCode) {
		this.miCode = miCode;
	}

	public String getMfrsId() {
		return mfrsId;
	}

	public void setMfrsId(String mfrsId) {
		this.mfrsId = mfrsId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPurMode() {
		return purMode;
	}

	public void setPurMode(String purMode) {
		this.purMode = purMode;
	}

	public String getStocUpper() {
		return stocUpper;
	}

	public void setStocUpper(String stocUpper) {
		this.stocUpper = stocUpper;
	}

	public String getStocLower() {
		return stocLower;
	}

	public void setStocLower(String stocLower) {
		this.stocLower = stocLower;
	}

	public String getIsPacket() {
		return isPacket;
	}

	public void setIsPacket(String isPacket) {
		this.isPacket = isPacket;
	}

	public String getIsMix() {
		return isMix;
	}

	public void setIsMix(String isMix) {
		this.isMix = isMix;
	}

	public String getStocId() {
		return stocId;
	}

	public void setStocId(String stocId) {
		this.stocId = stocId;
	}

	public String getReqNum() {
		return reqNum;
	}

	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}

	public String getSalemanCode() {
		return salemanCode;
	}

	public void setSalemanCode(String salemanCode) {
		this.salemanCode = salemanCode;
	}

	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}

	public BigDecimal getPacketCode() {
		return packetCode;
	}

	public void setPacketCode(BigDecimal packetCode) {
		this.packetCode = packetCode;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public String getAuditView() {
		return auditView;
	}

	public void setAuditView(String auditView) {
		this.auditView = auditView;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}