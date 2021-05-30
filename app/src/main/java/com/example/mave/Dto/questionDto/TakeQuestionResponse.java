package com.example.mave.Dto.questionDto;

import com.google.gson.annotations.SerializedName;

public class TakeQuestionResponse {

    @SerializedName("questionContent")
    private String questionContent;

    public TakeQuestionResponse() {
    }

    public TakeQuestionResponse(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
