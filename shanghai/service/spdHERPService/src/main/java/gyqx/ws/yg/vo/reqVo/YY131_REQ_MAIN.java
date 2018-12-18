package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材配送验收确认 YY131
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "psyslx", "jls" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY131_REQ_MAIN {

	/**
	 * 配送验收类型
	 */
	@XmlElement(name = "PSYSLX")
	private String psyslx;
	/**
	 * 记录数
	 */
	@XmlElement(name = "JLS")
	private Integer jls;

	public Integer getJls() {
		return jls;
	}

	public void setJls(Integer jls) {
		this.jls = jls;
	}

	public String getPsyslx() {
		return psyslx;
	}

	public void setPsyslx(String psyslx) {
		this.psyslx = psyslx;
	}

}
