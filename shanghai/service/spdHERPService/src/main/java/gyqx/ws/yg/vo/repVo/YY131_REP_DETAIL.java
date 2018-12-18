package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材配送验收确认 YY131
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "psmxbh", "hctbdm", "cljg", "clqkms" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY131_REP_DETAIL {

	/**
	 * 配送明细编号
	 */
	@XmlElement(name = "PSMXBH")
	private String psmxbh;
	/**
	 * 耗材统编代码
	 */
	@XmlElement(name = "HCTBDM")
	private String hctbdm;
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

	public String getPsmxbh() {
		return psmxbh;
	}

	public void setPsmxbh(String psmxbh) {
		this.psmxbh = psmxbh;
	}

	public String getHctbdm() {
		return hctbdm;
	}

	public void setHctbdm(String hctbdm) {
		this.hctbdm = hctbdm;
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
