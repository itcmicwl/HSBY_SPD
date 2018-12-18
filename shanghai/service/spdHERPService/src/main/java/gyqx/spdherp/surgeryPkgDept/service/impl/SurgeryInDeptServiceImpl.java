package gyqx.spdherp.surgeryPkgDept.service.impl;

import com.mysql.jdbc.StringUtils;
import common.db.SimpleDao;
import common.exception.ParameterException;
import common.exception.ValidateException;
import common.transform.Tx;
import common.utils.SysAtomUtil;
import common.utils.UtilsContext;
import gyqx.spdherp.applyMgr.service.IDeptBuySubService;
import gyqx.spdherp.po.*;
import gyqx.spdherp.stockMgr.vo.*;
import gyqx.spdherp.stockout.dao.RequestOutDao;
import gyqx.spdherp.surgery.constant.Constants;
import gyqx.spdherp.surgeryPkgDept.dao.SurgeryDeptDao;
import gyqx.spdherp.surgeryPkgDept.dao.SurgeryInDeptDao;
import gyqx.spdherp.surgeryPkgDept.service.ISurgeryDeptService;
import gyqx.spdherp.surgeryPkgDept.service.ISurgeryInDeptService;
import gyqx.spdherp.surgeryPkgDept.vo.*;
import gyqx.spdherp.surgeryPkgDept.vo.InStockListVo;
import gyqx.spdherp.surgeryPkgDept.vo.InStockVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static gyqx.spdherp.stockMgr.Constance.INSTOCK_STATUS_CHARGE;
import static gyqx.spdherp.stockout.Constance.OUTSTOCKBILL_STATUS_INSTOCK;
import static gyqx.spdherp.stockout.Constance.OUTSTOCKBILL_STATUS_PART_INSTOCK;

@Service
public class SurgeryInDeptServiceImpl implements ISurgeryInDeptService {
    @Resource
    private UtilsContext utilsContext;
    @Resource
    private SurgeryInDeptDao dao;
    @Resource
    private SimpleDao simpleDao;
    @Resource
    private SysAtomUtil atomUtil;
    @Resource
    private IDeptBuySubService deptSubService;
    @Override
    public InDeptSurInfoVo findAllSurInfo(InDeptSurInfoVo qBean) throws Exception {
        qBean.setStatus(Constants.SUR_PKG_STATUS_KSCK);
        InDeptSurInfoVo inSurgeryInfo = dao.getSurgeryInfo(qBean);
        if (inSurgeryInfo != null) {
            inSurgeryInfo.setSurInfoList(dao.findSurgeryList(qBean));
            inSurgeryInfo.getSurInfoList().forEach(o->{
                o.setSurInfoBatchList(dao.findSurgeryBatch(o));
                o.setSurInfoUniqueList(dao.findSurgeryUnique(o));
            });
        }
        return inSurgeryInfo;
    }

    @Override
    /**
     * 手术包请购入库
     */
    @Transactional
    public InStock add(InStockVo inStockVo) throws Exception {
        //分批次入库的情况下，先去除对象中批次和唯一码均为空的对象
        List<InStockListVo> inStockListVoss = new ArrayList<>();
        inStockVo.getLstDetail().forEach(r->{
            if(r.getLstBatch().size()==0&&r.getLstUniqueCode().size()==0){
                inStockListVoss.add(r);
            }
        });
        inStockVo.getLstDetail().removeAll(inStockListVoss);
        //批次表中重复行合并
        inStockVo.getLstDetail().forEach(l->{
            List<InStockBatchVo> inStockBatchVos = new ArrayList<>();
            Map<String, List<InStockBatchVo>> listMap =
                    l.getLstBatch().parallelStream().collect(Collectors.groupingBy(o -> o.getGoodsId() + o.getGoodsBatchId()));
            for (Map.Entry<String, List<InStockBatchVo>> entry : listMap.entrySet()){
                if(entry.getValue().size()>1){
                    entry.getValue().get(0).setQty(entry.getValue().parallelStream().map(InStockBatchVo::getQty).reduce(new BigDecimal("0.0"),BigDecimal::add));
                }
                inStockBatchVos.add(entry.getValue().get(0));
            }
            l.setLstBatch(inStockBatchVos);
        });
        this.checkBill(inStockVo);
        String billId = atomUtil.newId("cgInStock").prefix(inStockVo.getInOrgId()).get();//atomUtil.newValue("cgInStock");
        inStockVo.setBillId(billId);
        inStockVo.setId(billId);
        inStockVo.setLastUpdateDatetime(new Date());
        inStockVo.setStatus(INSTOCK_STATUS_CHARGE);
        InStock inStock = new InStock();
        List<InStockList> inStockLists = new ArrayList<>();
        List<InStockBatch> inStockBatches = new ArrayList<>();
        List<InStockUniqueCode> inStockUniqueCodeocks = new ArrayList<>();
        Tx.transform(inStockVo).to(inStock);
        simpleDao.insert(inStock);
        List<InStockListVo> lstDetail = inStockVo.getLstDetail();
        for (InStockListVo ls : lstDetail) {
            String detailId = atomUtil.newId("cgInStockDetail").prefix(inStock.getInOrgId()).get();//atomUtil.newValue("cgInStockDetail");
            ls.setId(detailId);
            ls.setPid(billId);
            ls.setBillId(inStock.getBillId());
            InStockList inStockList = new InStockList();
            Tx.transform(ls).to(inStockList);
            inStockLists.add(inStockList);
            if (ls.getLstBatch().size() > 0) {
                for (InStockBatchVo isb : ls.getLstBatch()) {
                    isb.setPid(ls.getId());
                    isb.setBillId(inStock.getBillId());
                    isb.setPRowId(ls.getInBillRow());
                    InStockBatch inStockBatch = new InStockBatch();
                    Tx.transform(isb).to(inStockBatch);
                    inStockBatches.add(inStockBatch);
                }
            }
            if (ls.getLstUniqueCode().size() > 0) {
                for (InStockUniqueCodeVo isuc : ls.getLstUniqueCode()) {
                    isuc.setId(isuc.getUniqueCode());
                    isuc.setPid(ls.getId());
                    isuc.setBillId(inStock.getBillId());
                    isuc.setPRowId(ls.getInBillRow());
                    InStockUniqueCode inStockUniqueCode = new InStockUniqueCode();
                    Tx.transform(isuc).to(inStockUniqueCode);
                    inStockUniqueCodeocks.add(inStockUniqueCode);
                }
            }
        }
        if(dao.insert(inStockLists, inStockBatches, inStockUniqueCodeocks)<0){
            throw new Exception("插入入库单相关表失败!");
        }
        //回写各单据状态
        writerBackBillStatus(inStockVo, inStock, inStockBatches, inStockUniqueCodeocks);
        return inStock;
    }

