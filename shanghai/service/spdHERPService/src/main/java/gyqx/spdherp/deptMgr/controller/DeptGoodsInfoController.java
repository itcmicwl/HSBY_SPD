package gyqx.spdherp.deptMgr.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import gyqx.spdherp.deptMgr.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.deptMgr.service.IDeptGoodsInfoService;
import gyqx.spdherp.po.DeptGoodsInfo;

@Controller
@RequestMapping(value = "deptMgr/deptGoodsInfo")
public class DeptGoodsInfoController extends BaseController {
	@Resource
	private IDeptGoodsInfoService service;

	@RequestMapping(value = "deptGoodsInfoList")
	@ResponseBody
	public AjaxResult<QueryResult<DeptGoodsInfoVo>> deptGoodsInfoList(@RequestBody QueryInfo<DeptGoodsInfoVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<DeptGoodsInfoVo>> result = new AjaxResult<QueryResult<DeptGoodsInfoVo>>();
		QueryResult<DeptGoodsInfoVo> list = service.deptGoodsInfoList(queryInfo);
		result.setData(list);
		return result;
	}

	@RequestMapping(value = "deptGoodsInfoEdit")
	@ResponseBody
	public AjaxResult<DeptGoodsInfo> deptGoodsInfoEdit(@RequestBody DeptGoodsInfo deptGoodsInfo) throws Exception {
		AjaxResult<DeptGoodsInfo> result = new AjaxResult<DeptGoodsInfo>();
		DeptGoodsInfo res = service.deptGoodsInfoEdit(deptGoodsInfo);
		result.setData(res);
		return result;
	}

	@RequestMapping(value = "deptGoodsInfoDelete")
	@ResponseBody
	public AjaxResult<DeptGoodsInfo> deptGoodsInfoDelete(@RequestBody DeptGoodsInfo deptGoodsInfo) throws Exception {
		AjaxResult<DeptGoodsInfo> result = new AjaxResult<DeptGoodsInfo>();
		DeptGoodsInfo res = service.deptGoodsInfoDelete(deptGoodsInfo);
		result.setData(res);
		return result;
	}

	@RequestMapping(value = "deptGoodsInfoMulDelete")
	@ResponseBody
	public AjaxResult<List<DeptGoodsInfo>> deptGoodsInfoMulDelete(@RequestBody List<DeptGoodsInfo> deptGoodsInfos)
			throws Exception {
		AjaxResult<List<DeptGoodsInfo>> result = new AjaxResult<List<DeptGoodsInfo>>();
		List<DeptGoodsInfo> res = service.deptGoodsInfoMulDelete(deptGoodsInfos);
		result.setData(res);
		return result;
	}

	@RequestMapping(value = "deptGoodsInfoImportQuery")
	@ResponseBody
	public AjaxResult<QueryResult<HosGoodsInfo>> deptGoodsInfoImportQuery(
			@RequestBody QueryInfo<DeptGoodsInfoVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<HosGoodsInfo>> result = new AjaxResult<QueryResult<HosGoodsInfo>>();
		QueryResult<HosGoodsInfo> res = service.deptGoodsInfoImportQuery(queryInfo);
		result.setData(res);
		return result;
	}

	@RequestMapping(value = "deptGoodsInfoImport")
	@ResponseBody
	public AjaxResult<List<DeptGoodsInfo>> deptGoodsInfoImport(@RequestBody DeptGoodsInfoImportVo deptGoodsInfoImportVo)
			throws Exception {
		AjaxResult<List<DeptGoodsInfo>> result = new AjaxResult<List<DeptGoodsInfo>>();
		List<DeptGoodsInfo> res = service.deptGoodsInfoImport(deptGoodsInfoImportVo);
		result.setData(res);
		return result;
	}

	@RequestMapping(value = "deptGoodsList4InStock")
	@ResponseBody
	public AjaxResult<QueryResult<DeptGoodsList4InStockVo>> deptGoodsList4InStock(
			@RequestBody QueryInfo<DeptGoodsList4InStockVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<DeptGoodsList4InStockVo>> result = new AjaxResult<QueryResult<DeptGoodsList4InStockVo>>();
		QueryResult<DeptGoodsList4InStockVo> list = service.getDeptGoodsList4InStock(queryInfo);
		result.setData(list);
		return result;
	}

	/**
	 * 用于查询业务员
	 * 
	 * @author LIWENKANG
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getSaleManList")
	@ResponseBody
	public AjaxResult<QueryResult<SaleManVo>> getSaleManList(@RequestBody @Valid QueryInfo<SaleManVo> queryInfo,
			Errors error) throws Exception {
		AjaxResult<QueryResult<SaleManVo>> result = new AjaxResult<QueryResult<SaleManVo>>();
		QueryResult<SaleManVo> data = service.getSaleManList(queryInfo);
		result.setData(data);
		return result;
	}

	@RequestMapping("/getGoodsByErpCode")
	@ResponseBody
	public AjaxResult<DeptGoodsReplaceVo> getGoodsByErpCode(@RequestParam("erpCode") String erpCode,
															@RequestParam("hosId") String hosId
															)throws Exception{
		AjaxResult<DeptGoodsReplaceVo> result = new AjaxResult<>();
		result.setData(service.getGoodsByErpCode(hosId,erpCode));
		return result;
	}
}
