package gyqx.spdherp.stockout.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import common.db.meta.Title;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-25 下午8:24
 */
@JsonIgnoreProperties
@Data
public class OutStockBillVo {
    /**
     * ID
     */
    @Id
    @Size(max = 36)
    @Column(name = "id")
    @Title("ID")
    private String id;
    /**
     * 单号
     */
    @Size(max = 36)
    @Column(name = "bill_id")
    @Title("单号")
    private String billId;
    /**
     * 出库类型
     */
    @Size(max = 10)
    @Column(name = "out_stock_kind")
    @Title("出库类型")
    private String outStockKind;
    /**
     * 出库方式
     * 10--实采，20--虚采
     */
    @Column(name = "out_stock_type")
    @Title("出库方式")
    private Integer outStockType;
    /**
     * 出库机构ID
     */
    @Size(max = 36)
    @Column(name = "out_org_id")
    @Title("出库机构ID")
    private String outOrgId;
    /**
     * 出库机构名称
     */
    @Size(max = 200)
    @Column(name = "out_org_name")
    @Title("出库机构名称")
    private String outOrgName;
    /**
     * 出库科室ID
     */
    @Size(max = 36)
    @Column(name = "out_dept_id")
    @Title("出库科室ID")
    private String outDeptId;
    /**
     * 出库科室名称
     */
    @Size(max = 200)
    @Column(name = "out_dept_name")
    @Title("出库科室名称")
    private String outDeptName;
    /**
     * 出库库房
     */
    @Size(max = 36)
    @Column(name = "out_stoc_id")
    @Title("出库库房")
    private String outStocId;
    /**
     * 入库机构ID
     */
    @Size(max = 36)
    @Column(name = "in_org_id")
    @Title("入库机构ID")
    private String inOrgId;
    /**
     * 入库机构名称
     */
    @Size(max = 200)
    @Column(name = "in_org_name")
    @Title("入库机构名称")
    private String inOrgName;
    /**
     * 入库科室ID
     */
    @Size(max = 36)
    @Column(name = "in_dept_id")
    @Title("入库科室ID")
    private String inDeptId;
    /**
     * 入库科室名称
     */
    @Size(max = 200)
    @Column(name = "in_dept_name")
    @Title("入库科室名称")
    private String inDeptName;
    /**
     * 制单日期
     */
    @Column(name = "fill_date")
    @Title("制单日期")
    private Date fillDate;
    /**
     * 制单人
     */
    @Size(max = 36)
    @Column(name = "filler")
    @Title("制单人")
    private String filler;
    /**
     * 审核人
     */
    @Size(max = 36)
    @Column(name = "auditor")
    @Title("审核人")
    private String auditor;
    /**
     * 审核时间
     */
    @Column(name = "audit_date")
    @Title("审核时间")
    private Date auditDate;
    /**
     * 记账人
     */
    @Size(max = 36)
    @Column(name = "accounter")
    @Title("记账人")
    private String accounter;
    /**
     * 记账时间
     */
    @Column(name = "account_date")
    @Title("记账时间")
    private Date accountDate;
    /**
     * 收货地址ID
     */
    @Size(max = 36)
    @JoinColumn(name = "rec_address_id", table = "receive_address", referencedColumnName = "id")
    @Title("收货地址ID")
    private String recAddressId;
    /**
     * 联系人
     */
    @Size(max = 80)
    @JoinColumn(name = "rec_linkman", table = "receive_address", referencedColumnName = "lxr")
    @Title("联系人")
    private String recLinkman;
    /**
     * 联系电话
     */
    @Size(max = 100)
    @JoinColumn(name = "rec_linkman_phone", table = "receive_address", referencedColumnName = "lxr_phone")
    @Title("联系电话")
    private String recLinkmanPhone;
    /**
     * 收货地址
     */
    @Size(max = 500)
    @JoinColumn(name = "rec_address", table = "receive_address", referencedColumnName = "address")
    @Title("收货地址")
    private String recAddress;
    /**
     * 状态
     */
    @Column(name = "status")
    @Title("状态")
    private Integer status;
    /**
     * 费用类型
     */
    @Column(name = "cost_kind")
    @Title("费用类型")
    private Integer costKind;
    /**
     * 患者姓名
     */
    @Size(max = 50)
    @Column(name = "patient_name")
    @Title("费用类型")
    private String patientName;
    /**
     * 数据版本
     */
    @Column(name = "version")
    @Title("数据版本")
    private int version;
    /**
     * 传输标志
     * [0 不需要；1 需传输 ; 2已传输]
     */
    @Column(name="trans_flag")
    @Title("传输标志")
    private Integer transFlag ;

    private int goodsCount;

    private Double goodsSum;

    private Double amount;

    private String stocLevel;
    
    private String auditorName;
    
    private String accounterName;

    private String st;//100:退货至供应商 默认为null

	private List<RequestStockGoodsVo> goodsList;

    private List<OutStockBillSubVo> subList;

    @JsonIgnore
    public List<RequestStockGoodsVo> getGoodsList() {
        return goodsList;
    }

    @JsonProperty
    public void setGoodsList(List<RequestStockGoodsVo> goodsList) {
        this.goodsList = goodsList;
    }

}
