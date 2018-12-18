package gyqx.spdherp.surgery.controller;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import gyqx.spdherp.applyMgr.vo.ApplySurPkgVo;
import gyqx.spdherp.applyMgr.vo.ApplySurgeryPkgVo;
import gyqx.spdherp.surgery.service.ISurgeryPkgService;
import gyqx.spdherp.surgery.vo.SurBill4Pack;
import gyqx.spdherp.surgery.vo.SurPackResVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 手术包订单主表(SurgeryPkg)表控制层
 *
 * @author moonbless
 * @since 2018-09-29 14:37:11
 */
@RestController
@RequestMapping("surgeryPkg")
public class SurgeryPkgController {
    /**
     * 服务对象
     */
    @Resource
    private ISurgeryPkgService surgeryPkgService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getById/{id}")
    public AjaxResult selectOne(@PathVariable("id")String id) {
        AjaxResult res = new AjaxResult();
        res.setData(this.surgeryPkgService.getById(id));
        return res;
    }

    @RequestMapping(value = "listByPage")
    @ResponseBody
    public AjaxResult listByPage(@RequestBody QueryInfo<SurgeryPkgVo> queryInfo)  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        QueryResult<SurgeryPkgVo> data = surgeryPkgService.listPage(queryInfo);
        result.setData(data);
        return result;
    }
    @RequestMapping(value = "getUnPackApplyBIllList")
    @ResponseBody
    public AjaxResult getUnPackApplyBIllList(@RequestBody QueryInfo<SurgeryPkgVo> queryInfo)  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        QueryResult<SurgeryPkgVo> data = surgeryPkgService.listPage(queryInfo);
        result.setData(data);
        return result;
    }
    @RequestMapping(value = "sumitSurPack")
    @ResponseBody
    public AjaxResult sumitSurPack(@RequestBody List<SurgeryPkgVo> surInfo)  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        List<SurPackResVo> data = surgeryPkgService.updateSurPack(surInfo);
        result.setData(data);
        return result;
    }
    @GetMapping(value = "unPackApplyBIll/{billId}/{stockId}")
    @ResponseBody
    public AjaxResult getUnPackApplyBIll(@PathVariable("billId")String billId,@PathVariable("stockId")String stockId) throws Exception{
        AjaxResult res = new AjaxResult();
        ApplySurPkgVo data = surgeryPkgService.creatOrGetSurPkgByApplyId(billId,stockId);
        res.setData(data);
        return res;
    }
    @GetMapping(value = "getSurBill4Pack/{surBillId}/{stockId}")
    @ResponseBody
    public AjaxResult getSurBill4Pack(@PathVariable("surBillId")String surBillId,@PathVariable("stockId")String stockId) throws Exception{
        AjaxResult res = new AjaxResult();
        SurBill4Pack data = surgeryPkgService.getSurBill4Pack(surBillId,stockId);
        res.setData(data);
        return res;
    }
    @PostMapping("list4Use")
    @ResponseBody
    public AjaxResult list4Use (@RequestBody SurgeryPkgVo query)throws Exception{
        AjaxResult res = new AjaxResult();
        List<SurgeryPkgVo> lst = surgeryPkgService.list4Use(query);
        res.setData(lst);
        return res;
    }
}