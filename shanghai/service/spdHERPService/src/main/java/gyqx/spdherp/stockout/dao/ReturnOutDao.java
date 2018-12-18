package gyqx.spdherp.stockout.dao;

import gyqx.spdherp.basedatamaint.vo.BarcodeParseResult;
import gyqx.spdherp.stockout.dao.mapper.ReturnOutMapper;
import gyqx.spdherp.stockout.vo.IdNameVo;
import gyqx.spdherp.stockout.vo.OutStockBillSupVo;
import gyqx.spdherp.stockout.vo.StockpileHzVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-9-8 下午3:32
 */
@Repository
public class ReturnOutDao {
    @Resource
    ReturnOutMapper mapper;

    public List<StockpileHzVo> listStockGoods(String hosId, String stockId, Integer stocKind, String filter) {
        List<StockpileHzVo> result = mapper.listStockGoods(hosId, stockId, stocKind, filter);
        return result;
    }

    public List<IdNameVo> listOrgsByHosId(String hosId, String deptId) {
        return mapper.listOrgsByHosId(hosId, deptId);
    }

    public List<StockpileHzVo> listStockGoodsByUniqueCode(String hosId, String stockId, Integer stocKind, String masterBarcode) {
        return mapper.listStockGoodsByUniqueCode(hosId, stockId, stocKind, masterBarcode);
    }

    public Collection<StockpileHzVo> listStockGoodsByGoodsIds(String hosId, String stockId, Integer stocKind, Collection<BarcodeParseResult> barcodeParseResults) {
        return mapper.listStockGoodsByGoodsIds(hosId, stockId, stocKind, barcodeParseResults);
    }

    public OutStockBillSupVo getAllInfoOutBill(String billId){
        return mapper.getAllInfoOutBill(billId);
    }

    public String modifyQty(String uniqueCode,String hosId){
        return mapper.modifyQty(uniqueCode,hosId);
    }
}
