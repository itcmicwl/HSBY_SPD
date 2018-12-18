package gyqx.spdherp.stockout.service;

import common.db.query.QueryResult;
import gyqx.spdherp.stockout.vo.IdNameVo;
import gyqx.spdherp.stockout.vo.IdNameVoSup;
import gyqx.spdherp.stockout.vo.StockpileHzVo;

import java.util.List;

/**
 * Created by cjzyw on 2018/5/18.
 */
public interface ReturnOutSupService {

    List<IdNameVoSup> listSup(String stockid);

    QueryResult<StockpileHzVo> listStockGoodsSup(String stockId, Integer stocKind, String filter, int pageNum, int pageSize,String provId);

}
