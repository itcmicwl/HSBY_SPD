package gyqx.spdhdi.orderMgr.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdhdi.orderMgr.dao.mapper.IPurchaseListMapper;
import gyqx.spdhdi.orderMgr.vo.PurchaseListVo;
@Repository
public class PurchaseListDao {
	@Resource
	IPurchaseListMapper purchaseListMapper;
	public List<PurchaseListVo> getPurchaseList(PurchaseListVo purchaseListVo){
		return purchaseListMapper.getPurchaseList(purchaseListVo);
	}
	public int insertPurchaseListByBatch(List<PurchaseListVo> lst){
		return purchaseListMapper.insertPurchaseListByBatch(lst);
	}
	public int UpdatePurchaseListByBatch(List<PurchaseListVo> lst){
		return purchaseListMapper.updatePurchaseListByBatch(lst);
	}
	public int deletePurchaseListByBillId(String billId){
		return purchaseListMapper.deletePurchaseListByBillId(billId);
	}
	public int deletePurchaseListById(String id){
		return purchaseListMapper.deletePurchaseListById(id);
	}
}
