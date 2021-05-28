package com.example.mave.Dto.AnswerDto;

import com.google.gson.annotations.SerializedName;

public class AllAnswerResponse {

    @SerializedName("userId")
    private String userId;

    @SerializedName("answerContent")
    private String answerContent;

    public AllAnswerResponse() {
    }

    public AllAnswerResponse(String userId, String answerContent) {
        this.userId = userId;
        this.answerContent = answerContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}
