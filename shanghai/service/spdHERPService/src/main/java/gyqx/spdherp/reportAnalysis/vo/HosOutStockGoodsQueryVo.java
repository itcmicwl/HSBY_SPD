package gyqx.spdherp.reportAnalysis.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class HosOutStockGoodsQueryVo {

    /**
     * 入库部门Id 列表
     */
    private List<String> deptIds;

    /**
     * 注册证号
     */
    private String certificateCode;

    /**
     * 医院Id
     */
    private String hosId;

    /**
     * 出库单号
     */
    private String billId;

    /**
     * 单据日期
     */
    private Date fillDate;

    /**
     * 审核日期
     */
    private Date auditDate;

    /**
     * 查询开始日期
     */
    @JsonProperty("sDate")
    private Date sDate;

    /**
     * 查询结束日期
     */
    @JsonProperty("eDate")
    private Date eDate;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品Id
     */
    private String goodsId;

    /**
     * 商品规格
     */
    private String goodsGg;

    /**
     * 计量单位
     */
    private String unit;

    /**
     * 出库部门Id
     */
    private String outDeptId;

    /**
     * 出库部门
     */
    private String outDeptName;

    /**
     * 入库部门Id
     */
    private String inDeptId;

    /**
     * 入库部门
     */
    private String inDeptName;

    /**
     * 出库单价
     */
    private BigDecimal price;

    /**
     * 出库数量
     */
    private String outQty;

    /**
     * 出库金额
     */
    private BigDecimal outAmount;

    /**
     * 状态
     */
    private int status;

    /**
     * 出库类型
     */
    private int outStockKind;

    /**
     * 出库方式，10--实库，20--虚库
     */
    private int outStockKType;

    /**
     * SO单号
     */
    private String saleBillId;

}
