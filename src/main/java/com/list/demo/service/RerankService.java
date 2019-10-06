package com.list.demo.service;

import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.vo.IntentAnswerInfoBo;

import java.util.List;

/**
 * @ClassName RerankService
 * @Description 重排序类
 * @Author cughu
 * @Date 2019/8/823:50
 * @Version v1.0
 **/
public interface RerankService {

    /**
     * 重排序结果集
     *
     * @param chatReqDTO       请求参数
     * @param answerInfoBoList 结果列表
     * @return 最终答案列表
     */
    IntentAnswerInfoBo rerankAnser(ChatReqDTO chatReqDTO, List<IntentAnswerInfoBo> answerInfoBoList);
}
