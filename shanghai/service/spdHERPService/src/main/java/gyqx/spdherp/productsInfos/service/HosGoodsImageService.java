package gyqx.spdherp.productsInfos.service;

import java.util.List;

import gyqx.spdhdi.po.HosGoodsImage;
import gyqx.spdherp.productsInfos.vo.HosGoodsImageVo; /**
 * @Auther Liangwu
 * @Date 17-11-3 上午11:55
 */
public interface HosGoodsImageService {
    String saveHosGoodsImage(String hosGoodsId, List<String> images) throws Exception;

    HosGoodsImageVo getHosGoodsImage(String hosGoodsId) throws Exception;

    Integer modHosGoodsImage(HosGoodsImage hosGoodsImage, List<String> images) throws Exception;

    Integer updateHosGoodsImage(String id, Integer version, final String filePath) throws Exception;
}
