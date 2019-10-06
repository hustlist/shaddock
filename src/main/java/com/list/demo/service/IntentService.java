package com.list.demo.service;

import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.vo.IntentInfoBo;

import java.util.List;

/**
 * @ClassName IntentService
 * @Description 意图识别接口类
 * @Author cughu
 * @Date 2019/8/420:43
 * @Version v1.0
 **/
public interface IntentService {

    /**
     * 通过请求获取意图列表
     *
     * @param chatReqDTO 请求数据
     * @return 意图列表
     */
    List<IntentInfoBo> getIntent(ChatReqDTO chatReqDTO);

}
