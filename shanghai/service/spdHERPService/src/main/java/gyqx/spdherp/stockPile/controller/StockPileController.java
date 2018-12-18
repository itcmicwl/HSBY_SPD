package gyqx.spdherp.stockPile.controller;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.stockPile.service.IStockPileDetailQueryService;
import gyqx.spdherp.stockPile.service.IStockPileService;
import gyqx.spdherp.stockPile.vo.DeptGoodsVo;
import gyqx.spdherp.stockPile.vo.StockPileDetailQueryVo;
import gyqx.spdherp.stockPile.vo.StockPileHZVo;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by zouliang on 2017-8-22.
 */
@RestController
@RequestMapping({"/stockPile"})
public class StockPileController extends BaseController {
    @Resource
    private IStockPileService service;
    @Resource
    private IStockPileDetailQueryService stockPileDetailService;

    @RequestMapping(value="listStockPileHZ")
    @ResponseBody
    public AjaxResult listStockPileHZ(@RequestBody @Valid QueryInfo<StockPileHZVo> queryInfo, Errors error) throws Exception{
        AjaxResult result =new AjaxResult();
        filterErrors(error);
        QueryResult<StockPileHZVo> ret=service.stockPileHZ(queryInfo);

        result.setData(ret);
        return result;
    }

    @RequestMapping(value="listStockPileDetail")
    @ResponseBody
    public AjaxResult listStockPileDetail(@RequestBody @Valid QueryInfo<StockPileDetailQueryVo> queryInfo, Errors error) throws Exception{
        AjaxResult result =new AjaxResult();
        filterErrors(error);
        QueryResult<StockPileDetailQueryVo> ret=stockPileDetailService.listByPage(queryInfo);

        result.setData(ret);
        return result;
    }
    @RequestMapping(value="getGoodsInfo4AutoPurByPage")
    @ResponseBody
    public AjaxResult listStockPileHZ(@RequestBody QueryInfo<DeptGoodsVo> queryInfo) throws Exception{
        AjaxResult result =new AjaxResult();
        QueryResult<DeptGoodsVo> ret=service.getGoodsInfo4AutoPurByPage(queryInfo);
        result.setData(ret);
        return result;
    }
}
