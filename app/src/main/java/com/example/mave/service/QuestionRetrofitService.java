package com.example.mave.service;


import com.example.mave.Dto.questionDto.TakeQuestionRequest;
import com.example.mave.Dto.questionDto.TakeQuestionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuestionRetrofitService {

    @POST("api/question/{questionNumber}")
    Call<TakeQuestionResponse> takeQuestion(@Path("questionNumber") Long questionNumber,
                                            @Body TakeQuestionRequest request);
}
