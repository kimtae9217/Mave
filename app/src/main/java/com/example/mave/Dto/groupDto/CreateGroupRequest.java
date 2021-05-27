package com.example.mave.Dto.groupDto;

import com.google.gson.annotations.SerializedName;

public class CreateGroupRequest {

    @SerializedName("userId")
    private String userId;

    @SerializedName("groupName")
    private String groupName;

    public CreateGroupRequest() {
    }

    public CreateGroupRequest(String userId, String groupName) {
        this.userId = userId;
        this.groupName = groupName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
