package com.list.demo.entity.dto;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @ClassName ChatReqDTO
 * @Description 聊天请求实体对象
 * @Author cughu
 * @Date 2019/8/416:09
 * @Version v1.0
 **/
public class ChatReqDTO implements Serializable {

    private String data;

    private String source;

    private String businessType;

    private String businessData;

    private String type;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessData() {
        return businessData;
    }

    public void setBusinessData(String businessData) {
        this.businessData = businessData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ChatReqDTO.class.getSimpleName() + "[", "]")
                .add("data='" + data + "'")
                .add("source='" + source + "'")
                .add("businessType='" + businessType + "'")
                .add("businessData='" + businessData + "'")
                .add("type='" + type + "'")
                .toString();
    }
}
