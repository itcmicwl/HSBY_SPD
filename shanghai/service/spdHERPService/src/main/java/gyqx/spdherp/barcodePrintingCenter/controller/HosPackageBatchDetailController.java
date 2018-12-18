package gyqx.spdherp.barcodePrintingCenter.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.po.HosPackageBatchDetail;
import gyqx.spdherp.barcodePrintingCenter.service.IHosPackageBatchDetailService;

import java.util.List;
import java.util.Map;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Controller
@RequestMapping(value = "barcodePrintingCenter/hosPackageBatchDetail")
public class HosPackageBatchDetailController extends BaseController 
{
	@Resource
	private IHosPackageBatchDetailService  hosPackageBatchDetailService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid HosPackageBatchDetail hosPackageBatchDetail ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosPackageBatchDetail ret = hosPackageBatchDetailService.add(hosPackageBatchDetail);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosPackageBatchDetail ret = hosPackageBatchDetailService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid HosPackageBatchDetail hosPackageBatchDetail ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosPackageBatchDetail ret = hosPackageBatchDetailService.update(hosPackageBatchDetail);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid HosPackageBatchDetail hosPackageBatchDetail ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HosPackageBatchDetail> ret = hosPackageBatchDetailService.list(hosPackageBatchDetail);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String,String>> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HosPackageBatchDetail> ret = hosPackageBatchDetailService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<HosPackageBatchDetail> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<HosPackageBatchDetail> ret = hosPackageBatchDetailService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
}
