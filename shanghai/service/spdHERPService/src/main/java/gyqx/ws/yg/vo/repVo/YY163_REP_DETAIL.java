package gyqx.ws.yg.vo.repVo;

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
@XmlType(propOrder = { "dpudibh", "dpuditm" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY163_REP_DETAIL {

	/**
	 * 单品UDI编号
	 */
	@XmlElement(name = "DPUDIBH")
	private String dpudibh;

	/**
	 * 单品UDI条码
	 */
	@XmlElement(name = "DPUDITM")
	private String dpuditm;

	public String getDpudibh() {
		return dpudibh;
	}

	public void setDpudibh(String dpudibh) {
		this.dpudibh = dpudibh;
	}

	public String getDpuditm() {
		return dpuditm;
	}

	public void setDpuditm(String dpuditm) {
		this.dpuditm = dpuditm;
	}

}
