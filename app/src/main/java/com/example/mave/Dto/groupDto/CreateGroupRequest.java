package com.example.mave.Dto.groupDto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class CreateGroupRequest {

    @SerializedName("userId")
    private String userId;

    @SerializedName("groupName")
    private String groupName;

    @SerializedName("questionTime")
    private String questionTime;


    public CreateGroupRequest() {
    }

    public CreateGroupRequest(String userId, String groupName, String questionTime) {
        this.userId = userId;
        this.groupName = groupName;
        this.questionTime = questionTime;

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
