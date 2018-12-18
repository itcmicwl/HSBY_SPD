package gyqx.spdherp.stockMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.InStockUniqueCode;
import gyqx.spdherp.stockMgr.vo.InStockUniqueCodeVo;
import org.apache.ibatis.annotations.Param;

public interface IInStockUniqueCodeMapper {
	InStockUniqueCodeVo get(@Param("id") String id);
	int update(InStockUniqueCodeVo bean);
	int insert(InStockUniqueCodeVo bean);
	int updateByBatch(List<InStockUniqueCodeVo> lst);
	int insertByBatch(List<InStockUniqueCodeVo> lst);
	int delById(@Param("id") String id);
	int delByBillId(@Param("billId") String billId);
	List<InStockUniqueCodeVo> list(InStockUniqueCodeVo queryInfo) ;
	List<InStockUniqueCodeVo> listByUniqueCode(@Param("qUniqueCodes") List<String> qUniqueCodes);
}

