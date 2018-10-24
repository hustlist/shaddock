package ${boInfo.packageName}.service;


import java.util.*;
import java.io.Serializable;
import org.springframework.stereotype.Service;

import ${boInfo.packageName}.model.*;
import ${boInfo.packageName}.dao.*;

/**
 * 服务接口
 */
@Service
public interface ${boInfo.serviceInfName} {

	public ${boInfo.boName} get(<#list boInfo.pks as pkfield>${pkfield.fieldType} ${pkfield.fieldName}<#if pkfield_has_next>,</#if></#list>) throws Exception;
	
	public List<${boInfo.boName}> getList(Map<String,Object> map,String orderField, String order) throws Exception;
	
	public int delete(<#list boInfo.pks as pkfield>${pkfield.fieldType} ${pkfield.fieldName}<#if pkfield_has_next>,</#if></#list>) throws Exception;

    public int insert(${boInfo.boName} <@lowerFC>${boInfo.boName}</@lowerFC>) throws Exception;

    public int update(${boInfo.boName} <@lowerFC>${boInfo.boName}</@lowerFC>) throws Exception;
    
    public long getCount(Map<String,Object> map,String orderField,String order)  throws Exception;

    public List<${boInfo.boName}> getPage(Map<String,Object> map,String orderField,String order,Long page,Long rows) throws Exception;

}