package gyqx.spdhdi.myInfos.controller;


import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.transform.Tx;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.myInfos.service.IProvHosDeptInfoCodeService;
import gyqx.spdhdi.myInfos.vo.ProvHosDeptInfoCodeVo;
import gyqx.spdhdi.myInfos.vo.ProvHosInfoCodeVo;
import gyqx.spdhdi.po.ProvHosDeptInfoCode;

@Controller
@RequestMapping(value = "myInfos/provHosDeptInfoCode")
public class ProvHosDeptInfoCodeController extends BaseController 
{
	@Resource
	private IProvHosDeptInfoCodeService  provHosDeptInfoCodeService;

	@RequestMapping(value = "add/{selectedHosDeptId}")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid ProvHosDeptInfoCodeVo provHosDeptInfoCodeVo ,Errors error,@PathVariable("selectedHosDeptId") String selectedHosDeptId  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		ProvHosDeptInfoCode phdc = new ProvHosDeptInfoCode();
		Tx.transform(provHosDeptInfoCodeVo).to(phdc);
		phdc.setHosDeptId(selectedHosDeptId);
		//需要通deptid 找到CompanyId
		ProvHosDeptInfoCode ret = provHosDeptInfoCodeService.add(phdc);
		result.setData(ret);
		return result;
	}
	
	@RequestMapping(value = "update/{selectedHosDeptId}")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid ProvHosDeptInfoCodeVo provHosDeptInfoCodeVo ,Errors error,@PathVariable("selectedHosDeptId") String selectedHosDeptId    )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		ProvHosDeptInfoCode phdc = new ProvHosDeptInfoCode();
		Tx.transform(provHosDeptInfoCodeVo).to(phdc);
		phdc.setHosDeptId(selectedHosDeptId);
		ProvHosDeptInfoCode ret = provHosDeptInfoCodeService.update(phdc);
		result.setData(ret);
		return result;
	}
	
	@RequestMapping(value = "getThePHDeptCodeVo")
	@ResponseBody
	public AjaxResult getThePHDeptCodeVo(@RequestBody @Valid QueryInfo<ProvHosDeptInfoCodeVo> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<ProvHosDeptInfoCodeVo> ret = provHosDeptInfoCodeService.list_PH_dept_code_voByPage(queryInfo);
		if(ret.getData().size()>0)
		result.setData(ret.getData().get(0));
		return result;
	}
	@RequestMapping(value = "getThePHDeptCodeVoByProvIdAndOrgId/{provId}/{orgId}")
	@ResponseBody
	public AjaxResult getThePHDeptCodeVoByProvIdAndOrgId(@PathVariable("provId") String provId , @PathVariable("orgId") String orgId )  throws  Exception  
	{ 
		AjaxResult result = new AjaxResult();
		ProvHosDeptInfoCodeVo res = provHosDeptInfoCodeService.getThePHDeptCodeVoByProvIdAndOrgId(provId,orgId);
		result.setData(res);
		return result;
	}
}
