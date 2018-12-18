package gyqx.spdherp.deptMgr.dao;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdherp.deptMgr.dao.mapper.IGoodsPurTemplateUserRelMapper;
import gyqx.spdherp.po.GoodsPurTemplateUserRel;

@Repository
public class GoodsPurTemplateUserRelDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IGoodsPurTemplateUserRelMapper mapper;

	public GoodsPurTemplateUserRel get(String id)
	{
		return dao.get(GoodsPurTemplateUserRel.class, id);	
	}	

	public GoodsPurTemplateUserRel insertAndGet(GoodsPurTemplateUserRel goodsPurTemplateUserRelation) throws Exception 
	{
		return dao.insertAndGet(goodsPurTemplateUserRelation);
	}

	public GoodsPurTemplateUserRel updateAndGet(GoodsPurTemplateUserRel goodsPurTemplateUserRelation) throws Exception {
		return dao.updateAndGet(goodsPurTemplateUserRelation);
	}
	public int updateFields(GoodsPurTemplateUserRel goodsPurTemplateUserRelation,String fieldNames) throws Exception
	{
		return dao.updateFields(goodsPurTemplateUserRelation, fieldNames);
	}
	public int updateNotNullFields(GoodsPurTemplateUserRel goodsPurTemplateUserRelation) throws Exception
	{
		return dao.updateNotNullFields(goodsPurTemplateUserRelation);
	}

	public void delete(GoodsPurTemplateUserRel goodsPurTemplateUserRelation) throws Exception {
		dao.delete(goodsPurTemplateUserRelation);
	}
	
	public GoodsPurTemplateUserRel getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (GoodsPurTemplateUserRel) dao.getByFieldName(GoodsPurTemplateUserRel.class, fieldName, fieldValue);
	}
	
	public List<GoodsPurTemplateUserRel> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<GoodsPurTemplateUserRel>) dao.queryBeanList(GoodsPurTemplateUserRel.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(GoodsPurTemplateUserRel.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<GoodsPurTemplateUserRel> list(GoodsPurTemplateUserRel qbean) throws Exception{		
		
		return mapper.list(qbean);
	}

	public List<GoodsPurTemplateUserRel> selectByGoodsPurTemplateUserRelation(
			GoodsPurTemplateUserRel goodsPurTemplateUserRelation) {
		// TODO Auto-generated method stub
		return mapper.selectByGoodsPurTemplateUserRelation(goodsPurTemplateUserRelation); 
	}
}


