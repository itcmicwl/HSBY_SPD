package gyqx.spdherp.stockout.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.stockout.service.ISickerUseListService;
import gyqx.spdherp.stockout.service.ISickerUseService;
import gyqx.spdherp.stockout.vo.SickerUserVo;
import gyqx.spdherp.stockout.vo.Sickuse4print;

@Controller
@RequestMapping(value = "stockout/sickerUse")
public class SickerUseController extends BaseController 
{
	@Resource
	private ISickerUseService  sickerUseService;
	@Resource
	private ISickerUseListService  sickerUseListService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid SickerUserVo sickerUseVo , Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		int ret = sickerUseService.add(sickerUseVo);
		if(ret>0){
			int code = 0;
			result.setCode(code);
		};
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "getById")
	@ResponseBody
	public AjaxResult get(@RequestParam("id") String id)  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		SickerUserVo ret = sickerUseService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid SickerUserVo sickerUse ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		int ret = sickerUseService.update(sickerUse);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid QueryInfo<SickerUserVo> queryInfo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<SickerUserVo> ret = sickerUseService.list(queryInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<SickerUserVo> queryInfo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<SickerUserVo> ret = sickerUseService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "getSickuserList4Print")
	@ResponseBody
	public AjaxResult getSickuserList4Print(@RequestParam("billId") String billId)  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		Sickuse4print ret = sickerUseService.getSickuserList4Print(billId);
		result.setData(ret);
		return result;
	}
}
