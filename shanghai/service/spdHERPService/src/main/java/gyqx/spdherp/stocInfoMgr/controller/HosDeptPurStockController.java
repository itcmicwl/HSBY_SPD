package gyqx.spdherp.stocInfoMgr.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.stocInfoMgr.service.HosDeptPurStockService;
import gyqx.spdherp.stocInfoMgr.vo.HosDeptPurStockVo;

@Controller
@RequestMapping("/baseData/stocInfoMgr/hosDeptPurStock")
public class HosDeptPurStockController extends BaseController {

	@Resource
	private HosDeptPurStockService hosDeptPurStockService;
	
	/**
	 * 查询科室请领关系
	 * @param hosDeptPurStockVo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/queryDeptRelation")
	@ResponseBody
	public AjaxResult<List<HosDeptPurStockVo>> queryDeptRelation(@RequestBody HosDeptPurStockVo hosDeptPurStockVo) throws Exception {
		AjaxResult<List<HosDeptPurStockVo>> res = new AjaxResult<>();
		List<HosDeptPurStockVo> deptLists = hosDeptPurStockService.queryDeptRelation(hosDeptPurStockVo);
		res.setData(deptLists);
		return res;
	}

	/**
	 * 保存科室请领关系
	 * @param hosDeptPurStockVo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/deptRelationSave")
	@ResponseBody
	public AjaxResult deptRelationSave(@RequestBody HosDeptPurStockVo hosDeptPurStockVo) throws Exception {
		AjaxResult res = new AjaxResult();
		hosDeptPurStockService.deptRelationSave(hosDeptPurStockVo);
		return res;
	}
	
	/**
	 * 设置默认请购科室
	 * @param hosDeptPurStockVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/setDefaultDept")
	@ResponseBody
	public AjaxResult<HosDeptPurStockVo> setDefaultDept(@RequestBody @Valid HosDeptPurStockVo hosDeptPurStockVo,Errors error) throws Exception{
		AjaxResult<HosDeptPurStockVo> res = new AjaxResult<>();
		filterErrors(error);
		hosDeptPurStockService.setDefaultDept(hosDeptPurStockVo);
		return res;
	}
}
