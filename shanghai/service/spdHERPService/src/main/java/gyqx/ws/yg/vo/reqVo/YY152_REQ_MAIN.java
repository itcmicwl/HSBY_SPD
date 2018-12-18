package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材退货明细获取 YY152
 * 
 * @description 必须输入开始日期和截止来获取 退货单信息，只能30 天以内的退货 天以内的退货 单信息 <br/>
 *              若一次调用返回的是否完结标识为否，则说明还存在未获取到数据，此时需要上传前一次调用返回的最后条信息退货明细编号或当次最后退货明细编号作为查询条件，其余条件不变的情况下再次调用接口获取后续数据<br/>
 *              可重复获取尚未响应的退货单信息<br/>
 *              每次最多返回 30 条信息，若超过 30 条信息，则需要通过上传本次调用返回的最后一条记录的明细ID来反复调用该接口<br/>
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "qsrq", "jzrq", "qybm", "djtxf", "thmxbh", "thmxbhcxtj" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY152_REQ_MAIN {

	/**
	 * 起始日期
	 */
	@XmlElement(name = "QSRQ")
	private String qsrq;
	/**
	 * 截至日期
	 */
	@XmlElement(name = "JZRQ")
	private String jzrq;
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	/**
	 * 单据填写方
	 */
	@XmlElement(name = "DJTXF")
	private String djtxf;
	/**
	 * 退货明细单号
	 */
	@XmlElement(name = "THMXBH")
	private String thmxbh;
	/**
	 * 退货明细单号查询条件
	 */
	@XmlElement(name = "THMXBHCXTJ")
	private String thmxbhcxtj;

	public String getQsrq() {
		return qsrq;
	}

	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}

	public String getJzrq() {
		return jzrq;
	}

	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getDjtxf() {
		return djtxf;
	}

	public void setDjtxf(String djtxf) {
		this.djtxf = djtxf;
	}

	public String getThmxbh() {
		return thmxbh;
	}

	public void setThmxbh(String thmxbh) {
		this.thmxbh = thmxbh;
	}

	public String getThmxbhcxtj() {
		return thmxbhcxtj;
	}

	public void setThmxbhcxtj(String thmxbhcxtj) {
		this.thmxbhcxtj = thmxbhcxtj;
	}

}
