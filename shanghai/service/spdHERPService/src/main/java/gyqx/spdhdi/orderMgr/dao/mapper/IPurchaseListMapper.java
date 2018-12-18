package gyqx.spdhdi.orderMgr.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdhdi.orderMgr.vo.PurchaseListVo;

public interface IPurchaseListMapper {
	List<PurchaseListVo> getPurchaseList(PurchaseListVo purchaseListVo);
	int insertPurchaseListByBatch(List<PurchaseListVo> lst);
	int updatePurchaseListByBatch(List<PurchaseListVo> lst);
	int deletePurchaseListByBillId(@Param("billId") String billId);
	int deletePurchaseListById(@Param("id") String id);
}
