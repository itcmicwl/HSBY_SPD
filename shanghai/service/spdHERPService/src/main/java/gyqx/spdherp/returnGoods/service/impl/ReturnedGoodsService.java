package gyqx.spdherp.returnGoods.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.ReturnedGoods;
import gyqx.spdherp.returnGoods.dao.ReturnedGoodsDao;
import gyqx.spdherp.returnGoods.service.IReturnedGoodsService;

@Service
public class ReturnedGoodsService implements IReturnedGoodsService {
	
	@Resource
	private ReturnedGoodsDao  dao;
	
	public ReturnedGoods get(String id)
	{
		return dao.get( id);	
	}	

	public ReturnedGoods add(ReturnedGoods returnedGoods) throws Exception 
	{
		return dao.insertAndGet(returnedGoods);
	}
		
	public ReturnedGoods update(ReturnedGoods returnedGoods) throws Exception {
		return dao.updateAndGet(returnedGoods);
	}
	public int updateFields(ReturnedGoods returnedGoods,String fieldNames) throws Exception
	{
		return dao.updateFields(returnedGoods, fieldNames);
	}

	public void delete(ReturnedGoods returnedGoods) throws Exception {
		dao.delete(returnedGoods);
	}
	
	public ReturnedGoods getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ReturnedGoods) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<ReturnedGoods> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ReturnedGoods>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<ReturnedGoods> list(ReturnedGoods bean) throws Exception
	{
		return (List<ReturnedGoods>) dao.list(bean);
	}	
	
	public QueryResult<ReturnedGoods> listByPage(QueryInfo<ReturnedGoods> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


