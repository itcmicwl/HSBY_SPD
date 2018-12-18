package gyqx.spdherp.barcodePrintingCenter.vo;

public class DsGoodsVo {
    private String goodsId;
    private String goodsName;
    private String mfrsName;
    private String mfrsId;
    private String goodsGg;

    private String havePixed;//已打包
    private String billId; //请购出库单
    private String isPacket;
    private Integer status;
    private String outOrgId;
    private String outStockKind;
    private String packetCode;
    private String totalQty;
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getMfrsName() {
        return mfrsName;
    }

    public void setMfrsName(String mfrsName) {
        this.mfrsName = mfrsName;
    }

    public String getMfrsId() {
        return mfrsId;
    }

    public void setMfrsId(String mfrsId) {
        this.mfrsId = mfrsId;
    }

    public String getGoodsGg() {
        return goodsGg;
    }

    public void setGoodsGg(String goodsGg) {
        this.goodsGg = goodsGg;
    }

    public String getHavePixed() {
        return havePixed;
    }

    public void setHavePixed(String havePixed) {
        this.havePixed = havePixed;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getIsPacket() {
        return isPacket;
    }

    public void setIsPacket(String isPacket) {
        this.isPacket = isPacket;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOutOrgId() {
        return outOrgId;
    }

    public void setOutOrgId(String outOrgId) {
        this.outOrgId = outOrgId;
    }

    public String getOutStockKind() {
        return outStockKind;
    }

    public void setOutStockKind(String outStockKind) {
        this.outStockKind = outStockKind;
    }

    public String getPacketCode() {
        return packetCode;
    }

    public void setPacketCode(String packetCode) {
        this.packetCode = packetCode;
    }

    public String getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(String totalQty) {
        this.totalQty = totalQty;
    }

    @Override
    public String toString() {
        return "DsGoodsVo{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", mfrsName='" + mfrsName + '\'' +
                ", mfrsId='" + mfrsId + '\'' +
                ", goodsGg='" + goodsGg + '\'' +
                ", havePixed='" + havePixed + '\'' +
                ", billId='" + billId + '\'' +
                ", isPacket='" + isPacket + '\'' +
                ", status=" + status +
                ", outOrgId='" + outOrgId + '\'' +
                ", outStockKind='" + outStockKind + '\'' +
                ", packetCode='" + packetCode + '\'' +
                ", totalQty='" + totalQty + '\'' +
                '}';
    }
}
