package gyqx.spdherp.spdOutPtl.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import gyqx.spdherp.spdOutPtl.service.IJdeGoodsStockInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.po.JdeGoodsStockInfo;

import java.util.List;
import java.util.Map;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@RestController
@RequestMapping(value = "/jde/jdeGoodsStockInfo")
public class JdeGoodsStockInfoController extends BaseController 
{
	@Resource
	private IJdeGoodsStockInfoService jdeGoodsStockInfoService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid JdeGoodsStockInfo jdeGoodsStockInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		JdeGoodsStockInfo ret = jdeGoodsStockInfoService.add(jdeGoodsStockInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		JdeGoodsStockInfo ret = jdeGoodsStockInfoService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid JdeGoodsStockInfo jdeGoodsStockInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		JdeGoodsStockInfo ret = jdeGoodsStockInfoService.update(jdeGoodsStockInfo);
		result.setData(ret);
		return result;
	}
	@PostMapping(value = "/list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid JdeGoodsStockInfo jdeGoodsStockInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<JdeGoodsStockInfo> ret = jdeGoodsStockInfoService.list(jdeGoodsStockInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String,String>> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<JdeGoodsStockInfo> ret = jdeGoodsStockInfoService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<JdeGoodsStockInfo> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<JdeGoodsStockInfo> ret = jdeGoodsStockInfoService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
}
