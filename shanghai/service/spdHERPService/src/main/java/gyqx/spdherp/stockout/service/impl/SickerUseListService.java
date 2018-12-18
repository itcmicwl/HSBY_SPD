package gyqx.spdherp.stockout.service.impl;

import javax.annotation.Resource;

import gyqx.spdherp.stockout.vo.SickerUserListVo;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.stockout.dao.SickerUseListDao;
import gyqx.spdherp.stockout.service.ISickerUseListService;

@Service
public class SickerUseListService implements ISickerUseListService {
	
	@Resource
	private SickerUseListDao  dao;

	@Override
	public SickerUserListVo get(String id) throws Exception {
		return dao.get(id);
	}

	@Override
	public List<SickerUserListVo> getListByBillId(String billId) throws Exception {
		return dao.getListByBillId(billId);
	}

	@Override
	public int add(SickerUserListVo sickerUseList) throws Exception {
		return dao.insert(sickerUseList);
	}

	@Override
	public int update(SickerUserListVo sickerUseList) throws Exception {
		return dao.update(sickerUseList);
	}

	@Override
	public List<SickerUserListVo> list(SickerUserListVo bean) throws Exception {
		return dao.list(bean);
	}

	@Override
	public QueryResult<SickerUserListVo> listByPage(QueryInfo<SickerUserListVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	@Override
	public int update4Return(List<SickerUserListVo> lst) throws Exception {
		return  dao.update4Return(lst);
	}
}


