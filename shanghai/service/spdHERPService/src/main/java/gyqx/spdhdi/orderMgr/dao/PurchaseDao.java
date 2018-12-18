package gyqx.spdhdi.orderMgr.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdhdi.orderMgr.dao.mapper.IPurchaseMapper;
import gyqx.spdhdi.orderMgr.vo.PurchaseVo;
@Repository
public class PurchaseDao {
	@Resource
	IPurchaseMapper purchaseMapper;
	public List<PurchaseVo> getPurchaseList(PurchaseVo purchaseVo){
		return purchaseMapper.getPurchaseList(purchaseVo);
	}
	public PurchaseVo getPurchaseByBillId(String billId){
		return purchaseMapper.getPurchaseByBillId(billId);
	}
	public PurchaseVo getPurByid(String id){
		return purchaseMapper.getPurByid(id);
	}
	public int insertPurchase(PurchaseVo purchaseVo){
		return purchaseMapper.insertPurchase(purchaseVo);
	}
	public int insertPurchaseByBatch(List<PurchaseVo> lst){
		return purchaseMapper.insertPurchaseByBatch(lst);
	}
	public int updatePurchase(PurchaseVo purchaseVo){
		return purchaseMapper.updatePurchase(purchaseVo);
	}
	public int deletePurchase(PurchaseVo purchaseVo){
		return purchaseMapper.deletePurchase(purchaseVo);
	}
	public Integer audit(PurchaseVo purBill){
		return purchaseMapper.audit(purBill);
	}
}
