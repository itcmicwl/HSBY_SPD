package gyqx.spdherp.stockPile.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.HosStockpile;
import gyqx.spdherp.stockPile.vo.HosStockPileVo;

public interface IHosStockpileService 
{
	HosStockpile get(String id) throws Exception ;
	HosStockpile add(HosStockpile hosStockpile) throws Exception ;
	HosStockpile update(HosStockpile hosStockpile) throws Exception;
	int insertByBatch(List<HosStockpile> lst) throws Exception;

	/**
	 * 仅用于批量入库
	 * 唯一码管理的商品作批量插入
	 * 非唯一码管理的商品批量插入或更新操作
	 * @param lst
	 * @return
	 * @throws Exception
	 */
	boolean addOrUpdateByBatch(List<HosStockpile> lst) throws Exception;
	List<HosStockpile> query(String predicate, String orderBy, Object... fieldValue) throws Exception;
	
	List<HosStockpile> list(HosStockpile bean) throws Exception;
	
	QueryResult<HosStockpile> listByPage(QueryInfo<HosStockpile> queryInfo) throws Exception;
	List<HosStockPileVo> getGoodsStoc(HosStockPileVo query) throws Exception;
}


