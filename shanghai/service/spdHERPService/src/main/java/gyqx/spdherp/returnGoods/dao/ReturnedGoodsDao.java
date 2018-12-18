package gyqx.spdherp.returnGoods.dao;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.returnGoods.dao.mapper.IReturnedGoodsMapper;
import gyqx.spdherp.po.ReturnedGoods;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class ReturnedGoodsDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IReturnedGoodsMapper mapper;

	public ReturnedGoods get(String id)
	{
		return dao.get(ReturnedGoods.class, id);	
	}	

	public ReturnedGoods insertAndGet(ReturnedGoods returnedGoods) throws Exception 
	{
		return dao.insertAndGet(returnedGoods);
	}

	public ReturnedGoods updateAndGet(ReturnedGoods returnedGoods) throws Exception {
		return dao.updateAndGet(returnedGoods);
	}
	public int updateFields(ReturnedGoods returnedGoods,String fieldNames) throws Exception
	{
		return dao.updateFields(returnedGoods, fieldNames);
	}
	public int updateNotNullFields(ReturnedGoods returnedGoods) throws Exception
	{
		return dao.updateNotNullFields(returnedGoods);
	}

	public void delete(ReturnedGoods returnedGoods) throws Exception {
		dao.delete(returnedGoods);
	}
	
	public ReturnedGoods getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ReturnedGoods) dao.getByFieldName(ReturnedGoods.class, fieldName, fieldValue);
	}
	
	public List<ReturnedGoods> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ReturnedGoods>) dao.queryBeanList(ReturnedGoods.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(ReturnedGoods.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<ReturnedGoods> list(ReturnedGoods qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


