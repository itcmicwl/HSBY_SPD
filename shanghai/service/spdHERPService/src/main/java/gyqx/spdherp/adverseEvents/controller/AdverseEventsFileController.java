package gyqx.spdherp.adverseEvents.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.adverseEvents.service.IAdverseEventsFileService;
import gyqx.spdherp.po.AdverseEvents;
import gyqx.spdherp.po.AdverseEventsFile;

@Controller
@RequestMapping(value = "adverseEvents/adverseEventsFile")
public class AdverseEventsFileController extends BaseController 
{
	@Resource
	private IAdverseEventsFileService  adverseEventsFileService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid AdverseEventsFile adverseEventsFile ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		AdverseEventsFile ret = adverseEventsFileService.add(adverseEventsFile);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		AdverseEventsFile ret = adverseEventsFileService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid AdverseEventsFile adverseEventsFile ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		AdverseEventsFile ret = adverseEventsFileService.update(adverseEventsFile);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid AdverseEvents adverseEvents ,Errors error)  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<AdverseEventsFile> ret = adverseEventsFileService.list(adverseEvents);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String,String>> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<AdverseEventsFile> ret = adverseEventsFileService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}
}
