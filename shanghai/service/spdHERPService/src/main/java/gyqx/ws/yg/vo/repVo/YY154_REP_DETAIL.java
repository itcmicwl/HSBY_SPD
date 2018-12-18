package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.List;

/**
 * 耗材配送单获取 YY154
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "psdbh", "psdh", "qybm", "psmxts", "psdzt" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "STRUCT")
public class YY154_REP_DETAIL {

	/**
	 * 配送单编号
	 */
	@XmlElement(name = "PSDBH")
	private String psdbh;
	/**
	 * 配送单号
	 */
	@XmlElement(name = "PSDH")
	private String psdh;
	/**
	 * 企业编码
	 */
	@XmlElement(name = "QYBM")
	private String qybm;
	/**
	 * 配送明细条数
	 */
	@XmlElement(name = "PSMXTS")
	private Integer psmxts;
	/**
	 * 配送单状态
	 */
	@XmlElement(name = "PSDZT")
	private String psdzt;
	/**
	 * 最后更新时间
	 */
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 数据版本
	 */
	private int version ;
	private String qymc;
	
	/**
	 * 查询条件：截止日期
	 */
	private int jzrq;
	/**
	 * 查询条件：起始日期
	 */
	private int qsrq;
	
	public int getJzrq() {
		return jzrq;
	}

	public void setJzrq(int jzrq) {
		this.jzrq = jzrq;
	}

	public int getQsrq() {
		return qsrq;
	}

	public void setQsrq(int qsrq) {
		this.qsrq = qsrq;
	}

	/**
	 * 配送单时间
	 */
	private int sendDate;
	
	public int getSendDate() {
		return sendDate;
	}

	public void setSendDate(int sendDate) {
		this.sendDate = sendDate;
	}

	private List<YY155_REP_DETAIL> lstDeatil;
	private List<YY161_REP_DETAIL> lstState;

	public String getPsdbh() {
		return psdbh;
	}

	public void setPsdbh(String psdbh) {
		this.psdbh = psdbh;
	}

	public String getPsdh() {
		return psdh;
	}

	public void setPsdh(String psdh) {
		this.psdh = psdh;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public Integer getPsmxts() {
		return psmxts;
	}

	public void setPsmxts(Integer psmxts) {
		this.psmxts = psmxts;
	}

	public String getPsdzt() {
		return psdzt;
	}

	public void setPsdzt(String psdzt) {
		this.psdzt = psdzt;
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

	public List<YY155_REP_DETAIL> getLstDeatil() {
		return lstDeatil;
	}

	public void setLstDeatil(List<YY155_REP_DETAIL> lstDeatil) {
		this.lstDeatil = lstDeatil;
	}

	public List<YY161_REP_DETAIL> getLstState() {
		return lstState;
	}

	public void setLstState(List<YY161_REP_DETAIL> lstState) {
		this.lstState = lstState;
	}

	public String getQymc() {
		return qymc;
	}

	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
}
