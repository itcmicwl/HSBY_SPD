package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.*;

/**
 * 耗材按发票明细获取 YY156
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "qybm", "fpmxbhcxtj" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY156_REQ_MAIN {

	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	/**
	 * 发票明细号查询条件
	 */
	@XmlElement(name = "FPMXBHCXTJ")
	private String fpmxbhcxtj;

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getFpmxbhcxtj() {
		return fpmxbhcxtj;
	}

	public void setFpmxbhcxtj(String fpmxbhcxtj) {
		this.fpmxbhcxtj = fpmxbhcxtj;
	}
}