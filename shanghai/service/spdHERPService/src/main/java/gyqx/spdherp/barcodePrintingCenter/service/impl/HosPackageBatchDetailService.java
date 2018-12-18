package gyqx.spdherp.barcodePrintingCenter.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.HosPackageBatchDetail;
import gyqx.spdherp.barcodePrintingCenter.dao.HosPackageBatchDetailDao;
import gyqx.spdherp.barcodePrintingCenter.service.IHosPackageBatchDetailService;

@Service
public class HosPackageBatchDetailService implements IHosPackageBatchDetailService {
	
	@Resource
	private HosPackageBatchDetailDao  dao;
	
	public HosPackageBatchDetail get(String id)
	{
		return dao.get( id);	
	}	

	public HosPackageBatchDetail add(HosPackageBatchDetail hosPackageBatchDetail) throws Exception 
	{
		return dao.insertAndGet(hosPackageBatchDetail);
	}
		
	public HosPackageBatchDetail update(HosPackageBatchDetail hosPackageBatchDetail) throws Exception {
		return dao.updateAndGet(hosPackageBatchDetail);
	}
	public int updateFields(HosPackageBatchDetail hosPackageBatchDetail,String fieldNames) throws Exception
	{
		return dao.updateFields(hosPackageBatchDetail, fieldNames);
	}

	public void delete(HosPackageBatchDetail hosPackageBatchDetail) throws Exception {
		dao.delete(hosPackageBatchDetail);
	}
	
	public HosPackageBatchDetail getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosPackageBatchDetail) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<HosPackageBatchDetail> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosPackageBatchDetail>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<HosPackageBatchDetail> list(HosPackageBatchDetail bean) throws Exception
	{
		return (List<HosPackageBatchDetail>) dao.list(bean);
	}	
	
	public QueryResult<HosPackageBatchDetail> listByPage(QueryInfo<HosPackageBatchDetail> queryInfo) throws Exception{		
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


