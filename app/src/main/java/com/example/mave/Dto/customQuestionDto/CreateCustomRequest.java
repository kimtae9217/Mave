package com.example.mave.Dto.customQuestionDto;

import com.google.gson.annotations.SerializedName;

public class CreateCustomRequest {

    @SerializedName("questionContent")
    private String questionContent;

    @SerializedName("diaryDate")
    private Long diaryDate;

    @SerializedName("groupId")
    private Long groupId;

    public CreateCustomRequest(String questionContent, Long diaryDate, Long groupId) {
        this.questionContent = questionContent;
        this.diaryDate = diaryDate;
        this.groupId = groupId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Long getDiaryDate() {
        return diaryDate;
    }

    public void setDiaryDate(Long diaryDate) {
        this.diaryDate = diaryDate;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
