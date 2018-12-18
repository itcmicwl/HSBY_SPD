package gyqx.spdherp.spdOutPtl.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import common.exception.ValidateException;
import gyqx.spdherp.spdOutPtl.service.ISpdInventoryService;
import gyqx.spdherp.spdOutPtl.vo.SpdInventoryVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.po.SpdInventory;
import java.util.List;
import java.util.Map;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@RestController
@RequestMapping(value = "/spdOutPtl/spdInventory")
public class SpdInventoryController extends BaseController 
{
	@Resource
	private ISpdInventoryService spdInventoryService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid SpdInventoryVo spdInventoryVo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		SpdInventoryVo ret = spdInventoryService.add(spdInventoryVo);
		result.setData(ret);
		return result;
	}
	@PostMapping("/getById")
	@ResponseBody
	public AjaxResult get(@RequestBody SpdInventoryVo spdInventoryVo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		SpdInventory ret = spdInventoryService.get(spdInventoryVo.getId());
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid SpdInventory spdInventory ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		String ret = spdInventoryService.update(spdInventory);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid SpdInventoryVo spdInventory ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<SpdInventoryVo> ret = spdInventoryService.list(spdInventory);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String,String>> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<SpdInventory> ret = spdInventoryService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<SpdInventoryVo> queryInfo , Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<SpdInventoryVo> ret = spdInventoryService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "closeBill")
	@ResponseBody
	public AjaxResult closeBill(@RequestBody @Valid SpdInventory spdInventory ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		String ret = spdInventoryService.closeBill(spdInventory.getHosId());
		result.setData(ret);
		return result;
	}
}
