package gyqx.spdherp.barcodePrintingCenter.vo;

import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.stockout.vo.vo4In.OutSub4InVo;

public class Undo {
    private String goodsId;
    private String batchCode; //批号
    private String goodsBatchId; //批次
    private int qty;  //数量
    private int pcode; // 整包数量
    private OutSub4InVo outSubObj;
    private OutStockBatch outBatchObj;

    @Override
    public String toString() {
        return "Undo{" +
                "goodsId='" + goodsId + '\'' +
                ", batchCode='" + batchCode + '\'' +
                ", goodsBatchId='" + goodsBatchId + '\'' +
                ", qty=" + qty +
                ", pcode=" + pcode +
                ", outSubObj=" + outSubObj +
                ", outBatchObj=" + outBatchObj +
                '}';
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getGoodsBatchId() {
        return goodsBatchId;
    }

    public void setGoodsBatchId(String goodsBatchId) {
        this.goodsBatchId = goodsBatchId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPcode() {
        return pcode;
    }

    public void setPcode(int pcode) {
        this.pcode = pcode;
    }

    public OutSub4InVo getOutSubObj() {
        return outSubObj;
    }

    public void setOutSubObj(OutSub4InVo outSubObj) {
        this.outSubObj = outSubObj;
    }

    public OutStockBatch getOutBatchObj() {
        return outBatchObj;
    }

    public void setOutBatchObj(OutStockBatch outBatchObj) {
        this.outBatchObj = outBatchObj;
    }
}
