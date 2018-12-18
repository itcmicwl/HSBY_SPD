package gyqx.spdherp.stockout.service.impl;

import common.db.SimpleDao;
import common.db.query.QueryResult;
import common.transform.Tx;
import common.utils.PageUtils;
import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import gyqx.spdherp.basedatamaint.controller.BarcodeParseController;
import gyqx.spdherp.basedatamaint.service.BarcodeParseService;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockList;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.dao.ReturnOutDao;
import gyqx.spdherp.stockout.service.ReturnOutService;
import gyqx.spdherp.stockout.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static gyqx.spdherp.basedatamaint.controller.BarcodeParseController.PACKAGE_PREFIX;
import static gyqx.spdherp.basedatamaint.controller.BarcodeParseController.UNIQUE_PREFIX;

@Service
public class ReturnOutServiceImpl implements ReturnOutService {
    private static Logger logger = LoggerFactory.getLogger(ReturnOutServiceImpl.class);
    @Resource
    private ReturnOutDao returnOutDao;

    @Resource
    private UtilsContext utilsContext;

    @Resource
    private BarcodeParseService barcodeParseService;

    @Resource
    private SimpleDao simpleDao;

    @Override
    public QueryResult<StockpileHzVo> listStockGoods(String stockId, Integer stocKind, String filter, int pageNum, int pageSize) {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        PageUtils.startPage(pageNum, pageSize);
        return PageUtils.endPage(returnOutDao.listStockGoods(hosId, stockId, stocKind, filter));
    }

    @Override
    public List<IdNameVo> listOrgs() {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        return returnOutDao.listOrgsByHosId(user.getCorpId(), user.getOrgId());
    }

    @Override
    public BarcodeResultSetVo parseBarcoe(String stockId, Integer stocKind, String masterBarcode, String slaverBarcode) throws ParseException {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        BarcodeResultSetVo result = new BarcodeResultSetVo();
        if (masterBarcode.length() == BarcodeParseController.PACKAGE_ID_LENGTH && masterBarcode.toLowerCase().startsWith(PACKAGE_PREFIX)) {
            // 定数包
            result.setBarcodeParseResults(barcodeParseService.parseHosPackageInfo(masterBarcode));
            result.setStockpile(returnOutDao.listStockGoodsByGoodsIds(hosId, stockId, stocKind, result.getBarcodeParseResults()));
        } else if (masterBarcode.length() == BarcodeParseController.UNIQUE_BARCODE_LENGTH && masterBarcode.toLowerCase().startsWith(UNIQUE_PREFIX)) {
            // 唯一码
            result.setStockpile(returnOutDao.listStockGoodsByUniqueCode(hosId, stockId, stocKind, masterBarcode));
        } else {
            // 其它条码情况
            result.setBarcodeParseResults(barcodeParseService.parseBarcodeHERP(masterBarcode, slaverBarcode));
            result.setStockpile(returnOutDao.listStockGoodsByGoodsIds(hosId, stockId, stocKind, result.getBarcodeParseResults()));
        }
        logger.info(result.getStockpile().toString());
        return result;
    }

    @Override
    public OutStock getAllInfoOutBill(InStockVo inStock) throws Exception {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        OutStockBillSupVo outStockBillSupVo = returnOutDao.getAllInfoOutBill(inStock.getOutBillId());
        OutStock outStock = new OutStock();
        Tx.transform(outStockBillSupVo).to(outStock);
        String id = user.getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_id");
        outStock.setId(id);
        outStock.setBillId(id);
        outStock.setFiller(user.getUserId());
        outStock.setFillDate(new Date());
        outStock.setAuditor(user.getUserId());
        outStock.setAuditDate(new Date());
        outStock.setAccounter(user.getUserId());
        outStock.setAccountDate(new Date());
        outStock.setOutOrgId(user.getCorpId());
        outStock.setOutOrgName(user.getCorpName());
        outStock.setOutDeptId(inStock.getInDeptId());
        outStock.setOutDeptName(inStock.getInDeptName());
        outStock.setOutStocId(inStock.getInStocId());
        outStock.setInOrgId(null);
        outStock.setInOrgName(null);
        outStock.setInDeptId(null);
        outStock.setInDeptName(null);
        outStock.setInStocId(null);
        outStock.setLastUpdateDatetime(new Date());
        outStock.setVersion(0);
        //插入出库单主表
        simpleDao.insert(outStock);
        List<OutStockBillListSupVo> outStockBillListSupVos = outStockBillSupVo.getlOutStockList();
        if (outStockBillListSupVos != null && outStockBillListSupVos.size() > 0) {
            OutStockList outStockList = new OutStockList();
            for (OutStockBillListSupVo osl : outStockBillListSupVos) {
                Tx.transform(osl).to(outStockList);
                String idListStock = user.getCorpId() +
                        utilsContext.getSysAtomUtil().newValue("out_stock_list_id");
                outStockList.setId(idListStock);
                outStockList.setPid(id);
                outStockList.setBillId(id);
                outStockList.setVersion(0);
                outStockList.setLastUpdateDatetime(new Date());
                simpleDao.insert(outStockList);

                if (osl.getlOutStockBatch() != null && osl.getlOutStockBatch().size() > 0) {
                    OutStockBatch outStockBatch = new OutStockBatch();
                    for (OutStockBatch osb : osl.getlOutStockBatch()) {
                        Tx.transform(osb).to(outStockBatch);
                        String idStockBatch = user.getCorpId() +
                                utilsContext.getSysAtomUtil().newValue("out_stock_batch_id");
                        outStockBatch.setId(idStockBatch);
                        outStockBatch.setPid(idListStock);
                        outStockBatch.setBillId(id);
                        outStockBatch.setVersion(0);
                        outStockBatch.setLastUpdateDatetime(new Date());
                        simpleDao.insert(outStockBatch);
                    }
                }

                if (osl.getlOutStockUniqueCode() != null && osl.getlOutStockUniqueCode().size() > 0) {
                    OutStockUniqueCode outStockUniqueCode = new OutStockUniqueCode();
                    for (OutStockUniqueCode osuc : osl.getlOutStockUniqueCode()) {
                        Tx.transform(osuc).to(outStockUniqueCode);
                        String idStockUniqueCode = user.getCorpId() +
                                utilsContext.getSysAtomUtil().newValue("out_stock_unique_code_id");
                        outStockUniqueCode.setId(idStockUniqueCode);
                        outStockUniqueCode.setPid(idListStock);
                        outStockUniqueCode.setBillId(id);
                        outStockUniqueCode.setVersion(0);
                        outStockUniqueCode.setQty(BigDecimal.valueOf(1));
                        outStockUniqueCode.setLastUpdateDatetime(new Date());
                        simpleDao.insert(outStockUniqueCode);
                    }
                }
            }
        }
        return outStock;
    }

    @Override
    public void modifyQty(OutStock outStock) throws Exception {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        OutStockBillSupVo outStockBillSupVo = returnOutDao.getAllInfoOutBill(outStock.getBillId());
        String sql = "UPDATE hos_stockpile set qty = 0 where id = ?";
        List<OutStockBillListSupVo> outStockBillListSupVos = outStockBillSupVo.getlOutStockList();
        for(OutStockBillListSupVo osl : outStockBillListSupVos){
            if (osl.getlOutStockUniqueCode() != null && osl.getlOutStockUniqueCode().size() > 0) {
                for(OutStockUniqueCode osuc : osl.getlOutStockUniqueCode()){
                    String id = returnOutDao.modifyQty(osuc.getUniqueCode(),hosId);
                    simpleDao.executeSql(sql,id);
                }
            }
        }
    }
}
