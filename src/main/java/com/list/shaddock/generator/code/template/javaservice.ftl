package ${boInfo.packageName}.service;


import java.util.*;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${boInfo.packageName}.model.*;
import ${boInfo.packageName}.dao.*;

/**
 * 服务接口
 */
@Service
public class ${boInfo.serviceName} implements ${boInfo.serviceInfName}{

	@Autowired
	private ${boInfo.daoInfName} <@lowerFC>${boInfo.daoName}</@lowerFC> = null;
	
	public void set${boInfo.daoName}(${boInfo.daoInfName} <@lowerFC>${boInfo.daoName}</@lowerFC>)
	{
		this.<@lowerFC>${boInfo.daoName}</@lowerFC> = <@lowerFC>${boInfo.daoName}</@lowerFC>;
	}

	/**
	 * 根据主键获取实体对象
	 */
	public ${boInfo.boName} get(<#list boInfo.pks as pkfield>${pkfield.fieldType} ${pkfield.fieldName}<#if pkfield_has_next>,</#if></#list>) throws Exception
	{
		return <@lowerFC>${boInfo.daoName}</@lowerFC>.get(<#list boInfo.pks as pkfield>${pkfield.fieldName}<#if pkfield_has_next>,</#if></#list>);
	}
	
	/**
	 * 获取符合条件的实体列表，按指定属性排序
	 */
	public List<${boInfo.boName}> getList(Map<String,Object> map,String orderField, String order) throws Exception
	{
		List<${boInfo.boName}> list = null;
		
		map.put("orderField",orderField);
		map.put("order",order);
		
		list = <@lowerFC>${boInfo.daoName}</@lowerFC>.getList(map);
		
		return list;
	}

	/**
	 * 删除指定记录
	 */
	@Transactional(propagation = Propagation.SUPPORTS, isolation=Isolation.READ_UNCOMMITTED)
	public int delete(<#list boInfo.pks as pkfield>${pkfield.fieldType} ${pkfield.fieldName}<#if pkfield_has_next>,</#if></#list>) throws Exception
	{
		return <@lowerFC>${boInfo.daoName}</@lowerFC>.delete(<#list boInfo.pks as pkfield>${pkfield.fieldName}<#if pkfield_has_next>,</#if></#list>);
	}

	/**
	 * 新增指定记录
	 */	
	@Transactional(propagation = Propagation.SUPPORTS, isolation=Isolation.READ_UNCOMMITTED)
    public int insert(${boInfo.boName} <@lowerFC>${boInfo.boName}</@lowerFC>) throws Exception
    {
    	return <@lowerFC>${boInfo.daoName}</@lowerFC>.insert(<@lowerFC>${boInfo.boName}</@lowerFC>);
    }

	/**
	 * 修改指定记录
	 */	
	@Transactional(propagation = Propagation.SUPPORTS, isolation=Isolation.READ_UNCOMMITTED)
    public int update(${boInfo.boName} <@lowerFC>${boInfo.boName}</@lowerFC>) throws Exception
    {
    	return <@lowerFC>${boInfo.daoName}</@lowerFC>.update(<@lowerFC>${boInfo.boName}</@lowerFC>);
    }
    
    /**
	 * 获取符合条件的记录数量
	 */	
    public long getCount(Map<String,Object> map,String orderField,String order)  throws Exception
    {
    	return <@lowerFC>${boInfo.daoName}</@lowerFC>.getCount(map);
    }


    public List<${boInfo.boName}> getPage(Map<String,Object> map,String orderField,String order,Long page,Long rows) throws Exception
    {
    	List<${boInfo.boName}> list = null;
		
		map.put("orderField",orderField);
		map.put("order",order);
		map.put("startRow",(page-1)*rows);
		map.put("rowSize",rows);
		
		list = <@lowerFC>${boInfo.daoName}</@lowerFC>.getPage(map);
		
		return list;
    }

}