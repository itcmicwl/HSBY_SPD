package gyqx.spdherp.deptMgr.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdherp.deptMgr.dao.mapper.IStaffStorehouseMgrMapper;
import gyqx.spdherp.deptMgr.vo.StocInfoVo;
import gyqx.spdherp.deptMgr.vo.UserStockVo;
import gyqx.spdherp.stocInfoMgr.vo.QueryVo;

@Repository
public class StaffStorehouseMgrDao {
	@Resource
	private IStaffStorehouseMgrMapper mapper;

	public int deleteUserStoc(String userId) {
		return mapper.deleteUserStoc(userId);
	}

	public List<UserStockVo> getStocksByUser(Map vo) throws Exception {
		return mapper.getStocksByUser(vo);
	}
	public List<UserStockVo> getStocksByHosUser(String userId,String hosId,String deptId){
		return mapper.getStocksByHosUser(userId,hosId,deptId);
	}
	public List<StocInfoVo> listStocInfo(QueryVo queryVo){
		return mapper.listStocInfo(queryVo);
	}
}
