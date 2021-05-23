package com.example.mave.retrofitUtil;

import com.example.mave.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("loginUser.php")
    Call<ApiResponse> loginUser(
            @Field("user_ID") String user_ID,
            @Field("user_PW") String user_PW);
}
