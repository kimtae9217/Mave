package com.example.mave.Dto.groupDto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FindGroupResponse {
    @SerializedName("groupId")
    private Long groupId;
    @SerializedName("groupName")
    private String groupName;
    @SerializedName("flowerCount")
    private int flowerCount;
    @SerializedName("flowerStatus")
    private int flowerStatus;
    @SerializedName("questionTime")
    private String questionTime;
    @SerializedName("diaryDate")
    private Long diaryDate;
    @SerializedName("completeDate")
    private Long completeDate;
    @SerializedName("isDateChanged")
    private Boolean isDateChanged;



    public FindGroupResponse() {
    }

    public FindGroupResponse(Long groupId, String groupName, int flowerCount, int flowerStatus, String questionTime, Long diaryDate,Long completeDate, Boolean isDateChanged) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.flowerCount = flowerCount;
        this.flowerStatus = flowerStatus;
        this.questionTime = questionTime;
        this.diaryDate = diaryDate;
        this.completeDate = completeDate;
        this.isDateChanged = isDateChanged;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public Long getDiaryDate() {
        return diaryDate;
    }

    public void setDiaryDate(Long diaryDate) {
        this.diaryDate = diaryDate;
    }

    public Boolean getDateChanged() {
        return isDateChanged;
    }

    public void setDateChanged(Boolean dateChanged) {
        isDateChanged = dateChanged;
    }

    public Long getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Long completeDate) {
        this.completeDate = completeDate;
    }
}
