package com.example.mave.service;

import com.example.mave.Dto.JoinMemberRequest;
import com.example.mave.Dto.JoinMemberResponse;
import com.example.mave.Dto.LoginRequest;
import com.example.mave.Dto.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MemberRetrofitService {


    @POST("api/members")
    Call<JoinMemberResponse> joinMember(@Body JoinMemberRequest request);

    @POST("api/members/login")
    Call<LoginResponse> login(@Body LoginRequest request);

}
