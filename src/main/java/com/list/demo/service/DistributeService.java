package com.list.demo.service;

import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.vo.IntentAnswerInfoBo;
import com.list.demo.entity.vo.IntentInfoBo;

import java.util.List;

/**
 * @ClassName DistributeService
 * @Description 任务分发接口类
 * @Author cughu
 * @Date 2019/8/823:48
 * @Version v1.0
 **/
public interface DistributeService {

    /**
     *  分发后获取对应答案
     * @param chatReqDTO 请求参数
     * @param intentInfoBoList 意图识别列表
     * @return 返回结果
     */
    List<IntentAnswerInfoBo> getAnswer(ChatReqDTO chatReqDTO, List<IntentInfoBo> intentInfoBoList);
}
