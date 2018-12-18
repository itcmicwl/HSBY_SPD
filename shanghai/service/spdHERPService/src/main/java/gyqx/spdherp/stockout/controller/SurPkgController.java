package gyqx.spdherp.stockout.controller;

import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.stockout.service.RequestOutService;
import gyqx.spdherp.stockout.service.SurPkgService;
import gyqx.spdherp.stockout.vo.IdNameVo;
import gyqx.spdherp.stockout.vo.SurPkgVo;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author liangwu
 * @Date 18-10-8 下午12:01
 */
@RestController
@RequestMapping("/stockMgr/out/sur")
public class SurPkgController extends BaseController {
    private final static int[] PAGE_SIZES = {30, 50, 100};

    @Autowired
    private SurPkgService service;

    @Autowired
    private RequestOutService requestOutService;

    @GetMapping("/surpkg")
    public AjaxResult listSurPkg(@RequestParam("stockId") String stockId,
                                 @RequestParam(value = "deptId", required = false) String deptId,
                                 @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "30") int pageSize) {
        Assert.isTrue(ArrayUtils.contains(PAGE_SIZES, pageSize), "分页信息必须为：" + ArrayUtils.toString(PAGE_SIZES));
        AjaxResult<QueryResult<SurPkgVo>> result = new AjaxResult<>();
        result.setData(service.listSurPkg(stockId, deptId, pageNum, pageSize));
        return result;
    }

    @GetMapping("/surpkg/{pkgCode}")
    public AjaxResult<SurPkgVo> getSurPkg(@PathVariable String pkgCode) {
        AjaxResult<SurPkgVo> result = new AjaxResult<>();
        result.setData(service.getSurPkg(pkgCode));
        return result;
    }

    @PostMapping("/surpkg")
    public AjaxResult saveOutStockBill(@RequestBody List<String> pkgCodeList) throws Exception {
        AjaxResult<Integer> result = new AjaxResult<>();
        result.setData(service.saveOutStockBill(pkgCodeList));
        return result;
    }

    @GetMapping("/getStockList")
    public AjaxResult<List<IdNameVo>> getStockList() {
        AjaxResult<List<IdNameVo>> result = new AjaxResult<>();
        result.setData(requestOutService.listOwnStock());
        return result;
    }
}
