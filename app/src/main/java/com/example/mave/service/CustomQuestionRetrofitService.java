package com.example.mave.service;


import com.example.mave.Dto.customQuestionDto.CreateCustomRequest;
import com.example.mave.Dto.customQuestionDto.CreateCustomResponse;
import com.example.mave.Dto.customQuestionDto.UseCustomRequest;
import com.example.mave.Dto.customQuestionDto.UseCustomResponse;
import com.example.mave.Dto.questionDto.TakeAllQuestionRequest;
import com.example.mave.Dto.questionDto.TakeAllQuestionResponse;
import com.example.mave.Dto.questionDto.TakeQuestionRequest;
import com.example.mave.Dto.questionDto.TakeQuestionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomQuestionRetrofitService {

    @POST("/api/custom")
    Call<CreateCustomResponse> createCustomQuestion(@Body CreateCustomRequest request);

    @POST("/api/custom/search")
    Call<CreateCustomResponse> checkCustom(@Body CreateCustomRequest request);

    @POST("/api/custom/{questionNumber}")
    Call<UseCustomResponse>useCustomQuestion(@Path("questionNumber") Long questionNumber,
                                             @Body UseCustomRequest request);
}
