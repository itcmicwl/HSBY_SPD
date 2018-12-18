package gyqx.spdherp.stockout.controller;

import common.db.query.QueryResult;
import common.exception.ParameterException;
import common.web.AjaxResult;
import gyqx.spdherp.stockout.service.ReturnOutSupService;
import gyqx.spdherp.stockout.vo.IdNameVoSup;
import gyqx.spdherp.stockout.vo.StockpileHzVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cjzyw on 2018/5/18.
 */
@RestController
@RequestMapping({"/stockMgr/out/returnOutSup"})
public class ReturnOutSupController {
    @Resource
    ReturnOutSupService service;

    @GetMapping("/getSupList")
    public AjaxResult<List<IdNameVoSup>> getSupList(@RequestParam(value = "stockid") String stockid) {
        //String stockid = "stoc-066";
        AjaxResult<List<IdNameVoSup>> result = new AjaxResult<>();
        result.setData(service.listSup(stockid));
        return result;
    }

    @GetMapping("/getStockGoodsListSup")
    public AjaxResult getStockGoodsList(@RequestParam("stockId") String stockId,
                                        @RequestParam("stocKind") Integer stocKind,
                                        @RequestParam("filter") String filter,
                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "0") int pageSize,
                                        @RequestParam("provId")String provId) throws ParameterException {
        if (!StringUtils.hasText(stockId) || !StringUtils.hasText(filter) || filter.length() < 3) {
            throw new ParameterException("搜索失败。");
        }
        AjaxResult<QueryResult<StockpileHzVo>> result = new AjaxResult<>();
        result.setData(service.listStockGoodsSup(stockId, stocKind, filter, pageNum, pageSize,provId));
        return result;
    }
}
