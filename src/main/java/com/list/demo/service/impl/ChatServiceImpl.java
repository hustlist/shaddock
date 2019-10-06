package com.list.demo.service.impl;

import com.list.demo.dao.SlotInfoDao;
import com.list.demo.dao.TaskSlotRelDao;
import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.dto.ChatRespDTO;
import com.list.demo.entity.vo.IntentAnswerInfoBo;
import com.list.demo.entity.vo.IntentInfoBo;
import com.list.demo.service.ChatService;
import com.list.demo.service.DistributeService;
import com.list.demo.service.IntentService;
import com.list.demo.service.RerankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ChatServiceImpl
 * @Description 闲聊实现类
 * @Author cughu
 * @Date 2019/8/416:32
 * @Version v1.0
 **/
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    SlotInfoDao slotInfoDao;

    @Autowired
    TaskSlotRelDao taskSlotRelDao;

    @Autowired
    IntentService intentService;

    @Autowired
    DistributeService distributeService;

    @Autowired
    RerankService rerankService;

    @Override
    public ChatRespDTO chat(ChatReqDTO chatReqDTO) {
        List<IntentInfoBo> intentInfoBoList = intentService.getIntent(chatReqDTO);
        List<IntentAnswerInfoBo> answerInfoBoList = distributeService.getAnswer(chatReqDTO, intentInfoBoList);
        IntentAnswerInfoBo intentAnswerInfoBo = rerankService.rerankAnser(chatReqDTO, answerInfoBoList);
        ChatRespDTO chatRespDTO  = convertTmpAnserToRespDTO(intentAnswerInfoBo);
        return chatRespDTO;
    }


    /**
     * 将中间结果转化为最终返回前端结果
     *
     * @param intentAnswerInfoBo 意图答案类型
     * @return 结果类型
     */
    private ChatRespDTO convertTmpAnserToRespDTO(IntentAnswerInfoBo intentAnswerInfoBo) {
        return new ChatRespDTO();
    }
}
