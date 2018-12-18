package gyqx.spdhdi.orderMgr.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
 
public interface IDistrBillListMapper {
	DistrBillListVo get(@Param("id") String id);
	int insertBatch(List<DistrBillListVo> bean);
	int update(DistrBillListVo bean);
	int updateByBatch(List<DistrBillListVo> bean);
	int delByBillId(@Param("billId") String billId);
	int delByPid(@Param("id") String id);
	List<DistrBillListVo> list(DistrBillListVo queryInfo) ;
	List<DistrBillListVo> list4instock(DistrBillListVo qbean);
}

