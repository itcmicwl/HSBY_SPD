package gyqx.spdherp.spdOutPtl.service.impl;

import javax.annotation.Resource;

import gyqx.spdherp.spdOutPtl.dao.SpdInventoryListDao;
import gyqx.spdherp.spdOutPtl.service.ISpdInventoryListService;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SpdInventoryList;

@Service
public class SpdInventoryListService implements ISpdInventoryListService {
	
	@Resource
	private SpdInventoryListDao dao;
	
	public SpdInventoryList get(String id)
	{
		return dao.get( id);	
	}	

	public SpdInventoryList add(SpdInventoryList spdInventoryList) throws Exception 
	{
		return dao.insertAndGet(spdInventoryList);
	}
		
	public SpdInventoryList update(SpdInventoryList spdInventoryList) throws Exception {
		return dao.updateAndGet(spdInventoryList);
	}
	public int updateFields(SpdInventoryList spdInventoryList,String fieldNames) throws Exception
	{
		return dao.updateFields(spdInventoryList, fieldNames);
	}

	public void delete(SpdInventoryList spdInventoryList) throws Exception {
		dao.delete(spdInventoryList);
	}
	
	public SpdInventoryList getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (SpdInventoryList) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<SpdInventoryList> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<SpdInventoryList>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<SpdInventoryList> list(SpdInventoryList bean) throws Exception
	{
		return (List<SpdInventoryList>) dao.list(bean);
	}	
	
	public QueryResult<SpdInventoryList> listByPage(QueryInfo<SpdInventoryList> queryInfo) throws Exception{		
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


