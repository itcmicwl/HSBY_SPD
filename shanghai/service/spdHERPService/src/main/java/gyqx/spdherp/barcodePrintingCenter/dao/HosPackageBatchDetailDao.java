package gyqx.spdherp.barcodePrintingCenter.dao;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.barcodePrintingCenter.dao.mapper.IHosPackageBatchDetailMapper;
import gyqx.spdherp.po.HosPackageBatchDetail;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class HosPackageBatchDetailDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IHosPackageBatchDetailMapper mapper;

	public HosPackageBatchDetail get(String id)
	{
		return dao.get(HosPackageBatchDetail.class, id);	
	}	

	public HosPackageBatchDetail insertAndGet(HosPackageBatchDetail hosPackageBatchDetail) throws Exception 
	{
		return dao.insertAndGet(hosPackageBatchDetail);
	}

	public HosPackageBatchDetail updateAndGet(HosPackageBatchDetail hosPackageBatchDetail) throws Exception {
		return dao.updateAndGet(hosPackageBatchDetail);
	}
	public int updateFields(HosPackageBatchDetail hosPackageBatchDetail,String fieldNames) throws Exception
	{
		return dao.updateFields(hosPackageBatchDetail, fieldNames);
	}
	public int updateNotNullFields(HosPackageBatchDetail hosPackageBatchDetail) throws Exception
	{
		return dao.updateNotNullFields(hosPackageBatchDetail);
	}

	public void delete(HosPackageBatchDetail hosPackageBatchDetail) throws Exception {
		dao.delete(hosPackageBatchDetail);
	}
	
	public HosPackageBatchDetail getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosPackageBatchDetail) dao.getByFieldName(HosPackageBatchDetail.class, fieldName, fieldValue);
	}
	
	public List<HosPackageBatchDetail> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosPackageBatchDetail>) dao.queryBeanList(HosPackageBatchDetail.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(HosPackageBatchDetail.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<HosPackageBatchDetail> list(HosPackageBatchDetail qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


