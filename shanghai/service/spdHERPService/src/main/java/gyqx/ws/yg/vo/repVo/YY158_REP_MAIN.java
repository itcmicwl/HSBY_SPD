package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.*;

/**
 * 耗材发票明细获取 YY158
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "sfwj", "dczhfpmxbh", "jls" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY158_REP_MAIN {

	/**
	 * 是否完结
	 */
	@XmlElement(name = "SFWJ")
	private String sfwj;
	/**
	 * 当次最后发票明细号
	 */
	@XmlElement(name = "DCZHFPMXBH")
	private String dczhfpmxbh;
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

	public String getDczhfpmxbh() {
		return dczhfpmxbh;
	}

	public void setDczhfpmxbh(String dczhfpmxbh) {
		this.dczhfpmxbh = dczhfpmxbh;
	}

	public Integer getJls() {
		return jls;
	}

	public void setJls(Integer jls) {
		this.jls = jls;
	}
}