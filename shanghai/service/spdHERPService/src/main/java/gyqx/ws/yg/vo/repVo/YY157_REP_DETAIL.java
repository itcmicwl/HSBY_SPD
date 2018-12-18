package gyqx.ws.yg.vo.repVo;

import java.math.BigDecimal;

import javax.xml.bind.annotation.*;

/**
 * 耗材发票信息获取 YY157
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "fpbh", "fpdm", "fph", "fprq", "fphszje", "qybm", "yybm", "psdbm", "cglx", "fpbz", "fpmxts",
		"fpzt" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY157_REP_DETAIL {

	/**
	 * 发票编号
	 */
	@XmlElement(name = "FPBH")
	private String fpbh;
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
	 * 发票明细条数
	 */
	@XmlElement(name = "FPMXTS")
	private Integer fpmxts;
	/**
	 * 发票状态
	 */
	@XmlElement(name = "FPZT")
	private String fpzt;

	public String getFpbh() {
		return fpbh;
	}

	public void setFpbh(String fpbh) {
		this.fpbh = fpbh;
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

	public Integer getFpmxts() {
		return fpmxts;
	}

	public void setFpmxts(Integer fpmxts) {
		this.fpmxts = fpmxts;
	}

	public String getFpzt() {
		return fpzt;
	}

	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}

}