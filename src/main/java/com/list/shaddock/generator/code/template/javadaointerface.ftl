package ${boInfo.packageName}.dao;


import java.util.*;
import java.io.Serializable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import ${boInfo.packageName}.model.*;

/**
 * 数据访问接口
 */
@Repository
public interface ${boInfo.daoInfName} {

	public ${boInfo.boName} get(<#list boInfo.pks as pkfield>@Param("${pkfield.fieldName}") ${pkfield.fieldType} ${pkfield.fieldName}<#if pkfield_has_next>,</#if></#list>);
	
	public List<${boInfo.boName}> getList(Map<String,Object> map);
	
	public int delete(<#list boInfo.pks as pkfield>@Param("${pkfield.fieldName}") ${pkfield.fieldType} ${pkfield.fieldName}<#if pkfield_has_next>,</#if></#list>);

    public int insert(${boInfo.boName} <@lowerFC>${boInfo.boName}</@lowerFC>);

    public int update(${boInfo.boName} <@lowerFC>${boInfo.boName}</@lowerFC>);
    
    public long getCount(Map<String,Object> map);

    public List<${boInfo.boName}> getPage(Map<String,Object> map);

}