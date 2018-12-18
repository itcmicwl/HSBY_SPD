package gyqx.spdherp.deptMgr.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyBatchAddVo;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplySubmitVo;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyVo;
import gyqx.spdherp.po.DeptGoodsApply;

public interface IDeptGoodsApplyService {

	/**
	 * 科室商品申请，批量查询医院商品信息,且商品未加入到科室商品中
	 * 
	 * @param queryObject
	 *            <li>goodsName 商品名称模糊查询</li>
	 *            <li>hosId 医院id</li>
	 *            <li>deptId 医院科室id</li>
	 * @return
	 * @throws Exception
	 */
	QueryResult<HosGoodsInfo> deptGoodsApplyBatchAddQuery(QueryInfo<DeptGoodsApplyVo> queryInfo) throws Exception;

	/**
	 * 批量新增科室产品申请
	 * 
	 * @param deptGoodsApplyBatchAddVo
	 *            产品信息
	 * @return
	 * @throws Exception
	 */
	List<DeptGoodsApply> deptGoodsApplyBatchAdd(DeptGoodsApplyBatchAddVo deptGoodsApplyBatchAddVo) throws Exception;

	/**
	 * 查询科室产品申请集合信息，过滤数据状态为40（已阅）
	 * 
	 * @param queryObject
	 *            <li>goodsName 商品名称模糊查询</li>
	 *            <li>status 商品审核状态</li>
	 *            <li>hosId 医院id</li>
	 *            <li>deptId 医院科室id</li>
	 * @return
	 */
	QueryResult<DeptGoodsApplyVo> findDeptGoodsApplyList(QueryInfo<DeptGoodsApplyVo> queryInfo) throws Exception;

	/**
	 * 执行处理商品批量提交
	 * 
	 * @param deptGoodsApplySubmitVo
	 * @throws Exception
	 */
	void processDeptGoodsApplySubmit(DeptGoodsApplySubmitVo deptGoodsApplySubmitVo) throws Exception;

	/**
	 * 根据申请id删除科室商品申请数据
	 * 
	 * @param hosId
	 *            医院id
	 * @param deptId
	 *            科室id
	 * @param id
	 *            申请数据id
	 */
	void deleteDeptGoodsApply(String hosId, String deptId, String id) throws Exception;

	/**
	 * 根据id更新科室商品申请申请状态为已阅
	 * 
	 * @param hosId
	 *            医院id
	 * @param deptId
	 *            科室id
	 * @param id
	 *            申请id
	 * @param version
	 *            数据版本
	 */
	void updateDeptGoodsApplyRead(String hosId, String deptId, String id, Integer version) throws Exception;

	/**
	 * 根据id更新科室商品申请申请状态为已阅
	 * 
	 * @param hosId
	 *            医院id
	 * @param deptId
	 *            科室id
	 * @param id
	 *            申请id
	 * @param version
	 *            数据版本
	 * @param applyReason
	 *            申请原由
	 */
	void updateDeptGoodsApplyReason(String hosId, String deptId, String id, Integer version,String applyReason) throws Exception;
}
