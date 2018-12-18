package gyqx.spdhdi.orderMgr.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.orderMgr.service.IPurchaseStatisticsService;
import gyqx.spdhdi.orderMgr.vo.DeptApplyGoodsAggregateVo;
import gyqx.spdhdi.orderMgr.vo.DeptApplyGoodsUnderAvgVo;
import gyqx.spdhdi.orderMgr.vo.DeptApplyGoodsUpperAnalysisVo;
import gyqx.spdhdi.orderMgr.vo.HosMonthHighValueConsumeVo;
import gyqx.spdhdi.orderMgr.vo.HosMonthInStockBillQueryVo;
import gyqx.spdhdi.orderMgr.vo.HosMonthLowValueConsumeVo;
import gyqx.spdhdi.orderMgr.vo.HosOutStockGoodsQueryVo;
import gyqx.spdhdi.orderMgr.vo.PurchaseStatisticsVo;

@Controller
@RequestMapping(value = "order/purchaseStatistics")
public class PurchaseStatisticsController extends BaseController {
	
	@Resource
	private IPurchaseStatisticsService purchaseStatisticsService;
	
	/**
	 * 查询科室采购金额、请购金额等情况
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPurchaseAmountForMonth")
	@ResponseBody
	public AjaxResult<QueryResult<PurchaseStatisticsVo>> getPurchaseAmountForMonth(@RequestBody QueryInfo<PurchaseStatisticsVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<PurchaseStatisticsVo>> result = new AjaxResult<QueryResult<PurchaseStatisticsVo>>();
		QueryResult<PurchaseStatisticsVo> lstSysProject = purchaseStatisticsService.getPurchaseAmountForMonth(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	
	
	/**
	 * 科室请购数量分析，主要与科室产品月请购上限数据对比分析
	 */
	@RequestMapping(value = "deptApplyGoodsUpperAnalysis")
	@ResponseBody
	public AjaxResult<QueryResult<DeptApplyGoodsUpperAnalysisVo>> deptApplyGoodsUpperAnalysis(@RequestBody QueryInfo<DeptApplyGoodsUpperAnalysisVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<DeptApplyGoodsUpperAnalysisVo>> result = new AjaxResult<QueryResult<DeptApplyGoodsUpperAnalysisVo>>();
		QueryResult<DeptApplyGoodsUpperAnalysisVo> lstSysProject = purchaseStatisticsService.listByPage(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	
	/**
	 * 科室请购商品数量汇总分析
	 */
	@RequestMapping(value = "getDeptApplyGoodsAggregate")
	@ResponseBody
	public AjaxResult<QueryResult<DeptApplyGoodsAggregateVo>> getDeptApplyGoodsAggregate(@RequestBody QueryInfo<DeptApplyGoodsAggregateVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<DeptApplyGoodsAggregateVo>> result = new AjaxResult<QueryResult<DeptApplyGoodsAggregateVo>>();
		QueryResult<DeptApplyGoodsAggregateVo> lstSysProject = purchaseStatisticsService.getDeptApplyGoodsAggregate(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	
	/**
	 * 低于周请购值请购产品查询分析
	 */
	@RequestMapping(value = "getDeptApplyGoodsUnderAvg")
	@ResponseBody
	public AjaxResult<QueryResult<DeptApplyGoodsUnderAvgVo>> getDeptApplyGoodsUnderAvg(@RequestBody QueryInfo<DeptApplyGoodsUnderAvgVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<DeptApplyGoodsUnderAvgVo>> result = new AjaxResult<QueryResult<DeptApplyGoodsUnderAvgVo>>();
		QueryResult<DeptApplyGoodsUnderAvgVo> lstSysProject = purchaseStatisticsService.getDeptApplyGoodsUnderAvg(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	
	
	/**
	 * 医院高值耗材月消耗汇总
	 */
	@RequestMapping(value = "getHosMonthHighValueConsume")
	@ResponseBody
	public AjaxResult<QueryResult<HosMonthHighValueConsumeVo>> getHosMonthHighValueConsume(@RequestBody QueryInfo<HosMonthHighValueConsumeVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<HosMonthHighValueConsumeVo>> result = new AjaxResult<QueryResult<HosMonthHighValueConsumeVo>>();
		QueryResult<HosMonthHighValueConsumeVo> lstSysProject = purchaseStatisticsService.getHosMonthHighValueConsume(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	
	/**
	 * 医院低值耗材月核算
	 */
	@RequestMapping(value = "getHosMonthLowValueConsume")
	@ResponseBody
	public AjaxResult<QueryResult<HosMonthLowValueConsumeVo>> getHosMonthLowValueConsume(@RequestBody QueryInfo<HosMonthLowValueConsumeVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<HosMonthLowValueConsumeVo>> result = new AjaxResult<QueryResult<HosMonthLowValueConsumeVo>>();
		QueryResult<HosMonthLowValueConsumeVo> lstSysProject = purchaseStatisticsService.getHosMonthLowValueConsume(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	
	/**
	 * 医院月高、低值耗材入库单据表查询
	 */
	@RequestMapping(value = "getHosMonthInStockBillQuery")
	@ResponseBody
	public AjaxResult<QueryResult<HosMonthInStockBillQueryVo>> getHosMonthInStockBillQuery(@RequestBody QueryInfo<HosMonthInStockBillQueryVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<HosMonthInStockBillQueryVo>> result = new AjaxResult<QueryResult<HosMonthInStockBillQueryVo>>();
		QueryResult<HosMonthInStockBillQueryVo> lstSysProject = purchaseStatisticsService.getHosMonthInStockBillQuery(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	
	/**
	 * 医院产品出库信息查询
	 */
	@RequestMapping(value = "getHosOutStockGoodsQuery")
	@ResponseBody
	public AjaxResult<QueryResult<HosOutStockGoodsQueryVo>> getHosOutStockGoodsQuery(@RequestBody QueryInfo<HosOutStockGoodsQueryVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<HosOutStockGoodsQueryVo>> result = new AjaxResult<QueryResult<HosOutStockGoodsQueryVo>>();
		QueryResult<HosOutStockGoodsQueryVo> lstSysProject = purchaseStatisticsService.getHosOutStockGoodsQuery(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
}
