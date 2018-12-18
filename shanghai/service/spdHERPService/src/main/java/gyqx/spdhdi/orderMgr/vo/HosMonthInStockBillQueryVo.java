package gyqx.spdhdi.orderMgr.vo;

import java.math.BigDecimal;

public class HosMonthInStockBillQueryVo {

	/**
	 * 入库单号
	 */
	private String instocBillId ;
	
	/**
	 * 会计期，如:201710，201712
	 */
	private String accountingPeriod;
	/**
	 * 入库类型
	 */
	private String instocType;
	
	/**
	 * 审核人员Id
	 */
	private String auditUser;
	
	/**
	 * 审核人员
	 */
	private String auditUserName;
	
	/**
	 * 入库部门Id
	 */
	private String instocDeptId;
	
	/**
	 * 入库部门
	 */
	private String instocDeptName;
	
	/**
	 * 供应商
	 */
	private String  provider;
	
	/**
	 * 入库金额
	 */
	private BigDecimal amount ;
	
	/**
	 * 付款方式
	 */
	private String payType;
	
	/**
	 * 物资分类
	 */
	private String goodsClassification;
	
	/**
	 * 低值或高值耗材，根据hos_goods_info 字段pur_mode 采购模式来确定
	 */
	private String highOrLower;

	public String getInstocBillId() {
		return instocBillId;
	}

	public void setInstocBillId(String instocBillId) {
		this.instocBillId = instocBillId;
	}

	public String getAccountingPeriod() {
		return accountingPeriod;
	}

	public void setAccountingPeriod(String accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}

	public String getInstocType() {
		return instocType;
	}

	public void setInstocType(String instocType) {
		this.instocType = instocType;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getInstocDeptId() {
		return instocDeptId;
	}

	public void setInstocDeptId(String instocDeptId) {
		this.instocDeptId = instocDeptId;
	}

	public String getInstocDeptName() {
		return instocDeptName;
	}

	public void setInstocDeptName(String instocDeptName) {
		this.instocDeptName = instocDeptName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getGoodsClassification() {
		return goodsClassification;
	}

	public void setGoodsClassification(String goodsClassification) {
		this.goodsClassification = goodsClassification;
	}

	public String getHighOrLower() {
		return highOrLower;
	}

	public void setHighOrLower(String highOrLower) {
		this.highOrLower = highOrLower;
	}

	@Override
	public String toString() {
		return "HosMonthInStockBillQueryVo [instocBillId=" + instocBillId + ", accountingPeriod=" + accountingPeriod
				+ ", instocType=" + instocType + ", auditUser=" + auditUser + ", auditUserName=" + auditUserName
				+ ", instocDeptId=" + instocDeptId + ", instocDeptName=" + instocDeptName + ", provider=" + provider
				+ ", amount=" + amount + ", payType=" + payType + ", goodsClassification=" + goodsClassification
				+ ", highOrLower=" + highOrLower + "]";
	}
	
}
