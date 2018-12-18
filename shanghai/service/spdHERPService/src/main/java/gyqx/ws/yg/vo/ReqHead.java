package gyqx.ws.yg.vo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by moonb on 2017/11/10.
 */
@XmlType(propOrder = { "ip", "mac", "remark" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "HEAD")
public class ReqHead {
	/**
	 * 地址
	 */
	@XmlElement(name = "IP")
	private String ip;
	/**
	 * MAC地址
	 */
	@XmlElement(name = "MAC")
	private String mac;
	/**
	 * 备注信息
	 */
	@XmlElement(name = "BZXX")
	private String remark;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
