package com.list.demo.entity.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.StringJoiner;

@Configuration
@ConfigurationProperties(prefix = "config.demo")
public class UserInfoDTO implements Serializable {

    private String userName;

    private String userNickName;

    private String userAge;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserInfoDTO.class.getSimpleName() + "[", "]")
                .add("userName='" + userName + "'")
                .add("userNickName='" + userNickName + "'")
                .add("userAge='" + userAge + "'")
                .toString();
    }
}
