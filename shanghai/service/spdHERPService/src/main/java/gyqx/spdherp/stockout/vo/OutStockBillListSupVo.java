package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockList;
import gyqx.spdherp.po.OutStockUniqueCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cjzyw on 2018/5/15.
 */
public class OutStockBillListSupVo extends OutStockList implements Serializable{
    private List<OutStockBatch> lOutStockBatch;
    private List<OutStockUniqueCode> lOutStockUniqueCode;

    public List<OutStockBatch> getlOutStockBatch() {
        return lOutStockBatch;
    }

    public void setlOutStockBatch(List<OutStockBatch> lOutStockBatch) {
        this.lOutStockBatch = lOutStockBatch;
    }

    public List<OutStockUniqueCode> getlOutStockUniqueCode() {
        return lOutStockUniqueCode;
    }

    public void setlOutStockUniqueCode(List<OutStockUniqueCode> lOutStockUniqueCode) {
        this.lOutStockUniqueCode = lOutStockUniqueCode;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
