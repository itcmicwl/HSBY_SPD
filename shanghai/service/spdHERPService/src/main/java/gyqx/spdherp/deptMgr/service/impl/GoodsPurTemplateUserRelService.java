package gyqx.spdherp.deptMgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.deptMgr.dao.GoodsPurTemplateUserRelDao;
import gyqx.spdherp.deptMgr.service.IGoodsPurTemplateUserRelService;
import gyqx.spdherp.po.GoodsPurTemplateUserRel;

@Service
public class GoodsPurTemplateUserRelService implements IGoodsPurTemplateUserRelService {
	
	@Resource
	private GoodsPurTemplateUserRelDao  dao;
	
    @Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	
	public GoodsPurTemplateUserRel get(String id)
	{
		return dao.get( id);	
	}	

	public GoodsPurTemplateUserRel add(GoodsPurTemplateUserRel goodsPurTemplateUserRelation) throws Exception 
	{
		return dao.insertAndGet(goodsPurTemplateUserRelation);
	}
		
	public GoodsPurTemplateUserRel update(GoodsPurTemplateUserRel goodsPurTemplateUserRelation) throws Exception {
		return dao.updateAndGet(goodsPurTemplateUserRelation);
	}
	public int updateFields(GoodsPurTemplateUserRel goodsPurTemplateUserRelation,String fieldNames) throws Exception
	{
		return dao.updateFields(goodsPurTemplateUserRelation, fieldNames);
	}

	public void delete(GoodsPurTemplateUserRel goodsPurTemplateUserRelation) throws Exception {
		dao.delete(goodsPurTemplateUserRelation);
	}
	
	public GoodsPurTemplateUserRel getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (GoodsPurTemplateUserRel) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<GoodsPurTemplateUserRel> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<GoodsPurTemplateUserRel>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<GoodsPurTemplateUserRel> list(GoodsPurTemplateUserRel bean) throws Exception
	{
		return (List<GoodsPurTemplateUserRel>) dao.list(bean);
	}	
	
	public QueryResult<GoodsPurTemplateUserRel> listByPage(QueryInfo<GoodsPurTemplateUserRel> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}
	
	//查询用户
	@Override
	public List<GoodsPurTemplateUserRel> selectByTempId(String tempId) {
		// TODO Auto-generated method stub
	    String hosId = userOnlineStateUtils.getCurrent().getCorpId();
	    String deptId = userOnlineStateUtils.getCurrent().getOrgId();
	    GoodsPurTemplateUserRel goodsPurTemplateUserRelation=new GoodsPurTemplateUserRel();
	    goodsPurTemplateUserRelation.setDeptId(deptId);
	    goodsPurTemplateUserRelation.setHosId(hosId);
	    goodsPurTemplateUserRelation.setTempId(tempId);
	    return   dao.selectByGoodsPurTemplateUserRelation(goodsPurTemplateUserRelation);
	}

}


