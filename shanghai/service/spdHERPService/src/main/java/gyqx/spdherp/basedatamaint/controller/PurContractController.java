package gyqx.spdherp.basedatamaint.controller;

import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.basedatamaint.service.PurContractService;
import gyqx.spdherp.basedatamaint.vo.PurContractVo;
import gyqx.spdherp.po.PurContract;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-16 下午3:24
 */
@RestController
@RequestMapping({"/baseData/baseDataMaint/contManage"})
public class PurContractController extends BaseController {
    @Resource
    private PurContractService service;

    @GetMapping("/getContractList")
    public AjaxResult getContractList(@RequestParam("searchText") String searchText,
                                      @RequestParam("signDateBegin") Date signDateBegin,
                                      @RequestParam("signDateEnd") Date signDateEnd,
                                      @RequestParam("pageNum") int pageNum,
                                      @RequestParam("pageSize") int pageSize) {
        AjaxResult<QueryResult<PurContractVo>> result = new AjaxResult<>();
        result.setData(service.listPurContract(searchText, signDateBegin,signDateEnd, pageNum, pageSize));
        return result;
    }

    @GetMapping("/getProvList")
    public AjaxResult getProvList(@RequestParam("provName") String provName){
        AjaxResult<List<PurContractVo>> result = new AjaxResult<>();
        result.setData(service.listProv(provName));
        return result;
    }

    @PostMapping("/addContract")
    public AjaxResult addContract(@Valid @RequestBody PurContract purContract, Errors errors) throws Exception {
        filterErrors(errors);
        AjaxResult<String> result = new AjaxResult<>();
        result.setData(service.savePurContract(purContract));
        return result;
    }

    @PutMapping("/modContract")
    public AjaxResult modContract(@Valid @RequestBody PurContract purContract, Errors errors) throws Exception {
        filterErrors(errors);
        AjaxResult<Integer> result = new AjaxResult<>();
        result.setData(service.updatePurContract(purContract));
        return result;
    }

    @DeleteMapping("/delContract")
    public AjaxResult delContract(@RequestParam("id") String id, @RequestParam("version") int version) throws Exception {
        AjaxResult<String> result = new AjaxResult<>();
        result.setData(service.deletePurContract(id, version));
        return result;
    }
}
