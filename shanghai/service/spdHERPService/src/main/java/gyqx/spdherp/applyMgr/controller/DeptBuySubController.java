package gyqx.spdherp.applyMgr.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import gyqx.spdherp.applyMgr.vo.DeptBuyBillVo;
import gyqx.spdherp.applyMgr.vo.ProvGoodsKindsVo;
import gyqx.spdherp.provManager.vo.HosCollectorProvVo;
import gyqx.spdherp.stockout.vo.OutStockBillVo;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.applyMgr.service.IDeptBuySubService;
import gyqx.spdherp.applyMgr.vo.DeptBuySubVo;
import gyqx.spdherp.applyMgr.vo.DeptCarGoodsStVo;
import gyqx.spdherp.po.DeptBuySub;

@Controller
@RequestMapping(value = "applyMgr/deptBuySub")
public class DeptBuySubController extends BaseController 
{
	@Resource
	private IDeptBuySubService  deptBuySubService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid DeptBuySub deptBuySub ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		DeptBuySub ret = deptBuySubService.add(deptBuySub);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		DeptBuySub ret = deptBuySubService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid DeptBuySub deptBuySub ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		DeptBuySub ret = deptBuySubService.update(deptBuySub);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid DeptBuySub deptBuySub ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<DeptBuySub> ret = deptBuySubService.list(deptBuySub);
		result.setData(ret);
		return result;
	}
	@GetMapping("/getVoListByOut")
	@ResponseBody
    public AjaxResult getBillDetail(@RequestParam("id") String id) {
        AjaxResult result = new AjaxResult();
        List<DeptBuySubVo> buySubVos = deptBuySubService.getVoListByOut(id);
        result.setData(buySubVos);
        return result;
    }
	
	
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String,String>> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<DeptBuySub> ret = deptBuySubService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<DeptBuySub> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<DeptBuySub> ret = deptBuySubService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listVoByPage")
	@ResponseBody
	public AjaxResult listVoByPage(@RequestBody @Valid QueryInfo<DeptBuySubVo> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<DeptBuySubVo> ret = deptBuySubService.listVoByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	
	@RequestMapping(value = "getDetailsVo4Pur")
	@ResponseBody
	public AjaxResult getDetailsVo4Pur(@RequestBody @Valid DeptBuySubVo deptBuySubVo  ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<DeptBuySubVo> ret = deptBuySubService.getDetailsVo4Pur(deptBuySubVo);
		result.setData(ret);
		return result;
	}
	
	/**
	 * 查询科室审核通过后的请购明细（三级科室合并）
	 * @param deptBuySubVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDetailsVo4Merger")
	@ResponseBody
	public AjaxResult getDetailsVo4Merger(@RequestBody @Valid DeptBuySubVo deptBuySubVo  ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<DeptBuySubVo> ret = deptBuySubService.getDetailsVo4Merger(deptBuySubVo);
		result.setData(ret);
		return result;
	}
	
	
	
	@RequestMapping(value = "dealBill4Pur")
	@ResponseBody
	public AjaxResult dealBill4Pur(@RequestBody @Valid  List<DeptBuySubVo> voList  ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
	    deptBuySubService.updateBill4Pur(voList);
		
		return result;
	}

	@RequestMapping(value = "getProvQGList")
	@ResponseBody
	public AjaxResult getProvQGList(@RequestBody DeptBuySubVo deptBuyBillVo)  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		List<ProvGoodsKindsVo> ret = deptBuySubService.getProvQGList(deptBuyBillVo);
		result.setData(ret);
		return result;
	}
}
