package gyqx.spdherp.stockout.dao;

import com.google.common.collect.Lists;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.stockout.Constance;
import gyqx.spdherp.stockout.dao.mapper.BillMgrMapper;
import gyqx.spdherp.stockout.po.ReturnProvBill;
import gyqx.spdherp.stockout.po.ReturnProvBillBatch;
import gyqx.spdherp.stockout.po.ReturnProvBillSub;
import gyqx.spdherp.stockout.po.ReturnProvBillUniqueCode;
import gyqx.spdherp.stockout.service.impl.BillMgrServiceImpl;
import gyqx.spdherp.stockout.vo.*;
import gyqx.spdherp.surgery.constant.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther Liangwu
 * @Date 17-8-29 上午11:17
 */
@Repository
public class BillMgrDao {
    @Resource
    private BillMgrMapper mapper;

    public List<SysOrgVo> listSysOrgByCorpId(String corpId, String stockId, String deptId, String userId, Boolean submitted) {
        List<Integer> statusList = submitted ?
                Collections.singletonList(Constance.OUTSTOCKBILL_STATUS_SUBMIT) :
                Arrays.asList(Constance.OUTSTOCKBILL_STATUS_SUBMIT, Constance.OUTSTOCKBILL_STATUS_CHECKED, Constance.OUTSTOCKBILL_STATUS_SAVE);
        return mapper.listSysOrgByCorpId(corpId, stockId, deptId, userId, statusList);
    }

    public List<OutStockBillVo> listBill(String corpId, String deptId, List<String> stockIdList, Date beginDate, Date endDate, String status, String userId) {
        List<Integer> statusList = Arrays.asList(Constance.OUTSTOCKBILL_STATUS_SUBMIT, Constance.OUTSTOCKBILL_STATUS_CHECKED, Constance.OUTSTOCKBILL_STATUS_SAVE);
        return mapper.listBill(corpId, deptId, stockIdList, beginDate, endDate, status, userId, statusList);
    }

    public OutStockBillVo getBillDetail(String id, String userId) {
        return mapper.getBillDetail(id, userId);
    }

    public boolean stockpilebatchOutstock(OutStock outStock) {
        return mapper.stockpilebatchOutstock(outStock) >= 0;
    }

    public List<EntireOutStockVo> getWholeOutStockByUniqueCode(List<String> uniqueCodeList, String hosId, Integer fillType) {
        return mapper.getWholeOutStockByUniqueCode(uniqueCodeList, hosId, fillType == -1 ? Constance.OUTSTOCKBILL_TYPE_REAL : Constance.OUTSTOCKBILL_TYPE_FAKE);
    }

    public boolean insertFillBills(List<EntireOutStockVo> outStockVos, List<EntireOutStockListVo> outStockListVos, List<OutStockUniqueCode> outStockUniqueCodeList) {
        return insertFillBills(outStockVos, outStockListVos, outStockUniqueCodeList, null);
    }

    public boolean insertFillBills(List<EntireOutStockVo> outStockVos,
                                   List<EntireOutStockListVo> outStockListVos,
                                   List<OutStockUniqueCode> outStockUniqueCodeList,
                                   List<OutStockBatch> outStockBatchList) {
        int mRet = mapper.insertFillOutStocks(outStockVos);
        int sRet = mapper.insertFillOutStockLists(outStockListVos);
        int uRet = 0;
        if (CollectionUtils.isNotEmpty(outStockUniqueCodeList)) {
            uRet = mapper.insertFillOutStockUniques(outStockUniqueCodeList);
        }

        int bRet = 0;
        if (CollectionUtils.isNotEmpty(outStockBatchList)){
            bRet = mapper.insertFillOutStockBatchs(outStockBatchList);
        }
        return mRet > 0 && sRet > 0 && (uRet > 0 || bRet > 0);
    }

