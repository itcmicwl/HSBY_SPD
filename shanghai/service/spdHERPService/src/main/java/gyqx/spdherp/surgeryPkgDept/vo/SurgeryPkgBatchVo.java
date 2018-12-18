package gyqx.spdherp.surgeryPkgDept.vo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurgeryPkgBatchVo {
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
     * 唯一码
     */
    private String uniqueCode ;
    /**
     * 批号
     */
    private String batchCode ;
    /**
     * 数量
     */
    private java.math.BigDecimal qty ;
    /**
     * 使用数量
     */
    private java.math.BigDecimal useQty ;
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
    /**
     * 灭菌日期
     */
    private java.util.Date sterilizationDate ;
    /**
     * 灭菌批号
     */
    private String sterilizationCode ;
    /**
     * 灭菌效期
     */
    private java.util.Date sterilizationEndDate ;
    /**
     * 有效期至
     */
    private java.util.Date expdtEndDate ;

}
