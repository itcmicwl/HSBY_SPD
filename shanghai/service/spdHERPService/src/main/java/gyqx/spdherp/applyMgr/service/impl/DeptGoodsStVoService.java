package gyqx.spdherp.applyMgr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import gyqx.spdherp.applyMgr.dao.DeptGoodsStVoDao;
import gyqx.spdherp.applyMgr.service.IDeptGoodsStVoService;
import gyqx.spdherp.applyMgr.vo.DeptGoodsStVo;

@Service
public class DeptGoodsStVoService implements IDeptGoodsStVoService {
	
	@Resource
	private DeptGoodsStVoDao  dao;	
	
	public QueryResult<DeptGoodsStVo> listByPage(QueryInfo<DeptGoodsStVo> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


