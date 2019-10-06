package com.list.demo.dao;


import com.list.demo.entity.model.SlotInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 数据访问接口
 */
@Repository
public interface SlotInfoDao {

	public SlotInfo get(@Param("id") Integer id);
	
	public List<SlotInfo> getList(Map<String, Object> map);
	
	public int delete(@Param("id") Integer id);

    public int insert(SlotInfo slotInfo);

    public int update(SlotInfo slotInfo);
    
    public long getCount(Map<String, Object> map);

    public List<SlotInfo> getPage(Map<String, Object> map);

}