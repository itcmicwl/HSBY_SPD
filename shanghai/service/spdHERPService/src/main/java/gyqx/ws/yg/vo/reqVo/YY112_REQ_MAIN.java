package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by moonb on 2017/12/6.
 */
@XmlType(propOrder = { "ygHosCode", "addressCode", "purType", "purOrderNo", "sumRow"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY112_REQ_MAIN{
    @XmlElement(name = "YYBM")
    private String ygHosCode;
    @XmlElement(name = "PSDBM")
    private String addressCode;
    @XmlElement(name = "DDLX")
    private String purType;
    @XmlElement(name = "DDBH")
    private String purOrderNo;
    @XmlElement(name = "SPSL")
    private Integer sumRow;

    public String getYgHosCode() {
        return ygHosCode;
    }

    public void setYgHosCode(String ygHosCode) {
        this.ygHosCode = ygHosCode;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getPurType() {
        return purType;
    }

    public void setPurType(String purType) {
        this.purType = purType;
    }

    public String getPurOrderNo() {
        return purOrderNo;
    }

    public void setPurOrderNo(String purOrderNo) {
        this.purOrderNo = purOrderNo;
    }

    public Integer getSumRow() {
        return sumRow;
    }

    public void setSumRow(Integer sumRow) {
        this.sumRow = sumRow;
    }
}
