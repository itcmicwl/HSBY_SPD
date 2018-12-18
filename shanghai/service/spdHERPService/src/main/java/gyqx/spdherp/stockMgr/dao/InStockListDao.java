package gyqx.spdherp.stockMgr.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.po.InStockList;
import gyqx.spdherp.stockMgr.vo.InSUniquecodeQueryVo;
import gyqx.spdherp.stockMgr.vo.InStockListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.stockMgr.dao.mapper.IInStockListMapper;


@Repository
public class InStockListDao 
{
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IInStockListMapper mapper;

	public InStockListVo get(String id)
	{
		return mapper.get(id);
	}	

	public int insert(InStockListVo inStockList) throws Exception
	{
		return mapper.insert(inStockList);
	}
	public int intsertByBatch(List<InStockList> lst) throws Exception{
		return mapper.insertByBatch(lst);
	}
	public  int update (InStockListVo inStock) throws  Exception{
		return mapper.update(inStock);
	}
	public  int updateByBatch(List<InStockListVo> lst) throws  Exception{
		return mapper.updateByBatch(lst);
	}
	public int delById(String id) throws Exception {
		return  mapper.delById(id);
	}
	public  int delByBillId(String billId) throws  Exception{
		return mapper.delByBillId(billId);
	}
	public List<InStockListVo> list(InStockListVo qbean) throws Exception{
		return mapper.list(qbean);
	}
	public List<InStockListVo> getSettledListByBillIdAndRow(List<InStockListVo> lst) throws Exception{
		return mapper.getSettledListByBillIdAndRow(lst);
	}
	public int updateInBillLstStatus(List<InStockListVo> lst) throws Exception{
		return mapper.updateInBillLstStatus(lst);
	}
	public List<InStockListVo> listByUniqueCode(List<String> qUniqueCodes) throws Exception{
		return mapper.listByUniqueCode(qUniqueCodes);
	}
	public InStockListVo getByUniqueCode(InSUniquecodeQueryVo uniqueCode) throws Exception{
		return mapper.getByUniqueCode(uniqueCode);
	}
}


