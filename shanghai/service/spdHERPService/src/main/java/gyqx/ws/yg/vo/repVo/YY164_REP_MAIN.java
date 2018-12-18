package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 企业信息获取(YY164)
 * Created by moonb on 2017/11/22.
 */
@XmlType(propOrder = { "sfwj", "dczhqybm","jls"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
public class YY164_REP_MAIN {
	
	/**
	 * 是否完结
	 */
	@XmlElement(name = "SFWJ")
	private String sfwj;
	
	/**
	 * 当次最后企业编码
	 */
	@XmlElement(name = "DCZHQYBM")
	private String dczhqybm;
	
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

	public String getDczhqybm() {
		return dczhqybm;
	}

	public void setDczhqybm(String dczhqybm) {
		this.dczhqybm = dczhqybm;
	}

	public Integer getJls() {
		return jls;
	}

	public void setJls(Integer jls) {
		this.jls = jls;
	}
}
