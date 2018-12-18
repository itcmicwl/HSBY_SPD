package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材发票状态获取 YY160
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "fpbh", "fpdm", "fph", "fpzt" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY160_REP_DETAIL {

	/**
	 * 发票编号
	 */
	@XmlElement(name = "FPBH")
	private String fpbh;
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
	 * 发票状态
	 */
	@XmlElement(name = "FPZT")
	private String fpzt;

	public String getFpbh() {
		return fpbh;
	}

	public void setFpbh(String fpbh) {
		this.fpbh = fpbh;
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

	public String getFpzt() {
		return fpzt;
	}

	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}

}