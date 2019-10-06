package com.list.demo.entity.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TaskSlotRel implements Serializable {

	/**
	 * 主键
	 */
	//设置校验规则，主键字段不能为空
	@NotNull(message="{TaskSlotRel.id.NotNull}")
	private Integer id; 

	/**
	 * 任务编码
	 */
	private String taskCode; 
	/**
	 * 词槽编码
	 */
	private String slotCode; 
	/**
	 * 有效标志位
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

	public String getTaskCode()
	{
		return taskCode;
	}
	
	public void setTaskCode(String taskCode)
	{
		this.taskCode = taskCode;
	}
	public String getSlotCode()
	{
		return slotCode;
	}
	
	public void setSlotCode(String slotCode)
	{
		this.slotCode = slotCode;
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
