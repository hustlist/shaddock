package ${boInfo.packageName}.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.*;

public class ${boInfo.boName} implements Serializable {

<#list boInfo.pks as field>
	/**
	 * ${field.dbFieldComment}
	 */
	//设置校验规则，主键字段不能为空
	@NotNull(message="{${boInfo.boName}.${field.fieldName}.NotNull}")
<#if field.fieldType="java.util.Date">
	//指定spring mvc绑定日期类型请求参数时使用的格式/转换为JSON字符串的格式
	@JsonFormat(patter="yyyy-MM-dd HH:mm:ss")
</#if>
	private ${field.fieldType} ${field.fieldName}; 
</#list>

<#list boInfo.fields as field><#if field.isDbPK()=false>
	/**
	 * ${field.dbFieldComment}
	 */
<#if field.fieldType="java.util.Date">
	//指定spring mvc绑定日期类型请求参数时使用的格式/转换为JSON字符串的格式
	@JsonFormat(patter="yyyy-MM-dd HH:mm:ss")
</#if>
	private ${field.fieldType} ${field.fieldName}; 
</#if></#list>

<#list boInfo.pks as field>
	public ${field.fieldType} get<@upperFC>${field.fieldName}</@upperFC>()
	{
		return ${field.fieldName};
	}
	
	public void set<@upperFC>${field.fieldName}</@upperFC>(${field.fieldType} ${field.fieldName})
	{
		this.${field.fieldName} = ${field.fieldName};
	}
</#list>

<#list boInfo.fields as field><#if field.isDbPK()=false>
	public ${field.fieldType} get<@upperFC>${field.fieldName}</@upperFC>()
	{
		return ${field.fieldName};
	}
	
	public void set<@upperFC>${field.fieldName}</@upperFC>(${field.fieldType} ${field.fieldName})
	{
		this.${field.fieldName} = ${field.fieldName};
	}
</#if></#list>

}
