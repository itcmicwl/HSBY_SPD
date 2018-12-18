package ${packageName}.dao;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;


<#list cls.imports as item>
import ${item.packageName}.${item.className};
</#list>
@Repository
public class ${className} 
{
<#list cls.refs as ritem >
	@Resource
	private ${ritem.classTypeName}  ${ritem.name};
</#list>	

<#list cls.methods as method>
	@Override
	public ${method.returnType.classTypeName} ${method.name}(<#list method.parameters as param><#if (param_index >0)>,</#if>${param.classTypeName} ${param.name}</#list> ) <#if (method.exceptions?size >0) > throws <#list method.exceptions as ex> ${ex.className} </#list> </#if>
	{
		<#list method.codes as codeline>
		${codeline}
		</#list>
	}
</#list>
}


