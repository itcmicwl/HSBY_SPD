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
import gyqx.spdherp.deptMgr.service.IHosDeptApplyUpperService;
import gyqx.spdherp.deptMgr.vo.HosDeptApplyUperQueryVo;
import gyqx.spdherp.po.HosDeptApplyUpper;

@Controller
@RequestMapping(value = "deptMgr/hosDeptApplyUpper")
public class HosDeptApplyUpperController extends BaseController 
{
	@Resource
	private IHosDeptApplyUpperService  hosDeptApplyUpperService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid HosDeptApplyUpper hosDeptApplyUpper ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosDeptApplyUpper ret = hosDeptApplyUpperService.add(hosDeptApplyUpper);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosDeptApplyUpper ret = hosDeptApplyUpperService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid HosDeptApplyUpper hosDeptApplyUpper ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosDeptApplyUpper ret = hosDeptApplyUpperService.update(hosDeptApplyUpper);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid HosDeptApplyUpper hosDeptApplyUpper ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HosDeptApplyUpper> ret = hosDeptApplyUpperService.list(hosDeptApplyUpper);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String,String>> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HosDeptApplyUpper> ret = hosDeptApplyUpperService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<HosDeptApplyUpper> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<HosDeptApplyUpper> ret = hosDeptApplyUpperService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	
	@RequestMapping(value = "queryHosDeptApplyUpperList")
	@ResponseBody
	public AjaxResult<QueryResult<HosDeptApplyUperQueryVo>> queryHosDeptApplyUpperList(@RequestBody QueryInfo<HosDeptApplyUpper> queryInfo )  throws  Exception  
	{
		AjaxResult<QueryResult<HosDeptApplyUperQueryVo>> result = new AjaxResult<QueryResult<HosDeptApplyUperQueryVo>>();
		QueryResult<HosDeptApplyUperQueryVo> ret = hosDeptApplyUpperService.queryHosDeptApplyUpperList(queryInfo);
		result.setData(ret);
		return result;
	}
}
