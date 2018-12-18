package gyqx.ws.yg.vo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "acceptDatetime", "res", "errMsg", "remark" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "HEAD")
public class RepHead {
	/**
	 * 接收时间
	 */
	@XmlElement(name = "JSSJ")
	private String acceptDatetime;
	/**
	 * 消息主体处理结果
	 */
	@XmlElement(name = "ZTCLJG")
	private String res;
	/**
	 * 错误提示内容
	 */
	@XmlElement(name = "CWXX")
	private String errMsg;
	/**
	 * 备注信息
	 */
	@XmlElement(name = "BZXX")
	private String remark;

	public String getAcceptDatetime() {
		return acceptDatetime;
	}

	public void setAcceptDatetime(String acceptDatetime) {
		this.acceptDatetime = acceptDatetime;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
