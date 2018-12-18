package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.*;

/**
 * Created by moonb on 2017/11/22.
 */
@XmlType(propOrder = { "opType", "hosCode", "addressCode", "purType", "purBillId", "applyBillId", "lastSendDate", "sumRowCount"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MAIN")
public class YY111_REQ_MAIN {
    @XmlElement(name = "CZLX")
    private String opType;
    @XmlElement(name = "YYBM")
    private String hosCode;
    @XmlElement(name = "PSDBM")
    private String addressCode;
    @XmlElement(name = "DDLX")
    private Integer purType;
    @XmlElement(name = "DDBH")
    private String purBillId;
    @XmlElement(name = "YYJHDH")
    private String applyBillId;
    @XmlElement(name = "ZWDHRQ")
    private String lastSendDate;
    @XmlElement(name = "JLS")
    private Integer sumRowCount;

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getHosCode() {
        return hosCode;
    }

    public void setHosCode(String hosCode) {
        this.hosCode = hosCode;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public Integer getPurType() {
        return purType;
    }

    public void setPurType(Integer purType) {
        this.purType = purType;
    }

    public String getPurBillId() {
        return purBillId;
    }

    public void setPurBillId(String purBillId) {
        this.purBillId = purBillId;
    }

    public String getApplyBillId() {
        return applyBillId;
    }

    public void setApplyBillId(String applyBillId) {
        this.applyBillId = applyBillId;
    }

    public String getLastSendDate() {
        return lastSendDate;
    }

    public void setLastSendDate(String lastSendDate) {
        this.lastSendDate = lastSendDate;
    }

    public Integer getSumRowCount() {
        return sumRowCount;
    }

    public void setSumRowCount(Integer sumRowCount) {
        this.sumRowCount = sumRowCount;
    }
}
