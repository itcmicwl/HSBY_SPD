package gyqx.spdherp.reportAnalysis.vo;

import java.math.BigDecimal;

public class HosMonthHighValueConsumeVo {
	
	/**
	 * 数据id
	 */
	private String Id;
	
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
	
	/**
	 * 低值或高值，1:高值，0:低值
	 */
	private String goodsType;
	
	/**
	 * 医院id
	 */
	private String hosId;
	
	/**
	 * 上一月份
	 */
	private String prevMonth;
	
	/**
	 * 物资分类id
	 */
	private String kindId;


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

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getPrevMonth() {
		return prevMonth;
	}

	public void setPrevMonth(String prevMonth) {
		this.prevMonth = prevMonth;
	}

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
}
