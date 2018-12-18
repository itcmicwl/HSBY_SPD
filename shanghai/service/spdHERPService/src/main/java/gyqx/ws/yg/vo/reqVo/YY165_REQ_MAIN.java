package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 企业资证信息获取 YY165
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "qybm", "zjlx", "zjbhxtj" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY165_REQ_MAIN {

	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;

	/**
	 * 证件类型
	 */
	@XmlElement(name = "ZJLX")
	private String zjlx;

	/**
	 * 证件编号查询条件
	 */
	@XmlElement(name = "ZJBHXTJ")
	private String zjbhxtj;

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getZjbhxtj() {
		return zjbhxtj;
	}

	public void setZjbhxtj(String zjbhxtj) {
		this.zjbhxtj = zjbhxtj;
	}

}
