package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材按退货单获取退货明细状态 YY162
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "thdbh", "thmxbhcxtj" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY162_REQ_MAIN {

	/**
	 * 退货单编号
	 */
	@XmlElement(name = "THDBH")
	private String thdbh;

	/**
	 * 退货明细编号查询条件
	 */
	@XmlElement(name = "THMXBHCXTJ")
	private String thmxbhcxtj;

	public String getThdbh() {
		return thdbh;
	}

	public void setThdbh(String thdbh) {
		this.thdbh = thdbh;
	}

	public String getThmxbhcxtj() {
		return thmxbhcxtj;
	}

	public void setThmxbhcxtj(String thmxbhcxtj) {
		this.thmxbhcxtj = thmxbhcxtj;
	}

}
