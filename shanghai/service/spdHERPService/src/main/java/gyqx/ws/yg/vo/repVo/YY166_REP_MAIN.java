package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 企业授权信息获取 YY166
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "sfwj", "dczhsqmxbh", "jls" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY166_REP_MAIN {

	/**
	 * 是否完结
	 */
	@XmlElement(name = "SFWJ")
	private String sfwj;

	/**
	 * 当次最后授权明细编号
	 */
	@XmlElement(name = "DCZHSQMXBH")
	private String dczhsqmxbh;

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

	public String getDczhsqmxbh() {
		return dczhsqmxbh;
	}

	public void setDczhsqmxbh(String dczhsqmxbh) {
		this.dczhsqmxbh = dczhsqmxbh;
	}

	public Integer getJls() {
		return jls;
	}

	public void setJls(Integer jls) {
		this.jls = jls;
	}

}
