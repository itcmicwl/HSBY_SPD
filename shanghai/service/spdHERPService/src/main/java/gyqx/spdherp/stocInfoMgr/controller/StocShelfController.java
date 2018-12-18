package gyqx.spdherp.stocInfoMgr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.SysConfigUtil;
import common.utils.UserOnlineStateUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.myInfos.service.ICompanyVoService;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdherp.stocInfoMgr.service.StocInfoService;
import gyqx.spdherp.stocInfoMgr.service.StocShelfService;
import gyqx.spdherp.stocInfoMgr.vo.ElTreeVo;
import gyqx.spdherp.stocInfoMgr.vo.StocInfoVo;
import gyqx.spdherp.stocInfoMgr.vo.StocLevel;
import gyqx.spdherp.stocInfoMgr.vo.StocShelfVo;

@Controller
@RequestMapping("/baseData/stocInfoMgr/stocShelf")
public class StocShelfController extends BaseController {

	@Resource
	private StocInfoService stocInfoService;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	@Resource
	private SysConfigUtil sysConfigUtil;
	@Resource
	private StocShelfService stocShelfService;
	@Resource
	private ICompanyVoService companyVoService;

	/**
	 * 库房树节点查询
	 * @param stocInfoVo 库房信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectStocInfo")
	@ResponseBody
	public AjaxResult<List<ElTreeVo>> selectStocInfo(@RequestBody StocInfoVo stocInfoVo) throws Exception {
		AjaxResult<List<ElTreeVo>> res = new AjaxResult<>();

		List<ElTreeVo> treeList = new ArrayList<>();

		// 根节点
		ElTreeVo root = new ElTreeVo();
		CompanyInfoVo com = companyVoService.getCompanyInfoById(userOnlineStateUtils.getCurrent().getCorpId());
		root.setId("-1");
		root.setLabel(com.getCname());
		root.setNodeType(com.getKind());

		// 父节点
		int level = Integer.parseInt(sysConfigUtil.getValue("stoc.level"));
		List<ElTreeVo> parentList = new ArrayList<>();

		for (StocLevel stocLevel : StocLevel.values()) {
			if (stocLevel.getIndex() <= level) {
				ElTreeVo parent = new ElTreeVo();
				parent.setId(String.valueOf(stocLevel.getIndex()));
				parent.setLabel(stocLevel.getName());
				ElTreeVo stocTree = stocTree(parent);
				parentList.add(stocTree);
			}
		}
		root.setChildren(parentList);
		treeList.add(root);
		res.setData(treeList);
		return res;
	}

	/**
	 * 分页查询区位列表
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getStocArea")
	@ResponseBody
	public AjaxResult<QueryResult<StocShelfVo>> getStocArea(@RequestBody QueryInfo<StocShelfVo> queryInfo,
			Errors error) throws Exception {
		AjaxResult<QueryResult<StocShelfVo>> result = new AjaxResult<QueryResult<StocShelfVo>>();
		queryInfo.getQueryObject().setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		QueryResult<StocShelfVo> data = stocShelfService.getStocArea(queryInfo);
		result.setData(data);
		return result;
	}
	
	
	/**
	 * 分页查询货位列表
	 * @param queryInfo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getStocShelf")
	@ResponseBody
	public AjaxResult<QueryResult<StocShelfVo>> getStocShelf(@RequestBody QueryInfo<StocShelfVo> queryInfo,
			Errors error) throws Exception {
		AjaxResult<QueryResult<StocShelfVo>> result = new AjaxResult<QueryResult<StocShelfVo>>();
		queryInfo.getQueryObject().setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		QueryResult<StocShelfVo> data = stocShelfService.getStocShelf(queryInfo);
		result.setData(data);
		return result;
	}
	
	/**
	 * 根据库房id查询区位信息
	 * @param stocShelfVo
	 * @return
	 */
	@RequestMapping("/getAreaList")
	@ResponseBody
	public AjaxResult<List<StocShelfVo>> getAreaList(@RequestBody StocShelfVo stocShelfVo){
		AjaxResult<List<StocShelfVo>> res = new AjaxResult<>();
		List<StocShelfVo> voList = stocShelfService.getAreaList(stocShelfVo);
		res.setData(voList);
		return res;
	}
	

	/**
	 * 编辑
	 * @param stocShelfVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult<StocShelfVo> update(@RequestBody @Valid StocShelfVo stocShelfVo, Errors error) throws Exception {
		AjaxResult<StocShelfVo> res = new AjaxResult<>();
		filterErrors(error);
		StocShelfVo vo = stocShelfService.updateStocShelf(stocShelfVo);
		res.setData(vo);
		return res;
	}

	/**
	 * 新增
	 * @param stocShelfVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public AjaxResult<StocShelfVo> insert(@RequestBody @Valid StocShelfVo stocShelfVo, Errors error) throws Exception {
		AjaxResult<StocShelfVo> res = new AjaxResult<>();
		filterErrors(error);
		StocShelfVo vo = stocShelfService.insertStocShelf(stocShelfVo);
		res.setData(vo);
		return res;
	}

	/**
	 * 删除记录
	 * @param stocShelfVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult<StocShelfVo> delete(@RequestBody @Valid StocShelfVo stocShelfVo, Errors error) throws Exception {
		AjaxResult<StocShelfVo> res = new AjaxResult<>();
		filterErrors(error);
		stocShelfService.deleteStocShelf(stocShelfVo);
		return res;
	}

	/**
	 * 设置子节点
	 * @param parentNode
	 * @return
	 * @throws Exception
	 */
	public ElTreeVo stocTree(ElTreeVo parentNode) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("stocLevel", parentNode.getLabel());
		List<StocInfoVo> stocInfoList = stocInfoService.queryStocInfo(param);
		for (StocInfoVo stocInfoVo : stocInfoList) {
			ElTreeVo vo = new ElTreeVo();
			vo.setId(stocInfoVo.getId());
			vo.setPid(stocInfoVo.getStocLevel());
			vo.setLabel(stocInfoVo.getStocName());

			ElTreeVo stocTree = stocTree(vo);
			parentNode.getChildren().add(stocTree);
		}
		return parentNode;
	}

}
