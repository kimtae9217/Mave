package com.example.mave.Dto.questionDto;

import com.google.gson.annotations.SerializedName;

public class TakeAllQuestionResponse {

    @SerializedName("questionContent")
    private String questionContent;

    public TakeAllQuestionResponse() {
    }

    public TakeAllQuestionResponse(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
