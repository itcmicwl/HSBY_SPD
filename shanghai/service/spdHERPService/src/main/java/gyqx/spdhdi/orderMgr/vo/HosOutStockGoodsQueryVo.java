package gyqx.spdhdi.orderMgr.vo;

import java.math.BigDecimal;
import java.util.Date;

public class HosOutStockGoodsQueryVo {

	/**
	 * 单据日期
	 */
	private Date billDate;
	
	/**
	 * 单据类型
	 */
	private String billType ;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品Id
	 */
	private String goodsId;
	
	/**
	 * 商品规格
	 */
	private String goodsGg;
	
	/**
	 * 计量单位
	 */
	private String unit;
	
	/**
	 * 出库部门Id
	 */
	private String outDeptId;
	
	/**
	 * 出库部门
	 */
	private String outDeptName;
	
	/**
	 * 入库部门Id
	 */
	private String inDeptId;
	
	/**
	 * 入库部门
	 */
	private String inDeptName;
	
	/**
	 * 出库单价
	 */
	private BigDecimal price;
	
	/**
	 * 出库数量
	 */
	private String outQuantity;
	
	/**
	 * 出库金额
	 */
	private BigDecimal outAmount;

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
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

	public String getOutDeptId() {
		return outDeptId;
	}

	public void setOutDeptId(String outDeptId) {
		this.outDeptId = outDeptId;
	}

	public String getOutDeptName() {
		return outDeptName;
	}

	public void setOutDeptName(String outDeptName) {
		this.outDeptName = outDeptName;
	}

	public String getInDeptId() {
		return inDeptId;
	}

	public void setInDeptId(String inDeptId) {
		this.inDeptId = inDeptId;
	}

	public String getInDeptName() {
		return inDeptName;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getOutQuantity() {
		return outQuantity;
	}

	public void setOutQuantity(String outQuantity) {
		this.outQuantity = outQuantity;
	}

	public BigDecimal getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(BigDecimal outAmount) {
		this.outAmount = outAmount;
	}

	@Override
	public String toString() {
		return "HosOutStockGoodsQueryVo [billDate=" + billDate + ", billType=" + billType + ", goodsName=" + goodsName
				+ ", goodsId=" + goodsId + ", goodsGg=" + goodsGg + ", unit=" + unit + ", outDeptId=" + outDeptId
				+ ", outDeptName=" + outDeptName + ", inDeptId=" + inDeptId + ", inDeptName=" + inDeptName + ", price="
				+ price + ", outQuantity=" + outQuantity + ", outAmount=" + outAmount + "]";
	}
	
	
}
