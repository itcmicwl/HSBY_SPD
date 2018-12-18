package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材按采购单获取采购明细状态(YY159) 
 * Created by moonb on 2017/11/22.
 */
@XmlType(propOrder = { "ddmxbh", "cgmxzt", "qykc", "cgmxshyj", "cgdclsm" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
public class YY159_REP_DETAIL {

	/**
	 * 采购单明细编号
	 */
	@XmlElement(name = "DDMXBH")
	private String ddmxbh;

	/**
	 * 采购单明细状态
	 */
	@XmlElement(name = "CGMXZT")
	private String cgmxzt;

	/**
	 * 企业库存
	 */
	@XmlElement(name = "QYKC")
	private String qykc;

	/**
	 * 采购单明细审核意见
	 */
	@XmlElement(name = "CGMXSHYJ")
	private String cgmxshyj;

	/**
	 * 采购单企业处理说明
	 */
	@XmlElement(name = "CGDCLSM")
	private String cgdclsm;

	public String getDdmxbh() {
		return ddmxbh;
	}

	public void setDdmxbh(String ddmxbh) {
		this.ddmxbh = ddmxbh;
	}

	public String getCgmxzt() {
		return cgmxzt;
	}

	public void setCgmxzt(String cgmxzt) {
		this.cgmxzt = cgmxzt;
	}

	public String getQykc() {
		return qykc;
	}

	public void setQykc(String qykc) {
		this.qykc = qykc;
	}

	public String getCgmxshyj() {
		return cgmxshyj;
	}

	public void setCgmxshyj(String cgmxshyj) {
		this.cgmxshyj = cgmxshyj;
	}

	public String getCgdclsm() {
		return cgdclsm;
	}

	public void setCgdclsm(String cgdclsm) {
		this.cgdclsm = cgdclsm;
	}
}
