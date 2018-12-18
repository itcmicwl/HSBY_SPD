package gyqx.spdherp.stockout.service.impl;

import common.db.SimpleDao;
import common.exception.ParameterException;
import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import gyqx.spdherp.forRiva.service.ICallForRivaService;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.stockout.Constance;
import gyqx.spdherp.stockout.dao.BillMgrDao;
import gyqx.spdherp.stockout.dao.ReturnMgrDao;
import gyqx.spdherp.stockout.service.ReturnMgrService;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
import gyqx.spdherp.stockout.vo.SysOrgVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static gyqx.spdherp.stockout.Constance.OUTSTOCK_KIND_RETURN;

@Service
public class ReturnMgrServiceImpl implements ReturnMgrService {
    @Resource
    private ReturnMgrDao returnMgrDao;

    @Resource
    private BillMgrDao billMgrDao;

    @Resource
    private UtilsContext utilsContext;

    @Resource
    private SimpleDao simpleDao;

    @Resource
    private ICallForRivaService iCallForRivaService;

    @Override
    public List<SysOrgVo> listSysOrg() {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        return returnMgrDao.listSysOrgByCorpId(user.getCorpId(), user.getOrgId(), user.getUserId());
    }

    @Override
    public List<OutStockBillVo> listBill(String outDeptId, Date beginDate, Date endDate, String status) {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        return returnMgrDao.listBill(user.getCorpId(), user.getOrgId(), user.getUserId(), outDeptId, beginDate, endDate, status);
    }

    @Override
    public OutStockBillVo getBillDetail(String id) {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        return returnMgrDao.getBillDetail(id, user.getUserId());
    }

    @Override
    @Transactional
    public Integer updateOutStock(OutStock outStock) throws Exception {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        OutStock outStockDb = simpleDao.get(OutStock.class, outStock.getId());

        if (validateVo(outStock, outStockDb, user)) {
            throw new ParameterException("输入出库单错误。");
        }

        switch (outStock.getStatus()) {
            case 20: // 审核
                outStock.setAuditor(user.getUserId());
                outStock.setAuditDate(new Date());

                // 改变数据库出库单状态，记帐时需要验证状态
                updateOutStockStatus(outStockDb, outStock.getStatus());
                outStock.setStatus(Constance.OUTSTOCKBILL_STATUS_ACCOUNT);
                outStock.setVersion(outStock.getVersion() + 1);

                outStock.setAccounter(user.getUserId());
                outStock.setAccountDate(new Date());
                // 商品正式出库
                // 调用存储过程
                if (!billMgrDao.stockpilebatchOutstock(outStock)) {
                    throw new ParameterException("记帐失败！", -1);
                }
                removeTakingInfo(outStock.getId());
                break;
            case 40: // 作废
                // 恢复库存
            case 50: // 驳回
                // 恢复库存
                removeTakingInfo(outStock.getId());
                removeOutStockListQty(outStock.getId());
                break;
            default:
                break;
        }

        outStock.setLastUpdateDatetime(new Date());
        if (simpleDao.updateNotNullFields(outStock) > 0) {
            return outStock.getVersion() + 1;
        } else {
            throw new ParameterException("更新状态失败");
        }
    }

    @Override
    public void callFiva(OutStock outStock) throws Exception {
        OutStock outStockDb = simpleDao.get(OutStock.class, outStock.getId());
        // 二级科室高值产品退货出库推送信息去高值柜
        if (outStock.getStatus().equals(Constance.OUTSTOCKBILL_STATUS_CHECKED)
                && outStockDb.getOutStockKind().equals(OUTSTOCK_KIND_RETURN.toString())
                && outStockDb.getOutStockType().equals(Constance.OUTSTOCKBILL_TYPE_FAKE)) {
            iCallForRivaService.callPushOutInfo2(outStock.getId());
        }
    }

    private void updateOutStockStatus(OutStock outStockDb, int status) {
        returnMgrDao.updateOutStockStatus(outStockDb, status);
    }

    private boolean validateVo(OutStock outStock, OutStock outStockDb, UserOnlineInfo user) {
        return outStockDb == null
                || outStockDb.getVersion() != outStock.getVersion()
                || !outStockDb.getOutStockKind().equals(outStock.getOutStockKind())
                || !outStockDb.getOutStockType().equals(outStock.getOutStockType())
                || !outStockDb.getInDeptId().equals(user.getOrgId())
                || !outStockDb.getOutStockKind().equals(Constance.OUTSTOCK_KIND_RETURN.toString());
    }

    private void removeOutStockListQty(String billId) {
        returnMgrDao.removeOutStockListQty(billId);
    }

    private void removeTakingInfo(String billId) {
        returnMgrDao.removeTakingInfo(billId);
    }
}
