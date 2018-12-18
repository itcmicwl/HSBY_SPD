package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材采购明细获取 YY151
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "sfwj", "dczhddmxdh", "jls" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY151_REP_MAIN {
	/**
	 * 是否完结
	 */
	@XmlElement(name = "SFWJ")
	private String sfwj;
	/**
	 * 当次最后退货明细单号
	 */
	@XmlElement(name = "DCZHDDMXBH")
	private String dczhddmxdh;
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

	public String getDczhddmxdh() {
		return dczhddmxdh;
	}

	public void setDczhddmxdh(String dczhddmxdh) {
		this.dczhddmxdh = dczhddmxdh;
	}

	public Integer getJls() {
		return jls;
	}

	public void setJls(Integer jls) {
		this.jls = jls;
	}

	@Override
	public String toString() {
		return "YY151_REP_MAIN [sfwj=" + sfwj + ", dczhddmxdh=" + dczhddmxdh + ", jls=" + jls + "]";
	}

}
