package gyqx.spdherp.deptMgr.service;


import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.GoodsPurTemplateUserRel;

public interface IGoodsPurTemplateUserRelService 
{
	GoodsPurTemplateUserRel get(String id) throws Exception ;
	GoodsPurTemplateUserRel add(GoodsPurTemplateUserRel templateUserRelationa) throws Exception ;
	GoodsPurTemplateUserRel update(GoodsPurTemplateUserRel goodsPurTemplateUserRelation) throws Exception;
	
	List<GoodsPurTemplateUserRel> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<GoodsPurTemplateUserRel> list(GoodsPurTemplateUserRel bean) throws Exception;
	
	QueryResult<GoodsPurTemplateUserRel> listByPage(QueryInfo<GoodsPurTemplateUserRel> queryInfo) throws Exception;

	List<GoodsPurTemplateUserRel> selectByTempId(String tempId);	

}


