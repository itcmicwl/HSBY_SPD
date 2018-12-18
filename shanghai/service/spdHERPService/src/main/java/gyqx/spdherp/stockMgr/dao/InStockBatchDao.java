package gyqx.spdherp.stockMgr.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.stockMgr.vo.InStockBatchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.stockMgr.dao.mapper.IInStockBatchMapper;
import gyqx.spdherp.po.InStockBatch;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class InStockBatchDao 
{
	@Resource
	private IInStockBatchMapper mapper;

	public InStockBatchVo get(String id)
	{
		return mapper.get(id);
	}

	public int insert(InStockBatchVo inStockList) throws Exception
	{
		return mapper.insert(inStockList);
	}
	public int intsertByBatch(List<InStockBatch> lst) throws Exception{
		return mapper.insertByBatch(lst);
	}
	public  int update (InStockBatchVo inStock) throws  Exception{
		return mapper.update(inStock);
	}
	public  int updateByBatch(List<InStockBatchVo> lst) throws  Exception{
		return mapper.updateByBatch(lst);
	}
	public int delById(String id) throws Exception {
		return  mapper.delById(id);
	}
	public  int delByBillId(String billId) throws  Exception{
		return mapper.delByBillId(billId);
	}
	public List<InStockBatchVo> list(InStockBatchVo qbean) throws Exception{

		return mapper.list(qbean);
	}
	public List<InStockBatchVo> listByUniqueCode(List<String> qUniqueCodes) throws Exception{
		return mapper.listByUniqueCode(qUniqueCodes);
	}
}


