package gyqx.ws.yg.vo.repVo;

import java.math.BigDecimal;

import javax.xml.bind.annotation.*;

/**
 * 耗材按发票明细获取 YY156
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "fpbh", "fpmxbh", "fpdm", "fph", "fprq", "fphszje", "qybm", "yybm", "psdbm", "cglx", "fpbz",
		"sfwpsfp", "wpsfpsm", "sfch", "hctbdm", "hcxfdm", "yybddm", "ggxhsm", "glmxbh", "xsddh", "scph", "scrq", "yxrq",
		"spsl", "wsdj", "hsdj", "sl", "se", "hsje", "pfj", "lsj", "zczh" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY156_REP_DETAIL {

	/**
	 * 发票编号
	 */
	@XmlElement(name = "FPBH")
	private String fpbh;
	/**
	 * 发票明细编号
	 */
	@XmlElement(name = "FPMXBH")
	private String fpmxbh;
	/**
	 * 发票代码
	 */
	@XmlElement(name = "FPDM")
	private String fpdm;
	/**
	 * 发票号
	 */
	@XmlElement(name = "FPH")
	private String fph;
	/**
	 * 发票日期
	 */
	@XmlElement(name = "FPRQ")
	private String fprq;
	/**
	 * 发票含税总金额
	 */
	@XmlElement(name = "FPHSZJE")
	private BigDecimal fphszje;
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	/**
	 * 医院编码
	 */
	@XmlElement(name = "YYBM")
	private String yybm;
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
	 * 发票备注
	 */
	@XmlElement(name = "FPBZ")
	private String fpbz;
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
	private BigDecimal spsl;
	/**
	 * 无税单价
	 */
	@XmlElement(name = "WSDJ")
	private BigDecimal wsdj;
	/**
	 * 含税单价
	 */
	@XmlElement(name = "HSDJ")
	private BigDecimal hsdj;
	/**
	 * 税率
	 */
	@XmlElement(name = "SL")
	private BigDecimal sl;
	/**
	 * 税额
	 */
	@XmlElement(name = "SE")
	private BigDecimal se;
	/**
	 * 含税金额
	 */
	@XmlElement(name = "HSJE")
	private BigDecimal hsje;
	/**
	 * 批发价
	 */
	@XmlElement(name = "PFJ")
	private BigDecimal pfj;
	/**
	 * 零售价
	 */
	@XmlElement(name = "LSJ")
	private BigDecimal lsj;
	/**
	 * 注册证号
	 */
	@XmlElement(name = "ZCZH")
	private String zczh;

	public String getFpbh() {
		return fpbh;
	}

	public void setFpbh(String fpbh) {
		this.fpbh = fpbh;
	}

	public String getFpmxbh() {
		return fpmxbh;
	}

	public void setFpmxbh(String fpmxbh) {
		this.fpmxbh = fpmxbh;
	}

	public String getFpdm() {
		return fpdm;
	}

	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	public String getFph() {
		return fph;
	}

	public void setFph(String fph) {
		this.fph = fph;
	}

	public String getFprq() {
		return fprq;
	}

	public void setFprq(String fprq) {
		this.fprq = fprq;
	}

	public BigDecimal getFphszje() {
		return fphszje;
	}

	public void setFphszje(BigDecimal fphszje) {
		this.fphszje = fphszje;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getYybm() {
		return yybm;
	}

	public void setYybm(String yybm) {
		this.yybm = yybm;
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

	public String getFpbz() {
		return fpbz;
	}

	public void setFpbz(String fpbz) {
		this.fpbz = fpbz;
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

	public BigDecimal getSpsl() {
		return spsl;
	}

	public void setSpsl(BigDecimal spsl) {
		this.spsl = spsl;
	}

	public BigDecimal getWsdj() {
		return wsdj;
	}

	public void setWsdj(BigDecimal wsdj) {
		this.wsdj = wsdj;
	}

	public BigDecimal getHsdj() {
		return hsdj;
	}

	public void setHsdj(BigDecimal hsdj) {
		this.hsdj = hsdj;
	}

	public BigDecimal getSl() {
		return sl;
	}

	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}

	public BigDecimal getSe() {
		return se;
	}

	public void setSe(BigDecimal se) {
		this.se = se;
	}

	public BigDecimal getHsje() {
		return hsje;
	}

	public void setHsje(BigDecimal hsje) {
		this.hsje = hsje;
	}

	public BigDecimal getPfj() {
		return pfj;
	}

	public void setPfj(BigDecimal pfj) {
		this.pfj = pfj;
	}

	public BigDecimal getLsj() {
		return lsj;
	}

	public void setLsj(BigDecimal lsj) {
		this.lsj = lsj;
	}

	public String getZczh() {
		return zczh;
	}

	public void setZczh(String zczh) {
		this.zczh = zczh;
	}

}