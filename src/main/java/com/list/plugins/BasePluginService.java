package com.list.plugins;

import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.vo.IntentAnswerInfoBo;
import com.list.demo.entity.vo.IntentInfoBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName BasePluginService
 * @Description 抽象服务类
 * @Author cughu
 * @Date 2019/8/1422:44
 * @Version v1.0
 **/
public abstract class BasePluginService  implements Runnable{

    private Logger logger = LoggerFactory.getLogger(BasePluginService.class);

    protected ChatReqDTO chatReqDTO;

    protected IntentInfoBo intentInfoBo;

    protected IntentAnswerInfoBo intentAnswerInfoBo;

    protected CountDownLatch countDownLatch;

    public BasePluginService(ChatReqDTO chatReqDTO, IntentInfoBo intentInfoBo,CountDownLatch countDownLatch) {
        this.chatReqDTO = chatReqDTO;
        this.intentInfoBo = intentInfoBo;
        this.countDownLatch = countDownLatch;
    }

    public ChatReqDTO getChatReqDTO() {
        return chatReqDTO;
    }

    public void setChatReqDTO(ChatReqDTO chatReqDTO) {
        this.chatReqDTO = chatReqDTO;
    }

    public IntentInfoBo getIntentInfoBo() {
        return intentInfoBo;
    }

    public void setIntentInfoBo(IntentInfoBo intentInfoBo) {
        this.intentInfoBo = intentInfoBo;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public IntentAnswerInfoBo getIntentAnswerInfoBo() {
        return intentAnswerInfoBo;
    }

    public void setIntentAnswerInfoBo(IntentAnswerInfoBo intentAnswerInfoBo) {
        this.intentAnswerInfoBo = intentAnswerInfoBo;
    }

    @Override
    public void run() {
        try{
            this.intentAnswerInfoBo = getAnswer();
        }catch(Exception ex){
            logger.error("invoke task error, error msg is {}",ex.getMessage());
        }
        finally {
            this.countDownLatch.countDown();
        }
    }

    /**
     * 根据意图和请求参数获取最终的答案列表
     *
     * @return 得分
     */
    public abstract IntentAnswerInfoBo getAnswer();

}
