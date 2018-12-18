package gyqx.spdherp.stockout.service;

import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
import gyqx.spdherp.stockout.vo.SysOrgVo;

import java.util.Date;
import java.util.List;

public interface ReturnMgrService {
    List<SysOrgVo> listSysOrg();

    List<OutStockBillVo> listBill(String outDeptId, Date beginDate, Date endDate, String status);

    OutStockBillVo getBillDetail(String id);

    Integer updateOutStock(OutStock outStock) throws Exception;

    void callFiva(OutStock outStock) throws Exception;
}
