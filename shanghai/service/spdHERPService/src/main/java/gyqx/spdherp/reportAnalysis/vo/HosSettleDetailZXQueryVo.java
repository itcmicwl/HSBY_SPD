package gyqx.spdherp.reportAnalysis.vo;

import java.math.BigDecimal;
import java.util.Date;

import gyqx.spdhdi.po.DistrBillList;

public class HosSettleDetailZXQueryVo extends DistrBillList {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311848634928165341L;

	/**
	 * 发票税票号
	 */
	private String invoicesCode;
	private Date invoicesDate;
	private String invoicesBm;
	private String invoicesMan;
	private String invoicesType;
	private BigDecimal invoicesAmount;
	public String getInvoicesCode() {
		return invoicesCode;
	}
	public void setInvoicesCode(String invoicesCode) {
		this.invoicesCode = invoicesCode;
	}
	public Date getInvoicesDate() {
		return invoicesDate;
	}
	public void setInvoicesDate(Date invoicesDate) {
		this.invoicesDate = invoicesDate;
	}
	public String getInvoicesBm() {
		return invoicesBm;
	}
	public void setInvoicesBm(String invoicesBm) {
		this.invoicesBm = invoicesBm;
	}
	public String getInvoicesMan() {
		return invoicesMan;
	}
	public void setInvoicesMan(String invoicesMan) {
		this.invoicesMan = invoicesMan;
	}
	public String getInvoicesType() {
		return invoicesType;
	}
	public void setInvoicesType(String invoicesType) {
		this.invoicesType = invoicesType;
	}
	public BigDecimal getInvoicesAmount() {
		return invoicesAmount;
	}
	public void setInvoicesAmount(BigDecimal invoicesAmount) {
		this.invoicesAmount = invoicesAmount;
	}
	
	

}
