package gyqx.spdherp.stockout.service.impl;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.UtilsContext;
import gyqx.platform.po.SysOrg;
import gyqx.spdherp.po.*;
import gyqx.spdherp.stockMgr.dao.InStockBatchDao;
import gyqx.spdherp.stockMgr.dao.InStockDao;
import gyqx.spdherp.stockMgr.dao.InStockListDao;
import gyqx.spdherp.stockMgr.vo.InStockBatchVo;
import gyqx.spdherp.stockMgr.vo.InStockListVo;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.Constance;
import gyqx.spdherp.stockout.dao.OutBill4InDao;
import gyqx.spdherp.stockout.dao.SickerUseDao;
import gyqx.spdherp.stockout.dao.SickerUseListDao;
import gyqx.spdherp.stockout.dao.SurgeryPkgOutDao;
import gyqx.spdherp.stockout.service.IFillBillService;
import gyqx.spdherp.stockout.service.SurgeryPkgOutService;
import gyqx.spdherp.stockout.vo.*;
import org.springframework.stereotype.Service;
import gyqx.spdherp.surgery.constant.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static gyqx.spdherp.stockMgr.Constance.INSTOCK_TYPE_TRUE;

@Service
public class SurgeryPkgOutServiceImpl implements SurgeryPkgOutService {
	
	@Resource
	private SurgeryPkgOutDao surgeryPkgOutDao;
	@Resource
	private SickerUseDao dao;
	@Resource
	private SickerUseListDao userListdao;
	@Resource
	private OutBill4InDao o4iDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IFillBillService fillBillService;
	@Resource
	private UtilsContext utilsContext;
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private InStockBatchDao inStockBatchDao;
	@Resource
	private InStockListDao inStockListDao;
	@Resource
	private InStockDao inStockDao;
	
	/**
	 * 获取病人消耗的（科室已出库）手术包
	 *
	 * @param surgeryPkg4UseVo
	 * @return
	 */
	@Override
	public List<SurgeryPkg4UseVo> querySickerSurPkg(SurgeryPkg4UseVo surgeryPkg4UseVo) {
		return surgeryPkgOutDao.querySickerSurPkg(surgeryPkg4UseVo);
	}
	
