package gyqx.spdherp.reportAnalysis.vo;

import java.math.BigDecimal;

public class HosSettleMsgQueryVo {
	
	private int settleBillCount;
	/**
	 * 已经结算数目
	 */
	private int settleCount;
	/**
	 * 未结算数目
	 */
	private int noSettleCount;
	
	private BigDecimal invoicesAmount;

	public int getSettleBillCount() {
		return settleBillCount;
	}

	public void setSettleBillCount(int settleBillCount) {
		this.settleBillCount = settleBillCount;
	}

	public int getSettleCount() {
		return settleCount;
	}

	public void setSettleCount(int settleCount) {
		this.settleCount = settleCount;
	}

	public int getNoSettleCount() {
		return noSettleCount;
	}

	public void setNoSettleCount(int noSettleCount) {
		this.noSettleCount = noSettleCount;
	}

	public BigDecimal getInvoicesAmount() {
		return invoicesAmount;
	}

	public void setInvoicesAmount(BigDecimal invoicesAmount) {
		this.invoicesAmount = invoicesAmount;
	}
	
	
}
