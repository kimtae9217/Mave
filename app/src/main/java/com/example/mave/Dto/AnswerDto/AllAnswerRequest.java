package com.example.mave.Dto.AnswerDto;

import com.google.gson.annotations.SerializedName;

public class AllAnswerRequest {

    @SerializedName("groupId")
    private Long groupId;

    public AllAnswerRequest() {
    }

    public AllAnswerRequest(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
