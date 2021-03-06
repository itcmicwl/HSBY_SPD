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
 * 耗材采购明细获取 YY151
 * 
 * @author LIWENKANG
 *
 */
@XmlType(propOrder = { "repHead", "main", "detail" })
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "XMLDATA")
public class YY151_REP_XML {
	/**
	 * 消息头
	 */
	@XmlElement(name = "HEAD")
	private RepHead repHead;
	/**
	 * 消息主条目
	 */
	@XmlElement(name = "MAIN")
	private YY151_REP_MAIN main;
	/**
	 * 消息明细条目
	 */
	@XmlElementWrapper(name = "DETAIL")
	@XmlElement(name = "STRUCT")
	private List<YY151_REP_DETAIL> detail;

	public RepHead getRepHead() {
		return repHead;
	}

	public void setRepHead(RepHead repHead) {
		this.repHead = repHead;
	}

	public YY151_REP_MAIN getMain() {
		return main;
	}

	public void setMain(YY151_REP_MAIN main) {
		this.main = main;
	}

	public List<YY151_REP_DETAIL> getDetail() {
		return detail;
	}

	public void setDetail(List<YY151_REP_DETAIL> detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "YY151_REP_XML [repHead=" + repHead + ", main=" + main + ", detail=" + detail + "]";
	}

}
