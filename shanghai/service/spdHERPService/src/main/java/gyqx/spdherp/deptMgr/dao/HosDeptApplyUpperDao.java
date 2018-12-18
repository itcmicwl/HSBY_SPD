package gyqx.spdherp.deptMgr.dao;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.deptMgr.dao.mapper.IHosDeptApplyUpperMapper;
import gyqx.spdherp.po.HosDeptApplyUpper;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class HosDeptApplyUpperDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IHosDeptApplyUpperMapper mapper;

	public HosDeptApplyUpper get(String id)
	{
		return dao.get(HosDeptApplyUpper.class, id);	
	}	

	public HosDeptApplyUpper insertAndGet(HosDeptApplyUpper hosDeptApplyUpper) throws Exception 
	{
		return dao.insertAndGet(hosDeptApplyUpper);
	}

	public HosDeptApplyUpper updateAndGet(HosDeptApplyUpper hosDeptApplyUpper) throws Exception {
		return dao.updateAndGet(hosDeptApplyUpper);
	}
	public int updateFields(HosDeptApplyUpper hosDeptApplyUpper,String fieldNames) throws Exception
	{
		return dao.updateFields(hosDeptApplyUpper, fieldNames);
	}
	public int updateNotNullFields(HosDeptApplyUpper hosDeptApplyUpper) throws Exception
	{
		return dao.updateNotNullFields(hosDeptApplyUpper);
	}

	public void delete(HosDeptApplyUpper hosDeptApplyUpper) throws Exception {
		dao.delete(hosDeptApplyUpper);
	}
	
	public HosDeptApplyUpper getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosDeptApplyUpper) dao.getByFieldName(HosDeptApplyUpper.class, fieldName, fieldValue);
	}
	
	public List<HosDeptApplyUpper> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosDeptApplyUpper>) dao.queryBeanList(HosDeptApplyUpper.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(HosDeptApplyUpper.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<HosDeptApplyUpper> list(HosDeptApplyUpper qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


