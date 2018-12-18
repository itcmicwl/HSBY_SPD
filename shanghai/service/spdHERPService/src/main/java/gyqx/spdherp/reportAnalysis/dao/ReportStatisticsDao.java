package gyqx.spdherp.reportAnalysis.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdherp.reportAnalysis.dao.mapper.IReportStatisticsMapper;
import gyqx.spdherp.reportAnalysis.vo.DeptApplyGoodsAggregateVo;
import gyqx.spdherp.reportAnalysis.vo.DeptApplyGoodsUnderAvgVo;
import gyqx.spdherp.reportAnalysis.vo.DeptApplyGoodsUpperAnalysisVo;
import gyqx.spdherp.reportAnalysis.vo.HosKindcodeVo;
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
import gyqx.spdherp.reportAnalysis.vo.SysConfigVo;


@Repository
public class ReportStatisticsDao {

	@Resource
	private IReportStatisticsMapper mpper;
	public List<PurchaseStatisticsVo> getPurchaseAmountForMonth(PurchaseStatisticsVo purchaseStatisticsVo) {
		
		return mpper.getPurchaseAmountForMonth(purchaseStatisticsVo);
	}
	public List<DeptApplyGoodsUpperAnalysisVo> list(DeptApplyGoodsUpperAnalysisVo qbean) throws Exception{		

		return mpper.list(qbean);
	}
	
	/**
	 * 医院产品出库信息查询
	 */
	public List<HosOutStockGoodsQueryVo> getHosOutStockGoodsQuery(HosOutStockGoodsQueryVo queryObject) {
		return mpper.getHosOutStockGoodsQuery(queryObject);
	}
	public List<HosMonthLowValueConsumeVo> getHosMonthLowValueConsume(HosMonthLowValueConsumeVo queryObject) {
		return mpper.getHosMonthLowValueConsume(queryObject);
	}
	
	public List<HosMonthHighValueConsumeVo> getHosMonthHighValueConsume(
			HosMonthHighValueConsumeVo hosMonthHighValueConsumeVo) {

		return mpper.getHosMonthHighValueConsume(hosMonthHighValueConsumeVo.getHosId(),
				hosMonthHighValueConsumeVo.getMonth(), hosMonthHighValueConsumeVo.getPrevMonth(),
				hosMonthHighValueConsumeVo.getGoodsType());
	}
	
	public List<HosMonthInStockBillQueryVo> getHosMonthInStockBillQuery(
			HosMonthInStockBillQueryVo hosMonthInStockBillQueryVo) {

		return mpper.getHosMonthInStockBillQuery(hosMonthInStockBillQueryVo.getHosId(),
				hosMonthInStockBillQueryVo.getInstockBillId(), hosMonthInStockBillQueryVo.getProvider(),
				hosMonthInStockBillQueryVo.getAccountingPeriod(), hosMonthInStockBillQueryVo.getInstockType());
	}
	
	/**
	 * 查询需要统计的医院配置信息
	 * @return
	 */
	public List<SysConfigVo> getHosStatisticsConfig() {
		
		return mpper.getHosStatisticsConfig();
	}
	
	/**
	 * 通过分类id查询产品分类信息
	 * @param id 分类id
	 * @return
	 */
	public HosKindcodeVo getHosKindcodeVoById(String id){
		
		return mpper.getHosKindcodeVoById(id);
	}
	
	/**
	 * 根据产品分类id查询该分类下的子节点信息
	 * @param pid 产品分类id
	 * @return
	 */
	public List<HosKindcodeVo> getHosKindcodeVoByPid(String pid) {

		return mpper.getHosKindcodeVoByPid(pid);
	}
	
	/**
	 * 通过医院id查询所有医院产品分类id信息
	 * @param hosId 医院id
	 * @return
	 */
	public List<HosKindcodeVo> getHosKindcodeVoByHosId(String hosId){
		
		return mpper.getHosKindcodeVoByHosId(hosId);
	}
	
