package com.list.demo.dao;


import com.list.demo.entity.model.TaskInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 数据访问接口
 */
@Repository
public interface TaskInfoDao {

	public TaskInfo get(@Param("id") Integer id);
	
	public List<TaskInfo> getList(Map<String, Object> map);
	
	public int delete(@Param("id") Integer id);

    public int insert(TaskInfo taskInfo);

    public int update(TaskInfo taskInfo);
    
    public long getCount(Map<String, Object> map);

    public List<TaskInfo> getPage(Map<String, Object> map);

    /**
     * 通过意图编码获取任务信息
     * @param intentCode 意图编码
     * @return 任务信息式图对象
     */
    TaskInfo getTaskInfoByTaskCode(@Param("taskCode") String intentCode);
}