package gyqx.spdherp.stockMgr.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.InStockUniqueCode;
import gyqx.spdherp.stockMgr.vo.InStockUniqueCodeVo;

public interface IInStockUniqueCodeService 
{
	InStockUniqueCodeVo get(String id) throws Exception ;
	int insert(InStockUniqueCodeVo bean) throws Exception ;
	int insertByBatch(List<InStockUniqueCodeVo> lst)  throws Exception;
	int update(InStockUniqueCodeVo bean) throws Exception;
	int UpdateByBatch(List<InStockUniqueCodeVo> lst) throws Exception;
	int delById(String id) throws Exception;
	int delByBillId(String billId) throws Exception;
	List<InStockUniqueCodeVo> list(InStockUniqueCodeVo bean) throws Exception;
	QueryResult<InStockUniqueCodeVo> listByPage(QueryInfo<InStockUniqueCodeVo> queryInfo) throws Exception;
	List<InStockUniqueCodeVo> listByUniqueCode(List<String> qUniqueCodes) throws Exception;
}


