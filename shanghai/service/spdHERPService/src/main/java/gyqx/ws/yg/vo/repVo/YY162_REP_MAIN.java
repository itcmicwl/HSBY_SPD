package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材按退货单获取退货明细状态 YY162
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "sfwj", "dczhthmxbh", "jls" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY162_REP_MAIN {

	/**
	 * 是否完结
	 */
	@XmlElement(name = "SFWJ")
	private String sfwj;

	/**
	 * 当次最后退货明细编号
	 */
	@XmlElement(name = "DCZHTHMXBH")
	private String dczhthmxbh;

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

	public String getDczhthmxbh() {
		return dczhthmxbh;
	}

	public void setDczhthmxbh(String dczhthmxbh) {
		this.dczhthmxbh = dczhthmxbh;
	}

	public Integer getJls() {
		return jls;
	}

	public void setJls(Integer jls) {
		this.jls = jls;
	}

}
