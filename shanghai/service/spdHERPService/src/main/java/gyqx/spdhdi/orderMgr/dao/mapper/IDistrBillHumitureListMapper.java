package gyqx.spdhdi.orderMgr.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdhdi.orderMgr.vo.DistrBillHumitureListVo;
import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
 
public interface IDistrBillHumitureListMapper {
	DistrBillHumitureListVo get(@Param("id") String id);
	int update(DistrBillHumitureListVo bean);
	int insertBatch(List<DistrBillHumitureListVo> bean);
	int delById(@Param("id") String id);
	int delByPid(@Param("pid") String pid);
	List<DistrBillHumitureListVo> list(DistrBillHumitureListVo queryInfo);
}

