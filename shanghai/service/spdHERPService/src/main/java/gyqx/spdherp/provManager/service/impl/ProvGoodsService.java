package gyqx.spdherp.provManager.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import gyqx.spdherp.provManager.dao.ProvGoodsDao;
import gyqx.spdherp.provManager.service.IProvGoodsService;
import gyqx.spdherp.provManager.vo.ProvGoodsVo;

@Service
public class ProvGoodsService implements IProvGoodsService {
	@Resource
	ProvGoodsDao dao;
	@Resource
	SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;

	@Override
	public QueryResult<ProvGoodsVo> queryProvGoodsList(QueryInfo<ProvGoodsVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.queryProvGoodsList(queryInfo.getQueryObject()));
	}
	

}
