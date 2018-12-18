package gyqx.spdherp.provManager.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.UserOnlineStateUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.provManager.service.IProvDeptUserRelationService;
import gyqx.spdherp.provManager.vo.ProvDeptUserRelationVo;
import gyqx.spdherp.provManager.vo.ProvDeptUserVo;
import gyqx.spdherp.provManager.vo.ProvHosRelationVo;
import gyqx.spdherp.provManager.vo.ProvUserRelationVo;

/**
 * @author Administrator 科室商品申请请求
 */
@Controller
@RequestMapping(value = "provMgr/provDeptUserRelation")
public class ProvDeptUserRelationController extends BaseController {

	@Resource
	private IProvDeptUserRelationService provDeptUserRelationService;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	

	/**
	 * 供应商与多个用户建立关联关系
	 * @param provDeptUserRelationVo
	 * @throws Exception
	 */
	@RequestMapping(value = "provDeptUserRelationSubmit")
	@ResponseBody
	public AjaxResult<QueryResult<ProvDeptUserVo>> provDeptUserRelationSubmit(
			@RequestBody ProvDeptUserRelationVo provDeptUserRelationVo) throws Exception {
		AjaxResult<QueryResult<ProvDeptUserVo>> result = new AjaxResult<QueryResult<ProvDeptUserVo>>();
		provDeptUserRelationService.processProvDeptUserRelation(provDeptUserRelationVo);
		return result;
	}
	
	/**
	 * 删除供应商与多个用户关联关系
	 * @param provDeptUserRelationVo
	 * @throws Exception
	 */
	@RequestMapping(value = "batchDelete")
	@ResponseBody
	public AjaxResult<QueryResult<ProvDeptUserVo>> batchDelete(
			@RequestBody ProvDeptUserRelationVo provDeptUserRelationVo) throws Exception {
		AjaxResult<QueryResult<ProvDeptUserVo>> result = new AjaxResult<QueryResult<ProvDeptUserVo>>();
		provDeptUserRelationService.processDelProvDeptUserRelation(provDeptUserRelationVo);
		return result;
	}
	
	/**
	 * 我的职员查询
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "userlistByUnit")
	@ResponseBody
	@Deprecated
	public AjaxResult<QueryResult<ProvDeptUserVo>> userlistByUnit(@RequestBody @Valid QueryInfo<ProvDeptUserRelationVo> queryInfo,
			Errors error) throws Exception {		
		AjaxResult<QueryResult<ProvDeptUserVo>> result = new AjaxResult<QueryResult<ProvDeptUserVo>>();
		queryInfo.getQueryObject().setHosId(userOnlineStateUtils.getCurrent().getCorpId());	
		QueryResult<ProvDeptUserVo> data = provDeptUserRelationService.getUserList(queryInfo);
		result.setData(data);
		return result;
	}	
	
	/**
	 * 查询供应商科室人员关系数据
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "checkRelUserList")
	@ResponseBody
	@Deprecated
	public AjaxResult<QueryResult<ProvDeptUserVo>> checkRelUserList(@RequestBody @Valid QueryInfo<ProvDeptUserRelationVo> queryInfo,
			Errors error) throws Exception {		
		AjaxResult<QueryResult<ProvDeptUserVo>> result = new AjaxResult<QueryResult<ProvDeptUserVo>>();
		queryInfo.getQueryObject().setHosId(userOnlineStateUtils.getCurrent().getCorpId());	
		QueryResult<ProvDeptUserVo> data = provDeptUserRelationService.getCheckRelUserList(queryInfo);
		result.setData(data);
		return result;
	}	

	/**
	 * 查询用户与供应商关系列表
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "userProvRelationList")
	@ResponseBody
	public AjaxResult<QueryResult<ProvUserRelationVo>> userProvRelationList(@RequestBody @Valid QueryInfo<ProvUserRelationVo> queryInfo,
			Errors error) throws Exception {		
		AjaxResult<QueryResult<ProvUserRelationVo>> result = new AjaxResult<QueryResult<ProvUserRelationVo>>();
		QueryResult<ProvUserRelationVo> data = provDeptUserRelationService.getUserProvRelationList(queryInfo);
		result.setData(data);
		return result;
	}
	
	/**
	 * 查询除关系人已建立关系的医院与供应商关系列表
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "userHosProvRelationList")
	@ResponseBody
	public AjaxResult<QueryResult<ProvHosRelationVo>> userHosProvRelationList(@RequestBody @Valid QueryInfo<ProvHosRelationVo> queryInfo,
			Errors error) throws Exception {		
		AjaxResult<QueryResult<ProvHosRelationVo>> result = new AjaxResult<QueryResult<ProvHosRelationVo>>();
		QueryResult<ProvHosRelationVo> data = provDeptUserRelationService.getUserHosProvRelationList(queryInfo);
		result.setData(data);
		return result;
	}
}
