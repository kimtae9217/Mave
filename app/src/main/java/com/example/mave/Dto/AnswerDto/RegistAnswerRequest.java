package com.example.mave.Dto.AnswerDto;

import com.google.gson.annotations.SerializedName;

public class RegistAnswerRequest {

    @SerializedName("userId")
    private String userId;

    @SerializedName("groupId")
    private Long groupId;

    @SerializedName("answer")
    private String answer;

    public RegistAnswerRequest() {
    }

    public RegistAnswerRequest(String userId, Long groupId, String answer) {
        this.userId = userId;
        this.groupId = groupId;
        this.answer = answer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
