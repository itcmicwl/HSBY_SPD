package gyqx.ws.yg.vo.repVo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import gyqx.ws.yg.vo.RepHead;

/**
 * 企业信息获取(YY164) Created by moonb on 2017/11/22.
 */
@XmlType(propOrder = { "repHead", "main", "detail" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "XMLDATA")
public class YY164_REP_XML {

	@XmlElement(name = "HEAD")
	private RepHead repHead;
	@XmlElement(name = "MAIN")
	private YY164_REP_MAIN main;
	@XmlElementWrapper(name = "DETAIL")
	@XmlElement(name = "STRUCT")
	private List<YY164_REP_DETAIL> detail;

	public RepHead getRepHead() {
		return repHead;
	}

	public YY164_REP_MAIN getMain() {
		return main;
	}

	public List<YY164_REP_DETAIL> getDetail() {
		return detail;
	}

	public void setRepHead(RepHead repHead) {
		this.repHead = repHead;
	}

	public void setMain(YY164_REP_MAIN main) {
		this.main = main;
	}

	public void setDetail(List<YY164_REP_DETAIL> detail) {
		this.detail = detail;
	}

}
