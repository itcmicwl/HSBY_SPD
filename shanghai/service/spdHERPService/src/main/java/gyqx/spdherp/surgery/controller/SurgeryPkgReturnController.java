package gyqx.spdherp.surgery.controller;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.surgery.service.ISurgeryPkgReturnService;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "surgery/return")
public class SurgeryPkgReturnController extends BaseController
{
    @Resource
    private ISurgeryPkgReturnService surgeryPkgReturnService;

    /**
     * 获取可退还手术包列表
     * @param queryInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "querySurPkgLstByPage")
    @ResponseBody
    public AjaxResult querySurPkgLstByPage(@RequestBody QueryInfo<SurgeryPkgVo> queryInfo )  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        QueryResult<SurgeryPkgVo> ret = surgeryPkgReturnService.listPage(queryInfo);
        result.setData(ret);
        return result;
    }




}
