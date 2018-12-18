package gyqx.spdherp.stockout.controller;

import common.exception.ParameterException;
import common.utils.DictionaryUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.stockout.service.RequestOutService;
import gyqx.spdherp.stockout.vo.*;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther Liangwu
 * @Date 17-8-16 上午10:30
 */
@RestController
@RequestMapping({"/stockMgr/out/requestOut"})
public class RequestOutController extends BaseController {
    @Resource
    private RequestOutService service;

    @GetMapping("/getOrgs")
    public AjaxResult<List<SysOrgVo>> getOrgs(@RequestParam("stockId") String stockId) {
        AjaxResult<List<SysOrgVo>> result = new AjaxResult<>();
        result.setData(service.listSysOrg(stockId));
        return result;
    }

    @GetMapping("/getStockList")
    public AjaxResult<List<IdNameVo>> getStockList() {
        AjaxResult<List<IdNameVo>> result = new AjaxResult<>();
        result.setData(service.listOwnStock());
        return result;
    }

    @GetMapping("/getRequestList") // <QueryResult<DeptBuyMainVo>>
    public AjaxResult getRequestList(@RequestParam(value = "beginDate", required = false) Date beginDate,
                                     @RequestParam(value = "endDate", required = false) Date endDate,
                                     @RequestParam(value = "billId", required = false) String billId,
                                     @RequestParam("deptId") String deptId,
                                     @RequestParam("stockId") String stockId) {
        AjaxResult<List<DeptBuyMainVo>> result = new AjaxResult<>();
        result.setData(service.listDeptBuyRequest(beginDate, endDate, billId, deptId, stockId));
        return result;
    }

    @GetMapping("/getRequestBillDetailList")
    public AjaxResult getRequestBillDetailList(@RequestParam("billId") String billId,
                                               @RequestParam("deptId") String deptId,
                                               @RequestParam("stockId") String stockId) {
        AjaxResult<List<DeptBuySubVo>> result = new AjaxResult<>();
        result.setData(service.listDeptBuyRequestDetail(billId, deptId, stockId));
        return result;
    }

    @GetMapping("/getDict")
    public AjaxResult<List<DictionaryUtils.DictionaryValue>> getDict(@RequestParam("dict") String dict) {
        AjaxResult<List<DictionaryUtils.DictionaryValue>> result = new AjaxResult<>();
        result.setData(service.listDictValue(dict));
        return result;
    }

    @PostMapping("/saveOutStockBill")
    public AjaxResult<String> saveOutStockBill(@Valid @RequestBody OutStockBillVo outStockBillVo, Errors errors) throws Exception {
        filterErrors(errors);
        AjaxResult<String> result = new AjaxResult<>();
        List<String> stockTableIdList = outStockBillVo.getGoodsList().stream().map(RequestStockGoodsVo::getStockTableId).collect(Collectors.toList());
        Set<String> stockTableIdSet = new HashSet<>(stockTableIdList);
        if (stockTableIdList.size() != stockTableIdSet.size()) {
            throw new ParameterException("同一商品批号&批次&唯一码ID重复！");
        }

        Map<String, List<RequestStockGoodsVo>> map = outStockBillVo.getGoodsList().stream().filter(requestStockGoodsVo -> requestStockGoodsVo.getIsPacket().equals("1"))
                    .collect(Collectors.groupingBy(RequestStockGoodsVo::getGoodsId));

        String errGoods = "";
        boolean isErrPacketQty = false;
        for (Map.Entry<String, List<RequestStockGoodsVo>> entry : map.entrySet()) {
            List<RequestStockGoodsVo> list = entry.getValue();
            int sumQty = (int) list.stream().mapToDouble(RequestStockGoodsVo::getQty).sum();
            int packetCode = (list.get(0).getPacketCode()).intValue();
            if (sumQty % packetCode != 0) {
                if("".equals(errGoods)){
                    errGoods = list.get(0).getGoodsName();
                } else {
                    errGoods = errGoods + "," + list.get(0).getGoodsName();
                }

                if(!isErrPacketQty){
                    isErrPacketQty = true;
                }
            }
        }
        if(isErrPacketQty){
            throw new ParameterException(String.format("定数包商品【 %s 】出库总数应为整数定数包！", errGoods));
        }

        result.setData(service.saveOutStockBill(outStockBillVo));
        return result;
    }

    @PostMapping("/getUniqueCodeList")
    public AjaxResult getUniqueCodeList(@RequestBody UniqueCodeQueryVo vo) {
        AjaxResult<List<GoodsNoIdUniqueVo>> result = new AjaxResult<>();
        result.setData(service.listUniqueCode(vo.getStockId(), vo.getBuyKind(), vo.getGoodsId()));
        return result;
    }
}
