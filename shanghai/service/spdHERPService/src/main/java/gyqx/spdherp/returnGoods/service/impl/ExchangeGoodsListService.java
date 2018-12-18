package gyqx.spdherp.returnGoods.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.ExchangeGoodsList;
import gyqx.spdherp.returnGoods.dao.ExchangeGoodsListDao;
import gyqx.spdherp.returnGoods.service.IExchangeGoodsListService;

@Service
public class ExchangeGoodsListService implements IExchangeGoodsListService {
	
	@Resource
	private ExchangeGoodsListDao  dao;
	
	public ExchangeGoodsList get(String id)
	{
		return dao.get( id);	
	}	

	public ExchangeGoodsList add(ExchangeGoodsList exchangeGoodsList) throws Exception 
	{
		return dao.insertAndGet(exchangeGoodsList);
	}
		
	public ExchangeGoodsList update(ExchangeGoodsList exchangeGoodsList) throws Exception {
		return dao.updateAndGet(exchangeGoodsList);
	}
	public int updateFields(ExchangeGoodsList exchangeGoodsList,String fieldNames) throws Exception
	{
		return dao.updateFields(exchangeGoodsList, fieldNames);
	}

	public void delete(ExchangeGoodsList exchangeGoodsList) throws Exception {
		dao.delete(exchangeGoodsList);
	}
	
	public ExchangeGoodsList getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ExchangeGoodsList) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<ExchangeGoodsList> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ExchangeGoodsList>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<ExchangeGoodsList> list(ExchangeGoodsList bean) throws Exception
	{
		return (List<ExchangeGoodsList>) dao.list(bean);
	}	
	
	public QueryResult<ExchangeGoodsList> listByPage(QueryInfo<ExchangeGoodsList> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


