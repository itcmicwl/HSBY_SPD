package gyqx.spdherp.stockout.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BigBatch4FillVo {
    private String surCode;
    private String bigBatchCode;//大批号或唯一码
    private BigDecimal qty;
}
