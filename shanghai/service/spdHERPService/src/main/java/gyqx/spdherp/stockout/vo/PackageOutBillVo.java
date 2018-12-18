package gyqx.spdherp.stockout.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import common.db.meta.Title;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Created by cjzyw on 2018/9/5.
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageOutBillVo {
    private String id;
    /**
     * 包号
     */
    private String packageId;

    private int rowId;

    private String provId;

    private String provName;

    private String hosId;

    private String hosName;

    private String goodsId;

    private String goodsName;

    private String goodsGg;

    private String mfrsId;

    private String mfrsName;

    private String made;

    private java.math.BigDecimal packetCode;

    private String batchCode;
    /**
     * 灭菌日期
     */
    private java.util.Date sterilizationDate;
    /**
     * 灭菌批号
     */
    private String sterilizationCode;
    /**
     * 灭菌效期
     */
    private java.util.Date sterilizationEndDate;
    /**
     * 有效期至
     */
    private java.util.Date expdtEndDate;

    private String unit;
    /**
     * 主表数量
     */
    private java.math.BigDecimal qty;
    /**
     * 整包数量数
     */
    private java.math.BigDecimal packetQty;
    /**
     * 单号
     */
    private String billId;
    /**
     * 注册证号
     */
    private String certificateCode;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 最后更新时间
     */
    private java.util.Date lastUpdateDatetime;

    private int version;

    //    子表字段
    private int prowId;
    /**
     * 产品批次
     */
    private String goodsBatchId;

    private java.math.BigDecimal inPrice;

    private java.util.Date inTime;

    private java.math.BigDecimal detailQty;

    private String bigBatchCode;
}
