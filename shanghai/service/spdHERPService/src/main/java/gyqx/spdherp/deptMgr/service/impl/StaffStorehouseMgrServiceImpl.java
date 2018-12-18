package gyqx.spdherp.deptMgr.service.impl;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import gyqx.platform.po.SysOrg;
import gyqx.spdherp.deptMgr.dao.StaffStorehouseMgrDao;
import gyqx.spdherp.deptMgr.service.IStaffStorehouseMgrService;
import gyqx.spdherp.deptMgr.vo.HosOperatorStockVo;
import gyqx.spdherp.deptMgr.vo.StocInfoVo;
import gyqx.spdherp.deptMgr.vo.UserStockSubmit;
import gyqx.spdherp.deptMgr.vo.UserStockVo;
import gyqx.spdherp.po.HosOperatorStock;
import gyqx.spdherp.po.StocInfo;
import gyqx.spdherp.stocInfoMgr.vo.QueryVo;

@Service
public class StaffStorehouseMgrServiceImpl implements IStaffStorehouseMgrService {

	@Resource
	SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private StaffStorehouseMgrDao staffStorehouseMgrDao;
	
	@Override
	public List<StocInfoVo> listStocInfo(String corpId) throws Exception {
		String sql = "SELECT s.id,s.hos_id,stoc_name,s.stoc_level FROM stoc_info s WHERE s.hos_id = ?";
		List<StocInfoVo> list = simpleDao.queryForList(sql, StocInfoVo.class, corpId);
		return list;
	}
	
	@Override
	public QueryResult<StocInfoVo> listStocInfo(QueryInfo<QueryVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(staffStorehouseMgrDao.listStocInfo(queryInfo.getQueryObject()));
	}
	
	@Override
	public List<StocInfo> listDeptStocInfo(String hosId,String deptId) throws Exception {
		String sql = "SELECT * FROM stoc_info s WHERE s.hos_id = ? and s.dept_id = ?";
		List<StocInfo> list = simpleDao.queryForList(sql, StocInfo.class, hosId,deptId);
		return list;
	}

	@Override
	public List<HosOperatorStockVo> queryStaffStock(String userId) throws Exception {
		userId = URLDecoder.decode(userId,"utf-8");
		String sql = "SELECT h.stock_id FROM hos_operator_stock h WHERE h.user_id = ?";
		List<HosOperatorStockVo> list = simpleDao.queryForList(sql, HosOperatorStockVo.class, userId);
		return list;
	}

	@Override
	public void userStocSubmit(UserStockSubmit userStockSubmit) throws Exception {
		int result = simpleDao.deleteByWhere("HosOperatorStock", "userId", userStockSubmit.getUserId());
//		staffStorehouseMgrDao.deleteUserStoc(userStockSubmit.getUserId());
		for(String stocId:userStockSubmit.getStockIds()){
			HosOperatorStock bean = new HosOperatorStock();
			String id = atomUtil.newValue("hos_operator_stock_id");
			bean.setId(id);
			bean.setHosId(userStockSubmit.getHosId());
			bean.setStockId(stocId);
			bean.setUserId(userStockSubmit.getUserId());
			simpleDao.insert(bean);
		}
	}
	public 	List<UserStockVo> getStocksByUser(Map vo) throws Exception{
	     return	staffStorehouseMgrDao.getStocksByUser(vo);
	}

	@Override
	public List<UserStockVo> getStocksByHosUser(String userId, String hosId, String deptId) throws Exception {
		return staffStorehouseMgrDao.getStocksByHosUser(userId,hosId,deptId);
	}

	public List<SysOrg> getOrgsByStock(String stockId) throws Exception{
		String sql ="select o.* from stoc_info s join sys_org o on s.dept_id= o.id where s.id=?";
		List<SysOrg>  orgs = simpleDao.queryForList(sql, SysOrg.class,stockId);
		return orgs;
	}


}
