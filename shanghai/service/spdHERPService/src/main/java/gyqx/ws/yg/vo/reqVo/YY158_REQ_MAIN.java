package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.*;

/**
 * 耗材发票明细获取 YY158
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "fpbh", "fpmxbhcxtj" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY158_REQ_MAIN {

	/**
	 * 发票编号
	 */
	@XmlElement(name = "FPBH")
	private String fpbh;
	/**
	 * 发票明细号查询条件
	 */
	@XmlElement(name = "FPMXBHCXTJ")
	private String fpmxbhcxtj;

	public String getFpbh() {
		return fpbh;
	}

	public void setFpbh(String fpbh) {
		this.fpbh = fpbh;
	}

	public String getFpmxbhcxtj() {
		return fpmxbhcxtj;
	}

	public void setFpmxbhcxtj(String fpmxbhcxtj) {
		this.fpmxbhcxtj = fpmxbhcxtj;
	}

}