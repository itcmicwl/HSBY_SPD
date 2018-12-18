package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材配送明细获取 YY155
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "psdbh", "psmxbhcxtj" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY155_REQ_MAIN {

	/**
	 * 配送单编号
	 */
	@XmlElement(name = "PSDBH")
	private String psdbh;
	/**
	 * 配送明细单号查询条件
	 */
	@XmlElement(name = "PSMXBHCXTJ")
	private String psmxbhcxtj;

	public String getPsdbh() {
		return psdbh;
	}

	public void setPsdbh(String psdbh) {
		this.psdbh = psdbh;
	}

	public String getPsmxbhcxtj() {
		return psmxbhcxtj;
	}

	public void setPsmxbhcxtj(String psmxbhcxtj) {
		this.psmxbhcxtj = psmxbhcxtj;
	}

}
