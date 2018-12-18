package gyqx.spdherp.surgery.controller;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.SurgeryPkgDefList;
import gyqx.spdherp.surgery.service.ISurgeryPkgDefListService;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefListVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "surgery/surgeryPkgDefList")
public class SurgeryPkgDefListController extends BaseController 
{
	@Resource
	private ISurgeryPkgDefListService  surgeryPkgDefListService;

	/**
	 * 更新手术包商品明细中的数量、是否消毒信息
	 * @param surgeryPkgDefList
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid SurgeryPkgDefList surgeryPkgDefList ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		int ret = surgeryPkgDefListService.update(surgeryPkgDefList);
		result.setData(ret);
		return result;
	}

	/**
	 * 删除手术包中的商品
	 * @param surgeryPkgDefListVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public AjaxResult delete(@RequestBody @Valid SurgeryPkgDefListVo surgeryPkgDefListVo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		int ret = surgeryPkgDefListService.delete(surgeryPkgDefListVo.getId());
		result.setData(ret);
		return result;
	}

	/**
	 * 批量删除手术包商品
	 * @param surgeryPkgDefListVos
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteByBatch")
	@ResponseBody
	public AjaxResult deleteByBatch(@RequestBody @Valid List<SurgeryPkgDefListVo> surgeryPkgDefListVos ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		int ret = surgeryPkgDefListService.deleteByBatch(surgeryPkgDefListVos);
		result.setData(ret);
		return result;
	}

	/**
	 * 查看商品详情
	 * @param surgeryPkgDefListVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsInfo")
	@ResponseBody
	public AjaxResult getGoodsInfo(@RequestBody @Valid SurgeryPkgDefListVo surgeryPkgDefListVo, Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HosGoodsInfoVo> ret = surgeryPkgDefListService.getHosGoodsInfoById(surgeryPkgDefListVo.getGoodsId());
		result.setData(ret);
		return result;
	}

	/**
	 * 根据医院医院、手术包，获取手术包对应商品信息
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<SurgeryPkgDefListVo> queryInfo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<SurgeryPkgDefListVo> ret = surgeryPkgDefListService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	/**
	 * 根据部门，获取可请购手术包商品
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list4QG")
	@ResponseBody
	public AjaxResult list4QG(@RequestBody @Valid SurgeryPkgDefListVo queryInfo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List ret = surgeryPkgDefListService.list4QG(queryInfo);
		result.setData(ret);
		return result;
	}
	/**
	 * 判断该手术包是否已经使用
	 * @param surgeryPkgDefListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "hasUseSurPkg")
	@ResponseBody
	public AjaxResult hasUseSurPkg(@RequestBody SurgeryPkgDefListVo surgeryPkgDefListVo) throws  Exception
	{
		AjaxResult result = new AjaxResult();
		List<SurgeryPkgDefListVo> ret = surgeryPkgDefListService.setUseSurPkg(surgeryPkgDefListVo.getSelSurIds());
		result.setData(ret);
		return result;
	}


	/**
	 * 根据手术包，医院id获取未导入的商品列表
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "listNotImportGoodsByPage")
	@ResponseBody
	public AjaxResult listNotImportGoodsByPage(@RequestBody @Valid QueryInfo<SurgeryPkgDefListVo> queryInfo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<SurgeryPkgDefListVo> ret = surgeryPkgDefListService.listNotImportGoodsByPage(queryInfo);
		result.setData(ret);
		return result;
	}

	/**
	 * 批量导入
	 * @param surgeryPkgDefListVos
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "importGoods")
	@ResponseBody
	public AjaxResult importGoods(@RequestBody @Valid List<SurgeryPkgDefListVo> surgeryPkgDefListVos , Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		int count = surgeryPkgDefListService.insertByBatch(surgeryPkgDefListVos);
		result.setData(count);
		return result;
	}
}
