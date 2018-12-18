package gyqx.spdherp.returnGoods.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.ReturnedGoodsList;
import gyqx.spdherp.returnGoods.dao.ReturnedGoodsListDao;
import gyqx.spdherp.returnGoods.service.IReturnedGoodsListService;

@Service
public class ReturnedGoodsListService implements IReturnedGoodsListService {
	
	@Resource
	private ReturnedGoodsListDao  dao;
	
	public ReturnedGoodsList get(String id)
	{
		return dao.get( id);	
	}	

	public ReturnedGoodsList add(ReturnedGoodsList returnedGoodsList) throws Exception 
	{
		return dao.insertAndGet(returnedGoodsList);
	}
		
	public ReturnedGoodsList update(ReturnedGoodsList returnedGoodsList) throws Exception {
		return dao.updateAndGet(returnedGoodsList);
	}
	public int updateFields(ReturnedGoodsList returnedGoodsList,String fieldNames) throws Exception
	{
		return dao.updateFields(returnedGoodsList, fieldNames);
	}

	public void delete(ReturnedGoodsList returnedGoodsList) throws Exception {
		dao.delete(returnedGoodsList);
	}
	
	public ReturnedGoodsList getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ReturnedGoodsList) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<ReturnedGoodsList> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ReturnedGoodsList>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<ReturnedGoodsList> list(ReturnedGoodsList bean) throws Exception
	{
		return (List<ReturnedGoodsList>) dao.list(bean);
	}	
	
	public QueryResult<ReturnedGoodsList> listByPage(QueryInfo<ReturnedGoodsList> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


