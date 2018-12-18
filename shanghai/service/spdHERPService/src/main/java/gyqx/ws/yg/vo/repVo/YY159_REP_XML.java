package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.*;

import gyqx.ws.yg.vo.RepHead;

import java.util.List;

/**
 * 耗材按采购单获取采购明细状态(YY159) Created by moonb on 2017/11/22.
 */
@XmlType(propOrder = { "repHead", "main", "detail" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "XMLDATA")
public class YY159_REP_XML {
	@XmlElement(name = "HEAD")
	private RepHead repHead;
	@XmlElement(name = "MAIN")
	private YY159_REP_MAIN main;
	@XmlElementWrapper(name = "DETAIL")
	@XmlElement(name = "STRUCT")
	private List<YY159_REP_DETAIL> detail;

	public RepHead getRepHead() {
		return repHead;
	}

	public YY159_REP_MAIN getMain() {
		return main;
	}

	public List<YY159_REP_DETAIL> getDetail() {
		return detail;
	}

	public void setRepHead(RepHead repHead) {
		this.repHead = repHead;
	}

	public void setMain(YY159_REP_MAIN main) {
		this.main = main;
	}

	public void setDetail(List<YY159_REP_DETAIL> detail) {
		this.detail = detail;
	}

}
