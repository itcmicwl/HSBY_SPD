package gyqx.spdherp.deptMgr.service.impl;

import java.math.BigDecimal;
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
import gyqx.spdherp.deptMgr.dao.DeptGoodsTemplateMgrDao;
import gyqx.spdherp.deptMgr.service.IDeptGoodsTemplateMgrService;
import gyqx.spdherp.deptMgr.vo.DeptGoodsInfoVo;
import gyqx.spdherp.deptMgr.vo.GoodsPurTemplateVo;
import gyqx.spdherp.deptMgr.vo.ImportDeptTemplateGoodsParam;
import gyqx.spdherp.deptMgr.vo.TemplateGoodsParam;
import gyqx.spdherp.deptMgr.vo.TemplateGoodsVo;
import gyqx.spdherp.po.GoodsPurTemplate;
import gyqx.spdherp.po.GoodsPurTemplateGoods;
import gyqx.spdherp.po.GoodsPurTemplateUserRel;

@Service
public class DeptGoodsTemplateMgrServiceImpl implements IDeptGoodsTemplateMgrService {

	@Resource
	SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private DeptGoodsTemplateMgrDao dao;
    @Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	
	@Override
	public List<GoodsPurTemplate> deptGoodsTemplateQuery(String hosId, String deptId) throws Exception {
		String sql = "select * from goods_pur_template s where s.hos_id = ? and s.dept_id = ?";
		List<GoodsPurTemplate> list = simpleDao.queryForList(sql, GoodsPurTemplate.class, hosId,deptId);
		return list;
	}
	
	/**
	 * 部门请购套餐（请购）
	 * @param hosId
	 * @param deptId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<GoodsPurTemplate> deptGoodsTemplateQuery4DeptBy(String hosId, String deptId) throws Exception {
		String sql = "select s.* from goods_pur_template s "
				+ " inner join goods_pur_template_user_rel r on s.hos_id = r.hos_id and s.dept_id = r.dept_id and s.id = r.temp_id "
				+ " where r.user_id = ? and s.hos_id = ? and s.dept_id = ?";
		List<GoodsPurTemplate> list = simpleDao.queryForList(sql, GoodsPurTemplate.class,
				userOnlineStateUtils.getCurrent().getUserId(), hosId, deptId);
		return list;
	}

	@Override
	public QueryResult<TemplateGoodsVo> queryDetpTemplateGoods(QueryInfo<TemplateGoodsParam> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.queryDetpTemplateGoods(queryInfo.getQueryObject()));
	}
	
	@Override
	public List<GoodsPurTemplateGoods> importDeptTemplateGoods(ImportDeptTemplateGoodsParam param) throws Exception {
		List<DeptGoodsInfoVo> goodsList = param.getGoodsList();
		String tempId = param.getTempId();
		for(DeptGoodsInfoVo g:goodsList){
			GoodsPurTemplateGoods bean = new GoodsPurTemplateGoods();
			bean.setId(atomUtil.newValue("temp_goods_id"));
			bean.setGoodsId(g.getGoodsId());
			bean.setTempId(tempId);
			bean.setGoodsNum(g.getGoodsNum());
			simpleDao.insert(bean);
		}
		return null;
	}

	@Override
	public GoodsPurTemplateGoods deleteDetpTemplateGoods(GoodsPurTemplateGoods bean) throws Exception {
		simpleDao.delete(bean);
		return null;
	}

	@Override
	public List<TemplateGoodsVo> mulDeleteDetpTemplateGoods(List<TemplateGoodsVo> templateGoods) throws Exception {
		for(TemplateGoodsVo g:templateGoods){
			GoodsPurTemplateGoods bean = new GoodsPurTemplateGoods();
			bean.setId(g.getTgId());
			simpleDao.delete(bean);
		}
		return null;
	}

	@Override
	public GoodsPurTemplate deptGoodsTemplateAdd(GoodsPurTemplateVo goodsPurTemplateVo) throws Exception {
		String tempId = atomUtil.newValue("goods_temp_id");
		goodsPurTemplateVo.setId(tempId);
		dao.addDeptGoodTemplate(goodsPurTemplateVo);
		//添加人员科室模板关系
		addGoodsPurTemplateUserRelation(goodsPurTemplateVo.getUsers(), tempId);
		return goodsPurTemplateVo;
	}

	/**
	 * 添加人员科室模板关系
	 * @param users
	 * @param tempId
	 * @throws Exception
	 */
	private void addGoodsPurTemplateUserRelation(String users[] , String tempId)
			throws Exception {
		GoodsPurTemplateUserRel goodsPurTemplateUserRelation = null;
	    String hosId = userOnlineStateUtils.getCurrent().getCorpId();
	    String deptId = userOnlineStateUtils.getCurrent().getOrgId();
	    //删除关系
	    String sql = "delete from goods_pur_template_user_rel where dept_id=? and hos_Id=? and temp_id=?";
	    simpleDao.executeSql(sql, deptId,hosId,tempId);
		for(String userId:users){
			goodsPurTemplateUserRelation = new GoodsPurTemplateUserRel();
			String id = UUID.randomUUID().toString();
			goodsPurTemplateUserRelation.setId(id);
			goodsPurTemplateUserRelation.setUserId(userId);
			goodsPurTemplateUserRelation.setTempId(tempId);
			goodsPurTemplateUserRelation.setHosId(hosId);
			goodsPurTemplateUserRelation.setDeptId(deptId);
			goodsPurTemplateUserRelation.setLastUpdateDatetime(new  Date() );
			simpleDao.insertAndGet(goodsPurTemplateUserRelation);
		}
	}

	@Override
	public GoodsPurTemplateVo deptGoodsTemplateEdit(GoodsPurTemplateVo goodsPurTemplateVo) throws Exception {
		dao.updateDeptGoodTemplate(goodsPurTemplateVo);
		addGoodsPurTemplateUserRelation(goodsPurTemplateVo.getUsers(), goodsPurTemplateVo.getId());
		return goodsPurTemplateVo;
	}
	
	@Override
	public List<GoodsPurTemplate> deptGoodsTemplateDelete(List<GoodsPurTemplate> goodsPurTemplates) throws Exception {
		for(GoodsPurTemplate g:goodsPurTemplates){
			simpleDao.deleteByWhere("GoodsPurTemplateGoods", "tempId", g.getId());
			simpleDao.deleteByWhere("GoodsPurTemplate", "id", g.getId());
			// simpleDao.delete(g);
		}
		return null;
	}

	@Override
	public BigDecimal updateGoodsNum(GoodsPurTemplateGoods bean) throws Exception {
		dao.updateGoodsNum(bean);
		return bean.getGoodsNum();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
