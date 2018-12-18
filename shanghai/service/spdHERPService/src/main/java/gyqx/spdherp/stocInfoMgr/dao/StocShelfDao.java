package gyqx.spdherp.stocInfoMgr.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.query.QueryInfo;
import gyqx.spdherp.stocInfoMgr.dao.mapper.StocShelfMapper;
import gyqx.spdherp.stocInfoMgr.vo.StocShelfVo;

@Repository
public class StocShelfDao {
	
	@Resource
	private StocShelfMapper mapper;

	public List<StocShelfVo> getStocArea(QueryInfo<StocShelfVo> queryInfo) {
		return mapper.getStocArea(queryInfo.getQueryObject());
	}
	
	public List<StocShelfVo> getStocShelf(QueryInfo<StocShelfVo> queryInfo) {
		return mapper.getStocShelf(queryInfo.getQueryObject());
	}
	
	public void updateStocShelf(StocShelfVo stocShelfVo) {
		mapper.updateStocShelf(stocShelfVo);
	}

	public void insertStocShelf(StocShelfVo stocShelfVo) {
		mapper.insertStocShelf(stocShelfVo);
	}

	public List<StocShelfVo> getAreaList(StocShelfVo stocShelfVo) {
		return mapper.getStocArea(stocShelfVo);
	}
}
