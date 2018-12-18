package gyqx.spdherp.stockPile.service.impl;

import javax.annotation.Resource;

import gyqx.spdherp.stockPile.dao.HosTakingStockDao;
import gyqx.spdherp.stockPile.service.IHosTakingStockService;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.HosTakingStock;

@Service
public class HosTakingStockService implements IHosTakingStockService {
	
	@Resource
	private HosTakingStockDao dao;
	
	public HosTakingStock get(String id)
	{
		return dao.get( id);	
	}	

	public HosTakingStock add(HosTakingStock hosTakingStock) throws Exception 
	{
		return dao.insertAndGet(hosTakingStock);
	}
		
	public HosTakingStock update(HosTakingStock hosTakingStock) throws Exception {
		return dao.updateAndGet(hosTakingStock);
	}
	public int updateFields(HosTakingStock hosTakingStock,String fieldNames) throws Exception
	{
		return dao.updateFields(hosTakingStock, fieldNames);
	}

	public void delete(HosTakingStock hosTakingStock) throws Exception {
		dao.delete(hosTakingStock);
	}
	
	public HosTakingStock getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosTakingStock) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<HosTakingStock> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosTakingStock>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<HosTakingStock> list(HosTakingStock bean) throws Exception
	{
		return (List<HosTakingStock>) dao.list(bean);
	}	
	
	public QueryResult<HosTakingStock> listByPage(QueryInfo<HosTakingStock> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


