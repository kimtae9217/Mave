package com.example.mave.Dto.groupDto;

import com.google.gson.annotations.SerializedName;

public class CreateGroupResponse {

    @SerializedName("groupId")
    private Long groupId;

    @SerializedName("diaryDate")
    private Long diaryDate;

    public CreateGroupResponse() {
    }

    public CreateGroupResponse(Long groupId,Long diaryDate) {
        this.groupId = groupId;
        this.diaryDate = diaryDate;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getDiaryDate() {
        return diaryDate;
    }

    public void setDiaryDate(Long diaryDate) {
        this.diaryDate = diaryDate;
    }
}