	/**
	 * 手术包消耗单提交
	 *
	 * @param sickerUse
	 * @return
	 */
	@Override
	public int add(SickerUserVo sickerUse) throws Exception {
		String useId = atomUtil.newValue("sicker_use_id"); //UUID.randomUUID().toString();
		sickerUse.setId(useId);
		List<SickerUserListVo> sickerUserList = new ArrayList<>();
		List<SurgeryPkg4UseVo> surgerPkgList = sickerUse.getPkgLstDetail();
		List<SurgeryPkg4UseBatchVo> surgeryPkg4UseBatchVosForUpdate = new ArrayList<>();
		if (surgerPkgList != null && surgerPkgList.size() > 0) {
			for (int i = 0; i < surgerPkgList.size(); i++) {
				SurgeryPkg4UseVo surPkg = surgerPkgList.get(i);
				List<SurgeryPkg4UseListVo> surgerPkgGoodsUseList = surgerPkgList.get(i).getSurgeryPkg4UseListVo();
				
				if (surgerPkgGoodsUseList != null && surgerPkgGoodsUseList.size() > 0) {
					for (int j = 0; j < surgerPkgGoodsUseList.size(); j++) {
						
						SurgeryPkg4UseListVo useGoods = surgerPkgGoodsUseList.get(j);
						BigDecimal remnantUseQty = useGoods.getUseQty();//手术包商品消耗总数
						List<SurgeryPkg4UseBatchVo> useGoodsSurgeryPkg4UseBatchVo = useGoods.getSurgeryPkg4UseBatchVo();
						if (useGoodsSurgeryPkg4UseBatchVo != null && useGoodsSurgeryPkg4UseBatchVo.size() > 0) {
							for (int k = 0; k < useGoodsSurgeryPkg4UseBatchVo.size(); k++) {
								SurgeryPkg4UseBatchVo batch = useGoodsSurgeryPkg4UseBatchVo.get(k);
								
								SickerUserListVo sickerUserListVo = new SickerUserListVo();
								
								sickerUserListVo.setId(UUID.randomUUID().toString());
								sickerUserListVo.setPId(useId);
								sickerUserListVo.setSurId(surPkg.getSurCode());
								sickerUserListVo.setBatchCode(useGoods.getBatchCode());
								sickerUserListVo.setGoodsBatchId(batch.getBatchId());
								sickerUserListVo.setUniqueCode(batch.getUniqueCode());
								sickerUserListVo.setBigBatchCode(batch.getBigBatchCode());
								sickerUserListVo.setPrice(batch.getInPrice());
								sickerUserListVo.setSelfCode(batch.getUniqueCode());//设置成唯一码
								
								sickerUserListVo.setUnit(useGoods.getUnit());
								sickerUserListVo.setOutOrgId(useGoods.getOutOrgId());
								sickerUserListVo.setOutStocId(useGoods.getOutStocId());
								sickerUserListVo.setEpcCode(useGoods.getEpcCode());
								sickerUserListVo.setExpdtEndDate(useGoods.getExpdtEndDate());
								sickerUserListVo.setGoodsGg(useGoods.getGoodsGg());
								sickerUserListVo.setGoodsName(useGoods.getGoodsName());
								sickerUserListVo.setHibcCode(useGoods.getHibcCode());
								//sickerUserListVo.setHisPrice(useGoods.getHisPrice());//暂无
								sickerUserListVo.setGoodsId(useGoods.getGoodsId());
								sickerUserListVo.setMasterCode(useGoods.getMasterCode());
								sickerUserListVo.setOutBillId(useGoods.getBillId());
								sickerUserListVo.setOutBillRow(useGoods.getOutBillRow() == null ? 0 : useGoods.getOutBillRow());
								
								sickerUserListVo.setRowNum(k+1);
								sickerUserListVo.setSecCode(useGoods.getSecCode());
								
								// sickerUserListVo.setPurMode(useGoods.getPurMode());
								sickerUserListVo.setProvId(useGoods.getProvId());
								sickerUserListVo.setPurMode(Constance.OUTSTOCKBILL_TYPE_FAKE);//虚拟采购
								sickerUserListVo.setStatus(Constance.BEFORE_CANCEL);//未核销
								sickerUserListVo.setSterilizationCode(useGoods.getSterilizationCode());
								sickerUserListVo.setSterilizationEndDate(useGoods.getSterilizationEndDate());
								
								//sickerUserListVo.setVersion(0);
								//sickerUserListVo.setLastUpdateDatetime(new Date());
								
								if (remnantUseQty.compareTo(batch.getQty()) >= 0) {
									batch.setUseQty(batch.getQty());
									sickerUserListVo.setUseQty(batch.getUseQty());
								} else {
									if (remnantUseQty.compareTo(BigDecimal.ZERO) >= 0){
										batch.setUseQty(remnantUseQty);
										sickerUserListVo.setUseQty(remnantUseQty);
									}else{
										batch.setUseQty(BigDecimal.ZERO);
										sickerUserListVo.setUseQty(BigDecimal.ZERO);
									}
								}
								remnantUseQty = remnantUseQty.subtract(batch.getQty());
								
								sickerUserList.add(sickerUserListVo);
								surgeryPkg4UseBatchVosForUpdate.add(batch);
							}
						}
					}
				}
			}
			//1.病人消耗单主表录入
			int res = dao.insert(sickerUse);
			//2.病人消耗子表录入，过滤掉 use_qty为 0 的 记录保存到，病人消耗明细表中
			List<SickerUserListVo> sickerUseDetail = sickerUserList.stream().
					filter(useList->useList.getUseQty().compareTo(BigDecimal.ZERO)>0).collect(Collectors.toList());
			sickerUse.setLstDetail(sickerUseDetail);
			userListdao.insertByBatch(sickerUseDetail);
			//3.删除库存占用信息
			//4.回写领用出库单（科室出库）状态、
			this.setOutBillStatus(sickerUserList);
			//5.回写手术包主表状态、批次表使用数量
			this.setSurPkgStatus(surgeryPkg4UseBatchVosForUpdate);
			//6.产品补实库单据
           if (Constance.SICKER_STATUS_SUMIT.equals(sickerUse.getStatus())) {
	           this.fillBillBySickerUse(sickerUse);
           }
           //7.写日志表
			this.addPkgLog(sickerUse);
           return res;
		}
		return 0;
	}
	
