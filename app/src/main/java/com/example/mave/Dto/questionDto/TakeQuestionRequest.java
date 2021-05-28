package com.example.mave.Dto.questionDto;

import com.google.gson.annotations.SerializedName;

public class TakeQuestionRequest {

    @SerializedName("groupId")
    private Long groupId;

    public TakeQuestionRequest() {
    }

    public TakeQuestionRequest(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
