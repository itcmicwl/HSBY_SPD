package gyqx.spdherp.stockout.service.impl;

import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.po.SickerUseList;
import gyqx.spdherp.stockMgr.service.IInStockService;
import gyqx.spdherp.stockout.Constance;
import gyqx.spdherp.stockout.service.BillMgrService;
import gyqx.spdherp.stockout.service.IFillBillService;
import gyqx.spdherp.stockout.vo.BigBatch4FillVo;
import gyqx.spdherp.stockout.vo.EntireOutStockListVo;
import gyqx.spdherp.stockout.vo.SickerUserListVo;
import gyqx.spdherp.stockout.vo.SickerUserVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by moonb on 2018/5/14.
 */
@Service
public class FillBillService implements IFillBillService {
    @Resource
    private IInStockService instockSer;
    //    @Resource
//    private IInStockUniqueCodeService iUniqueSer;
//    @Resource
//    private SysAtomUtil atomUtil;
    @Resource
    private BillMgrService billMgrService;

    @Override
    public void createFillBillBySickerUse(SickerUserVo sickerUserVo) throws Exception {
        List<SickerUserListVo> lstUse = sickerUserVo.getLstDetail();
        List<String> uniqueCode = lstUse.stream().filter(item -> item.getStatus() < Constance.AFTER_CANCEL && StringUtils.hasText(item.getUniqueCode())).map(SickerUseList::getSelfCode).collect(Collectors.toList());
        List<BigBatch4FillVo> bigBatchInfos = lstUse.stream().filter(item -> item.getStatus() < Constance.AFTER_CANCEL && StringUtils.hasText(item.getSurId()) && StringUtils.hasText(item.getBigBatchCode()))
                .map(o -> {
                    BigBatch4FillVo bb4f = new BigBatch4FillVo();
                    bb4f.setBigBatchCode(o.getBigBatchCode());
                    bb4f.setSurCode(o.getSurId());
                    bb4f.setQty(o.getUseQty());
                    return bb4f;
                }).collect(Collectors.toList());//手术包类型的低值也要补单据
        if (uniqueCode.size() == 0 && bigBatchInfos.size() == 0) {
            throw new Exception("没有需要补的单据");
        }
        this.createFillBillByUniqueCode(uniqueCode, bigBatchInfos, 1, sickerUserVo.getExecDeptId());
    }

    @Override
    public void createFillBillByUniqueCode(List<String> uniqueCode, Integer flag) throws Exception {
        if (uniqueCode.size() == 0) {
            throw new Exception("唯一码不能为空");
        }
        List<OutStockUniqueCode> outUniqueCodes = billMgrService.fillOutStockBill(uniqueCode, flag, null);
        instockSer.addFillInstockBill(uniqueCode, outUniqueCodes, flag, null);
    }

    @Override
    public void createFillBillByUniqueCode(List<String> uniqueCode, List<BigBatch4FillVo> bigBatchInfos, Integer flag, String execDeptIt) throws Exception {
        Assert.isTrue(CollectionUtils.isNotEmpty(uniqueCode) && CollectionUtils.isNotEmpty(bigBatchInfos), "唯一码或大批次不能为空");
        List<OutStockUniqueCode> outUniqueCodes = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(uniqueCode)) {
            outUniqueCodes.addAll(billMgrService.fillOutStockBill(uniqueCode, flag, execDeptIt));
        }
        List<EntireOutStockListVo> outStockBatchs = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(bigBatchInfos)) {
            outStockBatchs.addAll(billMgrService.fillOutStockBillByBigBatch(bigBatchInfos, flag, execDeptIt));
        }
        if (CollectionUtils.isNotEmpty(uniqueCode)) {
            instockSer.addFillInstockBill(uniqueCode, outUniqueCodes, flag, execDeptIt);
        }
        if (CollectionUtils.isNotEmpty(bigBatchInfos)) {
          instockSer.addFillInstockBillByBigBatchCode(bigBatchInfos,outStockBatchs, flag, execDeptIt);
        }
    }
}
