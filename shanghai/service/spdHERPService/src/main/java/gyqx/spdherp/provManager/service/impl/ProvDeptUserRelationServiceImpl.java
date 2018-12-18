package gyqx.spdherp.provManager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.po.ProvDeptUserRelation;
import gyqx.spdherp.provManager.dao.ProvDeptUserRelationDao;
import gyqx.spdherp.provManager.service.IProvDeptUserRelationService;
import gyqx.spdherp.provManager.vo.ProvDeptUserRelationVo;
import gyqx.spdherp.provManager.vo.ProvDeptUserVo;
import gyqx.spdherp.provManager.vo.ProvHosRelationVo;
import gyqx.spdherp.provManager.vo.ProvUserRelationVo;

@Service
public class ProvDeptUserRelationServiceImpl implements IProvDeptUserRelationService {

	@Resource
	SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ProvDeptUserRelationDao provDeptUserRelationDao;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	
	@Override
	public void processProvDeptUserRelation(ProvDeptUserRelationVo provDeptUserRelationVo) throws Exception {
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		String userId = userOnlineStateUtils.getCurrent().getUserId();
		provDeptUserRelationVo.setHosId(hosId);
		// 新增操作 一个供应商关联多个用户，
/*		if (provDeptUserRelationVo.getUserIds() != null && !provDeptUserRelationVo.getUserIds().isEmpty()) {
			for (ProvDeptUserVo provDeptUserVo : provDeptUserRelationVo.getUserIds()) {
				ProvDeptUserRelation bean = new ProvDeptUserRelation();
				bean.setId(UUID.randomUUID().toString());
				bean.setHosId(provDeptUserRelationVo.getHosId());
				bean.setProvId(provDeptUserRelationVo.getProvId());
				bean.setUserId(provDeptUserVo.getUserId());
				bean.setFillter(userId);
				bean.setLastUpdateDatetime(new Date());
				bean.setVersion(0);
				simpleDao.insert(bean);
			}
		}*/
		//新增一个用户关联多个供应商
		if (provDeptUserRelationVo.getProvIds() != null && !provDeptUserRelationVo.getProvIds().isEmpty()) {
			for (ProvHosRelationVo provHosRelationVo : provDeptUserRelationVo.getProvIds()) {
				ProvDeptUserRelation bean = new ProvDeptUserRelation();
				bean.setId(UUID.randomUUID().toString());
				bean.setHosId(provDeptUserRelationVo.getHosId());
				bean.setUserId(provDeptUserRelationVo.getUserId());
				bean.setProvId(provHosRelationVo.getProvId());
				bean.setFillter(userId);
				bean.setLastUpdateDatetime(new Date());
				bean.setVersion(0);
				simpleDao.insert(bean);
			}
		}

	}

	@Override
	public QueryResult<ProvDeptUserVo> getUserList(QueryInfo<ProvDeptUserRelationVo> queryInfo) throws Exception {
		String hosId = queryInfo.getQueryObject().getHosId();
		String deptId = queryInfo.getQueryObject().getDeptId();
		String provId = queryInfo.getQueryObject().getProvId();
		String ename = queryInfo.getQueryObject().getEname();
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(provDeptUserRelationDao.getUserList(provId,hosId,deptId,ename));
	}

	@Override
	public QueryResult<ProvDeptUserVo> getCheckRelUserList(QueryInfo<ProvDeptUserRelationVo> queryInfo)
			throws Exception {
		String hosId = queryInfo.getQueryObject().getHosId();
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(provDeptUserRelationDao.getCheckRelUserList(queryInfo.getQueryObject().getProvId(),
				hosId, queryInfo.getQueryObject().getDeptId()));
	}



	@Override
	public void processDelProvDeptUserRelation(ProvDeptUserRelationVo provDeptUserRelationVo) throws Exception {
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		provDeptUserRelationVo.setHosId(hosId);
		// 先删除该医院建立关系的数据信息
		if (provDeptUserRelationVo.getProvIds() != null && !provDeptUserRelationVo.getProvIds().isEmpty()) {
			List<String> provIds = new ArrayList<String>();
			for (ProvHosRelationVo provHosRelationVo : provDeptUserRelationVo.getProvIds()) {
				provIds.add(provHosRelationVo.getProvId());
			}
			provDeptUserRelationDao.deleteProvDeptUserRelation(provDeptUserRelationVo.getHosId(),
					provDeptUserRelationVo.getUserId(), provIds);
		}
	}

	@Override
	public QueryResult<ProvUserRelationVo> getUserProvRelationList(QueryInfo<ProvUserRelationVo> queryInfo)
			throws Exception {
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		String userId = queryInfo.getQueryObject().getUserId();
		String provName = queryInfo.getQueryObject().getProvName();
		PageUtils.startPage(queryInfo);
		queryInfo.getQueryObject().setHosId(hosId);;
		return PageUtils.endPage(provDeptUserRelationDao.getUserProvRelationList(hosId, userId, provName));
	}

	@Override
	public QueryResult<ProvHosRelationVo> getUserHosProvRelationList(QueryInfo<ProvHosRelationVo> queryInfo)
			throws Exception {
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		String userId = queryInfo.getQueryObject().getUserId();
		String provName = queryInfo.getQueryObject().getProvName();
		PageUtils.startPage(queryInfo);
		queryInfo.getQueryObject().setHosId(hosId);;
		return PageUtils.endPage(provDeptUserRelationDao.getUserHosProvRelationList(hosId, userId, provName));

	}
}
