package gyqx.spdherp.sickerMgr.controller;


import javax.annotation.Resource;
import javax.validation.Valid;

import gyqx.spdherp.po.HisKs;
import gyqx.spdherp.sickerMgr.vo.SickerInHospitalInfoVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.po.SickerInHospitalInfo;
import gyqx.spdherp.sickerMgr.service.ISickerInHospitalInfoService;

import java.util.List;
import java.util.Map;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Controller
@RequestMapping(value = "sickerMgr/sickerInHospitalInfo")
public class SickerInHospitalInfoController extends BaseController 
{
	@Resource
	private ISickerInHospitalInfoService  sickerInHospitalInfoService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid SickerInHospitalInfo sickerInHospitalInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		SickerInHospitalInfo ret = sickerInHospitalInfoService.add(sickerInHospitalInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		SickerInHospitalInfo ret = sickerInHospitalInfoService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid SickerInHospitalInfo sickerInHospitalInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		SickerInHospitalInfo ret = sickerInHospitalInfoService.update(sickerInHospitalInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid SickerInHospitalInfoVo sickerInHospitalInfo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<SickerInHospitalInfoVo> ret = sickerInHospitalInfoService.list(sickerInHospitalInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String,String>> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<SickerInHospitalInfo> ret = sickerInHospitalInfoService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody QueryInfo<SickerInHospitalInfoVo> queryInfo , Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<SickerInHospitalInfoVo> ret = sickerInHospitalInfoService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "getHisDepts")
	@ResponseBody
	public AjaxResult getHisDepts(@RequestBody HisKs hisKs ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HisKs> ret = sickerInHospitalInfoService.getHisDepts(hisKs);
		result.setData(ret);
		return result;
	}




}
