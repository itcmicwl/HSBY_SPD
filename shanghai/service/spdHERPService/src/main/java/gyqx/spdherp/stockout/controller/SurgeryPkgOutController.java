package gyqx.spdherp.stockout.controller;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.stockout.service.SurgeryPkgOutService;
import gyqx.spdherp.stockout.vo.SickerUserVo;
import gyqx.spdherp.stockout.vo.SurgeryPkg4UseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 手术包领用消耗出库
 */
@RestController
@RequestMapping({"/stockMgr/out/surgeryPkgOut"})
public class SurgeryPkgOutController extends BaseController {

    @Autowired
    private SurgeryPkgOutService surgeryPkgOutService;

    @RequestMapping(value = "querySickerSurPkg")
    @ResponseBody
    public AjaxResult<List<SurgeryPkg4UseVo>> querySickerSurPkg(@RequestBody SurgeryPkg4UseVo surgeryPkg4UseVo){
        AjaxResult<List<SurgeryPkg4UseVo>> result = new AjaxResult<>();
        List<SurgeryPkg4UseVo> list = surgeryPkgOutService.querySickerSurPkg(surgeryPkg4UseVo);
        result.setData(list);
        return  result;
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public AjaxResult add(@RequestBody @Valid SickerUserVo sickerUseVo , Errors error  )  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        int ret = surgeryPkgOutService.add(sickerUseVo);
        if(ret>0){
            int code = 0;
            result.setCode(code);
        }
        result.setData(ret);
        return result;
    }
}
