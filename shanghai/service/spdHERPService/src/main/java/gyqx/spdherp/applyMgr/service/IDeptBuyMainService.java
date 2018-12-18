package gyqx.spdherp.applyMgr.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.platform.sys.vo.SysOrgVo;
import gyqx.spdherp.applyMgr.vo.ApplySurPkgVo;
import gyqx.spdherp.applyMgr.vo.DeptBuyBillVo;
import gyqx.spdherp.applyMgr.vo.UnPackApplyVo;
import gyqx.spdherp.applyMgr.vo.SurDef4applyBillVo;
import gyqx.spdherp.po.DeptBuyMain;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;

public interface IDeptBuyMainService {
	DeptBuyMain get(String id) throws Exception;

	DeptBuyMain add(DeptBuyMain deptBuyMain) throws Exception;

	DeptBuyMain update(DeptBuyMain deptBuyMain) throws Exception;
	int close(String  billId) throws Exception;


	List<DeptBuyMain> query(String predicate, String orderBy, Object... fieldValue) throws Exception;

	List<DeptBuyMain> list(DeptBuyMain bean) throws Exception;

	QueryResult<DeptBuyMain> listByPage(QueryInfo<DeptBuyMain> queryInfo) throws Exception;

	void addDeptBuyBillVoByMix(DeptBuyBillVo vo) throws Exception;

	void mergeDeptApply(DeptBuyBillVo vo) throws Exception;
	
	void addDeptBuyBillVo(DeptBuyBillVo vo) throws Exception;

	QueryResult<DeptBuyBillVo> getVoList(QueryInfo<DeptBuyBillVo> queryInfo) throws Exception;

	DeptBuyBillVo getBillDetails(DeptBuyBillVo bv) throws Exception;

	DeptBuyBillVo updateDeptBuyBillVo(DeptBuyBillVo vo) throws Exception;

	List<DeptBuyBillVo> getMainBillVo4pur(DeptBuyBillVo queryInfo) throws Exception;
	List<DeptBuyBillVo> getMainBillVo4merger(DeptBuyBillVo queryInfo) throws Exception;


	/**
	 * 再次请购
	 * @param vo
	* @throws Exception 
	 */
	String createBillAgain(DeptBuyBillVo vo) throws Exception;
	
	/**
	 * 批量提交请购单
	 * @param billVoList
	 * @param state状态
	 * @throws Exception
	 */
	void processBatchSubmitBill(List<DeptBuyBillVo> billVoList,Integer state) throws Exception;
	
	/**
	 * 单个提交请购单
	 * @param vo
	 * @param state
	 * @return
	 * @throws Exception
	 */
	DeptBuyBillVo processSubmitBill(DeptBuyBillVo vo, Integer state) throws Exception;
	List<SysOrgVo> getBuyDeptList(DeptBuyBillVo queryInfo) throws Exception;
	QueryResult<DeptBuyBillVo> getUnPackApplyBIllList(QueryInfo<DeptBuyBillVo> queryInfo)throws Exception;
	UnPackApplyVo getUnPackApplyBIll(String billId,String stockId) throws Exception;
	DeptBuyBillVo getBillDetails4sur(DeptBuyBillVo bv) throws Exception;


}
