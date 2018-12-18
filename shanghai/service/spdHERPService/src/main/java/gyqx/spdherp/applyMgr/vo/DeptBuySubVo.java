package gyqx.spdherp.applyMgr.vo;

import gyqx.spdherp.po.DeptBuySub;

import java.util.List;

public class DeptBuySubVo extends DeptBuySub{
	
	private String brand; //品牌
	private String fieldCode2;//件包
	private String storeQty; //库存数量
	private String code  ; //hos_goods_info 商品编码  外网用于对码
	private String erpCode;
	private java.math.BigDecimal price; //hos_goods_info 中的单价
	private java.math.BigDecimal je ;//请购数* price 
	private String goodsName;//hos_goods_info 
	private String unit;//hos_goods_info 
	private String  goodsGg;
	private String  packeage;
	private String  mfrsId;
	private String mfrsName;
	private String provId;
	private String[] provIdArr;
	private String provName;
	private int buyKind = 10;
	//商品erpCode
	private String goodsErpCode;
	
	private Integer isPacket;
	private Integer packetCode;
	private Integer reqNum;

	//增加请购目标科室
	private String sourceWarehouseId;
	
	private String applyDeptName; //请购科室名
	private String kindName;//品种
	private String salemanId;
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getApplyDeptName() {
		return applyDeptName;
	}
	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}
	public String getSourceWarehouseId() {
		return sourceWarehouseId;
	}
	public void setSourceWarehouseId(String sourceWarehouseId) {
		this.sourceWarehouseId = sourceWarehouseId;
	}
	public Integer getReqNum() {
		return reqNum;
	}
	public void setReqNum(Integer reqNum) {
		this.reqNum = reqNum;
	}

	private java.math.BigDecimal taxRate ; // 税率 v_pro_hos_goodsinfo
	private java.math.BigDecimal unitRate ; //单位比率v_pro_hos_goodsinfo
	//查询条件 开始日期，结束日期
	private java.util.Date sDate ;
	
	private java.util.Date eDate ;
	
	//库房
	private String stockId;
	private List<String> lstMain;
	private String surName;
	public Integer getPacketCode() {
		return packetCode;
	}
	public void setPacketCode(Integer packetCode) {
		this.packetCode = packetCode;
	}
	public Integer getIsPacket() {
		return isPacket;
	}
	public void setIsPacket(Integer isPacket) {
		this.isPacket = isPacket;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public java.util.Date getsDate() {
		return sDate;
	}
	public void setsDate(java.util.Date sDate) {
		this.sDate = sDate;
	}
	public java.util.Date geteDate() {
		return eDate;
	}
	public void seteDate(java.util.Date eDate) {
		this.eDate = eDate;
	}
	public String getStoreQty() {
		return storeQty;
	}
	public void setStoreQty(String storeQty) {
		this.storeQty = storeQty;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public java.math.BigDecimal getPrice() {
		return price;
	}
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}
	public java.math.BigDecimal getJe() {
		return je;
	}
	public void setJe(java.math.BigDecimal je) {
		this.je = je;
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
	public String getPackeage() {
		return packeage;
	}
	public void setPackeage(String packeage) {
		this.packeage = packeage;
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
	public java.math.BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(java.math.BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	public java.math.BigDecimal getUnitRate() {
		return unitRate;
	}
	public void setUnitRate(java.math.BigDecimal unitRate) {
		this.unitRate = unitRate;
	}

	public int getBuyKind() {
		return buyKind;
	}

	public void setBuyKind(int buyKind) {
		this.buyKind = buyKind;
	}

	public String[] getProvIdArr() {
		return provIdArr;
	}

	public void setProvIdArr(String[] provIdArr) {
		this.provIdArr = provIdArr;
	}
	public String getSalemanId() {
		return salemanId;
	}
	public void setSalemanId(String salemanId) {
		this.salemanId = salemanId;
	}

	public List<String> getLstMain() {
		return lstMain;
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getFieldCode2() {
		return fieldCode2;
	}
	public void setFieldCode2(String fieldCode2) {
		this.fieldCode2 = fieldCode2;
	}
	public void setLstMain(List<String> lstMain) {
		this.lstMain = lstMain;
	}

	public String getGoodsErpCode() {
		return goodsErpCode;
	}

	public void setGoodsErpCode(String goodsErpCode) {
		this.goodsErpCode = goodsErpCode;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getErpCode() {
		return erpCode;
	}

	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}
}
