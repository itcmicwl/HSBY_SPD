package gyqx.spdhdi.orderMgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import gyqx.spdhdi.orderMgr.dao.PurchaseListDao;
import gyqx.spdhdi.orderMgr.service.IPurchaseListService;
import gyqx.spdhdi.orderMgr.vo.PurchaseListVo;
@Service
public class PurchaseListService implements IPurchaseListService {
	@Resource
	PurchaseListDao purchaseListDao;
	@Override
	public List<PurchaseListVo> getPurchaseList(PurchaseListVo purchaseListVo) throws Exception{
		return purchaseListDao.getPurchaseList(purchaseListVo);
	}

	@Override
	public int insertPurchaseListByBatch(List<PurchaseListVo> lst) throws Exception{
		return purchaseListDao.insertPurchaseListByBatch(lst);
	}

	@Override
	public int UpdatePurchaseListByBatch(List<PurchaseListVo> lst) throws Exception{
		return purchaseListDao.UpdatePurchaseListByBatch(lst);
	}

	@Override
	public int deletePurchaseListByBillId(String billId) throws Exception{
		return purchaseListDao.deletePurchaseListByBillId(billId);
	}

	@Override
	public int deletePurchaseListById(String id) throws Exception{
		return purchaseListDao.deletePurchaseListById(id);
	}

}
