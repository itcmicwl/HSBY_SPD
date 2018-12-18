package gyqx.spdherp.deptMgr.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.deptMgr.service.IDeptGoodsTemplateMgrService;
import gyqx.spdherp.deptMgr.vo.GoodsPurTemplateVo;
import gyqx.spdherp.deptMgr.vo.ImportDeptTemplateGoodsParam;
import gyqx.spdherp.deptMgr.vo.TemplateGoodsParam;
import gyqx.spdherp.deptMgr.vo.TemplateGoodsVo;
import gyqx.spdherp.po.GoodsPurTemplate;
import gyqx.spdherp.po.GoodsPurTemplateGoods;

@Controller
@RequestMapping(value = "deptMgr/deptGoodsTemplate")
public class DeptGoodsTemplateMgrController extends BaseController {
	@Resource
	private IDeptGoodsTemplateMgrService service;
	
	/**
	 * 查询科室产品模板信息
	 * @param stocInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="deptGoodsTemplateQuery")
	@ResponseBody
	public AjaxResult<List<GoodsPurTemplate>> deptGoodsTemplateQuery(@RequestBody GoodsPurTemplate goodsPurTemplate) throws Exception{
		AjaxResult<List<GoodsPurTemplate>> result = new AjaxResult<List<GoodsPurTemplate>>();
		List<GoodsPurTemplate> list = service.deptGoodsTemplateQuery(goodsPurTemplate.getHosId(),goodsPurTemplate.getDeptId());
		result.setData(list);
		return result;
	}
	
	/**
	 * 查询科室产品模板信息（我要请购）
	 * @param stocInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="deptGoodsTemplateQuery4DeptBy")
	@ResponseBody
	public AjaxResult<List<GoodsPurTemplate>> deptGoodsTemplateQuery4DeptBy(@RequestBody GoodsPurTemplate goodsPurTemplate) throws Exception{
		AjaxResult<List<GoodsPurTemplate>> result = new AjaxResult<List<GoodsPurTemplate>>();
		List<GoodsPurTemplate> list = service.deptGoodsTemplateQuery4DeptBy(goodsPurTemplate.getHosId(),goodsPurTemplate.getDeptId());
		result.setData(list);
		return result;
	}	
	
	/**
	 * 产品模板添加
	 * @param goodsPurTemplate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="deptGoodsTemplateAdd")
	@ResponseBody
	public AjaxResult<GoodsPurTemplate> deptGoodsTemplateAdd(@RequestBody GoodsPurTemplateVo goodsPurTemplateVo) throws Exception{
		AjaxResult<GoodsPurTemplate> result = new AjaxResult<GoodsPurTemplate>();
		GoodsPurTemplate bean = service.deptGoodsTemplateAdd(goodsPurTemplateVo);
		result.setData(bean);
		return result;
	}
	
	/**
	 * 产品模板编辑
	 * @param goodsPurTemplate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="deptGoodsTemplateEdit")
	@ResponseBody
	public AjaxResult<GoodsPurTemplateVo> deptGoodsTemplateEdit(@RequestBody GoodsPurTemplateVo goodsPurTemplateVo) throws Exception{
		AjaxResult<GoodsPurTemplateVo> result = new AjaxResult<GoodsPurTemplateVo>();
		GoodsPurTemplateVo bean = service.deptGoodsTemplateEdit(goodsPurTemplateVo);
		result.setData(bean);
		return result;
	}
	
	/**
	 * 产品模板删除
	 * @param goodsPurTemplate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="deptGoodsTemplateDelete")
	@ResponseBody
	public AjaxResult<List<GoodsPurTemplate>> deptGoodsTemplateDelete(@RequestBody List<GoodsPurTemplate> goodsPurTemplates) throws Exception{
		AjaxResult<List<GoodsPurTemplate>> result = new AjaxResult<List<GoodsPurTemplate>>();
		List<GoodsPurTemplate> beans = service.deptGoodsTemplateDelete(goodsPurTemplates);
		result.setData(beans);
		return result;
	}
	
	/**
	 * 查询科室模板产品信息
	 * @param stocInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="queryDeptTemplateGoods")
	@ResponseBody
	public AjaxResult<QueryResult<TemplateGoodsVo>> queryDeptTemplateGoods(@RequestBody QueryInfo<TemplateGoodsParam> queryInfo) throws Exception{
		AjaxResult<QueryResult<TemplateGoodsVo>> result = new AjaxResult<QueryResult<TemplateGoodsVo>>();
		QueryResult<TemplateGoodsVo> list = service.queryDetpTemplateGoods(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * 批量导入科室模板产品
	 * @param stocInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="importDeptTemplateGoods")
	@ResponseBody
	public AjaxResult<List<GoodsPurTemplateGoods>> importDeptTemplateGoods(@RequestBody ImportDeptTemplateGoodsParam param) throws Exception{
		AjaxResult<List<GoodsPurTemplateGoods>> result = new AjaxResult<List<GoodsPurTemplateGoods>>();
		List<GoodsPurTemplateGoods> list = service.importDeptTemplateGoods(param);
		result.setData(list);
		return result;
	}
	
	/**
	 * 更新产品数量/定数包数量
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateGoodsNum")
	@ResponseBody
	public AjaxResult<BigDecimal> updateGoodsNum(@RequestBody GoodsPurTemplateGoods param) throws Exception{
		AjaxResult<BigDecimal> result = new AjaxResult<BigDecimal>();
		BigDecimal updateGoodsNum = service.updateGoodsNum(param);
		result.setData(updateGoodsNum);
		return result;
	}
	
	/**
	 * 移除科室模板产品信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteDetpTemplateGoods")
	@ResponseBody
	public AjaxResult<GoodsPurTemplateGoods> deleteDetpTemplateGoods(@RequestBody GoodsPurTemplateGoods bean)
			throws Exception {
		AjaxResult<GoodsPurTemplateGoods> result = new AjaxResult<GoodsPurTemplateGoods>();
		GoodsPurTemplateGoods res = service.deleteDetpTemplateGoods(bean);
		result.setData(res);
		return result;
	}
	
	/**
	 * 批量移除科室模板产品信息
	 * @param templateGoods
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "mulDeleteDetpTemplateGoods")
	@ResponseBody
	public AjaxResult<List<TemplateGoodsVo>> mulDeleteDetpTemplateGoods(@RequestBody List<TemplateGoodsVo> templateGoods)
			throws Exception {
		AjaxResult<List<TemplateGoodsVo>> result = new AjaxResult<List<TemplateGoodsVo>>();
		List<TemplateGoodsVo> res = service.mulDeleteDetpTemplateGoods(templateGoods);
		result.setData(res);
		return result;
	}

}
