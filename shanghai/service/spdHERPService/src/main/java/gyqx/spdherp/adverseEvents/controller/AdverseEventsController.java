package gyqx.spdherp.adverseEvents.controller;

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
import gyqx.spdherp.adverseEvents.service.IAdverseEventsService;
import gyqx.spdherp.adverseEvents.vo.AdverseEventsVo;
import gyqx.spdherp.po.AdverseEvents;

@Controller
@RequestMapping(value = "adverseEvents/adverseEvents")
public class AdverseEventsController extends BaseController {
	@Resource
	private IAdverseEventsService adverseEventsService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult<AdverseEventsVo> add(@RequestBody @Valid AdverseEventsVo adverseEvents, Errors error)
			throws Exception {
		AjaxResult<AdverseEventsVo> result = new AjaxResult<AdverseEventsVo>();
		filterErrors(error);
		AdverseEventsVo ret = adverseEventsService.add(adverseEvents);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult<AdverseEvents> get(@PathVariable("id") String id, Errors error) throws Exception {
		AjaxResult<AdverseEvents> result = new AjaxResult<AdverseEvents>();
		filterErrors(error);
		AdverseEvents ret = adverseEventsService.get(id);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult<AdverseEvents> update(@RequestBody @Valid AdverseEvents adverseEvents, Errors error)
			throws Exception {
		AjaxResult<AdverseEvents> result = new AjaxResult<AdverseEvents>();
		filterErrors(error);
		AdverseEvents ret = adverseEventsService.update(adverseEvents);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult<List<AdverseEvents>> list(@RequestBody @Valid AdverseEvents adverseEvents, Errors error)
			throws Exception {
		AjaxResult<List<AdverseEvents>> result = new AjaxResult<List<AdverseEvents>>();
		filterErrors(error);
		List<AdverseEvents> ret = adverseEventsService.list(adverseEvents);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult<List<AdverseEvents>> query(@RequestBody @Valid QueryInfo<Map<String, String>> queryInfo,
			Errors error) throws Exception {
		AjaxResult<List<AdverseEvents>> result = new AjaxResult<List<AdverseEvents>>();
		filterErrors(error);
		List<AdverseEvents> ret = adverseEventsService.query(queryInfo.getPredicate(), queryInfo.getOrderBy(),
				queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult<QueryResult<AdverseEvents>> listByPage(@RequestBody @Valid QueryInfo<AdverseEvents> queryInfo,
			Errors error) throws Exception {
		AjaxResult<QueryResult<AdverseEvents>> result = new AjaxResult<QueryResult<AdverseEvents>>();
		filterErrors(error);
		QueryResult<AdverseEvents> ret = adverseEventsService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
}
