package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 企业授权信息获取 YY166
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "sqmxbh", "sqbh", "cglx", "sjsqbh", "sqqybm", "sqqymc", "bsqqybm", "bsqqymc", "qyrq", "yxrq" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY166_REP_DETAIL {

	/**
	 * 授权明细编号
	 */
	@XmlElement(name = "SQMXBH")
	private String sqmxbh;

	/**
	 * 授权编号
	 */
	@XmlElement(name = "SQBH")
	private String sqbh;

	/**
	 * 采购类型
	 */
	@XmlElement(name = "CGLX")
	private String cglx;

	/**
	 * 上级授权编号
	 */
	@XmlElement(name = "SJSQBH")
	private String sjsqbh;

	/**
	 * 授权企业编码
	 */
	@XmlElement(name = "SQQYBM")
	private String sqqybm;

	/**
	 * 授权企业名称
	 */
	@XmlElement(name = "SQQYMC")
	private String sqqymc;

	/**
	 * 被授权企业编码
	 */
	@XmlElement(name = "BSQQYBM")
	private String bsqqybm;

	/**
	 * 被授权企业名称
	 */
	@XmlElement(name = "BSQQYMC")
	private String bsqqymc;

	/**
	 * 启用日期
	 */
	@XmlElement(name = "QYRQ")
	private String qyrq;

	/**
	 * 有效日期
	 */
	@XmlElement(name = "YXRQ")
	private String yxrq;

	public String getSqmxbh() {
		return sqmxbh;
	}

	public void setSqmxbh(String sqmxbh) {
		this.sqmxbh = sqmxbh;
	}

	public String getSqbh() {
		return sqbh;
	}

	public void setSqbh(String sqbh) {
		this.sqbh = sqbh;
	}

	public String getCglx() {
		return cglx;
	}

	public void setCglx(String cglx) {
		this.cglx = cglx;
	}

	public String getSjsqbh() {
		return sjsqbh;
	}

	public void setSjsqbh(String sjsqbh) {
		this.sjsqbh = sjsqbh;
	}

	public String getSqqybm() {
		return sqqybm;
	}

	public void setSqqybm(String sqqybm) {
		this.sqqybm = sqqybm;
	}

	public String getSqqymc() {
		return sqqymc;
	}

	public void setSqqymc(String sqqymc) {
		this.sqqymc = sqqymc;
	}

	public String getBsqqybm() {
		return bsqqybm;
	}

	public void setBsqqybm(String bsqqybm) {
		this.bsqqybm = bsqqybm;
	}

	public String getBsqqymc() {
		return bsqqymc;
	}

	public void setBsqqymc(String bsqqymc) {
		this.bsqqymc = bsqqymc;
	}

	public String getQyrq() {
		return qyrq;
	}

	public void setQyrq(String qyrq) {
		this.qyrq = qyrq;
	}

	public String getYxrq() {
		return yxrq;
	}

	public void setYxrq(String yxrq) {
		this.yxrq = yxrq;
	}

}
