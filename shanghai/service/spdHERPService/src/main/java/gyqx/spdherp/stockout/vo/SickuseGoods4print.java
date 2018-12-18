package gyqx.spdherp.stockout.vo;

import java.util.Date;

public class SickuseGoods4print {
	private String id;
	private String jyxkCode;       
	private String provName;
	private String goodsName;     
	private String goodsGg;       
	private String unit;          
	private java.math.BigDecimal useQty;         
	private Date expdtEndDate;   
	private String batchCode;
	private String certificateCode;
	public String getJyxkCode() {
		return jyxkCode;
	}
	public void setJyxkCode(String jyxkCode) {
		this.jyxkCode = jyxkCode;
	}
	public String getProvName() {
		return provName;
	}
	public void setProvName(String provName) {
		this.provName = provName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public java.math.BigDecimal getUseQty() {
		return useQty;
	}
	public void setUseQty(java.math.BigDecimal useQty) {
		this.useQty = useQty;
	}
	public Date getExpdtEndDate() {
		return expdtEndDate;
	}
	public void setExpdtEndDate(Date expdtEndDate) {
		this.expdtEndDate = expdtEndDate;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public String getCertificateCode() {
		return certificateCode;
	}
	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}
}
