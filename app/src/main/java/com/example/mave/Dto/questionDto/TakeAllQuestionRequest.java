package com.example.mave.Dto.questionDto;

import com.google.gson.annotations.SerializedName;

public class TakeAllQuestionRequest {

    @SerializedName("groupId")
    private Long groupId;

    public TakeAllQuestionRequest() {
    }

    public TakeAllQuestionRequest(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
