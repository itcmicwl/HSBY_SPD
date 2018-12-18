
package ${packageName};

import java.util.*;
import javax.persistence.Column;
import common.db.meta.Title;

<#if bean.remark??>
/**
${bean.remark}
*/
</#if>
<#list bean.annotations as ann>
@${ann.name}<#if ann.param ??>[${ann.param}]</#if>
</#list>
public class ${bean.name} 
{
<#list bean.fields as field>
	<#if field.remark??>
	/**
${field.remark}	*/
	</#if>
	<#list field.annotations as ann>
	@${ann.name}<#if ann.param ??>[${ann.param}]</#if>
	</#list>
	@Column(name="${field.fieldName}")
	@Title("${field.cname}")
	private ${field.javaType} ${field.name} ;
</#list>

<#list bean.fields as field>
	public ${field.javaType} get${field.camelName} ()
	{
		return ${field.name} ;
	}
	
	public void set${field.camelName} (${field.javaType} ${field.name} )
	{
		this.${field.name} = ${field.name};
	}
</#list>
	@Override
	public String toString() {
		return "${bean.name} [" <#list bean.fields as field> +"<#if field_index != 0 >,</#if> ${field.name}=" +  ${field.name}   </#list>
			 + "]";
	}

}