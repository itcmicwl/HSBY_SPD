package gyqx.spdherp.stockout.dao;

import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.stockout.dao.mapper.ReturnMgrMapper;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
import gyqx.spdherp.stockout.vo.SysOrgVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class ReturnMgrDao {
    @Resource
    private ReturnMgrMapper mapper;
    public List<SysOrgVo> listSysOrgByCorpId(String corpId, String orgId, String userId) {
        return mapper.listSysOrgByCorpId(corpId, orgId, userId);
    }

    public List<OutStockBillVo> listBill(String corpId, String orgId, String userId, String outDeptId, Date beginDate, Date endDate, String status) {
        return mapper.listBill(corpId, orgId, userId, outDeptId, beginDate, endDate, status);
    }

    public OutStockBillVo getBillDetail(String id, String userId) {
        return mapper.getBillDetail(id, userId);
    }

    public void removeOutStockListQty(String billId) {
        mapper.removeOutStockListQty(billId);
    }

    public void removeTakingInfo(String billId) {
        mapper.removeTakingInfo(billId);
    }

    public void updateOutStockStatus(OutStock outStockDb, int status) {
        mapper.updateOutStockStatus(outStockDb, status);
    }
}
