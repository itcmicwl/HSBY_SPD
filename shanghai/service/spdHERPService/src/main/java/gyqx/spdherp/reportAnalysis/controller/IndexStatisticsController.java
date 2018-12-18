package gyqx.spdherp.reportAnalysis.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.utils.SysConfigUtil;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.provManager.vo.OutdateCertsVo;
import gyqx.spdherp.reportAnalysis.service.IIndexStatisticsService;
import gyqx.spdherp.reportAnalysis.vo.CertificateStacsVo;

/***
 * 首页统计相关数据控制器
 * @author suCity
 *
 */
@Controller
@RequestMapping(value = "reportAnalysis/IndexStatisticsController")
public class IndexStatisticsController extends BaseController {
	
	@Resource
	private IIndexStatisticsService indexStatisticsService;
	@Resource
	private SysConfigUtil sysConfigUtil;
	
	/**
	 * 医院产品出库信息查询
	 */
	/*@RequestMapping(value = "getHosOutStockGoodsQuery")
	@ResponseBody
	public AjaxResult<QueryResult<HosOutStockGoodsQueryVo>> getHosOutStockGoodsQuery(@RequestBody QueryInfo<HosOutStockGoodsQueryVo> queryInfo)
			throws Exception {
		AjaxResult<QueryResult<HosOutStockGoodsQueryVo>> result = new AjaxResult<QueryResult<HosOutStockGoodsQueryVo>>();
		QueryResult<HosOutStockGoodsQueryVo> lstSysProject = reportStatisticsService.getHosOutStockGoodsQuery(queryInfo);
		result.setData(lstSysProject);
		return result;
	}*/
	/**
	 * 查询医院供应商证各证照数量分布
	 * @return
	 */
	@RequestMapping(value = "queryCertificateStacs")
	@ResponseBody
	public AjaxResult<List<CertificateStacsVo>> queryCertificateStacs(@RequestBody QueryInfo<CertificateStacsVo> queryInfo){
		 AjaxResult<List<CertificateStacsVo>> result = new AjaxResult<>();
	     result.setData(indexStatisticsService.queryCertificateStacs(queryInfo.getQueryObject().getHosId()));
	     return result;
	}
	
	/**
	 * 查询医院供应商证各证照过期情况
	 * @param hosId 医院id
	 * @return
	 */
	@RequestMapping(value = "queryProvOutdateCerts")
	@ResponseBody
	public AjaxResult<List<OutdateCertsVo>> queryProvOutdateCerts(@RequestBody QueryInfo<OutdateCertsVo> queryInfo){
		AjaxResult<List<OutdateCertsVo>> result = new AjaxResult<>();
	     result.setData(indexStatisticsService.queryProvOutdateCerts(queryInfo.getQueryObject().getHosId()));
	     return result;
	}
	/**
	 * 获取设备科orgId
	 * @return
	 */
	@RequestMapping(value = "getEquipDepartmentOrgId",method=RequestMethod.GET)
	@ResponseBody
	public String getEquipDepartmentOrgId(){
		return this.sysConfigUtil.getValue("equipDepartmentOrgId");
	}
}
