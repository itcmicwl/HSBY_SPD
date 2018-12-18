package gyqx.spdherp.settleCenter.controller;


import javax.annotation.Resource;
import javax.validation.Valid;

import gyqx.spdherp.settleCenter.vo.HosOutBalanceVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.po.HosOutBalance;
import gyqx.spdherp.settleCenter.service.IHosOutBalanceService;

import java.util.List;
import java.util.Map;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Controller
@RequestMapping(value = "settleCenter/hosOutBalance")
public class HosOutBalanceController extends BaseController 
{
	@Resource
	private IHosOutBalanceService  hosOutBalanceService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody HosOutBalanceVo hosOutBalance , Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		//String  ret = hosOutBalanceService.add(hosOutBalance);
		List<String> ret = hosOutBalanceService.createSettleBill(hosOutBalance);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "getById/{id}")
	@ResponseBody
	public AjaxResult getById(@PathVariable("id") String id ,Errors error)  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosOutBalance ret = hosOutBalanceService.getById(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "getByBillId/{billId}")
	@ResponseBody
	public AjaxResult getByBillId(@PathVariable("billId") String billId)  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		HosOutBalance ret = hosOutBalanceService.getByBillId(billId);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody HosOutBalanceVo hosOutBalance ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosOutBalance ret = hosOutBalanceService.update(hosOutBalance);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid HosOutBalanceVo hosOutBalance ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HosOutBalanceVo> ret = hosOutBalanceService.list(hosOutBalance);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody QueryInfo<HosOutBalanceVo> queryInfo )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		QueryResult<HosOutBalanceVo> ret = hosOutBalanceService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
}
