package gyqx.spdherp.provManager.controller;

import java.util.List;

import javax.annotation.Resource;

import gyqx.platform.po.SysOrg;
import gyqx.spdherp.provManager.vo.*;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.po.HosCollector;
import gyqx.spdherp.po.HosCollectorProv;
import gyqx.spdherp.provManager.service.IDistriRelationService;

@Controller
@RequestMapping(value = "provMgr/distriRelation")
public class DistriRelationController extends BaseController {
	@Resource
	private IDistriRelationService service;

	/**
	 * 根据医院ID查询集配商信息
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryCollectorList")
	@ResponseBody
	public AjaxResult<QueryResult<DistriRelationVo>> queryCollectorList(
			@RequestBody QueryInfo<DistriRelationVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<DistriRelationVo>> result = new AjaxResult<QueryResult<DistriRelationVo>>();
		QueryResult<DistriRelationVo> list = service.queryCollectorList(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * 我要采购中选择用户建立关系的供应商的集配商信息 
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryUserRelationCollectorList")
	@ResponseBody
	public AjaxResult<QueryResult<DistriRelationVo>> queryUserRelationCollectorList(
			@RequestBody QueryInfo<DistriRelationVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<DistriRelationVo>> result = new AjaxResult<QueryResult<DistriRelationVo>>();
		QueryResult<DistriRelationVo> list = service.queryUserRelationCollectorList(queryInfo);
		result.setData(list);
		return result;
	}

	/**
	 * 根据医院ID、集配商ID查询合作商信息
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryPartnerList")
	@ResponseBody
	public AjaxResult<QueryResult<DistriRelationVo>> queryPartnerList(@RequestBody QueryInfo<PartnerParam> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<DistriRelationVo>> result = new AjaxResult<QueryResult<DistriRelationVo>>();
		QueryResult<DistriRelationVo> list = service.queryPartnerList(queryInfo);
		result.setData(list);
		return result;
	}
	
	/**
	 * 我要采购中选择用户建立关系的供应商的供应商信息 
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryUserRelationPartnerList")
	@ResponseBody
	public AjaxResult<QueryResult<DistriRelationVo>> queryUserRelationPartnerList(@RequestBody QueryInfo<PartnerParam> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<DistriRelationVo>> result = new AjaxResult<QueryResult<DistriRelationVo>>();
		QueryResult<DistriRelationVo> list = service.queryUserRelationPartnerList(queryInfo);
		result.setData(list);
		return result;
	}

	/**
	 * 批量导入集配商
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "importCollectorList")
	@ResponseBody
	public AjaxResult<List<HosCollector>> importCollectorList(@RequestBody List<ProvHosInfoVo> provHosInfoVos)
			throws Exception {
		AjaxResult<List<HosCollector>> result = new AjaxResult<List<HosCollector>>();
		List<HosCollector> res = service.importCollectorList(provHosInfoVos);
		result.setData(res);
		return result;
	}

	/**
	 * 批量导入合作商
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "importPartnerList")
	@ResponseBody
	public AjaxResult<List<HosCollectorProv>> importPartnerList(@RequestBody PartnerImortParam param) throws Exception {
		AjaxResult<List<HosCollectorProv>> result = new AjaxResult<List<HosCollectorProv>>();
		List<HosCollectorProv> res = service.importPartnerList(param);
		result.setData(res);
		return result;
	}

	/**
	 * 更新合作商配送关系类型
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updatePartnerKind")
	@ResponseBody
	public AjaxResult<DistriRelationVo> updatePartnerKind(@RequestBody DistriRelationVo param) throws Exception {
		AjaxResult<DistriRelationVo> result = new AjaxResult<DistriRelationVo>();
		DistriRelationVo res = service.updatePartnerKind(param);
		result.setData(res);
		return result;
	}
	
	/**
	 * 更改是否启用二级供应商
	 * @param distriRelationVos
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateSubprov")
	@ResponseBody
	public AjaxResult<DistriRelationVo> updateSubprov(@RequestBody DistriRelationVo param) throws Exception {
		AjaxResult<DistriRelationVo> result = new AjaxResult<DistriRelationVo>();
		DistriRelationVo res = service.updateSubprov(param);
		result.setData(res);
		return result;
	}

	/**
	 * 批量删除集配商
	 * @param distriRelationVos
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "mulDeleteCollectorList")
	@ResponseBody
	public AjaxResult<List<HosCollector>> mulDeleteCollectorList(@RequestBody List<DistriRelationVo> distriRelationVos)
			throws Exception {
		AjaxResult<List<HosCollector>> result = new AjaxResult<List<HosCollector>>();
		List<HosCollector> res = service.mulDeleteCollectorList(distriRelationVos);
		result.setData(res);
		return result;
	}

	@RequestMapping(value = "getHosColProvs")
	@ResponseBody
	public AjaxResult<List<HosCollectorProvVo>> getHosColProvs(@RequestBody HosCollectorProvVo hosCollectorProvVo)
			throws Exception {
		AjaxResult<List<HosCollectorProvVo>> result = new AjaxResult<>();
		List<HosCollectorProvVo> lst = service.getHosColProvs(hosCollectorProvVo);
		result.setData(lst);
		return result;
	}

	/**
	 * 批量删除合作商
	 * @param distriRelationVos
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "mulDeletePartnerList")
	@ResponseBody
	public AjaxResult<List<HosCollector>> mulDeletePartnerList(@RequestBody List<DistriRelationVo> distriRelationVos)
			throws Exception {
		AjaxResult<List<HosCollector>> result = new AjaxResult<List<HosCollector>>();
		List<HosCollector> res = service.mulDeletePartnerList(distriRelationVos);
		result.setData(res);
		return result;
	}


	/**
	 * 根据医院ID、集配商ID查询合作商信息
	 * @param hosId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getCollectorList/{hosId}")
	@ResponseBody
	public AjaxResult<List<SysOrg>> getCollectorList(@PathVariable("hosId") String hosId)
			throws Exception {
		AjaxResult<List<SysOrg>> result = new AjaxResult<>();
		List<SysOrg> list = service.getCollectorList(hosId);
		result.setData(list);
		return result;
	}
    

	/**
	 * 查询产品信息
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryGoodsList")
	@ResponseBody
	public AjaxResult<QueryResult<ProvProvGoodsVo>> queryGoodsList(@RequestBody QueryInfo<ProvProvGoodsVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<ProvProvGoodsVo>> res = new AjaxResult<QueryResult<ProvProvGoodsVo>>();
		QueryResult<ProvProvGoodsVo> list = service.queryGoodsList(queryInfo);
		res.setData(list);
		return res;
	}

	/**
	 * 默认选中的产品
	 * @param vo
	 * @return
	 */
	@RequestMapping("/getProvProvGoodsId")
	@ResponseBody
	public AjaxResult<List<ProvProvGoodsVo>> getProvProvGoodsId(@RequestBody ProvProvGoodsVo vo) {
		AjaxResult<List<ProvProvGoodsVo>> res = new AjaxResult<List<ProvProvGoodsVo>>();
		List<ProvProvGoodsVo> goodsIds = service.getProvProvGoodsId(vo);
		res.setData(goodsIds);
		return res;
	}

	/**
	 * 保存集配商配送商商品信息
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/provProvGoodsSave")
	@ResponseBody
	public AjaxResult<List<ProvProvGoodsVo>> provProvGoodsSave(@RequestBody ProvProvGoodsVo vo) throws Exception {
		AjaxResult<List<ProvProvGoodsVo>> res = new AjaxResult<List<ProvProvGoodsVo>>();
		List<ProvProvGoodsVo> goodsVos = service.provProvGoodsSave(vo);
		res.setData(goodsVos);
		return res;
	}


}
