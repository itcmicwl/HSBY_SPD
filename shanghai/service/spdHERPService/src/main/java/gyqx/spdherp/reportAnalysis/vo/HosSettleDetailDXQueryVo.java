package gyqx.spdherp.reportAnalysis.vo;

import java.math.BigDecimal;
import java.util.Date;

import gyqx.spdhdi.po.DistrBillList;
import gyqx.spdherp.settleCenter.vo.HosOutBalanceDetailVo;

public class HosSettleDetailDXQueryVo extends HosOutBalanceDetailVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311848634928165341L;


	private Date invoiceDate;
	private String invoiceMan;
	private String invoiceType;
	private String provGoodsErpCode;
	
	public String getProvGoodsErpCode() {
		return provGoodsErpCode;
	}
	public void setProvGoodsErpCode(String provGoodsErpCode) {
		this.provGoodsErpCode = provGoodsErpCode;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getInvoiceMan() {
		return invoiceMan;
	}
	public void setInvoiceMan(String invoiceMan) {
		this.invoiceMan = invoiceMan;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	
	

}
