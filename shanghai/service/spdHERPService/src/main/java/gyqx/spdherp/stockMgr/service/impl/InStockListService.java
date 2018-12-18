package gyqx.spdherp.stockMgr.service.impl;

import javax.annotation.Resource;

import gyqx.spdherp.po.InStockList;
import gyqx.spdherp.stockMgr.vo.InSUniquecodeQueryVo;
import gyqx.spdherp.stockMgr.vo.InStockListVo;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.stockMgr.dao.InStockListDao;
import gyqx.spdherp.stockMgr.service.IInStockListService;

@Service
public class InStockListService implements IInStockListService {
	
	@Resource
	private InStockListDao  dao;

	@Override
	public InStockListVo get(String id) throws Exception {
		return dao.get(id);
	}

	@Override
	public int insert(InStockListVo inStockList) throws Exception {
		return dao.insert(inStockList);
	}

	@Override
	public int insertByBatch(List<InStockList> lst)  throws Exception{
		return dao.intsertByBatch(lst);
	}

	@Override
	public int update(InStockListVo inStockList) throws Exception {
		return dao.update(inStockList);
	}

	@Override
	public int UpdateByBatch(List<InStockListVo> lst) throws Exception {
		return dao.updateByBatch(lst);
	}

	@Override
	public int delById(String id) throws Exception {
		return dao.delById(id);
	}

	@Override
	public int delByBillId(String billId) throws Exception {
		return dao.delByBillId(billId);
	}

	@Override
	public List<InStockListVo> list(InStockListVo bean) throws Exception {
		return dao.list(bean);
	}

	@Override
	public QueryResult<InStockListVo> listByPage(QueryInfo<InStockListVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	@Override
	public List<InStockListVo> listByUniqueCode(List<String> qUniqueCodes) throws Exception {
		return dao.listByUniqueCode(qUniqueCodes);
	}

	@Override
	public InStockListVo getByUniqueCode(InSUniquecodeQueryVo uniqueCode) throws Exception {
		return dao.getByUniqueCode(uniqueCode);
	}
}


