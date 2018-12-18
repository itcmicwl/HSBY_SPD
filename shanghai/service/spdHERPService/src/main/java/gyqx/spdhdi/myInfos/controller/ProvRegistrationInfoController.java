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
import gyqx.spdhdi.myInfos.service.IProvRegGoodsInfoQRService;
import gyqx.spdhdi.myInfos.service.IProvRegistrationInfoService;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQ;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQR;
import gyqx.spdhdi.po.ProvRegistrationInfo;

@Controller
@RequestMapping(value = "myInfo/provRegistrationInfo")
public class ProvRegistrationInfoController extends BaseController {
	@Resource
	private IProvRegistrationInfoService provRegistrationInfoService;
	@Resource
	private IProvRegGoodsInfoQRService provRegGoodsInfoQRService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid ProvRegistrationInfo provRegistrationInfo, Errors error)
			throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		ProvRegistrationInfo ret = provRegistrationInfoService.add(provRegistrationInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		ProvRegistrationInfo ret = provRegistrationInfoService.get(id);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid ProvRegistrationInfo provRegistrationInfo, Errors error)
			throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		ProvRegistrationInfo ret = provRegistrationInfoService.update(provRegistrationInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid ProvRegistrationInfo provRegistrationInfo, Errors error)
			throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<ProvRegistrationInfo> ret = provRegistrationInfoService.list(provRegistrationInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String, String>> queryInfo, Errors error)
			throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<ProvRegistrationInfo> ret = provRegistrationInfoService.query(queryInfo.getPredicate(),
				queryInfo.getOrderBy(), queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<ProvRegistrationInfo> queryInfo, Errors error)
			throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<ProvRegistrationInfo> ret = provRegistrationInfoService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "regCertsListByPage")
	@ResponseBody
	public AjaxResult<QueryResult<ProvRegGoodsInfoQR>> regCertsListByPage(@RequestBody @Valid QueryInfo<ProvRegGoodsInfoQ> queryInfo, Errors error)
			throws Exception {
		AjaxResult<QueryResult<ProvRegGoodsInfoQR>> result = new AjaxResult<QueryResult<ProvRegGoodsInfoQR>>();
		filterErrors(error);
		QueryResult<ProvRegGoodsInfoQR> ret = provRegGoodsInfoQRService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
}
