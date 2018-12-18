package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材按退货单获取退货明细状态 YY162
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "thmxdh", "thmxzt", "thdqyclsm" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY162_REP_DETAIL {

	/**
	 * 退货明细编号
	 */
	@XmlElement(name = "THMXBH")
	private String thmxdh;

	/**
	 * 退货明细状态
	 */
	@XmlElement(name = "THMXZT")
	private String thmxzt;

	/**
	 * 退货单企业处理说明
	 */
	@XmlElement(name = "THDQYCLSM")
	private String thdqyclsm;

	public String getThmxdh() {
		return thmxdh;
	}

	public void setThmxdh(String thmxdh) {
		this.thmxdh = thmxdh;
	}

	public String getThmxzt() {
		return thmxzt;
	}

	public void setThmxzt(String thmxzt) {
		this.thmxzt = thmxzt;
	}

	public String getThdqyclsm() {
		return thdqyclsm;
	}

	public void setThdqyclsm(String thdqyclsm) {
		this.thdqyclsm = thdqyclsm;
	}
}
