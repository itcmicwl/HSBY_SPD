package gyqx.spdhdi.orderMgr.service;


import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.orderMgr.vo.DistrBillHumitureListVo;
import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
import gyqx.spdhdi.orderMgr.vo.DistrBillUniqueListVo;
import gyqx.spdhdi.orderMgr.vo.DistrBillVo;

public interface IDistrBillService 
{
	DistrBillVo unRecDistrBillByBillId(String billId) throws Exception ;
	DistrBillVo getByBillId(String billId) throws Exception ;
	DistrBillVo getById(String id) throws Exception ;
	DistrBillVo add(DistrBillVo distrBill) throws Exception ;
	DistrBillVo update(DistrBillVo distrBill) throws Exception;	
	DistrBillVo updateStatus(DistrBillVo distrBill) throws Exception;	
	
	List<DistrBillVo> list(DistrBillVo bean) throws Exception;	
	QueryResult<DistrBillVo> listByPage(QueryInfo<DistrBillVo> queryInfo) throws Exception;	
	//--------------------------明细表-----------------------------
	DistrBillListVo getDetail(String id) throws Exception ;
	int insertDetailByBatch(List<DistrBillListVo> distrBillList) throws Exception ;
	int updateDetail(DistrBillListVo distrBillList) throws Exception;
	int updateDetailByBatch(List<DistrBillListVo> distrBillList) throws Exception;
	int delDistrBillDetailByPid(String pid) throws Exception;	
	int delDistrBillDetailByBillId(String billId) throws Exception;	
	List<DistrBillListVo> detailList(DistrBillListVo bean) throws Exception;
	//--------------------------温湿度--------------------------
	DistrBillHumitureListVo getDistrHumiture(String id) throws Exception ;
	int insertDistrHumiture(List<DistrBillHumitureListVo> distrBillHumitureList) throws Exception ;
	int updateDistrHumiture(DistrBillHumitureListVo distrBillHumitureList) throws Exception;
	List<DistrBillHumitureListVo> listDistrHumiture(DistrBillHumitureListVo bean) throws Exception;
	//--------------------------条码--------------------------
	DistrBillUniqueListVo getDistrUnique(String id) throws Exception ;
	int insertDistrUnique(List<DistrBillUniqueListVo> distrBillUniqueList) throws Exception ;
	int updateDistrUnique(DistrBillUniqueListVo distrBillUniqueList) throws Exception;
	List<DistrBillUniqueListVo> listDistrUnique(DistrBillUniqueListVo bean) throws Exception;
	boolean existUniqueCode(String UniqueCode) throws Exception;

}


