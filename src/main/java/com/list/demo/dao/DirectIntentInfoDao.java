package com.list.demo.dao;


import com.list.demo.entity.model.DirectIntentInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 数据访问接口
 */
@Repository
public interface DirectIntentInfoDao {

	public DirectIntentInfo get(@Param("id") Integer id);
	
	public List<DirectIntentInfo> getList(Map<String, Object> map);
	
	public int delete(@Param("id") Integer id);

    public int insert(DirectIntentInfo directIntentInfo);

    public int update(DirectIntentInfo directIntentInfo);
    
    public long getCount(Map<String, Object> map);

    public List<DirectIntentInfo> getPage(Map<String, Object> map);

}