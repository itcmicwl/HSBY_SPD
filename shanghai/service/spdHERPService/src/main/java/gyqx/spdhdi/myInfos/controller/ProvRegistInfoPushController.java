package gyqx.spdhdi.myInfos.controller;

import java.util.List;
import java.util.Map;

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
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.myInfos.service.ICompanyVoService;
import gyqx.spdhdi.myInfos.service.IProvRegistInfoPushService;
import gyqx.spdhdi.myInfos.vo.OutdateCertsImgVo;
import gyqx.spdhdi.myInfos.vo.OutdateCertsVo;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQ;
import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQ;
import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQR;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.ProvRegistInfoPush;

@Controller
@RequestMapping(value = "myInfo/provRegistInfoPush")
public class ProvRegistInfoPushController extends BaseController {
	@Resource
	private IProvRegistInfoPushService provRegistInfoPushService;
	@Resource
	private ICompanyVoService companyVoService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult<ProvRegistInfoPush> add(@RequestBody @Valid ProvRegistInfoPush provRegistInfoPush, Errors error)
			throws Exception {
		AjaxResult<ProvRegistInfoPush> result = new AjaxResult<ProvRegistInfoPush>();
		filterErrors(error);
		ProvRegistInfoPush ret = provRegistInfoPushService.add(provRegistInfoPush);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "multipleAdd")
	@ResponseBody
	public AjaxResult<List<ProvRegistInfoPush>> multipleAdd(
			@RequestBody @Valid List<ProvRegGoodsInfoQ> provRegGoodsInfoQs, Errors error) throws Exception {
		AjaxResult<List<ProvRegistInfoPush>> result = new AjaxResult<List<ProvRegistInfoPush>>();
		filterErrors(error);
		List<ProvRegistInfoPush> ret = provRegistInfoPushService.multipleAdd(provRegGoodsInfoQs);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult<ProvRegistInfoPush> get(@PathVariable("id") String id, Errors error) throws Exception {
		AjaxResult<ProvRegistInfoPush> result = new AjaxResult<ProvRegistInfoPush>();
		filterErrors(error);
		ProvRegistInfoPush ret = provRegistInfoPushService.get(id);
		result.setData(ret);
		return result;
	}

	/**
	 * 功能与update合并，该方法舍弃
	 * @param provRegistInfoPush
	 * @param error
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "update1")
	@ResponseBody
	public AjaxResult<ProvRegistInfoPush> update1(@RequestBody @Valid ProvRegistInfoPush provRegistInfoPush,
			Errors error) throws Exception {
		AjaxResult<ProvRegistInfoPush> result = new AjaxResult<ProvRegistInfoPush>();
		filterErrors(error);
		ProvRegistInfoPush ret = provRegistInfoPushService.update(provRegistInfoPush);
		result.setData(ret);
		return result;
	}*/
	
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult<ProvRegistInfoPush> update(@RequestBody @Valid ProvRegistInfoPushVo provRegistInfoPushVo,
			Errors error) throws Exception {
		AjaxResult<ProvRegistInfoPush> result = new AjaxResult<ProvRegistInfoPush>();
		filterErrors(error);
		ProvRegistInfoPush ret = provRegistInfoPushService.update(provRegistInfoPushVo);
		result.setData(ret);
		return result;
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult<List<ProvRegistInfoPush>> list(@RequestBody @Valid ProvRegistInfoPush provRegistInfoPush,
			Errors error) throws Exception {
		AjaxResult<List<ProvRegistInfoPush>> result = new AjaxResult<List<ProvRegistInfoPush>>();
		filterErrors(error);
		List<ProvRegistInfoPush> ret = provRegistInfoPushService.list(provRegistInfoPush);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult<List<ProvRegistInfoPush>> query(@RequestBody @Valid QueryInfo<Map<String, String>> queryInfo,
			Errors error) throws Exception {
		AjaxResult<List<ProvRegistInfoPush>> result = new AjaxResult<List<ProvRegistInfoPush>>();
		filterErrors(error);
		List<ProvRegistInfoPush> ret = provRegistInfoPushService.query(queryInfo.getPredicate(), queryInfo.getOrderBy(),
				queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}

	/**
	 * <供应商>推送证件注册证列表
	 * 
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult<QueryResult<ProvRegistInfoPush>> listByPage(
			@RequestBody @Valid QueryInfo<ProvRegistInfoPush> queryInfo, Errors error) throws Exception {
		AjaxResult<QueryResult<ProvRegistInfoPush>> result = new AjaxResult<QueryResult<ProvRegistInfoPush>>();
		filterErrors(error);
		QueryResult<ProvRegistInfoPush> ret = provRegistInfoPushService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}

	/**
	 * <供应商>推送证件注册证产品列表
	 * 
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pushedRegCertsListByPage")
	@ResponseBody
	public AjaxResult<QueryResult<ProvRegistGoodsPushQR>> pushedRegCertsListByPage(
			@RequestBody @Valid QueryInfo<ProvRegistGoodsPushQ> queryInfo, Errors error) throws Exception {
		AjaxResult<QueryResult<ProvRegistGoodsPushQR>> result = new AjaxResult<QueryResult<ProvRegistGoodsPushQR>>();
		filterErrors(error);
		QueryResult<ProvRegistGoodsPushQR> ret = provRegistInfoPushService.pushlistByPage(queryInfo);
		result.setData(ret);
		return result;
	}

	/**
	 * <医院>待审证件供应商列表
	 * 
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pushedRegCertsProvListByPage")
	@ResponseBody
	public AjaxResult<QueryResult<ProvRegistInfoPushVo>> pushedRegCertsProvListByPage(
			@RequestBody @Valid QueryInfo<ProvRegistInfoPushVo> queryInfo, Errors error) throws Exception {
		AjaxResult<QueryResult<ProvRegistInfoPushVo>> result = new AjaxResult<QueryResult<ProvRegistInfoPushVo>>();
		filterErrors(error);
		QueryResult<ProvRegistInfoPushVo> ret = provRegistInfoPushService.pushedRegCertsProvListByPage(queryInfo);
		result.setData(ret);
		return result;
	}

	/**
	 * <医院>待审证件供应商注册证产品列表
	 * 
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pushedGoodsProvListByPage")
	@ResponseBody
	public AjaxResult<QueryResult<ProvRegistInfoPushVo>> pushedGoodsProvListByPage(
			@RequestBody @Valid QueryInfo<ProvRegistInfoPushVo> queryInfo, Errors error) throws Exception {
		AjaxResult<QueryResult<ProvRegistInfoPushVo>> result = new AjaxResult<QueryResult<ProvRegistInfoPushVo>>();
		filterErrors(error);
		QueryResult<ProvRegistInfoPushVo> ret = provRegistInfoPushService.pushedGoodsProvListByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	
	/**
	 * <医院>溯源集合对象查询
	 * 
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getRetrospectMap")
	@ResponseBody
	public AjaxResult<Map<String,Object>> getRetrospectMap(
			@RequestBody @Valid QueryInfo<ProvRegistInfoPushVo> queryInfo, Errors error) throws Exception {
		AjaxResult<Map<String,Object>> result = new AjaxResult<Map<String,Object>>();
		filterErrors(error);
		Map<String,Object> ret = provRegistInfoPushService.getRetrospectMap(queryInfo);
		result.setData(ret);
		return result;
	}
	
	/**
	 * <医院>我的证件集合对象查询
	 * 
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPushedCertsMap")
	@ResponseBody
	public AjaxResult<Map<String,Object>> getPushedCertsMap(
			@RequestBody @Valid QueryInfo<ProvRegistInfoPushVo> queryInfo, Errors error) throws Exception {
		AjaxResult<Map<String,Object>> result = new AjaxResult<Map<String,Object>>();
		filterErrors(error);
		Map<String,Object> ret = provRegistInfoPushService.getPushedCertsMap(queryInfo);
		result.setData(ret);
		return result;
	}
	
	/**
	 * <医院>我的证件 过期证件图片查询
	 * 
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getOutdateCertsImg")
	@ResponseBody
	public AjaxResult<List<OutdateCertsImgVo>> getOutdateCertsImg(
			@RequestBody @Valid OutdateCertsVo outdateCertsVo, Errors error) throws Exception {
		AjaxResult<List<OutdateCertsImgVo>> result = new AjaxResult<List<OutdateCertsImgVo>>();
		filterErrors(error);
		List<OutdateCertsImgVo> ret = provRegistInfoPushService.getOutdateCertsImg(outdateCertsVo);
		result.setData(ret);
		return result;
	}
	
}
