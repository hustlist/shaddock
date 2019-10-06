package com.list.demo.entity.dto;

import java.io.Serializable;

/**
 * @ClassName ChatRespDTO
 * @Description 聊天返回实体对象
 * @Author cughu
 * @Date 2019/8/416:33
 * @Version v1.0
 **/
public class ChatRespDTO implements Serializable {

    private String answer;

    private String cmd;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChatRespDTO{");
        sb.append("answer='").append(answer).append('\'');
        sb.append(", cmd='").append(cmd).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
