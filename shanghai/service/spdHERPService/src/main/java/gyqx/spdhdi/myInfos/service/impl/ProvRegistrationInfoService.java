package gyqx.spdhdi.myInfos.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import common.utils.UserOnlineStateUtils;

import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.po.ProvRegistrationInfo;
import gyqx.spdhdi.myInfos.dao.ProvRegistrationInfoDao;
import gyqx.spdhdi.myInfos.service.IProvRegistrationInfoService;

@Service
public class ProvRegistrationInfoService implements IProvRegistrationInfoService {
	
	@Resource
	private ProvRegistrationInfoDao  dao;
	@Resource
	private UserOnlineStateUtils  userOnlineStateUtils;
	
	public ProvRegistrationInfo get(String id)
	{
		return dao.get( id);	
	}	

	public ProvRegistrationInfo add(ProvRegistrationInfo provRegistrationInfo) throws Exception 
	{
		return dao.insertAndGet(provRegistrationInfo);
	}
		
	public ProvRegistrationInfo update(ProvRegistrationInfo provRegistrationInfo) throws Exception {
		return dao.updateAndGet(provRegistrationInfo);
	}
	public int updateFields(ProvRegistrationInfo provRegistrationInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(provRegistrationInfo, fieldNames);
	}

	public void delete(ProvRegistrationInfo provRegistrationInfo) throws Exception {
		dao.delete(provRegistrationInfo);
	}
	
	public ProvRegistrationInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ProvRegistrationInfo) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<ProvRegistrationInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ProvRegistrationInfo>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<ProvRegistrationInfo> list(ProvRegistrationInfo bean) throws Exception
	{
		return (List<ProvRegistrationInfo>) dao.list(bean);
	}	
	
	public QueryResult<ProvRegistrationInfo> listByPage(QueryInfo<ProvRegistrationInfo> queryInfo) throws Exception{
		ProvRegistrationInfo info = queryInfo.getQueryObject();
		info.setProvId(userOnlineStateUtils.getCurrent().getCorpId());
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(info));
	}

}


