package gyqx.ws.yg.vo.reqVo;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材退货单填报 YY113
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "sxh", "cglx", "thlx", "hctbdm", "hcxfdm", "yybddm", "cgggxh", "scph", "scrq", "yxrq",
		"psmxtmlx", "psmxtm", "thsl", "thdj", "qybm", "thyy" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY113_REQ_DETAIL {
	/**
	 * 顺序号
	 */
	@XmlElement(name = "SXH")
	private String sxh;
	/**
	 * 采购类型
	 */
	@XmlElement(name = "CGLX")
	private String cglx;
	/**
	 * 退货类型
	 */
	@XmlElement(name = "THLX")
	private String thlx;
	/**
	 * 耗材统编代码
	 */
	@XmlElement(name = "HCTBDM")
	private String hctbdm;
	/**
	 * 耗材细分代码
	 */
	@XmlElement(name = "HCXFDM")
	private String hcxfdm;
	/**
	 * 医院本地代码
	 */
	@XmlElement(name = "YYBDDM")
	private String yybddm;
	/**
	 * 采购规格型号
	 */
	@XmlElement(name = "CGGGXH")
	private String cgggxh;
	/**
	 * 生产批号
	 */
	@XmlElement(name = "SCPH")
	private String scph;
	/**
	 * 生产日期
	 */
	@XmlElement(name = "SCRQ")
	private String scrq;
	/**
	 * 有效日期
	 */
	@XmlElement(name = "YXRQ")
	private String yxrq;
	/**
	 * 配送明细条码类型
	 */
	@XmlElement(name = "PSMXTMLX")
	private String psmxtmlx;
	/**
	 * 配送明细条码
	 */
	@XmlElement(name = "PSMXTM")
	private String psmxtm;
	/**
	 * 退货数量
	 */
	@XmlElement(name = "THSL")
	private BigDecimal thsl;
	/**
	 * 退货单价
	 */
	@XmlElement(name = "THDJ")
	private BigDecimal thdj;
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	/**
	 * 退货原因
	 */
	@XmlElement(name = "THYY")
	private String thyy;

	public String getSxh() {
		return sxh;
	}

	public void setSxh(String sxh) {
		this.sxh = sxh;
	}

	public String getCglx() {
		return cglx;
	}

	public void setCglx(String cglx) {
		this.cglx = cglx;
	}

	public String getThlx() {
		return thlx;
	}

	public void setThlx(String thlx) {
		this.thlx = thlx;
	}

	public String getHctbdm() {
		return hctbdm;
	}

	public void setHctbdm(String hctbdm) {
		this.hctbdm = hctbdm;
	}

	public String getHcxfdm() {
		return hcxfdm;
	}

	public void setHcxfdm(String hcxfdm) {
		this.hcxfdm = hcxfdm;
	}

	public String getYybddm() {
		return yybddm;
	}

	public void setYybddm(String yybddm) {
		this.yybddm = yybddm;
	}

	public String getCgggxh() {
		return cgggxh;
	}

	public void setCgggxh(String cgggxh) {
		this.cgggxh = cgggxh;
	}

	public String getScph() {
		return scph;
	}

	public void setScph(String scph) {
		this.scph = scph;
	}

	public String getScrq() {
		return scrq;
	}

	public void setScrq(String scrq) {
		this.scrq = scrq;
	}

	public String getYxrq() {
		return yxrq;
	}

	public void setYxrq(String yxrq) {
		this.yxrq = yxrq;
	}

	public String getPsmxtmlx() {
		return psmxtmlx;
	}

	public void setPsmxtmlx(String psmxtmlx) {
		this.psmxtmlx = psmxtmlx;
	}

	public String getPsmxtm() {
		return psmxtm;
	}

	public void setPsmxtm(String psmxtm) {
		this.psmxtm = psmxtm;
	}

	public BigDecimal getThsl() {
		return thsl;
	}

	public void setThsl(BigDecimal thsl) {
		this.thsl = thsl;
	}

	public BigDecimal getThdj() {
		return thdj;
	}

	public void setThdj(BigDecimal thdj) {
		this.thdj = thdj;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getThyy() {
		return thyy;
	}

	public void setThyy(String thyy) {
		this.thyy = thyy;
	}

}
