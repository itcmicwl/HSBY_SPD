package gyqx.ws.yg.vo.reqVo;

import java.math.BigDecimal;

import javax.xml.bind.annotation.*;

/**
 * 耗材发票支付确认 YY133
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "fpid", "fpdm", "fph", "qybm", "fpysjg", "fphszje" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY133_REQ_MAIN {

	/**
	 * 发票ID
	 */
	@XmlElement(name = "FPID")
	private String fpid;
	/**
	 * 发票代码
	 */
	@XmlElement(name = "FPDM")
	private String fpdm;
	/**
	 * 发票号
	 */
	@XmlElement(name = "FPH")
	private String fph;
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	/**
	 * 发票含税总金额
	 */
	@XmlElement(name = "FPHSZJE")
	private BigDecimal fphszje;

	public String getFpid() {
		return fpid;
	}

	public void setFpid(String fpid) {
		this.fpid = fpid;
	}

	public String getFpdm() {
		return fpdm;
	}

	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	public String getFph() {
		return fph;
	}

	public void setFph(String fph) {
		this.fph = fph;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public BigDecimal getFphszje() {
		return fphszje;
	}

	public void setFphszje(BigDecimal fphszje) {
		this.fphszje = fphszje;
	}

}