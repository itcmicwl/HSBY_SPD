package gyqx.spdherp.spdOutPtl.service.impl;

import javax.annotation.Resource;

import gyqx.spdherp.spdOutPtl.dao.JdeGoodsStockInfoDao;
import gyqx.spdherp.spdOutPtl.service.IJdeGoodsStockInfoService;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.JdeGoodsStockInfo;

@Service
public class JdeGoodsStockInfoService implements IJdeGoodsStockInfoService {
	
	@Resource
	private JdeGoodsStockInfoDao dao;
	
	public JdeGoodsStockInfo get(String id)
	{
		return dao.get( id);	
	}	

	public JdeGoodsStockInfo add(JdeGoodsStockInfo jdeGoodsStockInfo) throws Exception 
	{
		return dao.insertAndGet(jdeGoodsStockInfo);
	}
		
	public JdeGoodsStockInfo update(JdeGoodsStockInfo jdeGoodsStockInfo) throws Exception {
		return dao.updateAndGet(jdeGoodsStockInfo);
	}
	public int updateFields(JdeGoodsStockInfo jdeGoodsStockInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(jdeGoodsStockInfo, fieldNames);
	}

	public void delete(JdeGoodsStockInfo jdeGoodsStockInfo) throws Exception {
		dao.delete(jdeGoodsStockInfo);
	}
	
	public JdeGoodsStockInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (JdeGoodsStockInfo) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<JdeGoodsStockInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<JdeGoodsStockInfo>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<JdeGoodsStockInfo> list(JdeGoodsStockInfo bean) throws Exception
	{
		return (List<JdeGoodsStockInfo>) dao.list(bean);
	}	
	
	public QueryResult<JdeGoodsStockInfo> listByPage(QueryInfo<JdeGoodsStockInfo> queryInfo) throws Exception{		
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


