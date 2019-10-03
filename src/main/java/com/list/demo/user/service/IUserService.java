package com.list.demo.user.service;


import java.util.*;
import java.io.Serializable;
import org.springframework.stereotype.Service;

import com.list.demo.user.model.*;
import com.list.demo.user.dao.*;

/**
 * 服务接口
 */
@Service
public interface IUserService {

	public User get(Integer id) throws Exception;
	
	public List<User> getList(Map<String,Object> map,String orderField, String order) throws Exception;
	
	public int delete(Integer id) throws Exception;

    public int insert(User user) throws Exception;

    public int update(User user) throws Exception;
    
    public long getCount(Map<String,Object> map,String orderField,String order)  throws Exception;

    public List<User> getPage(Map<String,Object> map,String orderField,String order,Long page,Long rows) throws Exception;

}