package com.example.mave.Dto.groupDto;

import com.google.gson.annotations.SerializedName;

public class FindGroupResponse {
    @SerializedName("groupName")
    private String groupName;
    @SerializedName("flowerCount")
    private int flowerCount;
    @SerializedName("flowerStatus")
    private int flowerStatus;

    public FindGroupResponse() {
    }

    public FindGroupResponse(String groupName, int flowerCount, int flowerStatus) {
        this.groupName = groupName;
        this.flowerCount = flowerCount;
        this.flowerStatus = flowerStatus;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getFlowerCount() {
        return flowerCount;
    }

    public void setFlowerCount(int flowerCount) {
        this.flowerCount = flowerCount;
    }

    public int getFlowerStatus() {
        return flowerStatus;
    }

    public void setFlowerStatus(int flowerStatus) {
        this.flowerStatus = flowerStatus;
    }
}
