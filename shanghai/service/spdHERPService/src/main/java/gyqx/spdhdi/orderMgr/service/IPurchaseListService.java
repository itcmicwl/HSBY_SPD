package gyqx.spdhdi.orderMgr.service;

import java.util.List;

import gyqx.spdhdi.orderMgr.vo.PurchaseListVo;

public interface IPurchaseListService {
	List<PurchaseListVo> getPurchaseList(PurchaseListVo purchaseListVo) throws Exception;
	int insertPurchaseListByBatch(List<PurchaseListVo> lst)throws Exception;
	int UpdatePurchaseListByBatch(List<PurchaseListVo> lst)throws Exception;
	int deletePurchaseListByBillId(String billId)throws Exception;
	int deletePurchaseListById(String id)throws Exception;
}
