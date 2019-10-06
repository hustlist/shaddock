package com.list.demo.entity.vo;

import com.list.demo.entity.model.SlotInfo;

/**
 * @ClassName IntentInfoBo
 * @Description 意图实体类
 * @Author cughu
 * @Date 2019/8/922:19
 * @Version v1.0
 **/
public class IntentInfoBo {

    private String intentCode;

    private String intentName;

    private String intentBean;

    private SlotInfo slotInfo;

    private float score;

    public String getIntentBean() {
        return intentBean;
    }

    public void setIntentBean(String intentBean) {
        this.intentBean = intentBean;
    }

    public String getIntentCode() {
        return intentCode;
    }

    public void setIntentCode(String intentCode) {
        this.intentCode = intentCode;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public SlotInfo getSlotInfo() {
        return slotInfo;
    }

    public void setSlotInfo(SlotInfo slotInfo) {
        this.slotInfo = slotInfo;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IntentInfoBo{");
        sb.append("intentCode='").append(intentCode).append('\'');
        sb.append(", intentName='").append(intentName).append('\'');
        sb.append(", intentBean='").append(intentBean).append('\'');
        sb.append(", slotInfo=").append(slotInfo);
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
