package gyqx.spdherp.stockout.dao.mapper;

import gyqx.spdherp.barcodePrintingCenter.vo.DsGoodsVo;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockList;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.stockout.vo.Out4UseVo;
import gyqx.spdherp.stockout.vo.OutStockBatchVo;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
import gyqx.spdherp.stockout.vo.vo4In.Out4Settle;
import gyqx.spdherp.stockout.vo.vo4In.OutBill4InVo;
import gyqx.spdherp.stockout.vo.vo4In.OutSub4InVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OutBill4InMapper {

    List<OutSub4InVo> getSubs(@Param("billId") String billId, @Param("isPacket") String isPacket);

    List<Out4Settle> outBill4settle(Out4Settle bean);

    List<OutStockBatch> getBatchs(@Param("billId") String billId);

    List<OutStockBatch> getBatchsByPid(@Param("billId") String billId, @Param("pid") String pid);


    List<OutStockUniqueCode> getUniqueCodes(@Param("billId") String billId);

    List<OutStockUniqueCode> getUniqueCodesByPid(@Param("billId") String billId, @Param("pid") String pid,
                                                 @Param("pRowId") int pRowId);

    List<OutStockUniqueCode> getUniqueCodesByPid(@Param("billId") String billId, @Param("pid") String pid);

    Integer updateUniqueCodesUsed(@Param("list") List<OutStockUniqueCode> lst);

    OutBill4InVo getOutBill(@Param("billId") String billId);

    List<OutBill4InVo> list4Instock(OutBill4InVo qb);

    int updateOutBillLstStatus(List<OutStockList> lst);

    int updateBatchUseQty(@Param("useQty") BigDecimal useQty, @Param("billId") String billId,
                          @Param("goodsId") String goodsId, @Param("batchCode") String batchCode, @Param("goodsBatchId") String goodsBatchId);

    Out4UseVo outBill4Use(Out4UseVo query);

    int updateOutStockStatus(OutStock outStock);

    int stockpilebatch(OutStockBillVo outStockBillVo);

    List<OutStockUniqueCode> getUniqueCodeList(String outBillId);

    List<DsGoodsVo> getDSGoodsByOutBill(DsGoodsVo qb);

    int setOutBatchQty(List<OutStockBatchVo> lst4Update);
}
