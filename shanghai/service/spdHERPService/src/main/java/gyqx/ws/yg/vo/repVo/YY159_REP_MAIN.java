package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材按采购单获取采购明细状态(YY159)
 * Created by moonb on 2017/11/22.
 */
@XmlType(propOrder = { "sfwj", "dczhddmxbh","jls"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY159_REP_MAIN {
	
	/**
	 * 是否完结
	 */
	@XmlElement(name = "SFWJ")
	private String sfwj;
	
	/**
	 * 当次最后采购明单号
	 */
	@XmlElement(name = "DCZHDDMXBH")
	private String dczhddmxbh;
	
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

	public String getDczhddmxbh() {
		return dczhddmxbh;
	}

	public void setDczhddmxbh(String dczhddmxbh) {
		this.dczhddmxbh = dczhddmxbh;
	}

	public Integer getJls() {
		return jls;
	}

	public void setJls(Integer jls) {
		this.jls = jls;
	}
}
