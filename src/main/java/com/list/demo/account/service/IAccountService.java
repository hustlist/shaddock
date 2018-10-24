package com.list.demo.account.service;


import java.util.*;
import java.io.Serializable;
import org.springframework.stereotype.Service;

import com.list.demo.account.model.*;
import com.list.demo.account.dao.*;

/**
 * 服务接口
 */
@Service
public interface IAccountService {

	public Account get(Long accountId) throws Exception;
	
	public List<Account> getList(Map<String,Object> map,String orderField, String order) throws Exception;
	
	public int delete(Long accountId) throws Exception;

    public int insert(Account account) throws Exception;

    public int update(Account account) throws Exception;
    
    public long getCount(Map<String,Object> map,String orderField,String order)  throws Exception;

    public List<Account> getPage(Map<String,Object> map,String orderField,String order,Long page,Long rows) throws Exception;

}