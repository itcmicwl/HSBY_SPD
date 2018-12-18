package gyqx.spdherp.stocInfoMgr.controller;

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
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.GoodsPurTemplateGoods;
import gyqx.spdherp.po.StockShelf;
import gyqx.spdherp.stocInfoMgr.service.IStocShelfGoodsInfoService;
import gyqx.spdherp.stocInfoMgr.vo.QueryResultVo;
import gyqx.spdherp.stocInfoMgr.vo.QueryVo;
import gyqx.spdherp.stocInfoMgr.vo.StockShelfGoodsInfoVo;
import gyqx.spdherp.stocInfoMgr.vo.StockShelfGoodsVo;
import gyqx.spdherp.stocInfoMgr.vo.StockShelfVo;

@Controller
@RequestMapping(value = "/baseData/stocInfoMgr/stocShelfGoodsInfo")
public class StocShelfGoodsInfoController extends BaseController {
	
	@Resource
	private IStocShelfGoodsInfoService service;
	
	/**
	 * 批量导入科室模板产品
	 * @param stocInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="importStockShelfGoods")
	@ResponseBody
	public AjaxResult<List<GoodsPurTemplateGoods>> importStockShelfGoods(@RequestBody StockShelfGoodsVo param) throws Exception{
		AjaxResult<List<GoodsPurTemplateGoods>> result = new AjaxResult<List<GoodsPurTemplateGoods>>();
		List<GoodsPurTemplateGoods> list = service.importStockShelfGoods(param);
		result.setData(list);
		return result;
	}
	
	/**
	 * 产品模板删除
	 * @param goodsPurTemplate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="deleteStockShelfGoods")
	@ResponseBody
	public AjaxResult<List<HosGoodsInfoVo>> deleteStockShelfGoods(@RequestBody List<HosGoodsInfoVo> hosGoodsInfoVos) throws Exception{
		AjaxResult<List<HosGoodsInfoVo>> result = new AjaxResult<List<HosGoodsInfoVo>>();
		List<HosGoodsInfoVo> beans = service.deleteStockShelfGoods(hosGoodsInfoVos);
		result.setData(beans);
		return result;
	}
	
	/**
	 * 查询库房对应科室产品列表
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listByPage")
	@ResponseBody
	public AjaxResult<QueryResult<QueryResultVo>> listByPage(@RequestBody QueryInfo<QueryVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<QueryResultVo>> result = new AjaxResult<QueryResult<QueryResultVo>>();
		QueryResult<QueryResultVo> data = service.listByPage(queryInfo);
		result.setData(data);
		return result;
	}
	
	
	/**
	 * 查询货位产品信息列表
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryStockShelfGoodsList")
	@ResponseBody
	public AjaxResult<QueryResult<HosGoodsInfoVo>> queryStockShelfGoodsList(@RequestBody QueryInfo<StockShelfGoodsInfoVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<HosGoodsInfoVo>> result = new AjaxResult<QueryResult<HosGoodsInfoVo>>();
		QueryResult<HosGoodsInfoVo> data = service.queryStockShelfGoodsList(queryInfo);
		result.setData(data);
		return result;
	}
	
	/**
	 * 查询医院区位、货位信息
	 * @param corpId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="queryStockShelfList")
	@ResponseBody
	public AjaxResult<List<StockShelfVo>> queryStockShelfList(@RequestBody StockShelf paramObj) throws Exception{
		AjaxResult<List<StockShelfVo>> result = new AjaxResult<List<StockShelfVo>>();
		List<StockShelfVo> stocInfoList = service.queryStockShelfList(paramObj);
		result.setData(stocInfoList);
		return result;
	}
	
}
