package gyqx.spdherp.stockMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.InStock;
import gyqx.spdherp.stockMgr.vo.FillInstockVo;
import gyqx.spdherp.stockout.vo.BigBatch4FillVo;
import gyqx.spdherp.stockout.vo.vo4In.Out4Settle;
import org.apache.ibatis.annotations.Param;

import gyqx.spdherp.stockMgr.vo.InStockVo;
 
public interface IInStockMapper {
	InStockVo getById(@Param("id") String id);
	InStockVo getByBillId(@Param("billId") String billId);
	int update(InStockVo bean);
	int insert(InStockVo bean);
	int insertByBatch(List<InStock> bean);
	//int delete(@Param("id") String id);
	int auditAndunAudit(InStockVo queryInfo);
	int charge(InStockVo inStockVo);
	int charge4Sur(@Param("id")String id,@Param("accounter")String accounter);
	/**
	 * 采购入库收货单列表
	 * @param queryInfo
	 * @return
	 */
	List<InStockVo> purchaseList(InStockVo queryInfo) ;
	
	/**
	 * 请购入库单列表
	 * @param queryInfo
	 * @return
	 */
	List<InStockVo> list(InStockVo queryInfo) ;
	List<Out4Settle> inBill4settle(Out4Settle bean);
	List<InStockVo> listByUniqueCode(@Param("qUniqueCodes") List<String> qUniqueCodes);
	List<FillInstockVo> getQGByBigBatchCode(@Param("lstBigBatchs")List<BigBatch4FillVo> lstBigBatchs,@Param("deptId")String deptId);
	List<FillInstockVo> getCGByBigBatchCode(@Param("lstBigBatchs") List<BigBatch4FillVo> lstBigBatchs);
}

