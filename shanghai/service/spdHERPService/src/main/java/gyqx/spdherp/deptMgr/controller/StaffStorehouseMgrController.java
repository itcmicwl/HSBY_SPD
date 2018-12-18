package gyqx.spdherp.deptMgr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.UserOnlineStateUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.platform.po.SysOrg;
import gyqx.spdherp.deptMgr.service.IStaffStorehouseMgrService;
import gyqx.spdherp.deptMgr.vo.HosOperatorStockVo;
import gyqx.spdherp.deptMgr.vo.StocInfoVo;
import gyqx.spdherp.deptMgr.vo.UserStockSubmit;
import gyqx.spdherp.deptMgr.vo.UserStockVo;
import gyqx.spdherp.po.StocInfo;
import gyqx.spdherp.stocInfoMgr.vo.QueryVo;

@Controller
@RequestMapping(value = "deptMgr/stocInfo")
public class StaffStorehouseMgrController extends BaseController {
	@Resource
	private IStaffStorehouseMgrService service;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	/**
	 * 查询医院库房信息
	 * @param corpId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="listStocInfo")
	@ResponseBody
	public AjaxResult<List<StocInfoVo>> listStocInfo(@RequestBody String corpId) throws Exception{
		AjaxResult<List<StocInfoVo>> result = new AjaxResult<List<StocInfoVo>>();
		List<StocInfoVo> stocInfoList = service.listStocInfo(corpId.replace("=", ""));
		result.setData(stocInfoList);
		return result;
	}
	
	/**
	 * 查询医院库房信息：带分页
	 * @param corpId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="listStocInfo2")
	@ResponseBody
	public AjaxResult<QueryResult<StocInfoVo>> listStocInfo2(@RequestBody QueryInfo<QueryVo> queryInfo) throws Exception{
		AjaxResult<QueryResult<StocInfoVo>> result = new AjaxResult<QueryResult<StocInfoVo>>();
		QueryResult<StocInfoVo> stocInfoList = service.listStocInfo(queryInfo);
		result.setData(stocInfoList);
		return result;
	}
	
	
	/**
	 * 查询科室库房信息
	 * @param stocInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="listDeptStocInfo")
	@ResponseBody
	public AjaxResult<List<StocInfo>> listDeptStocInfo(@RequestBody StocInfo stocInfo) throws Exception{
		AjaxResult<List<StocInfo>> result = new AjaxResult<List<StocInfo>>();
		List<StocInfo> stocInfoList = service.listDeptStocInfo(stocInfo.getHosId(),stocInfo.getDeptId());
		result.setData(stocInfoList);
		return result;
	}
	
	/**
	 * 查询职工库房信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="queryStaffStock")
	@ResponseBody
	public AjaxResult<List<HosOperatorStockVo>> queryStaffStock(@RequestBody String userId) throws Exception{
		AjaxResult<List<HosOperatorStockVo>> result = new AjaxResult<List<HosOperatorStockVo>>();
		List<HosOperatorStockVo> stocInfoList = service.queryStaffStock(userId.replace("=", ""));
		result.setData(stocInfoList);
		return result;
	}
	
	/**
	 * 修改职工库房信息
	 * @param userStockSubmit
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="userStocSubmit")
	@ResponseBody
	public AjaxResult userStocSubmit(@RequestBody UserStockSubmit userStockSubmit) throws Exception{
		AjaxResult result = new AjaxResult();
		service.userStocSubmit(userStockSubmit);
		return result;
	}
   @GetMapping(value = "userStocks/{userId}/{hosId}/{deptId}")
   @ResponseBody
   public AjaxResult<List<UserStockVo>> userStocks(@PathVariable String userId,@PathVariable String hosId,@PathVariable String deptId) throws Exception {
	   AjaxResult<List<UserStockVo>> res = new AjaxResult<>();
	   List<UserStockVo> stocks = service.getStocksByHosUser(userId,hosId,deptId);
	   res.setData(stocks);
	   return res;
   }

	/**
	 * 入库时 通过用户获取可入库的库房
	 * @author ganwei
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getStocksByUser/{flag}")
	@ResponseBody
	public AjaxResult<Map<String, Object>> getStocksByUser(@PathVariable int flag) throws Exception {
		AjaxResult<Map<String, Object>> res = new AjaxResult<>();	
		Map<String,Object> request=new HashMap<String,Object>();
		//根据登录用户获取医院ID,科室ID,用户ID并封装参数
		request.put("hosId",userOnlineStateUtils.getCurrent().getCorpId());
		//判断请求参数 如果为1 则表示只需要当前科室库房
		if(flag == 1)
		request.put("deptId",userOnlineStateUtils.getCurrent().getOrgId());
		request.put("userId",userOnlineStateUtils.getCurrent().getUserId());    
		//查询
		List<UserStockVo> stocks = service.getStocksByUser(request);
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("comanyInfo", companyInfo);
		map.put("stocInfoList", stocks);
		res.setData(map);
		return res;
	}
	
	
	
	/**
	 * 入库时，通过所选择的库房获取该库房的所属科室
	 * @author ganwei
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getOrgsByStock/{stockId}")
	@ResponseBody

	public AjaxResult getOrgsByStock(@PathVariable("stockId") String stockId,HttpServletRequest  request,HttpServletResponse response) throws Exception{
		AjaxResult result = new AjaxResult();	
	
		
			List<SysOrg> data = service.getOrgsByStock(stockId);
			if(data.size()>0){
				result.setData(data.get(0));
			}
		
		return result;
	}
	
}
