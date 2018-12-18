package gyqx.spdherp.deptMgr.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import common.web.UserOnlineInfo;
import gyqx.spdherp.deptMgr.Constance;
import gyqx.spdherp.deptMgr.dao.DeptGoodsAuditDao;
import gyqx.spdherp.deptMgr.service.IDeptGoodsAuditService;
import gyqx.spdherp.deptMgr.service.IDeptGoodsInfoService;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyVo;

@Service
public class DeptGoodsAuditServiceImpl implements IDeptGoodsAuditService {

	@Resource
	SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private DeptGoodsAuditDao deptGoodsAuditDao;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	@Resource
	private IDeptGoodsInfoService deptService;

	@Override
	public QueryResult<DeptGoodsApplyVo> findDeptGoodsAuditList(QueryInfo<DeptGoodsApplyVo> queryInfo)
			throws Exception {
		UserOnlineInfo user = userOnlineStateUtils.getCurrent();
		queryInfo.getQueryObject().setHosId(user.getCorpId());
		queryInfo.getQueryObject().setDeptId(user.getOrgId());
		queryInfo.getQueryObject().setAuditer(user.getUserId());
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(deptGoodsAuditDao.getDeptGoodsAuditList(queryInfo.getQueryObject()));
	}

	@Override
	public void deptGoodsAuditList(List<DeptGoodsApplyVo> vo) throws Exception {
		// 更新科室产品申请表
		String userCode = userOnlineStateUtils.getCurrent().getUserCode();
		// 获取科室产品申请表ID
		List<String> deptApplyGoodsIds = vo.stream().map(DeptGoodsApplyVo::getId).collect(Collectors.toList());
		// 根据科室产品申请表id，查询科室待插入产品信息列表
		List<DeptGoodsApplyVo> deptGoodsList = deptGoodsAuditDao.getApplyGoodsList(deptApplyGoodsIds);
		for (DeptGoodsApplyVo deptGoodsApplyVo : deptGoodsList) {
			String id = atomUtil.newValue("dept-goods-info-id");
			deptGoodsApplyVo.setId(id);
		}
		// 导入科室产品信息
		deptGoodsAuditDao.importDeptGoodsInfo(deptGoodsList);
		// 更新科室产品申请状态
		deptGoodsAuditDao.updateDeptApplyGoodsStatus(deptApplyGoodsIds, userCode);
	}

	@Override
	public void deptGoodsReject(DeptGoodsApplyVo vo) {
		String userCode = userOnlineStateUtils.getCurrent().getUserCode();
		vo.setStatus(Constance.APPLY_STATUS_REJECT);
		vo.setAuditer(userCode);
		deptGoodsAuditDao.deptGoodsAuditList(vo);
	}

}
