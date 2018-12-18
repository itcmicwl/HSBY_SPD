package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 企业信息获取(YY164)
 * Created by moonb on 2017/11/22.
 */
@XmlType(propOrder = { "qybm", "qymc","qydz","lxr","lxdh"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
public class YY164_REP_DETAIL {
	
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	
	/**
	 * 企业名称
	 */
	@XmlElement(name = "QYMC")
	private String qymc;
	
	/**
	 * 企业地址
	 */
	@XmlElement(name = "QYDZ")
	private String qydz;
	
	/**
	 * 联系人
	 */
	@XmlElement(name = "LXR")
	private String lxr;
	
	/**
	 * 联系电话
	 */
	@XmlElement(name = "LXDH")
	private String lxdh;

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getQymc() {
		return qymc;
	}

	public void setQymc(String qymc) {
		this.qymc = qymc;
	}

	public String getQydz() {
		return qydz;
	}

	public void setQydz(String qydz) {
		this.qydz = qydz;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

}
