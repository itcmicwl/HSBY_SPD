package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 企业资证信息获取 YY165
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "zjbh", "zjlx", "zjwybm", "qyrq", "yxrq" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY165_REP_DETAIL {

	/**
	 * 证件编号
	 */
	@XmlElement(name = "ZJBH")
	private String zjbh;

	/**
	 * 证件类型
	 */
	@XmlElement(name = "ZJLX")
	private String zjlx;

	/**
	 * 证件唯一编号
	 */
	@XmlElement(name = "ZJWYBH")
	private String zjwybm;

	/**
	 * 证件启用日期
	 */
	@XmlElement(name = "QYRQ")
	private String qyrq;

	/**
	 * 证件有效期
	 */
	@XmlElement(name = "YXRQ")
	private String yxrq;

	public String getZjbh() {
		return zjbh;
	}

	public void setZjbh(String zjbh) {
		this.zjbh = zjbh;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getZjwybm() {
		return zjwybm;
	}

	public void setZjwybm(String zjwybm) {
		this.zjwybm = zjwybm;
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