	/**
	 * 写日志表
	 * @param sickerUse
	 * @return
	 */
	public void addPkgLog(SickerUserVo sickerUse){
		List<SurgeryPkg4UseVo> pkgs = sickerUse.getPkgLstDetail();
		pkgs.forEach(pkg->{
			//写手术包日志表
			PkgLog pkgLog = new PkgLog();
			pkgLog.setBillId(sickerUse.getId());
			pkgLog.setId(UUID.randomUUID().toString().replace("-", ""));
			pkgLog.setCode(pkg.getSurCode());
			//包类型 0:手术包 1:定数包
			pkgLog.setPackageKind(0);
			pkgLog.setDescription("手术包消耗");
			pkgLog.setFiller(utilsContext.getUserStateUtils().getCurrent().getEname());
			pkgLog.setFillDate(new Date());
			pkgLog.setLastUpdateDatetime(new Date());
			pkgLog.setVersion(0);
			try {
				simpleDao.insert(pkgLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	/**
	 * 补单据
	 * @param sickerUse
	 */
	public void fillBillBySickerUse(SickerUserVo sickerUse) throws Exception{
		List<SickerUserListVo> sickerUseDetail = sickerUse.getLstDetail();
		List<String> uniqueCode = sickerUseDetail.stream().filter(item -> item.getStatus() == 1 && item.getSelfCode()!=null).map(SickerUseList::getSelfCode).collect(Collectors.toList());
		int inStockRes = 0;
		if (uniqueCode.size() > 0) {
			fillBillService.createFillBillBySickerUse(sickerUse);//唯一码管理商品 补单据
			//inStockRes = fillBillService.createFillBillByUniqueCode(uniqueCode, 1, sickerUse.getExecDeptId());//唯一码管理商品 补单据
		}
		/***************************非唯一码管理补请购出库单   Start   ***************************/
		//非唯一码管理的消耗列表
		List<SickerUserListVo> sickerUseDetailOfNoUniqMgr = sickerUseDetail.stream().filter(item->item.getStatus()==1 && item.getSelfCode()==null).collect(Collectors.toList());
		this.fillOutBill(sickerUse,sickerUseDetailOfNoUniqMgr);//补出库单
		this.fillInStockBill(sickerUse,sickerUseDetailOfNoUniqMgr);//补入库库单
		/***************************非唯一码管理补请购出库单   End   ***************************/
	}
	
	/**
	 * 补入库单
	 * @param sickerUse
	 * @param sickerUseDetailOfNoUniqMgr
	 */
	public int fillInStockBill(SickerUserVo sickerUse, List<SickerUserListVo> sickerUseDetailOfNoUniqMgr) throws Exception{
		List<String> bigBatchCodesList = sickerUseDetailOfNoUniqMgr.stream().filter(item -> item.getStatus() == 1).map(SickerUseList::getBigBatchCode).collect(Collectors.toList());
		String hosId = sickerUse.getHosId();
		String execDeptId = sickerUse.getExecDeptId();
		List<InStockVo> inStockVos = this.surgeryPkgOutDao.getWholeInStockByBigBatchCode(bigBatchCodesList);
		List<InStock> inStocks = new ArrayList<>();
		List<InStockList> inStockListVos = new ArrayList<>();
		List<InStockBatch> inStockBatchVos = new ArrayList<>();
		for(InStockVo inStockVo:inStockVos){
			String billId = atomUtil.newId("cgInStock").prefix(hosId).get();
			inStockVo.setId(billId);
			inStockVo.setBillId(billId);
			inStockVo.setInStockType(INSTOCK_TYPE_TRUE);
			inStockVo.setStatus(gyqx.spdherp.stockMgr.Constance.INSTOCK_STATUS_CHARGE);
			if(inStockVo.getInStockKind().equals(gyqx.spdherp.stockMgr.Constance.INSTOCK_KIND_DEPTAPPLY)) {
				inStockVo.setRemark("病人手术包消耗补请购入库单");
				if (null != execDeptId) {
					inStockVo.setInDeptId(execDeptId);
				}
			}else if(inStockVo.getInStockKind().equals(gyqx.spdherp.stockMgr.Constance.INSTOCK_KIND_SEND)){
				inStockVo.setRemark("病人手术包消耗补采购入库单");
			}
			int i=1;
			for (InStockListVo inStockListVo : inStockVo.getLstDetail()) {
				String detailId = atomUtil.newId("cgInStockDetail").prefix(hosId).get();
				inStockListVo.setId(detailId);
				inStockListVo.setPid(billId);
				inStockListVo.setBillId(billId);
				inStockListVo.setInBillRow(i++);
				BigDecimal subOutQtySum = new BigDecimal(0.0);
				for (InStockBatchVo batch : inStockListVo.getLstBatch()) {
					BigDecimal batchQty = sickerUseDetailOfNoUniqMgr.stream().filter(detail->detail.getBigBatchCode().equals(batch.getBigBatchCode())).map(SickerUserListVo::getUseQty).reduce(BigDecimal::add).get();
					batch.setPid(inStockListVo.getId());
					batch.setBillId(billId);
					batch.setQty(batchQty);
					subOutQtySum = subOutQtySum.add(batchQty);
					batch.setPRowId(inStockListVo.getInBillRow());
					batch.setLastUpdateDatetime(new Date());
					batch.setVersion(batch.getVersion() + 1);
					inStockBatchVos.add(batch);
				}
				inStockListVo.setOutQty(subOutQtySum);
				inStockListVo.setInQty(subOutQtySum);
				inStockListVos.add(inStockListVo);
			}
			inStocks.add(inStockVo);
		}
		int resCount = 0;
		if(inStockVos.size()>0){
			inStockDao.insertByBatch(inStocks);
			resCount++;
		}
		if(inStockListVos.size()>0){
			inStockListDao.intsertByBatch(inStockListVos);
			resCount++;
		}
		if(inStockBatchVos.size()>0){
			inStockBatchDao.intsertByBatch(inStockBatchVos);
			resCount++;
		}
		return  resCount;
	}
	
	public List<OutStockBatch> fillOutBill(SickerUserVo sickerUse, List<SickerUserListVo> sickerUseDetailOfNoUniqMgr) {
		List<String> bigBatchCodesList = sickerUseDetailOfNoUniqMgr.stream().filter(item -> item.getStatus() == 1).map(SickerUseList::getBigBatchCode).collect(Collectors.toList());
		String hosId = sickerUse.getHosId();
		String execDeptId = sickerUse.getExecDeptId();
		List<EntireOutStockVo> outStockVos = this.surgeryPkgOutDao.getWholeOutStockByBigBatchCode(bigBatchCodesList, sickerUse.getHosId(), 1);
		List<EntireOutStockListVo> outStockListVos = outStockVos.stream().map(EntireOutStockVo::getEntireOutStockListVoList).flatMap(Collection::stream).collect(Collectors.toList());
		//List<OutStockBatch> outStockBatchList = outStockListVos.stream().map(EntireOutStockListVo::getOutStockBatchList).flatMap(Collection::stream).collect(Collectors.toList());
		List<OutStockBatch> outStockBatchListForInsert  = new ArrayList<>() ;
		for (EntireOutStockVo main : outStockVos) {
			String mId = hosId + utilsContext.getSysAtomUtil().newValue("out_stock_id");
			main.setBillId(mId);
			main.setId(mId);
			main.setOutStockType(Constance.OUTSTOCKBILL_TYPE_REAL); // 实库
			main.setLastUpdateDatetime(new Date());
			main.setVersion(main.getVersion() + 1);
			main.setRemark("补病人手术包请购出库单");
			main.setPatientName(sickerUse.getPkgLstDetail().get(0).getSickerName());
			if (main.getOutStockKind().equals(Constance.OUTSTOCK_KIND_REQUEST.toString()) && execDeptId != null) {
				main.setInDeptId(execDeptId);
				SysOrg sysOrg = simpleDao.get(SysOrg.class, execDeptId);
				main.setInDeptName(sysOrg.getEname());
			}
			int rowNum = 1;
			for (EntireOutStockListVo sub : main.getEntireOutStockListVoList()) {
				String sId = hosId + utilsContext.getSysAtomUtil().newValue("out_stock_list_id");
				sub.setBillId(mId);
				sub.setId(sId);
				sub.setPid(mId);
				sub.setOutBillRow(rowNum++);
				BigDecimal subOutQtySum = new BigDecimal(0.0);
				sub.setLastUpdateDatetime(new Date());
				sub.setVersion(sub.getVersion() + 1);
				//根据大批号去重
				List<OutStockBatch> distinctClass = sub.getOutStockBatchList().stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getBigBatchCode() ))), ArrayList::new));
				for (OutStockBatch batch : distinctClass) {
					String uId = hosId + utilsContext.getSysAtomUtil().newValue("out_stock_batch_id");
					BigDecimal batchQty = sickerUseDetailOfNoUniqMgr.stream().filter(detail->detail.getBigBatchCode().equals(batch.getBigBatchCode())).map(SickerUserListVo::getUseQty).reduce(BigDecimal::add).get();
					batch.setId(uId);
					batch.setPid(sId);
					batch.setBillId(mId);
					batch.setUseQty(batchQty);
					batch.setQty(batchQty);
					subOutQtySum = subOutQtySum.add(batchQty);
					batch.setPRowId(sub.getOutBillRow());
					batch.setLastUpdateDatetime(new Date());
					batch.setVersion(batch.getVersion() + 1);
					outStockBatchListForInsert.add(batch);
				}
				sub.setOutQty(subOutQtySum);
			}
		}
		if(outStockVos.size()>0 && outStockListVos.size()>0 && outStockBatchListForInsert.size()>0){
			this.surgeryPkgOutDao.fillOutBills(outStockVos,outStockListVos,outStockBatchListForInsert);
		}
		return outStockBatchListForInsert;
	}
	
