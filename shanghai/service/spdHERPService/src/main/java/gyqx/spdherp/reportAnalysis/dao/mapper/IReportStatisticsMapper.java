package gyqx.spdherp.reportAnalysis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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


public interface IReportStatisticsMapper {

	List<PurchaseStatisticsVo> getPurchaseAmountForMonth(PurchaseStatisticsVo purchaseStatisticsVo);

	List<DeptApplyGoodsUpperAnalysisVo> list(DeptApplyGoodsUpperAnalysisVo queryInfo) ;

	List<HosOutStockGoodsQueryVo> getHosOutStockGoodsQuery(HosOutStockGoodsQueryVo queryObject);

	List<HosMonthLowValueConsumeVo> getHosMonthLowValueConsume(HosMonthLowValueConsumeVo queryObject);


	/**
	 * 医院高值耗材月消耗汇总
	 * 
	 * @param hosId
	 *            医院id
	 * @param month
	 *            统计月份
	 * @param prevMonth
	 *            上一月份
	 * @param goodsType
	 *            产品类型 低值或高值，1:高值，0:低值
	 * @return
	 */
	List<HosMonthHighValueConsumeVo> getHosMonthHighValueConsume(@Param("hosId") String hosId,
			@Param("month") String month, @Param("prevMonth") String prevMonth, @Param("goodsType") String goodsType);

	/**
	 * 医院月高、低值耗材入库单据表统计查询
	 * 
	 * @param hosId
	 *            医院id
	 * @param instockBillId
	 *            入库单号
	 * @param provider
	 *            供应商名称
	 * @param accountingPeriod
	 *            会计期
	 * @param instockType
	 *            入库类型
	 * @return
	 */
	List<HosMonthInStockBillQueryVo> getHosMonthInStockBillQuery(@Param("hosId") String hosId,
			@Param("instockBillId") String instockBillId, @Param("provider") String provider,
			@Param("accountingPeriod") String accountingPeriod, @Param("instockType") String instockType);
	
	/**
	 * 查询需要统计的医院配置信息
	 * @return
	 */
	public List<SysConfigVo> getHosStatisticsConfig();
	
	/**
	 * 通过分类id查询产品分类信息
	 * @param id 分类id
	 * @return
	 */
	public HosKindcodeVo getHosKindcodeVoById(@Param("id") String id);
	
	/**
	 * 根据产品分类id查询该分类下的子节点信息
	 * @param pid 产品分类id
	 * @return
	 */
	public List<HosKindcodeVo> getHosKindcodeVoByPid(@Param("pid") String pid);
	
	/**
	 * 通过医院id查询所有医院产品分类信息
	 * @param hosId 医院id
	 * @return
	 */
	public List<HosKindcodeVo> getHosKindcodeVoByHosId(@Param("hosId") String hosId);
	
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
	public List<HosMonthHighValueConsumeVo> getHosMonthHighValueStatistics(@Param("hosId") String hosId,
			@Param("prevMonth") String prevMonth, @Param("startDate") String startDate,
			@Param("endDate") String endDate,@Param("goodsType") String goodsType,@Param("stockType")String stockType);
	
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
	public List<HosMonthHighValueConsumeVo> getHosMonthHighValueStatisticsByKindId(@Param("hosId") String hosId,
			@Param("prevMonth") String prevMonth, @Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("goodsType") String goodsType,
			@Param("stockType") String stockType, @Param("kindId") String kindId,
			@Param("kindIds") List<String> kindIds);

	/**
	 * 查询周均请购数
	 * @param queryObject
	 * @return
	 */
	List<DeptApplyGoodsAggregateVo> getDeptApplyGoodsAggregate(DeptApplyGoodsAggregateVo queryObject);

	/**
	 * 低于周请购查询
	 * @param queryObject
	 * @return
	 */
	List<DeptApplyGoodsUnderAvgVo> getDeptApplyGoodsUnderAvg(DeptApplyGoodsUnderAvgVo queryObject);

	List<HosMonthGoodsReceiptQueryVo> getHosMonthGoodsReceiptQuery(HosMonthGoodsReceiptQueryVo queryObject);

	List<HosMonthGoodsReceiptDetailVo> getHosMonthGoodsReceiptDetail(HosMonthGoodsReceiptDetailVo queryObject);

	/**
	 * 直销结算查询
	 * @param queryObject
	 * @return
	 */
	List<HosSettleZXQueryVo> getHosSettleForZX(HosSettleZXQueryVo queryObject);

	/**
	 * 直销结算详情
	 * @param pid
	 * @return
	 */
	List<HosSettleDetailZXQueryVo> getHosSettleDetailForZX(@Param("pid")String pid);

	/**
	 * 统计信息
	 * @param queryObject
	 * @return
	 */
	List<HosSettleMsgQueryVo>  getHosSettleForZXMsg(HosSettleZXQueryVo queryObject);

	/**
	 * 定向结算发票查询
	 * @param queryObject
	 * @return
	 */
	List<HosSettleDXQueryVo> getHosSettleForDX(HosSettleDXQueryVo queryObject);

	/**
	 * 定向结算详情查询
	 * @param queryObject
	 * @return
	 */
	List<HosSettleDetailDXQueryVo> getHosSettleDetailForDX(HosSettleDetailDXQueryVo queryObject);

	/**
	 * 定向发票统计信息
	 * @param queryObject
	 * @return
	 */
	List<HosSettleMsgQueryVo> getHosSettleForDXMsg(HosSettleDXQueryVo queryObject);
	
	
}



	
