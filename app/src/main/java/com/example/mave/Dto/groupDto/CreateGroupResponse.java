package com.example.mave.Dto.groupDto;

import com.google.gson.annotations.SerializedName;

public class CreateGroupResponse {

    @SerializedName("groupId")
    private Long groupId;

    public CreateGroupResponse() {
    }

    public CreateGroupResponse(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
