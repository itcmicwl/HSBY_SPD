package ${groupPackageName}.service;


<#list imports as item>
import ${item.packageName}.${item.className};
</#list>
import ${basePackageName}.po.${className};

public interface I${className}Service 
{
	${className} get(String id) throws Exception ;
	${className} add(${className} ${pname}) throws Exception ;
	${className} update(${className} ${pname}) throws Exception;
	
	List<${className}> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<${className}> list(${className} bean) throws Exception;
	
	QueryResult<${className}> listByPage(QueryInfo<${className}> queryInfo) throws Exception;	

}


