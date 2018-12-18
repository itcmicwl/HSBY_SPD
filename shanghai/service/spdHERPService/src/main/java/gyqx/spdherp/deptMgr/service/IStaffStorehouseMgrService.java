package gyqx.spdherp.deptMgr.service;

import java.util.List;
import java.util.Map;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.platform.po.SysOrg;
import gyqx.spdherp.deptMgr.vo.HosOperatorStockVo;
import gyqx.spdherp.deptMgr.vo.StocInfoVo;
import gyqx.spdherp.deptMgr.vo.UserStockSubmit;
import gyqx.spdherp.deptMgr.vo.UserStockVo;
import gyqx.spdherp.po.StocInfo;
import gyqx.spdherp.stocInfoMgr.vo.QueryVo;

public interface IStaffStorehouseMgrService {

	List<StocInfoVo> listStocInfo(String corpId) throws Exception;
	
	List<StocInfo> listDeptStocInfo(String hosId,String deptId) throws Exception;

	List<HosOperatorStockVo> queryStaffStock(String userId) throws Exception;

	void userStocSubmit(UserStockSubmit userStockSubmit) throws Exception;
	
	List<UserStockVo> getStocksByUser(Map vo) throws Exception;
	List<UserStockVo> getStocksByHosUser(String userId,String hosId,String deptId) throws Exception;
    List<SysOrg> getOrgsByStock(String stockId) throws Exception;

	QueryResult<StocInfoVo> listStocInfo(QueryInfo<QueryVo> queryInfo) throws Exception;
}
