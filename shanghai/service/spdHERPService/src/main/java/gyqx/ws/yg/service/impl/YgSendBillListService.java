package gyqx.ws.yg.service.impl;

import javax.annotation.Resource;

import com.sun.org.apache.regexp.internal.RE;
import gyqx.ws.yg.dao.YgSendBillListDao;
import gyqx.ws.yg.service.IYgSendBillListService;
import gyqx.ws.yg.vo.repVo.YY131_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY155_REP_DETAIL;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;

import java.util.ArrayList;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Service
public class YgSendBillListService implements IYgSendBillListService {
	
	@Resource
	private YgSendBillListDao dao;

	@Override
	public YY155_REP_DETAIL get(String psmxbh) throws Exception {
		return dao.get(psmxbh);
	}

	@Override
	public int update(YY155_REP_DETAIL bean) throws Exception {
		return dao.update(bean);
	}

	@Override
	public int updateByBatch(List<YY155_REP_DETAIL> lst) throws Exception {
		return dao.updateByBatch(lst);
	}

	@Override
	public int insert(YY155_REP_DETAIL bean) throws Exception {
		return dao.insert(bean);
	}

	@Override
	public int insertByBatch(List<YY155_REP_DETAIL> lst) throws Exception {
		return dao.insertByBatch(lst);
	}

	@Override
	public List<YY155_REP_DETAIL> list(YY155_REP_DETAIL queryInfo) throws Exception {
		return dao.list(queryInfo);
	}

	@Override
	public List<YY155_REP_DETAIL> list(String billId) throws Exception {
		YY155_REP_DETAIL quer = new YY155_REP_DETAIL();
		quer.setPsdbh(billId);
		return dao.list(quer);
	}

	@Override
	public QueryResult<YY155_REP_DETAIL> listByPage(QueryInfo<YY155_REP_DETAIL> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}
}