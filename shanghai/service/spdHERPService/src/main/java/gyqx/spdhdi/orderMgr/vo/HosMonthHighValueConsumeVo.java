package gyqx.spdhdi.orderMgr.vo;

import java.math.BigDecimal;

public class HosMonthHighValueConsumeVo {
	/**
	 * 汇总月份
	 */
	private String month;
	
	/**
	 * 期初金额
	 */
	private BigDecimal initialAmount; 
	
	/**
	 * 物资分类
	 */
	private String goodsClassification;
	
	/**
	 * 入库金额
	 */
	private BigDecimal inStocAmount;
	
	/**
	 * 出库金额
	 */
	private BigDecimal outStocAmount;
	
	/**
	 * 结存金额
	 */
	private BigDecimal inventoryAmount;

	@Override
	public String toString() {
		return "HosMonthHighValueConsume [month=" + month + ", initialAmount=" + initialAmount
				+ ", goodsClassification=" + goodsClassification + ", inStocAmount=" + inStocAmount + ", outStocAmount="
				+ outStocAmount + ", inventoryAmount=" + inventoryAmount + "]";
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(BigDecimal initialAmount) {
		this.initialAmount = initialAmount;
	}

	public String getGoodsClassification() {
		return goodsClassification;
	}

	public void setGoodsClassification(String goodsClassification) {
		this.goodsClassification = goodsClassification;
	}

	public BigDecimal getInStocAmount() {
		return inStocAmount;
	}

	public void setInStocAmount(BigDecimal inStocAmount) {
		this.inStocAmount = inStocAmount;
	}

	public BigDecimal getOutStocAmount() {
		return outStocAmount;
	}

	public void setOutStocAmount(BigDecimal outStocAmount) {
		this.outStocAmount = outStocAmount;
	}

	public BigDecimal getInventoryAmount() {
		return inventoryAmount;
	}

	public void setInventoryAmount(BigDecimal inventoryAmount) {
		this.inventoryAmount = inventoryAmount;
	}
	
	
}
