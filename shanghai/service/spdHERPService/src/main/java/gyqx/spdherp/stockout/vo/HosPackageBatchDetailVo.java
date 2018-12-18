package gyqx.spdherp.stockout.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import common.db.meta.Title;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Created by cjzyw on 2018/9/11.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HosPackageBatchDetailVo{
    /**
     * ID
     */
    @Id
    @Size(max=36)
    @Column(name="id")
    @Title("ID")
    private String id ;
    /**
     * 包号
     */
    @Size(max=50)
    @Column(name="p_package_id")
    @Title("包号")
    private String pPackageId ;
    /**
     * 包行号
     */
    @Column(name="row_id")
    @Title("包行号")
    private int rowId ;
    /**
     * 产品ID
     */
    @Size(max=36)
    @Column(name="goods_id")
    @Title("产品ID")
    private String goodsId ;
    /**
     * 产品批次
     */
    @Size(max=64)
    @Column(name="goods_batch_id")
    @Title("产品批次")
    private String goodsBatchId ;
    /**
     * 入库单价
     */
    @Column(name="in_price")
    @Title("入库单价")
    private java.math.BigDecimal inPrice ;
    /**
     * 入库时间
     */
    @Column(name="in_time")
    @Title("入库时间")
    private java.util.Date inTime ;
    /**
     * 数量
     */
    @Column(name="qty")
    @Title("数量")
    private java.math.BigDecimal qty ;
    /**
     * 大批次
     */
    @Size(max=2000)
    @Column(name="big_batch_code")
    @Title("大批次")
    private String bigBatchCode ;
    /**
     * 最后更新时间
     */
    @Column(name="last_update_datetime")
    @Title("最后更新时间")
    private java.util.Date lastUpdateDatetime ;
    /**
     * 数据版本
     */
    @Column(name="version")
    @Title("数据版本")
    private int version ;
}
