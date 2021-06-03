package com.example.mave.Dto.customQuestionDto;

import com.google.gson.annotations.SerializedName;

public class UseCustomResponse {

    @SerializedName("questionContent")
    private String questionContent;

    public UseCustomResponse() {
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
