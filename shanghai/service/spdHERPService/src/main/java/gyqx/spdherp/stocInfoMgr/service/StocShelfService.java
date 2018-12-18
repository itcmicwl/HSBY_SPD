package gyqx.spdherp.stocInfoMgr.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.stocInfoMgr.vo.StocShelfVo;

public interface StocShelfService {

	/**
	 * 查询区位信息
	 * @param queryInfo
	 * @return
	 */
	QueryResult<StocShelfVo> getStocArea(QueryInfo<StocShelfVo> queryInfo);
	
	/**
	 * 查询货位信息
	 * @param queryInfo 库房id, 上级id
	 * @return
	 */
	QueryResult<StocShelfVo> getStocShelf(QueryInfo<StocShelfVo> queryInfo);
	
	/**
	 * 更新
	 * @param stocShelfVo
	 * @return
	 * @throws Exception
	 */
	public StocShelfVo updateStocShelf(StocShelfVo stocShelfVo) throws Exception;
	
	/**
	 * 新增
	 * @param stocShelfVo
	 * @return
	 * @throws Exception
	 */
	public StocShelfVo insertStocShelf(StocShelfVo stocShelfVo) throws Exception;

	/**
	 * 删除
	 * @param stocShelfVo
	 */
	void deleteStocShelf(StocShelfVo stocShelfVo);
	
	/**
	 * 查询当前库房下的所有区位信息
	 * @param stocShelfVo
	 * @return
	 */
	List<StocShelfVo> getAreaList(StocShelfVo stocShelfVo);
}
