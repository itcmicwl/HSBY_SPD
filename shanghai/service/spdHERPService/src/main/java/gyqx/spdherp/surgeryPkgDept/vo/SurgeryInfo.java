package gyqx.spdherp.surgeryPkgDept.vo;
import gyqx.spdherp.po.SurgeryPkg;
import lombok.Data;

import java.util.Date;
@Data
public class SurgeryInfo extends SurgeryPkg{

//    子表信息
    /**
     * 产品ID
     */
    private String goodsId ;
    /**
     * 唯一码类型
     */
    private int uniqueKind ;
    /**
     * 状态
     */
    private int subStatus ;
    /**
     * 商品数量
     */
    private java.math.BigDecimal subQty ;
    /**
     * 单位
     */
    private String subUnit ;
    /**
     * 消毒人
     */
    private String firstSterilizer ;
    /**
     * 消毒时间
     */
    private Date firstSterilizeDate ;
    /**
     * 消毒效期
     */
    private Date sterilizeExpdate ;
    /**
     * 退货消毒人
     */
    private String secSterilizer ;
    /**
     * 退货消毒时间
     */
    private Date secSterilizeDate ;

    private String goodsName;
    private String goodsGg;
    private String provId;
    private String provCode;
    private String provName;
    private String hosName;
    private String made;
    private String mfrsId;
    private String mfrsName;
    private String brand;
    private String certificateCode;
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

//    批次表信息
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

    private java.math.BigDecimal inPrice ;

}
