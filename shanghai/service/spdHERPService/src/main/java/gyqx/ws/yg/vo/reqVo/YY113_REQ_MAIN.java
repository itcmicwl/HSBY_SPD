package gyqx.ws.yg.vo.reqVo;

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
 * @description 同一张退货单中可以包含不同企业的明细<br/>
 *              退货单在填写时无需与采购信息（原采购单、配送单或发票）关联<br/>
 *              一次上传的退货单中的退货明细顺序号不能重复<br/>
 *              一次上传的退货其退货明细不能多于 30 条，若多于 30 条，若多于 30 条需要拆分多个退货单 上传<br/>
 *              需要将返回的退货单号 与退货单明细记录到本地，已作为后续处理的依据<br/>
 *              退货单作废时可以无需上传采购明细信息，记录数填写的即可，在返回时，将不返回明细，只返回消息主体<br/>
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "czlx", "yybm", "psdbm", "sjthrq", "thdbh", "jls" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY113_REQ_MAIN {
	/**
	 * 操作类型
	 */
	@XmlElement(name = "CZLX")
	private String czlx;
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
	 * 实际退货日期
	 */
	@XmlElement(name = "SJTHRQ")
	private String sjthrq;
	/**
	 * 退货单编号
	 */
	@XmlElement(name = "THDBH")
	private String thdbh;
	/**
	 * 记录数
	 */
	@XmlElement(name = "JLS")
	private Integer jls;

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

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

	public String getSjthrq() {
		return sjthrq;
	}

	public void setSjthrq(String sjthrq) {
		this.sjthrq = sjthrq;
	}

	public String getThdbh() {
		return thdbh;
	}

	public void setThdbh(String thdbh) {
		this.thdbh = thdbh;
	}

	public Integer getJls() {
		return jls;
	}

	public void setJls(Integer jls) {
		this.jls = jls;
	}

}
