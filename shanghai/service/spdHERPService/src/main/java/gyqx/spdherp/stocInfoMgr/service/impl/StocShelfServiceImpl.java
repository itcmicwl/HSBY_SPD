package gyqx.spdherp.stocInfoMgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.stocInfoMgr.dao.StocShelfDao;
import gyqx.spdherp.stocInfoMgr.service.StocShelfService;
import gyqx.spdherp.stocInfoMgr.vo.StocShelfVo;

@Service
public class StocShelfServiceImpl implements StocShelfService{
	
	@Resource
	private StocShelfDao stocShelfDao;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private SimpleDao dao;

	@Override
	public QueryResult<StocShelfVo> getStocArea(QueryInfo<StocShelfVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(stocShelfDao.getStocArea(queryInfo));
	}
	
	@Override
	public QueryResult<StocShelfVo> getStocShelf(QueryInfo<StocShelfVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(stocShelfDao.getStocShelf(queryInfo));
	}

	@Override
	public StocShelfVo updateStocShelf(StocShelfVo stocShelfVo) throws Exception {
		stocShelfVo.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		stocShelfDao.updateStocShelf(stocShelfVo);
		return stocShelfVo;
	}

	@Override
	public StocShelfVo insertStocShelf(StocShelfVo stocShelfVo) throws Exception {
		stocShelfVo.setId(atomUtil.newValue("shelf-id")); // sys_atom
		stocShelfVo.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		stocShelfDao.insertStocShelf(stocShelfVo);
		return stocShelfVo;
	}

	@Override
	public void deleteStocShelf(StocShelfVo stocShelfVo) {
		dao.executeSql("delete from stock_shelf where id = ?", stocShelfVo.getId());
		if(stocShelfVo.getPid() == null){
			dao.executeSql("delete from stock_shelf where pid = ?", stocShelfVo.getId());
		}
	}

	@Override
	public List<StocShelfVo> getAreaList(StocShelfVo stocShelfVo) {
		stocShelfVo.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		return stocShelfDao.getAreaList(stocShelfVo);
	}
}
