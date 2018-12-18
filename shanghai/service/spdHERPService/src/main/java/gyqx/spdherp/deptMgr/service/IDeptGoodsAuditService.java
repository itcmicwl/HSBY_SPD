package gyqx.spdherp.deptMgr.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyVo;

public interface IDeptGoodsAuditService {

	/**
	 * 查询已提交的科室产品
	 * 
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	QueryResult<DeptGoodsApplyVo> findDeptGoodsAuditList(QueryInfo<DeptGoodsApplyVo> queryInfo) throws Exception;

	/**
	 * 审核科室产品申请
	 * 
	 * @param vo
	 * @throws Exception
	 */
	void deptGoodsAuditList(List<DeptGoodsApplyVo> vo) throws Exception;

	/**
	 * 驳回科室产品申请
	 * 
	 * @param vo
	 */
	void deptGoodsReject(DeptGoodsApplyVo vo);

}
