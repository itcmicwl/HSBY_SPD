package gyqx.spdherp.deptMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.deptMgr.vo.GoodsPurTemplateVo;
import gyqx.spdherp.deptMgr.vo.TemplateGoodsParam;
import gyqx.spdherp.deptMgr.vo.TemplateGoodsVo;
import gyqx.spdherp.po.GoodsPurTemplateGoods;

public interface IDeptGoodsTemplateMgr {
	
	List<TemplateGoodsVo> queryDetpTemplateGoods(TemplateGoodsParam templateGoodsParam);

	void updateGoodsNum(GoodsPurTemplateGoods bean);

	void addDeptGoodTemplate(GoodsPurTemplateVo goodsPurTemplateVo);
	
	void updateDeptGoodTemplate(GoodsPurTemplateVo goodsPurTemplateVo);

}
