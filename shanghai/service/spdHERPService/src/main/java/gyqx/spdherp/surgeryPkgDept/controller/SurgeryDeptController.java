package gyqx.spdherp.surgeryPkgDept.controller;

import common.web.AjaxResult;
import gyqx.spdherp.surgeryPkgDept.service.ISurgeryDeptService;
import gyqx.spdherp.surgeryPkgDept.vo.SurgeryPkgVo;
import gyqx.spdherp.surgeryPkgDept.vo.SurgeryVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@RequestMapping({"/surgeryPkg/out"})
@RestController
public class SurgeryDeptController {
    @Resource
    private ISurgeryDeptService service;
    @PostMapping("/getSurveryInfo")
    public AjaxResult getSurveryInfoByCode(@RequestBody SurgeryPkgVo surgeryPkgVo) throws Exception {
        AjaxResult<SurgeryPkgVo> result = new AjaxResult<>();
        SurgeryPkgVo surgeryInfoByCode = service.getSurgeryInfoByCode(surgeryPkgVo);
        if(surgeryInfoByCode.getSurCode()!=null){
            result.setData(service.getSurgeryInfoByCode(surgeryPkgVo));
        }else {
            result.setCode(-1);
        }
        return result;
    }
    @PostMapping("/getSurveryInfoPrint")
    public AjaxResult getSurveryInfoPrint(@RequestBody SurgeryPkgVo surgeryPkgVo) throws Exception {
        AjaxResult<List<SurgeryPkgVo>> result = new AjaxResult<>();
        result.setData(service.getPrintSurInfo(surgeryPkgVo));
        return result;
    }
    @PostMapping("/convertOutBill")
    public AjaxResult convertOutBill(@RequestBody SurgeryVo surgeryVo) throws Exception {
        AjaxResult<SurgeryVo> result = new AjaxResult<>();
        result.setData(service.convertOutBill(surgeryVo));
        return result;
    }
    @PostMapping("/submitOutBill")
    public AjaxResult submitOutBill(@RequestBody SurgeryVo surgeryVo) throws Exception {
        AjaxResult<String> result = new AjaxResult<>();
        result.setData(service.submitOutBill(surgeryVo));
        return result;
    }
}
