package com.example.mave.Dto.memeberDto;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("isLoginOk")
    private Boolean isLoginOk;

    public LoginResponse() {
    }

    public LoginResponse(Boolean isLoginOk) {
        this.isLoginOk = isLoginOk;
    }

    public Boolean getLoginOk() {
        return isLoginOk;
    }

    public void setLoginOk(Boolean loginOk) {
        isLoginOk = loginOk;
    }

}
