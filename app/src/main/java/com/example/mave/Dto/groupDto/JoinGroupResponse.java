package com.example.mave.Dto.groupDto;

import com.google.gson.annotations.SerializedName;

public class JoinGroupResponse {

    @SerializedName("groupId")
    private Long groupId;

    public JoinGroupResponse() {
    }

    public JoinGroupResponse(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
