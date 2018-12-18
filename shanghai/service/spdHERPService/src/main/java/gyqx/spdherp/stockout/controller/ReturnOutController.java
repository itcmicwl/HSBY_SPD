package gyqx.spdherp.stockout.controller;

import common.db.query.QueryResult;
import common.exception.ParameterException;
import common.utils.DictionaryUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.forRiva.service.ICallForRivaService;
import gyqx.spdherp.forRiva.vo.ForRivaH03;
import gyqx.spdherp.stockout.service.RequestOutService;
import gyqx.spdherp.stockout.service.ReturnOutService;
import gyqx.spdherp.stockout.vo.*;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static gyqx.spdherp.stockout.Constance.OUTSTOCK_KIND_RETURN;
import static gyqx.spdherp.stockout.Constance.OUTSTOCK_KIND_TRANSFER;

@RestController
@RequestMapping({"/stockMgr/out/returnOut"})
public class ReturnOutController extends BaseController {
    @Resource
    private RequestOutService requestOutService;

    @Resource
    private ReturnOutService service;

    @Resource
    private ICallForRivaService iCallForRivaService;

    @GetMapping("/getStockList")
    public AjaxResult<List<IdNameVo>> getStockList() {
        AjaxResult<List<IdNameVo>> result = new AjaxResult<>();
        result.setData(requestOutService.listOwnStock());
        return result;
    }

    @PostMapping("/getUniqueCodeList")
    public AjaxResult getUniqueCodeList(@RequestBody UniqueCodeQueryVo vo) {
        AjaxResult<List<GoodsNoIdUniqueVo>> result = new AjaxResult<>();
        result.setData(requestOutService.listUniqueCode(vo.getStockId(), vo.getBuyKind(), vo.getGoodsId()));
        return result;
    }

    @GetMapping("/getStockGoodsList")
    public AjaxResult getStockGoodsList(@RequestParam("stockId") String stockId,
                                        @RequestParam("stocKind") Integer stocKind,
                                        @RequestParam("filter") String filter,
                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "0") int pageSize) throws ParameterException {
        if (!StringUtils.hasText(stockId) || !StringUtils.hasText(filter) || filter.length() < 3) {
            throw new ParameterException("搜索条件错误。");
        }
        AjaxResult<QueryResult<StockpileHzVo>> result = new AjaxResult<>();
        result.setData(service.listStockGoods(stockId, stocKind, filter, pageNum, pageSize));
        return result;
    }

    @GetMapping("/getOrgList")
    public AjaxResult<List<IdNameVo>> getOrgList() {
        AjaxResult<List<IdNameVo>> result = new AjaxResult<>();
        result.setData(service.listOrgs());
        return result;
    }

    @GetMapping("/getDict")
    public AjaxResult<List<DictionaryUtils.DictionaryValue>> getDict(@RequestParam("dict") String dict) {
        AjaxResult<List<DictionaryUtils.DictionaryValue>> result = new AjaxResult<>();
        result.setData(requestOutService.listDictValue(dict));
        return result;
    }

    @PostMapping("/saveOutStockBill")
    public AjaxResult<String> saveOutStockBill(@Valid @RequestBody OutStockBillVo outStockBillVo, Errors errors) throws Exception {
        filterErrors(errors);
        if(!outStockBillVo.getOutStockKind().equals(OUTSTOCK_KIND_RETURN.toString())){
            throw new ParameterException("出库类型错误。");
        }
        AjaxResult<String> result = new AjaxResult<>();
        result.setData(requestOutService.saveOutStockBill(outStockBillVo));
        return result;
    }

    @GetMapping("/parseBarcode")
    public AjaxResult parseBarcode(@RequestParam("stockId") String stockId,
                                   @RequestParam("stocKind") Integer stocKind,
                                   @RequestParam("masterBarcode") String masterBarcode,
                                   @RequestParam(value = "slaverBarcode", required = false) String slaverBarcode) throws Exception {
        AjaxResult<BarcodeResultSetVo> result = new AjaxResult<>();
        result.setData(service.parseBarcoe(stockId, stocKind, masterBarcode, slaverBarcode));
        return result;
    }
}
