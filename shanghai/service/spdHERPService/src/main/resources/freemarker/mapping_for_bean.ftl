<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${groupPackageName}.dao.mapper.I${className}Mapper"> 
<!-- parameterType传入参数类型     resultMap=自己定义的一个返回类型     resultType返回类型 -->
	<select id="list"  resultMap="${pname}Map" parameterType="${basePackageName}.po.${className}">
		<![CDATA[
		select <#list tm.fields as field ><#if (field_index >0)>,</#if>${field.name}</#list> from ${tableName}
		]]>
		<where>
		<#list tm.fields as field >
		<#if field.pk = true >
			and ${field.name} = ${'#{'}${field.lowerCamelName}}
		</#if>
		</#list>
		</where>
	</select>
 	<resultMap type="${basePackageName}.po.${className}" id="${pname}Map">
 		<#list tm.fields as field >
 		<#if  field.pk = true >
		<id property="${field.lowerCamelName}" column="${field.name}"/>
		<#else>
		<result property="${field.lowerCamelName}"  column="${field.name}"/>
		</#if>
		</#list>
	</resultMap> 
	
	<update id="update" parameterType="${basePackageName}.po.${className}" >
		update ${tableName}
		<set>
		<#list tm.fields as field >
			<#if field.name == "version" >
			version=version+1,
			<#elseif field.pk != true >
			<if test='${field.lowerCamelName} != null'>  
				${field.name} = ${'#{'}${field.lowerCamelName}},
			</if> 
			</#if> 
		</#list>	
		</set>
		<where>
		<#list tm.fields as field >
		<#if field.name == "version" >
			and version= ${'#{'}${field.lowerCamelName}}
		<#elseif field.pk = true >
			and ${field.name} = ${'#{'}${field.lowerCamelName}}
		<#else>			
		</#if>
		</#list>
		</where>
	</update>
</mapper>