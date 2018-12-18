package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.*;

/**
 * Created by moonb on 2017/12/6.
 */
@XmlType(propOrder = { "orderNo"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
public class YY111_REP_MAIN {
    @XmlElement(name = "DDBH")
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
