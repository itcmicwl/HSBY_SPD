package gyqx.spdherp.stockMgr.controller;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.stockMgr.service.IInStockListService;
import gyqx.spdherp.stockMgr.service.IInStockService;
import gyqx.spdherp.stockMgr.vo.InSUniquecodeQueryVo;
import gyqx.spdherp.stockMgr.vo.InStockListVo;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.vo.vo4In.Out4Settle;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "stockMgr/inStock")
public class InStockController extends BaseController {
    @Resource
    private IInStockService inStockService;
    @Resource
    private IInStockListService inslSer;

    @PostMapping(value = "add")
    @ResponseBody
    public AjaxResult add(@RequestBody InStockVo inStock) throws Exception {
        AjaxResult<InStockVo> result = new AjaxResult();
        InStockVo ret = inStockService.add(inStock);
        result.setData(ret);
        return result;
    }

    @PostMapping(value = "add4Return")
    @ResponseBody
    public AjaxResult add4Return(@RequestBody InStockVo inStock) throws Exception {
        AjaxResult<InStockVo> result = new AjaxResult();
        InStockVo ret = inStockService.add4Return(inStock);
        result.setData(ret);
        return result;
    }

    @RequestMapping(value = "update4Return")
    @ResponseBody
    public AjaxResult update4Return(@RequestBody @Valid InStockVo inStock, Errors error) throws Exception {
        AjaxResult result = new AjaxResult();
        filterErrors(error);
        InStockVo ret = inStockService.update(inStock);
        result.setData(ret);
        return result;
    }

    @RequestMapping(value = "getById")
    @ResponseBody
    public AjaxResult getById(String id) throws Exception {
        AjaxResult<InStockVo> result = new AjaxResult();
        InStockVo ret = inStockService.getById(id);
        result.setData(ret);
        return result;
    }

    /**
     * 采购入库查询入库单明细
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getById4Instock")
    @ResponseBody
    public AjaxResult getById4Instock(String id) throws Exception {
        AjaxResult<InStockVo> result = new AjaxResult();
        InStockVo ret = inStockService.getById4Instock(id);
        result.setData(ret);
        return result;
    }

    @RequestMapping(value = "getByBillId/{billId}")
    @ResponseBody
    public AjaxResult getByBillId(@PathVariable("billId") String billId, Errors error) throws Exception {
        AjaxResult<InStockVo> result = new AjaxResult();
        filterErrors(error);
        InStockVo ret = inStockService.getByBillId(billId);
        result.setData(ret);
        return result;
    }

    @RequestMapping(value = "audit")
    @ResponseBody
    public AjaxResult audit(@RequestBody InStockVo inStock) throws Exception {
        AjaxResult<String> result = new AjaxResult();
        boolean ret = inStockService.audit(inStock);
        if (ret) {
            result.setData(inStock.getId());
            result.setCode(200);
        } else {
            result.setCode(500);
        }
        return result;
    }

    @RequestMapping(value = "unAudit")
    @ResponseBody
    public AjaxResult unAudit(@RequestBody InStockVo inStock) throws Exception {
        AjaxResult<String> result = new AjaxResult();
        boolean ret = inStockService.unAudit(inStock);
        if (ret) {
            result.setData(inStock.getId());
            result.setCode(200);
        } else {

            result.setCode(500);
        }
        return result;
    }

    @RequestMapping(value = "charge")
    @ResponseBody
    public AjaxResult charge(@RequestBody InStockVo inStock) throws Exception {
        AjaxResult<String> result = new AjaxResult();
        boolean ret = inStockService.charge(inStock);
        if (ret) {
            result.setData(inStock.getId());
            result.setCode(200);
        } else {
            result.setCode(500);
        }
        return result;
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public AjaxResult update(@RequestBody @Valid InStockVo inStock, Errors error) throws Exception {
        AjaxResult result = new AjaxResult();
        filterErrors(error);
        InStockVo ret = inStockService.update(inStock);
        result.setData(ret);
        return result;
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public AjaxResult list(@RequestBody @Valid InStockVo inStock, Errors error) throws Exception {
        AjaxResult result = new AjaxResult();
        filterErrors(error);
        List<InStockVo> ret = inStockService.list(inStock);
        result.setData(ret);
        return result;
    }

    @RequestMapping(value = "listByPage")
    @ResponseBody
    public AjaxResult listByPage(@RequestBody @Valid QueryInfo<InStockVo> queryInfo, Errors error) throws Exception {
        AjaxResult result = new AjaxResult();
        filterErrors(error);
        Date startDate = queryInfo.getQueryObject().getStartDate();
        Date endDate = queryInfo.getQueryObject().getEndDate();
        if (startDate != null) {
            String startDateStr = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            queryInfo.getQueryObject().setStartDateStr(startDateStr);
        }
        if (endDate != null) {
            String endDateStr = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            queryInfo.getQueryObject().setEndDateStr(endDateStr);
        }
        QueryResult<InStockVo> ret = inStockService.listByPage(queryInfo);
        result.setData(ret);
        return result;
    }

    @RequestMapping(value = "getInstockLstByUniqueCode")
    @ResponseBody
    public AjaxResult getByUniqueCode(@RequestBody InSUniquecodeQueryVo uniqueCode) throws Exception {
        AjaxResult res = new AjaxResult();
        InStockListVo data = inslSer.getByUniqueCode(uniqueCode);
        res.setData(data);
        return res;
    }


    @RequestMapping(value = "inBill4settle")
    @ResponseBody
    public AjaxResult inBill4settle(@RequestBody QueryInfo<Out4Settle> queryInfo) throws Exception {
        AjaxResult result = new AjaxResult();
        result.setData(inStockService.inBill4settle(queryInfo));
        return result;
    }
}
