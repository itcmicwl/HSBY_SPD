package gyqx.spdherp.applyMgr.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.applyMgr.vo.DeptBuyBillVo;
import gyqx.spdherp.applyMgr.vo.ProvGoodsKindsVo;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.applyMgr.dao.mapper.IDeptBuySubMapper;
import gyqx.spdherp.applyMgr.vo.DeptBuySubVo;
import gyqx.spdherp.po.DeptBuySub;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class DeptBuySubDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IDeptBuySubMapper mapper;

	public DeptBuySub get(String id)
	{
		return dao.get(DeptBuySub.class, id);	
	}	

	public DeptBuySub insertAndGet(DeptBuySub deptBuySub) throws Exception 
	{
		return dao.insertAndGet(deptBuySub);
	}

	public DeptBuySub updateAndGet(DeptBuySub deptBuySub) throws Exception {
		return dao.updateAndGet(deptBuySub);
	}
	public int updateFields(DeptBuySub deptBuySub,String fieldNames) throws Exception
	{
		return dao.updateFields(deptBuySub, fieldNames);
	}
	public int updateNotNullFields(DeptBuySub deptBuySub) throws Exception
	{
		return dao.updateNotNullFields(deptBuySub);
	}

	public void delete(DeptBuySub deptBuySub) throws Exception {
		dao.delete(deptBuySub);
	}
	
	public DeptBuySub getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (DeptBuySub) dao.getByFieldName(DeptBuySub.class, fieldName, fieldValue);
	}
	
	public List<DeptBuySub> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<DeptBuySub>) dao.queryBeanList(DeptBuySub.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(DeptBuySub.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<DeptBuySub> list(DeptBuySub qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
	
	public List<DeptBuySubVo> getVoList(DeptBuySubVo queryInfo)  throws Exception{	
		return mapper.getVoList(queryInfo);
	}
	public void deleteByBillId(String billId) throws Exception {
		mapper.deleteByBillId(billId);
	}
	public List<DeptBuySubVo> getDetailsVo4Pur(DeptBuySubVo queryInfo)  throws Exception{	
		return mapper.getDetailsVo4Pur(queryInfo);
	}
	public List<DeptBuySubVo> getDetailsVo4Merger(DeptBuySubVo queryInfo)  throws Exception{	
		return mapper.getDetailsVo4Merger(queryInfo);
	}
	public List<ProvGoodsKindsVo> getProvGoodsKinds(DeptBuySubVo queryInfo) throws Exception{
		return mapper.getProvGoodsKinds(queryInfo);
	}
	public List<DeptBuySubVo> getVoListByOut(String id) {
		// TODO Auto-generated method stub
		return mapper.getVoListByOut(id);
	}
	public List<DeptBuySubVo> getVoList4SurPack(String id,String stockId) {
		return mapper.getVoList4SurPack(id,stockId);
	}
}


