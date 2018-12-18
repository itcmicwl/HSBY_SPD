package gyqx.spdherp.stockout.dao.mapper;

import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
import gyqx.spdherp.stockout.vo.SysOrgVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface ReturnMgrMapper {
    List<SysOrgVo> listSysOrgByCorpId(@Param("corpId") String corpId,
                                      @Param("deptId") String deptId,
                                      @Param("userId") String userId);

    List<OutStockBillVo> listBill(@Param("corpId") String corpId,
                                  @Param("orgId") String orgId,
                                  @Param("userId") String userId,
                                  @Param("outDeptId") String outDeptId,
                                  @Param("beginDate") Date beginDate,
                                  @Param("endDate") Date endDate,
                                  @Param("status") String status);

    OutStockBillVo getBillDetail(@Param("id") String id,
                                 @Param("userId")  String userId);

    @Update("update out_stock_list set out_qty = 0, last_update_datetime = NOW(), version = version +1 where pid = #{pid}")
    void removeOutStockListQty(@Param("pid") String billId);

    @Delete("delete from hos_taking_stock where bill_id=#{billId}")
    void removeTakingInfo(@Param("billId") String billId);

    void updateOutStockStatus(@Param("outStock") OutStock outStockDb,
                              @Param("status") int status);
}
