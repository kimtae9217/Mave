package com.example.mave.Dto.customQuestionDto;

import com.google.gson.annotations.SerializedName;

public class  UseCustomRequest {
    @SerializedName("groupId")
    private Long groupId;

    public UseCustomRequest(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
