package gyqx.spdherp.productsInfos.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryParamUtils;
import common.exception.ParameterException;
import common.transform.Tx;
import common.utils.EntityUtils;
import common.utils.UtilsContext;
import gyqx.spdhdi.po.HosGoodsImage;
import gyqx.spdherp.productsInfos.service.HosGoodsImageService;
import gyqx.spdherp.productsInfos.vo.HosGoodsImageVo;

/**
 * @Auther Liangwu
 * @Date 17-11-3 上午11:55
 */
@Service
public class HosGoodsImageServiceImpl implements HosGoodsImageService {
    @Resource
    private SimpleDao simpleDao;

    @Resource
    private UtilsContext utilsContext;

    @Override
    public String saveHosGoodsImage(String hosGoodsId, List<String> images) throws Exception {
        if (simpleDao.checkExists(HosGoodsImage.class, "hosGoodsId", hosGoodsId) > 1) {
            throw new ParameterException("已存在此商品图片信息，无法新增！");
        }
        HosGoodsImage hosGoodsImage = new HosGoodsImage();
        String id = utilsContext.getSysAtomUtil().newValue("hos_goods_image_id");
        String uid = utilsContext.getUserStateUtils().getCurrent().getUserId();
        hosGoodsImage.setId(id);
        hosGoodsImage.setHosGoodsId(hosGoodsId);
        hosGoodsImage.setUid(uid);
        hosGoodsImage.setLastUpdateDatetime(new Date());
        hosGoodsImage.setVersion(0);

        simpleDao.insert(modImgUrl(hosGoodsImage, images));
        return id;
    }

    @Override
    public HosGoodsImageVo getHosGoodsImage(String hosGoodsId) throws Exception {
        long num = simpleDao.checkExists(HosGoodsImage.class, "hosGoodsId", hosGoodsId);
        if (num > 1) {
            throw new ParameterException("存在多条商品图片信息，请联系管理员！");
        } else if (num == 0) {
            return null;
        }
        HosGoodsImage hosGoodsImage = (HosGoodsImage) simpleDao.queryByFieldName(HosGoodsImage.class, "hosGoodsId", hosGoodsId).get(0);
        HosGoodsImageVo hosGoodsImageVo = new HosGoodsImageVo();
        Tx.transform(hosGoodsImage).to(hosGoodsImageVo);
        return hosGoodsImageVo;
    }

    @Override
    public Integer modHosGoodsImage(HosGoodsImage hosGoodsImage, List<String> images) throws Exception {
        simpleDao.updateNotNullFields(modImgUrl(hosGoodsImage, images));
        return hosGoodsImage.getVersion() + 1;
    }

    @Override
    public Integer updateHosGoodsImage(String id, Integer version, final String filePath) throws Exception {
        List<QueryParamUtils.Pair> pairs = QueryParamUtils.queryFields(new HosGoodsImage(), (pairx) -> pairx.getFieldName().startsWith("filePath"));

        boolean b = pairs.stream().anyMatch(o -> o.getFieldName().equals(filePath));
        if (!b) {
            throw new ParameterException("非有效图片地址！");
        }

        String tableName = EntityUtils.getTableName(HosGoodsImage.class);
        String columnName = EntityUtils.getFieldName(HosGoodsImage.class, filePath);
        String sql = "update " + tableName + " set " + columnName + "=\"\",version=version+1 where id=? and version=?";
        return simpleDao.executeSql(sql, id, version);
    }

    private HosGoodsImage modImgUrl(HosGoodsImage hosGoodsImage, List<String> images) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<HosGoodsImage> clz = HosGoodsImage.class;
        int len = images.size();
        for (int i = 0; i < len; i++) {
            String methodName = "setFilePath" + (i + 1);
            Method method = clz.getDeclaredMethod(methodName, String.class);
            method.invoke(hosGoodsImage, images.get(i));
        }
        return hosGoodsImage;
    }
}
