package gyqx.spdherp.forRiva.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import cn.rivamed.entity.ConsumablesInventory;
import cn.rivamed.entity.ReturnPremium;
import common.db.SimpleDao;
import common.exception.ExceptionMessage;
import common.transform.Tx;
import common.utils.UtilsContext;
import common.utils.json.Json;
import gyqx.api.forRiva.service.IStockCaller;
import gyqx.api.forRiva.service.impl.StockCaller;
import gyqx.spdherp.forRiva.dao.CallForRivaDao;
import gyqx.spdherp.forRiva.service.ICallForRivaService;
import gyqx.spdherp.po.*;
import gyqx.spdherp.stockout.dao.ReturnOutDao;
import gyqx.spdherp.stockout.vo.OutStockBillListSupVo;
import gyqx.spdherp.stockout.vo.OutStockBillSupVo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CallForRivaService implements ICallForRivaService{
	private static Logger log = LoggerFactory.getLogger(CallForRivaService.class);
	@Value("${riva.baseUrl}")
	private String baseUrl;
//	@Resource(name="riva.cabCaller")
	private StockCaller iStockCaller;
	@Resource
	private ReturnOutDao returnOutDao;
	@Resource
	private CallForRivaDao callForRivaDao;
	@Resource
	private UtilsContext utilsContext;
	@Resource
	private SimpleDao simpleDao;
	@PostConstruct
	private void init(){
		iStockCaller = new StockCaller();
		iStockCaller.setBaseUrl(baseUrl);
	}
	/**
	 * 高值一级库出库记账后推送出库信息去高值柜
	 * @param outStockBillId
	 * @return
	 * @throws Exception
	 */
	@Override
	public String callPushOutInfo(String outStockBillId) throws Exception {
		OutStockBillSupVo allInfoOutBill = returnOutDao.getAllInfoOutBill(outStockBillId);
		OutStock outStockBill = new OutStock();
		Tx.transform(allInfoOutBill).to(outStockBill);
		List<OutStockBillListSupVo> outStockBillListSupVos = allInfoOutBill.getlOutStockList();
		List<ConsumablesInventory> inventoryList = new ArrayList<>();
		for(OutStockBillListSupVo osl:outStockBillListSupVos){
			if(osl.getlOutStockUniqueCode()!=null&&osl.getlOutStockUniqueCode().size()>0){
				for(OutStockUniqueCode ouc:osl.getlOutStockUniqueCode()){
					if(osl.getId().equals(ouc.getPid())){
						ConsumablesInventory csi = new ConsumablesInventory();
						csi.setDepartmentId(allInfoOutBill.getInDeptId());
						csi.setEpc(ouc.getUniqueCode());
						csi.setExpirationDate(osl.getExpdtEndDate());
						csi.setFromLocation(null);
						csi.setFromLocationId(null);
						csi.setFromStockCode(allInfoOutBill.getOutStocId());
						csi.setId(allInfoOutBill.getId());
						csi.setIndicator(1);
						csi.setItemNum(ouc.getGoodsId());
						csi.setLocationCode(null);
						csi.setToLocation(null);
						String inStockId = this.getStocIdByEname(allInfoOutBill.getInDeptName());
						csi.setToStockCode(inStockId);
						csi.setLot(ouc.getGoodsBatchId());
						csi.setOutStockOrderId(allInfoOutBill.getBillId());
						csi.setOutStockOrderLineNum(String.valueOf(osl.getOutBillRow()));
						csi.setProductQty(1);
						csi.setReceptDate(null);
						csi.setSupplierName(osl.getProvName());
						csi.setSupplierNum(osl.getProvCode());
						csi.setUnit(osl.getUnit());
						inventoryList.add(csi);
					}
				}
			}
		}
		String sql = "update out_stock set last_update_datetime = ?, trans_flag = ? where id = ?";
		//String json = Json.writeObject(inventoryList);
		//FileUtils.write(new File("E:\\SPD开发\\项目\\save\\josn20180608.josn"), json,"utf-8");
		try {
			//增加推送数据状态标志位
			outStockBill.setTransFlag(1);
			outStockBill.setLastUpdateDatetime(new Date());
			simpleDao.executeSql(sql,outStockBill.getLastUpdateDatetime(),outStockBill.getTransFlag(),outStockBill.getId());
			iStockCaller.pushConsumablesInventory(inventoryList);
		} catch (Exception e) {
			log.error("#############################一级科室出库推送信息去高值柜{}, 推送失败, 错误信息: => {}",
					ExceptionMessage.fromException(e));
			return "fail";
		}
		outStockBill.setTransFlag(2);
		outStockBill.setLastUpdateDatetime(new Date());
		simpleDao.executeSql(sql,outStockBill.getLastUpdateDatetime(),outStockBill.getTransFlag(),outStockBill.getId());
		return "success";
	}

	/**
	 * 二级库退货出库审核后将高值的出库信息推送至高值柜
	 * @param outStockBillId
	 * @return
	 * @throws Exception
	 */
	@Override
	public String callPushOutInfo2(String outStockBillId) throws Exception {
		OutStockBillSupVo allInfoOutBill = returnOutDao.getAllInfoOutBill(outStockBillId);
		List<OutStockBillListSupVo> outStockBillListSupVos = allInfoOutBill.getlOutStockList();
		OutStock outStockBill = new OutStock();
		Tx.transform(allInfoOutBill).to(outStockBill);
		List<ReturnPremium> returnPremiums = new ArrayList<>();
		for(OutStockBillListSupVo osl:outStockBillListSupVos){
			if(osl.getlOutStockUniqueCode()!=null&&osl.getlOutStockUniqueCode().size()>0){
				for(OutStockUniqueCode ouc:osl.getlOutStockUniqueCode()){
					if(osl.getId().equals(ouc.getPid())){
						ReturnPremium rp = new ReturnPremium();
						rp.setCount(1);
						rp.setDeptCode(allInfoOutBill.getOutDeptId());
						rp.setEpc(ouc.getUniqueCode());
						rp.setItemCode(ouc.getGoodsId());
						rp.setStockCode(allInfoOutBill.getOutStocId());
						rp.setLastUpdateDate(ouc.getLastUpdateDatetime());
						returnPremiums.add(rp);
					}
				}
			}
		}
		String sql = "update out_stock set last_update_datetime = ?, trans_flag = ? where id = ?";
		try {
			outStockBill.setTransFlag(1);
			outStockBill.setLastUpdateDatetime(new Date());
			simpleDao.executeSql(sql,outStockBill.getLastUpdateDatetime(),outStockBill.getTransFlag(),outStockBill.getId());
			iStockCaller.pushApplyReturnStocks(returnPremiums);
		} catch (Exception e) {
			log.error("#############################二级科室退货推送信息去高值柜{}, 推送失败, 错误信息: => {}",
					ExceptionMessage.fromException(e));
			return "fail";
		}
		outStockBill.setTransFlag(2);
		outStockBill.setLastUpdateDatetime(new Date());
		simpleDao.executeSql(sql,outStockBill.getLastUpdateDatetime(),outStockBill.getTransFlag(),outStockBill.getId());
		return "success";
	}

	@Override
	public String getStocIdByEname(String ename){
		String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
		return callForRivaDao.getStocIdByEname(hosId,ename);
	}
}


