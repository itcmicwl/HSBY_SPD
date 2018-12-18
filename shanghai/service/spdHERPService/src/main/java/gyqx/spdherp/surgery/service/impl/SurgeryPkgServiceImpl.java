package gyqx.spdherp.surgery.service.impl;

import common.utils.SysAtomUtil;
import common.utils.UtilsContext;
import gyqx.spdherp.applyMgr.dao.DeptBuyMainDao;
import gyqx.spdherp.applyMgr.service.IApplySurgeryPkgService;
import gyqx.spdherp.applyMgr.service.impl.ApplySurgeryPkgServiceImpl;
import gyqx.spdherp.applyMgr.vo.ApplySurPkgVo;
import gyqx.spdherp.applyMgr.vo.ApplySurgeryPkgVo;
import gyqx.spdherp.po.HosTakingStock;
import gyqx.spdherp.po.SurgeryPkg;
import gyqx.spdherp.po.SurgeryPkgBatch;
import gyqx.spdherp.po.SurgeryPkgList;
import gyqx.spdherp.stockPile.service.IHosStockpileService;
import gyqx.spdherp.stockPile.vo.HosStockPileVo;
import gyqx.spdherp.stockout.vo.BigBatch4FillVo;
import gyqx.spdherp.stockout.vo.SickerUserListVo;
import gyqx.spdherp.surgery.constant.Constants;
import gyqx.spdherp.surgery.dao.PkgLogDao;
import gyqx.spdherp.surgery.dao.SurgeryPkgBatchDao;
import gyqx.spdherp.surgery.dao.SurgeryPkgListDao;
import gyqx.spdherp.surgery.vo.*;
import gyqx.spdherp.surgery.dao.SurgeryPkgDao;
import gyqx.spdherp.surgery.service.ISurgeryPkgService;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 手术包订单主表(SurgeryPkg)表服务实现类
 *
 * @author moonbless
 * @since 2018-09-29 14:37:11
 */
@Service("surgeryPkgService")
public class SurgeryPkgServiceImpl implements ISurgeryPkgService {
    @Autowired
    private SurgeryPkgDao surgeryPkgDao;
    @Autowired
    private SurgeryPkgBatchDao surBatchDao;
    @Autowired
    private SurgeryPkgListDao surgeryPkgListDao;
    @Autowired
    private IHosStockpileService hosStockSer;
    @Autowired
    private SysAtomUtil atomUtil;
    @Autowired
    private PkgLogDao pkgLogDao;
    @Autowired
    private IApplySurgeryPkgService asPkgService;
    @Autowired
    private UtilsContext utilsContext;
    @Autowired
    private UtilsContext utils;
    @Autowired
    private DeptBuyMainDao dao;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SurgeryPkgVo getById(String id) {
        return this.surgeryPkgDao.getById(id);
    }
    
    /**
     * 查询
     *
     * @return surgeryPkgVo 对象实例
     */
    @Override
    public List<SurgeryPkgVo> list(SurgeryPkgVo surgeryPkgVo) {
        return this.surgeryPkgDao.list(surgeryPkgVo);
    }

    @Override
    public List<SurgeryPkgVo> listVo(SurgeryPkgVo surgeryPkgVo) {
       return this.surgeryPkgDao.listVo(surgeryPkgVo);
    }

    /**
     * 分页查询
     *
     * @return query对象
     */
    @Override
    public QueryResult<SurgeryPkgVo> listPage(QueryInfo<SurgeryPkgVo> query) {
        PageUtils.startPage(query);
        return PageUtils.endPage(this.surgeryPkgDao.list(query.getQueryObject()));
    }

