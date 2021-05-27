package com.example.mave.Dto.memeberDto;

import com.google.gson.annotations.SerializedName;

public class JoinMemberResponse {

    @SerializedName("userId")
    private Long userId;

    public JoinMemberResponse() {
    }

    public JoinMemberResponse(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {        return "MemberJoinResponse{" +
                "userId=" + userId +
                '}';
    }
}
