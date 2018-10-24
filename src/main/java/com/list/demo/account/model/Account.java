package com.list.demo.account.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.*;

public class Account implements Serializable {

	/**
	 * 主键
	 */
	//设置校验规则，主键字段不能为空
	@NotNull(message="{Account.accountId.NotNull}")
	private Long accountId; 

	/**
	 * 账号
	 */
	private String account; 
	/**
	 * 昵称
	 */
	private String nickname; 
	/**
	 * 头像
	 */
	private String imgUrl; 
	/**
	 * 性别
	 */
	private String sex; 
	/**
	 * 电话号码
	 */
	private String phone; 
	/**
	 * 邮箱地址
	 */
	private String email; 
	/**
	 * 有效标志位
	 */
	private String enableFlag; 

	public Long getAccountId()
	{
		return accountId;
	}
	
	public void setAccountId(Long accountId)
	{
		this.accountId = accountId;
	}

	public String getAccount()
	{
		return account;
	}
	
	public void setAccount(String account)
	{
		this.account = account;
	}
	public String getNickname()
	{
		return nickname;
	}
	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public String getImgUrl()
	{
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}
	public String getSex()
	{
		return sex;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEnableFlag()
	{
		return enableFlag;
	}
	
	public void setEnableFlag(String enableFlag)
	{
		this.enableFlag = enableFlag;
	}

}
