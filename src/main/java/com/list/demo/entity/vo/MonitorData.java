package com.list.demo.entity.vo;

import java.io.Serializable;
import java.util.StringJoiner;

public class MonitorData implements Serializable {

    private String reqUrl;

    private String accIp;

    private String interType;

    private String reqName;

    private String reqSex;

    private String reqAge;

    private String respName;

    private String respClassifier;

    private String respAge;

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getAccIp() {
        return accIp;
    }

    public void setAccIp(String accIp) {
        this.accIp = accIp;
    }

    public String getInterType() {
        return interType;
    }

    public void setInterType(String interType) {
        this.interType = interType;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public String getReqSex() {
        return reqSex;
    }

    public void setReqSex(String reqSex) {
        this.reqSex = reqSex;
    }

    public String getReqAge() {
        return reqAge;
    }

    public void setReqAge(String reqAge) {
        this.reqAge = reqAge;
    }

    public String getRespName() {
        return respName;
    }

    public void setRespName(String respName) {
        this.respName = respName;
    }

    public String getRespClassifier() {
        return respClassifier;
    }

    public void setRespClassifier(String respClassifier) {
        this.respClassifier = respClassifier;
    }

    public String getRespAge() {
        return respAge;
    }

    public void setRespAge(String respAge) {
        this.respAge = respAge;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MonitorData.class.getSimpleName() + "[", "]")
                .add("reqUrl='" + reqUrl + "'")
                .add("accIp='" + accIp + "'")
                .add("interType='" + interType + "'")
                .add("reqName='" + reqName + "'")
                .add("reqSex='" + reqSex + "'")
                .add("reqAge='" + reqAge + "'")
                .add("respName='" + respName + "'")
                .add("respClassifier='" + respClassifier + "'")
                .add("respAge='" + respAge + "'")
                .toString();
    }
}
