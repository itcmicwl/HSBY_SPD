package gyqx.spdherp.stockout.vo;

import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * @Auther Liangwu
 * @Date 17-10-09 11:06
 */
public class OutStockBillBriefVo {
    /**
     * ID
     */
    @Id
    @Size(max = 36)
    @Column(name = "id")
    @Title("ID")
    private String id;
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
     * 出库科室名称
     */
    @Size(max = 200)
    @Column(name = "out_dept_name")
    @Title("出库科室名称")
    private String outDeptName;
    /**
     * 状态
     */
    @Column(name = "status")
    @Title("状态")
    private Integer status;
    /**
     * 数据版本
     */
    @Column(name = "version")
    @Title("数据版本")
    private int version;

    private Integer goodsCount;

    private Double goodsSum;

    private Double amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutStockKind() {
        return outStockKind;
    }

    public void setOutStockKind(String outStockKind) {
        this.outStockKind = outStockKind;
    }

    public Integer getOutStockType() {
        return outStockType;
    }

    public void setOutStockType(Integer outStockType) {
        this.outStockType = outStockType;
    }

    public String getOutDeptName() {
        return outDeptName;
    }

    public void setOutDeptName(String outDeptName) {
        this.outDeptName = outDeptName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Double getGoodsSum() {
        return goodsSum;
    }

    public void setGoodsSum(Double goodsSum) {
        this.goodsSum = goodsSum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
