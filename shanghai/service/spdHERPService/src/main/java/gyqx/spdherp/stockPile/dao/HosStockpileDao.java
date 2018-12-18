package gyqx.spdherp.stockPile.dao;


import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.annotation.Resource;

import gyqx.spdherp.stockPile.dao.mapper.IHosStockpileMapper;
import gyqx.spdherp.stockPile.vo.HosStockPileVo;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdherp.po.HosStockpile;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class HosStockpileDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IHosStockpileMapper mapper;

	public HosStockpile get(String id)
	{
		return dao.get(HosStockpile.class, id);	
	}	

	public HosStockpile insertAndGet(HosStockpile hosStockpile) throws Exception 
	{
		return dao.insertAndGet(hosStockpile);
	}

	public HosStockpile updateAndGet(HosStockpile hosStockpile) throws Exception {
		return dao.updateAndGet(hosStockpile);
	}
	public int updateFields(HosStockpile hosStockpile,String fieldNames) throws Exception
	{
		return dao.updateFields(hosStockpile, fieldNames);
	}
	public int updateNotNullFields(HosStockpile hosStockpile) throws Exception
	{
		return dao.updateNotNullFields(hosStockpile);
	}

	public void delete(HosStockpile hosStockpile) throws Exception {
		dao.delete(hosStockpile);
	}
	
	public HosStockpile getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosStockpile) dao.getByFieldName(HosStockpile.class, fieldName, fieldValue);
	}
	
	public List<HosStockpile> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosStockpile>) dao.queryBeanList(HosStockpile.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(HosStockpile.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<HosStockpile> list(HosStockpile qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
	public  int insertByBatch(List<HosStockpile> lst) throws  Exception{
		return mapper.insertByBatch(lst);
	}
	public List<HosStockPileVo> getGoodsStoc(HosStockPileVo query) throws Exception{
		return mapper.getGoodsStoc(query);
	}
}


