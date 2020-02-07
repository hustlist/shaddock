package com.list.demo.service;

import com.list.demo.entity.vo.UserInfoReq;
import com.list.demo.entity.vo.UserInfoResp;

public interface UserInfoService {

    /**
     * 获取用户信息
     *
     * @param userInfoReq
     * @return
     */
    UserInfoResp getUserInfo(UserInfoReq userInfoReq);


    /**
     * 发送kafka消息
     *
     * @param msg 消息内容
     * @return 发送是否成功
     */
    String sendKafkaMsgDemo(String msg);
}
