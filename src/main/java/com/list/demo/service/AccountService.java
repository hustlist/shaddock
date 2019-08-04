package com.list.demo.service;


import java.util.*;

import com.list.demo.dao.AccountDao;
import com.list.demo.model.Account;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 服务接口
 */
@Service
public class AccountService implements IAccountService{

	@Autowired
	private AccountDao accountDao = null;
	
	public void setAccountDao(AccountDao accountDao)
	{
		this.accountDao = accountDao;
	}

	/**
	 * 根据主键获取实体对象
	 */
	public Account get(Long accountId) throws Exception
	{
		return accountDao.get(accountId);
	}
	
	/**
	 * 获取符合条件的实体列表，按指定属性排序
	 */
	public List<Account> getList(Map<String,Object> map,String orderField, String order) throws Exception
	{
		List<Account> list = null;
		
		map.put("orderField",orderField);
		map.put("order",order);
		
		list = accountDao.getList(map);
		
		return list;
	}

	/**
	 * 删除指定记录
	 */
	@Transactional(propagation = Propagation.SUPPORTS, isolation=Isolation.READ_UNCOMMITTED)
	public int delete(Long accountId) throws Exception
	{
		return accountDao.delete(accountId);
	}

	/**
	 * 新增指定记录
	 */	
	@Transactional(propagation = Propagation.SUPPORTS, isolation=Isolation.READ_UNCOMMITTED)
    public int insert(Account account) throws Exception
    {
    	return accountDao.insert(account);
    }

	/**
	 * 修改指定记录
	 */	
	@Transactional(propagation = Propagation.SUPPORTS, isolation=Isolation.READ_UNCOMMITTED)
    public int update(Account account) throws Exception
    {
    	return accountDao.update(account);
    }
    
    /**
	 * 获取符合条件的记录数量
	 */	
    public long getCount(Map<String,Object> map,String orderField,String order)  throws Exception
    {
    	return accountDao.getCount(map);
    }


    public List<Account> getPage(Map<String,Object> map,String orderField,String order,Long page,Long rows) throws Exception
    {
    	List<Account> list = null;
		
		map.put("orderField",orderField);
		map.put("order",order);
		map.put("startRow",(page-1)*rows);
		map.put("rowSize",rows);
		
		list = accountDao.getPage(map);
		
		return list;
    }

}