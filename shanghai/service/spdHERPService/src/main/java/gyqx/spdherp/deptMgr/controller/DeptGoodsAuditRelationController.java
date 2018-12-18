package gyqx.spdherp.deptMgr.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.web.AjaxResult;
import gyqx.spdherp.deptMgr.service.IDeptGoodsAuditRelationService;
import gyqx.spdherp.deptMgr.service.IDeptGoodsInfoService;
import gyqx.spdherp.deptMgr.vo.ReceiveAddressVo;
import gyqx.spdherp.po.DeptGoodsAuditRelation;
import gyqx.spdherp.po.HosDeptApplyUpper;
import common.web.BaseController;

@Controller
@RequestMapping(value = "deptMgr/deptGoodsAuditAelation")
public class DeptGoodsAuditRelationController extends BaseController {
	@Resource
	private IDeptGoodsAuditRelationService service;
	/**
	 * 新增
	 * 
	 * @param IDeptGoodsAuditAelation
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public AjaxResult<ReceiveAddressVo> insert(@RequestBody @Valid List<DeptGoodsAuditRelation> deptGoodsAuditRelations, Errors error)
			throws Exception {
		AjaxResult<ReceiveAddressVo> res = new AjaxResult<>();
		filterErrors(error);
		service.insertDeptGoodsAuditRelation(deptGoodsAuditRelations);
		return res;
	}
	
	/**
	 * 查询
	 * 
	 * @param IDeptGoodsAuditAelation
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/select")
	@ResponseBody
	public AjaxResult<ReceiveAddressVo> select( @RequestBody @Valid DeptGoodsAuditRelation deptGoodsAuditRelations,Errors error)
			throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<DeptGoodsAuditRelation> ret = service.selectByAuditer(deptGoodsAuditRelations.getAuditer());
		result.setData(ret);
		return result;
	}
	}
