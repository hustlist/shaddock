package ${dbconfig.javaPackage};

import java.io.Serializable;
import java.util.*;

import com.list.shaddock.generator.code.annotation.*;

@ModelBusi(rootSrcPath="${dbconfig.rootSrcPath}",packageName="${dbconfig.javaPackage}",boName="${dbconfig.boName}")
@DbTable(name="${dbconfig.table}")
public class ${FileName} implements Serializable {

<#list dbColumns as pkfield><#if pkfield.pk>
	@DbField(name="${pkfield.name}",type="${pkfield.type}",length="${pkfield.length}",precision="${pkfield.precision}",pk=${pkfield.pk?string("true","false")},comment="${pkfield.comment?default("")}")
	@FieldBusi(visible=true,search=true,order=true)
	public ${pkfield.javaType} ${pkfield.javaName};
</#if></#list>

<#list dbColumns as field><#if !field.pk>
	@DbField(name="${field.name}",type="${field.type}",length="${field.length}",precision="${field.precision}",pk=${field.pk?string("true","false")},comment="${field.comment?default("")}")
	@FieldBusi(visible=true,search=${(field_index lt 2)?string("true","false")},order=${(field_index lt 2)?string("true","false")})
	public ${field.javaType} ${field.javaName};
</#if></#list>


<#list dbColumns as pkfield><#if pkfield.pk>
	public ${pkfield.javaType} get<@upperFC>${pkfield.javaName}</@upperFC>()
	{
		return ${pkfield.javaName};
	}
	
	public void set<@upperFC>${pkfield.javaName}</@upperFC>(${pkfield.javaType} ${pkfield.javaName})
	{
		this.${pkfield.javaName} = ${pkfield.javaName};
	}
</#if></#list>

<#list dbColumns as field><#if !field.pk>
	public ${field.javaType} get<@upperFC>${field.javaName}</@upperFC>()
	{
		return ${field.javaName};
	}
	
	public void set<@upperFC>${field.javaName}</@upperFC>(${field.javaType} ${field.javaName})
	{
		this.${field.javaName} = ${field.javaName};
	}
</#if></#list>
}