	/**
	 * 计算统计医院在指定时间段的统计数据
	 * @param hosId 医院id
	 * @param prevMonth 上个月份信息
	 * @param startDate 统计开始日期
	 * @param endDate 统计结束日期
	 * @param goodsType 产品类型 0：低值 1：高值
	 * @param stockType 产品类型 10：低值 20：高值
	 * @return
	 */
	public List<HosMonthHighValueConsumeVo> getHosMonthHighValueStatistics(String hosId, String prevMonth,
			String startDate, String endDate, String goodsType, String stockType) {

		return mpper.getHosMonthHighValueStatistics(hosId, prevMonth, startDate, endDate, goodsType, stockType);
	}
	
	/**
	 *	根据分类id统计医院在指定时间段的高低值耗材消耗统计数据
	 * @param hosId 医院id
	 * @param prevMonth 上个月份信息
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param goodsType 产品类型 0：低值 1：高值
	 * @param stockType 产品类型 10：低值 20：高值
	 * @param kindId 分类id
	 * @param kindIds 分类id及所有子节点id
	 * @return
	 */
	public List<HosMonthHighValueConsumeVo> getHosMonthHighValueStatisticsByKindId(String hosId, String prevMonth,
			String startDate, String endDate, String goodsType, String stockType, String kindId, List<String> kindIds) {

		return mpper.getHosMonthHighValueStatisticsByKindId(hosId, prevMonth, startDate, endDate, goodsType, stockType,
				kindId, kindIds);
	}
	
	/**
	 * 查询月请购汇总分析
	 * @param queryObject
	 * @return
	 */
	public List<DeptApplyGoodsAggregateVo> getDeptApplyGoodsAggregate(DeptApplyGoodsAggregateVo queryObject) {
		return mpper.getDeptApplyGoodsAggregate(queryObject);
	}
	
	/**
	 * 低于周请购查询
	 * @param queryObject
	 * @return
	 */
	public List<DeptApplyGoodsUnderAvgVo> getDeptApplyGoodsUnderAvg(DeptApplyGoodsUnderAvgVo queryObject) {
		return mpper.getDeptApplyGoodsUnderAvg(queryObject);
	}
	public List<HosMonthGoodsReceiptQueryVo> getHosMonthGoodsReceiptQuery(HosMonthGoodsReceiptQueryVo queryObject) {
		return mpper.getHosMonthGoodsReceiptQuery(queryObject);
	}
	public List<HosMonthGoodsReceiptDetailVo> getHosMonthGoodsReceiptDetail(HosMonthGoodsReceiptDetailVo queryObject) {
		return mpper.getHosMonthGoodsReceiptDetail(queryObject);
	}
	
	/**
	 * 直销业务结算查询
	 * @param queryObject
	 * @return
	 */
	public List<HosSettleZXQueryVo> getHosSettleForZX(HosSettleZXQueryVo queryObject) {
		return mpper.getHosSettleForZX(queryObject);
	}
	/**
	 * 直销结算详情
	 * @param queryObject
	 * @return
	 */
	public List<HosSettleDetailZXQueryVo> getHosSettleDetailForZX(HosSettleDetailZXQueryVo queryObject) {
		return mpper.getHosSettleDetailForZX(queryObject.getPid());
	}
	/**
	 * 统计信息
	 * @param queryObject
	 * @return
	 */
	public List<HosSettleMsgQueryVo> getHosSettleForZXMsg(HosSettleZXQueryVo queryObject) {
		return mpper.getHosSettleForZXMsg(queryObject);
	}
	
	/**
	 * 定向结算发票查询
	 * @param queryObject
	 * @return
	 */
	public List<HosSettleDXQueryVo> getHosSettleForDX(HosSettleDXQueryVo queryObject) {
		return mpper.getHosSettleForDX(queryObject);
	}
	/**
	 * 定向结算详情查询
	 * @param queryObject
	 * @return
	 */
	public List<HosSettleDetailDXQueryVo> getHosSettleDetailForDX(HosSettleDetailDXQueryVo queryObject) {
		return mpper.getHosSettleDetailForDX(queryObject);
	}
	/**
	 * 定向发票统计信息
	 * @param queryObject
	 * @return
	 */
	public List<HosSettleMsgQueryVo> getHosSettleForDXMsg(HosSettleDXQueryVo queryObject) {
		return mpper.getHosSettleForDXMsg(queryObject);
	}
}
