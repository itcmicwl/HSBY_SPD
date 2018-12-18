package gyqx.spdhdi.orderMgr.controller;


import java.util.List;

import javax.annotation.Resource;

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
import gyqx.spdhdi.orderMgr.service.IDistrBillService;
import gyqx.spdhdi.orderMgr.vo.DistrBillVo;

@Controller
@RequestMapping(value = "orderMgr/distrBill")
public class DistrBillController extends BaseController 
{
	@Resource
	private IDistrBillService  distrBillService;

	@RequestMapping(value = "distrBillByBillId/{billId}")
	@ResponseBody
	public AjaxResult<DistrBillVo> getDistrBill(@PathVariable("billId") String billId)  throws  Exception  
	{
		AjaxResult<DistrBillVo> result = new AjaxResult<DistrBillVo>();	
		DistrBillVo ret = distrBillService.getByBillId(billId);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "unRecdistrBillByBillId/{billId}")
	@ResponseBody
	public AjaxResult<DistrBillVo> unRecdistrBillByBillId(@PathVariable("billId") String billId)  throws  Exception
	{
		AjaxResult<DistrBillVo> result = new AjaxResult<DistrBillVo>();
		DistrBillVo ret = distrBillService.unRecDistrBillByBillId(billId);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "distrBillById/{id}")
	@ResponseBody
	public AjaxResult<DistrBillVo> getDistrBillById(@PathVariable("id") String id)  throws  Exception  
	{
		AjaxResult<DistrBillVo> result = new AjaxResult<DistrBillVo>();	
		DistrBillVo ret = distrBillService.getById(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult<List<DistrBillVo>> list(@RequestBody DistrBillVo distrBill ,Errors error  )  throws  Exception  
	{
		AjaxResult<List<DistrBillVo>> result = new AjaxResult<List<DistrBillVo>>();
		filterErrors(error);
		List<DistrBillVo> ret = distrBillService.list(distrBill);
		result.setData(ret);
		return result;
	}	
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody QueryInfo<DistrBillVo> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<DistrBillVo> ret = distrBillService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "get/{uniqueCode}")
	@ResponseBody
	public AjaxResult existUniqueCode(@PathVariable("uniqueCode") String uniqueCode)  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		boolean ret = distrBillService.existUniqueCode(uniqueCode);
		result.setData(ret);
		return result;
	}
}
