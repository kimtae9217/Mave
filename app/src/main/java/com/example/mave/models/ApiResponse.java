package com.example.mave.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @Expose
    @SerializedName("user_ID")
    private String user_ID;

    @Expose
    @SerializedName("user_PW")
    private String user_PW;


    @Expose
    @SerializedName("nickname")
    private String nickname;

    @Expose
    @SerializedName("success")
    private boolean success;

    @Expose
    @SerializedName("message")
    private String message;


    public String getUser_ID() {
        return user_ID;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUser_PW() {
        return user_PW;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }


}