	/**
	 * 修改手术包主表、明细状态，批次表使用数量
	 * @param surgeryPkg4UseBatchVosForUpdate
	 * @throws Exception
	 */
	public void setSurPkgStatus(List<SurgeryPkg4UseBatchVo> surgeryPkg4UseBatchVosForUpdate) throws Exception{
		List<SurgeryPkg> surgeryPkgs = new ArrayList<>();//手术包列表
		List<SurgeryPkgList> surgeryPkgListDetail = new ArrayList<>();//手术包中的所有商品列表
		Map<String, List<SurgeryPkg4UseBatchVo>> collect = surgeryPkg4UseBatchVosForUpdate.stream().collect(Collectors.groupingBy(SurgeryPkg4UseBatchVo::getSurCode));
		collect.forEach((surCode,surgerBathcList)->{
			SurgeryPkgBatch queryVo = new SurgeryPkgBatch();
			queryVo.setSurCode(surCode);
			List<SurgeryPkgBatch> list = this.surgeryPkgOutDao.querySurgeryPkgBatchSub(queryVo);
			
			SurgeryPkg surgeryPkg = new SurgeryPkg4UseVo();
			surgeryPkg.setSurCode(surCode);
			if(list!=null && list.size()>0){
				if(list.size()>surgerBathcList.size()){
					surgeryPkg.setStatus(Constants.SUR_PKG_STATUS_BRXH);//手术包部分消耗
				}else{
					long count = surgerBathcList.stream().filter(batch -> batch.getQty().compareTo(batch.getUseQty())>0).count();
					if(count>0){
						surgeryPkg.setStatus(Constants.SUR_PKG_STATUS_BRXH);//手术包部分消耗
					}else{
						surgeryPkg.setStatus(Constants.SUR_PKG_STATUS_WC);//手术包全部消耗,完成状态
					}
				}
			}
			//surgeryPkg.setStatus(41);//手术包只要打开，状态就变为“消耗”
			surgeryPkgs.add(surgeryPkg);
			//获取手术包一个商品所有的批次列表
			Map<String ,List<SurgeryPkg4UseBatchVo>>  surgeryPkgListBatchs = surgerBathcList.stream().collect(Collectors.groupingBy(SurgeryPkg4UseBatchVo::getGoodsId));
			surgeryPkgListBatchs.forEach((goodsId,batchs)->{
				SurgeryPkgList surgeryPkgList = new SurgeryPkgList();
				surgeryPkgList.setGoodsId(goodsId);
				surgeryPkgList.setSurCode(surCode);
				surgeryPkgList = surgeryPkgOutDao.findSurgeryPkgLists(surgeryPkgList).stream().findFirst().get();
				BigDecimal sumUseQty = batchs.stream().map(SurgeryPkg4UseBatchVo::getUseQty).reduce(BigDecimal.ZERO, BigDecimal::add);
				if(surgeryPkgList.getQty().compareTo(sumUseQty)==0){
					surgeryPkgList.setStatus(Constants.SUR_PKG_DETAIL_STATUS_ALL);//手术包中 该商品全部消耗，已完成
				}else{
					surgeryPkgList.setStatus(Constants.SUR_PKG_DETAIL_STATUS_SOME);//手术包中 该商品部分消耗，未完成
				}
				surgeryPkgListDetail.add(surgeryPkgList);
			});
		});
		surgeryPkgOutDao.batchUpdateSurgeryPkgBatchUseQty(surgeryPkg4UseBatchVosForUpdate);//修改手术包商品使用数量
		surgeryPkgOutDao.batchUpdateSurgeryPkgListStatus(surgeryPkgListDetail);//更新手术包商品明细个商品的消耗 状态
		surgeryPkgOutDao.batchUpdateSurgeryPkgStatus(surgeryPkgs);//更新手术包主表状态
	}
	
