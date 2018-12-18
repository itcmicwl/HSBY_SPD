package gyqx.spdherp.stockout.controller;

import common.exception.ParameterException;
import common.utils.DictionaryUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.stockout.service.PackageOutService;
import gyqx.spdherp.stockout.service.RequestOutService;
import gyqx.spdherp.stockout.vo.*;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static gyqx.spdherp.basedatamaint.controller.BarcodeParseController.PACKAGE_ID_LENGTH;
import static gyqx.spdherp.basedatamaint.controller.BarcodeParseController.PACKAGE_PREFIX;
import static gyqx.spdherp.stockout.Constance.OUTSTOCK_KIND_PCKAGE;

@RestController
@RequestMapping({"/stockMgr/out/packageOut"})
public class PackageOutController extends BaseController {
    @Resource
    private RequestOutService requestOutService;

    @Resource
    private PackageOutService service;

    @GetMapping("/getStockList")
    public AjaxResult<List<IdNameVo>> getStockList() {
        AjaxResult<List<IdNameVo>> result = new AjaxResult<>();
        result.setData(requestOutService.listOwnStock());
        return result;
    }


    @GetMapping("/getDict")
    public AjaxResult<List<DictionaryUtils.DictionaryValue>> getDict(@RequestParam("dict") String dict) {
        AjaxResult<List<DictionaryUtils.DictionaryValue>> result = new AjaxResult<>();
        result.setData(requestOutService.listDictValue(dict));
        return result;
    }

    @PostMapping("/saveOutStockBill")
    public AjaxResult<String> saveOutStockBill(@RequestBody PackageWhole packageWhole, Errors errors) throws Exception {
        filterErrors(errors);
        if (!packageWhole.getOutStockKind().equals(OUTSTOCK_KIND_PCKAGE.toString())) {
            throw new ParameterException("出库类型错误。");
        }
        AjaxResult<String> result = new AjaxResult<>();
        result.setData(service.addPkg(packageWhole));
        return result;
    }

    @GetMapping("/parseBarcode")
    public AjaxResult parseBarcode(@RequestParam("stockId") String stockId,
                                   @RequestParam("stocKind") Integer stocKind,
                                   @RequestParam("masterBarcode") String masterBarcode,
                                   @RequestParam(value = "slaverBarcode", required = false) String slaverBarcode) throws Exception {
        AjaxResult<BarcodeResultSetVo> result = new AjaxResult<>();
        return result;
    }

    @PostMapping("/parseBarcodeCombine")
    public AjaxResult parseBarcodeCombine(@Valid @RequestBody PackageWhole packageWhole) throws Exception {
        AjaxResult<Object> result = new AjaxResult<>();
        if (packageWhole.getPackageId().length() == PACKAGE_ID_LENGTH &&
                packageWhole.getPackageId().toLowerCase().startsWith(PACKAGE_PREFIX)) {
            PackageWhole packageOutVo = service.parseHosPackageInfo(packageWhole);
            result.setData(packageOutVo);
        }
        return result;
    }
}
