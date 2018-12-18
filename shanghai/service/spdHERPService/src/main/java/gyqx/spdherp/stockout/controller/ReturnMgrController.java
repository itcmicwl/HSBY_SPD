package gyqx.spdherp.stockout.controller;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.forRiva.service.ICallForRivaService;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.stockout.service.ReturnMgrService;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
import gyqx.spdherp.stockout.vo.SysOrgVo;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static gyqx.spdherp.stockout.Constance.OUTSTOCK_KIND_REQUEST;
import static gyqx.spdherp.stockout.Constance.OUTSTOCK_KIND_RETURN;

@RestController
@RequestMapping({"/stockMgr/out/returnMgr"})
public class ReturnMgrController extends BaseController {
    @Resource
    private ReturnMgrService service;

    @GetMapping("/getOrgs")
    public AjaxResult<List<SysOrgVo>> getOrgs() {
        AjaxResult<List<SysOrgVo>> result = new AjaxResult<>();
        result.setData(service.listSysOrg());
        return result;
    }

    @GetMapping("/getBillList") // <List<OutStockBillVo>>
    public AjaxResult getBillList(@RequestParam("outDeptId") String outDeptId,
                                  @RequestParam(value = "beginDate", required = false) Date beginDate,
                                  @RequestParam(value = "endDate", required = false) Date endDate,
                                  @RequestParam("status") String status) {
        AjaxResult<List<OutStockBillVo>> result = new AjaxResult<>();
        result.setData(service.listBill(outDeptId, beginDate, endDate, status));
        return result;
    }

    @GetMapping("/getBillDetail")
    public AjaxResult<OutStockBillVo> getBillDetail(@RequestParam("id") String id) {
        AjaxResult<OutStockBillVo> result = new AjaxResult<>();
        result.setData(service.getBillDetail(id));
        return result;
    }

    @PatchMapping("/updateOutStockBill")
    public AjaxResult<Integer> updateOutStockBill(@Valid @RequestBody OutStock outStock, Errors errors) throws Exception {
        filterErrors(errors);
        AjaxResult<Integer> result = new AjaxResult<>();
        result.setData(service.updateOutStock(outStock));
        service.callFiva(outStock);
        return result;
    }
}
