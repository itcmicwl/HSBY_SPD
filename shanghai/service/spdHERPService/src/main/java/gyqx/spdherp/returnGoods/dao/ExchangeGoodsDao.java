package gyqx.spdherp.returnGoods.dao;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.returnGoods.dao.mapper.IExchangeGoodsMapper;
import gyqx.spdherp.po.ExchangeGoods;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class ExchangeGoodsDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IExchangeGoodsMapper mapper;

	public ExchangeGoods get(String id)
	{
		return dao.get(ExchangeGoods.class, id);	
	}	

	public ExchangeGoods insertAndGet(ExchangeGoods exchangeGoods) throws Exception 
	{
		return dao.insertAndGet(exchangeGoods);
	}

	public ExchangeGoods updateAndGet(ExchangeGoods exchangeGoods) throws Exception {
		return dao.updateAndGet(exchangeGoods);
	}
	public int updateFields(ExchangeGoods exchangeGoods,String fieldNames) throws Exception
	{
		return dao.updateFields(exchangeGoods, fieldNames);
	}
	public int updateNotNullFields(ExchangeGoods exchangeGoods) throws Exception
	{
		return dao.updateNotNullFields(exchangeGoods);
	}

	public void delete(ExchangeGoods exchangeGoods) throws Exception {
		dao.delete(exchangeGoods);
	}
	
	public ExchangeGoods getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ExchangeGoods) dao.getByFieldName(ExchangeGoods.class, fieldName, fieldValue);
	}
	
	public List<ExchangeGoods> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ExchangeGoods>) dao.queryBeanList(ExchangeGoods.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(ExchangeGoods.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<ExchangeGoods> list(ExchangeGoods qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


