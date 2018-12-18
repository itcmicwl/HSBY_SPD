package gyqx.spdherp.reportAnalysis.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
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


public interface IReportStatisticsService {

	/**
	 *  查询科室采购金额、请购金额等情况
	 * @param queryInfo
	 * @return
	 */
	QueryResult<PurchaseStatisticsVo> getPurchaseAmountForMonth(QueryInfo<PurchaseStatisticsVo> queryInfo)  throws Exception;;

	QueryResult<DeptApplyGoodsUpperAnalysisVo> listByPage(QueryInfo<DeptApplyGoodsUpperAnalysisVo> queryInfo) throws Exception;

	/**
	 * 科室请购产品数量汇总分析
	 * @param queryInfo
	 * @return
	 */
	QueryResult<DeptApplyGoodsAggregateVo> getDeptApplyGoodsAggregate(QueryInfo<DeptApplyGoodsAggregateVo> queryInfo);

	/**
	 * 低于周请购值查询分析
	 * @param queryInfo
	 * @return
	 */
	QueryResult<DeptApplyGoodsUnderAvgVo> getDeptApplyGoodsUnderAvg(QueryInfo<DeptApplyGoodsUnderAvgVo> queryInfo);

	/**
	 * 医院高值耗材月消耗汇总
	 * @param queryInfo
	 * @return
	 */
	QueryResult<HosMonthHighValueConsumeVo> getHosMonthHighValueConsume(
			QueryInfo<HosMonthHighValueConsumeVo> queryInfo);

	/**
	 * 医院低值耗材月核算
	 */
	QueryResult<HosMonthLowValueConsumeVo> getHosMonthLowValueConsume(QueryInfo<HosMonthLowValueConsumeVo> queryInfo);

	/**
	 * 医院月高、低值耗材入库单据表查询
	 * @param queryInfo
	 * @return
	 */
	QueryResult<HosMonthInStockBillQueryVo> getHosMonthInStockBillQuery(
			QueryInfo<HosMonthInStockBillQueryVo> queryInfo);

	/**
	 * 医院产品出库信息查询
	 */
	QueryResult<HosOutStockGoodsQueryVo> getHosOutStockGoodsQuery(QueryInfo<HosOutStockGoodsQueryVo> queryInfo);	
	
	/**
	 * 处理医院高值耗材月消耗统计
	 * @throws Exception 
	 */
	public void processStatisticsHosMonthHighValueConsume() throws Exception;

	QueryResult<HosMonthGoodsReceiptQueryVo> getHosMonthGoodsReceiptQuery(
			QueryInfo<HosMonthGoodsReceiptQueryVo> queryInfo);

	QueryResult<HosMonthGoodsReceiptDetailVo> getHosMonthGoodsReceiptDetail(
			QueryInfo<HosMonthGoodsReceiptDetailVo> queryInfo);

	/**
	 * 查询当前登陆用户所管理的库房对应的科室（报表查询中当前用户可见的科室）
	 */
	QueryResult<UserDeptVo> getUserDepts(QueryInfo<UserDeptVo> queryInfo);
	

	/**
	 * 直销结算查询
	 * @param queryInfo
	 * @return
	 */
	QueryResult<HosSettleZXQueryVo> getHosSettleForZX(QueryInfo<HosSettleZXQueryVo> queryInfo);

	/**
	 * 直销结算详情
	 * @param queryInfo
	 * @return
	 */
	QueryResult<HosSettleDetailZXQueryVo> getHosSettleDetailForZX(QueryInfo<HosSettleDetailZXQueryVo> queryInfo);

	/**
	 * 统计信息
	 * @param queryInfo
	 * @return
	 */
	QueryResult<HosSettleMsgQueryVo> getHosSettleForZXMsg(QueryInfo<HosSettleZXQueryVo> queryInfo);

	/**
	 * 定向结算查询
	 * @param queryInfo
	 * @return
	 */
	QueryResult<HosSettleDXQueryVo> getHosSettleForDX(QueryInfo<HosSettleDXQueryVo> queryInfo);

	/**
	 * 定向结算详情
	 * @param queryInfo
	 * @return
	 */
	QueryResult<HosSettleDetailDXQueryVo> getHosSettleDetailForDX(QueryInfo<HosSettleDetailDXQueryVo> queryInfo);

	/**
	 * 定向发票统计信息
	 * @param queryInfo
	 * @return
	 */
	QueryResult<HosSettleMsgQueryVo> getHosSettleForDXMsg(QueryInfo<HosSettleDXQueryVo> queryInfo);
}
