package com.example.mave.service;


import com.example.mave.Dto.questionDto.TakeAllQuestionRequest;
import com.example.mave.Dto.questionDto.TakeAllQuestionResponse;
import com.example.mave.Dto.questionDto.TakeQuestionRequest;
import com.example.mave.Dto.questionDto.TakeQuestionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuestionRetrofitService {

    @POST("api/question/{questionNumber}")
    Call<TakeQuestionResponse> takeQuestion(@Path("questionNumber") Long questionNumber,
                                            @Body TakeQuestionRequest request);

    @POST("/api/allQuestion/{questionNumber}")
    Call<List<TakeAllQuestionResponse>>takeAllQuestion(@Path("questionNumber") Long questionNumber,
                                                       @Body TakeAllQuestionRequest request);
}
