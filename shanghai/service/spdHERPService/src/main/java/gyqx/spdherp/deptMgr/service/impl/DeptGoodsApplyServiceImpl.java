package gyqx.spdherp.deptMgr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.deptMgr.Constance;
import gyqx.spdherp.deptMgr.dao.DeptGoodsApplyDao;
import gyqx.spdherp.deptMgr.service.IDeptGoodsApplyService;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyBatchAddVo;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplySubmitVo;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyVo;
import gyqx.spdherp.po.DeptGoodsApply;

@Service
public class DeptGoodsApplyServiceImpl implements IDeptGoodsApplyService {

	@Resource
	SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private DeptGoodsApplyDao deptGoodsApplyDao;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	
	@Override
	public QueryResult<DeptGoodsApplyVo> findDeptGoodsApplyList(QueryInfo<DeptGoodsApplyVo> queryInfo)
			throws Exception {
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		String deptId = userOnlineStateUtils.getCurrent().getOrgId();
		queryInfo.getQueryObject().setHosId(hosId);
		queryInfo.getQueryObject().setDeptId(deptId);
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(deptGoodsApplyDao.getDeptGoodsApplyList(queryInfo.getQueryObject()));
	}
	
	@Override
	public QueryResult<HosGoodsInfo> deptGoodsApplyBatchAddQuery(QueryInfo<DeptGoodsApplyVo> queryInfo) throws Exception {
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		String deptId = userOnlineStateUtils.getCurrent().getOrgId();
		queryInfo.getQueryObject().setHosId(hosId);
		queryInfo.getQueryObject().setDeptId(deptId);
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(deptGoodsApplyDao.deptGoodsApplyBatchAddQuery(queryInfo.getQueryObject()));
	}

	@Override
	public List<DeptGoodsApply> deptGoodsApplyBatchAdd(DeptGoodsApplyBatchAddVo deptGoodsApplyBatchAddVo)
			throws Exception {
		List<DeptGoodsApply> deptGoodsApplys = new ArrayList<DeptGoodsApply>();
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		String deptId = userOnlineStateUtils.getCurrent().getOrgId();
		String userId = userOnlineStateUtils.getCurrent().getUserId();
		List<HosGoodsInfo> hosGoodsInfos = deptGoodsApplyBatchAddVo.getHosGoodsInfos();
		for(HosGoodsInfo h:hosGoodsInfos){
			DeptGoodsApply bean = new DeptGoodsApply();
			bean.setId(UUID.randomUUID().toString());
			bean.setHosId(hosId);
			bean.setDeptId(deptId);
			bean.setHosGoodsId(h.getId());
			bean.setApplyTime(new Date());
			bean.setFillter(userId);
			//初始状态 保存 0
			bean.setStatus(Constance.APPLY_STATUS_SAVE);
			bean.setLastUpdateDatetime(new Date());
			bean.setVersion(0);
			DeptGoodsApply deptGoodsApply = simpleDao.insertAndGet(bean);
			deptGoodsApplys.add(deptGoodsApply);
		}
		return deptGoodsApplys;
	}

	@Override
	public void processDeptGoodsApplySubmit(DeptGoodsApplySubmitVo deptGoodsApplySubmitVo) throws Exception {
		List<DeptGoodsApplyVo> deptGoodsApplys = deptGoodsApplySubmitVo.getDeptGoodsApplys();
		List<String> deptGoodsApplyIds = new ArrayList<String>();
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		String deptId = userOnlineStateUtils.getCurrent().getOrgId();
		for (DeptGoodsApplyVo deptGoodsApplyVo : deptGoodsApplys) {
			if (deptGoodsApplyVo.getStatus() == 0) {
				deptGoodsApplyIds.add(deptGoodsApplyVo.getId());
				if (StringUtils.isNotBlank(deptGoodsApplyVo.getApplyReason())) {
					//更新申请理由
					deptGoodsApplyDao.updateDeptGoodsApplyReason(hosId, deptId, deptGoodsApplyVo.getId(),
							deptGoodsApplyVo.getVersion(), deptGoodsApplyVo.getApplyReason());
				}
			}
		}
		if (!deptGoodsApplyIds.isEmpty()) {
			// 执行批量提交操作
			deptGoodsApplyDao.updateDeptGoodsApplySubmit(hosId, deptId, deptGoodsApplyIds);
		}
	}

	@Override
	public void deleteDeptGoodsApply(String hosId, String deptId, String id) throws Exception {
		deptGoodsApplyDao.deleteDeptGoodsApply(hosId, deptId, id);
	}

	@Override
	public void updateDeptGoodsApplyRead(String hosId, String deptId, String id, Integer version) throws Exception {
		deptGoodsApplyDao.updateDeptGoodsApplyRead(hosId, deptId, id, version);
	}

	@Override
	public void updateDeptGoodsApplyReason(String hosId, String deptId, String id, Integer version, String applyReason)
			throws Exception {
		deptGoodsApplyDao.updateDeptGoodsApplyReason(hosId, deptId, id, version, applyReason);
		
	}

}
