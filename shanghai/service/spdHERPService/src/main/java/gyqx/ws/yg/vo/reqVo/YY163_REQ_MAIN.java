package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材单品UDI获取 YY163
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "psmxbh", "dpudibhcxtj" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY163_REQ_MAIN {

	/**
	 * 配送明细单号
	 */
	@XmlElement(name = "PSMXBH")
	private String psmxbh;

	/**
	 * 单品UDI编号查询条件
	 */
	@XmlElement(name = "DPUDIBHCXTJ")
	private String dpudibhcxtj;

	public YY163_REQ_MAIN() {
	}

	public YY163_REQ_MAIN(String psmxbh) {
		super();
		this.psmxbh = psmxbh;
	}

	public YY163_REQ_MAIN(String psmxbh, String dpudibhcxtj) {
		super();
		this.psmxbh = psmxbh;
		this.dpudibhcxtj = dpudibhcxtj;
	}

	public String getPsmxbh() {
		return psmxbh;
	}

	public void setPsmxbh(String psmxbh) {
		this.psmxbh = psmxbh;
	}

	public String getDpudibhcxtj() {
		return dpudibhcxtj;
	}

	public void setDpudibhcxtj(String dpudibhcxtj) {
		this.dpudibhcxtj = dpudibhcxtj;
	}
}
