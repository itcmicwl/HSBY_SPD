package gyqx.spdherp.basedatamaint.dao;

import gyqx.spdhdi.po.ProvGoodsInfo;
import gyqx.spdherp.basedatamaint.dao.mapper.BarcodeParseMapper;
import gyqx.spdherp.basedatamaint.vo.BarcodeParseResult;
import gyqx.spdherp.basedatamaint.vo.BarcodeRuleVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther Liangwu
 * @Date 17-8-12 下午3:57
 */
@Repository
public class BarcodeParseDao {
    @Resource
    private BarcodeParseMapper mapper;

    public Set<BarcodeRuleVo> listByBarcodeLen(int barcodeLen) {
        return new HashSet<>(mapper.listByBarcodeLen(barcodeLen));
    }

    public Set<String> listHistNameById(List<ProvGoodsInfo> goodsList, String corpId) {
        return mapper.listHisNameById(goodsList, corpId);
    }
/*

    public List<String> listProvGoodsHosId(String goodsNo, String hosId, String provId) {
        return mapper.listProvGoodsHosId(goodsNo, hosId, provId);
    }

    public List<String> listHosGoodsId(String goodsNo, String hosId) {
        return mapper.listHosGoodsId(goodsNo, hosId);
    }
*/

    public List<BarcodeParseResult> listHosPackageInfo(String barcodeStr, String hosId) {
        return mapper.listHosPackageInfo(barcodeStr, hosId);
    }

    public Set<BarcodeParseResult> listGoodsInfo(String goodsNo, String hosId, String provId) {
        return mapper.listHosGoodsInfo(goodsNo, hosId, provId);
    }
}