    public OutStockQtySum getOutStockQtySum(String outStockId) {
        List<Integer> statusList = Arrays.asList(Constance.OUTSTOCKBILL_STATUS_ACCOUNT, Constance.OUTSTOCKBILL_STATUS_PART_INSTOCK);
        return mapper.getOutStockQtySum(outStockId, Constance.OUTSTOCK_KIND_REQUEST, statusList);
    }

    public String getHosIdByUniqueCode(String uniqueCode) {
        return mapper.getHosIdByUniqueCode(uniqueCode);
    }

    public int updateSurPkg(List<String> pkgCodeList) {
        return mapper.updateSurPkg(pkgCodeList, Constants.SUR_PKG_STATUS_DABAO, Constants.SUR_PKG_STATUS_QGCK);
    }

    public int updateTakingInfo(String billId, List<String> pkgCodeList) {
        return mapper.updateTakingInfo(billId, pkgCodeList);
    }

    public List<EntireOutStockVo> getWholeOutStockByBigBatch(List<BigBatch4FillVo> bigBatchInfos, Integer fillType) {
        return mapper.getWholeOutStockByBigBatch(bigBatchInfos, fillType == Constance.FILL_OUT_REAL ? Constance.OUTSTOCKBILL_TYPE_REAL : Constance.OUTSTOCKBILL_TYPE_FAKE);
    }

    public EntireOutStockVo getEntireOutStockById(String billId) {
        return mapper.getEntireOutStockById(billId, Constance.OUTSTOCKBILL_STATUS_ACCOUNT);
    }

    public boolean updateEntireOutStock(String outStockId, String remark, int version, Map<BillMgrServiceImpl.IdVersion, Boolean> subIdMap) {
        int mainResult = mapper.updateOutStock(outStockId, remark, version);
        int subResult = mapper.updateOutStockSub(subIdMap.keySet());
        List<String> uniquePids = Lists.newArrayList();
        List<String> batchPids = Lists.newArrayList();
        subIdMap.forEach((k, v) -> {
            if (v == Boolean.TRUE) {
                uniquePids.add(k.getId());
            } else {
                batchPids.add(k.getId());
            }
        });
        int uniqueResult = uniquePids.size();
        if (uniquePids.size() > 0) {
            uniqueResult = mapper.updateOutStockUnique(uniquePids);
        }
        int batchResult = batchPids.size();
        if (batchPids.size() > 0) {
            batchResult = mapper.updateOutStockBatch(batchPids);
        }
        return mainResult == 1
                && subResult == subIdMap.keySet().size()
                && uniqueResult == uniquePids.size()
                && batchResult == batchPids.size();
    }

    public List<ProvGoods> listProvGoods(List<String> hosGoodsIds) {
        return mapper.listProvGoods(hosGoodsIds);
    }

    public boolean insertWholeReturnProvBillBatch(List<ReturnProvBill> returnProvBillList,
                                                  List<ReturnProvBillSub> returnProvBillSubList,
                                                  List<ReturnProvBillBatch> returnProvBillBatchList,
                                                  List<ReturnProvBillUniqueCode> returnProvBillUniqueCodeList) {
        int mainResult = mapper.insertReturnProvBillBatch(returnProvBillList);
        int subResult = mapper.insertReturnProvBillSubBatch(returnProvBillSubList);
        int batchResult = returnProvBillBatchList.size();
        if (batchResult > 0) {
            batchResult = mapper.insertReturnProvBillBatchBatch(returnProvBillBatchList);
        }
        int uniqueResult = returnProvBillUniqueCodeList.size();
        if (uniqueResult > 0) {
            uniqueResult = mapper.insertReturnProvBillUniqueBatch(returnProvBillUniqueCodeList);
        }
        return mainResult == returnProvBillList.size()
                && subResult == returnProvBillSubList.size()
                && batchResult == returnProvBillBatchList.size()
                && uniqueResult == returnProvBillUniqueCodeList.size();
    }

/*    public Integer updateOutStockBillStatus(OutStock outStock) {
        mapper.updateOutStockBillStatus(outStock);
        return outStock.getVersion() + 1;
    }*/
}
