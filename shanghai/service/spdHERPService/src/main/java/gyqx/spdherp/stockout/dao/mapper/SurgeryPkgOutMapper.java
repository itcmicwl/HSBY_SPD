package gyqx.spdherp.stockout.dao.mapper;

import gyqx.spdherp.po.*;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurgeryPkgOutMapper {

    List<SurgeryPkg4UseVo> querySickerSurPkg(SurgeryPkg4UseVo surgeryPkg4UseVo);
	
	/**
	 * 获取批次列表
	 * @param outBillId
	 * @return
	 */
	List<OutStockBatchVo> queryOutStockBatchByBillId(String outBillId);
	
	List<SurgeryPkgList> findSurgeryPkgLists(SurgeryPkgList surgeryPkgList);
	
	/**
	 * 批量更新批次表使用状态
	 * @param stockBatches
	 */
	void updateBatchSurgeryUseQty(List<OutStockBatchVo> stockBatches);
	
	/**
	 * 修改手术包商品使用数量
	 * @param surgeryPkg4UseBatchVosForUpdate
	 */
	void batchUpdateSurgeryPkgBatchUseQty(List<SurgeryPkg4UseBatchVo> surgeryPkg4UseBatchVosForUpdate);
	/**
	 * 更新手术包主表状态
	 * @param surgeryPkgList
	 */
	void batchUpdateSurgeryPkgStatus(List<SurgeryPkg> surgeryPkgList);
	
	/**
	 * 更新手术包商品明细个商品的消耗 状态
	 * @param surgeryPkgListDetail
	 */
	void batchUpdateSurgeryPkgListStatus(List<SurgeryPkgList> surgeryPkgListDetail);
	
	/**
	 * 根据手术包号获取对应的出库单
	 * @param surCode
	 * @return
	 */
	List<OutStock> getOutStockBySurCode(String surCode);
	
	List<EntireOutStockVo> getWholeOutStockByBigBatchCode(@Param("bigBatchCodesList") List<String> bigBatchCodesList, @Param("hosId") String hosId, @Param("fillType")int fillType);
	
	void insertFillOutStocks(@Param("outStockVos")List<EntireOutStockVo> outStockVos);
	
	void insertFillOutStockLists(@Param("outStockListVos")List<EntireOutStockListVo> outStockListVos);
	
	void insertFillOutStockBatchs(@Param("insertFillOutStockBatchs")List<OutStockBatch> insertFillOutStockBatchs);
	
	List<InStockVo> getWholeInStockByBigBatchCode(@Param("bigBatchCodesList") List<String> bigBatchCodesList);
	
	List<SurgeryPkgBatch> surgeryPkgBatchSub(SurgeryPkgBatch queryVo);
}
