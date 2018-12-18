package gyqx.spdherp.reportAnalysis.vo;

import java.math.BigDecimal;
import java.util.Date;

import gyqx.spdhdi.orderMgr.vo.DistrBillVo;
import gyqx.spdherp.settleCenter.vo.HosOutBalanceVo;

public class HosSettleDXQueryVo extends HosOutBalanceVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4396789693148891354L;

	private int settleBillCount;
	/**
	 * 已经结算数目
	 */
	private int settleCount;
	/**
	 * 未结算数目
	 */
	private int noSettleCount;
	/**
	 * 每条结算单明细总数
	 */
	private int settleBillDetailCount;
	
	public int getSettleBillDetailCount() {
		return settleBillDetailCount;
	}

	public void setSettleBillDetailCount(int settleBillDetailCount) {
		this.settleBillDetailCount = settleBillDetailCount;
	}

	public int getSettleCount() {
		return settleCount;
	}

	public void setSettleCount(int settleCount) {
		this.settleCount = settleCount;
	}

	/**
	 * 查询开始日期
	 */
	private Date sDate;
	
	/**
	 * 查询结束日期
	 */
	private Date eDate;
	
	/**
	 * 发票号
	 */
	private String invoiceCodes;
	
	/**
	 * 发票金额
	 */
	private BigDecimal invoiceAmount;

	public int getNoSettleCount() {
		return noSettleCount;
	}

	public void setNoSettleCount(int noSettleCount) {
		this.noSettleCount = noSettleCount;
	}

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public String getInvoiceCodes() {
		return invoiceCodes;
	}

	public void setInvoiceCodes(String invoiceCodes) {
		this.invoiceCodes = invoiceCodes;
	}

	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public int getSettleBillCount() {
		return settleBillCount;
	}

	public void setSettleBillCount(int settleBillCount) {
		this.settleBillCount = settleBillCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
