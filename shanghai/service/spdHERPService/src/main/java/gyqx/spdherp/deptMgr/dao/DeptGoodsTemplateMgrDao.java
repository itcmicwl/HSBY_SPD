package gyqx.spdherp.deptMgr.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdherp.deptMgr.dao.mapper.IDeptGoodsTemplateMgr;
import gyqx.spdherp.deptMgr.vo.GoodsPurTemplateVo;
import gyqx.spdherp.deptMgr.vo.TemplateGoodsParam;
import gyqx.spdherp.deptMgr.vo.TemplateGoodsVo;
import gyqx.spdherp.po.GoodsPurTemplateGoods;

@Repository
public class DeptGoodsTemplateMgrDao {
	@Resource
	private IDeptGoodsTemplateMgr mapper;

	public List<TemplateGoodsVo> queryDetpTemplateGoods(TemplateGoodsParam templateGoodsParam){
		return mapper.queryDetpTemplateGoods(templateGoodsParam);
	}

	public void updateGoodsNum(GoodsPurTemplateGoods bean) {
		mapper.updateGoodsNum(bean);
	}
	
	public void addDeptGoodTemplate(GoodsPurTemplateVo goodsPurTemplateVo){
		mapper.addDeptGoodTemplate(goodsPurTemplateVo);
	}
	
	public void updateDeptGoodTemplate(GoodsPurTemplateVo goodsPurTemplateVo){
		mapper.updateDeptGoodTemplate(goodsPurTemplateVo);
	}
}
