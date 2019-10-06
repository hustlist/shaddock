package com.list.demo.entity.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TaskInfo implements Serializable {

	/**
	 * 主键
	 */
	//设置校验规则，主键字段不能为空
	@NotNull(message="{TaskInfo.id.NotNull}")
	private Integer id; 

	/**
	 * 任务编码
	 */
	private String taskCode; 
	/**
	 * 任务名称
	 */
	private String taskName;
	/**
	 * 任务对应的bean名称
	 */
	private String taskBean;
	/**
	 * 版本号
	 */
	private String version;

	public String getTaskBean() {
		return taskBean;
	}

	public void setTaskBean(String taskBean) {
		this.taskBean = taskBean;
	}

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
	public String getTaskName()
	{
		return taskName;
	}
	
	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}
	public String getVersion()
	{
		return version;
	}
	
	public void setVersion(String version)
	{
		this.version = version;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TaskInfo{");
		sb.append("id=").append(id);
		sb.append(", taskCode='").append(taskCode).append('\'');
		sb.append(", taskName='").append(taskName).append('\'');
		sb.append(", taskBean='").append(taskBean).append('\'');
		sb.append(", version='").append(version).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
