package gyqx.spdherp.stocInfoMgr.dao;

import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.StockShelf;
import gyqx.spdherp.stocInfoMgr.dao.mapper.IStocShelfGoodsInfoMapper;
import gyqx.spdherp.stocInfoMgr.vo.GoodsShelfInfoVo;
import gyqx.spdherp.stocInfoMgr.vo.QueryResultVo;
import gyqx.spdherp.stocInfoMgr.vo.QueryVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class StocShelfGoodsInfoDao {
    @Resource
    private IStocShelfGoodsInfoMapper mapper;

    public List<QueryResultVo> list(QueryVo qbean) throws Exception {
        return mapper.list(qbean);
    }

    public List<HosGoodsInfoVo> queryStockShelfGoodsList(StockShelf stockShelf) {
        return mapper.queryStockShelfGoodsList(stockShelf);
    }

    public List<GoodsShelfInfoVo> findShelfByGoodsIdList(List<String> goodsIdList, String stocId, String hosId) {
        return mapper.findShelfByGoodsIdList(goodsIdList, stocId, hosId);
    }
}
