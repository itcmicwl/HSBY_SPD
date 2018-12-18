package gyqx.spdhdi.orderMgr.vo;

import java.math.BigDecimal;

public class HosMonthLowValueConsumeVo {
	/**
	 * 科室id
	 */
	private String deptId;
	
	/**
	 * 科室名称
	 */
	private String deptName;
	
	/***
	 * 自筹金费
	 */
	private BigDecimal ownExpense;
	
	/**
	 * 财政拨款
	 */
	private BigDecimal financialAppropriation;
	
	/**
	 * 捐赠
	 */
	private BigDecimal donation;
	
	/**
	 * 科研金费
	 */
	private BigDecimal researchFee;
	
	/**
	 * 总计
	 */
	private BigDecimal total;
	
	/**
	 * 汇总月份
	 */
	private String fillMonth;

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

	public BigDecimal getOwnExpense() {
		return ownExpense;
	}

	public void setOwnExpense(BigDecimal ownExpense) {
		this.ownExpense = ownExpense;
	}

	public BigDecimal getFinancialAppropriation() {
		return financialAppropriation;
	}

	public void setFinancialAppropriation(BigDecimal financialAppropriation) {
		this.financialAppropriation = financialAppropriation;
	}

	public BigDecimal getDonation() {
		return donation;
	}

	public void setDonation(BigDecimal donation) {
		this.donation = donation;
	}

	public BigDecimal getResearchFee() {
		return researchFee;
	}

	public void setResearchFee(BigDecimal researchFee) {
		this.researchFee = researchFee;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getFillMonth() {
		return fillMonth;
	}

	public void setFillMonth(String fillMonth) {
		this.fillMonth = fillMonth;
	}
}