    /**
     * 新增数据
     *
     * @param surgeryPkgVo 实例对象
     * @return 实例对象
     */
    @Override
    public SurgeryPkgVo insert(SurgeryPkgVo surgeryPkgVo) {
        this.surgeryPkgDao.insert(surgeryPkgVo);
        return surgeryPkgVo;
    }
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insertByBatch(List<SurgeryPkgVo> lst) {
        return this.surgeryPkgDao.insertByBatch(lst);
    }
    /**
     * 修改数据
     *
     * @param surgeryPkgVo 实例对象
     * @return 实例对象
     */
    @Override
    public SurgeryPkgVo update(SurgeryPkgVo surgeryPkgVo) {
        this.surgeryPkgDao.update(surgeryPkgVo);
        return this.getById(surgeryPkgVo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.surgeryPkgDao.deleteById(id);
    }

//    @Override
//    public List<SurgeryPkgVo> SurPack(List<SurgeryPkgVo> surInfo) throws Exception {
//        List<SurgeryPkgListVo> lstSurDetail = new ArrayList<>();
//        List<SurgeryPkgBatchVo> lstGoodsBatch= new ArrayList<>();
//        List<PkgLogVo> lstLog = new ArrayList<>();
//        List<ApplySurgeryPkgVo> lstASur = new ArrayList<>();
//        List<HosTakingStock> lstHosTaking = new ArrayList<>();
//        for (SurgeryPkgVo surgeryPkgVo : surInfo) {
//            surgeryPkgVo.setId(atomUtil.newId("surPkgId").prefix(surgeryPkgVo.getHosId()).get());
//            surgeryPkgVo.setSurCode(surgeryPkgVo.getId());
//            surgeryPkgVo.setStatus(Constants.SUR_PKG_STATUS_DABAO);
//            //处理打包日志表
//            PkgLogVo plog = new PkgLogVo();
//            plog.setBillId(surgeryPkgVo.getId());
//            plog.setCode(surgeryPkgVo.getId());
//            plog.setDescription("打包");
//            plog.setPackageKind(0);
//            plog.setFiller(surgeryPkgVo.getPacker());
//            lstLog.add(plog);
//            //准备回写请购单打包表数据
//            ApplySurgeryPkgVo asp = new ApplySurgeryPkgVo();
//            asp.setBillId(surgeryPkgVo.getApplyBillId());
//            asp.setSurId(surgeryPkgVo.getSurId());
//            asp.setQty(new BigDecimal(1));
//            lstASur.add(asp);
//            int index =0;
//            for (SurgeryPkgListVo detailVo : surgeryPkgVo.getSurgeryPkgListVos()) {
//                detailVo.setId(atomUtil.newId("surPkgDetailId").prefix(surgeryPkgVo.getHosId()).get());
//                detailVo.setSurCode(surgeryPkgVo.getId());
//                detailVo.setStatus(Constants.SUR_PKG_DETAIL_STATUS_SOME);
//                lstSurDetail.add(detailVo);
//                index++;
//                for (SurgeryPkgBatchVo goodsBatch : detailVo.getLstGoodsBatch()) {
//                    goodsBatch.setSurCode(surgeryPkgVo.getId());
//                    lstGoodsBatch.add(goodsBatch);
//
//                    HosTakingStock hosTakingStock = new HosTakingStock();//处理库存占用
//                    hosTakingStock.setId(utilsContext.getSysAtomUtil().newValue("hos_taking_stock_id"));
//                    hosTakingStock.setBillId(surgeryPkgVo.getId());
//                    hosTakingStock.setBillRownum(index);
//                    hosTakingStock.setHosGoodsId(detailVo.getGoodsId());
//                    hosTakingStock.setHosId(surgeryPkgVo.getHosId());
//                    hosTakingStock.setKind(Constants.HOS_TAKING_KIND_SUR_PKG);
//                    hosTakingStock.setQty(goodsBatch.getQty());
//                    hosTakingStock.setStocId(surgeryPkgVo.getCurStockId());
//                    hosTakingStock.setStockpileId(goodsBatch.getStoreId());
//                    lstHosTaking.add(hosTakingStock);
//                }
//            }
//            surBatchDao.insertHosTakingStock(lstHosTaking);//占用库存
//            asPkgService.setSurQtyByPack(lstASur);//回写请购单打包表
//            surBatchDao.insertByBatch(lstGoodsBatch);
//            surgeryPkgListDao.insertByBatch(lstSurDetail);
//            surgeryPkgDao.insertByBatch(surInfo);
//            pkgLogDao.insertByBatch(lstLog);
//        }
//        return surInfo;
//    }
    @Override
    public List<SurPackResVo> updateSurPack(List<SurgeryPkgVo> surInfo) throws Exception {
        List<SurPackResVo> res = new ArrayList<>();
        List<SurgeryPkgBatchVo> lstGoodsBatch= new ArrayList<>();
        List<PkgLogVo> lstLog = new ArrayList<>();
        List<ApplySurgeryPkgVo> lstASur = new ArrayList<>();
        List<HosTakingStock> lstHosTaking = new ArrayList<>();
        List<SurgeryPkgVo> lstSurgeryPkgVo = new ArrayList<>();
        for (SurgeryPkgVo surgeryPkgVo : surInfo) {
            if(surgeryPkgVo.getStatus()>Constants.SUR_PKG_STATUS_JIANHUO){ //此包已打过包
                continue;
            }
            surgeryPkgVo.setStatus(Constants.SUR_PKG_STATUS_DABAO);
            int index =0;
            boolean flag = true; //标记此包能否打包
            List<SurgeryPkgBatchVo> lstGoodsBatchDetail= new ArrayList<>();
            List<HosTakingStock> lstHosTakingDetail = new ArrayList<>();
            for (SurgeryPkgListVo detailVo : surgeryPkgVo.getSurgeryPkgListVos()) {
                index++;
                BigDecimal totalQty = detailVo.getLstGoodsBatch().stream().map(item -> item.getQty()).reduce((cur, next) -> cur.add(next)).get();
                if (totalQty.compareTo(detailVo.getQty()) != 0) {//数量不一致不能打包
                    flag = false;
                    break;
                }
                for (SurgeryPkgBatchVo goodsBatch : detailVo.getLstGoodsBatch()) {
                    totalQty.add(goodsBatch.getQty());
                    goodsBatch.setSurCode(surgeryPkgVo.getId());
                    lstGoodsBatchDetail.add(goodsBatch);
                    //处理库存占用
                    HosTakingStock hosTakingStock = new HosTakingStock();
                    hosTakingStock.setId(utilsContext.getSysAtomUtil().newValue("hos_taking_stock_id"));
                    hosTakingStock.setBillId(surgeryPkgVo.getId());
                    hosTakingStock.setBillRownum(index);
                    hosTakingStock.setHosGoodsId(detailVo.getGoodsId());
                    hosTakingStock.setHosId(surgeryPkgVo.getHosId());
                    hosTakingStock.setKind(Constants.HOS_TAKING_KIND_SUR_PKG);
                    hosTakingStock.setQty(goodsBatch.getQty());
                    hosTakingStock.setStocId(surgeryPkgVo.getCurStockId());
                    hosTakingStock.setStockpileId(goodsBatch.getStoreId());
                    lstHosTakingDetail.add(hosTakingStock);
                }
            }
            SurPackResVo r = new SurPackResVo();
            r.setSurName(surgeryPkgVo.getSurName());
            if(flag){//若此包可被打包则加入待更新数据集合否则舍弃
                lstGoodsBatch.addAll(lstGoodsBatchDetail);
                lstHosTaking.addAll(lstHosTakingDetail);
                lstSurgeryPkgVo.add(surgeryPkgVo);
                //处理打包日志表
                PkgLogVo plog = new PkgLogVo();
                plog.setBillId(surgeryPkgVo.getId());
                plog.setCode(surgeryPkgVo.getId());
                plog.setDescription("打包");
                plog.setPackageKind(0);
                plog.setFiller(surgeryPkgVo.getPacker());
                lstLog.add(plog);
                //准备回写请购单打包表数据
                ApplySurgeryPkgVo asp = new ApplySurgeryPkgVo();
                asp.setBillId(surgeryPkgVo.getApplyBillId());
                asp.setSurId(surgeryPkgVo.getSurId());
                asp.setQty(new BigDecimal(1));
                lstASur.add(asp);
                r.setMsg("已打包");
                r.setState(true);
            }else{
                r.setMsg("打包失败");
                r.setState(false);
            }
            res.add(r);
        }
        surBatchDao.insertHosTakingStock(lstHosTaking);//占用库存
        asPkgService.setSurQtyByPack(lstASur);//回写请购单打包表
        surBatchDao.insertByBatch(lstGoodsBatch);//写入手术包批次表
        surgeryPkgDao.updateByBatch(lstSurgeryPkgVo);//更新手术包信息表主表
        pkgLogDao.insertByBatch(lstLog);//写入包日志表
        return res;
    }
    @Override
    public ApplySurPkgVo creatOrGetSurPkgByApplyId(String billId, String stockId) throws Exception {
        ApplySurPkgVo asp = dao.getPackApplyBill(billId);
        if(asp.getLstSurPkg() ==null || asp.getLstSurPkg().size() == 0){
            asp.setLstSurPkg(this.creatSurPkgInfo(asp));
        }
        //库存信息
        HosStockPileVo stocQuery = new HosStockPileVo();
        Set<String> lstGoodsId = new HashSet<>();
        asp.getLstSurPkg().forEach(item->{
            lstGoodsId.addAll(item.getSurgeryPkgListVos().stream().map(o->o.getGoodsId()).collect(Collectors.toSet()));
        });
        asp.setGoodsIds(lstGoodsId);
        stocQuery.setHosId(asp.getHosId());
        stocQuery.setStocId(stockId);
        stocQuery.setGoodsIds(lstGoodsId);
        List<HosStockPileVo> goodsStocInfoList = hosStockSer.getGoodsStoc(stocQuery).stream().filter(o->{
            return o.getQty().compareTo(o.getTakeQty())>0;
        }).collect(Collectors.toList());
        goodsStocInfoList.forEach(item->{
            item.setQty(item.getQty().subtract(item.getTakeQty()));
            item.setTakeQty(new BigDecimal(0));
        });
        asp.setLstStocInfo(goodsStocInfoList);
        return asp;
    }
    private List<SurgeryPkgVo> creatSurPkgInfo(ApplySurPkgVo aspv){
        List<SurgeryPkgApplyVo> lstSurPkgDef = aspv.getLstSurPkgDef();
        if(lstSurPkgDef == null || lstSurPkgDef.size()==0){
            return null;
        }
        List<SurgeryPkgVo> lstSPV = new ArrayList<>();
        List<SurgeryPkgListVo> lstSPVDetail = new ArrayList<>();
        List<PkgLogVo> lstPkLog = new ArrayList<>();
        for (SurgeryPkgApplyVo asp : lstSurPkgDef) {
            for(Integer i =0;i<asp.getPackQty();i++) {
                SurgeryPkgVo surgeryPkgVo = new SurgeryPkgVo();
                surgeryPkgVo.setId(atomUtil.newId("surPkgId").prefix(aspv.getHosId()).get());
                surgeryPkgVo.setSurCode(surgeryPkgVo.getId());
                surgeryPkgVo.setStatus(Constants.SUR_PKG_STATUS_JIANHUO);
                surgeryPkgVo.setSurId(asp.getId());
                surgeryPkgVo.setApplyBillId(aspv.getBillId());
                surgeryPkgVo.setHosId(aspv.getHosId());
                surgeryPkgVo.setDeptId(aspv.getBuyDeptId());
                surgeryPkgVo.setSurName(asp.getCname());
                lstSPV.add(surgeryPkgVo);
                //处理打包日志表
                PkgLogVo plog = new PkgLogVo();
                plog.setBillId(surgeryPkgVo.getId());
                plog.setCode(surgeryPkgVo.getId());
                plog.setDescription("捡货");
                plog.setPackageKind(0);
                plog.setFiller(utils.getUserStateUtils().getCurrent().getUserId());
                lstPkLog.add(plog);
                for (SurgeryPkgDefListVo surGoodsInfo : asp.getLstGoods()) {
                    SurgeryPkgListVo splv = new SurgeryPkgListVo();
                    splv.setId(atomUtil.newId("surPkgDetailId").prefix(surgeryPkgVo.getHosId()).get());
                    splv.setStatus(0);
                    splv.setSurCode(surgeryPkgVo.getId());
                    splv.setGoodsId(surGoodsInfo.getGoodsId());
                    splv.setGoodsGg(surGoodsInfo.getHosGoods().getGoodsGg());
                    splv.setGoodsName(surGoodsInfo.getHosGoods().getGoodsName());
                    splv.setMade(surGoodsInfo.getHosGoods().getMade());
                    splv.setMfrsName(surGoodsInfo.getHosGoods().getMfrsName());
                    splv.setBrand(surGoodsInfo.getHosGoods().getBrand());
                    splv.setUniqueKind(new Integer(surGoodsInfo.getHosGoods().getUniqueCodeStrategy()));
                    splv.setQty(surGoodsInfo.getQty());
                    splv.setUnit(surGoodsInfo.getHosGoods().getUnit());
                    splv.setShouldSterilize(surGoodsInfo.getShouldSterilize());
                    if(splv.getShouldSterilize() == 1){
                        surgeryPkgVo.setShouldSterilize(splv.getShouldSterilize());
                    }
                    surgeryPkgVo.getSurgeryPkgListVos().add(splv);
                    lstSPVDetail.add(splv);
                }
            }
        }
        surgeryPkgListDao.insertByBatch(lstSPVDetail);
        surgeryPkgDao.insertByBatch(lstSPV);
        pkgLogDao.insertByBatch(lstPkLog);
        return lstSPV;
    }

    @Override
    public SurBill4Pack getSurBill4Pack(String billId, String stockId) throws Exception {
        SurBill4Pack res = new SurBill4Pack();
        SurgeryPkgVo surBill= this.getById(billId);
        //库存信息
        HosStockPileVo stocQuery = new HosStockPileVo();
        Set<String> lstGoodsId = surBill.getSurgeryPkgListVos().stream().map(o->o.getGoodsId()).collect(Collectors.toSet());
        stocQuery.setHosId(surBill.getHosId());
        stocQuery.setStocId(stockId);
        stocQuery.setGoodsIds(lstGoodsId);
        List<HosStockPileVo> goodsStocInfoList = hosStockSer.getGoodsStoc(stocQuery).stream().filter(o->{
            return o.getQty().compareTo(o.getTakeQty())>0;
        }).collect(Collectors.toList());
        goodsStocInfoList.forEach(item->{
            item.setQty(item.getQty().subtract(item.getTakeQty()));
            item.setTakeQty(new BigDecimal(0));
        });
        res.setGoodsIds(lstGoodsId);
        res.setLstStocInfo(goodsStocInfoList);
        res.setSurBill(surBill);
        return res;
    }

    @Override
    public int updateStatusByBatch(List<SurgeryPkg> lst) throws Exception {
        return surgeryPkgDao.updateStatusByBatch(lst);
    }

    @Override
    public List<SurgeryPkgVo> list4Use(SurgeryPkgVo query) throws Exception {
        if(!StringUtils.hasText(query.getSickerName())){
            throw new Exception("病人不能为空");
        }
        if(!StringUtils.hasText(query.getHosId()) || !StringUtils.hasText(query.getDeptId())){
            throw new Exception("医院或部门不能为空");
        }
        return surgeryPkgDao.list4Use(query);
    }

    @Override
    public void setSurStatus(List<BigBatch4FillVo> lst,String desc) throws Exception {
        List<SurgeryPkg> lstSur = new ArrayList<>();
        List<SurgeryPkgList> lstSurLst = new ArrayList<>();
        List<SurgeryPkgBatch> lstSurBatch = new ArrayList<>();
        List<PkgLogVo> lstLog = new ArrayList<>();
        Map<String ,List<BigBatch4FillVo>> subGroupBySurId = lst.stream().collect(Collectors.groupingBy(BigBatch4FillVo::getSurCode));
        subGroupBySurId.forEach((surCode,lstSickerLst)->{
            SurgeryPkgVo spv = this.getById(surCode);
            if(spv != null){
                spv.setStatus(Constants.SUR_PKG_STATUS_WC);
                spv.setCurStockId("");
                PkgLogVo plog = new PkgLogVo();
                plog.setBillId(spv.getId());
                plog.setCode(spv.getId());
                plog.setDescription(desc);
                plog.setPackageKind(0);
                plog.setFiller(utilsContext.getUserStateUtils().getCurrent().getUserId());
                lstLog.add(plog);
                for (SurgeryPkgListVo splv : spv.getSurgeryPkgListVos()) {
                    BigDecimal totalUse = new BigDecimal(0);
                    for (SurgeryPkgBatchVo goodsBatch : splv.getLstGoodsBatch()) {
                        Optional<BigBatch4FillVo> sulv = null;
                        if(splv.getUniqueKind()<3){         //高值根据唯一码区分
                            sulv = lstSickerLst.stream().filter(o->StringUtils.hasText(o.getBigBatchCode()) && o.getBigBatchCode().equals(goodsBatch.getUniqueCode())).findAny();
                        }else{                              //低值根据大批号区分
                            sulv = lstSickerLst.stream().filter(o->StringUtils.hasText(o.getBigBatchCode()) && o.getBigBatchCode().equals(goodsBatch.getBigBatchCode())).findAny();
                        }
                        if(goodsBatch.getUseQty() == null){
                            goodsBatch.setUseQty(new BigDecimal(0));
                        }
                        if(sulv.isPresent()){
                            goodsBatch.setUseQty(goodsBatch.getUseQty().add(sulv.get().getQty()));
                            lstSurBatch.add(goodsBatch);
                        }
                        totalUse = totalUse.add(goodsBatch.getUseQty());
                    }
                    Integer flag = totalUse.compareTo(splv.getQty());
                    if(flag==0){
                        splv.setStatus(Constants.SUR_PKG_DETAIL_STATUS_ALL);
                    }else if(flag<0){
                        splv.setStatus(Constants.SUR_PKG_DETAIL_STATUS_SOME);
                        spv.setStatus(Constants.SUR_PKG_STATUS_BRXH);
                    }
                    lstSurLst.add(splv);
                }
            }
            lstSur.add(spv);
        });
        surBatchDao.updateStatus(lstSurBatch);//批次表信息
        surgeryPkgListDao.updateStatus(lstSurLst);
        surgeryPkgDao.updateStatusByBatch(lstSur);
        pkgLogDao.insertByBatch(lstLog);
    }
    @Override
    public void setSurStatusBySickerUse(List<SickerUserListVo> lst) throws Exception {
        List<BigBatch4FillVo> lstSurInfo = lst.stream().map(o->{
            BigBatch4FillVo bb4f = new BigBatch4FillVo();
            bb4f.setSurCode(o.getSurId());
            bb4f.setQty(o.getUseQty());
            if(StringUtils.hasText(o.getSelfCode())){
                bb4f.setBigBatchCode(o.getSelfCode());
            }else {
                bb4f.setBigBatchCode(o.getBigBatchCode());
            }
            return bb4f;
        }).collect(Collectors.toList());
        this.setSurStatus(lstSurInfo,"消耗");
    }
}