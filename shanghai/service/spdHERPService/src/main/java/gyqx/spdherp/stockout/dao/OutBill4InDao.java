package gyqx.spdherp.stockout.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import gyqx.spdherp.barcodePrintingCenter.vo.DsGoodsVo;
import gyqx.spdherp.po.OutStockList;
import gyqx.spdherp.stockout.vo.Out4UseVo;
import gyqx.spdherp.stockout.vo.OutStockBatchVo;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
import gyqx.spdherp.stockout.vo.SickerUserListVo;
import gyqx.spdherp.stockout.vo.vo4In.Out4Settle;
import org.springframework.stereotype.Repository;

import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.stockout.dao.mapper.BillMgrMapper;
import gyqx.spdherp.stockout.dao.mapper.OutBill4InMapper;
import gyqx.spdherp.stockout.vo.vo4In.OutBill4InVo;
import gyqx.spdherp.stockout.vo.vo4In.OutSub4InVo;

@Repository
public class OutBill4InDao {
	@Resource
	private BillMgrMapper mapper;
	@Resource
	private OutBill4InMapper o4iMapper;

	public List<OutSub4InVo> getSubs(String billId, String isPacket) {
		return o4iMapper.getSubs(billId, isPacket);
	}

	public List<OutStockBatch> getBatchs(String billId) {
		return o4iMapper.getBatchs(billId);
	}
	
	public List<OutStockBatch> getBatchsByPid(String billId,String pid) {
		return o4iMapper.getBatchsByPid(billId,pid);
	}

	public List<OutStockUniqueCode> getUniqueCodes(String billId) {
		return o4iMapper.getUniqueCodes(billId);
	}
	public List<OutStockUniqueCode> getUniqueCodesByPid(String billId,String pid,int pRowId) {
		return o4iMapper.getUniqueCodesByPid(billId,pid,pRowId);
	}
	public Integer updateUniqueCodesUsed(List<OutStockUniqueCode> lst){return o4iMapper.updateUniqueCodesUsed(lst);}
	public OutBill4InVo getOutBill(String billId) {
		return o4iMapper.getOutBill(billId);
	}

	public List<Out4Settle> outBill4settle(Out4Settle bean) {
		return o4iMapper.outBill4settle(bean);
	}

	public List<OutBill4InVo> list4Instock(OutBill4InVo qb) {
		return o4iMapper.list4Instock(qb);
	}
	public  int updateOutBillLstStatus(List<OutStockList> lst){

		return o4iMapper.updateOutBillLstStatus(lst);
	}

	public int updateBatchUseQty(BigDecimal useQty, String billId, String goodsId, String batchCode, String goodsBatchId){
		return o4iMapper.updateBatchUseQty(useQty, billId, goodsId, batchCode, goodsBatchId);
	}

	public Out4UseVo outBill4Use(Out4UseVo query) {
		return o4iMapper.outBill4Use(query);
	}

	public int updateOutStockStatus(OutStock outStock) {
		return o4iMapper.updateOutStockStatus(outStock);
	}

	public int stockpilebatch(OutStockBillVo outStockBillVo) {
		// TODO Auto-generated method stub
		return o4iMapper.stockpilebatch(outStockBillVo);
	}

	public List<OutStockUniqueCode> getUniqueCodeList(String outBillId) {
		// TODO Auto-generated method stub
		return o4iMapper.getUniqueCodeList(outBillId);
	}
	public List<DsGoodsVo> getDSGoodsByOutBill(DsGoodsVo qb) {
		// TODO Auto-generated method stub
		return o4iMapper.getDSGoodsByOutBill(qb);
	}
	public int setOutBatchQty(List<OutStockBatchVo> lst4Update){
		return  o4iMapper.setOutBatchQty(lst4Update);
	}
}
