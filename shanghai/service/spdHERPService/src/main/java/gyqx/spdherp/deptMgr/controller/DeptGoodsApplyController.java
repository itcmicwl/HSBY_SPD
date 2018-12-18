package gyqx.spdherp.deptMgr.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.UserOnlineStateUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.deptMgr.service.IDeptGoodsApplyService;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyBatchAddVo;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplySubmitVo;
import gyqx.spdherp.deptMgr.vo.DeptGoodsApplyVo;
import gyqx.spdherp.po.DeptGoodsApply;

/**
 * @author Administrator 科室商品申请请求
 */
@Controller
@RequestMapping(value = "deptMgr/deptGoodsApply")
public class DeptGoodsApplyController extends BaseController {

	@Resource
	private IDeptGoodsApplyService deptGoodsApplyService;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;

	/**
	 * 查询科室商品申请列表
	 * 
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deptGoodsApplyList")
	@ResponseBody
	public AjaxResult<QueryResult<DeptGoodsApplyVo>> deptGoodsInfoList(
			@RequestBody QueryInfo<DeptGoodsApplyVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<DeptGoodsApplyVo>> result = new AjaxResult<QueryResult<DeptGoodsApplyVo>>();
		QueryResult<DeptGoodsApplyVo> list = deptGoodsApplyService.findDeptGoodsApplyList(queryInfo);
		result.setData(list);
		return result;
	}

	/**
	 * 科室商品申请批量新增查询，批量查询医院商品信息,且商品未加入到科室商品中
	 * 
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deptGoodsApplyBatchAddQuery")
	@ResponseBody
	public AjaxResult<QueryResult<HosGoodsInfo>> deptGoodsApplyBatchAddQuery(
			@RequestBody QueryInfo<DeptGoodsApplyVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<HosGoodsInfo>> result = new AjaxResult<QueryResult<HosGoodsInfo>>();
		QueryResult<HosGoodsInfo> res = deptGoodsApplyService.deptGoodsApplyBatchAddQuery(queryInfo);
		result.setData(res);
		return result;
	}

	/**
	 * 科室产品申请批量新增
	 * 
	 * @param deptGoodsApplyBatchAddVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deptGoodsApplyBatchAdd")
	@ResponseBody
	public AjaxResult<List<DeptGoodsApply>> deptGoodsApplyBatchAdd(
			@RequestBody DeptGoodsApplyBatchAddVo deptGoodsApplyBatchAddVo) throws Exception {
		AjaxResult<List<DeptGoodsApply>> result = new AjaxResult<List<DeptGoodsApply>>();
		List<DeptGoodsApply> res = deptGoodsApplyService.deptGoodsApplyBatchAdd(deptGoodsApplyBatchAddVo);
		result.setData(res);
		return result;
	}

	/**
	 * 科室产品申请批量提交
	 * 
	 * @param deptGoodsApplyBatchAddVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deptGoodsApplyBatchSubmit")
	@ResponseBody
	public AjaxResult<List<DeptGoodsApply>> deptGoodsApplyBatchSubmit(
			@RequestBody DeptGoodsApplySubmitVo deptGoodsApplySubmitVo) throws Exception {
		AjaxResult<List<DeptGoodsApply>> result = new AjaxResult<List<DeptGoodsApply>>();
		deptGoodsApplyService.processDeptGoodsApplySubmit(deptGoodsApplySubmitVo);
		return result;
	}

	/**
	 * 更新科室商品申请状态为已阅
	 * 
	 * @param deptGoodsApplyVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deptGoodsApplyRead")
	@ResponseBody
	public AjaxResult<List<DeptGoodsApply>> deptGoodsApplyRead(@RequestBody DeptGoodsApplyVo deptGoodsApplyVo)
			throws Exception {
		AjaxResult<List<DeptGoodsApply>> result = new AjaxResult<List<DeptGoodsApply>>();
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		String deptId = userOnlineStateUtils.getCurrent().getOrgId();
		deptGoodsApplyService.updateDeptGoodsApplyRead(hosId, deptId, deptGoodsApplyVo.getId(),
				deptGoodsApplyVo.getVersion());
		return result;
	}

	/**
	 * 更新科室商品申请原油
	 * 
	 * @param deptGoodsApplyVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deptGoodsApplyReason")
	@ResponseBody
	public AjaxResult<List<DeptGoodsApply>> deptGoodsApplyReason(@RequestBody DeptGoodsApplyVo deptGoodsApplyVo)
			throws Exception {
		AjaxResult<List<DeptGoodsApply>> result = new AjaxResult<List<DeptGoodsApply>>();
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		String deptId = userOnlineStateUtils.getCurrent().getOrgId();
		deptGoodsApplyService.updateDeptGoodsApplyReason(hosId, deptId, deptGoodsApplyVo.getId(),
				deptGoodsApplyVo.getVersion(), deptGoodsApplyVo.getApplyReason());
		return result;
	}

	/**
	 * 删除科室商品申请数据
	 * 
	 * @param deptGoodsApplyVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteDeptGoodsApply")
	@ResponseBody
	public AjaxResult<List<DeptGoodsApply>> deleteDeptGoodsApply(@RequestBody DeptGoodsApplyVo deptGoodsApplyVo)
			throws Exception {
		AjaxResult<List<DeptGoodsApply>> result = new AjaxResult<List<DeptGoodsApply>>();
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		String deptId = userOnlineStateUtils.getCurrent().getOrgId();
		deptGoodsApplyService.deleteDeptGoodsApply(hosId, deptId, deptGoodsApplyVo.getId());
		return result;
	}
}
