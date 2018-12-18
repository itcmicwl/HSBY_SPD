package gyqx.spdhdi.orderMgr.controller;

import javax.annotation.Resource;

import gyqx.spdherp.stockPile.vo.DeptGoodsVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.orderMgr.service.IPurchaseListService;
import gyqx.spdhdi.orderMgr.service.IPurchaseService;
import gyqx.spdhdi.orderMgr.vo.PurchaseVo;

import java.util.List;

@Controller
@RequestMapping(value = "order/purchase")
public class PurchaseController extends BaseController {
	@Resource
	IPurchaseService purchaseService;
	@Resource
	IPurchaseListService purchaseListService;	

	@RequestMapping(value = "purchasePage")
	@ResponseBody
	public AjaxResult<QueryResult<PurchaseVo>> getSysProjectList(@RequestBody QueryInfo<PurchaseVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<PurchaseVo>> result = new AjaxResult<QueryResult<PurchaseVo>>();
		QueryResult<PurchaseVo> lstSysProject = purchaseService.getPurchaseList(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	@RequestMapping(value="getPurchase/{billid}",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult<PurchaseVo> getPurchase(@PathVariable String billid) throws Exception{
		AjaxResult<PurchaseVo> result = new AjaxResult<PurchaseVo>();
		PurchaseVo res = purchaseService.getPurchaseByBillId(billid);
		result.setData(res);
		return result;
	}
	@RequestMapping(value="getPurchaseById/{id}",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult<PurchaseVo> getPurchaseById(@PathVariable String id) throws Exception{
		AjaxResult<PurchaseVo> result = new AjaxResult<PurchaseVo>();
		PurchaseVo res = purchaseService.getPurByid(id);
		result.setData(res);
		return result;
	}
	@RequestMapping(value = "insertPurchase")
	@ResponseBody
	public AjaxResult<PurchaseVo> insertPurchase(@RequestBody PurchaseVo purchaseVo) throws Exception {
		AjaxResult<PurchaseVo> result = new AjaxResult<PurchaseVo>();
		PurchaseVo res = purchaseService.insertPurchase(purchaseVo);
		result.setData(res);
		return result;
	}
	@RequestMapping(value="updatePurchase")
	@ResponseBody
	public AjaxResult<PurchaseVo> updatePurchase(@RequestBody PurchaseVo purchaseVo) throws Exception{
		AjaxResult<PurchaseVo> result = new AjaxResult<PurchaseVo>();
		PurchaseVo res = purchaseService.updatePurchase(purchaseVo);
		result.setData(res);
		return result;
	}
	@RequestMapping(value="delPurchase")
	@ResponseBody
	public AjaxResult deletePurchase(@RequestBody PurchaseVo purchaseVo) throws Exception{
		AjaxResult result = new AjaxResult();
		int res = purchaseService.deletePurchase(purchaseVo);
		if (res < 1) {
			result.setCode(-1);
			result.setMsg("操作失败！");
		}
		return result;
	}
	@RequestMapping(value = "creatPurBillByStoc")
	@ResponseBody
	public AjaxResult<List<PurchaseVo>> creatPurBillByStoc(@RequestBody List<DeptGoodsVo> deptGoodsVo) throws Exception {
		AjaxResult<List<PurchaseVo>> result = new AjaxResult<>();
		List<PurchaseVo> res = purchaseService.creatPurBillByStoc(deptGoodsVo);
		result.setData(res);
		return result;
	}
    @RequestMapping(value = "audit/{flag}")
    @ResponseBody
    public AjaxResult<List<PurchaseVo>> audit(@RequestBody PurchaseVo purBill,@PathVariable Integer flag) throws Exception {
        AjaxResult result = new AjaxResult<>();
         Integer res = purchaseService.audit(purBill,flag);
        result.setData(res);
        return result;
    }
}
