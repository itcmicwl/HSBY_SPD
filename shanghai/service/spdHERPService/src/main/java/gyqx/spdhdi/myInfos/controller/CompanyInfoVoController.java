package gyqx.spdhdi.myInfos.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.myInfos.service.ICompanyVoService;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdhdi.po.BasCompanyCertificateImage;

@Controller
@RequestMapping(value = "myInfo/companyInfo")
public class CompanyInfoVoController extends BaseController {
	@Resource
	private ICompanyVoService companyVoService;

	@RequestMapping(value = "getCompanyInfo")
	@ResponseBody
	public AjaxResult<CompanyInfoVo> getCompanyInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody String corpId)
			throws Exception {
		AjaxResult<CompanyInfoVo> result = new AjaxResult<CompanyInfoVo>();
		CompanyInfoVo companyInfoVo = companyVoService.getCompanyInfo(corpId.replace("=", ""));
		result.setData(companyInfoVo);
		return result;
	}
	
	@RequestMapping(value = "updateCompanyInfoVo")
	@ResponseBody
	public AjaxResult<CompanyInfoVo> updateCompanyInfoVo(@RequestBody @Valid CompanyInfoVo company, Errors error) throws Exception {
		AjaxResult<CompanyInfoVo> res = new AjaxResult<CompanyInfoVo>();
		filterErrors(error);
		company = companyVoService.updateCompanyInfoVo(company);
		res.setData(company);
		return res;
	}
	
	@RequestMapping(value = "updateCompanyCertificate")
	@ResponseBody
	public AjaxResult<CompanyInfoVo> updateCompanyCertificate(@RequestBody @Valid CompanyInfoVo company, Errors error) throws Exception {
		AjaxResult<CompanyInfoVo> res = new AjaxResult<CompanyInfoVo>();
		filterErrors(error);
		company = companyVoService.updateCompanyCertificate(company);
		res.setData(company);
		return res;
	}
	
	@RequestMapping(value = "insertCompanyCertificate")
	@ResponseBody
	public AjaxResult<CompanyInfoVo> insertCompanyCertificate(@RequestBody @Valid CompanyInfoVo company, Errors error) throws Exception {
		AjaxResult<CompanyInfoVo> res = new AjaxResult<CompanyInfoVo>();
		filterErrors(error);
		company = companyVoService.insertCompanyCertificate(company);
		res.setData(company);
		return res;
	}
	
	@RequestMapping(value = "updateLogo")
	@ResponseBody
	public AjaxResult<CompanyInfoVo> updateLogo(@RequestBody @Valid CompanyInfoVo company, Errors error) throws Exception {
		AjaxResult<CompanyInfoVo> result = new AjaxResult<CompanyInfoVo>();
		filterErrors(error);
		int res = companyVoService.updateLogo(company);
		if (res < 1) {
			result.setCode(-1);
			result.setMsg("操作失败！");
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "deleteImage")
	@ResponseBody
	public AjaxResult deleteImage(@RequestBody @Valid BasCompanyCertificateImage basCompanyCertificateImage) throws Exception {
		AjaxResult result = new AjaxResult();
		int res = companyVoService.deleteImage(basCompanyCertificateImage.getId());
		if (res < 1) {
			result.setCode(-1);
			result.setMsg("操作失败！");
		}
		return result;
	}
}
