package gyqx.ws.yg.vo.reqVo;

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
@XmlType(propOrder = { "qsrq", "jzrq", "qybm", "ddlx", "cglx", "djtxf", "ddmxbh", "ddmxbhcxtj", })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY151_REQ_MAIN {

	/**
	 * 起始日期
	 */
	@XmlElement(name = "QSRQ")
	private String qsrq;
	/**
	 * 截止日期
	 */
	@XmlElement(name = "JZRQ")
	private String jzrq;
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	/**
	 * 采购单类型
	 */
	@XmlElement(name = "DDLX")
	private String ddlx;
	/**
	 * 采购类型
	 */
	@XmlElement(name = "CGLX")
	private String cglx;
	/**
	 * 单据填写方
	 */
	@XmlElement(name = "DJTXF")
	private String djtxf;
	/**
	 * 采购明细单号
	 */
	@XmlElement(name = "DDMXBH")
	private String ddmxbh;
	/**
	 * 采购明细单号查询条件
	 */
	@XmlElement(name = "DDMXBHCXTJ")
	private String ddmxbhcxtj;

	public String getQsrq() {
		return qsrq;
	}

	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}

	public String getJzrq() {
		return jzrq;
	}

	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getDdlx() {
		return ddlx;
	}

	public void setDdlx(String ddlx) {
		this.ddlx = ddlx;
	}

	public String getCglx() {
		return cglx;
	}

	public void setCglx(String cglx) {
		this.cglx = cglx;
	}

	public String getDjtxf() {
		return djtxf;
	}

	public void setDjtxf(String djtxf) {
		this.djtxf = djtxf;
	}

	public String getDdmxbh() {
		return ddmxbh;
	}

	public void setDdmxbh(String ddmxbh) {
		this.ddmxbh = ddmxbh;
	}

	public String getDdmxbhcxtj() {
		return ddmxbhcxtj;
	}

	public void setDdmxbhcxtj(String ddmxbhcxtj) {
		this.ddmxbhcxtj = ddmxbhcxtj;
	}

}
