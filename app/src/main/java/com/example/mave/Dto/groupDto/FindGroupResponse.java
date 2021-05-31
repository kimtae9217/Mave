package com.example.mave.Dto.groupDto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FindGroupResponse {
    @SerializedName("groupName")
    private String groupName;
    @SerializedName("flowerCount")
    private int flowerCount;
    @SerializedName("flowerStatus")
    private int flowerStatus;
    @SerializedName("questionTime")
    private String questionTime;
    @SerializedName("diaryDate")
    private int diaryDate;


    public FindGroupResponse() {
    }

    public FindGroupResponse(String groupName, int flowerCount, int flowerStatus, String questionTime, int diaryDate) {
        this.groupName = groupName;
        this.flowerCount = flowerCount;
        this.flowerStatus = flowerStatus;
        this.questionTime = questionTime;
        this.diaryDate = diaryDate;
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

    public String getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(String questionTime) {
        this.questionTime = questionTime;
    }

    public int getDiaryDate() {
        return diaryDate;
    }

    public void setDiaryDate(int diaryDate) {
        this.diaryDate = diaryDate;
    }
}
