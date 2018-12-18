package gyqx.spdhdi.myInfos.dao;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdhdi.myInfos.dao.mapper.IProvHosInfoCodeMapper;
import gyqx.spdhdi.myInfos.vo.ProvHosInfoCodeVo;
import gyqx.spdhdi.po.ProvHosInfoCode;

@Repository
public class ProvHosInfoCodeDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IProvHosInfoCodeMapper mapper;

	public ProvHosInfoCode get(String id)
	{
		return dao.get(ProvHosInfoCode.class, id);	
	}	

	public ProvHosInfoCode insertAndGet(ProvHosInfoCode provHosInfoCode) throws Exception 
	{
		return dao.insertAndGet(provHosInfoCode);
	}

	public ProvHosInfoCode updateAndGet(ProvHosInfoCode provHosInfoCode) throws Exception {
		return dao.updateAndGet(provHosInfoCode);
	}
	public int updateFields(ProvHosInfoCode provHosInfoCode,String fieldNames) throws Exception
	{
		return dao.updateFields(provHosInfoCode, fieldNames);
	}
	public int updateNotNullFields(ProvHosInfoCode provHosInfoCode) throws Exception
	{
		return dao.updateNotNullFields(provHosInfoCode);
	}

	public void delete(ProvHosInfoCode provHosInfoCode) throws Exception {
		dao.delete(provHosInfoCode);
	}
	
	public ProvHosInfoCode getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ProvHosInfoCode) dao.getByFieldName(ProvHosInfoCode.class, fieldName, fieldValue);
	}
	
	public List<ProvHosInfoCode> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ProvHosInfoCode>) dao.queryBeanList(ProvHosInfoCode.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(ProvHosInfoCode.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<ProvHosInfoCode> list(ProvHosInfoCode qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
	public List<ProvHosInfoCodeVo> list_PH_code_vo(ProvHosInfoCodeVo qbean) throws Exception{		

		return mapper.list_PH_code_vo(qbean);
	}
}


