package gyqx.spdherp.deptMgr.controller;


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
import gyqx.spdherp.deptMgr.service.IGoodsPurTemplateUserRelService;
import gyqx.spdherp.po.GoodsPurTemplateUserRel;

@Controller
@RequestMapping(value = "deptMgr/goodsPurTemplateUserRelation")
public class GoodsPurTemplateUserRelationController extends BaseController 
{
	@Resource
	private IGoodsPurTemplateUserRelService  goodsPurTemplateUserRelationService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody GoodsPurTemplateUserRel templateUserRelation ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		goodsPurTemplateUserRelationService.add(templateUserRelation);
		return result;
	}
	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		GoodsPurTemplateUserRel ret = goodsPurTemplateUserRelationService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid GoodsPurTemplateUserRel goodsPurTemplateUserRelation ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		GoodsPurTemplateUserRel ret = goodsPurTemplateUserRelationService.update(goodsPurTemplateUserRelation);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid GoodsPurTemplateUserRel goodsPurTemplateUserRelation ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<GoodsPurTemplateUserRel> ret = goodsPurTemplateUserRelationService.list(goodsPurTemplateUserRelation);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String,String>> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<GoodsPurTemplateUserRel> ret = goodsPurTemplateUserRelationService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<GoodsPurTemplateUserRel> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<GoodsPurTemplateUserRel> ret = goodsPurTemplateUserRelationService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	
	@RequestMapping(value = "selectByTempId/{tempId}")
	@ResponseBody
	public AjaxResult selectByTempId(@PathVariable("tempId") String tempId  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		List<GoodsPurTemplateUserRel> ret = goodsPurTemplateUserRelationService.selectByTempId(tempId);
		result.setData(ret);
		return result;
	}
}
