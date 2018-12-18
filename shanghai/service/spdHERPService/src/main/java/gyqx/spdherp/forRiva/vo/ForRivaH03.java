package gyqx.spdherp.forRiva.vo;

import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockList;
import gyqx.spdherp.po.OutStockUniqueCode;
import java.util.List;

/**
 * Created by cjzyw on 2018/6/2.
 */
public class ForRivaH03 {
    private OutStock outStock;
    private List<OutStockList> outStockLists;
    private List<OutStockUniqueCode> outStockUniqueCodeList;

    public ForRivaH03(OutStock outStock, List<OutStockList> outStockLists, List<OutStockUniqueCode> outStockUniqueCodeList) {
        this.outStock = outStock;
        this.outStockLists = outStockLists;
        this.outStockUniqueCodeList = outStockUniqueCodeList;
    }

    public ForRivaH03() {
    }

    public OutStock getOutStock() {
        return outStock;
    }

    public void setOutStock(OutStock outStock) {
        this.outStock = outStock;
    }

    public List<OutStockList> getOutStockLists() {
        return outStockLists;
    }

    public void setOutStockLists(List<OutStockList> outStockLists) {
        this.outStockLists = outStockLists;
    }

    public List<OutStockUniqueCode> getOutStockUniqueCodeList() {
        return outStockUniqueCodeList;
    }

    public void setOutStockUniqueCodeList(List<OutStockUniqueCode> outStockUniqueCodeList) {
        this.outStockUniqueCodeList = outStockUniqueCodeList;
    }
}
