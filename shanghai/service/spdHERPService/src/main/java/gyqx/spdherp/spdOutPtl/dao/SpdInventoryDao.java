package gyqx.spdherp.spdOutPtl.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.po.JdeGoodsStockInfo;
import gyqx.spdherp.po.SpdInventoryList;
import gyqx.spdherp.spdOutPtl.dao.mapper.ISpdInventoryMapper;
import gyqx.spdherp.spdOutPtl.vo.DeptBuyForSpdPtl;
import gyqx.spdherp.spdOutPtl.vo.SpdInventoryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.po.SpdInventory;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class SpdInventoryDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ISpdInventoryMapper mapper;

	public SpdInventory get(String id)
	{
		return dao.get(SpdInventory.class, id);	
	}	

	public SpdInventory insertAndGet(SpdInventory spdInventory) throws Exception 
	{
		return dao.insertAndGet(spdInventory);
	}

	public SpdInventory updateAndGet(SpdInventory spdInventory) throws Exception {
		return dao.updateAndGet(spdInventory);
	}
	public int updateFields(SpdInventory spdInventory,String fieldNames) throws Exception
	{
		return dao.updateFields(spdInventory, fieldNames);
	}
	public int updateNotNullFields(SpdInventory spdInventory) throws Exception
	{
		return dao.updateNotNullFields(spdInventory);
	}

	public void delete(SpdInventory spdInventory) throws Exception {
		dao.delete(spdInventory);
	}
	
	public SpdInventory getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (SpdInventory) dao.getByFieldName(SpdInventory.class, fieldName, fieldValue);
	}
	
	public List<SpdInventory> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<SpdInventory>) dao.queryBeanList(SpdInventory.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(SpdInventory.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<SpdInventoryVo> list(SpdInventoryVo qbean) throws Exception{
		if(qbean.getId()!=null){
			return mapper.getPtlInfo(qbean);
		}else {
			return mapper.list(qbean);
		}
	}
	public List<DeptBuyForSpdPtl> getIdAndName(String erpCode,String hosId){
		return mapper.getIdAndName(erpCode,hosId);
	}
	public int insertBatchSpdInvertory(List<SpdInventoryList> spdInventoryLists){
		return mapper.insertBatchSpdInvertory(spdInventoryLists);
	}
	public String getId(String hosId){
		return mapper.getId(hosId);
	}
}


