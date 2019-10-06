package com.list.demo.entity.vo;

/**
 * @ClassName Content
 * @Description 重排序使用字段
 * @Author cughu
 * @Date 2019/8/922:42
 * @Version v1.0
 **/
public class Content {

    private String answer;

    private String matchQuestion;

    private String stdQuestion;

    private String taskName;

    private String taskCode;

    private String taskNodeName;

    private String taskNodeCode;

    private float score;

    private String status;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMatchQuestion() {
        return matchQuestion;
    }

    public void setMatchQuestion(String matchQuestion) {
        this.matchQuestion = matchQuestion;
    }

    public String getStdQuestion() {
        return stdQuestion;
    }

    public void setStdQuestion(String stdQuestion) {
        this.stdQuestion = stdQuestion;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskNodeName() {
        return taskNodeName;
    }

    public void setTaskNodeName(String taskNodeName) {
        this.taskNodeName = taskNodeName;
    }

    public String getTaskNodeCode() {
        return taskNodeCode;
    }

    public void setTaskNodeCode(String taskNodeCode) {
        this.taskNodeCode = taskNodeCode;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Content{");
        sb.append("answer='").append(answer).append('\'');
        sb.append(", matchQuestion='").append(matchQuestion).append('\'');
        sb.append(", stdQuestion='").append(stdQuestion).append('\'');
        sb.append(", taskName='").append(taskName).append('\'');
        sb.append(", taskCode='").append(taskCode).append('\'');
        sb.append(", taskNodeName='").append(taskNodeName).append('\'');
        sb.append(", taskNodeCode='").append(taskNodeCode).append('\'');
        sb.append(", score=").append(score);
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
