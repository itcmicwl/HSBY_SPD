package gyqx.spdherp.spdOutPtl.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.spdOutPtl.dao.mapper.IJdeGoodsStockInfoMapper;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.po.JdeGoodsStockInfo;


@Repository
public class JdeGoodsStockInfoDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IJdeGoodsStockInfoMapper mapper;

	public JdeGoodsStockInfo get(String id)
	{
		return dao.get(JdeGoodsStockInfo.class, id);	
	}	

	public JdeGoodsStockInfo insertAndGet(JdeGoodsStockInfo jdeGoodsStockInfo) throws Exception 
	{
		return dao.insertAndGet(jdeGoodsStockInfo);
	}

	public JdeGoodsStockInfo updateAndGet(JdeGoodsStockInfo jdeGoodsStockInfo) throws Exception {
		return dao.updateAndGet(jdeGoodsStockInfo);
	}
	public int updateFields(JdeGoodsStockInfo jdeGoodsStockInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(jdeGoodsStockInfo, fieldNames);
	}
	public int updateNotNullFields(JdeGoodsStockInfo jdeGoodsStockInfo) throws Exception
	{
		return dao.updateNotNullFields(jdeGoodsStockInfo);
	}

	public void delete(JdeGoodsStockInfo jdeGoodsStockInfo) throws Exception {
		dao.delete(jdeGoodsStockInfo);
	}
	
	public JdeGoodsStockInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (JdeGoodsStockInfo) dao.getByFieldName(JdeGoodsStockInfo.class, fieldName, fieldValue);
	}
	
	public List<JdeGoodsStockInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<JdeGoodsStockInfo>) dao.queryBeanList(JdeGoodsStockInfo.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(JdeGoodsStockInfo.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<JdeGoodsStockInfo> list(JdeGoodsStockInfo qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


