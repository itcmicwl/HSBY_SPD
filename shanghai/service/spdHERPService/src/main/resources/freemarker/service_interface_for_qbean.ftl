package ${groupPackageName}.service;


<#list imports as item>
import ${item.packageName}.${item.className};
</#list>
import ${groupPackageName}.vo.${classNameQ};
import ${groupPackageName}.vo.${classNameQR};

public interface I${classNameQR}Service 
{
	QueryResult<${classNameQR}> listByPage(QueryInfo<${classNameQ}> queryInfo) throws Exception;	
}


