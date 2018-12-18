<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 引入接口，只要实现接口的方法就会调用对应接口方法的 select id -->
<mapper namespace="${packageName}.dao.mapper.${className}Mapper"> 
<!-- parameterType传入参数类型     resultMap=自己定义的一个返回类型     resultType返回类型 -->
	<select id="list"  resultMap="userMap" parameterType="${qbean.packageName}.${qbean.className}">
		<![CDATA[${sql}]]>
		<where>

		</where>
	</select>
 	<resultMap type="${qrbean.packageName}.${qrbean.className}" id="userMap">
 		<#list qrbean.fields as field>
 		<#if ( field.pk = true) >
		<id property="${field.name}" column="${field.fieldName}"/>
		<#else>
		<result property="${field.name}"  column="${field.fieldName}"/>
		</#if>>
		</#list>
	</resultMap> 
</mapper>