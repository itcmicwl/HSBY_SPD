package gyqx.spdherp.stockout.service.impl;

import common.db.SimpleDao;
import common.transform.Tx;
import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import gyqx.spdherp.po.*;
import gyqx.spdherp.stockout.dao.RequestOutDao;
import gyqx.spdherp.stockout.service.SurReturnOutService;
import gyqx.spdherp.stockout.vo.*;
import gyqx.spdherp.surgery.service.ISurgeryPkgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class SurReturnOutServiceImpl implements SurReturnOutService {
    private static Logger logger = LoggerFactory.getLogger(SurReturnOutServiceImpl.class);
    @Resource
    private UtilsContext utilsContext;
    @Resource
    private SimpleDao simpleDao;
    @Resource
    private RequestOutDao requestOutDao;
    @Autowired
    private ISurgeryPkgService surPkgSer;
    @Override
    public String saveSurReturnBill(OutStockBillVo outStockBillVo) throws Exception {
        OutStock outStockBill = new OutStock();
        Tx.transform(outStockBillVo).to(outStockBill);
        outStockBill= updateOutStockBillInfo(outStockBill);
        simpleDao.insert(outStockBill);  //出库单主表插入
        List<OutStockList> outStockLists = new ArrayList<>();
        List<OutStockBatch> outStockBatches = new ArrayList<>();
        List<OutStockUniqueCode> outStockUniqueCodeList = new ArrayList<>();
        List<HosTakingStock> hosTakingStocks = new ArrayList<>();
        // 以商品ID，批号，效期，灭菌效期，注册证号，货位进行分组
        Map<RequestStockGoodsVo, List<RequestStockGoodsVo>> goodsMap = outStockBillVo.getGoodsList().stream().collect(Collectors.groupingBy(o -> o));
        int outBillRow = 0;
        for (Map.Entry<RequestStockGoodsVo, List<RequestStockGoodsVo>> entrySet : goodsMap.entrySet()) {
            RequestStockGoodsVo key = entrySet.getKey();
            List<HosTakingStock> takingLst;

            //构建出库单子表
            OutStockList outStockBillSub =  new OutStockList();
            Tx.transform(key).to(outStockBillSub);
            outStockBillSub =cvt2SubObj(outStockBillSub,++outBillRow,outStockBill,key);
            outStockLists.add(outStockBillSub);
            //构建唯一码或批次
            for (Iterator<RequestStockGoodsVo> it = entrySet.getValue().iterator(); it.hasNext(); ) {
                RequestStockGoodsVo vo = it.next();
                if(StringUtils.hasText(vo.getUniqueCode())){  //唯一码管理，构建OutStockUniqueCode
                    OutStockUniqueCode u= cvt2UniqueCodeObj(outStockBillSub,  vo);
                    outStockUniqueCodeList.add(u);
                }
                else{//非唯一码管理，构建outStockBatch
                    OutStockBatch b  =cvt2BatchObj( outStockBillSub,  vo);
                    outStockBatches.add(b);
                }
            }
        }

        //插入出库明细表，批次，唯一码表
        //修改库存占用表
      List<String> surIds=  outStockBillVo.getGoodsList().stream().map(RequestStockGoodsVo::getSourceBillId).distinct().collect(Collectors.toList());
        StringBuffer wheresql =  new StringBuffer().append(" UPDATE hos_taking_stock set  sur_pkg_id = bill_id ,bill_id =? where bill_id in (");
        surIds.forEach(surId->{
            wheresql.append("'"+surId+"',");
        });
        String sql  =wheresql.toString();
        sql =sql.substring(0,sql.length()-1)+")";

        simpleDao.executeSql(sql,outStockBill.getBillId());
        requestOutDao.insertOutStockBill(outStockLists, outStockBatches, outStockUniqueCodeList, hosTakingStocks);
        this.setSurPkgStatus(outStockBillVo);
        return "ok";
    }
    private OutStock updateOutStockBillInfo(OutStock outStockBill) {
        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_id");
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        outStockBill.setId(id);
        outStockBill.setSourceBillId(outStockBill.getBillId());
        outStockBill.setBillId(id);
        outStockBill.setFiller(user.getUserId());
        outStockBill.setFillDate(new Date());
    //   outStockBill.setOutOrgName(user.getCorpName());
      //  IdNameVo deptInfo = requestOutDao.getDeptInfoByStockId(user.getCorpId(), outStockBill.getOutStocId());
      //  outStockBill.setOutDeptId(deptInfo.getId());
       // outStockBill.setOutDeptName(deptInfo.getName());
       // outStockBill.setInOrgId(user.getCorpId());
      //  outStockBill.setInOrgName(user.getCorpName());
        outStockBill.setVersion(0);
        outStockBill.setLastUpdateDatetime(new Date());
        return outStockBill;
    }
    private OutStockBatch cvt2BatchObj(OutStockList outStockBillSub, RequestStockGoodsVo vo) {
        OutStockBatch outStockBatch = new OutStockBatch();
        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_batch_id");
        outStockBatch.setId(id);
        outStockBatch.setPid(outStockBillSub.getId());
        outStockBatch.setBillId(outStockBillSub.getBillId());
        outStockBatch.setPRowId(outStockBillSub.getOutBillRow());
        outStockBatch.setProvId(outStockBillSub.getProvId());
        outStockBatch.setGoodsId(outStockBillSub.getGoodsId());
        outStockBatch.setGoodsBatchId(vo.getBatchId());
        outStockBatch.setBigBatchCode(vo.getBigBatchCode());
        outStockBatch.setInPrice(new BigDecimal(vo.getPrice() == null ? 0 : vo.getPrice()));
        outStockBatch.setInTime(new Date());
        outStockBatch.setQty(new BigDecimal(vo.getQty()));
        outStockBatch.setVersion(0);
        return outStockBatch;
    }

    private OutStockUniqueCode cvt2UniqueCodeObj(OutStockList outStockSub, RequestStockGoodsVo requestStockGoodsVo) {
        OutStockUniqueCode outStockUniqueCode = new OutStockUniqueCode();
        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_unique_code_id");
        outStockUniqueCode.setId(id);
        outStockUniqueCode.setPid(outStockSub.getId());
        outStockUniqueCode.setBillId(outStockSub.getBillId());
        outStockUniqueCode.setPRowId(outStockSub.getOutBillRow());
        outStockUniqueCode.setProvId(outStockSub.getProvId());
        outStockUniqueCode.setGoodsId(outStockSub.getGoodsId());
        outStockUniqueCode.setGoodsBatchId(requestStockGoodsVo.getBatchId());
        outStockUniqueCode.setInPrice(new BigDecimal(requestStockGoodsVo.getPrice()));
        outStockUniqueCode.setInTime(new Date());
        outStockUniqueCode.setUniqueCode(requestStockGoodsVo.getUniqueCode());
        outStockUniqueCode.setQty(BigDecimal.ONE);
        outStockUniqueCode.setVersion(0);

//        outStockUniqueCode.setLastUpdateDatetime(new Date());

        return outStockUniqueCode;
    }

    private OutStockList cvt2SubObj(OutStockList outStockSub, int outBillRow, OutStock outStockBill, RequestStockGoodsVo requestStockGoodsVo) {
        String id = utilsContext.getUserStateUtils().getCurrent().getCorpId() +
                utilsContext.getSysAtomUtil().newValue("out_stock_list_id");
        outStockSub.setId(id);
        outStockSub.setGoodsId(requestStockGoodsVo.getGoodsId());
        outStockSub.setSourceBillId(requestStockGoodsVo.getSourceBillId());
        outStockSub.setPid(outStockBill.getId());
        outStockSub.setBillId(outStockBill.getBillId());
        outStockSub.setOutBillRow(outBillRow);
        outStockSub.setOutQty(new BigDecimal(requestStockGoodsVo.getQty()));
       // outStockSub.setShelfId(hosStockpile.getShelfCode());

        if (StringUtils.hasText(requestStockGoodsVo.getBatchNo()))
            outStockSub.setBatchCode(requestStockGoodsVo.getBatchNo());
        if (StringUtils.hasText(requestStockGoodsVo.getCertificateCode()))
            outStockSub.setCertificateCode(requestStockGoodsVo.getCertificateCode());
        if (requestStockGoodsVo.getSterilizationDate() != null)
            outStockSub.setSterilizationDate(requestStockGoodsVo.getSterilizationDate());
        if (StringUtils.hasText(requestStockGoodsVo.getSterilizationCode()))
            outStockSub.setSterilizationCode(requestStockGoodsVo.getSterilizationCode());
        if (requestStockGoodsVo.getSterilizationEndDate() != null)
            outStockSub.setSterilizationEndDate(requestStockGoodsVo.getSterilizationEndDate());
        if (requestStockGoodsVo.getExpdtEndDate() != null)
            outStockSub.setExpdtEndDate(requestStockGoodsVo.getExpdtEndDate());
        outStockSub.setSurCode(requestStockGoodsVo.getSourceBillId());
//        if (StringUtils.hasText(requestStockGoodsVo.getBarcode()))
//            outStockSub.setMasterCode(requestStockGoodsVo.getBarcode());
//        if (StringUtils.hasText(requestStockGoodsVo.getSecCode()))
//            outStockSub.setSecCode(requestStockGoodsVo.getSecCode());
//        if (StringUtils.hasText(requestStockGoodsVo.getHibcCode()))
//            outStockSub.setHibcCode(requestStockGoodsVo.getHibcCode());

        outStockSub.setStatus(0);
        outStockSub.setVersion(0);

//        outStockSub.setLastUpdateDatetime(new Date());

        return outStockSub;
    }
    private void setSurPkgStatus(OutStockBillVo outStockBillVo)throws Exception{
        List<BigBatch4FillVo> lstSurInfo = outStockBillVo.getGoodsList().stream().map(o->{
            BigBatch4FillVo bb4f = new BigBatch4FillVo();
            bb4f.setSurCode(o.getSourceBillId());
            bb4f.setQty(new BigDecimal(o.getQty()));
            if(StringUtils.hasText(o.getUniqueCode())){
                bb4f.setBigBatchCode(o.getUniqueCode());
            }else {
                bb4f.setBigBatchCode(o.getBigBatchCode());
            }
            return bb4f;
        }).collect(Collectors.toList());
        surPkgSer.setSurStatus(lstSurInfo,"手术包退货");
    }


}
