package com.list.demo.controller;

import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.dto.ChatRespDTO;
import com.list.demo.entity.vo.ResultData;
import com.list.demo.service.ChatService;
import com.list.shaddock.common.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/robot")
public class ChatController {

    //日志对象
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    ChatService chatService;

    /**
     * @return com.example.demo.entity.vo.ResultData<java.lang.String>
     * @Author cughu
     * @Description
     * @Date 10:45 2019/8/3
     * @Param []
     **/
    @PostMapping("/chat")
    public ResultData<ChatRespDTO> chat(@RequestBody @Validated ChatReqDTO chatReqDTO, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        long startTime = System.currentTimeMillis();
        ResultData<ChatRespDTO> resultData = new ResultData<>();
        resultData.setData(chatService.chat(chatReqDTO));
        logger.info("==================>耗时：" ,(System.currentTimeMillis() - startTime));
        return resultData;
    }
}
