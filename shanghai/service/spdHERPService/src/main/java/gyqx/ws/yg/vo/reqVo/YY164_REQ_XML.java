package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import gyqx.ws.yg.vo.ReqHead;

/**
 * YY164获取企业信息
 * Created by moonb on 2017/12/6.
 */
@XmlType(propOrder = { "reqHead", "main"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "XMLDATA")
public class YY164_REQ_XML{
    @XmlElement(name = "HEAD")
    private ReqHead reqHead;
    @XmlElement(name = "MAIN")
    private YY164_REQ_MAIN main;
    public ReqHead getReqHead() {
        return reqHead;
    }

    public void setReqHead(ReqHead reqHead) {
        this.reqHead = reqHead;
    }

    public YY164_REQ_MAIN getMain() {
        return main;
    }

    public void setMain(YY164_REQ_MAIN main) {
        this.main = main;
    }
}
