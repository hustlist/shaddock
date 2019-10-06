package com.list.demo.entity.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DirectIntentInfo implements Serializable {

	/**
	 * 主键
	 */
	//设置校验规则，主键字段不能为空
	@NotNull(message="{DirectIntentInfo.id.NotNull}")
	private Integer id; 

	/**
	 * 输入类型
	 */
	private String type; 
	/**
	 * 渠道
	 */
	private String source; 
	/**
	 * 业务类型
	 */
	private String businessType; 
	/**
	 * 业务数据
	 */
	private String businessData; 
	/**
	 * 意图
	 */
	private String intent; 
	/**
	 * 是否有效
	 */
	private String enableFlag; 

	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	public String getSource()
	{
		return source;
	}
	
	public void setSource(String source)
	{
		this.source = source;
	}
	public String getBusinessType()
	{
		return businessType;
	}
	
	public void setBusinessType(String businessType)
	{
		this.businessType = businessType;
	}
	public String getBusinessData()
	{
		return businessData;
	}
	
	public void setBusinessData(String businessData)
	{
		this.businessData = businessData;
	}
	public String getIntent()
	{
		return intent;
	}
	
	public void setIntent(String intent)
	{
		this.intent = intent;
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
