package gyqx.spdherp.basedatamaint.controller;

import common.db.query.QueryResult;
import common.utils.DictionaryUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.po.BasMfrsInfo;
import gyqx.spdherp.basedatamaint.service.BasMfrsInfoService;
import gyqx.spdherp.basedatamaint.vo.BasMfrsInfoVo;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-14 下午3:47
 */
@RestController
@RequestMapping({"/baseData/baseDataMaint/BasMfrsInfo"})
public class BasMfrsInfoController extends BaseController {
    @Resource
    private BasMfrsInfoService service;

    @GetMapping("/getMfrsKind") // List<DictionaryUtils.DictionaryValue>
    public AjaxResult getMfrsKind(){
        AjaxResult<List<DictionaryUtils.DictionaryValue>> result = new AjaxResult<>();
        result.setData(service.listCjlxDict());
        return result;
    }

    @GetMapping("/getMfrsInfoList") // <QueryResult<BasMfrsInfoVo>>
    public AjaxResult getMfrsInfoList(String mfrsName, String mfrsKind, int pageNum, int pageSize){
        AjaxResult<QueryResult<BasMfrsInfoVo>> result = new AjaxResult<>();
        result.setData(service.listBasMfrsInfo(mfrsName, mfrsKind, pageNum, pageSize));
        return result;
    }

    @PostMapping("/addBasMfrsInfo")
    public AjaxResult<String> addMfrsInfo(@Valid @RequestBody BasMfrsInfo basMfrsInfo, Errors errors) throws Exception {
        filterErrors(errors);
        AjaxResult<String> result = new AjaxResult<>();
        result.setData(service.saveBasMfrsInfo(basMfrsInfo));
        return result;
    }

    @PutMapping("/editBasMfrsInfo")
    public AjaxResult<Integer> editBasMfrsInfo(@Valid @RequestBody BasMfrsInfo basMfrsInfo, Errors errors) throws Exception {
        filterErrors(errors);
        AjaxResult<Integer> result = new AjaxResult<>();
        result.setData(service.updateBasMfrsInfo(basMfrsInfo));
        return result;
    }

    @DeleteMapping("/delBasMfrsInfo")
    public AjaxResult<String> delBasMfrsInfo(@RequestParam(value = "id", required = true) String id,
                                             @RequestParam(value = "version", required = true) int version) throws Exception {
        AjaxResult<String> result = new AjaxResult<>();
        result.setData(service.removeBasMfrsInfo(id, version));
        return result;
    }
}
