package gyqx.spdherp.stockout.controller;

import common.db.query.QueryResult;
import common.exception.ParameterException;
import common.utils.DictionaryUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.stockout.service.RequestOutService;
import gyqx.spdherp.stockout.service.ReturnOutService;
import gyqx.spdherp.stockout.service.TransferOutService;
import gyqx.spdherp.stockout.vo.*;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static gyqx.spdherp.stockout.Constance.OUTSTOCK_KIND_TRANSFER;

/**
 * @Auther Liangwu
 * @Date 17-9-8 下午3:14
 */
@RestController
@RequestMapping({"/stockMgr/out/transferOut"})
public class TransferOutController extends BaseController {
    @Resource
    private RequestOutService requestOutService;

    @Resource
    private TransferOutService service;

    @Resource
    private ReturnOutService returnOutService;

    @GetMapping("/getOrgList")
    public AjaxResult<List<IdNameVo>> getOrgList(){
        AjaxResult<List<IdNameVo>> result = new AjaxResult<>();
        result.setData(service.listOrgs());
        return result;
    }

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
            throw new ParameterException("搜索失败。");
        }
        AjaxResult<QueryResult<StockpileHzVo>> result = new AjaxResult<>();
        result.setData(returnOutService.listStockGoods(stockId, stocKind, filter, pageNum, pageSize));
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
        if(!outStockBillVo.getOutStockKind().equals(OUTSTOCK_KIND_TRANSFER.toString())){
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
        result.setData(returnOutService.parseBarcoe(stockId, stocKind, masterBarcode, slaverBarcode));
        return result;
    }
}
