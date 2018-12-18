package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材配送点传报 YY101
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "czlx", "psdbm", "psdmc", "psdz", "lxrxm", "lxdh", "yzbm", "bzxx" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY101_REQ_MAIN {
	/**
	 * 操作类型
	 */
	@XmlElement(name = "CZLX")
	private String czlx;
	/**
	 * 配送点编码
	 */
	@XmlElement(name = "PSDBM")
	private String psdbm;
	/**
	 * 配送点名称
	 */
	@XmlElement(name = "PSDMC")
	private String psdmc;
	/**
	 * 配送地址
	 */
	@XmlElement(name = "PSDZ")
	private String psdz;
	/**
	 * 联系人姓名
	 */
	@XmlElement(name = "LXRXM")
	private String lxrxm;
	/**
	 * 联系电话
	 */
	@XmlElement(name = "LXDH")
	private String lxdh;
	/**
	 * 邮政编码
	 */
	@XmlElement(name = "YZBM")
	private String yzbm;
	/**
	 * 备注信息
	 */
	@XmlElement(name = "BZXX")
	private String bzxx;

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

	public String getPsdbm() {
		return psdbm;
	}

	public void setPsdbm(String psdbm) {
		this.psdbm = psdbm;
	}

	public String getPsdmc() {
		return psdmc;
	}

	public void setPsdmc(String psdmc) {
		this.psdmc = psdmc;
	}

	public String getPsdz() {
		return psdz;
	}

	public void setPsdz(String psdz) {
		this.psdz = psdz;
	}

	public String getLxrxm() {
		return lxrxm;
	}

	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public String getBzxx() {
		return bzxx;
	}

	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}

}
