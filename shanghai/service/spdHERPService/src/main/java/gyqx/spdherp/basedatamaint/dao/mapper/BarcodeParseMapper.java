package gyqx.spdherp.basedatamaint.dao.mapper;

import gyqx.spdhdi.po.ProvGoodsInfo;
import gyqx.spdherp.basedatamaint.vo.BarcodeParseResult;
import gyqx.spdherp.basedatamaint.vo.BarcodeRuleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Auther Liangwu
 * @Date 17-8-12 下午3:57
 */
public interface BarcodeParseMapper {
    List<BarcodeRuleVo> listByBarcodeLen(int barcodeLen);

    Set<String> listHisNameById(@Param("goodsList") List<ProvGoodsInfo> goodsList,
                                @Param("corpId") String corpId);
/*

    List<String> listProvGoodsHosId(@Param("goodsNo") String goodsNo,
                                    @Param("hosId") String hosId,
                                    @Param("provId") String provId);

    List<String> listHosGoodsId(@Param("goodsNo") String goodsNo,
                                @Param("hosId") String hosId);
*/

    List<BarcodeParseResult> listHosPackageInfo(@Param("barcodeStr") String barcodeStr,
                                                @Param("hosId") String hosId);

    Set<BarcodeParseResult> listHosGoodsInfo(@Param("goodsNo") String goodsNo,
                                             @Param("hosId") String hosId,
                                             @Param("provId") String provId);
}
