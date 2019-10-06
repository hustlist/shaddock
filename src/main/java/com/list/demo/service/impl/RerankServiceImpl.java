package com.list.demo.service.impl;

import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.vo.IntentAnswerInfoBo;
import com.list.demo.service.RerankService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RerankServiceImpl
 * @Description 重排序实现类
 * @Author cughu
 * @Date 2019/8/823:50
 * @Version v1.0
 **/
@Service
public class RerankServiceImpl implements RerankService {


    @Override
    public IntentAnswerInfoBo rerankAnser(ChatReqDTO chatReqDTO, List<IntentAnswerInfoBo> answerInfoBoList) {
        return null;
    }
}
