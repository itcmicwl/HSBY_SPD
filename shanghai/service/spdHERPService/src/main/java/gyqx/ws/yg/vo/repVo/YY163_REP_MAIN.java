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
@XmlType(propOrder = { "sfwj", "dczhdpudibh", "jls" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY163_REP_MAIN {

	/**
	 * 是否完结
	 */
	@XmlElement(name = "SFWJ")
	private String sfwj;

	/**
	 * 当次最后单品UDI编号
	 */
	@XmlElement(name = "DCZHDPUDIBH")
	private String dczhdpudibh;

	/**
	 * 记录数
	 */
	@XmlElement(name = "JLS")
	private Integer jls;

	public String getSfwj() {
		return sfwj;
	}

	public void setSfwj(String sfwj) {
		this.sfwj = sfwj;
	}

	public String getDczhdpudibh() {
		return dczhdpudibh;
	}

	public void setDczhdpudibh(String dczhdpudibh) {
		this.dczhdpudibh = dczhdpudibh;
	}

	public Integer getJls() {
		return jls;
	}

	public void setJls(Integer jls) {
		this.jls = jls;
	}

}
