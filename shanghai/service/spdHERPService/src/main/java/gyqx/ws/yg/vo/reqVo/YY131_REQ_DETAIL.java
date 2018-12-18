package gyqx.ws.yg.vo.reqVo;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材配送验收确认 YY131
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "psmxbh", "hctbdm", "scph", "psl", "ystgs", "ysbgs", "ysbzsm" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY131_REQ_DETAIL {

	/**
	 * 配送明细编号
	 */
	@XmlElement(name = "PSMXBH")
	private String psmxbh;
	/**
	 * 耗材统编代码
	 */
	@XmlElement(name = "HCTBDM")
	private String hctbdm;
	/**
	 * 生产批号
	 */
	@XmlElement(name = "SCPH")
	private String scph;
	/**
	 * 配送数量
	 */
	@XmlElement(name = "PSL")
	private BigDecimal psl;
	/**
	 * 本次验收通过数
	 */
	@XmlElement(name = "YSTGS")
	private BigDecimal ystgs;
	/**
	 * 本次验收不过数
	 */
	@XmlElement(name = "YSBGS")
	private BigDecimal ysbgs;
	/**
	 * 验收备注说明
	 */
	@XmlElement(name = "YSBZSM")
	private String ysbzsm;
	/**
	 * 最后更新时间
	 */
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 数据版本
	 */
	private int version ;
	private Integer isSend;
	private String psdbh;
	public String getPsmxbh() {
		return psmxbh;
	}

	public void setPsmxbh(String psmxbh) {
		this.psmxbh = psmxbh;
	}

	public String getHctbdm() {
		return hctbdm;
	}

	public void setHctbdm(String hctbdm) {
		this.hctbdm = hctbdm;
	}

	public String getScph() {
		return scph;
	}

	public void setScph(String scph) {
		this.scph = scph;
	}

	public BigDecimal getPsl() {
		return psl;
	}

	public void setPsl(BigDecimal psl) {
		this.psl = psl;
	}

	public BigDecimal getYstgs() {
		return ystgs;
	}

	public void setYstgs(BigDecimal ystgs) {
		this.ystgs = ystgs;
	}

	public BigDecimal getYsbgs() {
		return ysbgs;
	}

	public void setYsbgs(BigDecimal ysbgs) {
		this.ysbgs = ysbgs;
	}

	public String getYsbzsm() {
		return ysbzsm;
	}

	public void setYsbzsm(String ysbzsm) {
		this.ysbzsm = ysbzsm;
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

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

    public String getPsdbh() {
        return psdbh;
    }

    public void setPsdbh(String psdbh) {
        this.psdbh = psdbh;
    }
}
