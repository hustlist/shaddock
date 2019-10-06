package com.list.plugins.selectproduct.service;

import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.vo.IntentAnswerInfoBo;
import com.list.demo.entity.vo.IntentInfoBo;
import com.list.plugins.BasePluginService;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName ProductAnalysisServiceImpl
 * @Description 如何选产品实现类
 * @Author cughu
 * @Date 2019/9/1 10:39
 * @Version v1.0
 **/
public class selectProductServiceImpl extends BasePluginService {

    public selectProductServiceImpl(ChatReqDTO chatReqDTO, IntentInfoBo intentInfoBo, CountDownLatch countDownLatch) {
        super(chatReqDTO, intentInfoBo, countDownLatch);
    }

    @Override
    public IntentAnswerInfoBo getAnswer() {
        return new IntentAnswerInfoBo();
    }
}
