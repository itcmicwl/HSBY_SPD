package gyqx.spdherp.surgery.service.impl;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.SurgeryPkgDefList;
import gyqx.spdherp.surgery.dao.SurgeryPkgDefListDao;
import gyqx.spdherp.surgery.service.ISurgeryPkgDefListService;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SurgeryPkgDefListService implements ISurgeryPkgDefListService {
	
	@Resource
	private SurgeryPkgDefListDao  dao;

	@Resource
	private UserOnlineStateUtils userOnline;

	@Resource
	private SysAtomUtil atomUtil;

	@Resource
	private SimpleDao simpleDao;

	public int update(SurgeryPkgDefList surgeryPkgDefList) throws Exception {
		return dao.update(surgeryPkgDefList);
	}

	public List<SurgeryPkgDefListVo> list(SurgeryPkgDefListVo bean) throws Exception
	{
		return (List<SurgeryPkgDefListVo>) dao.list(bean);
	}

	@Override
	public List<SurgeryPkgDefListVo> list4QG(SurgeryPkgDefListVo queryInfo) throws Exception {
		return dao.list4QG(queryInfo);
	}

	public QueryResult<SurgeryPkgDefListVo> listByPage(QueryInfo<SurgeryPkgDefListVo> queryInfo) throws Exception{
		
		PageUtils.startPage(queryInfo);
		List<SurgeryPkgDefListVo> surgeryPkgDefListVos = dao.list(queryInfo.getQueryObject());
		return PageUtils.endPage(surgeryPkgDefListVos);
	}

	public QueryResult<SurgeryPkgDefListVo> listNotImportGoodsByPage(QueryInfo<SurgeryPkgDefListVo> queryInfo){
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.listNotImportGoods(queryInfo.getQueryObject()));
	}

	public int insertByBatch(List<SurgeryPkgDefListVo> surgeryPkgDefListVos) throws Exception {

		if(surgeryPkgDefListVos != null && surgeryPkgDefListVos.size() > 0) {

			String hosId = userOnline.getCurrent().getCorpId();

			for(SurgeryPkgDefListVo surgeryPkgDefListVo : surgeryPkgDefListVos){
				String id = hosId +":"+ atomUtil.newValue("surgery_def_list_id");
				surgeryPkgDefListVo.setId(id);

				//surgeryPkgDefListVo.setSurId(surGoodsVo.getSurId());
				surgeryPkgDefListVo.setGoodsId(surgeryPkgDefListVo.getHosGoods().getId());
				surgeryPkgDefListVo.setHosId(hosId);

				surgeryPkgDefListVo.setLastUpdateDatetime(new java.sql.Date(new Date().getTime()));
				surgeryPkgDefListVo.setVersion(0);

			}
		}

		return dao.insertByBatch(surgeryPkgDefListVos);
	}

	public int delete(String id){
		return dao.delete(id);
	}

	public int deleteByBatch(List<SurgeryPkgDefListVo> list){
		return  dao.deleteByBatch(list);
	}

	public 	List<HosGoodsInfoVo> getHosGoodsInfoById(String goodsId){
		return 	dao.getHosGoodsInfoById(goodsId);
	}

	@Override
	public List<SurgeryPkgDefListVo> setUseSurPkg(List<String> surIds) {
		return setSurPkgUseInfo(surIds);
	}

	private List<SurgeryPkgDefListVo> setSurPkgUseInfo(List<String> surIds){
		List<SurgeryPkgDefListVo> surgeryPkgDefListVos = new ArrayList<>();

		for (String surId: surIds) {
			SurgeryPkgDefListVo surgeryPkgDefListVo = new SurgeryPkgDefListVo();
			surgeryPkgDefListVo.setSurId(surId);
			surgeryPkgDefListVo.setHasUse(false);

			long num = simpleDao.queryForObject("SELECT count(*) from dept_buy_car where sur_id = ?", long.class, surId );
			if(num == 0){
				num = simpleDao.queryForObject("select count(0) from apply_surgery_pkg t where t.apply_qty >t.qty and t.sur_id = ?", long.class, surId );
			}
			if(num > 0){
				surgeryPkgDefListVo.setHasUse(true);
			}
			surgeryPkgDefListVos.add(surgeryPkgDefListVo);
		}
		return surgeryPkgDefListVos;
	}
}


