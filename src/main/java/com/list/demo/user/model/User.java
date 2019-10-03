package com.list.demo.user.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {

	/**
	 * 
	 */
	//设置校验规则，主键字段不能为空
	@NotNull(message="{User.id.NotNull}")
	private Integer id; 

	/**
	 * 
	 */
	private String name; 
	/**
	 * 
	 */
	private Integer age; 

	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public Integer getAge()
	{
		return age;
	}
	
	public void setAge(Integer age)
	{
		this.age = age;
	}

}
