package gyqx.spdherp.stockMgr.service.impl;

import javax.annotation.Resource;

import gyqx.spdherp.stockMgr.vo.InStockBatchVo;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.InStockBatch;
import gyqx.spdherp.stockMgr.dao.InStockBatchDao;
import gyqx.spdherp.stockMgr.service.IInStockBatchService;

@Service
public class InStockBatchService implements IInStockBatchService {
	
	@Resource
	private InStockBatchDao  dao;

	@Override
	public InStockBatchVo get(String id) throws Exception {
		return dao.get(id);
	}

	@Override
	public int insert(InStockBatchVo inStockList) throws Exception {
		return dao.insert(inStockList);
	}

	@Override
	public int insertByBatch(List<InStockBatch> lst) throws Exception {
		return dao.intsertByBatch(lst);
	}

	@Override
	public int update(InStockBatchVo inStockList) throws Exception {
		return dao.update(inStockList);
	}

	@Override
	public int UpdateByBatch(List<InStockBatchVo> lst) throws Exception {
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
	public List<InStockBatchVo> list(InStockBatchVo bean) throws Exception {
		return dao.list(bean);
	}

	@Override
	public QueryResult<InStockBatchVo> listByPage(QueryInfo<InStockBatchVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	@Override
	public List<InStockBatchVo> listByUniqueCode(List<String> qUniqueCodes) throws Exception {
		return dao.listByUniqueCode(qUniqueCodes);
	}
}


