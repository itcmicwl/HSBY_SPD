package gyqx.spdherp.stockout.dao;

import gyqx.spdherp.po.*;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.dao.mapper.SurgeryPkgOutMapper;
import gyqx.spdherp.stockout.vo.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SurgeryPkgOutDao {

    @Resource
    private SurgeryPkgOutMapper surgeryPkgOutMapper;
    public List<SurgeryPkg4UseVo> querySickerSurPkg(SurgeryPkg4UseVo surgeryPkg4UseVo) {
        return  surgeryPkgOutMapper.querySickerSurPkg(surgeryPkg4UseVo);
    }
	
	/**
	 * 根据出库单号获取出库批次列表
	 * @param outBillId
	 * @return
	 */
	public List<OutStockBatchVo> queryOutStockBatchByBillId(String outBillId) {
    	return  surgeryPkgOutMapper.queryOutStockBatchByBillId(outBillId);
	}
	
	public void updateBatchSurgeryUseQty(List<OutStockBatchVo> stockBatches) {
		surgeryPkgOutMapper.updateBatchSurgeryUseQty(stockBatches);
	}
	
	/**
	 * 修改手术包商品使用数量
	 * @param surgeryPkg4UseBatchVosForUpdate
	 */
	public void batchUpdateSurgeryPkgBatchUseQty(List<SurgeryPkg4UseBatchVo> surgeryPkg4UseBatchVosForUpdate) {
		surgeryPkgOutMapper.batchUpdateSurgeryPkgBatchUseQty(surgeryPkg4UseBatchVosForUpdate);
	}
	
	/**
	 * 更新手术包主表状态
	 * @param surgeryPkgList
	 */
	public void batchUpdateSurgeryPkgStatus(List<SurgeryPkg> surgeryPkgList) {
		surgeryPkgOutMapper.batchUpdateSurgeryPkgStatus(surgeryPkgList);
	}
	
	public List<SurgeryPkgList> findSurgeryPkgLists(SurgeryPkgList surgeryPkgList) {
		return surgeryPkgOutMapper.findSurgeryPkgLists(surgeryPkgList);
	}
	
	/**
	 * //更新手术包商品明细个商品的消耗 状态
	 * @param surgeryPkgListDetail
	 */
	public void batchUpdateSurgeryPkgListStatus(List<SurgeryPkgList> surgeryPkgListDetail) {
		surgeryPkgOutMapper.batchUpdateSurgeryPkgListStatus(surgeryPkgListDetail);
	}
	
	/**
	 * 补单据
	 * @param sickerUse
	 */
	public void fillBillBySickerUse(SickerUserVo sickerUse) {
	}
	
	/**
	 * 根据手术包号获取对应的请购出库单
	 * @param surCode
	 */
	public List<OutStock> getOutStockBySurCode(String surCode) {
		return surgeryPkgOutMapper.getOutStockBySurCode(surCode);
	}
	
	/**
	 *
	 * @param bigBatchCodesList
	 * @param hosId
	 * @param fillType
	 * @return
	 */
	public List<EntireOutStockVo> getWholeOutStockByBigBatchCode(List<String> bigBatchCodesList, String hosId, int fillType) {
		return surgeryPkgOutMapper.getWholeOutStockByBigBatchCode(bigBatchCodesList,hosId,fillType);
	}
	
	public void fillOutBills(List<EntireOutStockVo> outStockVos, List<EntireOutStockListVo> outStockListVos, List<OutStockBatch> outStockBatchListForInsert) {
		surgeryPkgOutMapper.insertFillOutStocks(outStockVos);
		surgeryPkgOutMapper.insertFillOutStockLists(outStockListVos);
		surgeryPkgOutMapper.insertFillOutStockBatchs(outStockBatchListForInsert);
		
	}
	
	public List<InStockVo> getWholeInStockByBigBatchCode(List<String> bigBatchCodesList) {
		return surgeryPkgOutMapper.getWholeInStockByBigBatchCode(bigBatchCodesList);
	}
	
	public List<SurgeryPkgBatch> querySurgeryPkgBatchSub(SurgeryPkgBatch queryVo) {
		return surgeryPkgOutMapper.surgeryPkgBatchSub(queryVo);
	}
}
