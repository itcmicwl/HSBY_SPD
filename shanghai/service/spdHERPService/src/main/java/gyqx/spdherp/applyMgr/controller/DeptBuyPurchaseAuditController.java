package gyqx.spdherp.applyMgr.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.applyMgr.Constance;
import gyqx.spdherp.applyMgr.service.IDeptBuyMainService;
import gyqx.spdherp.applyMgr.vo.DeptBuyBillVo;

/**
 * 请购审核
 * @author tianyunpeng
 * @date 2017-11-17
 * @desc 本类主要用来做请购审核的查询和审核提交操作
 */
@Controller
@RequestMapping(value = "applyMgr/purchaseAudit")
public class DeptBuyPurchaseAuditController extends BaseController 
{
	@Resource
	private IDeptBuyMainService  deptBuyMainService;

	/**
	 * 请购审核查询
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "listVoByPage")
	@ResponseBody
	public AjaxResult listVoByPage(@RequestBody @Valid QueryInfo<DeptBuyBillVo> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
//		if(queryInfo.getQueryObject().getBuyKind()==0){
//			queryInfo.getQueryObject().setBuyKind(null);
//		}
		DeptBuyBillVo deptBuyBillVo = queryInfo.getQueryObject();
		deptBuyBillVo.setAuditType(Constance.DEPTAPPLAY_AUDIT_TYPE_PURCHASE_AUDIT);
		QueryResult<DeptBuyBillVo> ret = deptBuyMainService.getVoList(queryInfo);
		result.setData(ret);
		return result;
	}

	/**
	 * 请购审核审核提交
	 * @param userId
	 * @param billVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "submitBill")
	@ResponseBody
	public AjaxResult submitBill(@RequestBody DeptBuyBillVo billVo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		deptBuyMainService.processSubmitBill(billVo,Constance.DEPTAPPLAY_MAIN_STATUS_COMMITED);
		return result;
	}
	
	/**
	 * 请购审核审核批量提交
	 * @param userId
	 * @param billVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "batchSubmitBill")
	@ResponseBody
	public AjaxResult batchSubmitBill(@RequestBody List<DeptBuyBillVo> billVoList ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		deptBuyMainService.processBatchSubmitBill(billVoList,Constance.DEPTAPPLAY_MAIN_STATUS_COMMITED);
		return result;
	}
}
