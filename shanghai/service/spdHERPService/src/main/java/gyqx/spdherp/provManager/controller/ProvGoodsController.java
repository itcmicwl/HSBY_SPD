package gyqx.spdherp.provManager.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.provManager.service.IProvGoodsService;
import gyqx.spdherp.provManager.vo.ProvGoodsVo;

@Controller
@RequestMapping(value = "provMgr/provGoods")
public class ProvGoodsController extends BaseController {
	@Resource
	private IProvGoodsService service;
	
	/**
	 * 根据医院ID、供应商ID查询供应商产品信息
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryProvGoodsList")
	@ResponseBody
	public AjaxResult<QueryResult<ProvGoodsVo>> queryProvGoodsList(@RequestBody QueryInfo<ProvGoodsVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<ProvGoodsVo>> result = new AjaxResult<QueryResult<ProvGoodsVo>>();
		QueryResult<ProvGoodsVo> list = service.queryProvGoodsList(queryInfo);
		result.setData(list);
		return result;
	}
	
}
