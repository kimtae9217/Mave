package com.example.mave.Dto.groupDto;

import com.google.gson.annotations.SerializedName;

public class JoinGroupRequest {

    @SerializedName("userId")
    private String userId;

    public JoinGroupRequest() {
    }

    public JoinGroupRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
