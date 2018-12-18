package gyqx.spdherp.returnGoods.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.ExchangeGoods;
import gyqx.spdherp.returnGoods.dao.ExchangeGoodsDao;
import gyqx.spdherp.returnGoods.service.IExchangeGoodsService;

@Service
public class ExchangeGoodsService implements IExchangeGoodsService {
	
	@Resource
	private ExchangeGoodsDao  dao;
	
	public ExchangeGoods get(String id)
	{
		return dao.get( id);	
	}	

	public ExchangeGoods add(ExchangeGoods exchangeGoods) throws Exception 
	{
		return dao.insertAndGet(exchangeGoods);
	}
		
	public ExchangeGoods update(ExchangeGoods exchangeGoods) throws Exception {
		return dao.updateAndGet(exchangeGoods);
	}
	public int updateFields(ExchangeGoods exchangeGoods,String fieldNames) throws Exception
	{
		return dao.updateFields(exchangeGoods, fieldNames);
	}

	public void delete(ExchangeGoods exchangeGoods) throws Exception {
		dao.delete(exchangeGoods);
	}
	
	public ExchangeGoods getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ExchangeGoods) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<ExchangeGoods> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ExchangeGoods>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<ExchangeGoods> list(ExchangeGoods bean) throws Exception
	{
		return (List<ExchangeGoods>) dao.list(bean);
	}	
	
	public QueryResult<ExchangeGoods> listByPage(QueryInfo<ExchangeGoods> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


