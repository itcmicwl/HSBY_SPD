package gyqx.spdherp.deptMgr.vo;

import gyqx.spdherp.po.HosOperatorStock;

/**
 * 库房人员维护Vo
 * 入库时依据操作人员获取可入库的库房
 * @author ganwei
 *
 */
public class UserStockVo extends HosOperatorStock{
	//库房基本信息表属性
  private String stockName ;
  private String stockFZR;
  private String stockPhone;
  private String stockLevel;
  private String shortPinyin;
  private String stockCode;
  private String stockAddress;
public String getStockAddress() {
	return stockAddress;
}
public void setStockAddress(String stockAddress) {
	this.stockAddress = stockAddress;
}
public String getStockName() {
	return stockName;
}
public void setStockName(String stockName) {
	this.stockName = stockName;
}
public String getStockFZR() {
	return stockFZR;
}
public void setStockFZR(String stockFZR) {
	this.stockFZR = stockFZR;
}
public String getStockPhone() {
	return stockPhone;
}
public void setStockPhone(String stockPhone) {
	this.stockPhone = stockPhone;
}
public String getStockLevel() {
	return stockLevel;
}
public void setStockLevel(String stockLevel) {
	this.stockLevel = stockLevel;
}
public String getShortPinyin() {
	return shortPinyin;
}
public void setShortPinyin(String shortPinyin) {
	this.shortPinyin = shortPinyin;
}
public String getStockCode() {
	return stockCode;
}
public void setStockCode(String stockCode) {
	this.stockCode = stockCode;
}
  
}
