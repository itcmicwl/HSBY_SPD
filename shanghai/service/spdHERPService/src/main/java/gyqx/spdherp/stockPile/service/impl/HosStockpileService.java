package gyqx.spdherp.stockPile.service.impl;

import javax.annotation.Resource;


import com.mysql.jdbc.StringUtils;
import gyqx.spdherp.stockPile.dao.HosStockpileDao;
import gyqx.spdherp.stockPile.service.IHosStockpileService;
import gyqx.spdherp.stockPile.vo.HosStockPileVo;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import java.util.stream.Collectors;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.HosStockpile;

@Service
public class HosStockpileService implements IHosStockpileService {
	
	@Resource
	private HosStockpileDao dao;
	
	public HosStockpile get(String id)
	{
		return dao.get( id);	
	}	

	public HosStockpile add(HosStockpile hosStockpile) throws Exception 
	{
		return dao.insertAndGet(hosStockpile);
	}
		
	public HosStockpile update(HosStockpile hosStockpile) throws Exception {
		return dao.updateAndGet(hosStockpile);
	}
	public int updateFields(HosStockpile hosStockpile,String fieldNames) throws Exception
	{
		return dao.updateFields(hosStockpile, fieldNames);
	}

	public void delete(HosStockpile hosStockpile) throws Exception {
		dao.delete(hosStockpile);
	}
	
	public HosStockpile getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosStockpile) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<HosStockpile> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosStockpile>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<HosStockpile> list(HosStockpile bean) throws Exception
	{
		return (List<HosStockpile>) dao.list(bean);
	}	

	public QueryResult<HosStockpile> listByPage(QueryInfo<HosStockpile> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	@Override
	public int insertByBatch(List<HosStockpile> lst) throws Exception {
		return dao.insertByBatch(lst);
	}

	@Override
	public boolean addOrUpdateByBatch(List<HosStockpile> lst) throws Exception {
//		List<HosStockpile> lst4Add = lst.stream().filter(o -> !StringUtils.isNullOrEmpty(o.getUniqueCode())).collect(Collectors.toList());
		return false;
	}

	@Override
	public List<HosStockPileVo> getGoodsStoc(HosStockPileVo query) throws Exception {
		return dao.getGoodsStoc(query);
	}
}


