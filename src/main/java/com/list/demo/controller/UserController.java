package com.list.demo.controller;

import com.list.demo.entity.vo.ResultData;
import com.list.demo.entity.vo.UserInfoReq;
import com.list.demo.entity.vo.UserInfoResp;
import com.list.demo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("spring2.1.4样例")
@RestController
@RequestMapping("/user")
public class UserController {

    //日志对象
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    UserInfoService userInfoService;

    @ApiOperation(value = "打招呼", notes = "测试方法")
    @PostMapping("/info")
    public ResultData<UserInfoResp> getUserInfo(@RequestBody @Validated UserInfoReq userInfoReq, BindingResult bindingResult) {
        ResultData<UserInfoResp> resultData = new ResultData<>();
        long startTime = System.currentTimeMillis();
        resultData.setData(userInfoService.getUserInfo(userInfoReq));
        logger.info("==================>耗时：" + (System.currentTimeMillis() - startTime));
        return resultData;
    }

    @GetMapping("/{msg}")
    public ResultData<String> sendKafkaMsgDemo(@PathVariable("msg") String msg){
        ResultData<String> resultData = new ResultData<>();
        long startTime = System.currentTimeMillis();
        resultData.setData(userInfoService.sendKafkaMsgDemo(msg));
        logger.info("==================>耗时：" + (System.currentTimeMillis() - startTime));
        return resultData;
    }

}
