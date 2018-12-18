package gyqx.spdherp.stockMgr.service;


import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.OutStockBatch;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.stockMgr.vo.InStockInfoVo;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.vo.BigBatch4FillVo;
import gyqx.spdherp.stockout.vo.EntireOutStockListVo;
import gyqx.spdherp.stockout.vo.vo4In.Out4Settle;

public interface IInStockService 
{
	/**
	 * 根据ID获取入库单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	InStockVo getById(String id) throws Exception ;
	InStockVo getByBillId(String billId) throws Exception ;
	InStockInfoVo getInStockInfoVo(List<String> uniqueCodes) throws Exception;
	/**
	 * 入库单审核
	 * @param inStock 应包含入库单ID，审核人，数据版本
	 * @return
	 * @throws Exception
	 */
	boolean audit(InStockVo inStock) throws  Exception;

	/**
	 * 取消审核
	 * @param inStock 应包含入库单ID，审核人，数据版本
	 * @return
	 * @throws Exception
	 */
	boolean unAudit(InStockVo inStock) throws  Exception;

	/**
	 * 记账（写库存表）
	 * @param inStock 应该包含入库单ID，记账人，数据版本
	 * @return
	 * @throws Exception
	 */
	boolean charge(InStockVo inStock) throws  Exception;
	InStockVo add(InStockVo inStock) throws Exception ;
	InStockVo addSup(InStockVo inStock) throws Exception;
	InStockVo add4Return(InStockVo instock) throws Exception;
	InStockVo update(InStockVo inStock) throws Exception;
	//void  delete(String id) throws Exception;
	List<InStockVo> list(InStockVo bean) throws Exception;
	QueryResult<InStockVo> listByPage(QueryInfo<InStockVo> queryInfo) throws Exception;
	/**
	 * 入库单结算
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	QueryResult<Out4Settle> inBill4settle(QueryInfo<Out4Settle> queryInfo) throws Exception;
	InStockVo getById4Instock(String id) throws Exception;
	List<InStockVo> listByUniqueCode(List<String> qUniqueCodes) throws Exception;

	/**
	 * 病人消耗补高值入库单
	 * @param uniqueCode
	 * @param outUniqueCodes
	 * @param flag -1 or 1
	 * @param execDeptId 病人消耗，执行科室Id
	 * @return
	 * @throws Exception
	 */
	int addFillInstockBill(List<String> uniqueCode, List<OutStockUniqueCode> outUniqueCodes,Integer flag, String execDeptId) throws Exception;
	int addFillInstockBillByBigBatchCode(List<BigBatch4FillVo> bigBatchCodes, List<EntireOutStockListVo> lstOutList, Integer flag, String execDeptId) throws Exception;
}


