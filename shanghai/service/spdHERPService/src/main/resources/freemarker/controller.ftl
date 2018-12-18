package ${packageName};


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.web.AjaxResult;
import common.web.BaseController;

<#list cls.imports as item>
import ${item.packageName}.${item.className};
</#list>

@Controller
@RequestMapping(value = "${url}")
public class ${className}Controller extends BaseController 
{
<#list cls.refs as ritem >
	@Resource
	private ${ritem.classTypeName}  ${ritem.name};
</#list>	

<#list cls.methods as method>
	@RequestMapping(value = "${method.name}")
	@ResponseBody
	public AjaxResult ${method.name}(<#list method.parameters as param><#if (param_index >0)>,</#if>@RequestBody @Valid ${param.classTypeName} ${param.pname}</#list><#if (method.parameters?size >0)> ,Errors error </#if> ) <#if (method.exceptions?size >0) > throws <#list method.exceptions as ex> ${ex.className} </#list> </#if><#if (method.exceptions?size==0 && method.parameters?size >0) >throws  Exception</#if>
	{
		AjaxResult result = new AjaxResult();
		<#if (method.parameters?size >0)>
		filterErrors(error);
		</#if>
		<#if method.returnType.name =="void" >
		${cls.name}.${method.name}(<#list method.parameters as param><#if (param_index >0)>,</#if>${param.pname}</#list>);
		<#elseif method.returnType.classTypeName == "<T> T" >
		Object ret = ${cls.name}.${method.name}(<#list method.parameters as param><#if (param_index >0)>,</#if>${param.pname}</#list>);
		result.setData(ret);
		<#else>
		${method.returnType.classTypeName} ret = ${cls.name}.${method.name}(<#list method.parameters as param><#if (param_index >0) >,</#if>${param.pname}</#list>);
		result.setData(ret);
		</#if>
		return result;
	}
</#list>
}
