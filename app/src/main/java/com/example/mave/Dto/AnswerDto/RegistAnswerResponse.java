package com.example.mave.Dto.AnswerDto;

import com.google.gson.annotations.SerializedName;

public class RegistAnswerResponse {

    @SerializedName("answer")
    private String answer;

    @SerializedName("isFinish")
    private Boolean isFinish;

    public RegistAnswerResponse() {
    }

    public RegistAnswerResponse(String answer, Boolean isFinish) {
        this.answer = answer;
        this.isFinish = isFinish;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getFinish() {
        return isFinish;
    }

    public void setFinish(Boolean finish) {
        isFinish = finish;
    }
}
