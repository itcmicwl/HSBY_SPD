package gyqx.spdhdi.orderMgr.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.orderMgr.vo.PurchaseListVo;
import gyqx.spdhdi.orderMgr.vo.PurchaseVo;
import gyqx.spdherp.stockPile.vo.DeptGoodsVo;

import java.util.List;
import java.util.Map;

public interface IPurchaseService {
	QueryResult<PurchaseVo> getPurchaseList(QueryInfo<PurchaseVo> queryInfo) throws Exception;
	PurchaseVo getPurchaseByBillId(String billId) throws Exception;
	PurchaseVo getPurByid(String id) throws Exception;
	PurchaseVo insertPurchase(PurchaseVo purchaseVo) throws Exception;
	PurchaseVo updatePurchase(PurchaseVo purchaseVo) throws Exception;
	int deletePurchase(PurchaseVo purchaseVo) throws Exception;
	PurchaseVo updatePurchaseStatus(PurchaseVo purchaseVo) throws Exception;
	int updatePurStatus(Map<String,List<PurchaseListVo>> map) throws Exception;
	List<PurchaseVo> creatPurBillByStoc(List<DeptGoodsVo> lstGoods) throws Exception;
    Integer audit(PurchaseVo purBill,Integer flag) throws Exception;
}
