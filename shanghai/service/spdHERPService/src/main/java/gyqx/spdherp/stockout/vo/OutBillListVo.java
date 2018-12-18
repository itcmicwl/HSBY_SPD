package gyqx.spdherp.stockout.vo;

import common.db.meta.Title;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockList;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OutBillListVo extends OutStock implements Serializable {
    /**
     * 出库单制作开始时间，设置查询范围用于查询
     */
    private java.util.Date startDate;
    private String startDateStr;
    /**
     * 出库单制作结束时间，设置查询范围用于查询
     */
    private java.util.Date endDate;
    private String endDateStr;
    //单据状态
    private String billStatus;

    private String goodsNames;
    //制单人
    private String fillerName;

    private String shortPinYin;

    private List<OutStockList> lstOutStock;

    public String getShortPinYin() {
        if (shortPinYin != null) {
            return shortPinYin.trim();
        }
        return shortPinYin;
    }

    public String getGoodsNames() {
        if (goodsNames != null) {
            return goodsNames.trim();
        }
        return goodsNames;
    }

    public void setGoodsNames(String goodsNames) {

        this.goodsNames = goodsNames;
    }

    public void setShortPinYin(String shortPinYin) {
        this.shortPinYin = shortPinYin;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public List<OutStockList> getLstOutStock() {
        return lstOutStock;
    }

    public void setLstOutStock(List<OutStockList> lstOutStock) {
        this.lstOutStock = lstOutStock;
    }

    public String getFillerName() {
        return fillerName;
    }

    public void setFillerName(String fillerName) {
        this.fillerName = fillerName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
