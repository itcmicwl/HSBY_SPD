package gyqx.spdherp.stockMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.InStockBatch;
import gyqx.spdherp.stockMgr.vo.InStockBatchVo;
import gyqx.spdherp.stockMgr.vo.InStockListVo;
import org.apache.ibatis.annotations.Param;

public interface IInStockBatchMapper {
	InStockBatchVo get(@Param("id") String id);
	int update(InStockBatchVo bean);
	int insert(InStockBatchVo bean);
	int updateByBatch(List<InStockBatchVo> lst);
	int insertByBatch(List<InStockBatch> lst);
	int delById(@Param("id") String id);
	int delByBillId(@Param("billId") String billId);
	List<InStockBatchVo> list(InStockBatchVo queryInfo) ;
	List<InStockBatchVo> listByUniqueCode(@Param("qUniqueCodes") List<String> qUniqueCodes);

}

