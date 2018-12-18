package gyqx.spdhdi.myInfos.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdhdi.myInfos.dao.mapper.IProvRegistGoodsPushMapper;
import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQ;
import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQR;
import gyqx.spdhdi.po.ProvRegistGoodsPush;

@Repository
public class ProvRegistGoodsPushDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IProvRegistGoodsPushMapper mapper;
	
	public List<ProvRegistGoodsPushQR> list(ProvRegistGoodsPushQ qbean) throws Exception{		
		return mapper.pushedRegCertsList(qbean);
	}

	public ProvRegistGoodsPush get(String id)
	{
		return dao.get(ProvRegistGoodsPush.class, id);	
	}	

	public ProvRegistGoodsPush insertAndGet(ProvRegistGoodsPush provRegistGoodsPush) throws Exception 
	{
		return dao.insertAndGet(provRegistGoodsPush);
	}

	public ProvRegistGoodsPush updateAndGet(ProvRegistGoodsPush provRegistGoodsPush) throws Exception {
		return dao.updateAndGet(provRegistGoodsPush);
	}
	public int updateFields(ProvRegistGoodsPush provRegistGoodsPush,String fieldNames) throws Exception
	{
		return dao.updateFields(provRegistGoodsPush, fieldNames);
	}
	public int updateNotNullFields(ProvRegistGoodsPush provRegistGoodsPush) throws Exception
	{
		return dao.updateNotNullFields(provRegistGoodsPush);
	}

	public void delete(ProvRegistGoodsPush provRegistGoodsPush) throws Exception {
		dao.delete(provRegistGoodsPush);
	}
	
	public ProvRegistGoodsPush getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ProvRegistGoodsPush) dao.getByFieldName(ProvRegistGoodsPush.class, fieldName, fieldValue);
	}
	
	public List<ProvRegistGoodsPush> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ProvRegistGoodsPush>) dao.queryBeanList(ProvRegistGoodsPush.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(ProvRegistGoodsPush.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<ProvRegistGoodsPush> list(ProvRegistGoodsPush qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


