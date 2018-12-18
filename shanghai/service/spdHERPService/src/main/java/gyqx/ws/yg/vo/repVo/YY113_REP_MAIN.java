package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材退货单填报 YY113
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "thdbh" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY113_REP_MAIN {

	/**
	 * 退货单编号
	 */
	@XmlElement(name = "THDBH")
	private String thdbh;

	public String getThdbh() {
		return thdbh;
	}

	public void setThdbh(String thdbh) {
		this.thdbh = thdbh;
	}

}
