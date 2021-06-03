package com.example.mave.Dto.customQuestionDto;

import com.google.gson.annotations.SerializedName;

public class CreateCustomResponse {

    @SerializedName("questionContent")
    private String questionContent;

    public CreateCustomResponse() {
    }


    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
