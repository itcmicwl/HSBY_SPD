package gyqx.spdhdi.orderMgr.dao.mapper;

import java.util.List;

import gyqx.spdhdi.po.Purchase;
import org.apache.ibatis.annotations.Param;

import gyqx.spdhdi.orderMgr.vo.PurchaseVo;

public interface IPurchaseMapper {
	List<PurchaseVo> getPurchaseList(PurchaseVo purchaseVo);
	PurchaseVo getPurchaseByBillId(@Param("billId") String billId);
	PurchaseVo getPurByid(@Param("id") String id);
	int insertPurchase(PurchaseVo purchaseVo);
	int updatePurchase(PurchaseVo purchaseVo);
	int deletePurchase(PurchaseVo purchaseVo);
	int insertPurchaseByBatch(List<PurchaseVo> lst);
	Integer audit(PurchaseVo purchaseVo);
}
