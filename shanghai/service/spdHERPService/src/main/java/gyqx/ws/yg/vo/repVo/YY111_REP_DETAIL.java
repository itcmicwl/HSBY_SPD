package gyqx.ws.yg.vo.repVo;

import javax.xml.bind.annotation.*;

/**
 * Created by moonb on 2017/12/6.
 */
@XmlType(propOrder = { "detailNo", "rowNumber","goodsYgCode","goddsYgxfCode","hosGoodsId","goodsStock","result","resRemark"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
public class YY111_REP_DETAIL {
    @XmlElement(name = "DDMXBH")
    private String detailNo;
    @XmlElement(name = "SXH")
    private Integer rowNumber;
    @XmlElement(name = "HCTBDM")
    private String goodsYgCode;
    @XmlElement(name = "HCXFDM")
    private String goddsYgxfCode;
    @XmlElement(name = "YYBDDM")
    private String hosGoodsId;
    @XmlElement(name = "QYKC")
    private String goodsStock;
    @XmlElement(name = "CLJG")
    private String result;
    @XmlElement(name = "CLQKMS")
    private String resRemark;

    public String getDetailNo() {
        return detailNo;
    }

    public void setDetailNo(String detailNo) {
        this.detailNo = detailNo;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getGoodsYgCode() {
        return goodsYgCode;
    }

    public void setGoodsYgCode(String goodsYgCode) {
        this.goodsYgCode = goodsYgCode;
    }

    public String getGoddsYgxfCode() {
        return goddsYgxfCode;
    }

    public void setGoddsYgxfCode(String goddsYgxfCode) {
        this.goddsYgxfCode = goddsYgxfCode;
    }

    public String getHosGoodsId() {
        return hosGoodsId;
    }

    public void setHosGoodsId(String hosGoodsId) {
        this.hosGoodsId = hosGoodsId;
    }

    public String getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(String goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResRemark() {
        return resRemark;
    }

    public void setResRemark(String resRemark) {
        this.resRemark = resRemark;
    }
}
