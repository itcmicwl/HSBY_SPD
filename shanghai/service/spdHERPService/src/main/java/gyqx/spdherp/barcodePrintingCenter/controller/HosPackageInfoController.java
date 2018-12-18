package gyqx.spdherp.barcodePrintingCenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.validation.Valid;

import common.db.SimpleDao;
import gyqx.spdherp.stockout.service.OutBill4InService;
import gyqx.spdherp.stockout.vo.vo4In.OutBill4InVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.barcodePrintingCenter.service.IHosPackageInfoService;
import gyqx.spdherp.barcodePrintingCenter.vo.HosPackageInfoVo;
import gyqx.spdherp.po.HosPackageInfo;
import gyqx.spdherp.stockout.vo.vo4In.OutSub4InVo;

@Controller
@RequestMapping(value = "barcodePrintingCenter/hosPackageInfo")
public class HosPackageInfoController extends BaseController {
	@Resource
	private IHosPackageInfoService hosPackageInfoService;
	@Resource
	private OutBill4InService o4iService;
	@Resource
	private SimpleDao simpleDao;
	@RequestMapping(value = "addHosPackageInfos")
	@ResponseBody
	public AjaxResult<List<HosPackageInfoVo>> addHosPackageInfos(@RequestBody Map<String,List<OutSub4InVo>> map) throws Exception {
		AjaxResult<List<HosPackageInfoVo>> result = new AjaxResult<List<HosPackageInfoVo>>();
		List<HosPackageInfoVo> lists = hosPackageInfoService.addHosPackageInfos(map);
		result.setData(lists);
		return result;
	}

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid HosPackageInfo hosPackageInfo, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosPackageInfo ret = hosPackageInfoService.add(hosPackageInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "get/{id}")
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosPackageInfo ret = hosPackageInfoService.get(id);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid HosPackageInfo hosPackageInfo, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosPackageInfo ret = hosPackageInfoService.update(hosPackageInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid HosPackageInfo hosPackageInfo, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HosPackageInfo> ret = hosPackageInfoService.list(hosPackageInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String, String>> queryInfo, Errors error)
			throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HosPackageInfo> ret = hosPackageInfoService.query(queryInfo.getPredicate(), queryInfo.getOrderBy(),
				queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<HosPackageInfo> queryInfo, Errors error)
			throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<HosPackageInfo> ret = hosPackageInfoService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "listDsPacksByOutBillId")
	@ResponseBody
	public AjaxResult listDsPacksByOutBillId(@RequestBody @Valid QueryInfo<HosPackageInfo> queryInfo, Errors error)
			throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<HosPackageInfo> ret = hosPackageInfoService.listDsPacksByOutBillId(queryInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "createDsCode")
	@ResponseBody
	public AjaxResult createDsCode(@RequestParam("id") String id, @RequestParam("isPacket") String isPacket)
			throws Exception {
		AjaxResult result = new AjaxResult();
		//先查询是否打过包
		if(simpleDao.checkExists(HosPackageInfo.class,"billId",id)>0){
			//返回包信息
		}
		else {
			//没打过
			OutBill4InVo outBillVo = o4iService.getOutBillDetails(id, isPacket);
			//将 outBillVo.getLstOutsubVo() 转map
			Map<String, OutSub4InVo> subLstMap = outBillVo.getLstOutsubVo().stream().collect(Collectors.toMap(OutSub4InVo::getGoodsId, a -> a, (k1, k2) -> k1));
			Map<String, List<OutSub4InVo>> idSubsMap = new HashMap<String, List<OutSub4InVo>>();
			for (Map.Entry<String, OutSub4InVo> entry : subLstMap.entrySet()) {
				List<OutSub4InVo> subLst = new ArrayList<>();
				for (OutSub4InVo sub : outBillVo.getLstOutsubVo()) {
					if (entry.getKey().equals(sub.getGoodsId())) {
						subLst.add(sub);
					}
				}
				idSubsMap.put(entry.getKey(), subLst);
			}
			hosPackageInfoService.addHosPackageInfos(idSubsMap);
		}
		return result;
	}
}
