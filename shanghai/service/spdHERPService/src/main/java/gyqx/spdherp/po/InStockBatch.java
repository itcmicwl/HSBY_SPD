
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 入库单批次表
 */
@Table(name = "in_stock_batch")
public class InStockBatch implements IBean {
    /**
     * ID
     */
    @Id
    @Size(max = 36)
    @Column(name = "id")
    @Title("ID")
    private String id;
    /**
     * PID
     */
    @Size(max = 36)
    @Column(name = "pid")
    @Title("PID")
    private String pid;
    /**
     * 单号
     */
    @Size(max = 36)
    @Column(name = "bill_id")
    @Title("单号")
    private String billId;
    /**
     * PROWID
     */
    @Column(name = "p_row_id")
    @Title("PROWID")
    private int pRowId;
    /**
     * 供应商ID
     */
    @Size(max = 36)
    @Column(name = "prov_id")
    @Title("供应商ID")
    private String provId;
    /**
     * 产品ID
     */
    @Size(max = 36)
    @Column(name = "goods_id")
    @Title("产品ID")
    private String goodsId;
    /**
     * 产品批次
     */
    @Size(max = 64)
    @Column(name = "goods_batch_id")
    @Title("产品批次")
    private String goodsBatchId;
    /**
     * 入库单价
     */
    @Column(name = "in_price")
    @Title("入库单价")
    private java.math.BigDecimal inPrice;
    /**
     * 入库时间
     */
    @Column(name = "in_time")
    @Title("入库时间")
    private java.util.Date inTime;
    /**
     * 数量
     */
    @Column(name = "qty")
    @Title("数量")
    private java.math.BigDecimal qty;
    /**
     * 大批次
     */
    @Size(max = 2000)
    @Column(name = "big_batch_code")
    @Title("大批次")
    private String bigBatchCode;
    /**
     * 最后更新时间
     */
    @Column(name = "last_update_datetime")
    @Title("最后更新时间")
    private java.util.Date lastUpdateDatetime;
    /**
     * 数据版本
     */
    @Column(name = "version")
    @Title("数据版本")
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public int getPRowId() {
        return pRowId;
    }

    public void setPRowId(int pRowId) {
        this.pRowId = pRowId;
    }

    public int getpRowId() {
        return pRowId;
    }

    public void setpRowId(int pRowId) {
        this.pRowId = pRowId;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsBatchId() {
        return goodsBatchId;
    }

    public void setGoodsBatchId(String goodsBatchId) {
        this.goodsBatchId = goodsBatchId;
    }

    public java.math.BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(java.math.BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    public java.util.Date getInTime() {
        return inTime;
    }

    public void setInTime(java.util.Date inTime) {
        this.inTime = inTime;
    }

    public java.math.BigDecimal getQty() {
        return qty;
    }

    public void setQty(java.math.BigDecimal qty) {
        this.qty = qty;
    }

    public String getBigBatchCode() {
        return bigBatchCode;
    }

    public void setBigBatchCode(String bigBatchCode) {
        this.bigBatchCode = bigBatchCode;
    }

    public java.util.Date getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(java.util.Date lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }



    @Override
    public String toString() {
        return "InStockBatch [" + " id=" + id + ", pid=" + pid + ", billId=" + billId + ", pRowId=" + pRowId + ", provId=" + provId + ", goodsId=" + goodsId + ", goodsBatchId=" + goodsBatchId + ", inPrice=" + inPrice + ", inTime=" + inTime + ", qty=" + qty + ", bigBatchCode=" + bigBatchCode + ", lastUpdateDatetime=" + lastUpdateDatetime + ", version=" + version
                + "]";
    }

}