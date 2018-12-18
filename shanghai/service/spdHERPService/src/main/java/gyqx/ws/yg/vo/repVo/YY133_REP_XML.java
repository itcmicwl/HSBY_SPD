package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import gyqx.ws.yg.vo.RepHead;

/**
 * 耗材发票支付确认 YY133
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "repHead" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "XMLDATA")
public class YY133_REP_XML {
	/**
	 * 消息头
	 */
	@XmlElement(name = "HEAD")
	private RepHead repHead;

	public RepHead getRepHead() {
		return repHead;
	}

	public void setRepHead(RepHead repHead) {
		this.repHead = repHead;
	}
}
