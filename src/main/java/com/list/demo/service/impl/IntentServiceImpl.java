package com.list.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.list.demo.dao.DirectIntentInfoDao;
import com.list.demo.dao.TaskInfoDao;
import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.model.DirectIntentInfo;
import com.list.demo.entity.model.TaskInfo;
import com.list.demo.entity.vo.IntentInfoBo;
import com.list.demo.service.IntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IntentServiceImpl
 * @Description 意图识别实现类
 * @Author cughu
 * @Date 2019/8/420:44
 * @Version v1.0
 **/
@Service
public class IntentServiceImpl implements IntentService {

    @Autowired
    DirectIntentInfoDao directIntentInfoDao;

    @Autowired
    TaskInfoDao taskInfoDao;

    @Override
    public List<IntentInfoBo> getIntent(ChatReqDTO chatReqDTO) {
        List<DirectIntentInfo> directIntentInfoList = getDirectIntent(chatReqDTO);
        return convertIntentInfoToIntentBo(directIntentInfoList.subList(0, 1));
    }

    /**
     * @param subList 将数据库中查询获取的意图列表转化为意图识别列表
     * @return 意图识别列表
     */
    private List<IntentInfoBo> convertIntentInfoToIntentBo(List<DirectIntentInfo> subList) {
        List<IntentInfoBo> intentInfoBoList = new ArrayList<>();
        TaskInfo taskInfo = null;
        for (DirectIntentInfo directIntentInfo : subList) {
            IntentInfoBo tmp = new IntentInfoBo();
            tmp.setIntentCode(directIntentInfo.getIntent());
            tmp.setIntentName(directIntentInfo.getIntent());
            taskInfo = taskInfoDao.getTaskInfoByTaskCode(directIntentInfo.getIntent());
            tmp.setIntentBean(taskInfo.getTaskBean());
            tmp.setScore(1.0f);
            intentInfoBoList.add(tmp);
        }
        return intentInfoBoList;
    }

    private List<DirectIntentInfo> getDirectIntent(ChatReqDTO chatReqDTO) {
        Map<String, Object> map = new HashMap<String, Object>(8);
        map.put("source", chatReqDTO.getSource());
        map.put("type", chatReqDTO.getType());
        if (!StringUtils.isEmpty(chatReqDTO.getBusinessType())) {
            map.put("businessType", chatReqDTO.getBusinessType());
        }
        if (!StringUtils.isEmpty(chatReqDTO.getBusinessData())) {
            map.put("businessData", chatReqDTO.getBusinessData());
        }
        return directIntentInfoDao.getList(map);
    }
}
