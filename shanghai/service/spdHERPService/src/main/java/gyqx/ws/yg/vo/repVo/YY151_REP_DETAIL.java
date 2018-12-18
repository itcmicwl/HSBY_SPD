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
 * 耗材采购明细获取 YY151
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "ddlx", "djtxf", "ddmxbh", "ddbh", "sxh", "yyjhdh", "qybm", "psdbm", "cglx", "hctbdm", "hcxfdm",
		"yybddm", "pm", "gg", "xh", "ggxhsm", "dw", "scqy", "cgggxh", "pssm", "cgsl", "cgdj", "sfjj", "psyq", "cwxx",
		"dcpsbs", "bzsm" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY151_REP_DETAIL {

	/**
	 * 采购单类型
	 */
	@XmlElement(name = "DDLX")
	private String ddlx;
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
	 * 采购单号
	 */
	@XmlElement(name = "DDBH")
	private String ddbh;
	/**
	 * 顺序号
	 */
	@XmlElement(name = "SXH")
	private String sxh;
	/**
	 * 医院计划单号
	 */
	@XmlElement(name = "YYJHDH")
	private String yyjhdh;
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
	 * 采购类型
	 */
	@XmlElement(name = "CGLX")
	private String cglx;
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
	 * 配送说明
	 */
	@XmlElement(name = "PSSM")
	private String pssm;
	/**
	 * 采购数量
	 */
	@XmlElement(name = "CGSL")
	private BigDecimal cgsl;
	/**
	 * 采购单价
	 */
	@XmlElement(name = "CGDJ")
	private BigDecimal cgdj;
	/**
	 * 是否加急
	 */
	@XmlElement(name = "SFJJ")
	private String sfjj;
	/**
	 * 配送要求
	 */
	@XmlElement(name = "PSYQ")
	private String psyq;
	/**
	 * 仓位信息
	 */
	@XmlElement(name = "CWXX")
	private String cwxx;
	/**
	 * 多次配送标识
	 */
	@XmlElement(name = "DCPSBS")
	private String dcpsbs;
	/**
	 * 备注说明
	 */
	@XmlElement(name = "BZSM")
	private String bzsm;

	public String getDdlx() {
		return ddlx;
	}

	public void setDdlx(String ddlx) {
		this.ddlx = ddlx;
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

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getSxh() {
		return sxh;
	}

	public void setSxh(String sxh) {
		this.sxh = sxh;
	}

	public String getYyjhdh() {
		return yyjhdh;
	}

	public void setYyjhdh(String yyjhdh) {
		this.yyjhdh = yyjhdh;
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

	public String getCglx() {
		return cglx;
	}

	public void setCglx(String cglx) {
		this.cglx = cglx;
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

	public String getPssm() {
		return pssm;
	}

	public void setPssm(String pssm) {
		this.pssm = pssm;
	}

	public BigDecimal getCgsl() {
		return cgsl;
	}

	public void setCgsl(BigDecimal cgsl) {
		this.cgsl = cgsl;
	}

	public BigDecimal getCgdj() {
		return cgdj;
	}

	public void setCgdj(BigDecimal cgdj) {
		this.cgdj = cgdj;
	}

	public String getSfjj() {
		return sfjj;
	}

	public void setSfjj(String sfjj) {
		this.sfjj = sfjj;
	}

	public String getPsyq() {
		return psyq;
	}

	public void setPsyq(String psyq) {
		this.psyq = psyq;
	}

	public String getCwxx() {
		return cwxx;
	}

	public void setCwxx(String cwxx) {
		this.cwxx = cwxx;
	}

	public String getDcpsbs() {
		return dcpsbs;
	}

	public void setDcpsbs(String dcpsbs) {
		this.dcpsbs = dcpsbs;
	}

	public String getBzsm() {
		return bzsm;
	}

	public void setBzsm(String bzsm) {
		this.bzsm = bzsm;
	}

}
