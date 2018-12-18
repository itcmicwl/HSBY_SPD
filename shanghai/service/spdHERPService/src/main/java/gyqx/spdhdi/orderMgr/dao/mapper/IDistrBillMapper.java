package gyqx.spdhdi.orderMgr.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdhdi.orderMgr.vo.DistrBillVo;
 
public interface IDistrBillMapper {
	int update(DistrBillVo bean);
	int updateStatus(DistrBillVo qbean);
	int insert(DistrBillVo bean);
	DistrBillVo getById(@Param("id") String id);
	DistrBillVo getByBillId(@Param("billId") String billId);
	List<DistrBillVo> list(DistrBillVo queryInfo);
}

