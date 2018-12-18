package gyqx.spdherp.stockout.dao.mapper;

import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.stockout.po.ReturnProvBill;
import gyqx.spdherp.stockout.po.ReturnProvBillBatch;
import gyqx.spdherp.stockout.po.ReturnProvBillSub;
import gyqx.spdherp.stockout.po.ReturnProvBillUniqueCode;
import gyqx.spdherp.stockout.service.impl.BillMgrServiceImpl;
import gyqx.spdherp.stockout.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Auther Liangwu
 * @Date 17-8-29 上午11:17
 */
public interface BillMgrMapper {
    List<SysOrgVo> listSysOrgByCorpId(@Param("corpId") String corpId,
                                      @Param("stockId") String stockId,
                                      @Param("deptId") String deptId,
                                      @Param("userId") String userId,
                                      @Param("statusList") List<Integer> statusList);

    List<OutStockBillVo> listBill(@Param("corpId") String corpId,
                                  @Param("deptId") String deptId,
                                  @Param("stockIdList") List<String> stockIdList,
                                  @Param("beginDate") Date beginDate,
                                  @Param("endDate") Date endDate,
                                  @Param("status") String status,
                                  @Param("userId") String userId,
                                  @Param("statusList") List<Integer> statusList);

    OutStockBillVo getBillDetail(@Param("id") String id, @Param("userId") String userId);

    Integer stockpilebatchOutstock(OutStock outStock);

    List<EntireOutStockVo> getWholeOutStockByUniqueCode(@Param("uniqueCodeList") List<String> uniqueCodeList,
                                                        @Param("hosId") String hosId,
                                                        @Param("outStockType") Integer outStockType);

    int insertFillOutStocks(@Param("outStocks") List<EntireOutStockVo> outStockVos);

    int insertFillOutStockLists(@Param("outStockLists") List<EntireOutStockListVo> outStockListVos);

    int insertFillOutStockUniques(@Param("outStockUniques") List<OutStockUniqueCode> outStockUniqueCodeList);

    int insertFillOutStockBatchs(@Param("outStockBatchs") List<OutStockBatch> outStockBatchs);

    OutStockQtySum getOutStockQtySum(@Param("outStockId") String outStockId,
                                     @Param("kind") Integer kind,
                                     @Param("statusList") List<Integer> statusList);

    @Select("SELECT hos_id FROM hos_stockpile WHERE unique_code = #{uniqueCode} LIMIT 1")
    String getHosIdByUniqueCode(@Param("uniqueCode") String uniqueCode);

    int updateSurPkg(@Param("pkgCodeList") List<String> pkgCodeList,
                     @Param("pkgStDabao") int pkgStDabao,
                     @Param("pkgStQgck") int pkgStQgck);

    int updateTakingInfo(@Param("billId") String billId, @Param("pkgCodeList") List<String> pkgCodeList);

    List<EntireOutStockVo> getWholeOutStockByBigBatch(@Param("bigBatchInfoList") List<BigBatch4FillVo> bigBatchInfoList,
                                                      @Param("outStockType") Integer outStockType);

    EntireOutStockVo getEntireOutStockById(@Param("billId") String billId,
                                           @Param("status") int status);

    @Update("UPDATE out_stock SET remark = #{remark}, last_update_datetime = NOW(), version = version + 1 WHERE id = #{id} AND version = #{version}")
    int updateOutStock(@Param("id") String id,
                       @Param("remark") String remark,
                       @Param("version") int version);

    int updateOutStockSub(@Param("idVersions") Set<BillMgrServiceImpl.IdVersion> idVersions);

    int updateOutStockUnique(@Param("pids") List<String> uniquePids);

    int updateOutStockBatch(@Param("pids") List<String> batchPids);

    List<ProvGoods> listProvGoods(@Param("hosGoodsIds") List<String> hosGoodsIds);

    int insertReturnProvBillBatch(@Param("list") List<ReturnProvBill> returnProvBillList);

    int insertReturnProvBillSubBatch(@Param("list") List<ReturnProvBillSub> returnProvBillSubList);

    int insertReturnProvBillBatchBatch(@Param("list") List<ReturnProvBillBatch> returnProvBillBatchList);

    int insertReturnProvBillUniqueBatch(@Param("list") List<ReturnProvBillUniqueCode> returnProvBillUniqueCodeList);

//    void updateOutStockBillStatus(OutStock outStock);
}
