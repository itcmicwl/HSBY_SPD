package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.*;

/**
 * 耗材发票信息获取 YY157
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "sfwj", "dczhfpbh", "jls" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY157_REP_MAIN {

	/**
	 * 是否完结
	 */
	@XmlElement(name = "SFWJ")
	private String sfwj;
	/**
	 * 当次最后发票明细号
	 */
	@XmlElement(name = "DCZHFPBH")
	private String dczhfpbh;
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

	public String getDczhfpbh() {
		return dczhfpbh;
	}

	public void setDczhfpbh(String dczhfpbh) {
		this.dczhfpbh = dczhfpbh;
	}

	public Integer getJls() {
		return jls;
	}

	public void setJls(Integer jls) {
		this.jls = jls;
	}

}