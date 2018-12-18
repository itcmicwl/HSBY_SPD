package gyqx.spdherp.deptMgr.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import gyqx.spdherp.deptMgr.service.IDeptGoodsAuditService;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyVo;

@Controller
@RequestMapping(value = "deptMgr/deptGoodsAudit")
public class DeptGoodsAuditController {

	@Resource
	private IDeptGoodsAuditService auditService;
	
	/**
	 * 查询科室商品申请列表
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "findDeptGoodsAuditList")
	@ResponseBody
	public AjaxResult<QueryResult<DeptGoodsApplyVo>> findDeptGoodsAuditList(@RequestBody QueryInfo<DeptGoodsApplyVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<DeptGoodsApplyVo>> result = new AjaxResult<QueryResult<DeptGoodsApplyVo>>();
		QueryResult<DeptGoodsApplyVo> list = auditService.findDeptGoodsAuditList(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * 批量审核通过
	 * @param queryInfo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "deptGoodsAuditList")
	@ResponseBody
	public AjaxResult deptGoodsAuditList(@RequestBody List<DeptGoodsApplyVo> queryInfo) throws Exception{
		AjaxResult result = new AjaxResult<>();
		auditService.deptGoodsAuditList(queryInfo);
		return result;
	}
	
	/**
	 * 驳回
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deptGoodsReject")
	@ResponseBody
	public AjaxResult deptGoodsReject(@RequestBody DeptGoodsApplyVo queryInfo) throws Exception{
		AjaxResult result = new AjaxResult<>();
		auditService.deptGoodsReject(queryInfo);
		return result;
	}
	
}
