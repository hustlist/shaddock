package com.list.demo.service.impl;

import com.list.demo.entity.vo.UserInfoReq;
import com.list.demo.entity.vo.UserInfoResp;
import com.list.demo.service.UserInfoService;
import com.list.shaddock.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    protected Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    RedisService redisService;

    @Override
//    @Cacheable(value = "userInfoResp",key = "#userInfoReq.name",condition = "#userInfoReq != null")
    public UserInfoResp getUserInfo(UserInfoReq userInfoReq) {
        logger.info("--------------》读取内存读取内存数据");
        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setAge(userInfoReq.getAge());
        userInfoResp.setClassifier(userInfoReq.getSex());
        userInfoResp.setName(userInfoReq.getName());
        return userInfoResp;
    }

}
