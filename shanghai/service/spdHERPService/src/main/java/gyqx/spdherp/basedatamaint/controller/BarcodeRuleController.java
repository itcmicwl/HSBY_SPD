package gyqx.spdherp.basedatamaint.controller;

import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.basedatamaint.service.BarcodeRuleService;
import gyqx.spdherp.basedatamaint.vo.BarcodeRuleVo;
import gyqx.spdherp.stockout.vo.IdNameVo;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-11 下午4:23
 */
@RestController
@RequestMapping({"/baseData/baseDataMaint/BarcodeRule"})
public class BarcodeRuleController extends BaseController {

    @Resource
    private BarcodeRuleService service;

    @GetMapping("/getBarcodeRuleList")
    public AjaxResult getBarcodeRuleList(@RequestParam(value = "barcode", defaultValue = "") String barcode,
                                         @RequestParam(value = "barcodeLen", defaultValue = "0") int barcodeLen,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "30") int pageSize){
        AjaxResult<QueryResult<BarcodeRuleVo>> result = new AjaxResult<>();
        result.setData(service.listBarcodeRule(barcode, barcodeLen, pageNum, pageSize));
        return result;
    }

    @PostMapping("/addBarcodeRule")
    public AjaxResult<String> addBarcodeRule(@Valid @RequestBody BarcodeRuleVo barcodeRuleVo, Errors errors) throws Exception {
        filterErrors(errors);
        AjaxResult<String> result = new AjaxResult<>();
        result.setData(service.saveBarcodeRule(barcodeRuleVo));
        return result;
    }

    @PutMapping("/modBarcodeRule")
    public AjaxResult<Integer> modBarcodeRule(@Valid @RequestBody BarcodeRuleVo barcodeRuleVo, Errors errors) throws Exception {
        filterErrors(errors);
        AjaxResult<Integer> result = new AjaxResult<>();
        result.setData(service.updateBarcodeRule(barcodeRuleVo));
        return result;
    }

    @DeleteMapping("/delBarcodeRule")
    public AjaxResult<String> delBarcodeRule(@RequestParam(value = "id", required = true) String id,
                                             @RequestParam(value = "version", required = true) int version) throws Exception {
        AjaxResult<String> result = new AjaxResult<>();
        result.setData(service.removeBarcodeRule(id, version));
        return result;
    }

    @GetMapping("/getMfrsList")
    public AjaxResult getProvList(@RequestParam("mfrsName") String mfrsName){
        AjaxResult<List<IdNameVo>> result = new AjaxResult<>();
        result.setData(service.listProv(mfrsName));
        return result;
    }
}