    public void writerBackBillStatus(InStockVo inStockVo, InStock inStock, List<InStockBatch> inStockBatches, List<InStockUniqueCode> inStockUniqueCodeocks) throws Exception {
        //回写出库单子表的已出库数量以及出库单主表状态
        String sql = "UPDATE out_stock_batch set use_qty = use_qty + ?,version = version+1,last_update_datetime = NOW() " +
                "where bill_id = ? and goods_id=? and goods_batch_id =? AND p_row_id = ? ";
        for (InStockBatch r : inStockBatches) {
            simpleDao.executeSql(sql, r.getQty(), inStock.getOutBillId(), r.getGoodsId(), r.getGoodsBatchId(), r.getPRowId());
        }
        String sqlU = "UPDATE out_stock_unique_code set is_used = 1,version = version+1,last_update_datetime = NOW() " +
                "where bill_id = ? and unique_code = ?";
        inStockUniqueCodeocks.forEach(u->simpleDao.executeSql(sqlU,inStock.getOutBillId(),u.getUniqueCode()));
        //更新请购出库单主表状态
        //Optional<InStockList> any = inStockLists.parallelStream().filter(l -> l.getInQty().compareTo(l.getOutQty()) == -1).findAny();
        List<OutStockBatch> outStockBatches = dao.listBatchByBillId(inStock.getOutBillId());
        List<OutStockUniqueCode> outStockUniqueCodes = dao.listUniByBillId(inStock.getOutBillId());
        String sqlMain = "UPDATE out_stock set STATUS = ?,version = version+1,last_update_datetime=NOW() where bill_id = ?";
        if(outStockBatches.parallelStream().map(o -> o.getQty().subtract(o.getUseQty())).anyMatch(r -> r.compareTo(BigDecimal.ZERO) == 1)||
                outStockUniqueCodes.parallelStream().anyMatch(r->r.getIsUsed()==0)){
            simpleDao.executeSql(sqlMain,OUTSTOCKBILL_STATUS_PART_INSTOCK,inStock.getOutBillId());
        }else {
            simpleDao.executeSql(sqlMain,OUTSTOCKBILL_STATUS_INSTOCK,inStock.getOutBillId());
        }
        //回写手术包的所在库房信息
        String sqlSur = "UPDATE surgery_pkg set cur_stock_id = ?,status=?,version = version+1," +
                "last_update_datetime = NOW() where sur_code = ?";
        for (String s : inStockVo.getBarcodeList()) {
            simpleDao.executeSql(sqlSur, inStock.getInStocId(), Constants.SUR_PKG_STATUS_QGRK, s);
            PkgLog pkgLog = addPkgLog(inStock, s);
            simpleDao.insert(pkgLog);
        }
        //回写请购单主表的状态
        if(null!=inStockVo.getSourceBillId()){
            deptSubService.updateMainStateByBillId(inStockVo.getSourceBillId());
        }
    }

    public PkgLog addPkgLog(InStock inStock, String s) {
        //写手术包日志表
        PkgLog pkgLog = new PkgLog();
        pkgLog.setBillId(inStock.getBillId());
        pkgLog.setId(UUID.randomUUID().toString().replace("-", ""));
        pkgLog.setCode(s);
        //手术包类型 0:手术包 1:定数包
        pkgLog.setPackageKind(0);
        pkgLog.setDescription("手术包请购入库");
        pkgLog.setFiller(utilsContext.getUserStateUtils().getCurrent().getEname());
        pkgLog.setFillDate(new Date());
        pkgLog.setLastUpdateDatetime(new Date());
        pkgLog.setVersion(0);
        return pkgLog;
    }

    private void checkBill(InStockVo inStock) throws Exception{
        if(StringUtils.isNullOrEmpty(inStock.getInStocId())){
            ValidateException.Throw("stocId","入库库房不能为空","");
        }
        if(inStock.getLstDetail().size() == 0){
            ValidateException.Throw("lstDetail","明细行不能为空","");
        }
        for (InStockListVo inStockListVo: inStock.getLstDetail()) {
            if(inStockListVo.getInQty() == null || inStockListVo.getInQty().compareTo(new BigDecimal(0)) == 0){
                ValidateException.Throw("hosPurQty",String.format("第%d行：%s的数量必须大于零",inStockListVo.getInBillRow(),inStockListVo.getGoodsName()),"");
            }
        }
    }
}
