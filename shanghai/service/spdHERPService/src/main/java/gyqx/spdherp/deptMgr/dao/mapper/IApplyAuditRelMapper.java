package gyqx.spdherp.deptMgr.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdherp.po.ApplyAuditRel;
 
public interface IApplyAuditRelMapper {
	int update(ApplyAuditRel bean);
	int insert(ApplyAuditRel bean);
	List<ApplyAuditRel> list(ApplyAuditRel queryInfo) ;
	
	
    int delete(@Param("userId") String userId);
}

