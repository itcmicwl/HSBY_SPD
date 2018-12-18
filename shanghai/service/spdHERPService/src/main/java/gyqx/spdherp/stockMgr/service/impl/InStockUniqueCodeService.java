package gyqx.spdherp.stockMgr.service.impl;

import javax.annotation.Resource;

import gyqx.spdherp.stockMgr.vo.InStockUniqueCodeVo;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;

import java.math.BigDecimal;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.stockMgr.dao.InStockUniqueCodeDao;
import gyqx.spdherp.stockMgr.service.IInStockUniqueCodeService;

@Service
public class InStockUniqueCodeService implements IInStockUniqueCodeService {
	
	@Resource
	private InStockUniqueCodeDao  dao;

	@Override
	public InStockUniqueCodeVo get(String id) throws Exception {
		return dao.get(id);
	}

	@Override
	public int insert(InStockUniqueCodeVo bean) throws Exception {
		return dao.insert(bean);
	}

	@Override
	public int insertByBatch(List<InStockUniqueCodeVo> lst) throws Exception {
		lst.stream().filter(item -> item.getQty() == null).forEach(item -> item.setQty(BigDecimal.ONE));
		return dao.intsertByBatch(lst);
	}

	@Override
	public int update(InStockUniqueCodeVo bean) throws Exception {
		return dao.update(bean);
	}

	@Override
	public int UpdateByBatch(List<InStockUniqueCodeVo> lst) throws Exception {
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
	public List<InStockUniqueCodeVo> list(InStockUniqueCodeVo bean) throws Exception {
		return dao.list(bean);
	}

	@Override
	public QueryResult<InStockUniqueCodeVo> listByPage(QueryInfo<InStockUniqueCodeVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	@Override
	public List<InStockUniqueCodeVo> listByUniqueCode(List<String> qUniqueCodes) throws Exception {
		return dao.listByUniqueCode(qUniqueCodes);
	}
}


