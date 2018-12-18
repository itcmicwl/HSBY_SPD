package gyqx.spdhdi.orderMgr.dao.mapper;

import java.util.List;

import gyqx.spdhdi.orderMgr.vo.DeptApplyGoodsUpperAnalysisVo;
import gyqx.spdhdi.orderMgr.vo.PurchaseStatisticsVo;

public interface IPurchaseStatisticsMapper {

	List<PurchaseStatisticsVo> getPurchaseAmountForMonth(PurchaseStatisticsVo purchaseStatisticsVo);

	List<DeptApplyGoodsUpperAnalysisVo> list(DeptApplyGoodsUpperAnalysisVo queryInfo) ;

}
