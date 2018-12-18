package gyqx.spdherp.stockout.dao.mapper;

import gyqx.spdherp.basedatamaint.vo.BarcodeParseResult;
import gyqx.spdherp.stockout.vo.IdNameVo;
import gyqx.spdherp.stockout.vo.OutStockBillSupVo;
import gyqx.spdherp.stockout.vo.StockpileHzVo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-9-8 下午3:33
 */
public interface ReturnOutMapper {
    List<StockpileHzVo> listStockGoods(@Param("hosId") String hosId,
                                       @Param("stockId") String stockId,
                                       @Param("stocKind") Integer stocKind,
                                       @Param("filter") String filter);

    List<IdNameVo> listOrgsByHosId(@Param("hosId") String hosId,
                                   @Param("deptId") String deptId);

    List<StockpileHzVo> listStockGoodsByUniqueCode(@Param("hosId") String hosId,
                                                   @Param("stockId") String stockId,
                                                   @Param("stocKind") Integer stocKind,
                                                   @Param("masterBarcode") String masterBarcode);

    Collection<StockpileHzVo> listStockGoodsByGoodsIds(@Param("hosId") String hosId,
                                                       @Param("stockId") String stockId,
                                                       @Param("stocKind") Integer stocKind,
                                                       @Param("goods") Collection<BarcodeParseResult> goods);

    OutStockBillSupVo getAllInfoOutBill(@Param("billId") String billId);

    String modifyQty(@Param("uniqueCode")String uniqueCode,
                     @Param("hosId")String hosId);
}
