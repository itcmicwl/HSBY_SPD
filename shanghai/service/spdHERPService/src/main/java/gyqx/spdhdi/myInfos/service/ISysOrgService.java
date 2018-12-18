package gyqx.spdhdi.myInfos.service;

import java.util.List;
import java.util.Map;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.platform.po.*;

public interface ISysOrgService 
{
	public SysOrg addOrg(SysOrg org) throws Exception;
	
	public SysOrg updateOrg(SysOrg user) throws Exception;
	
	public QueryResult<SysOrg> listOrg(QueryInfo queryInfo) throws Exception;
	
	public void deleteSysOrg(SysOrg org) throws Exception;	

	public List getAll() throws Exception;	
	public List query(Map<String, Object> map) throws Exception;
	public List getOrgVo(Map<String, Object> map) throws Exception;
    public void stateDeleteOrgs(Map<String, Object> map)throws Exception ;

}
