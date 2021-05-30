package com.example.mave.service;

import com.example.mave.Dto.AnswerDto.AllAnswerRequest;
import com.example.mave.Dto.AnswerDto.AllAnswerResponse;
import com.example.mave.Dto.AnswerDto.RegistAnswerRequest;
import com.example.mave.Dto.AnswerDto.RegistAnswerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface AnswerRetrofitService {

    @POST("api/answer/{questionNumber}")
    Call<RegistAnswerResponse> registAnswer(@Path("questionNumber") Long questionNumber,
                                            @Body RegistAnswerRequest request);

    @POST("api/allAnswer/{questionNumber}")
    Call<List<AllAnswerResponse>> allAnswer(@Path("questionNumber") Long questionNumber,
                                            @Body AllAnswerRequest request);
}
