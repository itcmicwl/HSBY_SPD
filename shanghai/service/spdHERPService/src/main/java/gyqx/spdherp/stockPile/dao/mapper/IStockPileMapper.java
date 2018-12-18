package gyqx.spdherp.stockPile.dao.mapper;

import java.util.List;

import gyqx.spdherp.stockPile.vo.DeptGoodsVo;
import gyqx.spdherp.stockPile.vo.StockPileHZVo;

/**
 * Created by zouliang on 2017-8-22.
 */
public interface IStockPileMapper {
    List<StockPileHZVo> listStockPileHZ(StockPileHZVo queryinfo);
    List<DeptGoodsVo> getGoodsInfo4AutoPur(DeptGoodsVo dgv);
}
