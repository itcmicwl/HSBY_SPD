package ${packageName}.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import ${packageName}.${cls.className};

<#list cls.imports as item>
import ${item.packageName}.${item.className};
</#list>
@Service
public class ${className} implements ${cls.className} {
<#list cls.refs as ritem >
	@Resource
	private ${ritem.classTypeName}  ${ritem.name};
</#list>
<#list daoRefs as ritem>
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


