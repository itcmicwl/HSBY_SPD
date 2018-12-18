package gyqx.spdherp.stockMgr.controller;

import common.exception.ParameterException;
import common.web.AjaxResult;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.stockMgr.service.IInStockService;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.dao.BillMgrDao;
import gyqx.spdherp.stockout.service.ReturnOutService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import static gyqx.spdherp.stockMgr.Constance.INSTOCK_TYPE_TRUE;

/**
 * Created by cjzyw on 2018/5/15.
 */
@Controller
@RequestMapping(value = "stockMgr/inStock")
public class InStockSupController {
    @Resource
    private IInStockService inStockService;
    @Resource
    private ReturnOutService returnOutService;
    @Resource
    private BillMgrDao billMgrDao;
    @PostMapping(value = "addSup")
    @ResponseBody
    public AjaxResult addSup(@RequestBody InStockVo inStock )  throws  Exception

    {
        AjaxResult<InStockVo> result = new AjaxResult();
        //入库单据一级写入库存数据数量为零
        InStockVo inStockVo = inStockService.addSup(inStock);
        //保存出库信息
//        OutStock outStock = returnOutService.getAllInfoOutBill(inStockVo);
//        if(outStock.getOutStockType()!=INSTOCK_TYPE_TRUE){
//            returnOutService.modifyQty(outStock);
//        }
        result.setData(inStockVo);
        return result;
    }
}
