package com.list.demo.service;

import com.list.demo.entity.vo.UserInfoReq;
import com.list.demo.entity.vo.UserInfoResp;

public interface UserInfoService {

    /**
     *  获取用户信息
     * @return
     * @param userInfoReq
     */
    UserInfoResp getUserInfo(UserInfoReq userInfoReq);

}
