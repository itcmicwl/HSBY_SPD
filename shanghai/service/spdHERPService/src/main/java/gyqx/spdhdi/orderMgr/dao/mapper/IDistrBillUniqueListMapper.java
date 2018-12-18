package gyqx.spdhdi.orderMgr.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdhdi.orderMgr.vo.DistrBillUniqueListVo;

public interface IDistrBillUniqueListMapper {
	DistrBillUniqueListVo get(String id);

	int update(DistrBillUniqueListVo bean);

	int insertBatch(List<DistrBillUniqueListVo> bean);

	int delById(@Param("id") String id);

	int delByPid(@Param("pid") String pid);
	int existUniqueCode(@Param("uniqueCode") String uniqueCode);
	List<DistrBillUniqueListVo> list(DistrBillUniqueListVo queryInfo);
}
