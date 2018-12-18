package gyqx.spdherp.stockout.dao.mapper;

import gyqx.spdherp.po.*;
import gyqx.spdherp.stockout.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RequestOutMapper {
    List<SysOrgVo> listSysOrgByCorpId(@Param("corpId") String corpId,
                                      @Param("stockId") String stockId,
                                      @Param("deptId") String deptId,
                                      @Param("userId") String userId);

    List<IdNameVo> listOwnStock(@Param("hosId") String hosId,
                                @Param("userId") String userId);

    List<DeptBuyMainVo> listDeptBuyRequest(@Param("beginDate") Date beginDate,
                                           @Param("endDate") Date endDate,
                                           @Param("billId") String billId,
                                           @Param("deptId") String deptId,
                                           @Param("stockId") String stockId,
                                           @Param("hosId") String hosId,
                                           @Param("userId") String userId);

    List<GoodsNoIdUniqueVo> listUniqueCode(@Param("hosId") String hosId,
                                           @Param("stockId") String stockId,
                                           @Param("buyKind") String buyKind,
                                           @Param("goodsIdList") List<String> goodsIdList);

    List<HosStockpileVo> listHosStockpile(@Param("stockTableId") String stockTableId,
                                        @Param("hosId") String hosId,
                                        @Param("stockId") String stockId,
                                        @Param("goodsId") String goodsId,
                                        @Param("batchNo") String batchNo,
                                        @Param("batchId") String batchId,
                                        @Param("uniqueCode") String uniqueCode,
                                        @Param("stocKind") Integer stocKind,
                                        @Param("isUnique") boolean isUnique);

    List<DeptBuySubVo> listDeptBuyRequestDetail(@Param("hosId") String hosId,
                                                @Param("billId") String billId,
                                                @Param("deptId") String deptId,
                                                @Param("stockId") String stockId);

    List<HosStockpileVo> listHosStockpileWithDs(@Param("sub") RequestStockGoodsVo requestStockGoodsVo,
                                              @Param("main") OutStock outStockBill);

    IdNameVo getDeptInfoByStockId(@Param("hosId") String hosId,
                                  @Param("stockId") String stockId);

    Integer insertOutStockLists(@Param("outStockLists") List<? extends OutStockList> outStockLists);

    Integer insertOutStockBatches(@Param("outStockBatches") List<OutStockBatch> outStockBatches);

    Integer insertOutStockUniqueCodes(@Param("outStockUniqueCodes") List<OutStockUniqueCode> outStockUniqueCodes);

    Integer insertHosTakingStock(@Param("hosTakingStocks") List<HosTakingStock> hosTakingStocks);
}