package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材退货单填报 YY113
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "thmxbh", "sxh", "hctbdm", "hcxfdm", "yybddm", "cljg", "clqkms" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY113_REP_DETAIL {

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
	 * 处理结果
	 */
	@XmlElement(name = "CLJG")
	private String cljg;
	/**
	 * 处理情况描述
	 */
	@XmlElement(name = "CLQKMS")
	private String clqkms;

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

	public String getCljg() {
		return cljg;
	}

	public void setCljg(String cljg) {
		this.cljg = cljg;
	}

	public String getClqkms() {
		return clqkms;
	}

	public void setClqkms(String clqkms) {
		this.clqkms = clqkms;
	}

}
