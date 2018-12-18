package gyqx.spdherp.po;
import java.math.BigDecimal;
import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 手术包请购单表(ApplySurgeryPkg)实体类
 *
 * @author moonbless
 * @since 2018-10-08 11:13:29
 */
@Table(name="apply_surgery_pkg")
public class ApplySurgeryPkg implements IBean {
        //ID    
    @Id
    @Size(max=36)
    @Column(name="id")    
    @Title("ID")
    private String id;
        //请购单ID    
    @Size(max=36)
    @Column(name="bill_id")    
    @Title("请购单ID")
    private String billId;
    //手术包配置ID    
    @Size(max=36)
    @Column(name="sur_id")    
    @Title("手术包配置ID")
    private String surId;
    //请购包数    
    @Size(max=18)
    @Column(name="apply_qty")    
    @Title("请购包数")
    private BigDecimal applyQty;
    //已打包数    
    @Size(max=18)
    @Column(name="qty")    
    @Title("已打包数")
    private BigDecimal qty;
    //数量    
    @Size(max=11)
    @Column(name="status")    
    @Title("数量")
    private Integer status;
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
    
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }
    
    public String getSurId() {
        return surId;
    }

    public void setSurId(String surId) {
        this.surId = surId;
    }
    
    public BigDecimal getApplyQty() {
        return applyQty;
    }

    public void setApplyQty(BigDecimal applyQty) {
        this.applyQty = applyQty;
    }
    
    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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