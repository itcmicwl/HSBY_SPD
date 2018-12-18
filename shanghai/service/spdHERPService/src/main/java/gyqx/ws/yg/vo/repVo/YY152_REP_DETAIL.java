package gyqx.ws.yg.vo.repVo;

import java.math.BigDecimal;

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
@XmlType(propOrder = { "djtxf", "qybm", "psdbm", "thdbh", "thmxbh", "sxh", "cglx", "thlx", "hctbdm", "hcxfdm", "yybddm",
		"pm", "gg", "xh", "ggxhsm", "dw", "scqy", "cgggxh", "scph", "scrq", "yxrq", "psmxtmlx", "psmxtm", "thsl",
		"thdj", "thyy" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY152_REP_DETAIL {

	/**
	 * 单据填写方
	 */
	@XmlElement(name = "DJTXF")
	private String djtxf;
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	/**
	 * 配送点编码
	 */
	@XmlElement(name = "PSDBM")
	private String psdbm;
	/**
	 * 退货单编号
	 */
	@XmlElement(name = "THDBH")
	private String thdbh;
	/**
	 * 退货明细编号
	 */
	@XmlElement(name = "THMXBH")
	private String thmxbh;
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
	 * 品名
	 */
	@XmlElement(name = "PM")
	private String pm;
	/**
	 * 规格
	 */
	@XmlElement(name = "GG")
	private String gg;
	/**
	 * 型号
	 */
	@XmlElement(name = "XH")
	private String xh;
	/**
	 * 规格型号说明
	 */
	@XmlElement(name = "GGXHSM")
	private String ggxhsm;
	/**
	 * 单位
	 */
	@XmlElement(name = "DW")
	private String dw;
	/**
	 * 生产企业名称
	 */
	@XmlElement(name = "SCQY")
	private String scqy;
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
	 * 退货原因
	 */
	@XmlElement(name = "THYY")
	private String thyy;

	public String getDjtxf() {
		return djtxf;
	}

	public void setDjtxf(String djtxf) {
		this.djtxf = djtxf;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getPsdbm() {
		return psdbm;
	}

	public void setPsdbm(String psdbm) {
		this.psdbm = psdbm;
	}

	public String getThdbh() {
		return thdbh;
	}

	public void setThdbh(String thdbh) {
		this.thdbh = thdbh;
	}

	public String getThmxbh() {
		return thmxbh;
	}

	public void setThmxbh(String thmxbh) {
		this.thmxbh = thmxbh;
	}

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

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getGg() {
		return gg;
	}

	public void setGg(String gg) {
		this.gg = gg;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getGgxhsm() {
		return ggxhsm;
	}

	public void setGgxhsm(String ggxhsm) {
		this.ggxhsm = ggxhsm;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getScqy() {
		return scqy;
	}

	public void setScqy(String scqy) {
		this.scqy = scqy;
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

	public String getThyy() {
		return thyy;
	}

	public void setThyy(String thyy) {
		this.thyy = thyy;
	}

	@Override
	public String toString() {
		return "YY152_REP_DETAIL [djtxf=" + djtxf + ", qybm=" + qybm + ", psdbm=" + psdbm + ", thdbh=" + thdbh
				+ ", thmxbh=" + thmxbh + ", sxh=" + sxh + ", cglx=" + cglx + ", thlx=" + thlx + ", hctbdm=" + hctbdm
				+ ", hcxfdm=" + hcxfdm + ", yybddm=" + yybddm + ", pm=" + pm + ", gg=" + gg + ", xh=" + xh + ", ggxhsm="
				+ ggxhsm + ", dw=" + dw + ", scqy=" + scqy + ", cgggxh=" + cgggxh + ", scph=" + scph + ", scrq=" + scrq
				+ ", yxrq=" + yxrq + ", psmxtmlx=" + psmxtmlx + ", psmxtm=" + psmxtm + ", thsl=" + thsl + ", thdj="
				+ thdj + ", thyy=" + thyy + "]";
	}

}
