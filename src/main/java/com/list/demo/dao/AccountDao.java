package com.list.demo.dao;


import java.util.*;

import com.list.demo.model.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 数据访问接口
 */
@Repository
public interface AccountDao {

	public Account get(@Param("accountId") Long accountId);
	
	public List<Account> getList(Map<String,Object> map);
	
	public int delete(@Param("accountId") Long accountId);

    public int insert(Account account);

    public int update(Account account);
    
    public long getCount(Map<String,Object> map);

    public List<Account> getPage(Map<String,Object> map);

}