package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 企业信息获取(YY164)
 * Created by moonb on 2017/11/22.
 */
@XmlType(propOrder = { "qybm", "qymc","qybmcxtj"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
public class YY164_REQ_MAIN {
	
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	
	/**
	 * 企业名称
	 */
	@XmlElement(name = "QYMC")
	private String qymc;
	
	/**
	 * 企业编码查询条件
	 */
	@XmlElement(name = "QYBMCXTJ")
	private String qybmcxtj = "";


	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getQymc() {
		return qymc;
	}

	public void setQymc(String qymc) {
		this.qymc = qymc;
	}

	public String getQybmcxtj() {
		return qybmcxtj;
	}

	public void setQybmcxtj(String qybmcxtj) {
		this.qybmcxtj = qybmcxtj;
	}


}
