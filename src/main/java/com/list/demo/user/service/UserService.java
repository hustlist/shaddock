package com.list.demo.user.service;


import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.list.demo.user.model.*;
import com.list.demo.user.dao.*;

/**
 * 服务接口
 */
@Service
public class UserService implements IUserService{

	@Autowired
	private UserDao userDao = null;


	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	/**
	 * 根据主键获取实体对象
	 */
	public User get(Integer id) throws Exception
	{
		return userDao.get(id);
	}
	
	/**
	 * 获取符合条件的实体列表，按指定属性排序
	 */
	public List<User> getList(Map<String,Object> map,String orderField, String order) throws Exception
	{
		List<User> list = null;
		
		map.put("orderField",orderField);
		map.put("order",order);
		
		list = userDao.getList(map);
		
		return list;
	}

	/**
	 * 删除指定记录
	 */
	@Transactional(propagation = Propagation.SUPPORTS, isolation=Isolation.READ_UNCOMMITTED)
	public int delete(Integer id) throws Exception
	{
		return userDao.delete(id);
	}

	/**
	 * 新增指定记录
	 */	
	@Transactional(propagation = Propagation.SUPPORTS, isolation=Isolation.READ_UNCOMMITTED)
    public int insert(User user) throws Exception
    {
    	return userDao.insert(user);
    }

	/**
	 * 修改指定记录
	 */	
	@Transactional(propagation = Propagation.SUPPORTS, isolation=Isolation.READ_UNCOMMITTED)
    public int update(User user) throws Exception
    {
    	return userDao.update(user);
    }
    
    /**
	 * 获取符合条件的记录数量
	 */	
    public long getCount(Map<String,Object> map,String orderField,String order)  throws Exception
    {
    	return userDao.getCount(map);
    }


    public List<User> getPage(Map<String,Object> map,String orderField,String order,Long page,Long rows) throws Exception
    {
    	List<User> list = null;
		
		map.put("orderField",orderField);
		map.put("order",order);
		map.put("startRow",(page-1)*rows);
		map.put("rowSize",rows);
		
		list = userDao.getPage(map);
		
		return list;
    }

}