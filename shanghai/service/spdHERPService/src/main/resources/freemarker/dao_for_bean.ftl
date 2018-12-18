package ${groupPackageName}.dao;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import ${groupPackageName}.dao.mapper.I${className}Mapper;
import ${basePackageName}.po.${className};

<#list imports as item>
import ${item.packageName}.${item.className};
</#list>

@Repository
public class ${className}Dao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private I${className}Mapper mapper;

	public ${className} get(String id)
	{
		return dao.get(${className}.class, id);	
	}	

	public ${className} insertAndGet(${className} ${pname}) throws Exception 
	{
		return dao.insertAndGet(${pname});
	}

	public ${className} updateAndGet(${className} ${pname}) throws Exception {
		return dao.updateAndGet(${pname});
	}
	public int updateFields(${className} ${pname},String fieldNames) throws Exception
	{
		return dao.updateFields(${pname}, fieldNames);
	}
	public int updateNotNullFields(${className} ${pname}) throws Exception
	{
		return dao.updateNotNullFields(${pname});
	}

	public void delete(${className} ${pname}) throws Exception {
		dao.delete(${pname});
	}
	
	public ${className} getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (${className}) dao.getByFieldName(${className}.class, fieldName, fieldValue);
	}
	
	public List<${className}> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<${className}>) dao.queryBeanList(${className}.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(${className}.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<${className}> list(${className} qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
}


