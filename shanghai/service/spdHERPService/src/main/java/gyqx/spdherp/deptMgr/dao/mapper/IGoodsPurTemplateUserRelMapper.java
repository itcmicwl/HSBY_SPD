package gyqx.spdherp.deptMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.GoodsPurTemplateUserRel;
 
public interface IGoodsPurTemplateUserRelMapper {
	int update(GoodsPurTemplateUserRel bean);
	int insert(GoodsPurTemplateUserRel bean);
	List<GoodsPurTemplateUserRel> list(GoodsPurTemplateUserRel queryInfo) ;
	List<GoodsPurTemplateUserRel> selectByGoodsPurTemplateUserRelation(GoodsPurTemplateUserRel goodsPurTemplateUserRelation);
}

