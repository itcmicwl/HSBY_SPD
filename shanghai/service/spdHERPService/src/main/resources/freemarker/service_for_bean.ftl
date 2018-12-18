package ${groupPackageName}.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
<#list imports as item>
import ${item.packageName}.${item.className};
</#list>
import ${basePackageName}.po.${className};
import ${groupPackageName}.dao.${className}Dao;
import ${groupPackageName}.service.I${className}Service;

@Service
public class ${className}Service implements I${className}Service {
	
	@Resource
	private ${className}Dao  dao;
	
	public ${className} get(String id)
	{
		return dao.get( id);	
	}	

	public ${className} add(${className} ${pname}) throws Exception 
	{
		return dao.insertAndGet(${pname});
	}
		
	public ${className} update(${className} ${pname}) throws Exception {
		return dao.updateAndGet(${pname});
	}
	public int updateFields(${className} ${pname},String fieldNames) throws Exception
	{
		return dao.updateFields(${pname}, fieldNames);
	}

	public void delete(${className} ${pname}) throws Exception {
		dao.delete(${pname});
	}
	
	public ${className} getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (${className}) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<${className}> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<${className}>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<${className}> list(${className} bean) throws Exception
	{
		return (List<${className}>) dao.list(bean);
	}	
	
	public QueryResult<${className}> listByPage(QueryInfo<${className}> queryInfo) throws Exception{		
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


