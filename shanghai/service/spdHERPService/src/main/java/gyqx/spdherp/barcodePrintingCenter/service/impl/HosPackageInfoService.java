package gyqx.spdherp.barcodePrintingCenter.service.impl;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import gyqx.spdherp.barcodePrintingCenter.vo.MDetail;
import gyqx.spdherp.barcodePrintingCenter.vo.MPkage;
import gyqx.spdherp.barcodePrintingCenter.vo.Undo;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.util.StringUtil;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.transform.Tx;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.barcodePrintingCenter.dao.HosPackageBatchDetailDao;
import gyqx.spdherp.barcodePrintingCenter.dao.HosPackageInfoDao;
import gyqx.spdherp.barcodePrintingCenter.service.IHosPackageInfoService;
import gyqx.spdherp.barcodePrintingCenter.vo.HosPackageInfoVo;
import gyqx.spdherp.po.HosPackageBatchDetail;
import gyqx.spdherp.po.HosPackageInfo;
import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.stockout.vo.vo4In.OutSub4InVo;

@Service
public class HosPackageInfoService implements IHosPackageInfoService {
	
	@Resource
	private HosPackageInfoDao  dao;
	@Resource
	private HosPackageBatchDetailDao  detailDao;
	@Resource
	UserOnlineStateUtils userOnlineState;
	@Resource
	private SysAtomUtil atomUtil;

	public List<String> listPackageCodeByBillId(String billId){
		return dao.listPackageCodeByBillId(billId);
	}

