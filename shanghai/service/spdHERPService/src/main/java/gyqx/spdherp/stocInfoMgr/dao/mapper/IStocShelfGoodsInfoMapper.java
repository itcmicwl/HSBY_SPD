package gyqx.spdherp.stocInfoMgr.dao.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.StockShelf;
import gyqx.spdherp.stocInfoMgr.vo.GoodsShelfInfoVo;
import gyqx.spdherp.stocInfoMgr.vo.QueryResultVo;
import gyqx.spdherp.stocInfoMgr.vo.QueryVo;
import org.apache.ibatis.annotations.Param;

public interface IStocShelfGoodsInfoMapper {

	List<QueryResultVo> list(QueryVo queryInfo) ;

	List<HosGoodsInfoVo> queryStockShelfGoodsList(StockShelf stockShelf);

    List<GoodsShelfInfoVo> findShelfByGoodsIdList(@Param("goodsIdList") List<String> goodsIdList,
											   @Param("stocId") String stocId,
											   @Param("hosId") String hosId);
}

