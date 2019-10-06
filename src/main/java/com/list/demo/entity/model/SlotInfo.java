package com.list.demo.entity.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SlotInfo implements Serializable {

	/**
	 * 主键
	 */
	//设置校验规则，主键字段不能为空
	@NotNull(message="{SlotInfo.id.NotNull}")
	private Integer id; 

	/**
	 * 词槽编码
	 */
	private String slotCode; 
	/**
	 * 词槽名称
	 */
	private String slotName; 
	/**
	 * 词槽类型（0，全局；1仅限此任务）
	 */
	private String slotType; 
	/**
	 * 是否有效标志位
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

	public String getSlotCode()
	{
		return slotCode;
	}
	
	public void setSlotCode(String slotCode)
	{
		this.slotCode = slotCode;
	}
	public String getSlotName()
	{
		return slotName;
	}
	
	public void setSlotName(String slotName)
	{
		this.slotName = slotName;
	}
	public String getSlotType()
	{
		return slotType;
	}
	
	public void setSlotType(String slotType)
	{
		this.slotType = slotType;
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
