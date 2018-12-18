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
 * Created by moonb on 2017/12/6.
 */
@XmlType(propOrder = { "repHead", "main", "detail" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "XMLDATA")
public class YY111_REP_XML {
	@XmlElement(name = "HEAD")
	private RepHead repHead;
	@XmlElement(name = "MAIN")
	private YY111_REP_MAIN main;
	@XmlElementWrapper(name = "DETAIL")
	@XmlElement(name = "STRUCT")
	private List<YY111_REP_DETAIL> detail;

	public RepHead getRepHead() {
		return repHead;
	}

	public void setRepHead(RepHead repHead) {
		this.repHead = repHead;
	}

	public YY111_REP_MAIN getMain() {
		return main;
	}

	public void setMain(YY111_REP_MAIN main) {
		this.main = main;
	}

	public List<YY111_REP_DETAIL> getDetail() {
		return detail;
	}

	public void setDetail(List<YY111_REP_DETAIL> detail) {
		this.detail = detail;
	}
}
