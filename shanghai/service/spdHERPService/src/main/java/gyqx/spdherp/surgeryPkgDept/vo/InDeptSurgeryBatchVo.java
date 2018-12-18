package gyqx.spdherp.surgeryPkgDept.vo;

import lombok.Data;

@Data
public class InDeptSurgeryBatchVo {
    private String id;
    /**
     * 手术打包码
     */
    private String surCode ;
    /**
     * 产品ID
     */
    private String goodsId ;
    /**
     * 批次
     */
    private String batchId ;
    /**
     * 大批次
     */
    private String bigBatchCode ;
    /**
     * 数量
     */
    private java.math.BigDecimal qty ;
    /**
     * 单位
     */
    private String unit ;
    /**
     * 备注
     */
    private String remark ;
    /**
     * 入库价格
     */
    private java.math.BigDecimal inPrice ;
}
