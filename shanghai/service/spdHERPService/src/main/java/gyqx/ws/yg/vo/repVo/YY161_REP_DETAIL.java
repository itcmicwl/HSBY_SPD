package gyqx.ws.yg.vo.repVo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材配送单状态获取 YY161
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "psmxbh", "psmxzt", "yyytgs", "yyygbs", "ysytgs", "ysybgs" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY161_REP_DETAIL {

	/**
	 * 配送明细编号
	 */
	@XmlElement(name = "PSMXBH")
	private String psmxbh;
	/**
	 * 配送明细状态
	 */
	@XmlElement(name = "PSMXZT")
	private String psmxzt;
	/**
	 * 已预验通过数
	 */
	@XmlElement(name = "YYYTGS")
	private BigDecimal yyytgs;
	/**
	 * 已预验不过数
	 */
	@XmlElement(name = "YYYBGS")
	private BigDecimal yyygbs;
	/**
	 * 已实验通过数
	 */
	@XmlElement(name = "YSYTGS")
	private BigDecimal ysytgs;
	/**
	 * 已实验不过数
	 */
	@XmlElement(name = "YSYBGS")
	private BigDecimal ysybgs;
	private String psdbh;
	private List<String> psmxArr = new ArrayList<>();
	/**
	 * 最后更新时间
	 */
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 数据版本
	 */
	private int version ;
	public String getPsmxbh() {
		return psmxbh;
	}

	public void setPsmxbh(String psmxbh) {
		this.psmxbh = psmxbh;
	}

	public String getPsmxzt() {
		return psmxzt;
	}

	public void setPsmxzt(String psmxzt) {
		this.psmxzt = psmxzt;
	}

	public BigDecimal getYyytgs() {
		return yyytgs;
	}

	public void setYyytgs(BigDecimal yyytgs) {
		this.yyytgs = yyytgs;
	}

	public BigDecimal getYyygbs() {
		return yyygbs;
	}

	public void setYyygbs(BigDecimal yyygbs) {
		this.yyygbs = yyygbs;
	}

	public BigDecimal getYsytgs() {
		return ysytgs;
	}

	public void setYsytgs(BigDecimal ysytgs) {
		this.ysytgs = ysytgs;
	}

	public BigDecimal getYsybgs() {
		return ysybgs;
	}

	public void setYsybgs(BigDecimal ysybgs) {
		this.ysybgs = ysybgs;
	}

	public Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPsdbh() {
		return psdbh;
	}

	public void setPsdbh(String psdbh) {
		this.psdbh = psdbh;
	}

	public List<String> getPsmxArr() {
		return psmxArr;
	}

	public void setPsmxArr(List<String> psmxArr) {
		this.psmxArr = psmxArr;
	}
}
