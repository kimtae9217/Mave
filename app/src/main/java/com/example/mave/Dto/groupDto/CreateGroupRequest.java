package com.example.mave.Dto.groupDto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalTime;

public class CreateGroupRequest {

    @SerializedName("userId")
    private String userId;

    @SerializedName("groupName")
    private String groupName;

    @SerializedName("questionTimeHour")
    private int questionTimeHour;

    @SerializedName("questionTimeMinute")
    private int questionTimeMinute;


    public CreateGroupRequest() {
    }

    public CreateGroupRequest(String userId, String groupName, int questionTimeHour, int questionTimeMinute) {
        this.userId = userId;
        this.groupName = groupName;
        this.questionTimeHour = questionTimeHour;
        this.questionTimeMinute = questionTimeMinute;

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
