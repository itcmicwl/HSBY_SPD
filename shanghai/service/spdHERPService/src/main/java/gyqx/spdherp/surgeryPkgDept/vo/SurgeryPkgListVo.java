package gyqx.spdherp.surgeryPkgDept.vo;
import lombok.Data;
import javax.persistence.Column;
import java.util.Date;
import java.util.List;
@Data
public class SurgeryPkgListVo {
    private String id;
    /**
     * 手术包包码
     */
    private String surCode ;
    /**
     * 产品ID
     */
    @Column(name="goods_id")
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
    /**
     * 用于手术包界面显示
     */
    private String uniqueCode;
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
    private List<SurgeryPkgBatchVo> pkgBatchList;
}
