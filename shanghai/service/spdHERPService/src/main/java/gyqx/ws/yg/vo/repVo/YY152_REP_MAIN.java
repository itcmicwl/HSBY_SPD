package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材退货明细获取 YY152
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "sfwj", "dczhthmxdh", "jls" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY152_REP_MAIN {
	/**
	 * 是否完结
	 */
	@XmlElement(name = "SFWJ")
	private String sfwj;
	/**
	 * 当次最后退货明细单号
	 */
	@XmlElement(name = "DCZHTHMXBH")
	private String dczhthmxdh;
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

	public String getDczhthmxdh() {
		return dczhthmxdh;
	}

	public void setDczhthmxdh(String dczhthmxdh) {
		this.dczhthmxdh = dczhthmxdh;
	}

	public Integer getJls() {
		return jls;
	}

	public void setJls(Integer jls) {
		this.jls = jls;
	}

	@Override
	public String toString() {
		return "YY152_REP_MAIN [sfwj=" + sfwj + ", dczhthmxdh=" + dczhthmxdh + ", jls=" + jls + "]";
	}

}
