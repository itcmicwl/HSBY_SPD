package gyqx.spdherp.po;
import java.math.BigDecimal;
import java.util.Date;
import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 手术包订单批次表(SurgeryPkgBatch)实体类
 *
 * @author moonbless
 * @since 2018-10-12 16:46:43
 */
@Table(name="surgery_pkg_batch")
public class SurgeryPkgBatch implements IBean {
        //ID    
    @Id
    @Size(max=36)
    @Column(name="id")    
    @Title("ID")
    private String id;
        //手术打包码    
    @Size(max=36)
    @Column(name="sur_code")    
    @Title("手术打包码")
    private String surCode;
    //产品ID    
    @Size(max=36)
    @Column(name="goods_id")    
    @Title("产品ID")
    private String goodsId;
    //批次    
    @Size(max=36)
    @Column(name="batch_id")    
    @Title("批次")
    private String batchId;
    //大批次    
    @Size(max=256)
    @Column(name="big_batch_code")    
    @Title("大批次")
    private String bigBatchCode;
    //唯一码    
    @Size(max=36)
    @Column(name="unique_code")    
    @Title("唯一码")
    private String uniqueCode;
    //批号    
    @Size(max=36)
    @Column(name="batch_code")    
    @Title("批号")
    private String batchCode;
    //数量    
    @Size(max=18)
    @Column(name="qty")    
    @Title("数量")
    private BigDecimal qty;
    //使用数量    
    @Size(max=18)
    @Column(name="use_qty")    
    @Title("使用数量")
    private BigDecimal useQty;
        
    @Size(max=18)
    @Column(name="in_price")    
    @Title("${column.comment}")
    private BigDecimal inPrice;
        
    @Size(max=-1)
    @Column(name="sterilization_date")    
    @Title("${column.comment}")
    private Date sterilizationDate;
        
    @Size(max=-1)
    @Column(name="sterilization_end_date")    
    @Title("${column.comment}")
    private Date sterilizationEndDate;
        
    @Size(max=64)
    @Column(name="sterilization_code")    
    @Title("${column.comment}")
    private String sterilizationCode;
        
    @Size(max=-1)
    @Column(name="expdt_end_date")    
    @Title("${column.comment}")
    private Date expdtEndDate;
    //单位    
    @Size(max=36)
    @Column(name="unit")    
    @Title("单位")
    private String unit;
    //备注    
    @Size(max=256)
    @Column(name="remark")    
    @Title("备注")
    private String remark;
    //最后更新时间    
    @Size(max=3)
    @Column(name="last_update_datetime")    
    @Title("最后更新时间")
    private Object lastUpdateDatetime;
    //数据版本    
    @Size(max=11)
    @Column(name="version")    
    @Title("数据版本")
    private int version;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getSurCode() {
        return surCode;
    }

    public void setSurCode(String surCode) {
        this.surCode = surCode;
    }
    
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    
    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
    
    public String getBigBatchCode() {
        return bigBatchCode;
    }

    public void setBigBatchCode(String bigBatchCode) {
        this.bigBatchCode = bigBatchCode;
    }
    
    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
    
    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }
    
    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }
    
    public BigDecimal getUseQty() {
        return useQty;
    }

    public void setUseQty(BigDecimal useQty) {
        this.useQty = useQty;
    }
    
    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }
    
    public Date getSterilizationDate() {
        return sterilizationDate;
    }

    public void setSterilizationDate(Date sterilizationDate) {
        this.sterilizationDate = sterilizationDate;
    }
    
    public Date getSterilizationEndDate() {
        return sterilizationEndDate;
    }

    public void setSterilizationEndDate(Date sterilizationEndDate) {
        this.sterilizationEndDate = sterilizationEndDate;
    }
    
    public String getSterilizationCode() {
        return sterilizationCode;
    }

    public void setSterilizationCode(String sterilizationCode) {
        this.sterilizationCode = sterilizationCode;
    }
    
    public Date getExpdtEndDate() {
        return expdtEndDate;
    }

    public void setExpdtEndDate(Date expdtEndDate) {
        this.expdtEndDate = expdtEndDate;
    }
    
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Object getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(Object lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }
    
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}