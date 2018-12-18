package gyqx.spdherp.reportAnalysis.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.reportAnalysis.service.IReportStatisticsService;
import gyqx.spdherp.reportAnalysis.vo.DeptApplyGoodsAggregateVo;
import gyqx.spdherp.reportAnalysis.vo.DeptApplyGoodsUnderAvgVo;
import gyqx.spdherp.reportAnalysis.vo.DeptApplyGoodsUpperAnalysisVo;
import gyqx.spdherp.reportAnalysis.vo.HosMonthGoodsReceiptDetailVo;
import gyqx.spdherp.reportAnalysis.vo.HosMonthGoodsReceiptQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosMonthHighValueConsumeVo;
import gyqx.spdherp.reportAnalysis.vo.HosMonthInStockBillQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosMonthLowValueConsumeVo;
import gyqx.spdherp.reportAnalysis.vo.HosOutStockGoodsQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosSettleDXQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosSettleDetailDXQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosSettleDetailZXQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosSettleMsgQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosSettleZXQueryVo;
import gyqx.spdherp.reportAnalysis.vo.PurchaseStatisticsVo;
import gyqx.spdherp.reportAnalysis.vo.UserDeptVo;

@Controller
@RequestMapping(value = "reportAnalysis/reportStatistics")
public class ReportStatisticsController extends BaseController {
	
