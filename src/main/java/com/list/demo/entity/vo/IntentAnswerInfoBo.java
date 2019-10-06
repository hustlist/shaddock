package com.list.demo.entity.vo;

/**
 * @ClassName IntentAnswerInfoBo
 * @Description 意图对应的答案信息类
 * @Author cughu
 * @Date 2019/8/1422:45
 * @Version v1.0
 **/
public class IntentAnswerInfoBo {

    private IntentInfoBo intentInfoBo;

    private AnswerInfoBo answerInfoBo;

    public IntentInfoBo getIntentInfoBo() {
        return intentInfoBo;
    }

    public void setIntentInfoBo(IntentInfoBo intentInfoBo) {
        this.intentInfoBo = intentInfoBo;
    }

    public AnswerInfoBo getAnswerInfoBo() {
        return answerInfoBo;
    }

    public void setAnswerInfoBo(AnswerInfoBo answerInfoBo) {
        this.answerInfoBo = answerInfoBo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IntentAnswerInfoBo{");
        sb.append("intentInfoBo=").append(intentInfoBo);
        sb.append(", answerInfoBo=").append(answerInfoBo);
        sb.append('}');
        return sb.toString();
    }
}
