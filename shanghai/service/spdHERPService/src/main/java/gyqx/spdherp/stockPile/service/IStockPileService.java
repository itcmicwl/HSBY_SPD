package gyqx.spdherp.stockPile.service;
import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.stockPile.vo.DeptGoodsVo;
import gyqx.spdherp.stockPile.vo.StockPileHZVo;

/**
 * Created by zouliang on 2017-8-22.
 */
public interface IStockPileService {
    QueryResult<StockPileHZVo> stockPileHZ(QueryInfo<StockPileHZVo> bean) throws Exception;
    QueryResult<DeptGoodsVo> getGoodsInfo4AutoPurByPage(QueryInfo<DeptGoodsVo> bean) throws Exception;
    List<DeptGoodsVo> getGoodsInfo4AutoPur(DeptGoodsVo dgv) throws Exception;
}
