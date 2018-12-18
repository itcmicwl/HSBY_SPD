package gyqx.spdherp.barcodePrintingCenter.dao;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.barcodePrintingCenter.dao.mapper.IHosPackageInfoMapper;
import gyqx.spdherp.po.HosPackageInfo;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class HosPackageInfoDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private IHosPackageInfoMapper mapper;

	public HosPackageInfo get(String id)
	{
		return dao.get(HosPackageInfo.class, id);	
	}

	public List<String> listPackageCodeByBillId(String billId){
		return mapper.listPackageCodeByBillId(billId);
	}

	public HosPackageInfo insertAndGet(HosPackageInfo hosPackageInfo) throws Exception 
	{
		return dao.insertAndGet(hosPackageInfo);
	}

	public HosPackageInfo updateAndGet(HosPackageInfo hosPackageInfo) throws Exception {
		return dao.updateAndGet(hosPackageInfo);
	}
	public int updateFields(HosPackageInfo hosPackageInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(hosPackageInfo, fieldNames);
	}
	public int updateNotNullFields(HosPackageInfo hosPackageInfo) throws Exception
	{
		return dao.updateNotNullFields(hosPackageInfo);
	}

	public void delete(HosPackageInfo hosPackageInfo) throws Exception {
		dao.delete(hosPackageInfo);
	}
	
	public HosPackageInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosPackageInfo) dao.getByFieldName(HosPackageInfo.class, fieldName, fieldValue);
	}
	
	public List<HosPackageInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosPackageInfo>) dao.queryBeanList(HosPackageInfo.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(HosPackageInfo.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<HosPackageInfo> list(HosPackageInfo qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
	public List<HosPackageInfo> listDsPacksByOutBillId(HosPackageInfo qbean) throws Exception{

		return mapper.listDsPacksByOutBillId(qbean);
	}

}