	/**
	 * 修改科室出库单据"状态" 及 “使用数量”
	 * @param lst
	 * @throws Exception
	 */
	public void setOutBillStatus(List<SickerUserListVo> lst) throws Exception {
		//按出库单编号分组
		Map<String, List<SickerUserListVo>> collect = lst.stream().collect(Collectors.groupingBy(SickerUserListVo::getOutBillId));
		collect.forEach((outBillId, lstSul) -> {
			OutBillListVo outBillListVo = new OutBillListVo();
			outBillListVo.setBillId(outBillId);
			List<OutStockUniqueCode> uniqueCodeList = o4iDao.getUniqueCodeList(outBillId);//获取该出库单里所有唯一码管理的商品
			List<OutStockBatchVo> stockBatches = surgeryPkgOutDao.queryOutStockBatchByBillId(outBillId);//获取该出库单里所有非唯一码管理的商品
			
			for (SickerUserListVo sickerUserListVo : lstSul) {
				String uniqueCode = sickerUserListVo.getSelfCode();
				Integer outBillRow = sickerUserListVo.getOutBillRow();
				if (null != uniqueCode) {
					for (OutStockUniqueCode outStockUniqueCode : uniqueCodeList) {
						if (outStockUniqueCode.getPRowId() == outBillRow && outStockUniqueCode.getUniqueCode().equals(uniqueCode)) {
							outStockUniqueCode.setIsUsed(1);
						}
					}
				} else {
					String surCode = sickerUserListVo.getSurId();
					String goodsBatchId = sickerUserListVo.getGoodsBatchId();
					String batchCode = sickerUserListVo.getBatchCode();
					String goodsId = sickerUserListVo.getGoodsId();
					for (OutStockBatchVo batch : stockBatches) {
						if (batch.getPRowId() == outBillRow && surCode.equals(batch.getSurCode())
								&& goodsId.equals(batch.getGoodsId()) && goodsBatchId.equals(batch.getGoodsBatchId())
								&& batchCode.equals(batch.getBatchCode())) {
							batch.setUseQty(batch.getUseQty().add(sickerUserListVo.getUseQty()));
						}
					}
				}
			}
			long remnantBatchCount = stockBatches.stream().
					filter(batch -> batch.getQty().subtract(batch.getUseQty()).compareTo(BigDecimal.ZERO) > 0 ).count();
            long remnantUniqueCount = uniqueCodeList.stream().filter(unique -> unique.getIsUsed() == null || unique.getIsUsed() == 0).count();
            OutStock outStock = new OutStock();
            outStock.setBillId(outBillId);
            if ((remnantBatchCount+remnantUniqueCount) == 0) {
                //全部验收
                outStock.setStatus(Constance.OUTSTOCKBILL_ALLUSE_STATUS);
            } else {
                //未全部验收
                outStock.setStatus(Constance.OUTSTOCKBILL_HALFUSE_STATUS);
            }
            o4iDao.updateOutStockStatus(outStock);
            //update outStockBill
            //update 唯一码表信息
            List<OutStockUniqueCode> uniqueCLst = uniqueCodeList.stream().filter(unique -> unique.getIsUsed() != null && unique.getIsUsed() == 1).collect(Collectors.toList());
            if (uniqueCLst != null && uniqueCLst.size() != 0) {
                o4iDao.updateUniqueCodesUsed(uniqueCLst);
            }
			surgeryPkgOutDao.updateBatchSurgeryUseQty(stockBatches);
		});
	}
}
