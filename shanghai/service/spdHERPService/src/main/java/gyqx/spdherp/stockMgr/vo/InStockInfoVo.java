package gyqx.spdherp.stockMgr.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moonb on 2018/5/16.
 * 入库单信息（扁平结构）
 */
public class InStockInfoVo {
    List<InStockVo> lstInStock = new ArrayList<>();
    List<InStockListVo> lstInStockDetail = new ArrayList<>();
    List<InStockBatchVo> lstInStockBatch = new ArrayList<>();
    List<InStockUniqueCodeVo> lstInStockUnique = new ArrayList<>();

    public List<InStockVo> getLstInStock() {
        return lstInStock;
    }

    public void setLstInStock(List<InStockVo> lstInStock) {
        this.lstInStock = lstInStock;
    }

    public List<InStockListVo> getLstInStockDetail() {
        return lstInStockDetail;
    }

    public void setLstInStockDetail(List<InStockListVo> lstInStockDetail) {
        this.lstInStockDetail = lstInStockDetail;
    }

    public List<InStockBatchVo> getLstInStockBatch() {
        return lstInStockBatch;
    }

    public void setLstInStockBatch(List<InStockBatchVo> lstInStockBatch) {
        this.lstInStockBatch = lstInStockBatch;
    }

    public List<InStockUniqueCodeVo> getLstInStockUnique() {
        return lstInStockUnique;
    }

    public void setLstInStockUnique(List<InStockUniqueCodeVo> lstInStockUnique) {
        this.lstInStockUnique = lstInStockUnique;
    }
}
