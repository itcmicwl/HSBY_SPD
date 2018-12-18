package gyqx.spdherp.provManager.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.myInfos.vo.BasMfrsInfoVo;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdhdi.myInfos.vo.ProvFrwtsVoR;
import gyqx.spdhdi.myInfos.vo.ProvJxsqsVo;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.ProvCns;
import gyqx.spdherp.provManager.service.IProvCertsService;
import gyqx.spdherp.provManager.vo.OutdateCertsImgVo;
import gyqx.spdherp.provManager.vo.OutdateCertsVo;
import gyqx.spdherp.provManager.vo.ProvIncorruptAgrmtVo;
import gyqx.spdherp.provManager.vo.ProvSalesRepVo;

@Controller
@RequestMapping(value = "provMgr/provCerts")
public class ProvCertsController extends BaseController {
	@Resource
	private IProvCertsService service;
	
	/**
	 * 廉政协议书列表查询
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "listIncorruptAgrmt")
	@ResponseBody
	public AjaxResult<QueryResult<ProvIncorruptAgrmtVo>> listIncorruptAgrmt(@RequestBody QueryInfo<ProvRegistInfoPushVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<ProvIncorruptAgrmtVo>> result = new AjaxResult<QueryResult<ProvIncorruptAgrmtVo>>();
		QueryResult<ProvIncorruptAgrmtVo> list = service.listIncorruptAgrmt(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * 销售代表列表查询
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "salesRepList")
	@ResponseBody
	public AjaxResult<QueryResult<ProvSalesRepVo>> salesRepList(@RequestBody QueryInfo<ProvRegistInfoPushVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<ProvSalesRepVo>> result = new AjaxResult<QueryResult<ProvSalesRepVo>>();
		QueryResult<ProvSalesRepVo> list = service.salesRepList(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * 承诺书列表查询
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cnsList")
	@ResponseBody
	public AjaxResult<QueryResult<ProvCns>> cnsList(@RequestBody QueryInfo<ProvRegistInfoPushVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<ProvCns>> result = new AjaxResult<QueryResult<ProvCns>>();
		QueryResult<ProvCns> list = service.cnsList(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * 法人委托书列表查询
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "wtsList")
	@ResponseBody
	public AjaxResult<QueryResult<ProvFrwtsVoR>> wtsList(@RequestBody QueryInfo<ProvRegistInfoPushVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<ProvFrwtsVoR>> result = new AjaxResult<QueryResult<ProvFrwtsVoR>>();
		QueryResult<ProvFrwtsVoR> list = service.wtsList(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * 经销授权书列表查询
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "jxsqsList")
	@ResponseBody
	public AjaxResult<QueryResult<ProvJxsqsVo>> jxsqsList(@RequestBody QueryInfo<ProvRegistInfoPushVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<ProvJxsqsVo>> result = new AjaxResult<QueryResult<ProvJxsqsVo>>();
		QueryResult<ProvJxsqsVo> list = service.jxsqsList(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * 生产厂商列表查询
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getBasMfrsList")
	@ResponseBody
	public AjaxResult<QueryResult<BasMfrsInfoVo>> getBasMfrsList(@RequestBody QueryInfo<ProvRegistInfoPushVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<BasMfrsInfoVo>> result = new AjaxResult<QueryResult<BasMfrsInfoVo>>();
		QueryResult<BasMfrsInfoVo> list = service.getBasMfrsList(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * 注册证列表查询
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPushedRegCertsList")
	@ResponseBody
	public AjaxResult<QueryResult<ProvRegistInfoPushVo>> getPushedRegCertsList(@RequestBody QueryInfo<ProvRegistInfoPushVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<ProvRegistInfoPushVo>> result = new AjaxResult<QueryResult<ProvRegistInfoPushVo>>();
		QueryResult<ProvRegistInfoPushVo> list = service.getPushedRegCertsList(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * 根据医院ID、供应商ID查询供应商过期证件信息
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryProvOutdateCerts")
	@ResponseBody
	public AjaxResult<QueryResult<OutdateCertsVo>> queryProvOutdateCerts(@RequestBody QueryInfo<OutdateCertsVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<OutdateCertsVo>> result = new AjaxResult<QueryResult<OutdateCertsVo>>();
		QueryResult<OutdateCertsVo> list = service.queryProvOutdateCerts(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * <医院>我的证件 过期证件图片查询
	 * @param outdateCertsVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryOutdateCertsImg")
	@ResponseBody
	public AjaxResult<List<OutdateCertsImgVo>> queryOutdateCertsImg(@RequestBody @Valid OutdateCertsVo outdateCertsVo) 
			throws Exception {
		AjaxResult<List<OutdateCertsImgVo>> result = new AjaxResult<List<OutdateCertsImgVo>>();
		List<OutdateCertsImgVo> ret = service.queryOutdateCertsImg(outdateCertsVo);
		result.setData(ret);
		return result;
	}
	/**
	 * <医院>我的证件 过期证件图片查询
	 * @param outdateCertsVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getProvCompanyInfo")
	@ResponseBody
	public AjaxResult<CompanyInfoVo> getProvCompanyInfo(@RequestBody String provId) 
			throws Exception {
		AjaxResult<CompanyInfoVo> result = new AjaxResult<CompanyInfoVo>();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
}
