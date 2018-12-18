package gyqx.ws.yg.vo.repVo;

import java.math.BigDecimal;

import javax.xml.bind.annotation.*;

/**
 * 耗材发票明细获取 YY158
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "fpmxbh", "sfwpsfp", "wpsfpsm", "sfch", "hctbdm", "hcxfdm", "yybddm", "ggxhsm", "glmxbh",
		"xsddh", "scph", "scrq", "yxrq", "spsl", "wsdj", "hsdj", "sl", "se", "hsje", "pfj", "lsj", "zczh" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY158_REP_DETAIL {

	/**
	 * 发票明细编号
	 */
	@XmlElement(name = "FPMXBH")
	private String fpmxbh;
	/**
	 * 是否无配送发票
	 */
	@XmlElement(name = "SFWPSFP")
	private String sfwpsfp;
	/**
	 * 无配送发票说明
	 */
	@XmlElement(name = "WPSFPSM")
	private String wpsfpsm;
	/**
	 * 是否冲红
	 */
	@XmlElement(name = "SFCH")
	private String sfch;
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
	 * 规格型号说明
	 */
	@XmlElement(name = "GGXHSM")
	private String ggxhsm;
	/**
	 * 关联明细编号
	 */
	@XmlElement(name = "GLMXBH")
	private String glmxbh;
	/**
	 * 销售订单号
	 */
	@XmlElement(name = "XSDDH")
	private String xsddh;
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
	 * 商品数量
	 */
	@XmlElement(name = "SPSL")
	private String spsl;
	/**
	 * 无税单价
	 */
	@XmlElement(name = "WSDJ")
	private String wsdj;
	/**
	 * 含税单价
	 */
	@XmlElement(name = "HSDJ")
	private String hsdj;
	/**
	 * 税率
	 */
	@XmlElement(name = "SL")
	private String sl;
	/**
	 * 税额
	 */
	@XmlElement(name = "SE")
	private String se;
	/**
	 * 含税金额
	 */
	@XmlElement(name = "HSJE")
	private String hsje;
	/**
	 * 批发价
	 */
	@XmlElement(name = "PFJ")
	private String pfj;
	/**
	 * 零售价
	 */
	@XmlElement(name = "LSJ")
	private String lsj;
	/**
	 * 注册证号
	 */
	@XmlElement(name = "ZCZH")
	private String zczh;

	public String getFpmxbh() {
		return fpmxbh;
	}

	public void setFpmxbh(String fpmxbh) {
		this.fpmxbh = fpmxbh;
	}

	public String getSfwpsfp() {
		return sfwpsfp;
	}

	public void setSfwpsfp(String sfwpsfp) {
		this.sfwpsfp = sfwpsfp;
	}

	public String getWpsfpsm() {
		return wpsfpsm;
	}

	public void setWpsfpsm(String wpsfpsm) {
		this.wpsfpsm = wpsfpsm;
	}

	public String getSfch() {
		return sfch;
	}

	public void setSfch(String sfch) {
		this.sfch = sfch;
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

	public String getGgxhsm() {
		return ggxhsm;
	}

	public void setGgxhsm(String ggxhsm) {
		this.ggxhsm = ggxhsm;
	}

	public String getGlmxbh() {
		return glmxbh;
	}

	public void setGlmxbh(String glmxbh) {
		this.glmxbh = glmxbh;
	}

	public String getXsddh() {
		return xsddh;
	}

	public void setXsddh(String xsddh) {
		this.xsddh = xsddh;
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

	public String getSpsl() {
		return spsl;
	}

	public void setSpsl(String spsl) {
		this.spsl = spsl;
	}

	public String getWsdj() {
		return wsdj;
	}

	public void setWsdj(String wsdj) {
		this.wsdj = wsdj;
	}

	public String getHsdj() {
		return hsdj;
	}

	public void setHsdj(String hsdj) {
		this.hsdj = hsdj;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getSe() {
		return se;
	}

	public void setSe(String se) {
		this.se = se;
	}

	public String getHsje() {
		return hsje;
	}

	public void setHsje(String hsje) {
		this.hsje = hsje;
	}

	public String getPfj() {
		return pfj;
	}

	public void setPfj(String pfj) {
		this.pfj = pfj;
	}

	public String getLsj() {
		return lsj;
	}

	public void setLsj(String lsj) {
		this.lsj = lsj;
	}

	public String getZczh() {
		return zczh;
	}

	public void setZczh(String zczh) {
		this.zczh = zczh;
	}

}