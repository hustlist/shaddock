package com.list.demo.service;

import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.dto.ChatRespDTO;

/**
 * @ClassName ChatService
 * @Description 聊天接口
 * @Author cughu
 * @Date 2019/8/416:32
 * @Version v1.0
 **/
public interface ChatService {

    /**
     * 聊天接口
     * @param chatReqDTO
     * @return
     */
   ChatRespDTO chat(ChatReqDTO chatReqDTO);

}
