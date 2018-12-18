package gyqx.spdherp.stockMgr.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.stockMgr.vo.InStockUniqueCodeVo;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.stockMgr.dao.mapper.IInStockUniqueCodeMapper;
import gyqx.spdherp.po.InStockUniqueCode;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class InStockUniqueCodeDao 
{
	@Resource
	private IInStockUniqueCodeMapper mapper;

	public InStockUniqueCodeVo get(String id)
	{
		return mapper.get(id);
	}

	public int insert(InStockUniqueCodeVo inStockUniqueCodeVo) throws Exception
	{
		return mapper.insert(inStockUniqueCodeVo);
	}
	public int intsertByBatch(List<InStockUniqueCodeVo> lst) throws Exception{
		return mapper.insertByBatch(lst);
	}
	public  int update (InStockUniqueCodeVo inStock) throws  Exception{
		return mapper.update(inStock);
	}
	public  int updateByBatch(List<InStockUniqueCodeVo> lst) throws  Exception{
		return mapper.updateByBatch(lst);
	}
	public int delById(String id) throws Exception {
		return  mapper.delById(id);
	}
	public  int delByBillId(String billId) throws  Exception{
		return mapper.delByBillId(billId);
	}
	public List<InStockUniqueCodeVo> list(InStockUniqueCodeVo qbean) throws Exception{

		return mapper.list(qbean);
	}
	public List<InStockUniqueCodeVo> listByUniqueCode(List<String> qUniqueCodes) throws Exception{
		return mapper.listByUniqueCode(qUniqueCodes);
	}
}


