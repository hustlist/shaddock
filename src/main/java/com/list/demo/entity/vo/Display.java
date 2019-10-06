package com.list.demo.entity.vo;

/**
 * @ClassName Display
 * @Description 前端页面展示类型
 * @Author cughu
 * @Date 2019/8/922:45
 * @Version v1.0
 **/
public class Display {

    private String type;

    private String answer;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Display{");
        sb.append("type='").append(type).append('\'');
        sb.append(", answer='").append(answer).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
