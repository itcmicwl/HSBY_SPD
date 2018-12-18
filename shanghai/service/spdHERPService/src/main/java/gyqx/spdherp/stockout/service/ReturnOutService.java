package gyqx.spdherp.stockout.service;

import common.db.query.QueryResult;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.vo.BarcodeResultSetVo;
import gyqx.spdherp.stockout.vo.IdNameVo;
import gyqx.spdherp.stockout.vo.StockpileHzVo;

import java.text.ParseException;
import java.util.List;

public interface ReturnOutService {
    QueryResult<StockpileHzVo> listStockGoods(String stockId, Integer stocKind, String filter, int pageNum, int pageSize);

    List<IdNameVo> listOrgs();

    BarcodeResultSetVo parseBarcoe(String stockId, Integer stocKind, String masterBarcode, String slaverBarcode) throws ParseException;

    OutStock getAllInfoOutBill(InStockVo inStockVo) throws Exception;

    void modifyQty(OutStock outStock) throws Exception;
}
