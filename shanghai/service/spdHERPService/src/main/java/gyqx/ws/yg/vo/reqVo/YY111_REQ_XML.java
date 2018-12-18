package gyqx.ws.yg.vo.reqVo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import gyqx.ws.yg.vo.ReqHead;

/**
 * Created by moonb on 2017/12/5.
 */
@XmlType(propOrder = { "reqHead", "main","detail"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "XMLDATA")
public class YY111_REQ_XML{
    @XmlElement(name = "HEAD")
    private ReqHead reqHead;
    @XmlElement(name = "MAIN")
    private YY111_REQ_MAIN main;
    @XmlElementWrapper(name = "DETAIL")
    @XmlElement(name = "STRUCT")
    private List<YY111_REQ_DETAIL> detail;
    public ReqHead getReqHead() {
        return reqHead;
    }

    public void setReqHead(ReqHead reqHead) {
        this.reqHead = reqHead;
    }

    public YY111_REQ_MAIN getMain() {
        return main;
    }

    public void setMain(YY111_REQ_MAIN main) {
        this.main = main;
    }

    public List<?> getDetail() {
        return detail;
    }

    public void setDetail(List<YY111_REQ_DETAIL> detail) {
        this.detail = detail;
    }
}