	@Override
	public List<HosPackageInfoVo> addHosPackageInfos(Map<String, List<OutSub4InVo>> map)  throws Exception {

		List<HosPackageInfoVo> vos = new ArrayList<HosPackageInfoVo>();
		for (Map.Entry<String, List<OutSub4InVo>> entry : map.entrySet()) {
			List<OutSub4InVo> OutSub4InVos = entry.getValue();// 同一产品集合：总出库数量 / 包装数量 = 包数 （一包一码）

			//gw add start
			List<Undo> undolst= new  ArrayList(); //商品o 未完成打包的零头对象
			int pNum =OutSub4InVos.get(0).getPacketCode().intValue();// 商品o 定数包的包装数（一包多少个）
			for(OutSub4InVo o : OutSub4InVos) {

				List<OutStockBatch> batchList = o.getLstOutBatch();
				for(OutStockBatch b :batchList){

					//如果该批号能打整包
					for(int i=0;i<b.getQty().divideAndRemainder(new BigDecimal(pNum))[0].intValue();i++){
						//					//执行插入定数包主表逻辑
						HosPackageInfo hosPackageInfo = new HosPackageInfo();
						hosPackageInfo.setId( atomUtil.newValue("hos_package_info_id"));
						hosPackageInfo.setPackageId(atomUtil.newId("packageId").prefix(o.getGoodsId(),o.getPacketCode()).get()) ;
						hosPackageInfo.setBatchCode(o.getBatchCode());
						hosPackageInfo.setBillId(o.getBillId());
						hosPackageInfo.setExpdtEndDate(o.getExpdtEndDate());
						hosPackageInfo.setGoodsGg(o.getGoodsGg());
						hosPackageInfo.setGoodsId(o.getGoodsId());
						hosPackageInfo.setGoodsName(o.getGoodsName());
						hosPackageInfo.setHosId(userOnlineState.getCurrent().getCorpId());
						hosPackageInfo.setHosName(userOnlineState.getCurrent().getCorpName());
						//hosPackageInfo.setLastUpdateDatetime();
						hosPackageInfo.setMade(o.getMade());
						hosPackageInfo.setMfrsId(o.getMfrsId());
						hosPackageInfo.setMfrsName(o.getMfrsName());
						hosPackageInfo.setPacketCode(new BigDecimal(pNum));
						hosPackageInfo.setPacketQty(new BigDecimal(pNum));
						hosPackageInfo.setProvId(o.getProvId());
						hosPackageInfo.setProvName(o.getProvName());
						hosPackageInfo.setQty(new BigDecimal(pNum));
						hosPackageInfo.setRowId(1);
						hosPackageInfo.setSterilizationCode(o.getSterilizationCode());
						hosPackageInfo.setSterilizationDate(o.getSterilizationDate());
						hosPackageInfo.setSterilizationEndDate(o.getSterilizationEndDate());
						hosPackageInfo.setUnit(o.getUnit());
						hosPackageInfo = dao.insertAndGet(hosPackageInfo);  //插入定数包主表

						HosPackageBatchDetail packageDetail = new HosPackageBatchDetail();
						packageDetail.setId(atomUtil.newValue("hos_package_batch_detail_id"));
						packageDetail.setPPackageId(hosPackageInfo.getPackageId());
						packageDetail.setRowId(hosPackageInfo.getRowId());
						packageDetail.setGoodsId(hosPackageInfo.getGoodsId());
						packageDetail.setGoodsBatchId(b.getGoodsBatchId());
						packageDetail.setInPrice(b.getInPrice());
						packageDetail.setInTime(b.getInTime());
						packageDetail.setQty(new BigDecimal(pNum));
						packageDetail.setBigBatchCode(b.getBigBatchCode());
						packageDetail = detailDao.insertAndGet(packageDetail);
						HosPackageInfoVo vo = new HosPackageInfoVo();
						vo = Tx.transform(hosPackageInfo).to(vo);
						vo.getHosPackageBatchDetailLst().add(packageDetail);
                        vos.add(vo);

					}
					if(b.getQty().divideAndRemainder(new BigDecimal(pNum))[1].compareTo(new BigDecimal(0))==1){ //收集每行不能打整包的数据
					   //将剩余的暂存  OutSub4InVo o OutStockBatch b  new BigDecimal(packageCode))[0]
						Undo ob = new Undo();
						ob.setGoodsId(o.getGoodsId());
						ob.setBatchCode(o.getBatchCode());
						ob.setPcode(pNum);
						ob.setGoodsBatchId(b.getGoodsBatchId());
						ob.setQty(b.getQty().divideAndRemainder(new BigDecimal(pNum))[1].intValue());
						ob.setOutSubObj(o);
						ob.setOutBatchObj(b);
						undolst.add(ob);
					}
				}
			}
            //对当前商品零散数进行打包 List<HosPackageInfoVo> vos = new ArrayList<HosPackageInfoVo>();
            if(undolst.size()>0){
				//将undolst 按批号、批次进行排序
				undolst.sort(Comparator.comparing(Undo::getBatchCode).thenComparing(Undo::getGoodsBatchId));
                List<HosPackageInfoVo> vos4Undo=      addPackage4UndoLst(undolst);
                vos.addAll(vos4Undo);
            }

		}
		return vos;
	}
	
/*
	public List<HosPackageInfoVo> addHosPackageInfos1(Map<String, List<OutSub4InVo>> map)  throws Exception {
		List<HosPackageInfoVo> vos = new ArrayList<HosPackageInfoVo>();
		for (Map.Entry<String, List<OutSub4InVo>> entry : map.entrySet()) {
			List<OutSub4InVo> OutSub4InVos = entry.getValue();// 同一产品集合：总出库数量 / 包装数量 = 包数 （一包一码）
			int totalQty = 0; // 同一产品总出库数量
			for(OutSub4InVo o : OutSub4InVos){
				totalQty += o.getOutQty().intValue();
			}
			// 包装数量
			int packageCode = OutSub4InVos.get(0).getPacketCode().intValue();
			// 包数
			int countPackage = totalQty / packageCode;
			for(int i=1;i<=countPackage;i++){
				
				OutSub4InVo o = OutSub4InVos.get(0);
				String packageId = atomUtil.newId("packageId").prefix(o.getGoodsId(),o.getPacketCode()).get();
				List<OutStockBatch> outStockBatchs = o.getLstOutBatch();
				for(int j=0;j<outStockBatchs.size();j++){
					HosPackageInfo hosPackageInfo = new HosPackageInfo();
					hosPackageInfo.setId(atomUtil.newValue("hos_package_info_id"));
					hosPackageInfo.setPackageId(packageId);
					hosPackageInfo.setRowId(j+1);
					hosPackageInfo.setHosId(userOnlineState.getCurrent().getCorpId());
					hosPackageInfo.setHosName(userOnlineState.getCurrent().getCorpName());
					
					hosPackageInfo.setBillId(o.getBillId());
					hosPackageInfo.setGoodsId(o.getGoodsId());
					hosPackageInfo.setGoodsName(o.getGoodsName());
					hosPackageInfo.setGoodsGg(o.getGoodsGg());
					hosPackageInfo.setUnit(o.getUnit());
					hosPackageInfo.setQty(o.getLstOutBatch().get(0).getQty());
					
					hosPackageInfo.setProvId(o.getProvId());
					hosPackageInfo.setProvName(o.getProvName());
					hosPackageInfo.setMfrsId(o.getMfrsId());
					hosPackageInfo.setMfrsName(o.getMfrsName());
					hosPackageInfo.setMade(o.getMade());
					hosPackageInfo.setPacketCode(o.getPacketCode());
					hosPackageInfo.setBatchCode(o.getBatchCode());
					hosPackageInfo.setSterilizationCode(o.getSterilizationCode());
					hosPackageInfo.setSterilizationDate(o.getSterilizationDate());
					hosPackageInfo.setSterilizationEndDate(o.getSterilizationEndDate());
					hosPackageInfo.setExpdtEndDate(o.getExpdtEndDate());
					// 整包数量
					BigDecimal packetQty = hosPackageInfo.getQty().multiply(hosPackageInfo.getPacketCode());
					hosPackageInfo.setPacketQty(packetQty);
					
					HosPackageInfo bean = dao.insertAndGet(hosPackageInfo);
					
					HosPackageInfoVo vo = new HosPackageInfoVo();
					vo = Tx.transform(bean).to(vo);
					
					HosPackageBatchDetail packageDetail = new HosPackageBatchDetail();
					packageDetail.setId(atomUtil.newValue("hos_package_batch_detail_id"));
					packageDetail.setPPackageId(bean.getPackageId());
					packageDetail.setRowId(bean.getRowId());
					packageDetail.setGoodsId(bean.getGoodsId());
					packageDetail.setGoodsBatchId(outStockBatchs.get(j).getGoodsBatchId());
					packageDetail.setInPrice(outStockBatchs.get(j).getInPrice());
					packageDetail.setInTime(outStockBatchs.get(j).getInTime());
					packageDetail.setQty(outStockBatchs.get(j).getQty());
					packageDetail.setBigBatchCode(outStockBatchs.get(j).getBigBatchCode());
					HosPackageBatchDetail detail = detailDao.insertAndGet(packageDetail);
					vo.setHosPackageBatchDetails(detail);
					vos.add(vo);
				}
				
			}
		}
		return vos;
	}
*/

