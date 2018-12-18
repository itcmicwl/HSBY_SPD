package gyqx.spdhdi.myInfos.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.myInfos.service.IProvHosInfoVoService;
import gyqx.spdhdi.myInfos.vo.HosInfoVo;
import gyqx.spdhdi.myInfos.vo.HosProvVo;
import gyqx.spdhdi.myInfos.vo.ProvHosInfoVo;
@Controller
@RequestMapping(value = "myInfo/provHosInfo")
public class ProvHosInfoVoController extends BaseController {
	@Resource
	IProvHosInfoVoService provHosInfoVoService;

	@RequestMapping(value = "provHosInfoVoList")
	@ResponseBody
	public AjaxResult<QueryResult<ProvHosInfoVo>> getProvHosInfoVoList(
			@RequestBody QueryInfo<ProvHosInfoVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<ProvHosInfoVo>> result = new AjaxResult<QueryResult<ProvHosInfoVo>>();
		QueryResult<ProvHosInfoVo> lstProvHosInfoVoList = provHosInfoVoService.getProvHosInfoVoList(queryInfo);
		result.setData(lstProvHosInfoVoList);
		return result;
	}
	
	@RequestMapping(value = "getHosInfoVoList")
	@ResponseBody
	public AjaxResult<QueryResult<HosInfoVo>> getHosInfoVoList(@RequestBody QueryInfo<HosInfoVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<HosInfoVo>> result = new AjaxResult<QueryResult<HosInfoVo>>();
		QueryResult<HosInfoVo> lstProvHosInfoVoList = provHosInfoVoService.getHosInfoVoList(queryInfo);
		result.setData(lstProvHosInfoVoList);
		return result;
	}
	
	@RequestMapping(value = "getHosProvVoList")
	@ResponseBody
	public AjaxResult<QueryResult<HosProvVo>> getHosProvVoList(@RequestBody QueryInfo<HosProvVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<HosProvVo>> result = new AjaxResult<QueryResult<HosProvVo>>();
		QueryResult<HosProvVo> lstProvHosInfoVoList = provHosInfoVoService.getHosProvVoList(queryInfo);
		result.setData(lstProvHosInfoVoList);
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "insert")
	@ResponseBody
	public AjaxResult insertProvHosInfo(@RequestBody @Valid ProvHosInfoVo provHosInfoVo) throws Exception {
		AjaxResult result = new AjaxResult();
//		if (sysProjectMenuVo.getCode() == null || "".equals(sysProjectMenuVo.getCode())) {
//			ValidateException.Throw("code", "编码不能为空", "");
//		}
//		boolean flag = provHosInfoVoService.existProMenuCode(sysProjectMenuVo);
//		if (flag) {
//			ValidateException.Throw("code", String.format("%s 已存在。", sysProjectMenuVo.getCode()),
//					sysProjectMenuVo.getCode());
//		}
		int res = provHosInfoVoService.insertProvHosInfo(provHosInfoVo);
		if (res < 1) {
			result.setCode(-1);
			result.setMsg("操作失败！");
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult setProvHosInfo(@RequestBody @Valid ProvHosInfoVo provHosInfoVo) throws Exception {
		AjaxResult result = new AjaxResult();
		int res = provHosInfoVoService.setProvHosInfo(provHosInfoVo);
		if (res < 1) {
			result.setCode(-1);
			result.setMsg("操作失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "setStatus")
	@ResponseBody
	public AjaxResult<ProvHosInfoVo> setStatus(@RequestBody ProvHosInfoVo provHosInfoVo) throws Exception {
		AjaxResult<ProvHosInfoVo> res = new AjaxResult<ProvHosInfoVo>();
		int flag = provHosInfoVoService.setStatus(provHosInfoVo);
		if(flag>0){
			res.setCode(0);
			res.setMsg("操作失败");
		}else{
			res.setCode(-1);
			res.setMsg("操作成功");
		}
		return res;
	}
	
	/**
	 * （根据医院ID）解除关系
	 * @param provHosInfoVo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "delete")
	@ResponseBody
	public AjaxResult deleteByPrimaryKey(@RequestBody @Valid ProvHosInfoVo provHosInfoVo) throws Exception {
		AjaxResult result = new AjaxResult();
		if (StringUtils.isEmpty(provHosInfoVo.getHosId())) {
			ValidateException.Throw("ID", "医院ID不能为空", "");
		}
		int res = provHosInfoVoService.deleteByPrimaryKey(provHosInfoVo.getHosId());
		if (res < 1) {
			result.setCode(-1);
			result.setMsg("操作失败！");
		}
		return result;
	}
	
}
