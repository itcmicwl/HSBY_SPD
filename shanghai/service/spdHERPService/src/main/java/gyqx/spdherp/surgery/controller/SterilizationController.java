package gyqx.spdherp.surgery.controller;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.surgery.service.ISterilizationService;
import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "surgery/sterilization")
public class SterilizationController extends BaseController
{
    @Resource
    private ISterilizationService sterilizationService;

    /**
     * 获取需要消毒的手术包列表
     * @param queryInfo
     * @param error
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getFirstSterilizationPkgByPage")
    @ResponseBody
    public AjaxResult getFirstSterilizationPkgByPage(@RequestBody QueryInfo<SurgeryPkgVo> queryInfo ,Errors error  )  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        filterErrors(error);
        QueryResult<SurgeryPkgVo> ret = sterilizationService.listFirstPkgByPage(queryInfo);
        result.setData(ret);
        return result;
    }

    /**
     * 获取已消毒的手术包列表
     * @param queryInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "listFirstExpireByPage")
    @ResponseBody
    public AjaxResult listFirstExpireByPage(@RequestBody QueryInfo<SurgeryPkgVo> queryInfo)  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        QueryResult<SurgeryPkgVo> ret = sterilizationService.listFirstExpireByPage(queryInfo);
        result.setData(ret);
        return result;
    }

    /**
     * 根据手术包码，获取未消毒的手术包商品
     * @param queryInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getFirstSterilizationListByPage")
    @ResponseBody
    public AjaxResult getFirstSterilizationListByPage(@RequestBody QueryInfo<SurgeryPkgVo> queryInfo)  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        QueryResult<SurgeryPkgListVo> ret = sterilizationService.getNotFirstPkgListBySurCode(queryInfo);
        result.setData(ret);
        return result;
    }

    /**
     * 手术包消毒
     * @param surgeryPkgVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateFirstSterilization")
    @ResponseBody
    public AjaxResult updateFirstSterilization(@RequestBody SurgeryPkgVo surgeryPkgVo )  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        int ret = sterilizationService.updateFirstSterialization(surgeryPkgVo);
        result.setData(ret);
        return result;
    }
}
