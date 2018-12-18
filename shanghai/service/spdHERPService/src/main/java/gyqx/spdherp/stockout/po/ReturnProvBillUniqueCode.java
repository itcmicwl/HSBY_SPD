package gyqx.spdherp.stockout.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Liangwu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnProvBillUniqueCode {

    /**
     * 主键
     * ID
     * isNullAble:0
     */
    private String id;

    /**
     * PID
     * isNullAble:1
     */
    private String pid;

    /**
     * 单号
     * isNullAble:1
     */
    private String billId;

    /**
     * PROWID
     * isNullAble:1
     */
    private Integer pRowId;

    /**
     * 供应商ID
     * isNullAble:1
     */
    private String provId;

    /**
     * 产品ID
     * isNullAble:1
     */
    private String goodsId;

    /**
     * 产品批次
     * isNullAble:1
     */
    private String goodsBatchId;

    /**
     * 唯一码
     * isNullAble:1
     */
    private String uniqueCode;

    /**
     * 最后更新时间
     * isNullAble:1
     */
    private Date lastUpdateDatetime;

    /**
     * 数据版本
     * isNullAble:1
     */
    private Integer version;
}
