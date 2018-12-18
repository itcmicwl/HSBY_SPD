package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材配送单获取 YY154
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "qsrq", "jzrq", "qybm", "psdh", "psdbhcxtj" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY154_REQ_MAIN {

	/**
	 * 起始日期
	 */
	@XmlElement(name = "QSRQ")
	private String qsrq;
	/**
	 * 截止日期
	 */
	@XmlElement(name = "JZRQ")
	private String jzrq;
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	/**
	 * 配送单号
	 */
	@XmlElement(name = "PSDH")
	private String psdh;
	/**
	 * 配送单编号查询条件
	 */
	@XmlElement(name = "PSDBHCXTJ")
	private String psdbhcxtj;

	public String getQsrq() {
		return qsrq;
	}

	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}

	public String getJzrq() {
		return jzrq;
	}

	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getPsdh() {
		return psdh;
	}

	public void setPsdh(String psdh) {
		this.psdh = psdh;
	}

	public String getPsdbhcxtj() {
		return psdbhcxtj;
	}

	public void setPsdbhcxtj(String psdbhcxtj) {
		this.psdbhcxtj = psdbhcxtj;
	}

}
