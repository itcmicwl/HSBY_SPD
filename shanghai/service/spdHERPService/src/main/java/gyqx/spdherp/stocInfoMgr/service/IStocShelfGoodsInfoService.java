package gyqx.spdherp.stocInfoMgr.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.GoodsPurTemplateGoods;
import gyqx.spdherp.po.StockShelf;
import gyqx.spdherp.stocInfoMgr.vo.*;

import java.util.List;

public interface IStocShelfGoodsInfoService {
	
	List<StockShelfVo> queryStockShelfList(StockShelf paramObj) throws Exception;
	
	QueryResult<QueryResultVo> listByPage(QueryInfo<QueryVo> queryInfo) throws Exception;

	List<GoodsPurTemplateGoods> importStockShelfGoods(StockShelfGoodsVo param) throws Exception;

	QueryResult<HosGoodsInfoVo> queryStockShelfGoodsList(QueryInfo<StockShelfGoodsInfoVo> queryInfo) throws Exception;

	List<HosGoodsInfoVo> deleteStockShelfGoods(List<HosGoodsInfoVo> hosGoodsInfoVos) throws Exception;

    /**
     * 根据商品ID查找默认对应货位（医院为操作者医院）
     * @param goodsIdList 医院商品ID列表
     * @param stocId 医院库房ID
     * @return 返回商品对应的货位列表
     */
	List<GoodsShelfInfoVo> findShelfByGoodsIdList(List<String> goodsIdList, String stocId);

}
