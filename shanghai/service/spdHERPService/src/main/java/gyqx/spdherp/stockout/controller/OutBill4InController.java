package gyqx.spdherp.stockout.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import gyqx.spdherp.barcodePrintingCenter.vo.DsGoodsVo;
import gyqx.spdherp.stockMgr.service.IInStockService;
import gyqx.spdherp.stockout.vo.Out4UseVo;
import gyqx.spdherp.stockout.vo.SickerUserVo;
import gyqx.spdherp.stockout.vo.vo4In.Out4Settle;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.stockout.service.BillMgrService;
import gyqx.spdherp.stockout.service.OutBill4InService;
import gyqx.spdherp.stockout.vo.vo4In.OutBill4InVo;

import java.util.List;

/**
 * 
 * @author ganwei
 *
 */
@RestController
@RequestMapping(value = "stockMgr/out4In")
public class OutBill4InController extends BaseController {
	@Resource
	private BillMgrService service;
	@Resource
	private OutBill4InService o4iService;
	@Resource
	private IInStockService inStockService;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getBillList4Instock") // <List<OutStockBillVo>>
	@ResponseBody
	public AjaxResult getBillList4Instock(@RequestBody @Valid QueryInfo<OutBill4InVo> queryInfo, Errors error)
			throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<OutBill4InVo> ret = o4iService.getBillList4Instock(queryInfo);
		result.setData(ret);
		return result;
	}

	/**
	 * 查询请购出库单，定数包打包用
	 * @param id
	 * @param isPacket
	 * @return
	 */
	@GetMapping("/getBillDetail4In2")
	public AjaxResult<OutBill4InVo> getBillDetail4In2(@RequestParam("id") String id,@RequestParam("isPacket") String isPacket) {
		AjaxResult<OutBill4InVo> result = new AjaxResult<>();
		result.setData(o4iService.getOutBillDetails(id,isPacket));
		return result;
	}
	
	@GetMapping("/getBillDetail4In")
	public AjaxResult<OutBill4InVo> getBillDetail4In(@RequestParam("id") String id) {
		AjaxResult<OutBill4InVo> result = new AjaxResult<>();
		result.setData(o4iService.getOutBillDetails(id,null));
		return result;
	}
	
	@PostMapping("outBill4settle")
	public AjaxResult outBill4settle(@RequestBody QueryInfo<Out4Settle> queryInfo) throws Exception{
		AjaxResult result = new AjaxResult();
		result.setData(o4iService.outBill4settle(queryInfo));
		return result;
	}
	
	@RequestMapping(value = "getOutBill4Use")
	@ResponseBody
	public AjaxResult getOutBill4Use(@RequestBody @Valid QueryInfo<Out4UseVo> queryInfo) throws Exception{
		AjaxResult <Out4UseVo>result = new AjaxResult<>();
		Out4UseVo ret = o4iService.outBill4Use(queryInfo);
		result.setData(ret);
		return result;
	}

	/**
	 * 查询请购出库单， 查看定数包商品
	 */
	@RequestMapping("getDSGoodsByOutBill")
	public AjaxResult getDSGoodsByOutBill(@RequestBody @Valid QueryInfo<DsGoodsVo> queryInfo) throws Exception{
		AjaxResult result = new AjaxResult();
		QueryResult<DsGoodsVo> ret = o4iService.getDSGoodsByOutBill(queryInfo);
		result.setData(ret);
		return result;
	}
}
