package gyqx.spdherp.deptMgr.dao.mapper;

import java.util.List;
import java.util.Map;

import gyqx.spdherp.deptMgr.vo.StocInfoVo;
import gyqx.spdherp.deptMgr.vo.UserStockVo;
import gyqx.spdherp.stocInfoMgr.vo.QueryVo;
import org.apache.ibatis.annotations.Param;

public interface IStaffStorehouseMgrMapper {

	int deleteUserStoc(String userId);

	List<UserStockVo> getStocksByUser(Map vo);
	List<UserStockVo> getStocksByHosUser(@Param("userId") String userId,@Param("hosId") String hosId,@Param("deptId") String deptId);
	List<StocInfoVo> listStocInfo(QueryVo queryVo);
}
