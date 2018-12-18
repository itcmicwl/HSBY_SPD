package gyqx.spdherp.spdOutPtl.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.spdOutPtl.dao.mapper.ISpdInventoryListMapper;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.po.SpdInventoryList;
@Repository
public class SpdInventoryListDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ISpdInventoryListMapper mapper;

	public SpdInventoryList get(String id)
	{
		return dao.get(SpdInventoryList.class, id);	
	}	

	public SpdInventoryList insertAndGet(SpdInventoryList spdInventoryList) throws Exception 
	{
		return dao.insertAndGet(spdInventoryList);
	}

	public SpdInventoryList updateAndGet(SpdInventoryList spdInventoryList) throws Exception {
		return dao.updateAndGet(spdInventoryList);
	}
	public int updateFields(SpdInventoryList spdInventoryList,String fieldNames) throws Exception
	{
		return dao.updateFields(spdInventoryList, fieldNames);
	}
	public int updateNotNullFields(SpdInventoryList spdInventoryList) throws Exception
	{
		return dao.updateNotNullFields(spdInventoryList);
	}

	public void delete(SpdInventoryList spdInventoryList) throws Exception {
		dao.delete(spdInventoryList);
	}
	
	public SpdInventoryList getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (SpdInventoryList) dao.getByFieldName(SpdInventoryList.class, fieldName, fieldValue);
	}
	
	public List<SpdInventoryList> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<SpdInventoryList>) dao.queryBeanList(SpdInventoryList.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(SpdInventoryList.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<SpdInventoryList> list(SpdInventoryList qbean) throws Exception{		
		
		return mapper.list(qbean);
	}

	public int updateBatch(String pid){
		return mapper.updateBatch(pid);
	}
	public int countState(String pid){
		return mapper.countState(pid);
	}
	public List<SpdInventoryList> getSpdInventoryList(String hosId){
		return mapper.getSpdInventoryList(hosId);
	}
}


