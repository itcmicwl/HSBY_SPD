package gyqx.spdherp.applyMgr.service;


import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.applyMgr.vo.DeptBuyBillVo;
import gyqx.spdherp.applyMgr.vo.DeptBuySubVo;
import gyqx.spdherp.applyMgr.vo.ProvGoodsKindsVo;
import gyqx.spdherp.po.DeptBuySub;
import gyqx.spdherp.provManager.vo.HosCollectorProvVo;

public interface IDeptBuySubService 
{
	DeptBuySub get(String id) throws Exception ;
	DeptBuySub add(DeptBuySub deptBuySub) throws Exception ;
	DeptBuySub update(DeptBuySub deptBuySub) throws Exception;
	
	List<DeptBuySub> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<DeptBuySub> list(DeptBuySub bean) throws Exception;
	List<DeptBuySubVo> listVo(DeptBuySubVo bean) throws Exception;
	
	QueryResult<DeptBuySub> listByPage(QueryInfo<DeptBuySub> queryInfo) throws Exception;	
	
	QueryResult<DeptBuySubVo> listVoByPage(QueryInfo<DeptBuySubVo> queryInfo) throws Exception;	

	
	List<DeptBuySubVo> getDetailsVo4Pur(DeptBuySubVo queryInfo)  throws Exception;
	List<DeptBuySubVo> getDetailsVo4Merger(DeptBuySubVo queryInfo)  throws Exception;
    void updateBill4Pur( List<DeptBuySubVo> voList) throws Exception;

    DeptBuySub updateBill4OutStock(DeptBuySub sub) throws Exception;
	List<ProvGoodsKindsVo> getProvQGList(DeptBuySubVo deptBuyBillVo) throws Exception;
	List<DeptBuySubVo> getVoListByOut(String id);
	 int updateMainStateByBillId(String  billId) throws Exception;
	
}


