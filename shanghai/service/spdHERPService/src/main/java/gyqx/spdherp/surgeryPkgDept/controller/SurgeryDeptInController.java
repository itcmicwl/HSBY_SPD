package gyqx.spdherp.surgeryPkgDept.controller;

import common.web.AjaxResult;
import gyqx.spdherp.po.InStock;
import gyqx.spdherp.surgeryPkgDept.service.ISurgeryInDeptService;
import gyqx.spdherp.surgeryPkgDept.vo.InDeptSurInfoVo;
import gyqx.spdherp.surgeryPkgDept.vo.InStockVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping({"/surgeryPkg/in"})
@RestController
public class SurgeryDeptInController {
    @Resource
    private ISurgeryInDeptService service;
    @PostMapping("/getSurveryInfo")
    public AjaxResult getSurveryInfo(@RequestBody InDeptSurInfoVo inDeptSurInfoVo) throws Exception {
        AjaxResult<InDeptSurInfoVo> result = new AjaxResult<>();
        InDeptSurInfoVo inSurInfo = service.findAllSurInfo(inDeptSurInfoVo);
        if(inSurInfo==null){
            result.setCode(-1);
        }
        result.setData(inSurInfo);
        return result;
    }
    @PostMapping("/add")
    public AjaxResult add(@RequestBody InStockVo inStockVo) throws Exception {
        AjaxResult<InStock> result = new AjaxResult<>();
        result.setData(service.add(inStockVo));
        return result;
    }
}
