package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材按配送明细获取 YY153
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "qybm", "psmxbhcxtj" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY153_REQ_MAIN {
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	/**
	 * 配送明细单号查询条件
	 */
	@XmlElement(name = "PSMXBHCXTJ")
	private String psmxbhcxtj;

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getPsmxbhcxtj() {
		return psmxbhcxtj;
	}

	public void setPsmxbhcxtj(String psmxbhcxtj) {
		this.psmxbhcxtj = psmxbhcxtj;
	}

}
