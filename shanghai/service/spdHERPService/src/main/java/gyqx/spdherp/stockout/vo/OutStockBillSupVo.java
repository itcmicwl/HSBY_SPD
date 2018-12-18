package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.po.OutStock;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cjzyw on 2018/5/15.
 */
public class OutStockBillSupVo extends OutStock implements Serializable{
    private List<OutStockBillListSupVo> lOutStockList;

    public List<OutStockBillListSupVo> getlOutStockList() {
        return lOutStockList;
    }

    public void setlOutStockList(List<OutStockBillListSupVo> lOutStockList) {
        this.lOutStockList = lOutStockList;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
