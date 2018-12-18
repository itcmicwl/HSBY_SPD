package gyqx.spdherp.deptMgr.service;

import java.math.BigDecimal;
import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.deptMgr.vo.GoodsPurTemplateVo;
import gyqx.spdherp.deptMgr.vo.ImportDeptTemplateGoodsParam;
import gyqx.spdherp.deptMgr.vo.TemplateGoodsParam;
import gyqx.spdherp.deptMgr.vo.TemplateGoodsVo;
import gyqx.spdherp.po.GoodsPurTemplate;
import gyqx.spdherp.po.GoodsPurTemplateGoods;

public interface IDeptGoodsTemplateMgrService {

	List<GoodsPurTemplate> deptGoodsTemplateQuery(String hosId, String deptId) throws Exception;
	
	List<GoodsPurTemplate> deptGoodsTemplateQuery4DeptBy(String hosId, String deptId) throws Exception;

	QueryResult<TemplateGoodsVo> queryDetpTemplateGoods(QueryInfo<TemplateGoodsParam> queryInfo) throws Exception;

	GoodsPurTemplateGoods deleteDetpTemplateGoods(GoodsPurTemplateGoods bean) throws Exception;

	List<TemplateGoodsVo> mulDeleteDetpTemplateGoods(List<TemplateGoodsVo> templateGoods) throws Exception;

	List<GoodsPurTemplateGoods> importDeptTemplateGoods(ImportDeptTemplateGoodsParam param) throws Exception;

	GoodsPurTemplate deptGoodsTemplateAdd(GoodsPurTemplateVo goodsPurTemplateVo) throws Exception;

	GoodsPurTemplateVo deptGoodsTemplateEdit(GoodsPurTemplateVo goodsPurTemplateVo) throws Exception;

	List<GoodsPurTemplate> deptGoodsTemplateDelete(List<GoodsPurTemplate> goodsPurTemplates) throws Exception;

	BigDecimal updateGoodsNum(GoodsPurTemplateGoods param) throws Exception;


}
