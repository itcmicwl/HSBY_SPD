package gyqx.spdhdi.orderMgr.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.orderMgr.vo.DeptApplyGoodsAggregateVo;
import gyqx.spdhdi.orderMgr.vo.DeptApplyGoodsUnderAvgVo;
import gyqx.spdhdi.orderMgr.vo.DeptApplyGoodsUpperAnalysisVo;
import gyqx.spdhdi.orderMgr.vo.HosMonthHighValueConsumeVo;
import gyqx.spdhdi.orderMgr.vo.HosMonthInStockBillQueryVo;
import gyqx.spdhdi.orderMgr.vo.HosMonthLowValueConsumeVo;
import gyqx.spdhdi.orderMgr.vo.HosOutStockGoodsQueryVo;
import gyqx.spdhdi.orderMgr.vo.PurchaseStatisticsVo;

public interface IPurchaseStatisticsService {

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
}
