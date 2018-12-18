package gyqx.spdherp.productsInfos.controller;


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
import gyqx.spdherp.po.HosCostCode;
import gyqx.spdherp.productsInfos.service.IHosCostCodeService;
import gyqx.spdherp.productsInfos.vo.HosCostCodeVo;

@Controller
@RequestMapping(value = "productsInfos/hosCostCode")
public class HosCostCodeController extends BaseController 
{
	@Resource
	private IHosCostCodeService  hosCostCodeService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid HosCostCode hosCostCode ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosCostCode ret = hosCostCodeService.add(hosCostCode);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosCostCode ret = hosCostCodeService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid HosCostCode hosCostCode ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosCostCode ret = hosCostCodeService.update(hosCostCode);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid HosCostCode hosCostCode ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HosCostCode> ret = hosCostCodeService.list(hosCostCode);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String,String>> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HosCostCode> ret = hosCostCodeService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<HosCostCode> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<HosCostCode> ret = hosCostCodeService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	
	@RequestMapping(value = "listVoByPage")
	@ResponseBody
	public AjaxResult listVoByPage(@RequestBody @Valid QueryInfo<HosCostCodeVo> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<HosCostCodeVo> ret = hosCostCodeService.listByPage_HosCostCodeGoods(queryInfo);
		result.setData(ret);
		return result;
	}
}
