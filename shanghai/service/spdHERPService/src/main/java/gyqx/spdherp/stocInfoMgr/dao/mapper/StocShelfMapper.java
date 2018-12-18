package gyqx.spdherp.stocInfoMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.stocInfoMgr.vo.StocShelfVo;

public interface StocShelfMapper {

	/**
	 * 查询区位
	 * @param queryObject
	 * @return
	 */
	List<StocShelfVo> getStocArea(StocShelfVo queryObject);
	
	/**
	 * 查询货位
	 * @param queryObject
	 * @return
	 */
	List<StocShelfVo> getStocShelf(StocShelfVo queryObject);

	/**
	 * 更新库位信息
	 * @param stocShelfVo
	 */
	void updateStocShelf(StocShelfVo stocShelfVo);

	/**
	 * 新增库位信息
	 * @param stocShelfVo
	 */
	void insertStocShelf(StocShelfVo stocShelfVo);
}
