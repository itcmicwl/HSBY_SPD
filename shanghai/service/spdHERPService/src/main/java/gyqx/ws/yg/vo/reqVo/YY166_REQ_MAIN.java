package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 企业授权信息获取 YY166
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "hctbdm", "hcxfdm", "qybm", "sqmxbhcxtj" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY166_REQ_MAIN {

	/**
	 * 耗材统编代码
	 */
	@XmlElement(name = "HCTBDM")
	private String hctbdm;

	/**
	 * 耗材细分代码
	 */
	@XmlElement(name = "HCXFDM")
	private String hcxfdm;

	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;

	/**
	 * 授权明细编号查询条件
	 */
	@XmlElement(name = "SQMXBHCXTJ")
	private String sqmxbhcxtj;

	public String getHctbdm() {
		return hctbdm;
	}

	public void setHctbdm(String hctbdm) {
		this.hctbdm = hctbdm;
	}

	public String getHcxfdm() {
		return hcxfdm;
	}

	public void setHcxfdm(String hcxfdm) {
		this.hcxfdm = hcxfdm;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getSqmxbhcxtj() {
		return sqmxbhcxtj;
	}

	public void setSqmxbhcxtj(String sqmxbhcxtj) {
		this.sqmxbhcxtj = sqmxbhcxtj;
	}
}
