package gyqx.spdherp.stockout.controller;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.sickerMgr.vo.SickerInHospitalInfoVo;
import gyqx.spdherp.stockout.service.OutBillListService;
import gyqx.spdherp.stockout.vo.EnameValVo;
import gyqx.spdherp.stockout.vo.OutBillListVo;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping({"/stockMgr/out/listBill"})
@RestController
public class OutBillListController extends BaseController {
    @Resource
    private OutBillListService service;

    @GetMapping("/getOutStatus")
    @ResponseBody
    public AjaxResult<List<EnameValVo>> getOutStatus() throws Exception {
        AjaxResult<List<EnameValVo>> result = new AjaxResult<>();
        result.setData(service.getOutStatus());
        return result;
    }

    @PostMapping(value = "/listByPage")
    @ResponseBody
    public AjaxResult listByPage(@RequestBody QueryInfo<OutBillListVo> queryInfo , Errors error  )  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        filterErrors(error);
        Date startDate = queryInfo.getQueryObject().getStartDate();
        Date endDate = queryInfo.getQueryObject().getEndDate();
        if(startDate != null){
            String startDateStr = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            queryInfo.getQueryObject().setStartDateStr(startDateStr);
        }
        if(endDate != null){
            String endDateStr = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            queryInfo.getQueryObject().setEndDateStr(endDateStr);
        }
        QueryResult<OutBillListVo> ret = service.listByPage(queryInfo);
        result.setData(ret);
        return result;
    }

    @PostMapping("/getByBillId")
    @ResponseBody
    public AjaxResult getByBillId(@RequestBody OutBillListVo outBillListVo,Errors error) throws Exception {
        AjaxResult result = new AjaxResult();
        filterErrors(error);
        result.setData(service.getByBillId(outBillListVo));
        return result;
    }
}
