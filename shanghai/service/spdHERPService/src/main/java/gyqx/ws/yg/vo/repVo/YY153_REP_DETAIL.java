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
 * 耗材按配送明细获取 YY153
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "psdbm", "psdbh", "psmxbh", "psdh", "qybm", "psmxtmlx", "psmxtm", "ddmxbh", "yyjhdh", "sxh",
		"cwxx", "xsddh", "hctbdm", "hcxfdm", "yybddm", "pm", "gg", "xh", "ggxhsm", "dw", "scqy", "scph", "scrq", "yxrq",
		"psl" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY153_REP_DETAIL {

	/**
	 * 配送点编码
	 */
	@XmlElement(name = "PSDBM")
	private String psdbm;
	/**
	 * 配送单编号
	 */
	@XmlElement(name = "PSDBH")
	private String psdbh;
	/**
	 * 配送明细编号
	 */
	@XmlElement(name = "PSMXBH")
	private String psmxbh;
	/**
	 * 配送单号
	 */
	@XmlElement(name = "PSDH")
	private String psdh;
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
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
	 * 采购单明细编号
	 */
	@XmlElement(name = "DDMXBH")
	private String ddmxbh;
	/**
	 * 医院计划单号
	 */
	@XmlElement(name = "YYJHDH")
	private String yyjhdh;
	/**
	 * 顺序号
	 */
	@XmlElement(name = "SXH")
	private String sxh;
	/**
	 * 仓位信息
	 */
	@XmlElement(name = "CWXX")
	private String cwxx;
	/**
	 * 销售订单号
	 */
	@XmlElement(name = "XSDDH")
	private String xsddh;
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
	 * 配送数量
	 */
	@XmlElement(name = "PSL")
	private BigDecimal psl;

	public String getPsdbm() {
		return psdbm;
	}

	public void setPsdbm(String psdbm) {
		this.psdbm = psdbm;
	}

	public String getPsdbh() {
		return psdbh;
	}

	public void setPsdbh(String psdbh) {
		this.psdbh = psdbh;
	}

	public String getPsmxbh() {
		return psmxbh;
	}

	public void setPsmxbh(String psmxbh) {
		this.psmxbh = psmxbh;
	}

	public String getPsdh() {
		return psdh;
	}

	public void setPsdh(String psdh) {
		this.psdh = psdh;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
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

	public String getDdmxbh() {
		return ddmxbh;
	}

	public void setDdmxbh(String ddmxbh) {
		this.ddmxbh = ddmxbh;
	}

	public String getYyjhdh() {
		return yyjhdh;
	}

	public void setYyjhdh(String yyjhdh) {
		this.yyjhdh = yyjhdh;
	}

	public String getSxh() {
		return sxh;
	}

	public void setSxh(String sxh) {
		this.sxh = sxh;
	}

	public String getCwxx() {
		return cwxx;
	}

	public void setCwxx(String cwxx) {
		this.cwxx = cwxx;
	}

	public String getXsddh() {
		return xsddh;
	}

	public void setXsddh(String xsddh) {
		this.xsddh = xsddh;
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

	public BigDecimal getPsl() {
		return psl;
	}

	public void setPsl(BigDecimal psl) {
		this.psl = psl;
	}

}
