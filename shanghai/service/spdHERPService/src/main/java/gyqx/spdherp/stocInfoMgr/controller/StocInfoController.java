package gyqx.spdherp.stocInfoMgr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.exception.ParameterException;
import common.utils.UserOnlineStateUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.platform.sys.vo.SysOrgVo;
import gyqx.spdhdi.myInfos.service.ICompanyVoService;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdherp.stocInfoMgr.service.StocInfoService;
import gyqx.spdherp.stocInfoMgr.vo.StocInfoVo;

@Controller
@RequestMapping("/baseData/stocInfoMgr/stocInfo")
public class StocInfoController extends BaseController{
	
	@Resource
	private StocInfoService stocInfoService;
	@Resource
	private ICompanyVoService companyVoService;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	
	/**
	 * 添加条件查询库存信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getStocsByParam")
	@ResponseBody
	public AjaxResult<Map<String, Object>> getStocsByParam(@RequestBody Map<String, Object> param) throws Exception{
		AjaxResult<Map<String, Object>> res = new AjaxResult<>();
		CompanyInfoVo companyInfo = companyVoService.getCompanyInfoById(userOnlineStateUtils.getCurrent().getCorpId());
		List<StocInfoVo> stocInfoList = stocInfoService.queryStocInfo(param);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comanyInfo", companyInfo);
		map.put("stocInfoList", stocInfoList);
		res.setData(map);
		return res;
	}
	
	/**
	 * 查询科室信息
	 * @param stocInfoVo
	 * @return
	 */
	@RequestMapping("/getDeptsInfo")
	@ResponseBody
	public AjaxResult<List<SysOrgVo>> getDeptsInfo(@RequestBody StocInfoVo stocInfoVo) {
		AjaxResult<List<SysOrgVo>> res = new AjaxResult<>();
		List<SysOrgVo> data = stocInfoService.getOrgInfo();
		res.setData(data);
		return res;
	}
	
	/**
	 * 查询库房详情
	 * @param stocInfoVo
	 * @return
	 */
	@RequestMapping("/getCurrentStocs")
	@ResponseBody
	public AjaxResult<StocInfoVo> getCurrentStocs(@RequestBody StocInfoVo stocInfoVo) {
		AjaxResult<StocInfoVo> res = new AjaxResult<>();
		StocInfoVo data = stocInfoService.queryStocById(stocInfoVo.getId());
		res.setData(data);
		return res;
	}
	
	
	/**
	 * 保存库房信息
	 * @param stocInfoVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult<StocInfoVo> update(@RequestBody @Valid StocInfoVo stocInfoVo, Errors error)
			throws Exception {
		AjaxResult<StocInfoVo> res = new AjaxResult<>();
		filterErrors(error);
		if (stocInfoVo.getId() != null && stocInfoVo.getId() != "") {
			stocInfoVo =  stocInfoService.updateStocInfo(stocInfoVo);
		} else {
			if(checkName(stocInfoVo.getStocName(), stocInfoVo.getStocLevel())){
				throw new ParameterException("库房名称重复");
			}else{
				stocInfoVo = stocInfoService.insertStocInfo(stocInfoVo);
			}
		}
		res.setData(stocInfoVo);
		return res;
	}
	
	/**
	 * 删除库房信息
	 * @param stocInfoVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult<StocInfoVo> delete(@RequestBody @Valid StocInfoVo stocInfoVo ,Errors error) throws Exception
	{
		AjaxResult<StocInfoVo> res = new AjaxResult<>();
		filterErrors(error);
		stocInfoService.deleteStocInfo(stocInfoVo);
		return res;
	}
	
	/**
	 * 同一库房下有相同的名称
	 * @param name
	 * @param stocLevel
	 * @return
	 * @throws Exception
	 */
	public boolean checkName(String name, String stocLevel) throws Exception{
		Map<String, Object> param = new HashMap<>();
		param.put("stocLevel", stocLevel);
		param.put("stocName", name); // 确认相似的
		List<StocInfoVo> stocInfos = stocInfoService.queryStocInfo(param);
		if(stocInfos.size() > 0){
			for (StocInfoVo info : stocInfos) {
				if(name.equals(info.getStocName())){
					return true;
				}
			}
		}
		return false;
	}
	
}
