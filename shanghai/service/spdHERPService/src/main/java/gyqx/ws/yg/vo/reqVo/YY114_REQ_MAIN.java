package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材退货单确认 YY114
 * 
 * @description 退货单确认时需要按填报上传的信息重新填写<br/>
 *              商品 记录数 为填报时上传的退货明细 商品记录数，无需汇总<br/>
 *              已确认的退货单将正式生效且不能再进行作废操作<br/>
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "yybm", "psdbm", "thdbh", "spsl" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY114_REQ_MAIN {
	/**
	 * 医院编码
	 */
	@XmlElement(name = "YYBM")
	private String yybm;
	/**
	 * 配送点编码
	 */
	@XmlElement(name = "PSDBM")
	private String psdbm;
	/**
	 * 退货单编号
	 */
	@XmlElement(name = "THDBH")
	private String thdbh;
	/**
	 * 商品记录数
	 */
	@XmlElement(name = "SPSL")
	private Integer spsl;

	public String getYybm() {
		return yybm;
	}

	public void setYybm(String yybm) {
		this.yybm = yybm;
	}

	public String getPsdbm() {
		return psdbm;
	}

	public void setPsdbm(String psdbm) {
		this.psdbm = psdbm;
	}

	public String getThdbh() {
		return thdbh;
	}

	public void setThdbh(String thdbh) {
		this.thdbh = thdbh;
	}

	public Integer getSpsl() {
		return spsl;
	}

	public void setSpsl(Integer spsl) {
		this.spsl = spsl;
	}

}
