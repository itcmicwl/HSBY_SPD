package gyqx.spdherp.stockout.controller;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.stockout.service.SurReturnOutService;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 手术包退还出库
 */
@RestController
@RequestMapping({"/stockMgr/out/surReturnOut"})
public class SurReturnOutController extends BaseController {
    @Resource
    private SurReturnOutService surReturnOutService;


    @RequestMapping(value = "saveOutStockBill")
    @ResponseBody
    public AjaxResult saveOutStockBill(@RequestBody @Valid OutStockBillVo outStockBillVo , Errors error  )  throws  Exception
    {
        AjaxResult<String> result = new AjaxResult<>();
        result.setData(surReturnOutService.saveSurReturnBill(outStockBillVo));
        return result;
    }
}
