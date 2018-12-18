package gyqx.spdherp.returnGoods.dao;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdherp.po.ReturnedGoodsList;
import gyqx.spdherp.returnGoods.dao.mapper.IReturnedGoodsListMapper;

@Repository
public class ReturnedGoodsListDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IReturnedGoodsListMapper mapper;

	public ReturnedGoodsList get(String id)
	{
		return dao.get(ReturnedGoodsList.class, id);	
	}	

	public ReturnedGoodsList insertAndGet(ReturnedGoodsList returnedGoodsList) throws Exception 
	{
		returnedGoodsList.setId(atomUtil.newValue("exchange_goods_id"));
		returnedGoodsList.setLastUpdateDatetime(new Date());
		return dao.insertAndGet(returnedGoodsList);
	}

	public ReturnedGoodsList updateAndGet(ReturnedGoodsList returnedGoodsList) throws Exception {
		return dao.updateAndGet(returnedGoodsList);
	}
	public int updateFields(ReturnedGoodsList returnedGoodsList,String fieldNames) throws Exception
	{
		return dao.updateFields(returnedGoodsList, fieldNames);
	}
	public int updateNotNullFields(ReturnedGoodsList returnedGoodsList) throws Exception
	{
		return dao.updateNotNullFields(returnedGoodsList);
	}

	public void delete(ReturnedGoodsList returnedGoodsList) throws Exception {
		dao.delete(returnedGoodsList);
	}
	
	public ReturnedGoodsList getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ReturnedGoodsList) dao.getByFieldName(ReturnedGoodsList.class, fieldName, fieldValue);
	}
	
	public List<ReturnedGoodsList> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ReturnedGoodsList>) dao.queryBeanList(ReturnedGoodsList.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(ReturnedGoodsList.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<ReturnedGoodsList> list(ReturnedGoodsList qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


