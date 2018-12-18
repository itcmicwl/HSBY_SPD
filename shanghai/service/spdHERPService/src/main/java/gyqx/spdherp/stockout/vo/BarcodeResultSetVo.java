package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.basedatamaint.vo.BarcodeParseResult;

import java.util.Collection;

/**
 * @Author liangwu
 * @Date 18-5-9 上午9:03
 */
public class BarcodeResultSetVo {
    private Collection<StockpileHzVo> stockpile;
    private Collection<BarcodeParseResult> barcodeParseResults;

    public Collection<StockpileHzVo> getStockpile() {
        return stockpile;
    }

    public void setStockpile(Collection<StockpileHzVo> stockpile) {
        this.stockpile = stockpile;
    }

    public Collection<BarcodeParseResult> getBarcodeParseResults() {
        return barcodeParseResults;
    }

    public void setBarcodeParseResults(Collection<BarcodeParseResult> barcodeParseResults) {
        this.barcodeParseResults = barcodeParseResults;
    }
}
