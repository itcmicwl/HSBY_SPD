package gyqx.spdherp.productsInfos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.UserOnlineStateUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.myInfos.service.ICompanyVoService;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdherp.po.OperatingPackageGoodsInfo;
import gyqx.spdherp.po.OperatingPackageKindcode;
import gyqx.spdherp.productsInfos.service.OperatingPackageKindcodeService;
import gyqx.spdherp.productsInfos.service.OperationPackService;
import gyqx.spdherp.productsInfos.vo.OperatingPackageGoodsInfoVo;
import gyqx.spdherp.productsInfos.vo.OperatingPackageInfoVo;
import gyqx.spdherp.productsInfos.vo.PackGoodsInfoVo;
import gyqx.spdherp.stocInfoMgr.vo.ElTreeVo;

/**
 * 手术包维护
 * 
 * @date 2017年10月26日 上午10:12:55
 */
@RequestMapping("/productsInfos/operationPackInfo")
@Controller
public class OperationPackController extends BaseController {

	@Resource
	private OperationPackService operationPackService;
	@Resource
	private ICompanyVoService companyVoService;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	@Resource
	private OperatingPackageKindcodeService operatingPackageKindcodeService;
	
	
	/**
	 * 查询手术包信息
	 * @param operatingPackageInfoVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPackInfo")
	@ResponseBody
	public AjaxResult<List<ElTreeVo>> queryPackInfo(@RequestBody OperatingPackageInfoVo operatingPackageInfoVo) throws Exception{
		AjaxResult<List<ElTreeVo>> res = new AjaxResult<>();
		List<ElTreeVo> treeList = new ArrayList<>();

		// 根节点
		ElTreeVo root = new ElTreeVo();
		String kindCode = operatingPackageInfoVo.getKindCode();
		OperatingPackageKindcode packKind = operatingPackageKindcodeService.getOperatingPackageKindcodeById(operatingPackageInfoVo.getHosId(), kindCode);
		root.setId(packKind.getId());
		root.setLabel(packKind.getKindName());
		
		List<ElTreeVo> parentList = new ArrayList<>();
		
		// 查询手术包信息
		List<OperatingPackageInfoVo> packList = operationPackService.queryPackInfo(operatingPackageInfoVo);
		if(packList == null || packList.size() == 0){
			return res;
		}
		for (OperatingPackageInfoVo vo : packList) {
			ElTreeVo tree = new ElTreeVo();
			tree.setId(vo.getId());
			tree.setLabel(vo.getCname());
			parentList.add(tree);
		}
		root.setChildren(parentList);
		treeList.add(root);
		res.setData(treeList);
		return res;
	}

	/**
	 * 查询手术包类型信息
	 * @param operatingPackageInfoVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPackKind")
	@ResponseBody
	public AjaxResult<List<ElTreeVo>> queryPackKind(@RequestBody OperatingPackageKindcode operatingPackageKindcode)
			throws Exception {
		AjaxResult<List<ElTreeVo>> res = new AjaxResult<>();
		List<ElTreeVo> treeList = new ArrayList<>();

		// 根节点
		// 根据医院id查询
		ElTreeVo root = new ElTreeVo();
		if (StringUtils.isEmpty(operatingPackageKindcode.getHosId())) {
			operatingPackageKindcode.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		}
		String hosId = operatingPackageKindcode.getHosId();
		CompanyInfoVo com = companyVoService.getCompanyInfoById(hosId);
		root.setId(com.getId());
		root.setLabel(com.getCname());

		List<ElTreeVo> parentList = new ArrayList<>();

		// 查询手术包类型
		List<OperatingPackageKindcode> kindcodes = operatingPackageKindcodeService
				.getOperatingPackageKindcodeByPid(hosId, null);
		if (kindcodes != null && kindcodes.size() > 0) {
			for (OperatingPackageKindcode kindcode : kindcodes) {
				ElTreeVo ptree = new ElTreeVo();
				ptree.setId(kindcode.getId());
				ptree.setLabel(kindcode.getKindName());
				ElTreeVo p = recursiveTree(hosId, ptree);
				parentList.add(p);
			}
		}

		root.setChildren(parentList);
		treeList.add(root);
		res.setData(treeList);
		return res;
	}

	/**
	 * 组成手术包类型树
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	private ElTreeVo recursiveTree(String hosId, ElTreeVo ptree) throws Exception {
		List<OperatingPackageKindcode> codes = operatingPackageKindcodeService.getOperatingPackageKindcodeByPid(hosId,
				ptree.getId());
//		if (codes == null || codes.size() == 0) {
//			if (ptree.getId() != null) {
//				// 查询手术包信息
//				OperatingPackageInfoVo infoVo = new OperatingPackageInfoVo();
//				infoVo.setHosId(hosId);
//				infoVo.setKindCode(ptree.getId());
//				List<OperatingPackageInfoVo> packList = operationPackService.queryPackInfo(infoVo);
//				List<ElTreeVo> packTrees = new ArrayList<>();
//				for (OperatingPackageInfoVo vo : packList) {
//					ElTreeVo packTree = new ElTreeVo();
//					packTree.setId(vo.getId());
//					packTree.setLabel(vo.getCname());
//					packTrees.add(packTree);
//					ptree.setChildren(packTrees);
//				}
//			}
//		}
		for (OperatingPackageKindcode code : codes) {
			ElTreeVo ctree = new ElTreeVo();
			ctree.setId(code.getId());
			ctree.setLabel(code.getKindName());
			ElTreeVo t = recursiveTree(hosId, ctree);
			ptree.getChildren().add(t);
		}
		return ptree;
	}

	/**
	 * 添加手术包类型
	 * @param OperatingPackageKindcode
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertKind")
	@ResponseBody
	public AjaxResult<OperatingPackageKindcode> insertKind(@RequestBody @Valid  Map<String,Object> params,
			Errors error) throws Exception {
		AjaxResult<OperatingPackageKindcode> res = new AjaxResult<>();
		filterErrors(error);
		String pId = (String) params.get("pId");
		String kindName = (String) params.get("kindName");
		String hosId = (String) params.get("hosId");
		OperatingPackageKindcode code = new OperatingPackageKindcode();
		if(StringUtils.isNotEmpty(pId)){
			code.setPId(pId);
		}
		code.setKindName(kindName);
		code.setHosId(hosId);
		OperatingPackageKindcode vo = operatingPackageKindcodeService.insertKind(code);
		res.setData(vo);
		return res;
	}
	
	
	/**
	 * 添加手术包
	 * @param operatingPackageInfoVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public AjaxResult<OperatingPackageInfoVo> Insert(@RequestBody @Valid OperatingPackageInfoVo operatingPackageInfoVo,
			Errors error) throws Exception {
		AjaxResult<OperatingPackageInfoVo> res = new AjaxResult<>();
		filterErrors(error);
		OperatingPackageInfoVo vo = operationPackService.insertPackInfo(operatingPackageInfoVo);
		res.setData(vo);
		return res;
	}

	/**
	 * 删除手术包
	 * @param operatingPackageInfoVo 同时删除关联产品
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult<OperatingPackageInfoVo> delete(@RequestBody @Valid OperatingPackageInfoVo operatingPackageInfoVo,
			Errors error) throws Exception {
		AjaxResult<OperatingPackageInfoVo> res = new AjaxResult<>();
		filterErrors(error);
		operationPackService.deletePackInfo(operatingPackageInfoVo);
		return res;
	}
	
	/**
	 * 删除手术包类型
	 * @param operatingPackageInfoVo 同时删除关联产品
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteKind")
	@ResponseBody
	public AjaxResult<OperatingPackageKindcode> deleteKind(@RequestBody @Valid OperatingPackageKindcode operatingPackageKindcode,
			Errors error) throws Exception {
		AjaxResult<OperatingPackageKindcode> res = new AjaxResult<>();
		filterErrors(error);
		operatingPackageKindcodeService.delete(operatingPackageKindcode);
		// FIXME 删除手术包信息
		return res;
	}

	/**
	 * 分页查询手术包产品信息
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPackGoodsInfo")
	@ResponseBody
	public AjaxResult<QueryResult<PackGoodsInfoVo>> getPackGoodsInfo(
			@RequestBody QueryInfo<OperatingPackageGoodsInfoVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<PackGoodsInfoVo>> res = new AjaxResult<>();
		QueryResult<PackGoodsInfoVo> list = operationPackService.getPackGoodsInfo(queryInfo);
		res.setData(list);
		return res;
	}

	/**
	 * 移除手术包产品
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteGood")
	@ResponseBody
	public AjaxResult<OperatingPackageGoodsInfo> deleteGood(@RequestBody OperatingPackageGoodsInfo bean)
			throws Exception {
		AjaxResult<OperatingPackageGoodsInfo> res = new AjaxResult<>();
		operationPackService.deleteGood(bean);
		return res;
	}

	/**
	 * 批量删除手术包信息
	 * @param packGoods
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mulDelete")
	@ResponseBody
	public AjaxResult<PackGoodsInfoVo> mulDelete(@RequestBody List<PackGoodsInfoVo> packGoods) throws Exception {
		AjaxResult<PackGoodsInfoVo> res = new AjaxResult<>();
		operationPackService.mulDelete(packGoods);
		return res;
	}

	/**
	 * 批量导入医院产品信息
	 * @param goodsInfos
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/importHosGoods")
	@ResponseBody
	public AjaxResult importHosGoods(@RequestBody OperatingPackageGoodsInfoVo goodsInfos) throws Exception {
		AjaxResult res = new AjaxResult();
		operationPackService.importHosGoods(goodsInfos);
		return res;
	}

}
