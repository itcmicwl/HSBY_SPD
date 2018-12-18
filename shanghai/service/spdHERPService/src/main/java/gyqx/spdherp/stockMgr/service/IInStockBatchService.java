package gyqx.spdherp.stockMgr.service;


import java.util.List;

import com.mysql.jdbc.StringUtils;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.InStockBatch;
import gyqx.spdherp.stockMgr.vo.InStockBatchVo;
import org.apache.ibatis.annotations.Param;

public interface IInStockBatchService 
{
	InStockBatchVo get(String id) throws Exception ;
	int insert(InStockBatchVo inStockList) throws Exception ;
	int insertByBatch(List<InStockBatch> lst)  throws Exception;
	int update(InStockBatchVo inStockList) throws Exception;
	int UpdateByBatch(List<InStockBatchVo> lst) throws Exception;
	int delById(String id) throws Exception;
	int delByBillId(String billId) throws Exception;
	List<InStockBatchVo> list(InStockBatchVo bean) throws Exception;
	List<InStockBatchVo> listByUniqueCode(List<String> qUniqueCodes) throws Exception;
	QueryResult<InStockBatchVo> listByPage(QueryInfo<InStockBatchVo> queryInfo) throws Exception;

}


