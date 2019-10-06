package com.list.demo.entity.vo;

import java.io.Serializable;
import java.util.StringJoiner;

public class UserInfoResp implements Serializable {

    private String name;

    private String age;

    private String classifier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserInfoResp.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("age='" + age + "'")
                .add("classifier='" + classifier + "'")
                .toString();
    }
}
