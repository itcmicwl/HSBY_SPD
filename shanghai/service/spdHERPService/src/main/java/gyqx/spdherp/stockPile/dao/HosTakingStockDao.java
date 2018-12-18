package gyqx.spdherp.stockPile.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.stockPile.dao.mapper.IHosTakingStockMapper;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.po.HosTakingStock;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class HosTakingStockDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IHosTakingStockMapper mapper;

	public HosTakingStock get(String id)
	{
		return dao.get(HosTakingStock.class, id);	
	}	

	public HosTakingStock insertAndGet(HosTakingStock hosTakingStock) throws Exception 
	{
		return dao.insertAndGet(hosTakingStock);
	}

	public HosTakingStock updateAndGet(HosTakingStock hosTakingStock) throws Exception {
		return dao.updateAndGet(hosTakingStock);
	}
	public int updateFields(HosTakingStock hosTakingStock,String fieldNames) throws Exception
	{
		return dao.updateFields(hosTakingStock, fieldNames);
	}
	public int updateNotNullFields(HosTakingStock hosTakingStock) throws Exception
	{
		return dao.updateNotNullFields(hosTakingStock);
	}

	public void delete(HosTakingStock hosTakingStock) throws Exception {
		dao.delete(hosTakingStock);
	}
	
	public HosTakingStock getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosTakingStock) dao.getByFieldName(HosTakingStock.class, fieldName, fieldValue);
	}
	
	public List<HosTakingStock> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosTakingStock>) dao.queryBeanList(HosTakingStock.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(HosTakingStock.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<HosTakingStock> list(HosTakingStock qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