	@Resource
	private IReportStatisticsService reportStatisticsService;
	
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
		QueryResult<PurchaseStatisticsVo> lstSysProject = reportStatisticsService.getPurchaseAmountForMonth(queryInfo);
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
		QueryResult<DeptApplyGoodsUpperAnalysisVo> lstSysProject = reportStatisticsService.listByPage(queryInfo);
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
		QueryResult<DeptApplyGoodsAggregateVo> lstSysProject = reportStatisticsService.getDeptApplyGoodsAggregate(queryInfo);
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
		QueryResult<DeptApplyGoodsUnderAvgVo> lstSysProject = reportStatisticsService.getDeptApplyGoodsUnderAvg(queryInfo);
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
		QueryResult<HosMonthHighValueConsumeVo> lstSysProject = reportStatisticsService.getHosMonthHighValueConsume(queryInfo);
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
		QueryResult<HosMonthLowValueConsumeVo> lstSysProject = reportStatisticsService.getHosMonthLowValueConsume(queryInfo);
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
		QueryResult<HosMonthInStockBillQueryVo> lstSysProject = reportStatisticsService.getHosMonthInStockBillQuery(queryInfo);
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
		QueryResult<HosOutStockGoodsQueryVo> lstSysProject = reportStatisticsService.getHosOutStockGoodsQuery(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	
	/**
	 * 医院产品收货信息查询
	 */
	@RequestMapping(value = "getHosMonthGoodsReceiptQuery")
	@ResponseBody
	public AjaxResult<QueryResult<HosMonthGoodsReceiptQueryVo>> getHosMonthGoodsReceiptQuery(@RequestBody QueryInfo<HosMonthGoodsReceiptQueryVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<HosMonthGoodsReceiptQueryVo>> result = new AjaxResult<QueryResult<HosMonthGoodsReceiptQueryVo>>();
		QueryResult<HosMonthGoodsReceiptQueryVo> lstSysProject = reportStatisticsService.getHosMonthGoodsReceiptQuery(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	
	/**
	 * 医院产品收货明细，各部门收货情况
	 */
	@RequestMapping(value = "getHosMonthGoodsReceiptDetail")
	@ResponseBody
	public AjaxResult<QueryResult<HosMonthGoodsReceiptDetailVo>> getHosMonthGoodsReceiptDetail(@RequestBody QueryInfo<HosMonthGoodsReceiptDetailVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<HosMonthGoodsReceiptDetailVo>> result = new AjaxResult<QueryResult<HosMonthGoodsReceiptDetailVo>>();
		QueryResult<HosMonthGoodsReceiptDetailVo> lstSysProject = reportStatisticsService.getHosMonthGoodsReceiptDetail(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	
	
	/**
	 * 查询当前登陆用户所管理的库房对应的科室（报表查询中当前用户可见的科室）
	 */
	@RequestMapping(value = "getUserDepts")
	@ResponseBody
	public AjaxResult<QueryResult<UserDeptVo>> getUserDepts(@RequestBody QueryInfo<UserDeptVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<UserDeptVo>> result = new AjaxResult<QueryResult<UserDeptVo>>();
		QueryResult<UserDeptVo> lstSysProject = reportStatisticsService.getUserDepts(queryInfo);
		result.setData(lstSysProject);
		return result;
	}
	
	/**
	 * 直销结算查询
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getHosSettleForZX")
	@ResponseBody
	public AjaxResult<QueryResult<HosSettleZXQueryVo>>  getHosSettleForZX(@RequestBody QueryInfo<HosSettleZXQueryVo> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult<QueryResult<HosSettleZXQueryVo>> result = new AjaxResult<QueryResult<HosSettleZXQueryVo>>();
		filterErrors(error);
		QueryResult<HosSettleZXQueryVo> ret = reportStatisticsService.getHosSettleForZX(queryInfo);
		result.setData(ret);
		return result;
	}
	/**
	 * 直销结算详情
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getHosSettleDetailForZX")
	@ResponseBody
	public AjaxResult<QueryResult<HosSettleDetailZXQueryVo>> getHosSettleDetailForZX(@RequestBody QueryInfo<HosSettleDetailZXQueryVo> queryInfo ,Errors error  )  throws  Exception
	{
		AjaxResult<QueryResult<HosSettleDetailZXQueryVo>> result = new AjaxResult<QueryResult<HosSettleDetailZXQueryVo>>();
		filterErrors(error);
		QueryResult<HosSettleDetailZXQueryVo> ret = reportStatisticsService.getHosSettleDetailForZX(queryInfo);
		result.setData(ret);
		return result;
	}
	
	/**
	 * 统计信息
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getHosSettleForZXMsg")
	@ResponseBody
	public AjaxResult<QueryResult<HosSettleMsgQueryVo>>  getHosSettleForZXMsg(@RequestBody QueryInfo<HosSettleZXQueryVo> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult<QueryResult<HosSettleMsgQueryVo>> result = new AjaxResult<QueryResult<HosSettleMsgQueryVo>>();
		filterErrors(error);
		QueryResult<HosSettleMsgQueryVo> ret = reportStatisticsService.getHosSettleForZXMsg(queryInfo);
		result.setData(ret);
		return result;
	}
	
	
	/**
	 * 定向结算查询
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getHosSettleForDX")
	@ResponseBody
	public AjaxResult<QueryResult<HosSettleDXQueryVo>>  getHosSettleForDX(@RequestBody QueryInfo<HosSettleDXQueryVo> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult<QueryResult<HosSettleDXQueryVo>> result = new AjaxResult<QueryResult<HosSettleDXQueryVo>>();
		QueryResult<HosSettleDXQueryVo> ret = reportStatisticsService.getHosSettleForDX(queryInfo);
		result.setData(ret);
		return result;
	}
	/**
	 * 定向结算详情
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getHosSettleDetailForDX")
	@ResponseBody
	public AjaxResult<QueryResult<HosSettleDetailDXQueryVo>> getHosSettleDetailForDX(@RequestBody QueryInfo<HosSettleDetailDXQueryVo> queryInfo ,Errors error  )  throws  Exception
	{
		AjaxResult<QueryResult<HosSettleDetailDXQueryVo>> result = new AjaxResult<QueryResult<HosSettleDetailDXQueryVo>>();
		filterErrors(error);
		QueryResult<HosSettleDetailDXQueryVo> ret = reportStatisticsService.getHosSettleDetailForDX(queryInfo);
		result.setData(ret);
		return result;
	}
	
	/**getHosSettleDetailForDX
	 * 定向结算统计信息
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getHosSettleForDXMsg")
	@ResponseBody
	public AjaxResult<QueryResult<HosSettleMsgQueryVo>>  getHosSettleForDXMsg(@RequestBody QueryInfo<HosSettleDXQueryVo> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult<QueryResult<HosSettleMsgQueryVo>> result = new AjaxResult<QueryResult<HosSettleMsgQueryVo>>();
		filterErrors(error);
		QueryResult<HosSettleMsgQueryVo> ret = reportStatisticsService.getHosSettleForDXMsg(queryInfo);
		result.setData(ret);
		return result;
	}
}
