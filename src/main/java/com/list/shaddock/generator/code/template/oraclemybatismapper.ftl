<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="${boInfo.packageName}.dao.${boInfo.daoInfName}" >

  <resultMap id="BaseResultMap" type="${boInfo.packageName}.model.${boInfo.boName}" >
  	<#list boInfo.pks as pkfield>
  		<id column="${pkfield.dbFieldName}" property="${pfield.fieldName}" jdbcType="${pkfield.dbFieldType}"/>
  	</#list>
	<#list boInfo.fields as field><#if field.isDbPK()=false>
  		<id column="${field.dbFieldName}" property="${field.fieldName}" jdbcType="${field.dbFieldType}"/>
  	</#if></#list>
  </resultMap>
  
  <sql id="Base_Column_List" >
    <#list boInfo.pks as pkfield>
  		${pkfield.dbFieldName},
  	</#list>
  	<#list boInfo.fields as field><#if field.isDbPK()=false>
  		${field.dbFieldName}<#if field_has_next>,</#if>
  	</#if></#list>
  </sql>
  
  <sql id="Base_Java_List" >
    <#list boInfo.pks as pkfield>
  		${r'#'}{${pkfield.fieldName},jdbcType=${pkfield.dbFieldType}},
  	</#list>
  	<#list boInfo.fields as field><#if field.isDbPK()=false>
  		${r'#'}{${field.fieldName},jdbcType=${field.dbFieldType}},<#if field_has_next>,</#if>
  	</#if></#list>
  </sql>
  
  <select id="get" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from ${boInfo.tableName}
    where 
    <#list boInfo.pks as pkfield>
  		<#if (pkfield_index > 0)> and </#if>${pkfield.dbFieldName}=${r"#"}{${pkfield.fieldName},jdbcType=${pkfield.dbFieldType}}
  	</#list>
  </select>
  
   <select id="getList" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from ${boInfo.tableName}
    where 1=1
    <#list boInfo.fields as field>
    	<#if field.search>
    	<#if field.fieldType=="java.lang.String">
    	<if test="${field.fieldName} != null and ${field.fieldName} != ''"> and t.${field.dbFieldName} = ${r"#"}{${pkfield.fieldName}}</if>
    	<#else>
    	<if test="${field.fieldName} != null"> and t.${field.dbFieldName} = ${r"#"}{${pkfield.fieldName}}</if>
    	</#if>
    	</#if>
  	</#list>
  	
  	<if test="orderField != null">
  		<choose>
 <#list boInfo.fields as field><#if field.order>
 			<when test="orderField=='${field.fieldName}'">order by ${field.dbFieldName}
 				<if test="order != null and order == 'desc'">desc</if>
 			</when>
</#if></#list>
		</choose>
	</if> 			 
  </select>
  
  
  <delete id="delete" >
    delete from ${boInfo.tableName}
    where 
	<#list boInfo.pks as pkfield>
  		<#if (pkfield_index > 0)> and </#if>${pkfield.dbFieldName}=${r"#"}{${pkfield.fieldName},jdbcType=${pkfield.dbFieldType}}
  	</#list>
  </delete>
  
  
  <insert id="insert" parameterType="${boInfo.packageName}.model.${boInfo.boName}" >
    insert into ${boInfo.tableName} 
    ( 
      	<include refid="Base_Column_List" />
    )
    values (
    	<include refid="Base_Java_List" />
    )
  </insert>
  
  <insert id="update" parameterType="${boInfo.packageName}.model.${boInfo.boName}" >
    update ${boInfo.tableName} 
    set
  	<#list boInfo.fields as field><#if field.isDbPK()=false>
  		${field.dbFieldName} = ${r'#'}{${pkfield.fieldName},jdbcType=${pkfield.dbFieldType}}<#if field_has_next>,</#if>
  	</#if></#list>
  	where
  	<#list boInfo.pks as pkfield>
  		<#if (pkfield_index > 0)> and </#if>${pkfield.dbFieldName}=${r"#"}{${pkfield.fieldName},jdbcType=${pkfield.dbFieldType}}
  	</#list>
  </insert>
  
  
  <select id="getCount" parameterType="java.util.Map" resultType="java.lang.Long">
  	select count(1)
  	from ${boInfo.tableName}
  	where 1=1
  	 <#list boInfo.fields as field>
    	<#if field.search>
    	<#if field.fieldType=="java.lang.String">
    	<if test="${field.fieldName} != null and ${field.fieldName} != ''"> and t.${field.dbFieldName} = ${r"#"}{${pkfield.fieldName}}</if>
    	<#else>
    	<if test="${field.fieldName} != null"> and t.${field.dbFieldName} = ${r"#"}{${pkfield.fieldName}}</if>
    	</#if>
    	</#if>
  	</#list>
  </select>
  
   <select id="getPage" resultMap="BaseResultMap" parameterType="java.util.Map">
    select 
    <include refid="Base_Column_List" />
    from ${boInfo.tableName}
    where 1=1
    <#list boInfo.fields as field>
    	<#if field.search>
    	<#if field.fieldType=="java.lang.String">
    	<if test="${field.fieldName} != null and ${field.fieldName} != ''"> and t.${field.dbFieldName} = ${r"#"}{${pkfield.fieldName}}</if>
    	<#else>
    	<if test="${field.fieldName} != null"> and t.${field.dbFieldName} = ${r"#"}{${pkfield.fieldName}}</if>
    	</#if>
    	</#if>
  	</#list>
  	
  	<if test="orderField != null">
  		<choose>
 <#list boInfo.fields as field><#if field.order>
 			<when test="orderField=='${field.fieldName}'">order by ${field.dbFieldName}
 				<if test="order != null and order == 'desc'">desc</if>
 			</when>
</#if></#list>
		</choose>
	</if> 
	<if test="startRow != null and rowSize != null">limit ${r"#"}{startRow}, ${r"#"}{rowSize}</if>	 
  </select>
  
</mapper>