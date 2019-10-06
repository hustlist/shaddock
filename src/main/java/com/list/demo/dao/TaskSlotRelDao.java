package com.list.demo.dao;


import com.list.demo.entity.model.TaskSlotRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 数据访问接口
 */
@Repository
public interface TaskSlotRelDao {

	public TaskSlotRel get(@Param("id") Integer id);
	
	public List<TaskSlotRel> getList(Map<String, Object> map);
	
	public int delete(@Param("id") Integer id);

    public int insert(TaskSlotRel taskSlotRel);

    public int update(TaskSlotRel taskSlotRel);
    
    public long getCount(Map<String, Object> map);

    public List<TaskSlotRel> getPage(Map<String, Object> map);

}