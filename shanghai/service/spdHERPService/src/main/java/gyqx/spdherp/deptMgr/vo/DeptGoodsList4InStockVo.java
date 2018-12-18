package gyqx.spdherp.deptMgr.vo;

import java.io.Serializable;

import gyqx.spdherp.po.DeptGoodsInfo;

public class DeptGoodsList4InStockVo extends DeptGoodsInfo implements Serializable{
	
	private String[] arrGoodsIDs;

	public String[] getArrGoodsIDs() {
		return arrGoodsIDs;
	}
	public void setArrGoodsIDs(String[] arrGoodsIDs) {
		this.arrGoodsIDs = arrGoodsIDs;
	}
	private String provName;  // 供应商	
	private String provId;   //供应商
	private String price;  // 医院产品信息 价格
	private String packeage;  //医院产品信息 包
	private String pinyin;  //医院产品信息
	private String code;  //医院产品编码

	private String stCondition;  //储运条件
	
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getProvName() {
		return provName;
	}
	public void setProvName(String provName) {
		this.provName = provName;
	}
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPackeage() {
		return packeage;
	}
	public void setPackeage(String packeage) {
		this.packeage = packeage;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStCondition() {
		return stCondition;
	}
	public void setStCondition(String stCondition) {
		this.stCondition = stCondition;
	}

}
