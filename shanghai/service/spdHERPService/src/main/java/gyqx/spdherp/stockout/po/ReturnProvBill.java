package gyqx.spdherp.stockout.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

/**
*
*  @author Liangwu
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnProvBill {

    /**
    * 主键
    * ID
    * isNullAble:0
    */
    private String id;

    /**
    * 来源单据号
    * isNullAble:1
    */
    private String sourceBillId;

    /**
    * 退货类型,10:验收退货，20,库房退货
    * isNullAble:1
    */
    private Integer returnKind;

    /**
    * 医院id
    * isNullAble:0
    */
    private String hosId;

    /**
    * 医院名称
    * isNullAble:0
    */
    private String hosName;

    /**
    * 退货科室
    * isNullAble:1
    */
    private String deptId;

    /**
    * 退货科室名称
    * isNullAble:1
    */
    private String deptName;

    /**
    * 退货库房ID
    * isNullAble:1
    */
    private String stocId;

    /**
    * 供应商ERPcode
    * isNullAble:1
    */
    private String provCode;

    /**
    * 供应商
    * isNullAble:1
    */
    private String provId;

    /**
    * 供应商名称
    * isNullAble:1
    */
    private String provName;

    /**
    * 状态,-1 已作废,0初始，20 已审核
    * isNullAble:1
    */
    private Integer status;

    /**
    * 制单日期
    * isNullAble:1
    */
    private Date fillDate;

    /**
    * 制单人ID
    * isNullAble:1
    */
    private String filler;

    /**
    * 制单人名称
    * isNullAble:1
    */
    private String fillerName;

    /**
    * 审核人
    * isNullAble:1
    */
    private String auditor;

    /**
    * 供应商确认人
    * isNullAble:1
    */
    private String confirmer;

    /**
    * 备注
    * isNullAble:1
    */
    private String remark;

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
