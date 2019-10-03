package com.list.demo.user.dao;


import java.util.*;
import java.io.Serializable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.list.demo.user.model.*;

/**
 * 数据访问接口
 */
@Repository
public interface UserDao {

	public User get(@Param("id") Integer id);
	
	public List<User> getList(Map<String,Object> map);
	
	public int delete(@Param("id") Integer id);

    public int insert(User user);

    public int update(User user);
    
    public long getCount(Map<String,Object> map);

    public List<User> getPage(Map<String,Object> map);

}