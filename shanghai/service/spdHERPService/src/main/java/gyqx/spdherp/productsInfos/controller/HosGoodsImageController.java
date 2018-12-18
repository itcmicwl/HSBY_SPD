package gyqx.spdherp.productsInfos.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.exception.ParameterException;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.po.HosGoodsImage;
import gyqx.spdherp.productsInfos.service.HosGoodsImageService;
import gyqx.spdherp.productsInfos.vo.HosGoodsImageVo;

/**
 * @Auther Liangwu
 * @Date 17-11-3 上午11:54
 */
@RestController
@RequestMapping("productsInfos/hosGoodsImage")
public class HosGoodsImageController extends BaseController {
    @Resource
    private HosGoodsImageService service;

    @PostMapping("/add/{id}")
    public AjaxResult<String> addHosGoodsImage(@PathVariable("id") String hosGoodsId, @RequestBody List<String> images) throws Exception {
        if (images.size() > 6 || images.size() == 0) {
            throw new ParameterException("上传图片数量不正确");
        }
        if (images.stream().allMatch(StringUtils::isEmpty)) {
            throw new ParameterException("上传图片不能为空");
        }
        AjaxResult<String> result = new AjaxResult<>();
        result.setData(service.saveHosGoodsImage(hosGoodsId, images));
        return result;
    }

    @GetMapping("/get/{id}")
    public AjaxResult<HosGoodsImageVo> getHosGoodsImage(@PathVariable("id") String hosGoodsId) throws Exception {
        AjaxResult<HosGoodsImageVo> result = new AjaxResult<>();
        result.setData(service.getHosGoodsImage(hosGoodsId));
        return result;
    }

    @PostMapping("/mod/{id}/{version}")
    public AjaxResult<Integer> modHosGoodsImage(@PathVariable("id") String id,
                                                @PathVariable("version") Integer version,
                                                @RequestBody List<String> images) throws Exception {
        AjaxResult<Integer> result = new AjaxResult<>();
        HosGoodsImage hosGoodsImage = new HosGoodsImage();
        hosGoodsImage.setId(id);
        hosGoodsImage.setVersion(version);
        result.setData(service.modHosGoodsImage(hosGoodsImage, images));
        return result;
    }

    @PatchMapping("/del/{id}/{version}/{filePath}")
    public AjaxResult<Integer> updateHosGoodsImage(@PathVariable("id") String id,
                                                   @PathVariable("version") Integer version,
                                                   @PathVariable("filePath") String filePath) throws Exception {
        AjaxResult<Integer> result = new AjaxResult<>();
        result.setData(service.updateHosGoodsImage(id, version, filePath));
        return result;
    }
}
