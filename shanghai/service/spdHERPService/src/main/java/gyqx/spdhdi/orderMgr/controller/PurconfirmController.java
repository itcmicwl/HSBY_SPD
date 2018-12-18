package gyqx.spdhdi.orderMgr.controller;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.orderMgr.service.IPurconfirmService;
import gyqx.spdhdi.orderMgr.vo.PurconfirmListVo;
import gyqx.spdhdi.orderMgr.vo.PurconfirmVo;
import gyqx.ws.yg.common.YgServices;
import gyqx.ws.yg.vo.repVo.YY111_REP_XML;
import gyqx.ws.yg.vo.repVo.YY112_REP_XML;
import gyqx.ws.yg.vo.repVo.YY159_REP_XML;
import gyqx.ws.yg.vo.reqVo.YY111_REQ_XML;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "orderMgr/purconfirm")
public class PurconfirmController extends BaseController 
{
	@Resource
	private IPurconfirmService  purconfirmService;

	@RequestMapping(value = "sendPurToYg")
	@ResponseBody
	public AjaxResult<YY111_REP_XML> sendPurToYg(@RequestBody @Valid YY111_REQ_XML purconfirm , Errors error  )  throws  Exception
	{
		AjaxResult<YY111_REP_XML> result = new AjaxResult<>();
		filterErrors(error);
		//PurconfirmVo ret = purconfirmService.add(purconfirm);
		//result.setData(ret);
		YY111_REP_XML res = purconfirmService.submitPurListToYg(purconfirm);
		result.setData(res);
		return result;
	}
	@RequestMapping(value = "confirmPurToYg/{billId}",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult<YY112_REP_XML> confirmPurToYg(@PathVariable("billId") String billId)  throws  Exception
	{
		AjaxResult<YY112_REP_XML> result = new AjaxResult<>();
		YY112_REP_XML res = purconfirmService.updatePurToYg(billId);
		result.setData(res);
		return result;
	}
    @RequestMapping(value = "getPurYgStatusByYgBillId/{ygBillId}",method=RequestMethod.GET)
    @ResponseBody
    public AjaxResult<YY159_REP_XML> getPurYgStatusByYgBillId(@PathVariable("ygBillId") String ygBillId)  throws  Exception
    {
        AjaxResult<YY159_REP_XML> result = new AjaxResult<>();
        YY159_REP_XML res = purconfirmService.getPurStatusByBillNo(ygBillId);
        result.setData(res);
        return result;
    }
	@RequestMapping(value = "getById/{id}")
	@ResponseBody
	public AjaxResult<PurconfirmVo> getById(@PathVariable("id") String id ,Errors error  )  throws  Exception  
	{
		AjaxResult<PurconfirmVo> result = new AjaxResult<PurconfirmVo>();
		filterErrors(error);
		PurconfirmVo ret = purconfirmService.getById(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "getByBillid/{billid}",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult<PurconfirmVo> getByBillid(@PathVariable String billid) throws  Exception  
	{
		AjaxResult<PurconfirmVo> result = new AjaxResult<PurconfirmVo>();
		PurconfirmVo ret = purconfirmService.getByBillid(billid);
		result.setData(ret);
		return result;
	}
	

	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult<List<PurconfirmVo>> list(@RequestBody @Valid PurconfirmVo purconfirm ,Errors error  )  throws  Exception  
	{
		AjaxResult<List<PurconfirmVo>> result = new AjaxResult<List<PurconfirmVo>>();
		filterErrors(error);
		List<PurconfirmVo> ret = purconfirmService.list(purconfirm);
		result.setData(ret);
		return result;
	}	
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult<QueryResult<PurconfirmVo>> listByPage(@RequestBody @Valid QueryInfo<PurconfirmVo> queryInfo)  throws  Exception  
	{
		AjaxResult<QueryResult<PurconfirmVo>> result = new AjaxResult<QueryResult<PurconfirmVo>>();
		PurconfirmVo p = queryInfo.getQueryObject();
		if(p.getBillId().indexOf(",")>0){
			p.setArrBillIds(p.getBillId().split(","));
			p.setBillId(null);
		}
		QueryResult<PurconfirmVo> ret = purconfirmService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "purConfirmDetail/{billId}",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult<List<PurconfirmListVo>> getPurConfirmDetail(@PathVariable String billId)  throws  Exception  
	{
		AjaxResult<List<PurconfirmListVo>> result = new AjaxResult<List<PurconfirmListVo>>();
		PurconfirmListVo plv = new PurconfirmListVo();
		plv.setBillId(billId);
		List<PurconfirmListVo> ret = purconfirmService.detailList(plv);
		result.setData(ret);
		return result;
	}
}
