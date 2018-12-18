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
public class ReturnSterilizationController extends BaseController
{
    @Resource
    private ISterilizationService sterilizationService;

    /**
     * 获取需要退还消毒的手术包列表
     * @param queryInfo
     * @param error
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getSecSterilizationPkgByPage")
    @ResponseBody
    public AjaxResult getSecSterilizationPkgByPage(@RequestBody @Valid QueryInfo<SurgeryPkgVo> queryInfo ,Errors error  )  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        filterErrors(error);
        QueryResult<SurgeryPkgVo> ret = sterilizationService.listSecPkgByPage(queryInfo);
        result.setData(ret);
        return result;
    }

    /**
     * 获取退还消毒后过期的的手术包列表
     * @param queryInfo
     * @param error
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getSecSterilizationPkgExpireByPage")
    @ResponseBody
    public AjaxResult getSecSterilizationPkgExpireByPage(@RequestBody @Valid QueryInfo<SurgeryPkgVo> queryInfo ,Errors error  )  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        filterErrors(error);
        QueryResult<SurgeryPkgVo> ret = sterilizationService.listSecExpire(queryInfo);
        result.setData(ret);
        return result;
    }

    @RequestMapping(value = "getSecSterilizationListByPage")
    @ResponseBody
    public AjaxResult getSecSterilizationListByPage(@RequestBody @Valid QueryInfo<SurgeryPkgVo> queryInfo ,Errors error  )  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        filterErrors(error);
        QueryResult<SurgeryPkgListVo> ret = sterilizationService.getNotSecPkgListBySurCode(queryInfo);
        result.setData(ret);
        return result;
    }

    /**
     * 退还消毒
     * @param surgeryPkgVo
     * @param error
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateSecSterilization")
    @ResponseBody
    public AjaxResult updateSecSterilization(@RequestBody @Valid SurgeryPkgVo surgeryPkgVo , Errors error  )  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        filterErrors(error);
        int ret = sterilizationService.updateSecSterialization(surgeryPkgVo);
        result.setData(ret);
        return result;
    }

}
