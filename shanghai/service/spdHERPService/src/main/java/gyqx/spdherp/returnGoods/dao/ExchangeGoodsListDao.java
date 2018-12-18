package gyqx.spdherp.returnGoods.dao;


import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.returnGoods.dao.mapper.IExchangeGoodsListMapper;
import gyqx.spdherp.po.ExchangeGoodsList;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class ExchangeGoodsListDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IExchangeGoodsListMapper mapper;

	public ExchangeGoodsList get(String id)
	{
		return dao.get(ExchangeGoodsList.class, id);	
	}	

	public ExchangeGoodsList insertAndGet(ExchangeGoodsList exchangeGoodsList) throws Exception 
	{
		exchangeGoodsList.setId(atomUtil.newValue("exchange_goods_id"));
		exchangeGoodsList.setLastUpdateDatetime(new Date());
		return dao.insertAndGet(exchangeGoodsList);
	}

	public ExchangeGoodsList updateAndGet(ExchangeGoodsList exchangeGoodsList) throws Exception {
		return dao.updateAndGet(exchangeGoodsList);
	}
	public int updateFields(ExchangeGoodsList exchangeGoodsList,String fieldNames) throws Exception
	{
		return dao.updateFields(exchangeGoodsList, fieldNames);
	}
	public int updateNotNullFields(ExchangeGoodsList exchangeGoodsList) throws Exception
	{
		return dao.updateNotNullFields(exchangeGoodsList);
	}

	public void delete(ExchangeGoodsList exchangeGoodsList) throws Exception {
		dao.delete(exchangeGoodsList);
	}
	
	public ExchangeGoodsList getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ExchangeGoodsList) dao.getByFieldName(ExchangeGoodsList.class, fieldName, fieldValue);
	}
	
	public List<ExchangeGoodsList> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ExchangeGoodsList>) dao.queryBeanList(ExchangeGoodsList.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(ExchangeGoodsList.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<ExchangeGoodsList> list(ExchangeGoodsList qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


