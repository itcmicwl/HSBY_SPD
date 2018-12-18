package gyqx.spdhdi.orderMgr.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdhdi.orderMgr.dao.mapper.IPurchaseStatisticsMapper;
import gyqx.spdhdi.orderMgr.vo.DeptApplyGoodsUpperAnalysisVo;
import gyqx.spdhdi.orderMgr.vo.PurchaseStatisticsVo;

@Repository
public class PurchaseStatisticsDao {

	@Resource
	private IPurchaseStatisticsMapper mpper;
	public List<PurchaseStatisticsVo> getPurchaseAmountForMonth(PurchaseStatisticsVo purchaseStatisticsVo) {
		
		return mpper.getPurchaseAmountForMonth(purchaseStatisticsVo);
	}
	public List<DeptApplyGoodsUpperAnalysisVo> list(DeptApplyGoodsUpperAnalysisVo qbean) throws Exception{		

		return mpper.list(qbean);
	}

}
