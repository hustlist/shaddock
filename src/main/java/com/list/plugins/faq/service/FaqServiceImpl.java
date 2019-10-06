package com.list.plugins.faq.service;

import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.vo.IntentAnswerInfoBo;
import com.list.demo.entity.vo.IntentInfoBo;
import com.list.plugins.BasePluginService;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName FaqServiceImpl
 * @Description Faq插件服务
 * @Author cughu
 * @Date 2019/8/14 22:49
 * @Version v1.0
 **/
public class FaqServiceImpl extends BasePluginService {

    public FaqServiceImpl(ChatReqDTO chatReqDTO, IntentInfoBo intentInfoBo, CountDownLatch countDownLatch) {
        super(chatReqDTO, intentInfoBo, countDownLatch);
    }

    @Override
    public IntentAnswerInfoBo getAnswer() {
        return new IntentAnswerInfoBo();
    }

}
