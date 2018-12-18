package gyqx.ws.yg.vo.repVo;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材配送明细获取 YY155
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "psmxbh", "psmxtmlx", "psmxtm", "ddmxbh", "yyjhdh", "sxh", "cwxx", "xsddh", "hctbdm", "hcxfdm",
		"yybddm", "pm", "gg", "xh", "ggxhsm", "dw", "scqy", "scph", "scrq", "yxrq", "psl" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY155_REP_DETAIL {

	/**
	 * 配送明细编号
	 */
	@XmlElement(name = "PSMXBH")
	private String psmxbh;
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
	private String psl;

	 private String psdbh;
	/**
	 * 最后更新时间
	 */
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 数据版本
	 */
	private int version ;
	public String getPsmxbh() {
		return psmxbh;
	}

	public void setPsmxbh(String psmxbh) {
		this.psmxbh = psmxbh;
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

	public String getPsl() {
		return psl;
	}

	public void setPsl(String psl) {
		this.psl = psl;
	}

	public Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPsdbh() {
		return psdbh;
	}

	public void setPsdbh(String psdbh) {
		this.psdbh = psdbh;
	}
}
