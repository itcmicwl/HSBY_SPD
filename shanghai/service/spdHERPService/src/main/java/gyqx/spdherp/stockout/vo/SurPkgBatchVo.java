package gyqx.spdherp.stockout.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author liangwu
 * @Date 18-10-8 下午1:51
 */
@Data
public class SurPkgBatchVo {
    private String id;
    @JsonIgnore
    private String goodsId;
    @JsonIgnore
    private String surId;
    @JsonIgnore
    private String surCode;
    private String batchId;
    private String bigBatchCode;
    private String uniqueCode;
    private String batchCode;
    private BigDecimal qty;
    @JsonIgnore
    private BigDecimal inPrice;
    @JsonIgnore
    private Date sterilizationDate;
    @JsonIgnore
    private Date sterilizationEndDate;
    @JsonIgnore
    private String sterilizationCode;
    @JsonIgnore
    private Date expdtEndDate;
    private String unit;
    private int version;
}
