package gyqx.spdherp.sickerMgr.controller;


import javax.annotation.Resource;
import javax.validation.Valid;

import gyqx.spdherp.sickerMgr.vo.SickerOperatorInfoVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.po.SickerOperatorInfo;
import gyqx.spdherp.sickerMgr.service.ISickerOperatorInfoService;

import java.util.List;
import java.util.Map;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Controller
@RequestMapping(value = "sickerMgr/sickerOperatorInfo")
public class SickerOperatorInfoController extends BaseController 
{
	@Resource
	private ISickerOperatorInfoService  sickerOperatorInfoService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid SickerOperatorInfo sickerOperatorInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		SickerOperatorInfo ret = sickerOperatorInfoService.add(sickerOperatorInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		SickerOperatorInfo ret = sickerOperatorInfoService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid SickerOperatorInfo sickerOperatorInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		SickerOperatorInfo ret = sickerOperatorInfoService.update(sickerOperatorInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid SickerOperatorInfoVo sickerOperatorInfo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<SickerOperatorInfoVo> ret = sickerOperatorInfoService.list(sickerOperatorInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String,String>> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<SickerOperatorInfo> ret = sickerOperatorInfoService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody QueryInfo<SickerOperatorInfoVo> queryInfo , Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<SickerOperatorInfoVo> ret = sickerOperatorInfoService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "getOperDepts")
	@ResponseBody
	public AjaxResult getOperDepts(@RequestBody SickerOperatorInfo sickerOperatorInfo,Errors error) throws Exception{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<SickerOperatorInfo> ret = sickerOperatorInfoService.getOperDepts(sickerOperatorInfo);
		result.setData(ret);
		return result;
	}

}