	public HosPackageInfo get(String id)
	{
		return dao.get( id);	
	}	

	public HosPackageInfo add(HosPackageInfo hosPackageInfo) throws Exception 
	{
		return dao.insertAndGet(hosPackageInfo);
	}
		
	public HosPackageInfo update(HosPackageInfo hosPackageInfo) throws Exception {
		return dao.updateAndGet(hosPackageInfo);
	}
	public int updateFields(HosPackageInfo hosPackageInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(hosPackageInfo, fieldNames);
	}

	public void delete(HosPackageInfo hosPackageInfo) throws Exception {
		dao.delete(hosPackageInfo);
	}
	
	public HosPackageInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosPackageInfo) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<HosPackageInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosPackageInfo>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<HosPackageInfo> list(HosPackageInfo bean) throws Exception
	{
		return (List<HosPackageInfo>) dao.list(bean);
	}	
	
	public QueryResult<HosPackageInfo> listByPage(QueryInfo<HosPackageInfo> queryInfo) throws Exception{		
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}
    public QueryResult<HosPackageInfo> listDsPacksByOutBillId(QueryInfo<HosPackageInfo> queryInfo) throws Exception{

        PageUtils.startPage(queryInfo);
        return PageUtils.endPage(dao.listDsPacksByOutBillId(queryInfo.getQueryObject()));
    }

	private  List<HosPackageInfoVo> addPackage4UndoLst(List<Undo> ulst) throws Exception{
        List<HosPackageInfoVo> vos = new ArrayList<HosPackageInfoVo>(); //用于返回
		List<HosPackageInfoVo> mainLst=new ArrayList<HosPackageInfoVo>();
		int st =0;
		int pNum = ulst.get(0).getPcode();
		for(Undo u:ulst){
			if(!mainLst.stream().anyMatch(m-> m.getBatchCode().equals(u.getBatchCode()))){
                HosPackageInfo hosPackageInfo = new HosPackageInfoVo();

				//补全主表其他信息  id 在下面逻辑创建
              //  hosPackageInfo.setId( atomUtil.newValue("hos_package_info_id"));
              //  hosPackageInfo.setPackageId(atomUtil.newId("packageId").prefix(o.getGoodsId(),o.getPacketCode()).get()) ;
                hosPackageInfo.setBatchCode(u.getOutSubObj().getBatchCode());
                hosPackageInfo.setBillId(u.getOutSubObj().getBillId());
                hosPackageInfo.setExpdtEndDate(u.getOutSubObj().getExpdtEndDate());
                hosPackageInfo.setGoodsGg(u.getOutSubObj().getGoodsGg());
                hosPackageInfo.setGoodsId(u.getOutSubObj().getGoodsId());
                hosPackageInfo.setGoodsName(u.getOutSubObj().getGoodsName());
                hosPackageInfo.setHosId(userOnlineState.getCurrent().getCorpId());
                hosPackageInfo.setHosName(userOnlineState.getCurrent().getCorpName());
                //hosPackageInfo.setLastUpdateDatetime();
                hosPackageInfo.setMade(u.getOutSubObj().getMade());
                hosPackageInfo.setMfrsId(u.getOutSubObj().getMfrsId());
                hosPackageInfo.setMfrsName(u.getOutSubObj().getMfrsName());
                hosPackageInfo.setPacketCode(new BigDecimal(pNum));
                hosPackageInfo.setPacketQty(new BigDecimal(pNum));
                hosPackageInfo.setProvId(u.getOutSubObj().getProvId());
                hosPackageInfo.setProvName(u.getOutSubObj().getProvName());
                hosPackageInfo.setQty(new BigDecimal(pNum));
              //  hosPackageInfo.setRowId(1);   //依据实际情况
                hosPackageInfo.setSterilizationCode(u.getOutSubObj().getSterilizationCode());
                hosPackageInfo.setSterilizationDate(u.getOutSubObj().getSterilizationDate());
                hosPackageInfo.setSterilizationEndDate(u.getOutSubObj().getSterilizationEndDate());
                hosPackageInfo.setUnit(u.getOutSubObj().getUnit());
                HosPackageInfoVo vo = new HosPackageInfoVo();
				mainLst.add(Tx.transform(hosPackageInfo).to(vo));
			}
			if(u.getQty()+st>=pNum){
				int delta = pNum-st ;  //差几个成整包
				u.setQty(u.getQty()-delta); // 打成整包后剩余数量
				// 构建明细对象
				HosPackageBatchDetail detail = new	HosPackageBatchDetail();
				detail.setGoodsBatchId(u.getGoodsBatchId());
				detail.setQty(new BigDecimal(delta));
				//补全HosPackageBatchDetail的其他属性
                detail.setGoodsId(u.getOutSubObj().getGoodsId());
                detail.setGoodsBatchId(u.getOutBatchObj().getGoodsBatchId());
                detail.setInPrice(u.getOutBatchObj().getInPrice());
                detail.setInTime(u.getOutBatchObj().getInTime());
                detail.setBigBatchCode(u.getOutBatchObj().getBigBatchCode());
				//
				mainLst.get(mainLst.size()-1).getHosPackageBatchDetailLst().add(detail);

				//String bh = UUID.randomUUID().toString();
				String packageId = atomUtil.newId("packageId").prefix(u.getGoodsId(),u.getPcode()).get();

				for(int i=1;i<=mainLst.size();i++){
                    HosPackageInfoVo mainObj=mainLst.get(i-1);
					//mainObj.setId("m:"+UUID.randomUUID().toString());
					mainObj.setId(atomUtil.newValue("hos_package_info_id"));
					// mainObj.setPh(); 批号之前已赋值
					mainObj.setRowId(i);
					mainObj.setQty(new BigDecimal(mainObj.getHosPackageBatchDetailLst().stream().map(d-> d.getQty().intValue()).reduce(Integer::sum).get())); // 需要根据实际情况运算
					mainObj.setPackageId(packageId);
                    HosPackageInfo obj = new HosPackageInfo();
                    obj = Tx.transform(mainObj).to(obj);
                     obj = dao.insertAndGet(obj);
                    mainObj =  Tx.transform(obj).to(mainObj);
                    for(int k=1;k<=mainObj.getHosPackageBatchDetailLst().size();k++){
						HosPackageBatchDetail d= mainObj.getHosPackageBatchDetailLst().get(k-1);
						d.setId(atomUtil.newValue("hos_package_batch_detail_id"));
						d.setRowId(i);  //明细表行号为主表的行号
						d.setPPackageId(mainObj.getPackageId());
                        d.setGoodsId(u.getOutSubObj().getGoodsId());
                     //   d.setGoodsBatchId(u.getOutBatchObj().getGoodsBatchId());
                      //  d.setInPrice(u.getOutBatchObj().getInPrice());
                      //  d.setInTime(u.getOutBatchObj().getInTime());
                      //  d.setBigBatchCode(u.getOutBatchObj().getBigBatchCode());
                        d = detailDao.insertAndGet(d);
                        mainObj.getHosPackageBatchDetailLst().set(k-1,d);
                       // mainObj.getHosPackageBatchDetailLst().add(d);
					}
					vos.add(mainObj);
				}



				mainLst.clear();
				if(u.getQty()>0){
					//有剩余
                    HosPackageInfoVo m = new HosPackageInfoVo();
					m.setBatchCode(u.getBatchCode());
					HosPackageBatchDetail dt = new HosPackageBatchDetail();
					dt.setGoodsBatchId(u.getGoodsBatchId());
					dt.setQty(new BigDecimal(u.getQty()));
					dt.setGoodsBatchId(u.getOutBatchObj().getGoodsBatchId());
					dt.setInPrice(u.getOutBatchObj().getInPrice());
					dt.setInTime(u.getOutBatchObj().getInTime());
					dt.setBigBatchCode(u.getOutBatchObj().getBigBatchCode());
					m.getHosPackageBatchDetailLst().add(dt);
					mainLst.add(m);
				}
				st= u.getQty();
			}
			else {  //不构成打整包的要求
				HosPackageBatchDetail dt = new HosPackageBatchDetail();
				dt.setGoodsBatchId(u.getGoodsBatchId());
				dt.setQty(new BigDecimal(u.getQty()));
				dt.setGoodsBatchId(u.getOutBatchObj().getGoodsBatchId());
				dt.setInPrice(u.getOutBatchObj().getInPrice());
				dt.setInTime(u.getOutBatchObj().getInTime());
				dt.setBigBatchCode(u.getOutBatchObj().getBigBatchCode());
				List<HosPackageBatchDetail> ds= new ArrayList();
				ds=mainLst.get(mainLst.size()-1).getHosPackageBatchDetailLst();
				ds.add(dt);
				mainLst.get(mainLst.size()-1).setHosPackageBatchDetailLst(ds);
				st =st+u.getQty();
			}
		}
		return vos;
	}



}


