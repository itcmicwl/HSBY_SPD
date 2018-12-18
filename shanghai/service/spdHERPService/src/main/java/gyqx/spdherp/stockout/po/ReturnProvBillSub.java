package gyqx.spdherp.stockout.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Liangwu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnProvBillSub implements Serializable {

    /**
     * 主键
     * ID
     * isNullAble:0
     */
    private String id;

    /**
     * 退货单号
     * isNullAble:0
     */
    private String billId;

    /**
     * 行号
     * isNullAble:0
     */
    private Integer rowNum;

    /**
     * 来源单据号
     * isNullAble:1
     */
    private String sourceBillId;

    /**
     * 来源单据行号
     * isNullAble:1
     */
    private Integer sourceBillRow;

    /**
     * 供应商品种ID
     * isNullAble:1
     */
    private String provGoodsId;

    /**
     * 医院品种ID
     * isNullAble:1
     */
    private String hosGoodsId;

    /**
     * 医院品种名称
     * isNullAble:1
     */
    private String hosGoodsName;

    /**
     * 供应商产品ERPcode
     * isNullAble:1
     */
    private String erpGoodsCode;

    /**
     * 规格
     * isNullAble:1
     */
    private String goodsGg;

    /**
     * 厂家ID
     * isNullAble:1
     */
    private String mrfsId;

    /**
     * 厂家名称
     * isNullAble:1
     */
    private String mfrsName;

    /**
     * 产地
     * isNullAble:1
     */
    private String made;

    /**
     * 退货数量
     * isNullAble:1
     */
    private java.math.BigDecimal qty;

    /**
     * 计量单位
     * isNullAble:1
     */
    private String unit;

    /**
     * 唯一码类型
     * isNullAble:1
     */
    private Integer uniqueKind;

    /**
     * 批号
     * isNullAble:1
     */
    private String batchCode;

    /**
     * 灭菌效期
     * isNullAble:1
     */
    private Date sterilizationEndDate;

    /**
     * 灭菌批号
     * isNullAble:1
     */
    private String sterilizationCode;

    /**
     * 灭菌日期
     * isNullAble:1
     */
    private Date sterilizationDate;

    /**
     * 效期
     * isNullAble:1
     */
    private Date expdtEndDate;

    /**
     * 生产日期
     * isNullAble:1
     */
    private Date productDate;

    /**
     * 注册证号
     * isNullAble:1
     */
    private String certificateCode;

    /**
     * 最后更新时间
     * isNullAble:0
     */
    private Date lastUpdateDatetime;

    /**
     * 数据版本
     * isNullAble:0
     */
    private Integer version;
}
