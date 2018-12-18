package gyqx.spdherp.stockMgr.dao;


import java.util.Arrays;
import java.util.List;


import javax.annotation.Resource;

import common.transform.Tx;
import gyqx.spdherp.po.InStock;
import gyqx.spdherp.stockMgr.Constance;
import gyqx.spdherp.stockMgr.vo.FillInstockVo;
import gyqx.spdherp.stockout.vo.BigBatch4FillVo;
import gyqx.spdherp.stockout.vo.vo4In.Out4Settle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import gyqx.spdherp.stockMgr.dao.mapper.IInStockMapper;
import gyqx.spdherp.stockMgr.vo.InStockVo;

@Repository
public class InStockDao 
{
	@Resource
	private SimpleDao dao;	

	@Resource
	private IInStockMapper mapper;

	public InStockVo getById(String id)
	{
		return mapper.getById(id);
	}
	public InStockVo getByBillId(String id)
	{
		return mapper.getByBillId(id);
	}

	public int insert(InStockVo inStock) throws Exception
	{
		return mapper.insert(inStock);
	}
	public int insertByBatch(List<InStock> lst) throws Exception{
		return mapper.insertByBatch(lst);
	}
	public int update(InStockVo inStock) throws Exception {
		return mapper.update(inStock);
	}

	/**
	 * 采购入库收货单列表
	 * @param qbean
	 * @return
	 * @throws Exception
	 */
	public List<InStockVo> purchaseList(InStockVo qbean) throws Exception{
		return mapper.purchaseList(qbean);
	}
	
	/**
	 * 请购入库单列表
	 * @param qbean
	 * @return
	 * @throws Exception
	 */
	public List<InStockVo> list(InStockVo qbean) throws Exception{
		return mapper.list(qbean);
	}
	public boolean auditAndunAudit(InStockVo inStock,boolean flag) throws  Exception{
		int status = flag? Constance.INSTOCK_STATUS_AUDIT:Constance.INSTOCK_STATUS_SAVE;
        inStock.setStatus(status);
		return  mapper.auditAndunAudit(inStock) > 0;
	}
	public int charge4Sur(String id,String accounter){
		return mapper.charge4Sur(id,accounter);
	}
	public boolean charge(InStockVo inStock) throws Exception{
	    int status = Constance.INSTOCK_STATUS_CHARGE;
        inStock.setStatus(status);
		int re =mapper.charge(inStock);
        if(re<0){
        	throw  new RuntimeException("记账失败");
		}
	    return re >= 0;
    }
	public List<Out4Settle> inBill4settle(Out4Settle bean) {
		return mapper.inBill4settle(bean);
	}
	public List<InStockVo> listByUniqueCode(List<String> qUniqueCodes) throws Exception{
		return mapper.listByUniqueCode(qUniqueCodes);
	}
	public List<FillInstockVo> getQGByBigBatchCode(List<BigBatch4FillVo> lstBigBatchs,String deptId){
		return mapper.getQGByBigBatchCode(lstBigBatchs,deptId);
	}
	public List<FillInstockVo> getCGByBigBatchCode(List<BigBatch4FillVo> lstBigBatchs){
		return mapper.getCGByBigBatchCode(lstBigBatchs);
	}
}


