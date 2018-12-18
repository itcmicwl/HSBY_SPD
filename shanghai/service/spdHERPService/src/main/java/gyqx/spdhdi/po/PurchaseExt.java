package gyqx.spdhdi.po;
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
 * 采购单扩展表(PurchaseExt)实体类
 *
 * @author moonbless
 * @since 2018-09-29 10:36:41
 */
public class PurchaseExt implements IBean {
        //ID    
    @Id
    @Size(max=36)
    @Column(name="id")    
    @Title("ID")
    private String id;
        //采购单明细ID    
    @Size(max=36)
    @Column(name="pid")    
    @Title("采购单明细ID")
    private String pid;
    //请购单号    
    @Size(max=36)
    @Column(name="apply_bill_id")    
    @Title("请购单号")
    private String applyBillId;
    //请购单行号    
    @Size(max=11)
    @Column(name="apply_bill_row")    
    @Title("请购单行号")
    private Integer applyBillRow;
    //产品ID    
    @Size(max=36)
    @Column(name="goods_id")    
    @Title("产品ID")
    private String goodsId;
    //请购数量    
    @Size(max=18)
    @Column(name="qty")    
    @Title("请购数量")
    private BigDecimal qty;
    //单位    
    @Size(max=36)
    @Column(name="unit")    
    @Title("单位")
    private String unit;
    //备注信息    
    @Size(max=36)
    @Column(name="remark")    
    @Title("备注信息")
    private String remark;
    //供应商    
    @Size(max=-1)
    @Column(name="last_update_datetime")    
    @Title("供应商")
    private Date lastUpdateDatetime;
    //配送关系类型    
    @Size(max=11)
    @Column(name="version")    
    @Title("配送关系类型")
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
    
    public String getApplyBillId() {
        return applyBillId;
    }

    public void setApplyBillId(String applyBillId) {
        this.applyBillId = applyBillId;
    }
    
    public Integer getApplyBillRow() {
        return applyBillRow;
    }

    public void setApplyBillRow(Integer applyBillRow) {
        this.applyBillRow = applyBillRow;
    }
    
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    
    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
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
    
    public Date getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(Date lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }
    
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}