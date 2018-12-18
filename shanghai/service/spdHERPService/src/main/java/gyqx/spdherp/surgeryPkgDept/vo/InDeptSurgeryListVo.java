package gyqx.spdherp.surgeryPkgDept.vo;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class InDeptSurgeryListVo {
    @Column(name="id")
    private String id;
    /**
     * 手术包包码
     */
    private String surCode ;
    /**
     * 产品ID
     */
    //@Column(name="goods_id")
    private String goodsId ;
    /**
     * 唯一码类型
     */
    private int uniqueKind ;
    /**
     * 状态
     */
    private int status ;
    /**
     * 商品数量
     */
    @Column(name="qty")
    private java.math.BigDecimal qty ;
    /**
     * 单位
     */
    private String unit ;
    /**
     * 是否需要消毒
     */
    private int shouldSterilize ;
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

    private String batchCode;

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

    private List<InDeptSurgeryBatchVo> surInfoBatchList;

    private List<InDeptSurgeryUniqueVo> surInfoUniqueList;
}
