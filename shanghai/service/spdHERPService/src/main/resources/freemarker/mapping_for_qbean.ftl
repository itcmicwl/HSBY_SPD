<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${groupPackageName}.dao.mapper.I${classNameQR}Mapper"> 

	<select id="list"  resultMap="${pname}Map" parameterType="${groupPackageName}.vo.${classNameQ}">
		<![CDATA[
		${sql}
		]]>
		<where>		

		</where>
	</select>
 	<resultMap type="${groupPackageName}.vo.${classNameQR}" id="${pname}Map">
 		<#list tm.fields as field >
 		<#if  field.pk = true >
		<id property="${field.name}" column="${field.fieldName}"/>
		<#else>
		<result property="${field.name}"  column="${field.fieldName}"/>
		</#if>
		</#list>
	</resultMap> 	
	
</mapper>